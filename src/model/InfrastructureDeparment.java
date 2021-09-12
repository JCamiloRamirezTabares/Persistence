package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDeparment {

	private final String BILDBOARD_FILE_NAME = "data/BillboardDataExported.csv";
	private final String REPORT_PATH = "data/report.txt";
	private final String SAVE_PATH = "data/java.bytecode";
	
	private String separatorCharacter = "\\|";
	
	private List<Bildboard> list;
	
	//BufferReader
	private BufferedReader br;

	//Constructor
	public InfrastructureDeparment() throws IOException {
		list = new ArrayList<Bildboard>();
		importData();
	}

	public List<Bildboard> getList() {
		return list;
	}
	
	public void importData() throws IOException {
		br = new BufferedReader(new FileReader(BILDBOARD_FILE_NAME));

		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String parts[] = line.split(separatorCharacter);

			double width = Double.parseDouble(parts[0]);
			double height = Double.parseDouble(parts[1]);
			boolean inUse = Boolean.parseBoolean(parts[2]);

			list.add(new Bildboard(width, height, inUse, parts[3]));
			line = br.readLine();
		}
		br.close();
	}
	
	public boolean addBillboard(double w, double h, boolean inUse, String brand) throws IOException {
		
		Bildboard newBillboard = new Bildboard(w, h, inUse, brand);
		FileWriter fw = new FileWriter(BILDBOARD_FILE_NAME, true);
		fw.write(w + "|" + h + "|" + inUse + "|" + brand + "\n");
		
		fw.close();
		
		if(list.add(newBillboard)) {
			saveBillboards();
			return true;
		} else {
			return false;
		}
		
		
	}
	
	public String generateDangerousReport() {
		String report = "==============================\n= DANGEROUS BILLBOARD REPORT =\n==============================\nThe dangerous billboard are:\n";
		int number = 1;
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).calculateArea() >= 160) {
				report += number + ". Billboard " + list.get(i).getBrand() + " with area " + list.get(i).calculateArea() + " cm^2\n";
				number++;
			}
		}
		
		
		return report;
	}
	
	public String exportDangerousBillboardReport() throws IOException {
		FileWriter fw = new FileWriter(REPORT_PATH, false);
		String report = generateDangerousReport();
		fw.write(report);
		fw.close();
		
		return report;
		
	}
	
	public void saveBillboards() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH));
		oos.writeObject(list);
		oos.close();
	}

}
