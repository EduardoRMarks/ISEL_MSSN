package ecosystem;

public class WorldConstants {
	//World
	public final static double[] WINDOW = {-10, 10, -10, 10};

	//Terrain
	public final static int NROWS = 45;
	public final static int NCOLS = 60;

	public static enum PatchType{
		EMPTY, OBSTACLE, FERTILE, FOOD
	}
	public final static double[] PATCH_TYPE_PROB = {0.2f, 0.2f, 0.2f, 0.4f};
	public final static int NSTATES = PatchType.values().length;
	public static int[][] TERRAIN_COLORS = {
			{255, 200, 60}, {102, 54, 12}, {200, 200, 60}, {40, 200, 20}
	};
	public final static float[] REGENERATION_TIME = {10.f, 20.f};


	//Prey Population
	public final static float PREY_SIZE = 0.2f;
	public final static float PREY_VELOCITY = 2f;
	public final static float PREY_FORCE = 7f;
	public final static float PREY_MASS = 0.8f;
	public final static int INI_PREY_POPULATION = 35;
	public final static float INI_PREY_ENERGY = 10f;
	public final static float ENERGY_FROM_PLANT = 4f;
	public final static float PREY_ENERGY_TO_REPRODUCE = 25f;
	public static int[] PREY_COLOR = {80, 100, 255};

	public final static float PREDATOR_SIZE = 0.5f;
	public final static float PREDATOR_VELOCITY = 4f;
	public final static float PREDATOR_FORCE = 20f;
	public final static float PREDATOR_MASS = 1.5f;
	public final static int INI_PREDATOR_POPULATION = 3;
	public final static float INI_PREDATOR_ENERGY = 20f;
	public final static float ENERGY_FROM_PREY = 5.5f;
	public final static float PREDATOR_ENERGY_TO_REPRODUCE = 35f;
	public static int[] PREDATOR_COLOR = {200, 100, 100};


	public final static float MONSTER_SIZE = 1;
	public final static float MONSTER_VELOCITY = 6f;
	public final static float MONSTER_FORCE = 20f;
	public final static float MONSTER_MASS = 1.3f;
	public final static int INI_MONSTER_POPULATION = 1;
	public final static float INI_MONSTER_ENERGY = 30f;
	public final static float ENERGY_FROM_PREDATOR = 8f;
	public static int[] MONSTER_COLOR = {255, 0, 0};


}
