package algorithm.pso.component;
/*
 * This <PSO-SVM_001> project in package <algorithm.pso.component> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 6:53 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import algorithm.svm.component.Parameter;
import java.util.Comparator;
import java.util.Random;

public class Particle extends ParticleBuilder<Data, Velocity>
{
    public static final Comparator<Particle> particlePbestFitnessDescendingComparator = (p1, p2) -> (int) (p2.pBest.getFitness() - p1.pBest.getFitness());
    private final Setting  setting;
    private final Random   random;
    private final Velocity exploration;
    private final Velocity exploitation;

    public Particle(final Parameter parameter, final Setting setting)
    {
        this.setting = setting;
        this.random = new Random();
        super.data = new Data(parameter);
        super.pBest = new Data(parameter);
        super.velocity = new Velocity(0.0, 0.0, 0.0);
        this.exploration = new Velocity(0.0, 0.0, 0.0);
        this.exploitation = new Velocity(0.0, 0.0, 0.0);
    }

    @Override public void assignPBest()
    {
        if(super.data.getFitness() > super.pBest.getFitness())
        {
            Data.replace(super.data, super.pBest);
        }
    }

    @Override public void calculateVelocity(Data gBest)
    {
        this.exploitation.measureDistance(super.pBest.getPosition(), super.data.getPosition());
        this.exploitation.multiplicate(this.setting.getLocalConfidence() * this.random.nextDouble());

        this.exploration.measureDistance(gBest.getPosition(), super.data.getPosition());
        this.exploration.multiplicate(this.setting.getSwarmConfidence() * this.random.nextDouble());

        super.velocity.addition(this.exploitation);
        super.velocity.addition(this.exploration);

        super.velocity.multiplicate(this.setting.getConstrictionFactor());
    }

    @Override public void updatePosition()
    {
        super.data.getPosition().updatePosition(super.velocity);
    }
}
