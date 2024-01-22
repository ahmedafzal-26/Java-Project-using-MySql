/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrated.ca;

import ioutils.IOUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ahmed
 */
public class Admin implements AuthenticationServiceAndUpdate{
    
    //THESE VARIABLES TO READ DATA FROM DATABASE
    private int admin_id;
    private String first_name;
    private String last_name;
    private String username;
    private String pass;
    private String email;
    private int contact_no;
    private String address;
    private String department;
    private int user_id;
    private String county;
    private String contact;
    

    //THESE VARIABLES TO TAKE INPUT FROM THE USER
    private int a_id;
    private String f_name;
    private String l_name;
    private String u_name;
    private String password;
    private String mail;
    private int contact_number;
    private String newAddress;
    private String branch; //department
    
    
    @Override
    public int idRetrieval(){
        try(    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
            ){
            String retrieveQuery = "SELECT admin_id FROM Admins WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, u_name);

            // Executing the query to retrieve user ID
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieving the user ID
            if (resultSet.next()) {
                admin_id = resultSet.getInt("admin_id");
            }
            else{
                System.out.println("User not found!");
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        return admin_id;
    }
    
    /**
     * 
     */
    @Override
    public  String login(){
        IOUtils myInput = new IOUtils();
        
        u_name = myInput.getUserText("Enter a Username ");
        password = myInput.getUserText("Enter your Password ");
        
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "SELECT * FROM Admins WHERE username = ? AND pass = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, u_name);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("successful");
                //idRetrieval();
                return u_name;
            }
            else{
                System.out.println("Unsuccessful");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        idRetrieval();
        return null;
    }

    @Override
    public boolean modifyCompleteProfile() {
        
        IOUtils myInput = new IOUtils();
        u_name = myInput.getUserText("Enter a username that you want:");
        f_name = myInput.getUserText1("Enter a First name that you want:");
        l_name = myInput.getUserText1("Enter a Last name that you want:");
        password = myInput.getUserText("Enter your password that you want:");
        mail = myInput.getUserEmail("Enter a new email:");
        contact_number = myInput.getUserInt("Enter a new contact number:");
        newAddress = myInput.getUserText("Enter a new address:");
        branch = myInput.getUserText1("Enter your new department:");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Admins SET username = ?, first_name = ?, last_name = ?, pass = ?, email = ?,"
                                + " contact_no = ?, address = ?, department = ? WHERE admin_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, u_name);
            preparedStatement.setString(2, f_name);
            preparedStatement.setString(3, l_name);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, mail);
            preparedStatement.setInt(6, contact_number);
            preparedStatement.setString(7, newAddress);
            preparedStatement.setString(8, branch);
            preparedStatement.setInt(9, admin_id);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateName() {
        
        IOUtils myInput = new IOUtils();
        f_name = myInput.getUserText1("Enter your First name");
        l_name = myInput.getUserText1("Enter your Last name");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Admins SET first_name = ?, last_name = ? WHERE admin_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, f_name);
            preparedStatement.setString(2, l_name);
            preparedStatement.setInt(3, admin_id);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUsername() {
        
        IOUtils myInput = new IOUtils();
        u_name = myInput.getUserText("Enter your new Username");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Admins SET username = ? WHERE admin_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, u_name);
            preparedStatement.setInt(2, admin_id);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean resetPassword() {
        
        IOUtils myInput = new IOUtils();
        password = myInput.getUserText("Enter your new Username");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Admins SET pass = ? WHERE admin_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, admin_id);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateContactNumber() {
        
        IOUtils myInput = new IOUtils();
        contact_number = myInput.getUserInt("Enter your new Contact Nummber");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Admins SET contact_no = ? WHERE admin_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setInt(1, contact_number);
            preparedStatement.setInt(2, admin_id);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmail() {
        
        IOUtils myInput = new IOUtils();
        mail = myInput.getUserEmail("Enter your new Email");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Admins SET email = ? WHERE admin_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, mail);
            preparedStatement.setInt(2, admin_id);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAddress() {
        
        IOUtils myInput = new IOUtils();
        newAddress = myInput.getUserText("Enter your new Address");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Admins SET address = ? WHERE admin_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, newAddress);
            preparedStatement.setInt(2, admin_id);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteUser() throws SQLException {
        
        IOUtils myInput = new IOUtils();
        f_name = myInput.getUserText("Enter the user first name to delete the user");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "DELETE FROM Users WHERE first_name = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, f_name);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean viewListOfUsers(){
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
            Statement stmt = conn.createStatement();
            ){
            String sql = "SELECT * FROM Users;";
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("UserID   Username  First-Name  Last-Name  Password        Email         Contact-Number     Address       County");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while(resultSet.next()) {
                
                // Retrieving data by column name
                user_id = resultSet.getInt("user_id");
                username = resultSet.getString("username");
                first_name = resultSet.getString("first_name");
                last_name = resultSet.getString("last_name");
                password = resultSet.getString("pass");
                email = resultSet.getString("email");
                contact = resultSet.getString("contact_no");
                address = resultSet.getString("address");
                county = resultSet.getString("city");
                
                // Displaying the retrieved data
                
                
                System.out.println(" "+user_id + "         " + username + "      " +first_name + "      " +last_name + "    " +password + "    " 
                                  +email + "     " +contact + "    " +address + "   " +county );
                
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void listOfOpsPerformedUsers(){
        System.out.println("    User Register his or her self");
        System.out.println("    Login into a system");
        System.out.println("    View tax Calculation");
    }
    
}
