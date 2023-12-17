package data;

public class Human implements IHuman {
	private Integer energy;
	private String type;
	
	public Human() {
		this.energy = 2;
		this.type = "Human";
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

	@Override
	public void useEnergy() {
		this.setEnergy(this.getEnergy() - 1);
	}
	
}
