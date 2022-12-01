/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_inputs;

import controllers.*;
import controllers.FeatureController;
import controllers.TrackingController;
import dao.DaoIterEvalution;
import dao.DaoTeamEvaluation;
import dao.DaoTracking;
import model.Tracking;
import java.sql.SQLException;
import model.IterationEvalution;
import model.TeamEvaluation;
import static view_inputs.TrainnerMenu.listTeamEvaluation;

/**
 *
 * @author Admin
 */
public class StudentMenu {

    public static void studentView(int id) throws SQLException, ClassNotFoundException {

        int option;
        while (true) {
            System.out.println("\nDashBoard----Student----- ");
            System.out.println("1. FeatureList");
            System.out.println("2. TrackingList");

            System.out.println("3. FunctionList");

            System.out.println("4. IssueList");            
            System.out.println("5. Team Evaluation List");
            System.out.println("6. Team Evaluation List");
            System.out.println("7. Back to home");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 7);

            switch (option) {
                case 1:
                    listFeature();
                    break;
                case 2:
                    listTracking(id);
                    break;
                case 3:
                    listFunction(id);
                    break;

                case 4:
                    listIssue(id);
                    break;
                case 5: 
                    listTeamEvaluation(id);
                    break;
                case 6: 
                    listEvaluation(id);
                    break;    
                default:
                    break;
            }

            if (option == 7) {


                return;
            }
        }
    }

    public static void listFeature() throws SQLException, ClassNotFoundException {
        int option;
        FeatureController featureControl = new FeatureController();
        while (true) {
            System.out.println("\nFeatureList--------- ");
            System.out.println("1. Show Feature List");
            System.out.println("2. Add Feature");
            System.out.println("3. Search Feature");
            System.out.println("4. Update Feature");
            System.out.println("5. Back");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 0, 5);
            switch (option) {
                case 1:
                    featureControl.ShowPagination();
                    break;
                case 2:
                    featureControl.AddFeature();
                    break;
                case 3:
                    featureControl.SearchFeatureList();
                    break;
                case 4:
                    featureControl.UpdateFeature();
                    break;
                case 5:

                    break;
                default:
                    break;
            }
            if (option == 5) {
                return;
            }
        }
    }

    public static void listFunction(int id) throws SQLException, ClassNotFoundException {
        int option;

        FunctionController functionController = new FunctionController();
        while (true) {
            System.out.println("\nFunctionMenu--------- ");
            System.out.println("1. Show Function List");
            System.out.println("2. Search Function");
            System.out.println("3. Add Function");
            System.out.println("4. Update Function");
            System.out.println("5. Back");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 0, 5);
            switch (option) {
                case 1:

                    functionController.showByPaginationForStu(id);
                    break;
                case 2:
                    functionController.searchForStu(id);
                    break;
                case 3:
                    functionController.addFunctionStudent();
                    break;
                case 4:
                    functionController.updateFunction();
                    break;
                default:
                    break;
            }
            if (option == 5) {
                return;
            }
        }
    }
    

    public static void listTracking(int id) throws SQLException, ClassNotFoundException {
        int option;
        int tracking = 0;
        TrackingController trackingControl = new TrackingController();
       Tracking w = DaoTracking.checkDisplayByPageStudent("a.tracking_id", "", id,"1");
        int i=0;
        if(w != null)
            System.out.println("You are currently in [team-"+w.getTopic_code()+"] [milestone-"+w.getMilestone_name()+"] [function-"+w.getFunction_name()+"]");
        if(w == null){
            System.out.println("Your tracking is empty:");return;}
        i=Inputter.inputInt("Edit another tracking? [1-yes,2-no]:", 1, 2);
        
        while(i==1){
            trackingControl.showPaginationStudent(id);
          tracking = Inputter.inputInt("Enter tracking ID you want to edit", 0, Integer.MAX_VALUE);
        i=Inputter.inputInt("Edit another tracking? [1-yes,2-no]:", 1, 2);
        w = DaoTracking.checkDisplayByPageStudent("a.tracking_id", String.valueOf(tracking), id,"");
        }
         

        while (true) {
            System.out.println("\nTrackingList--------- ");
            System.out.println("1. Show List");
            System.out.println("2. Add Tracking");
            System.out.println("3. Tracking Filter");
            System.out.println("4. Update Tracking");
            System.out.println("5. Update Status");
            System.out.println("6. Delete Status");
            System.out.println("7. Back");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 7);
            switch (option) {
                case 1:
                    TrackingController.showPaginationStudent(id);  
                    break;
                case 2:
                    trackingControl.addTrackingStudent(id,w.getTeamid());
                    break;
                case 3:
                    trackingControl.searchTrainer(id);
                    break;
                case 4:
                    trackingControl.trackingUpdate(id, w.getTeamid());
                    break;
                case 5:trackingControl.statusTrackingStudent(id);
                    break;
                case 6:trackingControl.deleteTracking(id);
                    break;
                default:
                    break;
            }
            if (option == 7) {
                return;
            }
        }
    }
    public static void listIssue(int id) throws SQLException, ClassNotFoundException{
        int option;
        IssueController issueController = new IssueController();
        FunctionController functionController = new FunctionController();
        while (true) {
            System.out.println("\nIsueMenu--------- ");
            System.out.println("1. Show Issue List");
            System.out.println("2. Search Issue");
            System.out.println("3. Add Issue");
            System.out.println("4. Update Issue");            
            System.out.println("5. Back");
            System.out.print("Your option: ");


            option = Inputter.inputInt("", 0, 5);
            switch (option) {
                case 1:

                    issueController.showByPaginationForStu(id);
                    break;
                case 2:
                    issueController.searchForStu(id);
                    break;
                case 3:

                    break;
                case 4:
                    break;
                default:
                    break;
            }
            if (option == 5) {
                return;
            }
        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        studentView(2);
    }
public static void listTeamEvaluation(int id) throws SQLException, ClassNotFoundException {
        int option;
        int teamEval = 0;
        TeamEvaluationController teamControl = new TeamEvaluationController();
        TeamEvaluation w = DaoTeamEvaluation.checkTeamEvaluationStudent("a.team_eval_id", "", id, "1");
        int i=0;
        if(w != null)
            System.out.println("You are currently in [team-"+w.getTeam_name()+"] [criteria-"+w.getCriteria_name()+"]" );
        if(w == null){
            System.out.println("Your Evaluation is empty:");return;}
        i=Inputter.inputInt("Edit another Evaluation? [1-yes,2-no]:", 1, 2);
        if(i==1){
        while(i==1){
            TeamEvaluationController.showPaginationStudent(id,"");
            teamEval = Inputter.inputInt("Enter Evaluation ID you want to edit", 0, Integer.MAX_VALUE);
        i=Inputter.inputInt("Edit another tracking? [1-yes,2-no]:", 1, 2);
        
        }
         w = DaoTeamEvaluation.checkTeamEvaluationStudent("a.team_eval_id", String.valueOf(teamEval), id,"");
        }
        while (true) {
            System.out.println("\nTeam Evaluation Menu--------- ");
            System.out.println("1. Show List");
            System.out.println("2. Filter");
            System.out.println("3. Back");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 4);
            switch (option) {
                case 1:
                    teamControl.showPaginationStudent(id,String.valueOf(w.getTeam_id()));  
                    break;
                case 2:
                    teamControl.searchFilterStudent(id,String.valueOf(w.getTeam_id()));
                    break;

                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }
    public static void listEvaluation(int id) throws SQLException, ClassNotFoundException {
        int option;
        int teamEval = 0;
        IterationEvalutionController iterationEvalutionController = new IterationEvalutionController();
        IterationEvalution iterationEvalution = DaoIterEvalution.getIterEvalofStu(id);
        teamEval = DaoIterEvalution.getTeamlofTrainer(id);
        System.out.println(teamEval);

        System.out.println("You are currently in [class-"+iterationEvalution.getClass_code()+"] [team-"+iterationEvalution.getTeam_name()+"] " );
        iterationEvalutionController.fillIntForStu("c.team_id", iterationEvalution.getEval_id(), id);
        while(true){
            option = Inputter.inputInt("Chosse another team-class(1-Y,2-N): ", 1, 2);
            if(option==1){
                
            }
            else break;
        }
        
        while (true) {
            System.out.println("\nTeam Evaluation Menu--------- ");
            System.out.println("1. Filter");
            System.out.println("2. List Team Eval");
            System.out.println("3. List MembverEval");
            System.out.println("4. Back");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 4);
            switch (option) {
                case 1:
                    iterationEvalutionController.listByFilter(id);
                    break;
                case 2:
                    listTeamEvaluation(id);
                    break;
                case 3:
                    //list memberEval
                    break;
                default:
                    break;
            }
            if (option == 4) {
                return;
            }
        }
    }
}
