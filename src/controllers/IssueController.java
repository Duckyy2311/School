/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.*;
import java.sql.SQLException;
import model.Team;
import view_inputs.Inputter;

/**
 *
 * @author Admin
 */
public class IssueController {
    public  void showByPaginationForTrainer(int id) throws SQLException, ClassNotFoundException{
        System.out.println("=====================Issue List=====================");
        int count = DaoIssue.countIssueForTrainer("a.issue_id", "", id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoIssue.displayByPageForTrain(1, "a.issue_id", "", id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoIssue.displayByPageForTrain(noPage, "a.issue_id", "", id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Issue List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoIssue.displayByPageForTrain(noPage, "a.function_id", "", id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoIssue.displayByPageForTrain(noPage, "a.issue_id", "", id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
    public void searchForTrainner(int id) throws SQLException, ClassNotFoundException{
        int option;
        System.out.println("=====================Issue Search=====================");
        while(true){
                                    System.out.println("\nSearch by--------- ");
                                    System.out.println("1. Issue Name       3.Topic Name");
                                    System.out.println("2. Agnee Name       4.Status");
                                    System.out.println("6. Back to Issue List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,6);
                                switch(option){
                                    case 1: searchStringForTrainner("Enter a part of issue Title: ", "a.issue_title",id);break;
                                    case 2: searchStringForTrainner("Enter a part of Agnee Name: ", "d.full_name",id);break;
                                    case 3: searchStringForTrainner("Enter a part of Topic Name: ", "b.topic_name",id);break;
                                    case 4: searchIntForTrainner("Enter Status(1-Open,2-Closed,3-Pending): ", "a.status", 1, 4,id);break;
                                    default: break;
                                }
                                if(option==6)
                                    return;
    }}
    public void searchStringForTrainner(String msg,String field,int id) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoIssue.countIssueForTrainer(field, filter, id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoIssue.displayByPageForTrain(1,field, filter, id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoIssue.displayByPageForTrain(noPage,field, filter, id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Issue List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoIssue.displayByPageForTrain(noPage,field, filter, id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoIssue.displayByPageForTrain(noPage,field, filter, id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }    
    public void searchIntForTrainner(String msg,String field,int min,int max,int id) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoIssue.countIssueForTrainer(field, String.valueOf(filter), id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoIssue.displayByPageForTrain(1,field, String.valueOf(filter), id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoIssue.displayByPageForTrain(noPage,field, String.valueOf(filter), id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Issue List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoIssue.displayByPageForTrain(noPage,field, String.valueOf(filter), id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoIssue.displayByPageForTrain(noPage,field, String.valueOf(filter), id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
    
        public  void showByPaginationForStu(int id) throws SQLException, ClassNotFoundException{
            Team team = DaoTeam.findTeam(id);
            int teamID = team.getTeamId();
            String teamName = team.getTopicName();
            System.out.println("You are in "+teamName);
            System.out.println("=====================Issue List=====================");            
        int count = DaoIssue.countIssueForStu("a.issue_id", "", teamID);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoIssue.displayByPageForStu(1, "a.issue_id", "", teamID);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoIssue.displayByPageForStu(noPage, "a.issue_id", "", teamID);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Issue List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoIssue.displayByPageForStu(noPage, "a.issue_id", "", teamID);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoIssue.displayByPageForStu(noPage, "a.issue_id", "", teamID);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
    public void searchForStu(int id) throws SQLException, ClassNotFoundException{
            Team team = DaoTeam.findTeam(id);
            int teamID = team.getTeamId();
            String teamName = team.getTopicName();
            System.out.println("You are in "+teamName);
        int option;
        System.out.println("=====================Issue Search=====================");
        while(true){
                                    System.out.println("\nSearch by--------- ");
                                    System.out.println("1. Issue Name       3.Team Name");
                                    System.out.println("2. Agnee Name       4.Status");
                                    System.out.println("5. Back to Issue List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,6);
                                switch(option){
                                    case 1: searchStringForStu("Enter a part of issue Title: ", "a.issue_title",teamID);break;
                                    case 2: searchStringForStu("Enter a part of Agnee Name: ", "d.full_name",teamID);break;
                                    case 3: searchStringForStu("Enter a part of Topic Name: ", "b.topic_name",teamID);break;
                                    case 4: searchIntForStu("Enter Status(1-Open,2-Closed,3-Pending): ", "a.status", 1, 4,teamID);break;
                                    default: break;
                                }
                                if(option==5)
                                    return;
    }}
    public void searchStringForStu(String msg,String field,int id) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoIssue.countIssueForStu(field, filter, id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoIssue.displayByPageForStu(1,field, filter, id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoIssue.displayByPageForStu(noPage,field, filter, id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Issue List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoIssue.displayByPageForStu(noPage,field, filter, id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoIssue.displayByPageForStu(noPage,field, filter, id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }    
    public void searchIntForStu(String msg,String field,int min,int max,int id) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoIssue.countIssueForStu(field, String.valueOf(filter), id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoIssue.displayByPageForStu(1,field, String.valueOf(filter), id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoIssue.displayByPageForStu(noPage,field, String.valueOf(filter), id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Issue List");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoIssue.displayByPageForStu(noPage,field, String.valueOf(filter), id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoIssue.displayByPageForStu(noPage,field, String.valueOf(filter), id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        IssueController issueController = new IssueController();
        issueController.searchForStu(2);
    }
}
