/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_inputs;

import controllers.ManagerUser;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Main {
    public static void main(String[] args) throws Exception {
        menu menu = new menu();
        ManagerUser manage_user = new ManagerUser();
        Scanner sc  = new Scanner(System.in);
        int option;
        
        
       // menu.addEmployee("005", "Thong", "SoaiCa", "6677350805", "Freaky@tiktok.com", "Ha Noi", Date, "Male", 3000, "Tai Ngan Anh");
        
        while(true){

            System.out.println("\nMenu--------- ");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Reset Pass");
            System.out.println("4. Exit");
            System.out.print("Your option: ");
            
            option = Inputter.inputInt("",1,4);
            switch(option){
                case 1: menu.loginUser() ; break;             
                case 2: menu.regisUser(); break;
                case 3: manage_user.resetPass(); break;
                case 4: System.out.println("Bye!"); return;
                default: System.out.println("Again!"); break;
                
            }
        }        
    }
}
