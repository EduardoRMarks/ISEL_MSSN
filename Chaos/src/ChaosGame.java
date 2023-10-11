import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class ChaosGame implements IProcessingApp{

    PVector currentPoint;
    ArrayList<PVector> initialPoints;

    @Override
    public void setup(PApplet p) {
        initShape(p);
        drawInitialRandomPoint(p);
    }

    @Override
    public void mousePressed(PApplet p) {

    }

    @Override
    public void keyPressed(PApplet p) {

    }

    private void initShape(PApplet p) {
        //Triangulo
        initialPoints =  new ArrayList();
        initialPoints.add(new PVector(0, p.height));
        initialPoints.add(new PVector(p.width/2, 0));
        initialPoints.add(new PVector(p.width, p.height));

        /*initialPoints.add(new PVector(0, 0));
        initialPoints.add(new PVector(p.width, 0));
        initialPoints.add(new PVector(0, p.height));
        initialPoints.add(new PVector(p.width, p.height));*/
        for (PVector p1 : initialPoints){
            p.point(p1.x, p1.y);
        }
    }

    private void drawInitialRandomPoint(PApplet p) {
        PVector randomInitialPoint = new PVector(p.random(p.width), p.random(p.height));
        p.point(randomInitialPoint.x, randomInitialPoint.y);
        currentPoint = randomInitialPoint;
    }



    @Override
    public void draw(PApplet p, float dt) {
        for (int i = 0; i < 100000; i++) {
            //roll dice
            int rand = (int) p.random(initialPoints.size());
            //lerp
            float x = PApplet.lerp(currentPoint.x, initialPoints.get(rand).x, 0.5f);
            float y = PApplet.lerp(currentPoint.y, initialPoints.get(rand).y, 0.5f);

            p.point(x, y);
            currentPoint = new PVector(x, y);
        }
    }
}
