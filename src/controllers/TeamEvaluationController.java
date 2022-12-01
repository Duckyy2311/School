/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoCriteria;
import dao.DaoMilestone;
import dao.DaoTeam;
import dao.DaoTeamEvaluation;
import dao.DaoTracking;
import dao.DaoUser;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import model.TeamEvaluation;
import model.Tracking;
import view_inputs.Inputter;

/**
 *
 * @author AnhMinh
 */
public class TeamEvaluationController {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TeamEvaluationController.showPagination(1, "");
    }
     public static void showPagination(int id, String team_id) throws SQLException, ClassNotFoundException{
        int count = DaoTeamEvaluation.countTeamEvaluationTrainer("a.team_eval_id","",id,team_id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTeamEvaluation.teamEvaluationDisplayByPage(1, "a.team_eval_id","",id,team_id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, "a.team_eval_id","",id,team_id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Team Evaluation Menu");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, "a.team_eval_id","",id,team_id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, "a.team_eval_id","",id,team_id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    
                                
    }
    }
    
      public static void showPaginationStudent(int id, String team_id) throws SQLException, ClassNotFoundException{
        int count = DaoTeamEvaluation.countStudent("a.team_eval_id","",id,team_id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(1, "a.team_eval_id","",id,team_id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, "a.team_eval_id","",id,team_id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to Team Evaluation Menu");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, "a.team_eval_id","",id,team_id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, "a.team_eval_id","",id,team_id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    
                                
    }
    }
      public void teamEvaluationUpdate(int id, String team_id, int eval_id) throws SQLException, ClassNotFoundException{
        
        
        TeamEvaluation c1 = DaoTeamEvaluation.checkTeam(eval_id);
                while(c1==null){
                    System.out.println("This Team Evaluation isn't exist,re-enter: ");
            return;
            
        }
              
        System.out.println("Team Eval ID: "+String.valueOf(c1.getTeam_eval_id()));
        System.out.println("Evaluation ID: "+ String.valueOf(c1.getEvaluation_id()));
        System.out.println("Criteria: "+c1.getCriteria_name());
        System.out.println("Team: "+c1.getTeam_name());
        System.out.println("Grade: "+String.valueOf(c1.getGrade()));
        System.out.println("Note: "+c1.getNote());

                
         DaoTeamEvaluation.teamEvaluationDisplayByPage(1, "a.evaluation_id", String.valueOf(eval_id), id, team_id);
         double grade =0;
        int op = Inputter.inputInt("Old grade: ["+c1.getGrade()+"], Do u want to change grade(1-Y,2-N): ", 1, 2);
        if(op == 1)  grade = Inputter.inputDouble("Enter new grade:",0,10);
        if(op == 2) grade = c1.getGrade();
        
        String note = null ;
        op = Inputter.inputInt("Old note: ["+c1.getNote()+"] Do u want to change note(1-Y,2-N): ", 1, 2);      
        if(op == 1)note = Inputter.inputNonBlankStr("input new  note:");
        if(op == 2) note = c1.getNote();

        TeamEvaluation c2 = new TeamEvaluation(c1.getEvaluation_id(), c1.getCriteria_id(), c1.getTeam_id(), grade, note);
        c2.setTeam_eval_id(c1.getTeam_eval_id());
        DaoTeamEvaluation.updateTeamEvaluation(c2);
        
    }
       public void searchFilter(int id, String team_id) throws SQLException, ClassNotFoundException{
        int option;
        while(true){
                                    System.out.println("\nFilter by--------- ");
                                    System.out.println("1. Evaluation ID      2.Criteria name");
                                    System.out.println("3. Back to Menu ");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: searchInt("Enter Evaluation ID: ", "a.evaluation_id",1,Integer.MAX_VALUE,id,team_id);break;
                                    case 2: searchString("Enter a part of Criteria name: ", "b.criteria_name",id,team_id);break;
                                    
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }}
       public void searchInt(String msg,String field,int min,int max, int id, String team_id) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoTeamEvaluation.countTeamEvaluationTrainer(field, String.valueOf(filter), id, team_id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
        DaoTeamEvaluation.teamEvaluationDisplayByPage(1, field, String.valueOf(filter),id, team_id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, field, String.valueOf(filter),id, team_id);
        int option;
        while(true){                if(noPage==0) return;
                                    System.out.println("\nShow--------- ");
                                    System.out.println("1. Next page");
                                    System.out.println("2. Pre page");
                                    System.out.println("3. Back to2 Menu");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: {
                                        noPage++;
                                        if(noPage>countPage) {System.out.println("There is no more page!");noPage--; break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, field, String.valueOf(filter),id, team_id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, field, String.valueOf(filter),id, team_id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
      public void searchString(String msg,String field, int id, String team_id) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoTeamEvaluation.countTeamEvaluationTrainer(field, String.valueOf(filter), id, team_id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTeamEvaluation.teamEvaluationDisplayByPage(1, field, filter,id, team_id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, field, String.valueOf(filter),id, team_id);
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
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, field, String.valueOf(filter),id, team_id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPage(noPage, field, String.valueOf(filter),id, team_id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
      public void searchFilterStudent(int id, String team_id) throws SQLException, ClassNotFoundException{
        int option;
        while(true){
                                    System.out.println("\nFilter by--------- ");
                                    System.out.println("1. Evaluation ID      2.Criteria name");
                                    System.out.println("3. Back to Menu ");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,3);
                                switch(option){
                                    case 1: searchIntStudent("Enter Evaluation ID: ", "a.evaluation_id",1,Integer.MAX_VALUE,id,team_id);break;
                                    case 2: searchStringStudent("Enter a part of Criteria name: ", "b.criteria_name",id,team_id);break;
                                    
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }}
       public void searchIntStudent(String msg,String field,int min,int max, int id, String team_id) throws SQLException, ClassNotFoundException{
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoTeamEvaluation.countStudent(field, String.valueOf(filter), id, team_id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
        DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(1, field, String.valueOf(filter),id, team_id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter),id,team_id);
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
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter),id, team_id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter),id, team_id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
      public void searchStringStudent(String msg,String field, int id, String team_id) throws SQLException, ClassNotFoundException{
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoTeamEvaluation.countStudent(field, String.valueOf(filter), id, team_id);
        int countPage = count/5;
        if(count%5!=0) countPage++;
        int noPage;
            DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(1, field, filter,id, team_id);
            noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to "+countPage+"'(0-exit): ", 0, countPage);
            if(noPage==0) return;
            else DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter),id, team_id);
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
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter),id, team_id);break;
                                        }}
                                    case 2: {
                                        noPage--;
                                        if(noPage<=0) {System.out.println("There is no more page!");noPage++;break;}
                                        else {DaoTeamEvaluation.teamEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter),id, team_id);break;
                                        }}
                                    default: break;
                                }
                                if(option==3)
                                    return;
    }
    }
}
