/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

// Importing a new scanner that interacts with the user

 import java.util.Scanner; 

/**
• MainApp - The main entry point for the Chat Application.
• Handles user interaction, registration, login, and the messaging menu.
• @author Student
*/
public class MainApp {

// Scanner declared as static at class level - accessible everywhere
private static Scanner scanner = new Scanner(System.in);

/**
* Main method - entry point of the application.
* @param args Command line arguments
*/
public static void main(String[] args) {
System.out.println("==========================================");
System.out.println(" WELCOME TO CHAT APPLICATION");
System.out.println("==========================================");

Login login = new Login();

// --- PART 1: REGISTRATION SECTION ---
System.out.println("\n=== USER REGISTRATION ===");
System.out.print("Enter a username: ");
String username = scanner.nextLine();
System.out.print("Enter a password: ");
String password = scanner.nextLine();
System.out.print("Enter your South African phone number (+27...): ");
String phone = scanner.nextLine();

// Call the registerUser method and store the message it returns
String response = login.registerUser(username, password, phone);
System.out.println(response);

// Only proceed to login if registration was successful
if (response.equals("User registered successfully.")) {
// --- PART 1: LOGIN SECTION ---
System.out.println("\n=== USER LOGIN ===");
System.out.print("Enter your username: ");
String loginUsername = scanner.nextLine();
System.out.print("Enter your password: ");
String loginPassword = scanner.nextLine();

// Call loginUser to check if details match the stored ones
boolean loggedIn = login.loginUser(loginUsername, loginPassword);

// Print out the correct login message
String loginMessage = login.returnLoginStatus(loggedIn);
System.out.println(loginMessage);

// --- PART 2: MESSAGING (only if logged in) ---
if (loggedIn) {
// EXACT welcome message as required by POE
System.out.println("\nWelcome to ChatGPT.");

// Launch the main menu loop
runMainMenu();
}
} else {
System.out.println("\nRegistration failed. Please restart the application.");
}

System.out.println("\nThank you for using ChatApp!");
scanner.close();
}

/**
* Runs the main menu loop for Part 2.
* Displays options and handles user choices.
*/
private static void runMainMenu() {
boolean running = true;

while (running) {
displayMenu();
System.out.print("Enter your choice: ");
int choice = scanner.nextInt();
scanner.nextLine(); // Consume newline

switch (choice) {
case 1:
sendMessagesFeature();
break;
case 2:
System.out.println("\nComing Soon.");
break;
case 3:
System.out.println("\nExiting ChatApp. Goodbye!");
running = false;
break;
default:
System.out.println("\nInvalid choice! Please enter 1, 2, or 3.");
}
}
}

/**
* Displays the main menu options.
*/
private static void displayMenu() {
System.out.println("\n==============================");
System.out.println("CHATAPP MENU");
System.out.println("==============================");
System.out.println("1) Send Messages");
System.out.println("2) Show recently sent messages");
System.out.println("3) Quit");
System.out.println("==============================");
}

/**
* Handles the send messages feature.
* Asks user how many messages, then loops to collect each message.
*/
private static void sendMessagesFeature() {
System.out.println("\n=== SEND MESSAGES ===");

// Ask how many messages
System.out.print("How many messages would you like to send? ");
int numMessages = scanner.nextInt();
scanner.nextLine();

// For loop runs exactly numMessages times
for (int i = 0; i < numMessages; i++) {
int currentMessageNumber = i + 1; // human-readable message number
System.out.println("\n--- Message " + currentMessageNumber + " ---");

// Create a temporary message for validation
Message tempMessage = new Message();

// Get recipient cell number
System.out.print("Enter recipient's cell number (with international code like +27 or 0): ");
String recipientInput = scanner.nextLine();

String cellValidation = tempMessage.checkRecipientCell(recipientInput);
System.out.println(cellValidation);

// If validation fails, ask again until valid
while (!cellValidation.equals("Cell phone number successfully captured.")) {
System.out.print("Please re-enter valid cell number: ");
recipientInput = scanner.nextLine();
cellValidation = tempMessage.checkRecipientCell(recipientInput);
System.out.println(cellValidation);
}

// Get message text
System.out.print("Enter your message (max 250 characters): ");
String messageTextInput = scanner.nextLine();

// Check message length
String lengthCheck = tempMessage.checkMessageLength(messageTextInput);
System.out.println(lengthCheck);

while (messageTextInput.length() > 250) {
System.out.print("Please re-enter shorter message: ");
messageTextInput = scanner.nextLine();
lengthCheck = tempMessage.checkMessageLength(messageTextInput);
System.out.println(lengthCheck);
}

// Create complete message object with all fields
Message completeMessage = new Message(recipientInput, messageTextInput, currentMessageNumber);

// Ask what to do with the message (Send, Disregard, or Store)
String actionResult = completeMessage.sentMessage(scanner);
System.out.println(actionResult);

// Display message details in the correct order
completeMessage.displayMessageDetails();

// Add to collection and increment counter if message was sent or stored
if (actionResult.equals("Message successfully sent.") ||
actionResult.equals("Message successfully stored.")) {
Message.addMessage(completeMessage);
}
}

// After all messages, display total count
Message msgSummary = new Message();
System.out.println("\n=== SESSION SUMMARY ===");
System.out.println("Total messages sent this session: " + msgSummary.returnTotalMessages());
}
}