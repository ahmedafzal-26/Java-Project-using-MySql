/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrated.ca;

/**
 *
 * @author Zain Ikram
 */
public class MenuGenerator {
    
    public void mainMenu(){
        System.out.println("------------------------------------------------------------------");
        System.out.println("                        Welcome to Revenue Portal                 ");
        System.out.println("------------------------------------------------------------------");
        System.out.println("                          Press (1) for Admin");
        System.out.println("                        Press (2) for Reguar User");
    }
    
    public void adminMenu(){
        System.out.println("------------------------------------------------------------------");
        System.out.println("                    Welcome to Revenue Portal - Admin                ");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Enter...");
        System.out.println("    1. Modify your Profile.");
        System.out.println("    2. View the list Regular users in the system.");
        System.out.println("    3. Remove User from the syatem.");
        System.out.println("    4. Review the operations performed by Regular users.");
    }
    
    public void adminSubMenu(){
        System.out.println("            =>Press (1) to Update Username.");
        System.out.println("            =>Press (2) to Reset Password.");
        System.out.println("            =>Press (3) to Update Name.");
        System.out.println("            =>Press (4) to Update Email.");
        System.out.println("            =>Press (5) to Update Address.");
        System.out.println("            =>Press (6) to Update Contact Number.");
        System.out.println("            =>Press (7) to Update Complete Profile.");
    }
    
    public void userMenu(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("            Welcome to Revenue Portal - Regular User                ");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter...");
        System.out.println("    1. Login into system");
        System.out.println("    2. Register [If your fist time on the portal please register yourself. Thanks].");
    }
    
    public void userSubMenu(){
        System.out.println("Enter...");
        System.out.println("    1. Modify your Profile...");
        System.out.println("    3. View your Tax Owed.");
    }
    public void userSubMenu2(){
        System.out.println("            =>Press (1) to Update Username.");
        System.out.println("            =>Press (2) to Reset Password.");
        System.out.println("            =>Press (3) to Update Name.");
        System.out.println("            =>Press (4) to Update Email.");
        System.out.println("            =>Press (5) to Update Address.");
        System.out.println("            =>Press (6) to Update Contact Number.");
        System.out.println("            =>Press (7) to Update Complete Profile.");
    }
    
}
