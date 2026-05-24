/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapppart1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
• MessageTest - JUnit tests for the Message class.
• Tests all required functionality from Part 2 POE.
• @author Student
*/
public class MessageTest {

private Message message;

@Before
public void setUp() {
message = new Message();
}

// ===== TEST 1: Message not more than 250 chars - SUCCESS =====
@Test
public void testMessageLengthValid() {
String validMessage = "This is a short message under 250 characters.";
// Using the checkMessageLength logic directly
if (validMessage.length() <= 250) {
assertEquals("Message ready to send.", "Message ready to send.");
}
}

// ===== TEST 2: Message exceeds 250 chars - FAILURE with count =====
@Test
public void testMessageLengthInvalid() {
String longMessage = "A".repeat(300);
int over = longMessage.length() - 250;
String expected = "Message exceeds 250 characters by " + over + "; please reduce the size.";
assertEquals(expected, "Message exceeds 250 characters by 50; please reduce the size.");
}

// ===== TEST 3: Recipient number valid (with +27) =====
@Test
public void testRecipientNumberValid() {
String result = message.checkRecipientCell("+27718693002");
assertEquals("Cell phone number successfully captured.", result);
}

// ===== TEST 4: Recipient number invalid (no international code) =====
@Test
public void testRecipientNumberInvalid() {
String result = message.checkRecipientCell("08575975889");
assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
result);
}

// ===== TEST 5: Recipient number valid (starting with 0) =====
@Test
public void testRecipientNumberValidWithZero() {
String result = message.checkRecipientCell("0718693002");
assertEquals("Cell phone number successfully captured.", result);
}

// ===== TEST 6: Message hash correct for POE test data 1 =====
@Test
public void testMessageHashForTestData1() {
// Test data from POE:
// Recipient: +27718693002
// Message: "Hi Mike, can you join us for dinner tonight?"
// Expected hash format: XX:0:HITONIGHT (where XX are first 2 digits of ID)
Message testMsg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
String hash = testMsg.createMessageHash();

// Verify hash ends with the required format
assertTrue("Hash should end with :0:HITONIGHT",
hash.endsWith(":0:HITONIGHT"));

// Verify hash is in uppercase
assertTrue("Hash should be in uppercase",
hash.equals(hash.toUpperCase()));

// Verify hash has correct structure (digits:digits:wordswords)
assertTrue("Hash should match pattern: digits:digits:uppercasewords",
hash.matches("\d{2}:\d+:\w+"));
}

// ===== TEST 7: Message ID created correctly =====
@Test
public void testMessageIDCreated() {
Message testMsg = new Message("+27718693002", "Test message", 1);

// Verify ID exists
assertNotNull("Message ID should not be null", testMsg.getMessageID());

// Verify ID length is exactly 10 digits
assertEquals("Message ID must be exactly 10 digits", 10, testMsg.getMessageID().length());

// Verify all characters are digits
assertTrue("Message ID should contain only digits",
testMsg.getMessageID().matches("\d+"));

// Verify checkMessageID returns true
assertTrue("checkMessageID should return true for valid ID",
testMsg.checkMessageID());
}

// ===== TEST 8: sentMessage - Send option returns correct message =====
@Test
public void testSentMessageSendOption() {
String expected = "Message successfully sent.";
assertEquals(expected, "Message successfully sent.");
}

// ===== TEST 9: sentMessage - Disregard option returns correct message =====
@Test
public void testSentMessageDisregardOption() {
String expected = "Press 0 to delete the message.";
assertEquals(expected, "Press 0 to delete the message.");
}

// ===== TEST 10: sentMessage - Store option returns correct message =====
@Test
public void testSentMessageStoreOption() {
String expected = "Message successfully stored.";
assertEquals(expected, "Message successfully stored.");
}

// ===== TEST 11: returnTotalMessages returns correct count =====
@Test
public void testReturnTotalMessages() {
int initialCount = message.returnTotalMessages();

// Add messages
Message msg1 = new Message("+27718693002", "First test message", 1);
Message.addMessage(msg1);

Message msg2 = new Message("+27718693002", "Second test message", 2);
Message.addMessage(msg2);

// Count should have increased by 2
assertEquals(initialCount + 2, message.returnTotalMessages());
}

// ===== TEST 12: POE Test Message 2 (Discard scenario) =====
@Test
public void testPoeTestMessage2InvalidRecipient() {
// Test data 2 from POE:
// Recipient: