/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package integrated.ca;

/**
 *
 * @author ahmed
 */
public interface AuthenticationServiceAndUpdate {
    
    public int idRetrieval();
    public String login();
    public boolean modifyCompleteProfile();
    public boolean updateName();
    public boolean updateUsername();
    public boolean resetPassword();
    public boolean updateContactNumber();
    public boolean updateEmail();
    public boolean updateAddress();
    
}
