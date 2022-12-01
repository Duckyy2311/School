/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import dao.*;
import java.sql.SQLException;
import view_inputs.Inputter;
import model.*;
/**
 *
 * @author Admin
 */
public class IterationEvalutionController {

    public void addEvalution(int id) throws SQLException, ClassNotFoundException{
        System.out.println("List class: "+DaoClassUser.ClassOnlList());
        int new_class = Inputter.inputInt("Chosse a class: ", 0, Integer.MAX_VALUE);
        while (!DaoClassUser.searchByID("class_id", new_class)) {
            new_class = Inputter.inputInt("Class does no exsit or opened,re-enter: ", 0, Integer.MAX_VALUE);
        }
        System.out.println("List team: "+DaoClassUser.TeamOnlList(new_class));
        int new_team = Inputter.inputInt("Chosse a team: ", 0, Integer.MAX_VALUE);
        while (!DaoClassUser.searchByID("team_id", new_team)) {
            new_team = Inputter.inputInt("Team does no exsit or opened,re-enter: ", 0, Integer.MAX_VALUE);
        }
        System.out.println("List user: "+DaoClassUser.UserOnlList(new_team,new_class));
        int new_user = Inputter.inputInt("Choose a student: ", 0, Integer.MAX_VALUE);
        while (!DaoClassUser.searchByID("user_id", new_user)) {
            new_user = Inputter.inputInt("Student does no exsit or opened,re-enter: ", 0, Integer.MAX_VALUE);
        }
        System.out.println("List Iteration: "+DaoIter.IterListByClass(new_class));
        int new_iter = Inputter.inputInt("Choose an Iter: ", 0, Integer.MAX_VALUE);
        while (!DaoIter.checkIterID(new_iter)) {
            new_iter = Inputter.inputInt("Iteration does no exsit or opened,re-enter: ", 0, Integer.MAX_VALUE);
        }
        if(DaoIterEvalution.checkExist(new_class, new_team, new_user, new_iter)){
            System.out.println("This eval is exist, do again");
            addEvalution(id);
        }
        int bonus = 0;
        double grade = 0;
        String note = "";
        IterationEvalution iterationEvalution = new IterationEvalution(0, new_iter, new_class, new_team, new_user, bonus, grade, note);
        DaoIterEvalution.isertIterEval(iterationEvalution);
        int evalID = DaoIterEvalution.findEval(new_class, new_team, new_user, new_iter);
        int criId = DaoTeamEvaluation.searchCriteriaIDTrainer(id, new_team, evalID);
        TeamEvaluation teamEvaluation = new TeamEvaluation(evalID, criId, new_team, 0, null);
        DaoTeamEvaluation.addTeamEvaluation(teamEvaluation);
        // add member eval funtion
    }
    
    public void showPagination(int id) throws SQLException, ClassNotFoundException {
        int count = DaoIterEvalution.countIterEvalforTrainer("a.evaluation_id", "", id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoIterEvalution.displayByPageForTrain(1,"a.evaluation_id", "", id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoIterEvalution.displayByPageForTrain(noPage,"a.evaluation_id", "", id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                        DaoIterEvalution.displayByPageForTrain(noPage,"a.evaluation_id", "", id);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                        DaoIterEvalution.displayByPageForTrain(noPage,"a.evaluation_id", "", id);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }
        public void listByFilter(int id) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nFilter by--------- ");
            System.out.println("1. Iter Name");
            System.out.println("2. Class Code and Team Name");
            System.out.println("3. User Name");
            System.out.println("4. Back");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 6);
            switch (option) {
                case 1:
                    System.out.println("List Iteration: "+DaoIterEvalution.listIterForTrainer(id));
                    int iterId = Inputter.inputInt("Enter Iter id: ", 0, Integer.MAX_VALUE);
                    fillInt("e.iteration_id", iterId, id);
                    break;
                case 2:
                    System.out.println("List Class: " + DaoIterEvalution.listClass(id));
                    int classID = Inputter.inputInt("Enter ID of Class: ", 0, Integer.MAX_VALUE);
                    System.out.println("List Team of class : " + DaoIterEvalution.listTeam(id,classID));
                    int team = Inputter.inputInt("Enter ID of Team: ", 0, Integer.MAX_VALUE);
                    fillInt("c.team_id", team, id);
                    break;                
                case 3:
                    System.out.println("List User: " + DaoIterEvalution.listUser(id));
                    int user = Inputter.inputInt("Enter ID of User: ", 0, Integer.MAX_VALUE);
                    fillInt("d.user_id", user, id);
                    break;    
                default:
                    break;
            }
            if (option == 4) {
                return;
            }
        }
    }
        public void fillInt(String field, int filter, int id) throws SQLException, ClassNotFoundException {

        int count = DaoIterEvalution.countFillterIterEvalforTrainer(field, filter, id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoIterEvalution.displayFillterByPageForTrain(1, field, filter, id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
         DaoIterEvalution.displayFillterByPageForTrain(noPage, field, filter, id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                         DaoIterEvalution.displayFillterByPageForTrain(noPage, field, filter, id);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                         DaoIterEvalution.displayFillterByPageForTrain(noPage, field, filter, id);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }
        public void listByFilterForStu(int id) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nFilter by--------- ");
            System.out.println("1. Iter Name");
            System.out.println("2. Class Code and Team Name");
            System.out.println("3. User Name");
            System.out.println("4. Back");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 6);
            switch (option) {
                case 1:
                    System.out.println("List Iteration: "+DaoIterEvalution.listIterForTrainer(id));
                    int iterId = Inputter.inputInt("Enter Iter id: ", 0, Integer.MAX_VALUE);
                    fillIntForStu("e.iteration_id", iterId, id);
                    break;
                case 2:
                    System.out.println("List Class: " + DaoIterEvalution.listClass(id));
                    int classID = Inputter.inputInt("Enter ID of Class: ", 0, Integer.MAX_VALUE);
                    System.out.println("List Team of class : " + DaoIterEvalution.listTeam(id,classID));
                    int team = Inputter.inputInt("Enter ID of Team: ", 0, Integer.MAX_VALUE);
                    fillIntForStu("c.team_id", team, id);
                    break;                
                case 3:
                    System.out.println("List User: " + DaoIterEvalution.listUser(id));
                    int user = Inputter.inputInt("Enter ID of User: ", 0, Integer.MAX_VALUE);
                    fillIntForStu("d.user_id", user, id);
                    break;    
                default:
                    break;
            }
            if (option == 4) {
                return;
            }
        }
    }
        public void fillIntForStu(String field, int filter, int id) throws SQLException, ClassNotFoundException {

        int count = DaoIterEvalution.countFillterIterEvalforStu(field, filter);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoIterEvalution.displayFillterByPageForStu(1, field, filter);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
         DaoIterEvalution.displayFillterByPageForStu(noPage, field, filter);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                         DaoIterEvalution.displayFillterByPageForStu(noPage, field, filter);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                         DaoIterEvalution.displayFillterByPageForStu(noPage, field, filter);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }
    public void updateIterEval(int id) throws SQLException, ClassNotFoundException{
        int evalID = Inputter.inputInt("Input Iter_eval ID: ", 0, Integer.MAX_VALUE);
        IterationEvalution iterationEvalution = DaoIterEvalution.getIterEval(evalID);
        
        System.out.println("Iter Eval ID: "+String.valueOf(iterationEvalution.getEval_id()));
        System.out.println("Class: "+ iterationEvalution.getClass_code());
        System.out.println("Team: "+iterationEvalution.getTeam_name());
        System.out.println("User: "+iterationEvalution.getFull_name());
        System.out.println("Iteration: "+iterationEvalution.getIter_name());
        System.out.println("Team Grade: "+String.valueOf(iterationEvalution.getTeam_grade()));
        System.out.println("Member Grade: "+String.valueOf(iterationEvalution.getMem_grade()));
        System.out.println("Bonus: "+String.valueOf(iterationEvalution.getBonus()));
        System.out.println("Grade: "+String.valueOf(iterationEvalution.getGrade()));
        System.out.println("Note: "+iterationEvalution.getNote());
        
        int newBonus = 0;
        int op = Inputter.inputInt("Do u want to update bonus(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            newBonus = Inputter.inputInt("Input bonus(1-10): ", 0, 10);
            iterationEvalution.setBonus(newBonus);
        }
        if (op == 2) {
            newBonus= iterationEvalution.getBonus();
        }
        
        op = Inputter.inputInt("Do u want to update 1-TeamEvaluation, 2-MemberEvaluation 3-No Update", 1, 3);
        if(op == 1){
            int teamEvalId=DaoTeamEvaluation.searchEvaluationIteration(evalID);
                    TeamEvaluationController b = new TeamEvaluationController();
                    b.teamEvaluationUpdate(id, String.valueOf(iterationEvalution.getTeam_id()), evalID);
        
        }
        if(op == 3){}
            
                double teamGrade = DaoIterEvalution.getTeamGrade(evalID);
        double memGrade = DaoIterEvalution.getMemGrade(evalID);

        iterationEvalution.setGrade((memGrade+teamGrade+newBonus/20)/2);
        
        String newNote;
        op = Inputter.inputInt("Do u want to update Note(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            newNote = Inputter.inputBlankStr("Input Note: ");
            iterationEvalution.setNote(newNote);
        }
        if (op == 2) {
            newNote= iterationEvalution.getNote();
        }
        DaoIterEvalution.updateEvaluation(iterationEvalution);

        
        op = Inputter.inputInt("Do u want to update Team Evaluation or Member Evaluation", 1, 3);
        if(op == 1){
            int teamEvalId=DaoTeamEvaluation.searchEvaluationIteration(evalID);
                    TeamEvaluationController b = new TeamEvaluationController();
                    b.teamEvaluationUpdate(id, String.valueOf(iterationEvalution.getTeam_id()), evalID);
        
        }

         op = Inputter.inputInt("Do u wan", 0, 0);

    }
       
        
}
