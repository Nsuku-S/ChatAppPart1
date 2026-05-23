/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapppart1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nsuku
 */
public class MessageTest {
    
   Scanner scanner = new Scanner(System.in);
   
    // Running tests to check messageID
    @Test
    public static void testValidMessageID() {
        // Check if messageID contains the correct number of digits
        assertTrue(message.checkMessageID("1234567890"));
    }
    
    @Test
    public static void testInvalidMessageID(){
        // Checks for incorrect messageID
        assertFalse(message.checkMessageID("1234Abc"));
    }
    
    @Test
    public static void testValidRecipientCell() {
        // Checks if the recipient's cell number is valid or contains South African international code
        assertTrue(message.checkRecipientCell("+27123456789"));
    }
    
    @Test
    public static void testInvalidRecipientCell(){
        // Checks for incorrect recipient's cell phone number
        assertFalse(message.checkRecipientCell("0712345689"));
    }
    
    @Test
    public void testValidSendMessages() {
        // Checks if the message sent contains not more than 250 characters
        assertTrue(message.checkMessageSent(""));
    }
    
    @Test
    public void testPrintMessages() {
        // prints out the messages that were sent
    }

  
}
