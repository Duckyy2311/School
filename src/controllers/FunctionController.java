/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.ConnectionUtils;
import dao.DaoFunction;
import dao.DaoTeam;
import dao.DaoUser;
import java.sql.Connection;
import java.sql.SQLException;
import model.Function;
import view_inputs.Inputter;

/**
 *
 * @author Admin
 */
public class FunctionController {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        FunctionController functionController = new FunctionController();
        functionController.showByPaginationForStu(2);
    }
    public  void showByPaginationForTrainer(int id) throws SQLException, ClassNotFoundException{
        System.out.println("======================Function List=======================");
        int count = DaoFunction.countFunctionForTrainer("a.function_id", "", id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoFunction.displayByPageForTrain(1, "a.function_id", "", id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoFunction.displayByPageForTrain(noPage, "a.function_id", "", id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Function Mana");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoFunction.displayByPageForTrain(noPage, "a.function_id", "", id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoFunction.displayByPageForTrain(noPage, "a.function_id", "", id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
    public void searchForTrainer(int id) throws SQLException, ClassNotFoundException{
        System.out.println("=====================Function Search====================");
        int option;
        while(true){
                                    System.out.println("\nSearch by--------- ");
                                    System.out.println("1. Func Name       3.Team Name");
                                    System.out.println("2. Feature Name    4.Status");
                                    System.out.println("6. Back to Function Mana");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,6);
                                switch(option){
                                    case 1: searchStringForTrainer("Enter a part of Function Name: ", "a.function_name",id);break;
                                    case 2: searchStringForTrainer("Enter a part of Feature Name: ", "c.feature_name",id);break;
                                    case 3: searchStringForTrainer("Enter a part of Team Name: ", "b.topic_name",id);break;
                                    case 4: searchIntForTrainer("Enter Status(1-Pending,2-Planned,3-Evaluated,4-Rejected): ", "a.status", 1, 4,id);break;
                                    default: break;
                                }
                                if(option==6)
                                    return;
    }}
    public void searchStringForTrainer(String msg,String field,int id) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoFunction.countFunctionForTrainer(field, filter, id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoFunction.displayByPageForTrain(1,field, filter, id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoFunction.displayByPageForTrain(noPage,field, filter, id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Function List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoFunction.displayByPageForTrain(noPage,field, filter, id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoFunction.displayByPageForTrain(noPage,field, filter, id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }    
    public void searchIntForTrainer(String msg,String field,int min,int max,int id) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoFunction.countFunctionForTrainer(field, String.valueOf(filter), id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoFunction.displayByPageForTrain(1,field, String.valueOf(filter), id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoFunction.displayByPageForTrain(noPage,field, String.valueOf(filter), id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Function List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoFunction.displayByPageForTrain(noPage,field, String.valueOf(filter), id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoFunction.displayByPageForTrain(noPage,field, String.valueOf(filter), id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
        public  void showByPaginationForStu(int id) throws SQLException, ClassNotFoundException{
            System.out.println("======================Function List=======================");
        int count = DaoFunction.countFunctionForStu("a.function_id", "", id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoFunction.displayByPageForStu(1, "a.function_id", "", id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoFunction.displayByPageForStu(noPage, "a.function_id", "", id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Function List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoFunction.displayByPageForStu(noPage, "a.function_id", "", id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoFunction.displayByPageForStu(noPage, "a.function_id", "", id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
    public void searchForStu(int id) throws SQLException, ClassNotFoundException{
        System.out.println("=====================Function Search====================");
        int option;
        while(true){
                                    System.out.println("\nSearch by--------- ");
                                    System.out.println("1. Func Name       3.Team Name");
                                    System.out.println("2. Feature Name    4.Pripority    5.Status");
                                    System.out.println("6. Back to Function List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,6);
                                switch(option){
                                    case 1: searchStringForStu("Enter a part of Function Name: ", "a.function_name",id);break;
                                    case 2: searchStringForStu("Enter a part of Feature Name: ", "c.feature_name",id);break;
                                    case 3: searchStringForStu("Enter a part of Team Name: ", "b.topic_name",id);break;
                                    case 4: searchIntForStu("Enter a Pripority: ", "a.priority",1,5,id);break;
                                    case 5: searchIntForStu("Enter Status(1-Pending,2-Planned,3-Evaluated,4-Rejected): ", "a.status", 1, 4,id);break;
                                    default: break;
                                }
                                if(option==6)
                                    return;
    }}
    public void searchStringForStu(String msg,String field,int id) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoFunction.countFunctionForStu(field, filter, id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoFunction.displayByPageForStu(1,field, filter, id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoFunction.displayByPageForStu(noPage,field, filter, id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Function List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoFunction.displayByPageForStu(noPage,field, filter, id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoFunction.displayByPageForStu(noPage,field, filter, id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }    
    public void searchIntForStu(String msg,String field,int min,int max,int id) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoFunction.countFunctionForStu(field, String.valueOf(filter), id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoFunction.displayByPageForStu(1,field, String.valueOf(filter), id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoFunction.displayByPageForStu(noPage,field, String.valueOf(filter), id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Function List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoFunction.displayByPageForStu(noPage,field, String.valueOf(filter), id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoFunction.displayByPageForStu(noPage,field, String.valueOf(filter), id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
  
   public void addFunctionStudent() throws SQLException, ClassNotFoundException {
        System.out.println("\nAdd Function--------- ");
        System.out.println("Team List" + DaoFunction.teamOfflList());
        int newTeamid = Inputter.inputInt("input  Team you want to add to function: ", 0, Integer.MAX_VALUE);
        while (DaoTeam.SearchTeamID(newTeamid) == false) {
            newTeamid = Inputter.inputInt("this Team is not exist, please try again: ", 0, Integer.MAX_VALUE);
        }

        String function_name = Inputter.inputNonBlankStr("input Function Name: ");

        System.out.println("Feature List" + DaoFunction.featureOfflList());
        int feature_id = Inputter.inputInt("Input feature you want to add to this tracking: ", 0, Integer.MAX_VALUE);
        while (DaoFunction.SearchFeatureDonl(feature_id)) {
            feature_id = Inputter.inputInt("this feature is not exist, please try again: ", 0, Integer.MAX_VALUE);
        }

        String access_roles = Inputter.inputNonBlankStr("input Access Roles: ");
        String description = Inputter.inputNonBlankStr("input Description: ");

        String complexity_id = Inputter.inputNonBlankStr("input ComplexityID: ");

        System.out.println("Owner List" + DaoUser.RoleList(2));
        int owner_id = Inputter.inputInt("Input owner_id: ", 0, Integer.MAX_VALUE);
        while (DaoUser.SearchUID(owner_id) == false) {
            owner_id = Inputter.inputInt("this User is not exist, please try again: ", 0, Integer.MAX_VALUE);
        }

        int priority = Inputter.inputInt("Input priority: ", 0, Integer.MAX_VALUE);

        int newStatus = Inputter.inputInt("Status([Pending-0][Planned-1][Evaluated-2][Rejected-3]",
                0, 3);

//        Function b = new Function(0, newTeamid, function_name, feature_id, access_roles, description, complexity_id, owner_id,priority, newStatus);
        Function b = new Function(0, newTeamid, function_name, feature_id, access_roles, description, complexity_id, String.valueOf(owner_id), priority, newStatus);
        DaoFunction.insertFunction(b);
    }

//    public void addFunction() throws SQLException, ClassNotFoundException {
//        int Id = Inputter.inputInt("Enter Function id to update: ", 0, Integer.MAX_VALUE);
//        Function c1 = DaoFunction.ProFunctionId(Id);
//                while(c1==null){
//            Id = Inputter.inputInt("This function isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
//            c1 = DaoFunction.ProFunctionId(Id);
//        }
//                System.out.printf("%-10s%-15s%-20s%-10s%-10s%-20s%-10s%-10s\n",
//                "ID",
//                "TeamID",
//                "FunctionName",
//                "FeatureID",
//                "AccessRoles",
//                "Description",
//                "ComplexityID",
//                "OwnerID",
//                "Priority",
//                "Status");
//        System.out.println(c1);
//        
//        String functionname = null;
//        int op = Inputter.inputInt("Do u want to update Function Name(1-Y,2-N): ", 1, 2);
//        if(op == 1) functionname = Inputter.inputNonBlankStr("Enter function name: ");
//        if(op == 2) functionname = c1.getFunction_name();
//        int feature_id = 0;
//        op = Inputter.inputInt("Do u want to update Feature(1-Y,2-N): ", 1, 2);
//        if(op == 1){System.out.println("List Feature: "+DaoUser.RoleList(2));
//        TrainerId = Inputter.inputInt("Enter trainer id: ", 0, Integer.MAX_VALUE);
//        while(DaoUser.SearchRolebyId(TrainerId)!=2){
//            TrainerId = Inputter.inputInt("This ID isn't Trainer,Re-Enter: ", 0, Integer.MAX_VALUE);
//        }}
//        if(op == 2) TrainerId = c1.getTrainer_id();
//        
//        int SubId = 0;
//        op = Inputter.inputInt("Do u want to update Subject Code(1-Y,2-N): ", 1, 2);
//        if(op == 1){System.out.println("List Class: "+DaoSubject.SubList());
//        SubId = Inputter.inputInt("Enter Subject Code: ", 0, Integer.MAX_VALUE);
//        while(!DaoSubject.CheckSubjectID(SubId)){
//            SubId = Inputter.inputInt("This Subject doesn't exist,Re-Enter Subject Code: ", 0, Integer.MAX_VALUE);
//        }}
//        if(op == 2) SubId=c1.getSub_id();
//        
//        int year = 0;
//        op = Inputter.inputInt("Do u want to update Year(1-Y,2-N): ", 1, 2);
//        if(op == 1) year = Inputter.inputInt("Enter Class Year: ", 2000, 3000);
//        if(op == 2) year = c1.getClass_year();
//        
//        String Term = null;
//        op = Inputter.inputInt("Do u want to update Term(1-Y,2-N): ", 1, 2);
//        if(op == 1) Term = Inputter.inputBlankStr("Enter Term of class: ");
//        if(op == 2) Term = c1.getTerm();
//        
//        int bl5 = 0;
//        op = Inputter.inputInt("Do u want to update bl5(1-Y,2-N): ", 1, 2);
//        if(op == 1) bl5 = Inputter.inputInt("Enter Block 5 check(2-No,1-Yes): ", 1, 2);
//        if(op == 2) bl5 = c1.getBl5();
//        
//        int status = c1.getStatus();        
//        Class c2 = new Class(Id, ClassCode, TrainerId, SubId, year, Term, bl5, status);
//
//        op = Inputter.inputInt("Do u want to update Class(1-Y,2-N): ", 1, 2);
//        if(op == 1) DaoClass.UpdateClass(c2);
//        if(op == 2) return;
//    }
//        System.out.println("\nAdd Function--------- ");
//        System.out.println("Team List" + DaoFunction.teamOfflList());
//        int newTeamid = Inputter.inputInt("input  Team you want to add to function: ", 0, Integer.MAX_VALUE);
//        while (DaoTeam.SearchTeamID(newTeamid) == false) {
//            newTeamid = Inputter.inputInt("this Team is not exist, please try again: ", 0, Integer.MAX_VALUE);
//        }
//
//        String function_name = Inputter.inputNonBlankStr("input Function Name: ");
//
//        System.out.println("Feature List" + DaoFunction.featureOfflList());
//        int feature_id = Inputter.inputInt("Input feature you want to add to this tracking: ", 0, Integer.MAX_VALUE);
//        while (DaoFunction.SearchFeatureDonl(feature_id)) {
//            feature_id = Inputter.inputInt("this feature is not exist, please try again: ", 0, Integer.MAX_VALUE);
//        }
//
//        String access_roles = Inputter.inputNonBlankStr("input Access Roles: ");
//        String description = Inputter.inputNonBlankStr("input Description: ");
//
//        String complexity_id = Inputter.inputNonBlankStr("input ComplexityID: ");
//
//        System.out.println("Owner List" + DaoUser.RoleList(2));
//        int owner_id = Inputter.inputInt("Input owner_id: ", 0, Integer.MAX_VALUE);
//        while (DaoUser.SearchUID(owner_id) == false) {
//            owner_id = Inputter.inputInt("this User is not exist, please try again: ", 0, Integer.MAX_VALUE);
//        }
//
//        int priority = Inputter.inputInt("Input priority: ", 0, Integer.MAX_VALUE);
//
//        int newStatus = Inputter.inputInt("Status([Pending-0][Planned-1][Evaluated-2][Rejected-3]",
//                0, 3);
//
////        Function b = new Function(0, newTeamid, function_name, feature_id, access_roles, description, complexity_id, owner_id,priority, newStatus);
//        Function b = new Function(0, newTeamid, function_name, feature_id, access_roles, description, complexity_id, String.valueOf(owner_id), priority, newStatus);
//        DaoFunction.insertFunction(b);
//
//    }
    public void updateFunction() throws SQLException, ClassNotFoundException {

//        DaoFunction.displayByPageForTrain(0, field, filter, 0);
        int function_id = Inputter.inputInt("Enter function id to update: ", 0, Integer.MAX_VALUE);
        Function c1 = DaoFunction.ProFunctionId(function_id);
        while (c1 == null) {
            function_id = Inputter.inputInt("This function isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoFunction.ProFunctionId(function_id);
        }
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                "team_id ",
                "function_name",
                "feature_id",
                "access_roles",
                "description ",
                "complexity_id",
                "owner_id",
                "priority ",
                "status ");

        System.out.println(c1);
        int team_Id = 0;
        ;
        int op = Inputter.inputInt("Do u want to update team_topic(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("Class List" + DaoFunction.teamOfflList());
            team_Id = Inputter.inputInt("Enter Team ID:", 0, Integer.MAX_VALUE);
        }
        if (op == 2) {
            team_Id = c1.getTeam_id();
        }

        String function_name = null;
        op = Inputter.inputInt("Do u want to update Function name(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            function_name = Inputter.inputNonBlankStr("input new Function name:");
        };
        if (op == 2) {
            function_name = c1.getFunction_name();
        }

        int feature_id = 0;
        op = Inputter.inputInt("Do u want to update Feature(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("Feature List" + DaoFunction.featureOfflList());
            feature_id = Inputter.inputInt("Enter Feature ID:", 0, Integer.MAX_VALUE);
        }
        if (op == 2) {
            feature_id = c1.getFeature_id();
        }

        String access_roles = null;
        op = Inputter.inputInt("Do u want to update Access Roles(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            access_roles = Inputter.inputNonBlankStr("input new Access Roles:");
        };
        if (op == 2) {
            access_roles = c1.getAccess_role();
        }

        String description = null;
        op = Inputter.inputInt("Do u want to update Description(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            description = Inputter.inputNonBlankStr("input new Description:");
        };
        if (op == 2) {
            description = c1.getDescription();
        }

        String complexity_id = null;
        op = Inputter.inputInt("Do u want to update Complexity ID(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            complexity_id = Inputter.inputNonBlankStr("input new Complexity_id:");
        };
        if (op == 2) {
            complexity_id = c1.getComplexity_id();
        }

        String owner_id = null;
        op = Inputter.inputInt("Do u want to update Assigner(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("Owner List" + DaoUser.RoleList(2));
            owner_id = Inputter.inputNonBlankStr("input new owner_id:");
        }
        if (op == 2) {
            owner_id = c1.getOwner_id();
        }

        int priority = 0;
        op = Inputter.inputInt("Do u want to update priority(1-Y,2-N): ", 1, 2);
        if (op == 1) {

            priority = Inputter.inputInt("Enter Priority:", 0, Integer.MAX_VALUE);
        }
        if (op == 2) {
            priority = c1.getPriority();
        }

        int status = c1.getStatus();

        Function c2 = new Function(0, team_Id, function_name, feature_id, access_roles, description, complexity_id, owner_id, priority, status);

    }

    public void statusFunction() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection();
        String ms = "";
        String[] TypeStatus = {"Pending", "Planned", "Evaluated",
            "Rejected"};
        System.out.println("\nStatus menu-----------");

        FunctionController.statusPagination();
        String topic = Inputter.inputNonBlankStr("Input team topic you want to change Status : ");
        while (DaoFunction.searchTeamTopic(topic) == false) {

            topic = Inputter.inputNonBlankStr("Input team topic you want to change Status : ");

        }
        int choice = Inputter.inputInt("Input status you want to change to([Pending-0][Planned-1][Evaluated-2][Rejected-3] ",
                0, 3);
        DaoFunction.UpdateStatus(choice, topic);
    }

    public static void statusPagination() throws SQLException, ClassNotFoundException {
        int count = DaoFunction.countAcStatus("");
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoFunction.StatusDisplayByPage(1, "");
        if (countPage <= 1) {
            return;
        }
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoFunction.StatusDisplayByPage(noPage, "");
        }

    }
 
    
}

