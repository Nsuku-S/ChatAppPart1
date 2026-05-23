/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
        
public class Message {
    
    //Section 1: fields. Declaring variables that will be used to send and recieve messages
    
   public String messageID; // 10 didgit auto-genertaed number
   public int messageNumber; // from a loop counter
   public String recipient; // validated 10-digit cellphone number that contains the country's international code
   public String textMessage; // maximum of 250 characters
   public String messageHash; //auto-generated 
   
   //Static fields to track all messages
   
   public static ArrayList<Message> allMessages = new ArrayList<>();
   public static int counter = 0;
    
    // Initialising a message object
   public Message(String recipient, String messageText, int messageNumber){
       this.recipient = recipient;
       this.textMessage = textMessage;
       this.messageNumber = messageNumber;
       this.messageID =  generateMessageID(); // auto-generate 10-digits
       this.messageHash = createMessageHash(); //auto-generate hash
   }
    
   // Section 3: required methods
   //generate a 10-didgit message ID
   public String generateMessageID(){
       Random rand = new Random();
       StringBuilder id = new StringBuilder();
       for (int i = 0; i < 10; i++){
           id.append(rand.nextInt(10));
       }
       return id.toString();
   }
   //1. checkMessageID() - validates if a message ID is 10 digits
   public static boolean checkMessageID(String id){
       return id != null && id.length()= 10 && id.matches(id);
   }
   //2. Checks if recepient phone number is valid (10 digits starting with a 0 or south african international code)
   public String checkRecipientCell(String cell){
       return cell != null && cell.length() == 10 && cell.startsWith("0") && cell.matches(cell);
   }
   //3. Creates a SHA-256 hash of message content 
   private String createsMessageHash(){
       try{
           String content = messageID + recipient + textMessage;
           MessageDigest digest = MessageDigest.getInstance("SHA-256");
           byte[] hash = digest.digest(content.getBytes());
   // Converting to hexadecimal string 
   StringBuilder hexString =  new StringBuilder();
   for (byte b : hash){
       String hex = Integer.toHexString(0xff & b);
       if (hex.length() == 1) hexString.append('0');
       hexString.append(hex);
   }
   return hexString.toString();
       } catch (NoSuchAlgorithmException e){
           e.printStackTrace();
           return "ERROR";
       }
   }
   //Send message 
   public static boolean sendMessage(Scanner scanner, String senderCell){
       System.out.println("===SEND NEW MESSAGE===");
       // Get a recipient cell number
       System.out.print("Enter recipient's cell number (10 digits starting with a 0 or South African international code)");
       String recipient = scanner.nextLine();
       while (!checkRecipientCell(recipient)){
           System.out.print("Invalid cell number! Try again:");
           recipient = scanner.nextLine();
       }
       // Get message text
       System.out.print("Enter your message (max 250 characters)");
       String textMessage = scanner.nextLine();
       while (textMessage.length()> 250)
       {
           System.out.print("Message too long! Max 250 characaters. Try again");
           textMessage = scanner.nextLine();
       }
       // Create and store message
       messageCounter++;
       Message newMessage = new Message(recipient, textMessage, messageCounter);
       allMessages.add(newMessage);
       System.out.println("Message sent successfully!");
       System.out.println("Message ID: " + newMessage.messageID);
       System.out.println("Message Hash: " + newMessage.messageHash.substring(0, 16) + "...\n");
       return true;
   }
   // Print all messages
   public static void printMessages(){
       System.out.println("=== ALL MESSAGES ===");
       // Get recipient's cellphone number
       System.out.print("Enter recipient's cellphone number (10 digits starting with South African international code +27)");
       String recipient = scanner.nextLine();
       while (!checkRecipientCell(recipient)){
           System.out.print("Invalid cell number! Try again:");
           recipient = scanner.nextLine();
       }
       
       // Get message text
       System.out.print("Enter your message (max 250 characters):");
       String textMessage = scanner.nextLine();
       while (textMessage.length() > 250) {
           System.out.print("Message too long! Max 250 characters. Try again:");
           textMessage = scanner.nextLine();
       }
       
       // Create and store a message
       messageCounter++;
       Message newMessage =  new Message(recipient, tetxMessage, messageCounter);
       allMessages.add(newMessage);
       System.out.println("Message sent successfully!");
       System.out.println("Message ID:" + newMessage.messageID);
       System.out.println("Message Hash:" + newMessage.messageHash.substring((0, 16) + "...\n"));
       return true;
   }
   
   // Print all messages
   public static void printMessages(){
       System.out.println("=== ALL MESSAGES ===");
       if (allMessages.isEmpty()){
           System.out.println("No nessages found.");
           return;
       }  
       
       System.out.print("Message Number, Message ID, Recipient, Text Message, Message Hash");
       System.out.println("=".repeat(100));
       for (Message msg = allMessages); {
         System.out.print(msg.messageNumber, msg.messageID, msg.recepient, msg.textMessage.length() >27 msg.textMessage.substring(0,24) + "..." : msg.textMessage, msg.messageHash.substring(0, 16) + "...");
       }
       System.out.println();       
   }
   
   // Return total number of messages
   public static int returnTotalMessages(){
       return allMessages.size();
   }
   // Getters
   public String getMessageID() {return messageID; }
   public int getMessageNumber(){return messageNumber; }
   public String getRecipient(){return recipient; }
   public String getTextMessage(){return textMessage; }
   public String getMessageHash(){return messageHash;}
   
}
