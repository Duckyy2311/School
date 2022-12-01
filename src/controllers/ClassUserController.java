/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import view_inputs.Inputter;
import connection.ConnectionUtils;
import dao.DaoClass;
import dao.DaoClassUser;
import dao.DaoSubject;
import dao.DaoUser;
import dao.DaoTeam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.ClassUser;
import model.Team;

/**
 *
 * @author AnhMinh
 */
public class ClassUserController {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ClassUserController b = new ClassUserController();
        b.addClassUser();
    }
     public void addClassUser() throws SQLException, ClassNotFoundException{
     SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
          
            System.out.println("\nAdd ClassUser--------- ");
            System.out.println("Class List"+DaoClassUser.ClassOnlList());
            int cID=Inputter.inputInt("input Class for the classUser using class_id: ",0,Integer.MAX_VALUE);
            while(DaoClass.SearchCIDonl(cID)==false){
                cID = Inputter.inputInt("this Class is not exist, please try again: ",0,Integer.MAX_VALUE);
            }
            System.out.println("Team List"+DaoClassUser.TeamOnlList(cID));
                  int newTeamid = Inputter.inputInt("input  Team you want to add to classUser: ",0,Integer.MAX_VALUE);                   
            while(DaoTeam.SearchTeamID(newTeamid)==false){
                newTeamid = Inputter.inputInt("this Team is not exist, please try again: ",0,Integer.MAX_VALUE);
            }
            
            System.out.println("User List"+DaoClassUser.UserOnlList(newTeamid,cID));
                  int newUser = Inputter.inputInt("Input User you want to add to classUser: ",0,Integer.MAX_VALUE);                   
            while(DaoUser.SearchUID(newUser)==false){
                newTeamid = Inputter.inputInt("this User is not exist, please try again: ",0,Integer.MAX_VALUE);
            }
                    
                                     
                    int Teamlead = Inputter.inputInt("Teamleader:[0-yes, 1-no] ",0,1);  
                    
                    Date dropout = Inputter.getDate("input new dropout date: ");      
                    
                    String usernote = Inputter.inputNonBlankStr("input usernotes: "); 
                    
                    String ongoing = Inputter.inputNonBlankStr("input ongoing eval: "); 
                    
                    String finalpres = Inputter.inputNonBlankStr("input final pres eval: ");      
                    
                    String finaltopic = Inputter.inputNonBlankStr("input final topic eval: "); 
                    
                    
                    int newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);  
                    
             ClassUser b = new ClassUser(cID,newTeamid,newUser,Teamlead,dropout, usernote, ongoing, finalpres,finaltopic, newStatus);       
                    DaoClassUser.AddClassUser(b);
                    }
     
     
      public static void showPagination() throws SQLException, ClassNotFoundException{
        int count = DaoClassUser.countUser("a.user_id","");
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoClassUser.TeamDisplayByPage(1, "a.user_id","");
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoClassUser.TeamDisplayByPage(noPage, "a.user_id","");
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Class List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoClassUser.TeamDisplayByPage(noPage, "a.user_id","");break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoClassUser.TeamDisplayByPage(noPage, "a.user_id","");break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    
                                
    }
    }
     @SuppressWarnings("empty-statement")
      public void classUserDetail() throws SQLException, ClassNotFoundException{
          SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(DaoUser.RoleList(1)) ;
        int UId = Inputter.inputInt("Enter user id to update: ", 0, Integer.MAX_VALUE);
        ClassUser c1 = DaoClassUser.ProUemail(UId);
                while(c1==null){
            UId = Inputter.inputInt("This user isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoClassUser.ProUemail(UId);
        }
                System.out.printf("%-15s%-15s%-15s%-20s%-20s%-20s%-20s%-20s%-20s%-10s\n",
                "ClassID",
                "teamID",
                "User_ID",
                "Team leader",
                "Dropout date",
                "user notes",
                "ongoing eval",
                "final pres eval",
                "final topic eval",
                "Status");
   
         System.out.println(c1);
        int ClassID = 0;
        ;
        int op = Inputter.inputInt("Do u want to update Class ID(1-Y,2-N): ", 1, 2);
        if(op == 1) {System.out.println("Class List"+DaoClassUser.ClassOnlList());
            ClassID = Inputter.inputInt("Enter class ID:",0,Integer.MAX_VALUE);}
        if(op == 2) ClassID = c1.getClass_id();
        
        int teamID = 0;
        
        op = Inputter.inputInt("Do u want to update Team ID(1-Y,2-N): ", 1, 2);
        if(op == 1){ System.out.println("Team List"+DaoClassUser.TeamOnlList(ClassID));
            teamID = Inputter.inputInt("Enter Team ID:",0,Integer.MAX_VALUE);}
        if(op == 2) teamID = c1.getTeam_id();
        
        int UserID = 0;
        
         op = Inputter.inputInt("Do u want to update User ID(1-Y,2-N): ", 1, 2);
        if(op == 1){ System.out.println("User List"+DaoUser.RoleList(1));
        UserID = Inputter.inputInt("Enter User ID:",0,Integer.MAX_VALUE);
        }if(op == 2) UserID = c1.getClass_id();
        
        int Teamlead = 0;
        op = Inputter.inputInt("Do u want to update TeamLeader(1-Y,2-N): ", 1, 2);      
        if(op == 1)Teamlead = Inputter.inputInt("Teamleader ?:(0-yes, 1-no)",0,1);;
        if(op == 2) Teamlead = c1.getTeam_leader();
        
        Date dropout = null;
        op = Inputter.inputInt("Do u want to update dropout date(1-Y,2-N): ", 1, 2);      
        if(op == 1)dropout = Inputter.getDateFur("input new dropout date: ");;  
        if(op == 2)dropout = c1.getDropout_date();
        
        String usernote = null ;
        op = Inputter.inputInt("Do u want to update User note(1-Y,2-N): ", 1, 2);      
        if(op == 1)usernote = Inputter.inputNonBlankStr("input new user note:");;
        if(op == 2) usernote = c1.getUser_notes();
        
        String ongoing = null ;
        op = Inputter.inputInt("Do u want to update ongoing eval (1-Y,2-N): ", 1, 2);      
        if(op == 1)ongoing = Inputter.inputNonBlankStr("input ongoing eval:");;
        if(op == 2) ongoing = c1.getOngoing_eval();
        
        String finalpres = null ;
        op = Inputter.inputInt("Do u want to update final pres eval(1-Y,2-N): ", 1, 2);      
        if(op == 1)finalpres = Inputter.inputNonBlankStr("input final pres eval:");;
        if(op == 2) finalpres = c1.getFinal_press_eval();
        
        String finaltopic = null ;
        op = Inputter.inputInt("Do u want to update final topic eval(1-Y,2-N): ", 1, 2);      
        if(op == 1)finaltopic = Inputter.inputNonBlankStr("input final pres eval:");;
        if(op == 2) finaltopic = c1.getFinal_topic_eval();
        
        int status = c1.getStatus();        
        ClassUser c2 = new ClassUser(ClassID, teamID, UserID, Teamlead, dropout, usernote, ongoing, finalpres, finaltopic,status);

        op = Inputter.inputInt("Do u want to update ClassUser(1-Y,2-N): ", 1, 2);
        if(op == 1) DaoClassUser.UpdateClassUser(c2);
        if(op == 2) return;
    }
      public void search() throws SQLException, ClassNotFoundException{
        int option;
        while(true){
                                    System.out.println("\nSearch by--------- ");
                                    System.out.println("1. Class code      3.Name");
                                    System.out.println("2. Topic code      4.Status");
                                    System.out.println("5. Back to ClassUserList");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,5);
                                switch(option){
                                    case 1: searchInt("Enter a part of Class ID: ", "a.class_id",0,Integer.MAX_VALUE);break;
                                    case 2: searchInt("Enter a part of Team ID: ", "a.team_id",0,Integer.MAX_VALUE);break;
                                    case 3: searchInt("Enter a part of User ID: ", "a.user_id",0,Integer.MAX_VALUE);break;
                                    case 4: searchInt("Enter Status(1-Active,0-Inactive): ", "a.status", 0,1);break;
                                   
                                    default: break;
                                }
                                if(option==5)
                                    return;
    }}
      public void searchInt(String msg,String field,int min,int max) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoClassUser.countClassUser(field,String.valueOf(filter));
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
        DaoClassUser.TeamDisplayByPage(1,field,String.valueOf(filter));
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoClassUser.TeamDisplayByPage(1,field,String.valueOf(filter));
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Class List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoClassUser.TeamDisplayByPage(noPage,field,String.valueOf(filter));break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoClassUser.TeamDisplayByPage(noPage,field,String.valueOf(filter));break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
      public void searchString(String msg,String field) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoClassUser.countClassUser(field,filter);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoClassUser.TeamDisplayByPage(1,field,filter);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoClassUser.TeamDisplayByPage(noPage,field,filter);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Class List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoClassUser.TeamDisplayByPage(noPage,field,filter);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoClassUser.TeamDisplayByPage(noPage,field,filter);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
      public void inactiveClassUser() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection(); 
        String ms = "";
        String[] TypeStatus = {"inactive", "active"};
       
        System.out.println("\nStatus menu-----------");
                        
                        System.out.println("1. inactivate");
                        System.out.println("2. activate");
                        int option = Inputter.inputInt("", 0, 3);
                        

            if(option==1){
                ClassUserController.acStatusPagination();
                 int ID= Inputter.inputInt("Input user ID you want to change Status into active: ",0,Integer.MAX_VALUE);
            while(DaoClassUser.SearchUserID(ID)==false){
                
                ID = Inputter.inputInt("Wrong user ID, reenter: ",0,Integer.MAX_VALUE);
                
            }
                DaoClassUser.UpdateStatus(0, ID);
        
        
        
        
    }
            if(option==2){
            ClassUserController.inStatusPagination();
            int ID= Inputter.inputInt("Input user ID you want to change Status into active: ",0,Integer.MAX_VALUE);
            while(DaoClassUser.SearchUserID(ID)==false){
                
                ID = Inputter.inputInt("Wrong user ID, reenter: ",0,Integer.MAX_VALUE);
                
            }
            DaoClassUser.UpdateStatus(1, ID);
                     

      
         
    }
      }
            public static void acStatusPagination() throws SQLException, ClassNotFoundException{
        int count = DaoClassUser.countAcStatus();
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoClassUser.StatusDisplayByPage(1,1);
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0 ) return;
            else DaoClassUser.StatusDisplayByPage(noPage,1);
        
    }
    public static void inStatusPagination() throws SQLException, ClassNotFoundException{
        int count = DaoClassUser.countInStatus();
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoClassUser.StatusDisplayByPage(1,0);
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoClassUser.StatusDisplayByPage(noPage,0);
        
    }
      
}
