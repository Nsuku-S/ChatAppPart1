package com.mycompany.chatapppart1;
        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

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
public class LoginTest {
    
    Login login = new Login();
    
    
    //Running tests to test if the username is valid or not. It should contain 5 characters, a digit, and an underscore.
    @Test
    public void testValidUserName(){
        // Test to check if the username is valid or not
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    @Test 
    public void testInvalidUsername_NoUnderscore(){
        // Test to see if the username contains an underscore or it does not
        assertFalse(login.checkUserName("kyle1"));
    }
    
    @Test
    public void testInvalidUsername_TooLong(){
        // The password should be 8 characters long and should contain a special character, a number and a capital letter
        assertFalse(login.checkUserName("kyle_123"));
    }
    
    // Testing the password complexity. The password should contain 8 characters, a capital letter, a digit and a special character
    @Test
    public void testValidPasswordComplexity(){
        //Test to see if the password meets all required complexity rules
        assertTrue(login.checkPasswordComplexity("Ch@ttApp1"));
    }
    
    @Test
    public void testInvalidPasswordComplexity_NoCapitalLetter(){
        // Check to see if there is no missing capital letter in the password
        assertFalse(login.checkPasswordComplexity("chattapp@1"));
    }
    
    @Test
    public void testInvalidPasswordComplexity_TooShort(){
        // Check to see if the password matches the required length (8 characters)
        assertFalse(login.checkPasswordComplexity("Ch@t1"));
    }
    
    
    // Testing the validity of the cellphone number.Checking if the cellphone number starts with the South African international code (+27..)
    // The cellphone number should be correctly formatted and be the required length.
    @Test
    public void testValidCellphoneNumber(){
        // Test to see if the cellphone number starts with the South African international code
        assertTrue(login.checkCellPhoneNumber("+27123456789"));
    }
    
    @Test
    public void testInvalidCellphoneNumber_IncorrectFormat(){
        // Test to see if the cellphone number is formatted correctly
        // Starts with the South African international code
        assertFalse(login.checkCellPhoneNumber("0721234567"));
    }
    
    // Testing the login user. The user should be able to login once they have entered the correct login credentials.
    // The user's username, password and cellphone number should be correct for the user to be able to login
    @Test
    public void testLoginUser(){
        // First check if the user is registered to store the data
        login.registerUser("kyl_1", "Ch@ttApp1", "+27123456789");
    }
    
    @Test
    public void testValidLoginUser(){
        // Test login with the partially correct login credentials of the user
        assertFalse(login.loginUser("kyl_1", "Ch@ttApp1"));
    }
    
    @Test
    public void testInvalidLoginUser(){
        // Test the login with incorrect details or credentials
        assertFalse(login.loginUser("wrong", "password"));
    }
    
   
}
