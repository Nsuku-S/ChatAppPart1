/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.chatapppart1;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

private String messageID;
private int messageNumber;
private String recipient;
private String messageText;
private String messageHash;
private String sendStatus;

private static ArrayList<Message> allMessages = new ArrayList<>();
private static int totalMessagesSent = 0;

public Message() {
this.messageID = "";
this.messageNumber = 0;
this.recipient = "";
this.messageText = "";
this.messageHash = "";
this.sendStatus = "";
}

public Message(String recipient, String messageText, int messageNumber) {
this.recipient = recipient;
this.messageText = messageText;
this.messageNumber = messageNumber;
this.messageID = generateMessageID();
this.messageHash = createMessageHash();
this.sendStatus = "";
}

private String generateMessageID() {
Random rand = new Random();
StringBuilder id = new StringBuilder();
for (int i = 0; i < 10; i++) {
id.append(rand.nextInt(10));
}
return id.toString();
}

public boolean checkMessageID() {
return messageID != null && messageID.length() <= 10;
}

public String checkRecipientCell(String cell) {
if (cell != null && (cell.startsWith("+27") || cell.startsWith("0"))) {
return "Cell phone number successfully captured.";
} else {
return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
}
}

public String createMessageHash() {
String idPart = messageID.substring(0, 2);
String numPart = String.valueOf(messageNumber);
String[] words = messageText.split(" ");
String firstWord = words[0];
String lastWord = words[words.length - 1];
String hash = idPart + ":" + numPart + ":" + firstWord + lastWord;
return hash.toUpperCase();
}

public String checkMessageLength(String messageText) {
if (messageText.length() <= 250) {
return "Message ready to send.";
} else {
int over = messageText.length() - 250;
return "Message exceeds 250 characters by " + over + "; please reduce the size.";
}
}

    public String sentMessage(Scanner scanner) {
    System.out.println("\nWhat would you like to do with this message?");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store Message to send later");
    System.out.print("Enter your choice: ");

     int option = scanner.nextInt();
      scanner.nextLine();

        switch (option) {
        case 1:
        this.sendStatus = "Sent";
        return "Message successfully sent.";
        case 2:
        this.sendStatus = "Disregarded";
        return "Press 0 to delete the message.";
        case 3:
        storeMessage();
        this.sendStatus = "Stored";
        return "Message successfully stored.";
        default:
        return "Invalid option.";
        }
}

public String printMessages() {
if (allMessages.isEmpty()) {
return "No messages found.";
}
StringBuilder result = new StringBuilder();
for (Message msg : allMessages) {
result.append("Message ").append(msg.messageNumber).append(": ")
.append(msg.messageText).append("\n");
}
return result.toString();
}

public int returnTotalMessages() {
return totalMessagesSent;
}

// FIXED storeMessage method
public void storeMessage() {
FileWriter fileWriter = null;
try {
String json = "{";
json = json + ""messageID": "" + escapeJson(this.messageID) + "",";
json = json + ""messageNumber": " + this.messageNumber + ",";
json = json + ""recipient": "" + escapeJson(this.recipient) + "",";
json = json + ""messageText": "" + escapeJson(this.messageText) + "",";
json = json + ""messageHash": "" + escapeJson(this.messageHash) + "",";
json = json + ""sendStatus": "" + escapeJson(this.sendStatus) + "",";
json = json + ""timestamp": "" + new java.util.Date().toString() + """;
json = json + "}\n";

fileWriter = new FileWriter("messages.json", true);
fileWriter.write(json);
System.out.println("Message saved to messages.json");

} catch (IOException e) {
System.out.println("Error saving message: " + e.getMessage());
} finally {
try {
if (fileWriter != null) {
fileWriter.close();
}
} catch (IOException e) {
System.out.println("Error closing file: " + e.getMessage());
}
}
}

private String escapeJson(String text) {
if (text == null) return "";
String result = text;
result = result.replace("\", "\\");
result = result.replace(""", "\"");
result = result.replace("\n", "\n");
result = result.replace("\r", "\r");
return result;
}

public void displayMessageDetails() {
System.out.println("\n=== MESSAGE DETAILS ===");
System.out.println("Message ID: " + messageID);
System.out.println("Message Hash: " + messageHash);
System.out.println("Recipient: " + recipient);
System.out.println("Message: " + messageText);
}

public static void addMessage(Message msg) {
allMessages.add(msg);
totalMessagesSent++;
}

// Getters
public String getMessageID() { return messageID; }
public int getMessageNumber() { return messageNumber; }
public String getRecipient() { return recipient; }
public String getMessageText() { return messageText; }
public String getMessageHash() { return messageHash; }
public String getSendStatus() { return sendStatus; }
}