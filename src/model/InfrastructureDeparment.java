package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDeparment {

	private String BILDBOARD_FILE_NAME = "data/BillboardDataExported.csv";
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
			return true;
		} else {
			return false;
		}
		
		
	}

}
