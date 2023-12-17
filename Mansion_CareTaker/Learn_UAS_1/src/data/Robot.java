package data;

public abstract class Robot implements IRobot {
	private String type;
	private Integer energy;
	
	public Robot(String type) {
		this.energy = 3;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
}
