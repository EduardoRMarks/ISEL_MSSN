package ecosystem;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tools.SubPlot;

public class Monster extends Animal{
    protected PImage img;
    protected Monster(PVector pos, float vel, float mForce, float mass, float radius, int color, PApplet p, SubPlot plt, PImage img) {
        super(pos, vel, mForce, mass, radius, color, p, plt);
        energy = WorldConstants.INI_MONSTER_ENERGY;
        this.img = img;
    }

    @Override
    public Animal reproduce(boolean mutate) {
        return null;
    }
    @Override
    public void eat(Terrain terrain, Animal target){
        if (target != null) {
            if (!(target.isDead())) {
                float dist = PVector.dist(pos, target.getPos());
                if (dist < 3 * target.getRadius()) {
                    if(target instanceof Prey){
                        energy += WorldConstants.ENERGY_FROM_PREY;
                    } else if (target instanceof Predator) {
                        energy += WorldConstants.ENERGY_FROM_PREDATOR;
                    }
                    target.setDead(true);
                }
            }
        }
    }

    @Override
    public void display(PApplet p, SubPlot plt) {
        float[] pp = plt.getPixelCoord(pos.x, pos.y);
        //a,b coordenadas da imagem x,y  ;   c,d largura e altura de cada imagem
        p.image(img, pp[0]-30f, pp[1]-30f, 60f, 60f);
    }

    @Override
    public void setImg(PImage img) {
        this.img = img;
    }
}
