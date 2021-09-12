package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.InfrastructureDeparment;

public class Main {
	
	private final static int EXIT_OPTION = 5;
	
	private BufferedReader br;
	
	private InfrastructureDeparment deparment;
	
	public Main() throws IOException {
		deparment = new InfrastructureDeparment();
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String [] juank) throws IOException {
		Main app = new Main();
		int option = 0;
		String line = "";
		
		System.out.println("|| WELCOME TO BILDBOARD DEPARMENT SYSTEM ||\n");
		System.out.println("Please, choose the option that you want to do:");
		System.out.println("( 1 ) Add a new billboard to the system \n( 2 ) Print current Billboards \n( 3 ) Generate a dangerous billboard report \n( 5 ) To exit");
		
		do {
			System.out.print("Option: ");
			line = app.br.readLine();
			option = Integer.parseInt(line);
			
			switch(option) {
			case 1:
				app.addNewBillboard();
				break;
			case 2:
				app.printBildboards();
				break;
			case 3:
				app.printReport();
				break;
			case 5:
				System.out.println("Bye :3, I will see you later");
				break;
			}
			
		}while(option != EXIT_OPTION);
		
	}
	
	public void printBildboards() {
		
		String infoBilboards = " WEIGHT  HEIGHT  IN USE  BRAND\n";
		
		for(int i = 0; i < deparment.getList().size(); i++) {
			
			infoBilboards += " " + deparment.getList().get(i).getWidth() + "  ";
			infoBilboards += " " + deparment.getList().get(i).getHeight() + "  ";
			infoBilboards += " " + deparment.getList().get(i).isInUse() + "  ";
			infoBilboards += " " + deparment.getList().get(i).getBrand() + "  ";
			infoBilboards += "\n";
			
		}
		
		System.out.println(infoBilboards);
		
		System.out.println("Total: " + deparment.getList().size() + " Bildboards");
		
	}
	
	public void addNewBillboard() throws IOException {
		System.out.println("\n===============================\n    Billboard Formulary    \n===============================\n");
		System.out.println("Please fill the following fields about the new Billboard");
		System.out.print("Weight: ");
		String w = br.readLine();
		System.out.print("Height: ");
		String h = br.readLine();
		System.out.print("Is in Use?: ");
		String use = br.readLine();
		System.out.print("What is the brand of the Billboard: ");
		String brand = br.readLine();
		
		double weight = Double.parseDouble(w);
		double height = Double.parseDouble(h);
		boolean inUse = Boolean.parseBoolean(use);
		
		if(deparment.addBillboard(weight, height, inUse, brand)) {
			System.out.println("The billboard has been added successfully");
		} else {
			System.out.println("An error occurred while registering the new Billboard");
		}
		
	}
	
	public void printReport() throws IOException {
		System.out.println(deparment.exportDangerousBillboardReport());
	}
	
	
}
