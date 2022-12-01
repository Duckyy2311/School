/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_inputs;

import java.sql.SQLException;

import controllers.MilestoneController;
import controllers.TrackingController;
import controllers.*;
import dao.DaoTeamEvaluation;
import dao.*;
import model.TeamEvaluation;
import model.*;

/**
 *
 * @author Admin
 */
public class TrainnerMenu {

    public static void trainerView(int id) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nDashBoard----Trainer----- ");
            System.out.println("1. ClassList");
            System.out.println("2. Milestone List");
            System.out.println("3. Class User List");
            System.out.println("4. Feature List");
            System.out.println("5. Team List");
            System.out.println("6. Tracking List");
            System.out.println("7. Function List");
            System.out.println("8. Issue List");
            System.out.println("9. Evaluation List");
            System.out.println("10. Back to home");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 10);
            switch (option) {
                case 1:
                    listClass(id);
                    break;
                case 2:
                    listMilestone(id);
                    break;
                case 3:
                    TrainnerMenu.listClassUser();
                case 6:
                    listTracking(id);
                    break;
                case 5:
                    TrainnerMenu b = new TrainnerMenu();
                    b.listTeam();
                    break;
                case 4:
                    listFeature();
                    break;
                case 7:
                    listFunction(id);
                    break;
                    case 8:
                        listIssue(id);
                        break;
                    case 9:
                        listEvaluation(id);
                        break;
                default:
                    break;

            }
            if (option == 10) {
                return;

            }
        }

    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        listTeamEvaluation(3);
    }
    public static void listClass(int id) throws SQLException, ClassNotFoundException {
        int option;
        ClassController classControl = new ClassController();
        while (true) {
            System.out.println("\nClassList--------- ");
            System.out.println("1. Show Class List");
            System.out.println("2. Search");
            System.out.println("3. List by Filter");
            System.out.println("4. Back to Dashboard");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 4);
            switch (option) {
                case 1:
                    classControl.showPagination(id,"a.trainer_id");
                    break;
                case 2:
                    classControl.search(id,"a.trainer_id");
                    break;
                case 3:
                    classControl.listByFilterForTrainer(id, "a.trainer_id");
                    break;
                default:
                    break;
            }
            if (option == 4) {
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

    public static void listMilestone(int id) throws SQLException, ClassNotFoundException {
        int option;
        MilestoneController milestoneControl = new MilestoneController();
        while (true) {
            System.out.println("\nFeatureList--------- ");
            System.out.println("1. Show Milestone List");
            System.out.println("2. Add Milestone");
            System.out.println("3. Search Milestone");
            System.out.println("4. Update Milestone");
            System.out.println("5. Close Milestone");
            System.out.println("6. Cancel Milestone");
            System.out.println("7.Back to Trainer Dashboard");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 7);
            switch (option) {
                case 1:
                    milestoneControl.ShowPagination(id);
                    break;
                case 2:
                    milestoneControl.AddMilestone(id);
                    break;
                case 3:
                    milestoneControl.listByFilter(id);
                    break;
                case 4:
                    milestoneControl.updateMilestone();
                    break;
                case 5:
                    milestoneControl.StatusClass(2);
                    break;
                case 6:
                    milestoneControl.StatusClass(3);
                    break;
                default:
                    break;
            }
            if (option == 5) {
                return;
            }
        }
    }

    public static void listTeam() throws SQLException, ClassNotFoundException {
        TeamController teamControl = new TeamController();
        TeamController.showPagination();

        int option;
        while (true) {

            System.out.println("\nTeamList Menu--------- ");
            System.out.println("1. Update Team");
            System.out.println("2. Add new Team");
            System.out.println("3. Active/deactivate Team");
            System.out.println("4. Search Team by Class:");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            TeamController s = new TeamController();
            option = Inputter.inputInt("", 0, 4);
            switch (option) {
                case 1:
                    s.updateTeam();
                    break;
                case 2:
                    s.addTeam();
                    break;
                case 3:
                    s.inactiveTeam();
                    break;
                case 4:
                    s.searchTeamList();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Again!");
                    break;

            }
        }
    }

    public static void listClassUser() throws SQLException, ClassNotFoundException {
        ClassUserController classUserControl = new ClassUserController();
        ClassUserController.showPagination();

        int option;
        while (true) {
            System.out.println("\nClassUserList Menu---------------");
            System.out.println("1. Update Class User");
            System.out.println("2. Add new Class User");
            System.out.println("3. Active/deactivate Class User");
            System.out.println("4. Search ClassUser by ClassID-TeamID-UserID:");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            ClassUserController s = new ClassUserController();
            option = Inputter.inputInt("", 0, 4);
            switch (option) {
                case 1:
                    s.classUserDetail();
                    break;
                case 2:
                    s.addClassUser();
                    break;
                case 3:
                    s.inactiveClassUser();
                    break;
                case 4:
                    s.search();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Again!");
                    break;

            }
        }

    }

    public static void listTracking(int id) throws SQLException, ClassNotFoundException {
        int option;
        TrackingController trackingControl = new TrackingController();
        while (true) {
            System.out.println("\nTrackingList--------- ");
            System.out.println("1. Show Tracking List");
            System.out.println("2. Tracking Filter");
            System.out.println("3. Back");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1:

                    trackingControl.showPagination(id);
                    break;
                case 2:
                    trackingControl.searchTrainer(id);
                    break;
                
                    default:
                    break;
            }
                    if (option == 3) {
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
                    functionController.showByPaginationForTrainer(id);
                    break;
                case 2:
                    functionController.searchForTrainer(id);
                    break;
                case 3:
                    functionController.addFunctionStudent();
                    break;
                case 4:
                    functionController.updateFunction();
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

    public static void listIssue(int id) throws SQLException, ClassNotFoundException {
        int option;
        IssueController issueController = new IssueController();
        issueController.showByPaginationForTrainer(id);
        while (true) {
            System.out.println("\nFunctionMenu--------- ");
            System.out.println("1. Show Issue List");
            System.out.println("2. Search Issue");
            System.out.println("5. Back");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 0, 5);
            switch (option) {
                case 1:
                    issueController.showByPaginationForTrainer(id);
                    break;
                case 2:
                    issueController.searchForTrainner(id);
                    break;
                case 3:

                    break;
                case 4:

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
    
    public static void listTeamEvaluation(int id) throws SQLException, ClassNotFoundException {
        int option;
        int teamEval = 0;
        TeamEvaluationController teamControl = new TeamEvaluationController();
        TeamEvaluation w = DaoTeamEvaluation.checkTeamEvaluation("a.team_eval_id", "", id, "1");
        int i=0;
        if(w != null)
            System.out.println("You are currently in [team-"+w.getTeam_name()+"] [criteria-"+w.getCriteria_name()+"]" );
        if(w == null){
            System.out.println("Your Evaluation is empty:");return;}
        i=Inputter.inputInt("Edit another Evaluation? [1-yes,2-no]:", 1, 2);
        if(i==1){
        while(i==1){
            TeamEvaluationController.showPagination(id,"");
            teamEval = Inputter.inputInt("Enter Evaluation ID you want to edit", 0, Integer.MAX_VALUE);
        i=Inputter.inputInt("Edit another tracking? [1-yes,2-no]:", 1, 2);
        
        }
         w = DaoTeamEvaluation.checkTeamEvaluation("a.team_eval_id", String.valueOf(teamEval), id,"");
        }
        while (true) {
            System.out.println("\nTeam Evaluation Menu--------- ");
            System.out.println("1. Show List");
            System.out.println("2. Filter");
            System.out.println("3. Update Team Evaluation");
            System.out.println("4. Back");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 4);
            switch (option) {
                case 1:
                    teamControl.showPagination(id,String.valueOf(w.getTeam_id()));  
                    break;
                case 2:
                    teamControl.searchFilter(id,String.valueOf(w.getTeam_id()));
                    break;
                case 3:
                    DaoTeamEvaluation.teamEvaluationDisplayByPage(1, "a.team_eval_id","",id,String.valueOf(w.getTeam_id()));
                    int team_evaluation = Inputter.inputInt("Enter Team Evaluation id to update: ", 0, Integer.MAX_VALUE);
                    teamControl.teamEvaluationUpdate(id,String.valueOf(w.getTeam_id()),team_evaluation);
                    break;


                default:
                    break;
            }
            if (option == 4) {
                return;
            }
        }
    }
    public static void listEvaluation(int id) throws SQLException, ClassNotFoundException {
        int option;
        int teamEval = 0;
        IterationEvalutionController iterationEvalutionController = new IterationEvalutionController();
        IterationEvalution iterationEvalution = DaoIterEvalution.getIterEvalofTrainer(id);
        teamEval = DaoIterEvalution.getTeamlofTrainer(id);

        System.out.println("You are currently in [class-"+iterationEvalution.getClass_code()+"] [team-"+iterationEvalution.getTeam_name()+"] " );
        iterationEvalutionController.fillInt("c.team_id", teamEval, id);
        while(true){
            option = Inputter.inputInt("Chosse another team-class(1-Y,2-N): ", 1, 2);
            if(option==1){
                
            }
            else break;
        }
        
        while (true) {
            System.out.println("\nTeam Evaluation Menu--------- ");
            System.out.println("1. Filter");
            System.out.println("2. Add Evaluation");
            System.out.println("3. Update Evaluation");
            System.out.println("4. List Team Eval");
            System.out.println("5. List MembverEval");
            System.out.println("6. Back");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 4);
            switch (option) {
                case 1:
                    iterationEvalutionController.listByFilter(id);
                    break;
                case 2:
                    iterationEvalutionController.addEvalution(id);
                    break;
                case 3:
                    iterationEvalutionController.updateIterEval(id);
                    break;
                case 4:
                    listTeamEvaluation(id);
                    break;
                case 5:
                    //list memberEval
                    break;
                default:
                    break;
            }
            if (option == 6) {
                return;
            }
        }
    }
}
