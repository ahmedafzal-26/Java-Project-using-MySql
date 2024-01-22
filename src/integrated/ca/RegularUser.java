/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrated.ca;

import ioutils.IOUtils;
import java.math.BigDecimal;
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
public class RegularUser implements AuthenticationServiceAndUpdate{
    
    //THESE VARIABLES TO READ DATA FROM DATABASE
    protected int user_id;
    private String first_name;
    private String last_name;
    private String username;
    private String pass;
    private String email;
    private String contact_no;
    private String address;
    private String city;

    //THESE VARIABLES TO TAKE INPUT FROM THE USER
    private int u_id;
    private String f_name;
    private String l_name;
    private String u_name;
    private String password;
    private String mail;
    private int contact_number;
    private String newAddress;
    private String county;
    
    //TAX CALCULATION VARIABLES
    private double gross_income;
    private double tax_credit;
    private double income_tax;
    private double temp;
    
    @Override
    public int idRetrieval(){
        try(    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
            ){
            String retrieveQuery = "SELECT user_id FROM users WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, u_name);

            // Executing the query to retrieve user ID
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieving the user ID
            if (resultSet.next()) {
                user_id = resultSet.getInt("user_id");
            }
            else{
                System.out.println("User not found!");
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        return user_id;
    }
    
    public boolean addUser() throws SQLException {
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
            Statement stmt = conn.createStatement();  
        ){
            IOUtils myInput = new IOUtils();
            
            f_name = myInput.getUserText1("Enter a First name");
            l_name = myInput.getUserText1("Enter a Last name");
            u_name = myInput.getUserText("Enter a username that you want");
            password = myInput.getUserText("Enter your password");
            mail = myInput.getUserEmail("Enter a your email:");
            contact_number = myInput.getUserInt("Enter a your contact number:");
            newAddress = myInput.getUserText("Enter a your address:");
            county = myInput.getUserText1("Enter your County or City :");
            
            String sql = String.format("INSERT INTO Users VALUES ("
                    + "%d, '%s', '%s', '%s','%s', '%s', '%d', '%s','%s');",
                     null, f_name, l_name, u_name, password, mail, contact_number, newAddress, county);
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public String login() {
        IOUtils myInput = new IOUtils();
        
        u_name = myInput.getUserText("Enter a Username ");
        password = myInput.getUserText("Enter your Password ");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "SELECT * FROM Users WHERE username = ? AND pass = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, u_name);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("successful");
                idRetrieval();
                return u_name;
            }
            else{
                System.out.println("Unsuccessful");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean modifyCompleteProfile() {
        
        IOUtils myInput = new IOUtils();
        
        f_name = myInput.getUserText1("Enter a First name that you want:");
        l_name = myInput.getUserText1("Enter a Last name that you want:");
        u_name = myInput.getUserText("Enter a Username that you want:");
        password = myInput.getUserText("Enter your Password that you want:");
        mail = myInput.getUserEmail("Enter a new Email:");
        contact_number = myInput.getUserInt("Enter a new Contact number:");
        newAddress = myInput.getUserText("Enter a new Address:");
        county = myInput.getUserText1("Enter your County or City:");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Users SET first_name = ?, last_name = ?, username = ?, pass = ?, email = ?,"
                                + " contact_no = ?, address = ?, city = ? WHERE user_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, f_name);
            preparedStatement.setString(2, l_name);
            preparedStatement.setString(3, u_name);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, mail);
            preparedStatement.setInt(6, contact_number);
            preparedStatement.setString(7, newAddress);
            preparedStatement.setString(8, county);
            preparedStatement.setInt(9, user_id);
            
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
            String updateQuery = "UPDATE Users SET first_name = ?, last_name = ? WHERE user_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, f_name);
            preparedStatement.setString(2, l_name);
            preparedStatement.setInt(3, user_id);
            
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
            String updateQuery = "UPDATE Users SET username = ? WHERE user_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, u_name);
            preparedStatement.setInt(2, user_id);
            
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
        password = myInput.getUserText("Enter your new Password");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Users SET pass = ? WHERE user_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, user_id);
            
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
            String updateQuery = "UPDATE Users SET contact_no = ? WHERE user_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setInt(1, contact_number);
            preparedStatement.setInt(2, user_id);
            
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
            String updateQuery = "UPDATE Users SET email = ? WHERE user_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, mail);
            preparedStatement.setInt(2, user_id);
            
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
        county = myInput.getUserText1("Enter your new County or City");
        
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
        ){
            String updateQuery = "UPDATE Users SET address = ?, city = ? WHERE user_id = ?";
            
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, newAddress);
            preparedStatement.setString(2, county);
            preparedStatement.setInt(3, user_id);
            
            preparedStatement.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public double taxCal(){
        if(gross_income < 40000){
            temp = gross_income - tax_credit;
            income_tax = temp * 0.2;
        }
        else if(gross_income > 40000){
            temp = gross_income - tax_credit;
            income_tax = temp * 0.4;
        }
        return income_tax;
    }
    
    public boolean addTaxData(){
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
            Statement stmt = conn.createStatement();  
        ){
            IOUtils myInput = new IOUtils();
            
            gross_income = myInput.getUserDecimal("Enter a your Gross Income");
            tax_credit = myInput.getUserDecimal("Enter a your Tax Credit");
            idRetrieval();
            taxCal();
            
            String sql = String.format("INSERT INTO user_tax_info VALUES ("
                    + "%d, '%s', '%s', '%s','%d');",
                     null, gross_income, tax_credit, income_tax, user_id);
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean viewTaxOwed(){
        
        try(    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datavault", "ooc2023", "ooc2023");
            ){
            String retrieveQuery = "SELECT U.user_id, U.first_name, U.last_name, UTI.income_tax FROM Users U INNER JOIN "
                                  + "user_tax_info UTI ON U.user_id = UTI.user_id WHERE U.user_id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(retrieveQuery);
            idRetrieval();
            preparedStatement.setInt(1, user_id);
            
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("User ID     First Name     Last Name     Income Tax");
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                BigDecimal incomeTax = rs.getBigDecimal("income_tax");

                System.out.printf("%d           %s             %s            %s\n",
                                   userId, firstName, lastName, incomeTax);
            }
            return true;
        }
        catch (Exception e) {
                e.printStackTrace();
                return false;
            }
    }
    
}
