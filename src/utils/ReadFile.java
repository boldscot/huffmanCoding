package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
	private File file;

	public ReadFile(File file) {
		this.file = file;

		try (BufferedReader br = new BufferedReader(new FileReader(file)) ) {

			String line;

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
