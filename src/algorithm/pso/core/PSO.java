package algorithm.pso.core;

import algorithm.pso.component.Data;
import algorithm.pso.component.Particle;
import algorithm.pso.component.Position;
import algorithm.pso.component.Setting;
import algorithm.pso.component.Velocity;
import algorithm.svm.component.Parameter;
import algorithm.svm.core.SVMLearner;
import dataset.DatasetGenerator;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetConverter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;

/*
 * This <PSO-SVM_001> project in package <algorithm.pso.core> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 9:10 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class PSO extends PSOOperation<Data, Velocity, Particle>
{
    private final Random                                            random;
    private final Setting                                           setting;
    private final Dataset                                           dataset;
    private final DatasetConverter<LinkedHashMap<Integer, Integer>> encoder;
    private final DatasetConverter<LinkedHashMap<Integer, Integer>> decoder;
    private final SVMLearner                                        svm;


    public PSO(final Setting setting, final DatasetGenerator generator)
    {
        this.random = new Random();
        this.setting = setting;
        this.dataset = generator.getDataset();
        this.encoder = generator.getEncoder();
        this.decoder = generator.getDecoder();
        super.particles = new Particle[this.setting.getSwarmSize()];
        this.svm = new SVMLearner(generateRandomParameter(), this.dataset.getTraining(), this.dataset.getTesting());
    }

    @Override public void run()
    {
        this.initializeSwarm();
        this.updateSwarmFitness();
        while(!this.isConditionSatisfied())
        {
            this.updateAllParticlePBest();
            this.assignGBest();
            this.evaluateAllParticle();
            this.updateStoppingCondition();
        }
    }

    @Override public void initializeSwarm()
    {
        this.gBest = new Data(this.generateRandomParameter());
        for(int particleIndex = -1, particleSize = this.setting.getSwarmSize(); ++particleIndex < particleSize; )
        {
            super.particles[particleIndex] = new Particle(this.generateRandomParameter(), this.setting);
        }
    }

    private Parameter generateRandomParameter()
    {
        return new Parameter(
                (this.random.nextDouble() * 0.9) + 0.1,
                (this.random.nextDouble() * 0.99999) + 0.00001,
                (this.random.nextDouble() * 99) + 1,
                this.dataset.getParameter().length,
                this.dataset.getStatuses().length,
                this.setting.getSVMLearningCount());
    }

    @Override public void updateStoppingCondition()
    {
        ++super.iteration;
    }

    @Override public boolean isConditionSatisfied()
    {
        return super.iteration == this.setting.getPSOIteration();
    }

    @Override public void updateSwarmFitness()
    {
        for(final Particle particle : super.particles)
        {
            this.calculateFitness(particle);
        }
    }


    @Override public void calculateFitness(Particle particle)
    {
        final Position position = particle.data.getPosition();
        this.svm.getParameter().updateParameter(position.getAugmentingFactor(), position.getLearningRate(), position.getConstantCost());
        this.svm.doSequentialLearning();
        particle.data.setFitness(this.svm.evaluateStrokeData());
    }

    public void evaluateAllParticle()
    {
        for(final Particle particle : super.particles)
        {
            particle.calculateVelocity(this.gBest);
            particle.updatePosition();
            this.calculateFitness(particle);
        }
    }

    public void updateAllParticlePBest()
    {
        for(final Particle particle : super.particles)
        {
            particle.assignPBest();
        }
    }

    @Override public void assignGBest()
    {
        Arrays.sort(super.particles, Particle.particlePbestFitnessDescendingComparator);

        if(super.particles[0].pBest.getFitness() > super.gBest.getFitness())
        {
            Data.replace(super.particles[0].pBest, super.gBest);
        }
    }
}
