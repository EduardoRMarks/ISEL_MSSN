package ecosystem;

import cells.MajorityCA;
import physics.Body;
import processing.core.PApplet;
import tools.SubPlot;

import java.util.ArrayList;
import java.util.List;

public class Terrain extends MajorityCA {
	public Terrain(PApplet p, SubPlot plt) {
		super(p, plt, WorldConstants.NROWS, WorldConstants.NCOLS, WorldConstants.NSTATES, 1);
	}

	@Override
	protected void createCells(){
		int minRT = (int) (WorldConstants.REGENERATION_TIME[0]*1000);
		int maxRT = (int) (WorldConstants.REGENERATION_TIME[1]*1000);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				int timeToGrow = (int) (minRT+(maxRT-minRT)*Math.random());
				cells[i][j] = new Patch(this, i, j, timeToGrow);
			}
		}
		setMooreNeighbors();
	}

	public List<Body> getGrass(){
		List<Body> bodies = new ArrayList<>();
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (cells[i][j].getState() == WorldConstants.PatchType.FOOD.ordinal()){
					Body b = new Body(this.getCenterCell(i, j));
					bodies.add(b);
				}
			}
		}
		return bodies;
	}

	public List<Body> getFire(){
		List<Body> bodies = new ArrayList<>();
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (cells[i][j].getState() == WorldConstants.PatchType.OBSTACLE.ordinal()){
					Body b = new Body(this.getCenterCell(i, j));
					bodies.add(b);
				}
			}
		}
		return bodies;
	}

	public void regenerate(){
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				((Patch)cells[i][j]).regenerate();
			}
		}
	}

	public List<Body> getObstacles() {
		List<Body> bodies = new ArrayList<Body>();
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (cells[i][j].getState() == WorldConstants.PatchType.OBSTACLE.ordinal()){
					Body b = new Body(this.getCenterCell(i, j));
					bodies.add(b);
				}
			}
		}
		return bodies;
	}
}
