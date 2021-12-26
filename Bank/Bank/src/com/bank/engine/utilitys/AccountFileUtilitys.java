package com.bank.engine.utilitys;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bank.engine.accounts.Account;
import com.bank.engine.accounts.CheckingAccount;


public class AccountFileUtilitys {

	private final String path = "res\\LOGS.txt";
	
	private final Path seniorPath = Paths.get("res\\LOGS.txt");	
	
	 private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	 private LocalDateTime now = LocalDateTime.now();  
	   
	 private final String PASSWORDSTRING = " PASSWORD: ";
	 private final String USERSTRING = "USER: " ;
	 private final String BALANCESTRING = " balance: ";
	 private final String TRANSACTIONSTRING = " Past Transactions:";
	 private final String BANKIDSTRING = " Bank ID: ";
	 private final String STATE = " State: ";
	 private final String FIRSTNAME = " First Name: ";
	 private final String LASTNAME = " Last Name: ";
	 private final String ZIPCODE = " Zip: ";
	 private final String CITY = " City: ";
	 private final String STREET = " Street: ";
	 private final String HOUSENUMBER = " House Number: ";
	   
	
	public void createNewUser(final Account account) {
		try {
			try (FileWriter f = new FileWriter(path, true);			
	                BufferedWriter b = new BufferedWriter(f);
	                PrintWriter p = new PrintWriter(b);) {
			   	p.println();
	            p.println(USERSTRING + account.getUsername());
	            p.println(account.getUsername() + PASSWORDSTRING + account.getPassword());
	            p.println(account.getUsername() + BALANCESTRING + "0");
	            p.println(account.getUsername() + TRANSACTIONSTRING);
	            p.println(account.getUsername() + BANKIDSTRING + createBankID());	         
	            p.println(account.getUsername() + FIRSTNAME + account.fileUtilGetFirstName());
	            p.println(account.getUsername() + LASTNAME + account.fileUtilGetLastName());
	            p.println(account.getUsername() + STATE + account.fileUtilGetState());
	            p.println(account.getUsername() + ZIPCODE + account.fileUtilGetZipcode());
	            p.println(account.getUsername() + CITY + account.fileUtilGetCity());
	            p.println(account.getUsername() + STREET + account.fileUtilGetStreet());
	            p.println(account.getUsername() + HOUSENUMBER + account.fileUtilGetHouseNumber());	         
		   }
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		   
	}
	
	public Account getUserWithUsername(final String username) {
		 try {
				List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
				String returnItem = null;
				for (int i = 0; i < fileContent.size(); i++) {			
				    if (fileContent.get(i).contains(username + PASSWORDSTRING)) {			   
				    	returnItem = fileContent.get(i);					
						String password = returnItem.substring(username.length() + PASSWORDSTRING.length(), returnItem.length());
						 Account account = new CheckingAccount(username, password);
						return account;
				    }
				}
				Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
			} catch(IOException e) {
				System.out.println("File error!" + e);
			}

		return null;
	}
	
	public boolean userLogin(final Account account)  {
		try {	
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + PASSWORDSTRING + account.getPassword())) {				        
			        return true;		      
			    } 
			}
			
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
			
		} catch(IOException e) {
			System.out.println("File error!" + e);
	}
		return false;
}
	
	public boolean doesUsernameExist(final Account account) {
		try {	
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername())) {				     
			        return true;		      
			    } 
			}			
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);		
		} catch(IOException e) {
			System.out.println("File error!" + e);
	}
		return false;
	}
	
	public String createBankID() {
	    Random r = new Random();
	    int number = r.nextInt(999999);
	    return String.format("%06d", number);
	}
	
	public void closeAccount(final Account account) {
		try {
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername())) {	
			    	fileContent.set(i, "");
			        
			    }
			}
			
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}	
	}
	
	public int getBalance(final Account account) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + BALANCESTRING)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);			
			return Integer.valueOf(returnItem.substring(account.getUsername().length() + BALANCESTRING.length(), returnItem.length()));
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return 0;
	}
	
	public String getFirstName(final Account account) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + FIRSTNAME)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);			
			return returnItem.substring(account.getUsername().length() + FIRSTNAME.length(), returnItem.length());
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return null;
	}
	
	public String getLastName(final Account account) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + LASTNAME)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);			
			return returnItem.substring(account.getUsername().length() + LASTNAME.length(), returnItem.length());
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return null;
	}
	
	public String getZipcode(final Account account) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + ZIPCODE)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);			
			return returnItem.substring(account.getUsername().length() + ZIPCODE.length(), returnItem.length());
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return null;
	}
	
	public String getCity(final Account account) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + CITY)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);			
			return returnItem.substring(account.getUsername().length() + CITY.length(), returnItem.length());
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return null;
	}
	
	public String getState(final Account account) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + STATE)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);			
			return returnItem.substring(account.getUsername().length() + STATE.length(), returnItem.length());
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return null;
	}
	
	public String getStreet(final Account account) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + STREET)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);			
			return returnItem.substring(account.getUsername().length() + STREET.length(), returnItem.length());
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return null;
	}
	
	public String getHouseNumber(final Account account) {
		try {		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			String returnItem = null;
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + HOUSENUMBER)) {	
			        returnItem = fileContent.get(i);	       
			    } 
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);			
			return returnItem.substring(account.getUsername().length() + HOUSENUMBER.length(), returnItem.length());
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		return null;
	}
	
	
	
	public void setTransaction(final Account thisAccount, final int myAmount, final String reason, final Account account) {
		try {
			setBalance(thisAccount, myAmount);
			String amount;
			if(myAmount > 0) {
				 amount = "+ " + myAmount;
			} else {
				amount = "- " + myAmount;
			}
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(thisAccount.getUsername() + TRANSACTIONSTRING)) {	
			        fileContent.set(i, fileContent.get(i) + " Transaction occurence: " 
			        + dtf.format(now) + " Amount: " + amount + ", Reason: " + reason + 
			        ", Other account: " + account.getUsername() + 
			        ", Account Bank ID: " + account.getBankID() + ",");
			        break;
			    }
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}	
	}
	
	public void setTransaction(final Account account, final int amount, final String reason) {
		try {
			setBalance(account, amount);		
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + TRANSACTIONSTRING)) {	
			        fileContent.set(i, fileContent.get(i) + " Transaction occurence: " + 
			        dtf.format(now) + " Amount: " + amount + ", Reason: " + reason + ",");
			       
			        break;
			    }
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}
		
	}
	
	public String getTransaction(final Account account) {	
		try {
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + TRANSACTIONSTRING)) {	
			        return fileContent.get(i);
			    }
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void clearTransactions(final Account account){	
		try {
			List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
			for (int i = 0; i < fileContent.size(); i++) {			
			    if (fileContent.get(i).contains(account.getUsername() + TRANSACTIONSTRING)) {	
			    	fileContent.set(i, account.getUsername() + TRANSACTIONSTRING);
			        
			    }
			}
			Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
		} catch(IOException e) {
			System.out.println("File error!" + e);
		}	
	}
	
	public int getBankID(final Account account) {
		 try {
				List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
				String returnItem = null;
				for (int i = 0; i < fileContent.size(); i++) {			
				    if (fileContent.get(i).contains(account.getUsername() + BANKIDSTRING)) {	
				    	returnItem = fileContent.get(i);
						return Integer.valueOf(returnItem.substring(account.getUsername().length() + 10, returnItem.length()));
				    }
				}
				Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
			} catch(IOException e) {
				System.out.println("File error!" + e);
			}

		return 0;
	}
	
	// Helper Method 
	public void setBalance(final Account account, final int balance) {	
		 try {
			 List<String> fileContent = new ArrayList<>(Files.readAllLines(seniorPath, StandardCharsets.UTF_8));
				for (int i = 0; i < fileContent.size(); i++) {			
				    if (fileContent.get(i).contains(account.getUsername() + BALANCESTRING)) {	
				        fileContent.set(i, account.getUsername() + BALANCESTRING + balance);
				        break;
				    }
				}
				Files.write(seniorPath, fileContent, StandardCharsets.UTF_8);
			} catch(IOException e) {
				System.out.println("File error!" + e);
		}
	}
}
