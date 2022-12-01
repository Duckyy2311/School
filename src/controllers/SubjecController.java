/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import view_inputs.Inputter;
import connection.ConnectionUtils;
import dao.DaoSubject;
import java.sql.Connection;
import java.sql.SQLException;
import model.Subject;

/**
 *
 * @author AnhMinh
 */
public class SubjecController {
   
            public void SearchSubjectList() throws SQLException, ClassNotFoundException{
        int auid = Inputter.inputInt("Enter a Authorid you want to search for: ",0,Integer.MAX_VALUE);
        int count = DaoSubject.countSubjectByAuthorID(auid);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
        
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoSubject.AuthorDisplayByPage(noPage,auid);
    } 
     public static void SubjectList() throws SQLException, ClassNotFoundException {
         SubjecController b = new SubjecController();
         b.ShowPagination();

        int option;
         while(true){

            System.out.println("\nSubjectList Menu--------- ");
            System.out.println("1. Update Subject");
            System.out.println("2. Active/inactive Subject");
            System.out.println("3. Search SubjectList by AuthorId:");
            System.out.println("4. Add new Subject");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            SubjecController s = new SubjecController();
            option = Inputter.inputInt("",0,4);
            switch(option){
                case 1: s.UpdateSubject();; break;             
                case 2: s.InactiveSubject(); break; 
                case 3: s.SearchSubjectList(); break;
                case 4: s.AddSubject();
                case 0: System.out.println("Bye!"); return;
                default: System.out.println("Again!"); break;
                
            }
        }        
    }
     
     public void UpdateSubject() throws SQLException, ClassNotFoundException{
            System.out.println("\nUpdating Subject--------- ");
            SubjecController w = new SubjecController();
            
            w.ShowPagination();
            int ID=Inputter.inputInt("Input Subject ID you want to update: ",0,Integer.MAX_VALUE);
            while(DaoSubject.SearchSubjectID(ID)==false){
                ID = Inputter.inputInt("Wrong Subject ID, reenter: ",0,Integer.MAX_VALUE);
            }
                    //updating Setting type ID
                    String newSubCode = Inputter.inputNonBlankStr("input new Subject_Code: ");                   

                    //updating Setting title                  
                    String newSubName = Inputter.inputNonBlankStr("input new Subject_Name: ");
                    
                    DaoSubject.AuthorDisplay();
                    //updating Setting value
                    
                    int newAuthID = Inputter.inputInt("input new AuthorID(): ",1,4);                         

                    //updating status
                    int newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);  
                    
             Subject b = new Subject(ID, newSubCode, newSubName, newAuthID, newStatus);       
                    DaoSubject.UpdateSubject(b);
           
         int option;
         while(true){

            System.out.println("-----------------------------------");
            System.out.println("1. Update another Setting");
            System.out.println("2. Deactivate status");
            System.out.println("3. Search Subject by AuthorID");
             System.out.println("4. Add new Subject");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            SubjecController s = new SubjecController();
            option = Inputter.inputInt("",0,4);
            switch(option){
                case 1: s.UpdateSubject();; break;   
                case 2: s.InactiveSubject();break;
                case 3: s.SearchSubjectList();break;
                case 4: s.AddSubject();
                case 0: System.out.println("Bye!"); return;
                default: System.out.println("Again!"); break;
                
            }
        }        
                }
     public void AddSubject() throws SQLException, ClassNotFoundException{
            System.out.println("\nAdd Subject--------- ");
            int ID=Inputter.inputInt("Input Subject ID you want to Add: ",0,Integer.MAX_VALUE);
            while(DaoSubject.SearchSubjectID(ID)==true){
                ID = Inputter.inputInt("this Subject ID is already exist, please try again: ",0,Integer.MAX_VALUE);
            }
                    // Setting type ID
                    String newSubCode = Inputter.inputNonBlankStr("input new Subject_Code: ");                   

                    //updating Setting title                  
                    String newSubName = Inputter.inputNonBlankStr("input new Subject_Name: ");
                    
                    DaoSubject.AuthorDisplay();
                    //updating Setting value
                    
                    int newAuthID = Inputter.inputInt("input new AuthorID(): ",1,4);                         

                    //updating status
                    int newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);  
                    
             Subject b = new Subject(ID, newSubCode, newSubName, newAuthID, newStatus);       
                    DaoSubject.UpdateSubject(b);
           
         int option;
         while(true){

            System.out.println("-----------------------------------");
            System.out.println("1. Update another Setting");
            System.out.println("2. Deactivate status");
            System.out.println("3. Search Subject by AuthorID");
            System.out.println("4. Add new Subject");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            SubjecController s = new SubjecController();
            option = Inputter.inputInt("",0,3);
            switch(option){
                case 1: s.UpdateSubject();; break;   
                case 2: s.InactiveSubject();break;
                case 3: s.SearchSubjectList();break;
                case 4: s.AddSubject();
                case 0: System.out.println("Bye!"); return;
                default: System.out.println("Again!"); break;
                
            }
        }        
                }
    
    private void InactiveSubject() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection(); 
        String ms = "";
        String[] TypeStatus = {"inactive", "active"};
       
        System.out.println("\nStatus menu-----------");
                        
                        System.out.println("1. inactivate");
                        System.out.println("2. activate");
                        int option = Inputter.inputInt("", 0, 3);
                        

            if(option==1){
                SubjecController.AcStatusPagination();
                int ID=Inputter.inputInt("Input Subject ID you want to change Status into inactive: ",0,Integer.MAX_VALUE);
            while(DaoSubject.SearchSubjectID(ID)==false){
                
                ID = Inputter.inputInt("Wrong Setting ID, reenter: ",0,Integer.MAX_VALUE);
                
            }
                DaoSubject.UpdateStatus(0, ID);
        
        
        
        
    }
            if(option==2){
            SubjecController.InStatusPagination();
            int ID= Inputter.inputInt("Input Subject ID you want to change Status into active: ",0,Integer.MAX_VALUE);
            while(DaoSubject.SearchSubjectID(ID)==false){
                
                ID = Inputter.inputInt("Wrong Subject ID, reenter: ",0,Integer.MAX_VALUE);
                
            }
            DaoSubject.UpdateStatus(1, ID);
                     

      
         
    }
    int opt;
         while(true){

            System.out.println("-----------------------------------");
            System.out.println("1. Update  Subject");
            System.out.println("2. Update another Status");
            System.out.println("3. Search Subject by author id ");
            System.out.println("0. Exit to main menu");
            System.out.print("Your option: ");
            SubjecController s = new SubjecController();
            opt = Inputter.inputInt("",0,4);
            switch(opt){
                case 1: s.UpdateSubject();; break;     
                case 2: s.InactiveSubject(); break;  
                case 3: s.SearchSubjectList();
                case 4: s.AddSubject();
                case 0: System.out.println("Bye!"); return;
                default: System.out.println("Again!"); break;
                
            }
        }  
         
         
}
    public static void ShowPagination() throws SQLException, ClassNotFoundException{
        int count = DaoSubject.countSubject();
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoSubject.SubjectDisplayByPage(1,"");
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to "+countPage+"'(0-exit)(): ", 0, countPage);
            if(noPage==0) return;
            else DaoSubject.SubjectDisplayByPage(noPage,"");
            
          
            
        
    }
    public static void AcStatusPagination() throws SQLException, ClassNotFoundException{
        int count = DaoSubject.countAcStatus();
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoSubject.StatusDisplayByPage(1,1);
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0 ) return;
            else DaoSubject.StatusDisplayByPage(noPage,1);
        
    }
    public static void InStatusPagination() throws SQLException, ClassNotFoundException{
        int count = DaoSubject.countInStatus();
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoSubject.StatusDisplayByPage(1,0);
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoSubject.StatusDisplayByPage(noPage,0);
        
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SubjecController b = new SubjecController();
        b.AddSubject();
    }
    
}
