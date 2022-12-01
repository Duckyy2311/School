/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import view_inputs.Inputter;
import connection.ConnectionUtils;
import dao.DaoTeam;
import dao.DaoClassUser;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import model.Team;

/**
 *
 * @author AnhMinh
 */
public class TeamController {
      
            public void searchTeamList() throws SQLException, ClassNotFoundException{
        int auid = Inputter.inputInt("Enter a Class you want to search for: ",0,Integer.MAX_VALUE);
        int count = DaoTeam.countTeamByClass(auid);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTeam.ClassDisplayByPage(1,auid);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeam.ClassDisplayByPage(noPage,auid);
    } 
     
     
     public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TeamController b = new TeamController();
        b.inactiveTeam();
    }
     public void addTeam() throws SQLException, ClassNotFoundException{
          
            System.out.println("\nAdd Team--------- ");
            int ID=Inputter.inputInt("Input Team ID you want to Add: ",0,Integer.MAX_VALUE);
            while(DaoTeam.SearchTeamID(ID)==true){
                ID = Inputter.inputInt("this Team ID is already exist, please try again: ",0,Integer.MAX_VALUE);
            }
                     System.out.println("Class List"+DaoTeam.ClassOnlList());
                    int newclassID = Inputter.inputInt("input new ClassId: ",0,Integer.MAX_VALUE);                   

                                     
                    String newTocode = Inputter.inputNonBlankStr("input new Topic_code: ");  
                    
                    String newToname = Inputter.inputNonBlankStr("input new Topic_name: ");      
                    
                    String newgit = Inputter.inputNonBlankStr("input new gitlab_url(): "); 

                    
                    int newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);  
                    
             Team b = new Team(ID, newclassID, newTocode, newToname,newgit, newStatus);       
                    DaoTeam.AddTeam(b);
           
//         int option;
//         while(true){
//
//            System.out.println("-----------------------------------");
//            System.out.println("1. Update another Team");
//            System.out.println("2. Deactivate status");
//            System.out.println("3. Search Subject by AuthorID");
//            System.out.println("4. Add new Subject");
//            System.out.println("0. Exit");
//            System.out.print("Your option: ");
//            TeamController s = new TeamController();
//            option = Inputter.inputInt("",0,3);
//            switch(option){
//                case 1: s.UpdateTeam();; break;   
//                case 2: s.InactiveTeam();break;
//                case 3: s.SearchTeamList();break;
//                case 4: s.AddTeam();break;
//                case 0: System.out.println("Bye!"); return;
//                default: System.out.println("Again!"); break;
//                
//            }
//        }        
                }
     
     public void updateTeam() throws SQLException, ClassNotFoundException{
            System.out.println("\nUpdating Team--------- ");
            TeamController w = new TeamController();
            
            TeamController.showPagination();
            int ID=Inputter.inputInt("Input Team ID you want to update: ",0,Integer.MAX_VALUE);
            
        Team c1 = DaoTeam.ProUemail(ID);
                while(c1==null){
            ID = Inputter.inputInt("This team isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoTeam.ProUemail(ID);
        }
            
            
            
            
            
                      int newclassID = 0;     
                      
                     int op = Inputter.inputInt("Do u want to update classID?(1-Y,2-N): ", 1, 2);      
                     System.out.println("Class List"+DaoTeam.ClassOnlList());
                     if(op == 1)newclassID = Inputter.inputInt("input new ClassId: ",0,Integer.MAX_VALUE); 
                     if(op == 2) newclassID = c1.getClass_id();
                    
                            String newTocode = null;          
                      op = Inputter.inputInt("Do u want to update Topic Code?(1-Y,2-N): ", 1, 2);      
                     if(op == 1)newTocode = Inputter.inputNonBlankStr("input new Topic_code: ");
                     if(op == 2) newTocode = c1.getTopicCode();
                     
                     String newToname =null;
                      op = Inputter.inputInt("Do u want to update TeamLeader(1-Y,2-N): ", 1, 2);      
                     if(op == 1)newToname = Inputter.inputNonBlankStr("input new Topic_name: ");  
                     if(op == 2) newToname = c1.getTopicName();
                     
                     
                      String newgit = null;
                      op = Inputter.inputInt("Do u want to update TeamLeader(1-Y,2-N): ", 1, 2);      
                     if(op == 1)newgit = Inputter.inputNonBlankStr("input new gitlab_url(): "); 
                     if(op == 2) newgit = c1.getGitlabUrl();
                     
                     int newStatus =0;
                      op = Inputter.inputInt("Do u want to update Status(1-Y,2-N): ", 1, 2);      
                     if(op == 1)newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1); 
                     if(op == 2) newStatus = c1.getStatus();
                                   Team b = new Team(ID, newclassID, newTocode, newToname,newgit, newStatus);       
                    op = Inputter.inputInt("Do u want to update This Team(1-Y,2-N): ", 1, 2);      
                     if(op == 1){DaoTeam.UpdateTeam(b);TeamController.showPagination();}
                     if(op == 2) return;
                    
               
                    
           
//         int option;
//         while(true){
//
//            System.out.println("-----------------------------------");
//            System.out.println("1. Update another Team");
//            System.out.println("2. Active/Deactivate status");
//            System.out.println("3. Search Team by Class");
//            System.out.println("4. Add a new Team");
//            System.out.println("0. Exit");
//            System.out.print("Your option: ");
//            TeamController s = new TeamController();
//            option = Inputter.inputInt("",0,3);
//            switch(option){
//                case 1: s.UpdateTeam();; break;   
//                case 2: s.InactiveTeam();break;
//                case 3: s.SearchTeamList();break;
//                case 4: s.AddTeam();break;
//                case 0: System.out.println("Bye!"); return;
//                default: System.out.println("Again!"); break;
//                
//            }
//     }        
                }
    
    public void inactiveTeam() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection(); 
        String ms = "";
       
        System.out.println("\nStatus menu-----------");
                        
                        System.out.println("1. inactivate");
                        System.out.println("2. activate");
                        System.out.println("0. back to menu");
                        int option = Inputter.inputInt("", 0, 3);
                        

            if(option==1){
                TeamController.acStatusPagination();
                int ID=Inputter.inputInt("Input Team ID you want to change Status into inactive: ",0,Integer.MAX_VALUE);
            while(DaoTeam.SearchTeamID(ID)==false){
                
                ID = Inputter.inputInt("Wrong Team ID, reenter: ",0,Integer.MAX_VALUE);
                
            }
                DaoTeam.UpdateStatus(0, ID);
        
        
        
        
    }
            if(option==2){
            TeamController.inStatusPagination();
            int ID= Inputter.inputInt("Input Team ID you want to change Status into active: ",0,Integer.MAX_VALUE);
            while(DaoTeam.SearchTeamID(ID)==false){
                
                ID = Inputter.inputInt("Wrong Team ID, reenter: ",0,Integer.MAX_VALUE);
                
            }
            DaoTeam.UpdateStatus(1, ID);
                     

      
         
    }
            if(option !=1 && option!=2)
                return;
//    int opt;
//         while(true){
//
//            System.out.println("-----------------------------------");
//            System.out.println("1. Update  Team");
//            System.out.println("2. Update another Status");
//            System.out.println("3. Add a new Team");
//            System.out.println("0. Exit to main menu");
//            System.out.print("Your option: ");
//            TeamController s = new TeamController();
//            opt = Inputter.inputInt("",0,3);
//            switch(opt){
//                case 1: s.UpdateTeam();; break;     
//                case 2: s.InactiveTeam();break;  
//                case 3 : s.AddTeam();break;
//                case 0: System.out.println("Bye!"); return;
//                default: System.out.println("Again!"); break;
//                
//            }
//        }  
         
         
}
    public static void showPagination() throws SQLException, ClassNotFoundException{
        int count = DaoTeam.countTeam();
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTeam.TeamDisplayByPage(1,"");
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to "+countPage+"'(0-exit)(): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeam.TeamDisplayByPage(noPage,"");
            
          
            
        
    }
    public static void acStatusPagination() throws SQLException, ClassNotFoundException{
        int count = DaoTeam.countAcStatus();
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTeam.StatusDisplayByPage(1,1);
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0 ) return;
            else DaoTeam.StatusDisplayByPage(noPage,1);
        
    }
    public static void inStatusPagination() throws SQLException, ClassNotFoundException{
        int count = DaoTeam.countInStatus();
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTeam.StatusDisplayByPage(1,0);
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeam.StatusDisplayByPage(noPage,0);
        
    }
    
   
    
}
