package data;

public class CleanerRobot extends Robot {

	public CleanerRobot() {
		super("CleanerRobot");
	}
	
	@Override
	public void rechargeEnergy() {
		super.setEnergy(super.getEnergy() + 1);
	}

	@Override
	public void useEnergy() {
		super.setEnergy(super.getEnergy() - 1);
	}

}
