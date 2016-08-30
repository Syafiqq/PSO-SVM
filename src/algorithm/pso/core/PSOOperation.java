package algorithm.pso.core;

import algorithm.pso.component.ParticleBuilder;

/*
 * This <PSO-SVM_001> project in package <algorithm.pso.core> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 9:08 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public abstract class PSOOperation<Data, Velocity, Particle extends ParticleBuilder<Data, Velocity>>
{
    public Particle[] particles;
    public Data       gBest;
    public int        iteration;

    public abstract void run();

    public abstract void initializeSwarm();

    public abstract void updateStoppingCondition();

    public abstract boolean isConditionSatisfied();

    public abstract void calculateFitness(Particle particle);

    public abstract void updateSwarmFitness();

    public abstract void assignGBest();
}
