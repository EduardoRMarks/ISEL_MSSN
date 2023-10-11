package ecosystem;

import processing.core.PApplet;
import tools.IProcessingApp;
import tools.SubPlot;

public class TestTerrainApp implements IProcessingApp {
	private float[] viewport = {0f, 0f, 1f, 1f};
	private SubPlot plt;
	private Terrain terrain;
	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(WorldConstants.WINDOW, viewport, p.width, p.height);
		terrain = new Terrain(p, plt);
		terrain.setStateColors(getColors(p));
		terrain.initRandom();
		for (int i = 0; i < 5; i++) {
			terrain.majorityRule();
		}
	}

	@Override
	public void mousePressed(PApplet p) {

	}

	@Override
	public void mouseReleased(PApplet p) {

	}

	@Override
	public void mouseDragged(PApplet p) {

	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void draw(PApplet p, float dt) {
		terrain.regenerate();
		terrain.display(p);
	}

	private int[] getColors(PApplet p){
		int colors[] = new int[WorldConstants.NSTATES];
		for (int i = 0; i < WorldConstants.NSTATES; i++) {
			colors[i] = p.color(WorldConstants.TERRAIN_COLORS[i][0],
					WorldConstants.TERRAIN_COLORS[i][1],
					WorldConstants.TERRAIN_COLORS[i][2]);
		}
		return colors;
	}
}
