package com.bank.engine.utilitys;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {

    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BLUE = "\u001B[34m";

    
    private String name;

    public Logging(String name) {
    	this.name = name;
    }
    
    public void success(String message) {   
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + GREEN + "SUCCESS: " + message + RESET);                          
    }

    
    public void severe(String message) {	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + RED + "SEVERE: " + message + RESET);  
    }

   
    public void info(String message) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + BLUE + "INFO: " + message + RESET);  
    }

    
    public void warn(String message) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();   
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + YELLOW + "WARNING: " + message + RESET);  
    }
    
    public void success(int message) {   	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + GREEN + "SUCCESS: "  + message + RESET);                          
    }

    
    public void severe(int message) {	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + RED + "SEVERE: " + message + RESET);  
    }

   
    public void info(int message) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + BLUE + "INFO: " + message + RESET);  
    }

    
    public void warn(int message) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();   
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + YELLOW  + "WARNING: " + message + RESET);  
    }   
    
    public void warn(NumberFormatException message) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();   
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + YELLOW + message + RESET);  
    }   
    
    public void warn(NullPointerException message) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();   
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + YELLOW + message + RESET);  
    }

	public void info(Object message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + BLUE + "INFO: " + message + RESET);  
	}

	public void warn(SQLException message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();   
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + YELLOW + message + RESET);  
	}   
	
	public void warn(ClassNotFoundException message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();   
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + YELLOW + message + RESET);  
	}   
	
	public void warn(Exception message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();   
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + YELLOW + message + RESET);  
	}

	public void severe(Exception message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();   
    	System.out.println(this.name + " [" + dtf.format(now) + "] " + YELLOW + message + RESET);  
		
	}   
 }

