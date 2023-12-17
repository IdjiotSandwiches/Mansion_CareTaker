package data;
import java.util.*;

public class Main {
	
	Scanner o = new Scanner(System.in);
	Random rnd = new Random();
	Vector<Robot> robot = new Vector<Robot>();
	Vector<Human> human = new Vector<Human>();
	Integer moves = 10;
	Integer cooking = rnd.nextInt(6) + 2;
	Integer cleaning = 8 - cooking;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		initData();
		mainMenu();
	}
	
	private void taskAndMoves() {
		System.out.println("Remaining moves: " + moves);
		System.out.println("Cooking tasks: " + cooking);
		System.out.println("Cleaning tasks: " + cleaning);
	}
	
	private void initData() {
		human.addAll(Arrays.asList(
				new Human(), 
				new Human()
		));
	}
	
	private void robotInformation(Robot robotType) {
		if(robotType instanceof ChefRobot) {
			ChefRobot chefRobot = (ChefRobot) robotType;
			System.out.println(String.format("%-20s %-10d", 
					"class " + chefRobot.getType(), chefRobot.getEnergy())
			);
		}
		if(robotType instanceof CleanerRobot) {
			CleanerRobot cleanerRobot = (CleanerRobot) robotType;
			System.out.println(String.format("%-20s %-10d", 
					"class " + cleanerRobot.getType(), cleanerRobot.getEnergy())
			);
		}
	}
	
	private void information() {
		System.out.println("------------------------------");
		System.out.println(
				String.format("%-20s %-10s", "Helper type", "Stamina")
		);
		System.out.println("------------------------------");
		for(Human humanType : human) {
			System.out.println(String.format("%-20s %-10d", 
					"class " + humanType.getType(), humanType.getEnergy())
			);
		}
		for(Robot robotType : robot) {
			robotInformation(robotType);
		}
		System.out.println("------------------------------");
	}
	
	private void mainMenu() {
		Integer choices = 0;
		do {
			taskAndMoves();
			information();
			System.out.println("1. Buy a new robot");
			System.out.println("2. Recharge a robot");
			System.out.println("3. Perform a cooking tasks");
			System.out.println("4. Perform a cleaning tasks");
			System.out.println("5. Exit");
			System.out.print(">> ");
			
			try {
				choices = o.nextInt();
			} 
			catch(Exception e) {
				System.out.println("Invalid input!...");
				o.next();
			}
			finally {
				o.nextLine();
			}
			
			switch(choices) {
				case 1:
					buyRobot();
					break;
				case 2:
					rechargeEnergy();
					break;
				case 3:
					performCooking();
					break;
				case 4:
					performCleaning();
					break;
				default:
					break;
			}
			
		} while(choices != 5);
	}
	
	private void buyRobot() {
		if(robot.size() == 2) {
			System.out.println("You cannot buy more robots!\n");
			return;
		}
		
		Integer choices = 0;
		
		do {
			System.out.println("Choose robot type: ");
			System.out.println("1. Chef Robot");
			System.out.println("2. Cleaner Robot");
			System.out.print(">> ");
			
			try {
				choices = o.nextInt();
			} 
			catch(Exception e) {
				System.out.println("Invalid input!...");
				o.next();
			}
			finally {
				o.nextLine();
			}
			
		} while(choices < 0 || choices > 2);
		
		switch(choices) {
			case 1:
				robot.add(new ChefRobot());
				System.out.println("You bought a chef robot!");
				break;
			case 2:
				robot.add(new CleanerRobot());
				System.out.println("You bought a cleaner robot!");
				break;
		}
		
		System.out.println("Press enter to continue...\n");
		o.nextLine();
	}
	
	private void rechargeEnergy() {
		if(robot.isEmpty()) {
			System.out.println("You do not have any robots!");
			return;
		}
		
		Integer choices = 0;
		
		do {
			Integer i = 0;
			System.out.println("----------------------------------------");
			System.out.println(
					String.format("%-5s %-20s %-10s","No.", "Helper type", "Stamina")
			);
			System.out.println("----------------------------------------");
			for(Robot robotType : robot) {
				System.out.print(String.format("%-5d ", ++i));
				robotInformation(robotType);
			}
			System.out.println("----------------------------------------");
			
			System.out.print("Enter robot number to recharge: ");
			
			try {
				choices = o.nextInt();
			} 
			catch(Exception e) {
				System.out.println("Invalid input!...");
				o.next();
			}
			finally {
				o.nextLine();
			}
			
		} while(choices < 0 || choices > robot.size());
		
		if(robot.get(choices - 1).getEnergy() == 3) {
			System.out.println("This robot has full stamina");
			System.out.println("Press enter to continue...\n");
			o.nextLine();
		}
		else {
			robot.get(choices - 1).rechargeEnergy();
			
			System.out.println("This robot is recharged (+1 stamina)");
			System.out.println("Press enter to continue...\n");
			o.nextLine();
		}
		
		moves--;
		
	}
	
	private void performCooking() {
		
		boolean check = false;
		
		if(cooking == 0) {
			System.out.println("There are no cooking tasks left!\n");
			return;
		}
		
		if(robot.isEmpty()) {
			if(humanWork()) {
				System.out.println("No available human or chef robot helpers to work\n");
				return;
			}
		}
		else {
			for(Robot robotType : robot) {
				if(robotType instanceof ChefRobot) {
					ChefRobot chefRobot = (ChefRobot) robotType;
					if(chefRobot.getEnergy() == 0) {
						check = true;
					}
					else {
						chefRobot.useEnergy();
						System.out.println("Robot finished a cooking task!");
						System.out.println("Press enter to continue...\n");
						check = false;
						o.nextLine();
						break;
					}
				}
				else {
					check = true;
				}
			}
			
		}
		
		if(check && humanWork()) {
			System.out.println("No available human or chef robot helpers to work\n");
			return;
		}
		
		cooking--;
		moves--;
	}
	
	private void performCleaning() {
		
		boolean check = false;
		
		if(cleaning == 0) {
			System.out.println("There are no cooking tasks left!\n");
			return;
		}
		
		if(robot.isEmpty()) {
			if(humanWork()) {
				System.out.println("No available human or chef robot helpers to work\n");
				return;
			}
		}
		else {
			for(Robot robotType : robot) {
				if(robotType instanceof CleanerRobot) {
					CleanerRobot cleanerRobot = (CleanerRobot) robotType;
					if(cleanerRobot.getEnergy() == 0) {
						check = true;
					}
					else {
						cleanerRobot.useEnergy();
						System.out.println("Robot finished a cooking task!");
						System.out.println("Press enter to continue...\n");
						check = false;
						o.nextLine();
						break;
					}
				}
				else {
					check = true;
				}
			}
			
		}
		
		if(check && humanWork()) {
			System.out.println("No available human or chef robot helpers to work\n");
			return;
		}
		
		cleaning--;
		moves--;
	}
	
	private boolean humanWork() {
		
		boolean check = false;
		
		for(Human humanType : human) {
			if(humanType.getEnergy() != 0) {
				humanType.useEnergy();
				System.out.println("Human finished a cooking task!");
				System.out.println("Press enter to continue...\n");
				check = false;
				o.nextLine();
				break;
			}
			else {
				check = true;
			}
		}
		
		return check;
	}
}
