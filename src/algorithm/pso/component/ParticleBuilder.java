package algorithm.pso.component;/*
 * This <PSO-SVM_001> project in package <algorithm.pso.component> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 6:54 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public abstract class ParticleBuilder<Data, Velocity>
{
    public Data     data;
    public Data     pBest;
    public Velocity velocity;

    public abstract void assignPBest();

    public abstract void calculateVelocity(final Data gBest);

    public abstract void updatePosition();
}

