package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public ReadFile() {}
	
	public static String readFromFile(File file) {
		String str = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file) ) ) {
			String line;
			while ((line = br.readLine()) != null) {
				str+=line+"\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return str;
	}
}
