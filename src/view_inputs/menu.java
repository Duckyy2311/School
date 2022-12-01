/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_inputs;

import controllers.ManagerUser;
import static controllers.ManagerUser.PassConvert;

import java.util.Scanner;
import model.*;
import controllers.SettingController;
import dao.DaoUser;
import static dao.DaoUser.CheckPass;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.mail.MessagingException;
/**
 *
 * @author Admin
 */
public class menu {
    ManagerUser MU = new ManagerUser();
        Scanner sc  = new Scanner(System.in);
        SettingController SL = new SettingController();
           public void loginUser() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException{
            String Email = Inputter.inputPattern("Enter Email (Ducky@gmail.com): ", "Wrong Email format! Please re-Enter: ", "^[a-zA-Z][a-zA-Z0-9]+@[a-z]+(\\.[a-z]+){1,6}$");
            String Pass = Inputter.inputNonBlankStr("Enter Password: ");
                    if(CheckPass(Email, PassConvert(Pass))==false){
            int op = Inputter.inputInt("UserID or Password is wrong(1-Homepage,2-Login again) your option: ", 1, 2);
            if(op==2) loginUser();
            else return;
        }
                    else loginA(Email);
        }
        public void loginA(String Email) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException{
            int option;
                                while(true){
                                    System.out.println("\nHomePage--------- ");
                                    System.out.println("1. DashBoard");
                                    System.out.println("2. Change Password");
                                    System.out.println("3. User Profile");
                                    System.out.println("4. Log out");
                                    System.out.print("Your option: ");
                                int id = DaoUser.findProUemail(Email).getUserId();
                                option = Inputter.inputInt("",1,4);
                                switch(option){
                                    
                                    case 1: {int role = DaoUser.searchRolebymail(Email);
                                        switch(role){
                                            case 1: StudentMenu.studentView(id);break;//student view
                                            case 2: TrainnerMenu.trainerView(id);break;//Train view
                                            case 3: AdminMenu.viewAdmin(id); break;//Admin view
                                            case 4: ManaMenu.viewMana(id);break;//Mana, auth view
                                        }
                                            break;}
                                    case 2: MU.ChangePass(Email); break;
                                    case 3: MU.findProUser(Email);break;
                                    case 4: break;
                                    default: break;
                                }
                                if(option==4)
                                    break;
                                } 
                                
        }
        public void regisUser() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, MessagingException, UnsupportedEncodingException{
            MU.Register();
            
            int option;
                    System.out.println("\nRegister Sucsess--------- ");
                    System.out.println("1. Homepage");
                    System.out.println("2. Login");
                    System.out.print("Your option: ");
            option = Inputter.inputInt("",1, 2);
            if(option==2) loginUser();
        }
        
       
}
