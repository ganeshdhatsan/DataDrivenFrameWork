package DataDrivenFramework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSV {

	@Test
	private void readCSVFile() throws FileNotFoundException, IOException, CsvException {

		String csvFile = System.getProperty("user.dir") + "\\usernameCSV.csv";
		FileReader fr = new FileReader(csvFile);
		CSVReader reader = new CSVReader(fr);
		try {
			List<String[]> records = reader.readAll();

			for (String[] record : records) {
				for (String value : record) {
					System.out.println(value);
				}
				System.out.println();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	private void readCSVFile2() throws FileNotFoundException, IOException, CsvException {

		String csvFile = System.getProperty("user.dir") + "\\usernameCSV.csv";
		FileReader fr = new FileReader(csvFile);
		CSVReader reader = new CSVReader(fr);
		try {
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				// Print values or process them as needed
				for (String value : nextLine) {
					System.out.print(value + " ");
				}
				System.out.println(); // New line after each row
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	private void readCSVFile1() throws FileNotFoundException, IOException, CsvException {

		String csvFile = System.getProperty("user.dir") + "\\usernameCSV.csv";
		FileReader fr = new FileReader(csvFile);
		CSVReader reader = new CSVReader(fr);
		try {
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {

				for (String value : nextLine) {
					String[] split = value.split(";");
					for (String single : split) {
						System.out.println(single);
					}
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
		

}
