 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

// Importing a new scanner that interacts with the user

 import java.util.Scanner; 

public class MainApp {
    public static void main(String[] args) {
        
        // The scanner permits the user to insert their information
        
        Scanner input = new Scanner(System.in);
        
        // Instantiate the login class to access its methods
        
        Login login = new Login();
        
        // Registration process. Where registration takes place or the user enters their details in order to register
        
        // --- REGISTRATION SECTION ---
        System.out.println("=== USER REGISTRATION ===");
        
        System.out.print("Enter a username: ");
        String username = input.nextLine();
        
        System.out.print("Enter a password: ");
        String password = input.nextLine();
        
        System.out.print("Enter your South African cell phone number (+27...) ");
        String phone = input.nextLine();
        
        // Call the registerUser method and store the message it returns
        
        String response = login.registerUser(username, password, phone);
        
        // Display the response message
        
        System.out.println(response);
        
        // --- LOGIN SECTION ---
        System.out.println("\n=== USER LOGIN ===");
        
        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();
        
        System.out.print("Enter your password: ");
        String loginPassword = input.nextLine();
        
        // Call loginUser to check if details match the stored ones
        
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        
        // Display the correct login message
        String loginMessage = login.returnLoginStatus(loggedIn);
        System.out.println(loginMessage);
        
        // Messaging.(only if login was successful.)
      if (loggedIn){
            System.out.println("Welcome to ChatApp");
            { else {  
                System.out.println("Login failed. Goodbye!");
                }
            }
        }
         }
}

