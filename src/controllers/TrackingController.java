/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.ConnectionUtils;
import dao.DaoMilestone;
import dao.DaoTracking;
import dao.DaoUser;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import model.Tracking;
import view_inputs.Inputter;

/**
 *
 * @author AnhMinh
 */
public class TrackingController {
    public void addTrackingStudent(int id,int team_id) throws SQLException, ClassNotFoundException{

                System.out.println("\nAdd Tracking--------- ");
                System.out.println("You are on team: "+team_id);
                System.out.println("\nMilestones List"+DaoTracking.MilestoneOfflListStudent(id, team_id));
                  int newmilestone = Inputter.inputInt("Input milestone you want to add to this tracking: ",0,Integer.MAX_VALUE);                   
                while(DaoMilestone.SearchMileIDonl(newmilestone)==false){
                newmilestone = Inputter.inputInt("this milestone is not exist, please try again: ",0,Integer.MAX_VALUE);
                }
                System.out.println("Funcion List"+DaoTracking.funcionOfflListStudent(id, team_id));
                      int newFunction = Inputter.inputInt("Input Function you want to add to this tracking: ",0,Integer.MAX_VALUE);                   
                while(DaoTracking.checkFunctionID(newFunction)==false){
                    newFunction = Inputter.inputInt("this Function is not exist, please try again: ",0,Integer.MAX_VALUE);
                }
        
                    
                    System.out.println("Assigner List"+DaoUser.RoleList(2));      
                    int assigner_id = Inputter.inputInt("Input assigner_id: ",0,Integer.MAX_VALUE); 
                    while(DaoUser.SearchUID(assigner_id)==false){
                    assigner_id = Inputter.inputInt("this User is not exist, please try again: ",0,Integer.MAX_VALUE);
                    }
                    
                    int assignee_id = id; 
                    
                    
                    
                    
                    String trackingNote = Inputter.inputNonBlankStr("input Tracking Note: "); 
                   
                    
                    String updates = Inputter.inputNonBlankStr("input updates: ");                       
                                    
                    
                    int newStatus = Inputter.inputInt("Status([Planned-0][Analysed-1][Designed-2][Coded-3]"
                            + "[Integrated-4][Submitted-5][Evaluated-6][Rejected-7]): ", 0, 7);  
                    
             Tracking b = new Tracking(team_id,newmilestone,newFunction,assigner_id, assignee_id, trackingNote, updates,newStatus);       
                    DaoTracking.addTracking(b);
                    
                    }
     public void deleteTracking(int id) throws SQLException, ClassNotFoundException{
     
         
            System.out.println("\nDelete Tracking--------- ");
            TrackingController.showPaginationStudent(id);
                    int trackingid = Inputter.inputInt("Enter tracking id to delete: ", 0, Integer.MAX_VALUE);
                    
                    
                  
                    DaoTracking.deleteStatus(trackingid);
                   
                    }

    public static void showPagination(int id) throws SQLException, ClassNotFoundException{
        int count = DaoTracking.countTrackingTrainer("a.team_id","",id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTracking.trackingDisplayByPageTrainer(1, "a.team_id","",id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTracking.trackingDisplayByPageTrainer(1, "a.team_id","",id);
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
                                        else {DaoTracking.trackingDisplayByPageTrainer(noPage, "a.team_id","",id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTracking.trackingDisplayByPageTrainer(noPage, "a.team_id","",id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    
                                
    }
    }
    
      public static void showPaginationStudent(int id) throws SQLException, ClassNotFoundException{
        int count = DaoTracking.countTrackingStudent("a.team_id","",id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTracking.trackingDisplayByPageStudent(1, "a.team_id","",id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTracking.trackingDisplayByPageStudent(1, "a.team_id","",id);
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
                                        else {DaoTracking.trackingDisplayByPageStudent(noPage, "a.team_id","",id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTracking.trackingDisplayByPageStudent(noPage, "a.team_id","",id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    
                                
    }
    }
     

      public void trackingUpdate(int id,int team_id) throws SQLException, ClassNotFoundException{
          SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        DaoTracking.trackingDisplayByPageStudent(1, "a.team_id",String.valueOf(team_id),id);
        int tracking_id = Inputter.inputInt("Enter tracking id to update: ", 0, Integer.MAX_VALUE);
        Tracking c1 = DaoTracking.checkTracking(tracking_id);
                while(c1==null){
            tracking_id = Inputter.inputInt("This tracking isn't exist,re-enter: ", 0, Integer.MAX_VALUE);

           
        }
               int op =0;
                System.out.printf("%-15s%-10s%-10s%-15s%-15s%-15s%-15s%-15s\n",
                "topic "
              ,"milestone"
              ,"function" 
              ,"assigner"
              ,"assignee "
               ,"tracking_note"
              ,"updates " 
              ,"status ");
        DaoTracking.trackingDisplayByPageStudent(1, "a.tracking_id",String.valueOf(tracking_id),id);
        
        int milestone_Id = 0;
        
        op = Inputter.inputInt("Do u want to update Milestone(1-Y,2-N): ", 1, 2);
        if(op == 1){ System.out.println("milestone List"+DaoTracking.MilestoneOfflListStudent(id,team_id));
            milestone_Id = Inputter.inputInt("Enter milestone ID:",0,Integer.MAX_VALUE);}
        if(op == 2) milestone_Id = c1.getMileStoneid();
        
        int function_Id = 0;
        
         op = Inputter.inputInt("Do u want to update Function(1-Y,2-N): ", 1, 2);
        if(op == 1){ System.out.println("Function List"+DaoTracking.funcionOfflListStudent(id, team_id));
        function_Id = Inputter.inputInt("Enter Function ID:",0,Integer.MAX_VALUE);
        }if(op == 2) function_Id = c1.getFunction_id();
        
        int assigner_Id = 0;
        op = Inputter.inputInt("Do u want to update Assigner(1-Y,2-N): ", 1, 2);      
        if(op == 1){System.out.println("Assigner List"+DaoUser.RoleList(2));        
            assigner_Id = Inputter.inputInt("Enter Assigner ID ",0,Integer.MAX_VALUE);;}
        if(op == 2) assigner_Id = c1.getAssigner_id();
        
        int assignee_Id = 0;
        op = Inputter.inputInt("Do u want to update Assigner(1-Y,2-N): ", 1, 2);      
        if(op == 1){System.out.println("Assignee List"+DaoUser.RoleList(1)); 
            assignee_Id = Inputter.inputInt("Enter Assignee ID :",0,Integer.MAX_VALUE);;}
        if(op == 2) assignee_Id = c1.getAssignee_id();
        
        String tracking_Note = null ;
        op = Inputter.inputInt("Do u want to update Tracking note(1-Y,2-N): ", 1, 2);      
        if(op == 1)tracking_Note = Inputter.inputNonBlankStr("input new tracking note:");;
        if(op == 2) tracking_Note = c1.getNote();
        
        String updates = null ;
        op = Inputter.inputInt("Do u want to change updates(1-Y,2-N): ", 1, 2);      
        if(op == 1)updates = Inputter.inputNonBlankStr("input updates:");;
        if(op == 2) updates = c1.getUpdates();
     
        
        int status = c1.getStatus();   
        
        Tracking c2 = new Tracking(team_id, milestone_Id, function_Id, assigner_Id, assignee_Id, tracking_Note, updates,status);
        c2.setTracking_id(tracking_id);
        DaoTracking.updateTracking(c2);
        
    }
      
      public void trackingUpdateTrainer(int id) throws SQLException, ClassNotFoundException{
          SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        DaoTracking.trackingDisplayByPageTrainer(1, "a.team_id","",id);
        int tracking_id = Inputter.inputInt("Enter tracking id to update: ", 0, Integer.MAX_VALUE);
        Tracking c1 = DaoTracking.checkTracking(tracking_id);
                while(c1==null){
            tracking_id = Inputter.inputInt("This tracking isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoTracking.checkTracking(tracking_id);
            
        }
               if( c1.getStatus() ==6 || c1.getStatus() ==7 )
                   return;
                System.out.printf("%-15s%-20s%-15s%-15s%-15s%-15s%-15s%-15s\n",
               "team_topic "
              ,"milestone_name"
              ,"function_name" 
              ,"assigner"
              ,"assignee "
               ,"tracking_note"
              ,"updates " 
              ,"status ");
                
         DaoTracking.trackingDisplayByPageTrainer(1, "a.tracking_id",String.valueOf(tracking_id),id);
        int team_Id = 0;
        ;
        int op = Inputter.inputInt("Do u want to update team_topic(1-Y,2-N): ", 1, 2);
        if(op == 1) {System.out.println("Class List"+DaoTracking.teamOfflListTrainer(id));
            team_Id = Inputter.inputInt("Enter Team ID:",0,Integer.MAX_VALUE);}
        if(op == 2) team_Id = c1.getTeamid();
        
        int milestone_Id = 0;
        
        op = Inputter.inputInt("Do u want to update Milestone(1-Y,2-N): ", 1, 2);
        if(op == 1){ System.out.println("milestone List"+DaoTracking.MilestoneOfflListTrainer(id, team_Id));
            milestone_Id = Inputter.inputInt("Enter milestone ID:",0,Integer.MAX_VALUE);}
        if(op == 2) milestone_Id = c1.getMileStoneid();
        
        int function_Id = 0;
        
         op = Inputter.inputInt("Do u want to update Function(1-Y,2-N): ", 1, 2);
        if(op == 1){ System.out.println("Function List"+DaoTracking.funcionOfflListTrainer(id, team_Id));
        function_Id = Inputter.inputInt("Enter Function ID:",0,Integer.MAX_VALUE);
        }if(op == 2) function_Id = c1.getFunction_id();
        
        int assigner_Id = 0;
        op = Inputter.inputInt("Do u want to update Assigner(1-Y,2-N): ", 1, 2);      
        if(op == 1){System.out.println("Assigner List"+DaoUser.RoleList(2));        
            assigner_Id = Inputter.inputInt("Enter Assigner ID:",0,Integer.MAX_VALUE);;}
        if(op == 2) assigner_Id = c1.getAssigner_id();
        
        int assignee_Id = 0;
        op = Inputter.inputInt("Do u want to update Assignee(1-Y,2-N): ", 1, 2);      
        if(op == 1){System.out.println("Assignee List"+DaoUser.RoleList(1)); 
            assignee_Id = Inputter.inputInt("Enter Assignee ID:",0,Integer.MAX_VALUE);}
        if(op == 2) assignee_Id = c1.getAssignee_id();
        
        String tracking_Note = null ;
        op = Inputter.inputInt("Do u want to update Tracking note(1-Y,2-N): ", 1, 2);      
        if(op == 1)tracking_Note = Inputter.inputNonBlankStr("input new tracking note:");
        if(op == 2) tracking_Note = c1.getNote();
        
        String updates = null ;
        op = Inputter.inputInt("Do u want to change updates(1-Y,2-N): ", 1, 2);      
        if(op == 1)updates = Inputter.inputNonBlankStr("input updates:");
        if(op == 2) updates = c1.getUpdates();
     
        
        int status = c1.getStatus();   
        
        Tracking c2 = new Tracking(team_Id, milestone_Id, function_Id, assigner_Id, assignee_Id, tracking_Note, updates,status);
        c2.setTracking_id(tracking_id);
        DaoTracking.updateTracking(c2);
        
    }
      public void searchStudent(int id) throws SQLException, ClassNotFoundException{
        int option;
        while(true){
                                    System.out.println("\nSearch by--------- ");
                                    System.out.println("1. milestone name      3.Function name");
                                    System.out.println("2. Topic code          4.Status");
                                    System.out.println("5. Assigner name       6.Assignee name");
                                    System.out.println("5. Back to ClassUserList");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,5);
                                switch(option){
                                    case 1: searchString("Enter a part of milestone_name: ", "c.milestone_name",id);break;
                                    case 2: searchString("Enter a part of Topic cope: ", "b.topic_code",id);break;
                                    case 3: searchString("Enter a part of Function name: ", "d.function_name",id);break;
                                    case 4: searchInt("Enter Status([Planned-0], [Analysed-1] [Designed-2] [Coded-3], [Integrated-4]"
                                            + "[Submitted-5] [Evaluated-6] [Rejected-7]): ", "a.status", 0,7,id);break;
                                    default: break;
                                }
                                if(option==5)
                                    return;
    }}
      public void searchTrainer(int id) throws SQLException, ClassNotFoundException{
        int option;
        while(true){
                                    System.out.println("\nSearch by--------- ");
                                    System.out.println("1. milestone name      3.Function name");
                                    System.out.println("2. Topic code          4.Status");
                                    System.out.println("5. Back to ClassUserList");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,5);
                                switch(option){
                                    case 1: searchStringTrainer("Enter a part of milestone_name: ", "c.milestone_name",id);break;
                                    case 2: searchStringTrainer("Enter a part of Topic cope: ", "b.topic_code",id);break;
                                    case 3: searchStringTrainer("Enter a part of Function name: ", "d.function_name",id);break;
                                    case 4: searchIntTrainer("Enter Status([Planned-0], [Analysed-1] [Designed-2] [Coded-3], [Integrated-4]"
                                                    + "[Submitted-5] [Evaluated-6] [Rejected-7]): ", "a.status", 0,7,id);break;

                                   
                                    default: break;
                                }
                                if(option==5)
                                    return;
    }}
      public void searchInt(String msg,String field,int min,int max, int id) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoTracking.countTeam(field,String.valueOf(filter));
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
        DaoTracking.trackingDisplayByPageStudent(1, field, String.valueOf(filter),id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTracking.trackingDisplayByPageStudent(noPage, field, String.valueOf(filter),id);
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
                                        else {DaoTracking.trackingDisplayByPageStudent(noPage, field, String.valueOf(filter),id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTracking.trackingDisplayByPageStudent(noPage, field, String.valueOf(filter),id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
      public void searchString(String msg,String field, int id) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoTracking.countTeam(field,filter);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTracking.trackingDisplayByPageStudent(1, field, filter,id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTracking.trackingDisplayByPageStudent(count, field, filter,id);
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
                                        else {DaoTracking.trackingDisplayByPageStudent(noPage, field, filter,id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTracking.trackingDisplayByPageStudent(noPage, field, filter,id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
      public void searchIntTrainer(String msg,String field,int min,int max, int id) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoTracking.countTeam(field,String.valueOf(filter));
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
        DaoTracking.trackingDisplayByPageTrainer(1, field, String.valueOf(filter),id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTracking.trackingDisplayByPageTrainer(noPage, field, String.valueOf(filter),id);
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
                                        else {DaoTracking.trackingDisplayByPageTrainer(noPage, field, String.valueOf(filter),id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTracking.trackingDisplayByPageTrainer(noPage, field, String.valueOf(filter),id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
      public void searchStringTrainer(String msg,String field, int id) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoTracking.countTeam(field,filter);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTracking.trackingDisplayByPageTrainer(1, field, filter,id);
            
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTracking.trackingDisplayByPageTrainer(count, field, filter,id);
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
                                        else {DaoTracking.trackingDisplayByPageTrainer(noPage, field, filter,id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTracking.trackingDisplayByPageTrainer(noPage, field, filter,id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
       
      public void statusTracking(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection(); 
        String ms = "";
        String[] TypeStatus = {"Planned", "Analysed","Designed","Coded","Intergrated","Submitted","Evaluated",
        "Rejected"};
       
        System.out.println("\nStatus menu-----------");
                        
        
                TrackingController.statusPagination(id);
                 String topic= Inputter.inputNonBlankStr("Input team topic you want to change Status : ");
            while(DaoTracking.searchTeamTopic(topic)==false){
                
                topic= Inputter.inputNonBlankStr("Input team topic you want to change Status : ");
                
            }
                int choice = Inputter.inputInt("Input status you want to change to([Planned-0][Analysed-1][Designed-2][Coded-3]\n "+
                 " [Integrated-4][Submitted-5][Evaluated-6][Rejected-7]): ", 0, 7);
                DaoTracking.updateStatus(choice, topic);
                TrackingController.statusPagination(id);
        
        
        
        
    
//            if(option==2){
//            ClassUserController.inStatusPagination();
//            int ID= Inputter.inputInt("Input user ID you want to change Status into active: ",0,Integer.MAX_VALUE);
//            while(DaoClassUser.SearchUserID(ID)==false){
//                
//                ID = Inputter.inputInt("Wrong user ID, reenter: ",0,Integer.MAX_VALUE);
//                
//            }
//            DaoClassUser.UpdateStatus(1, ID);
//                     
//
//      
//         
//    }
      }
      public void statusTrackingStudent(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection(); 
        String ms = "";
        String[] TypeStatus = {"Planned", "Analysed","Designed","Coded","Intergrated","Submitted","Evaluated",
        "Rejected"};
       
        System.out.println("\nStatus menu-----------");
                        
                       
                        
                        
                        

            
                TrackingController.statusPaginationStudent(id);
                 String topic= Inputter.inputNonBlankStr("Input team topic you want to change Status : ");
            while(DaoTracking.searchTeamTopic(topic)==false){
                
                topic= Inputter.inputNonBlankStr("Input team topic you want to change Status : ");
                
            }
                int choice = Inputter.inputInt("Input status you want to change to([Planned-0][Analysed-1][Designed-2][Coded-3]\n "+
                 " [Integrated-4][Submitted-5]): ", 0, 5);
                DaoTracking.updateStatus(choice, topic);
                TrackingController.statusPaginationStudent(id);
        
        
        
        
    
//            if(option==2){
//            ClassUserController.inStatusPagination();
//            int ID= Inputter.inputInt("Input user ID you want to change Status into active: ",0,Integer.MAX_VALUE);
//            while(DaoClassUser.SearchUserID(ID)==false){
//                
//                ID = Inputter.inputInt("Wrong user ID, reenter: ",0,Integer.MAX_VALUE);
//                
//            }
//            DaoClassUser.UpdateStatus(1, ID);
//                     
//
//      
//         
//    }
      }
            public static void statusPaginationStudent(int id) throws SQLException, ClassNotFoundException{
        int count = DaoTracking.countStatusStudent("",id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTracking.StatusDisplayByPageStudent(1,id);
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0 ) return;
            else DaoTracking.StatusDisplayByPageStudent(noPage,id);
        
    }
            public static void statusPagination(int id) throws SQLException, ClassNotFoundException{
        int count = DaoTracking.countStatusTrainer("",id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTracking.StatusDisplayByPageTrainer(1,"",id);
            if(countPage <=1) return;
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0 ) return;
            else DaoTracking.StatusDisplayByPageTrainer(noPage,"",id);
        
    }
    
}
