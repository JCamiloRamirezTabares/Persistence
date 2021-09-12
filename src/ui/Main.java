package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.InfrastructureDeparment;

public class Main {
	
	private final int EXIT_OPTION = 5;
	
	private BufferedReader br;
	
	private InfrastructureDeparment deparment;
	
	public Main() throws IOException {
		deparment = new InfrastructureDeparment();
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String [] juank) throws IOException {
		Main app = new Main();
		int option = 0;
		
		System.out.println("|| WELCOME TO BILDBOARD DEPARMENT SYSTEM ||\n");
		System.out.println("Please, choose the option that you wanna do:");
		System.out.println("( 1 ) Allows add a new Billdboard at the System \n( 2 ) Print the current Billboards \n");
		System.out.print("Option: ");
		String line = app.br.readLine();
		
		option = Integer.parseInt(line);
		
		switch(option) {
			case 1:
				System.out.println("Hola aun estoy vacio");
				break;
			case 2:
				app.printBildboards();
				break;
			
		}
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
		
	}
}
