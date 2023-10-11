package boids;

public abstract class Behavior implements IBeahvior{

    protected float weight;

    public Behavior (float weight){
        this.weight = weight;
    }


    @Override
    public void setWeight(float weight){
        this.weight = weight;
    }

    @Override
    public float getWeight(){
        return weight;
    }
}
