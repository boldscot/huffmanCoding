package controllers;

import java.io.File;
import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import utils.ReadFile;
import utils.WriteFile;

public class Main {
	private static BinaryTree bt;
	private String encodedString;

	public Main () {
		bt  = new BinaryTree();
	}

	public static void main(String [] args) throws IOException {
		Main main  = new Main();
		Shell shell = ShellFactory.createConsoleShell
				("Huffman Coding", "?list for instructions", main);
		shell.commandLoop();
	}
	
	@Command(description= "Read in a file and store it in a string")
	public void readFromFile() throws IOException {
		bt.fileString = ReadFile.readFromFile(new File("data/helloWorld.txt"));
		if (!(bt.fileString == null))
			System.out.println("File Read");
		else
			System.out.println("Error reading file");
	}
	
	@Command(description= "Make nodes and build the tree from the nodes")
	public void buildTree() {
		bt.buildTree();
	}
	
	@Command(description= "Encode the string into a series of 1's and 0's")
	public void encodeText() {
		encodedString = bt.encodeText(bt.fileString);
		if (!(encodedString == null))
			System.out.println("String encoded");
		else
			System.out.println("Error encoding string");
	}
	
	@Command(description= "Decode the string of 1's and 0's back into text")
	public void decodeText() {
		System.out.println(bt.decodeText(encodedString));
	}

	@Command(description= "Write to a file")
	public void writeToFile () throws IOException {
		File dataFile  = new File("data.txt");
		if (!dataFile.isFile())
			WriteFile.writeToFile(bt.fileString);
	}
}
