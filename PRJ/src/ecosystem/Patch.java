package ecosystem;

import cells.MajorityCell;

public class Patch extends MajorityCell {

	private long eatenTime;
	private int timeToGrow;

	public Patch(Terrain terrain, int row, int col, int timeToGrow) {
		super(terrain, row, col);
		this.timeToGrow = timeToGrow;
		eatenTime = System.currentTimeMillis();
	}

	public void setFertile(){
		state = WorldConstants.PatchType.FERTILE.ordinal();
		eatenTime = System.currentTimeMillis();
	}

	public void regenerate(){
		if (state == WorldConstants.PatchType.FERTILE.ordinal()
				&& System.currentTimeMillis() > (eatenTime + timeToGrow)){
			state = WorldConstants.PatchType.FOOD.ordinal();
		}
	}
}
