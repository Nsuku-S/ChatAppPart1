
package com.mycompany.chatapppart1;

public class Login {
    

    String username;
    String password;
    String phoneNumber;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        return phone.matches("\\+27\\d{9}");
    }

    public String registerUser(String username, String password, String phoneNumber) {

        if (!checkUserName(username)) {
            return "Username must contain '_' and be no more than 5 characters.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password must be at least 8 characters and include a capital letter, number, and special character.";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Phone number must start with +27 and be followed by 9 digits.";
        }

        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;

        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    public String returnLoginStatus(boolean success) {
        return success 
            ? "Welcome " + username + ", great to see you again!"
            : "Username or password incorrect.";
    }

  
}