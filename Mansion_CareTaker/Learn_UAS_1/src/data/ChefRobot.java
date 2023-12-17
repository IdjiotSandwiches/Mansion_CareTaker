package data;

public class ChefRobot extends Robot {

	public ChefRobot() {
		super("ChefRobot");
	}
	
	@Override
	public void rechargeEnergy() {
		super.setEnergy(getEnergy() + 1);
	}

	@Override
	public void useEnergy() {
		super.setEnergy(getEnergy() - 1);
	}

}
