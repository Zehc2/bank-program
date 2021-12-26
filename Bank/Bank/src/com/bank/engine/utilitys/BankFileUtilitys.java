package com.bank.engine.utilitys;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BankFileUtilitys {
	
	private Path seniorPath = Paths.get("res\\BankInfo.txt");	
	private String path = "res\\BankInfo.txt";
	
	private String BANKBALANCESTRING = "Current Bank Balance: ";
	
	
	public void createBank() {
		try (FileWriter f = new FileWriter(path, true);			
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {
		   	p.println(BANKBALANCESTRING + 200000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getBankTotal( ) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(BANKBALANCESTRING)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
			return Integer.valueOf(returnItem.substring(22, returnItem.length()));
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return 0;
	}
}
