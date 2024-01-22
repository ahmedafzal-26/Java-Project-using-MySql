/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrated.ca;

import ioutils.IOUtils;
import java.sql.SQLException;

/**
 *
 * @author ahmed
 */
public class CodeRunner {
    
    public void codeRunner() throws SQLException {
        MenuGenerator mg = new MenuGenerator();
        IOUtils myInput = new IOUtils();

        int value; // Used in switch case to take input from user
        String choice; // Used in do-while loop to exit or continue the program
        do {
            mg.mainMenu();
            System.out.println("");
            value = myInput.getUserInt("Enter your choice to continue...");

            switch(value) {
                case 1:
                    handleAdmin(mg, myInput);
                    break;
                case 2:
                    handleRegularUser(mg, myInput);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println("Do You Want to continue [Y/N]");
            choice = myInput.getUserText1("Enter your Choice..").toUpperCase();
        } while(!choice.equalsIgnoreCase("N"));
    }

    private void handleAdmin(MenuGenerator mg, IOUtils myInput) throws SQLException {
        Admin ad = new Admin();
        String valid = ad.login();
        if(valid != null) {
            System.out.println("You are successfully Logged In.");
            mg.adminMenu();
            System.out.println("");
            int adminChoice = myInput.getUserInt("Enter your choice to continue...", 1, 4);
            switch(adminChoice) {
                case 1:
                    handleAdminProfileUpdate(ad, mg, myInput);
                    break;
                case 2:
                    ad.viewListOfUsers();
                    break;
                case 3:
                    ad.DeleteUser();
                    break;
                case 4:
                    ad.listOfOpsPerformedUsers();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } else {
            System.out.println("Invalid Username or Password - Try Again!");
        }
    }

    private void handleAdminProfileUpdate(Admin ad, MenuGenerator mg, IOUtils myInput) throws SQLException {
        mg.adminSubMenu();
        System.out.println("");
        int updateChoice = myInput.getUserInt("Enter your choice to continue...", 1, 7);
        boolean result;
        switch(updateChoice) {
            case 1:
                result = ad.updateUsername();
                break;
            case 2:
                result = ad.resetPassword();
                break;
            case 3:
                result = ad.updateName();
                break;
            case 4:
                result = ad.updateEmail();
                break;
            case 5:
                result = ad.updateAddress();
                break;
            case 6:
                result = ad.updateContactNumber();
                break;
            case 7:
                result = ad.modifyCompleteProfile();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
        if(result) {
            System.out.println("Updated Successfully");
        } else {
            System.out.println("Unable to Update");
        }
    }

    private void handleRegularUser(MenuGenerator mg, IOUtils myInput) throws SQLException {
        RegularUser ru = new RegularUser() {};
        mg.userMenu();
        System.out.println("");
        int userChoice = myInput.getUserInt("Enter your choice to continue...", 1, 2);
        switch(userChoice) {
            case 1:
                handleRegularUserProfile(ru, mg, myInput);
                break;
            case 2:
                handleUserRegistration(ru);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void handleRegularUserProfile(RegularUser ru, MenuGenerator mg, IOUtils myInput) throws SQLException {
        String valid = ru.login();
        if(valid != null) {
            System.out.println("You are successfully Logged In.");
            mg.userSubMenu();
            System.out.println("");
            int profileChoice = myInput.getUserInt("Enter your choice to continue...", 1, 3);
            switch(profileChoice) {
                case 1:
                    updateUserProfile(ru, mg, myInput);
                    break;
                case 3:
                    ru.viewTaxOwed();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } else {
            System.out.println("Invalid Username or Password - Try Again!");
        }
    }

    private void handleUserRegistration(RegularUser ru) throws SQLException {
        System.out.println("Registering new user...");

        // Calling the addUser method from RegularUser class
        boolean registrationResult = ru.addUser();
        boolean registrationResult1 = ru.addTaxData();

        if (registrationResult && registrationResult1) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private void updateUserProfile(RegularUser ru, MenuGenerator mg, IOUtils myInput) throws SQLException {
        mg.userSubMenu2();
        System.out.println("");
        int updateChoice = myInput.getUserInt("Enter your choice to continue...", 1, 7);
        boolean result;
        switch(updateChoice) {
            case 1:
                result = ru.updateUsername();
                break;
            case 2:
                result = ru.resetPassword();
                break;
            case 3:
                result = ru.updateName();
                break;
            case 4:
                result = ru.updateEmail();
                break;
            case 5:
                result = ru.updateAddress();
                break;
            case 6:
                result = ru.updateContactNumber();
                break;
            case 7:
                result = ru.modifyCompleteProfile();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
        if(result) {
            System.out.println("Updated Successfully");
        } else {
            System.out.println("Unable to Update");
        }
    }
    
}
