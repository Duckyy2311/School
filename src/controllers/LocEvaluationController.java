/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.ConnectionUtils;
import dao.DaoClass;
import dao.DaoClassUser;
import dao.DaoFunction;
import dao.DaoMilestone;
import dao.DaoTeam;
import dao.DaoTracking;
import dao.DaoUser;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.ClassUser;
import model.Tracking;
import view_inputs.Inputter;
import dao.DaoIssue;
import dao.DaoLocEvaluation;
import model.Issue;
import model.LocEvaluation;

/**
 *
 * @author AnhMinh
 */
public class LocEvaluationController {

    public void addLocEvaluationTrainer(int id) throws SQLException, ClassNotFoundException {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");

        System.out.println("\nAdd Evaluation--------- ");
        System.out.println("Your Tracking List" + DaoLocEvaluation.ListTrackingTrainer(id));
        int newTracking = Inputter.inputInt("input Tracking you want to add : ", 0, Integer.MAX_VALUE);
        while (DaoLocEvaluation.SearchTracking(newTracking) == false) {
            newTracking = Inputter.inputInt("this Tracking does not exist, please try again: ", 0, Integer.MAX_VALUE);
        }

        String evaluationNote = Inputter.inputBlankStr("input Evaluation note: ");

        int newComplexityId = Inputter.inputInt("Status([1-Simple][2-Medium][3-Hard]", 1, 3);
        int newQualityiD = Inputter.inputInt("Input Quality_id ", 0, Integer.MAX_VALUE);
        LocEvaluation l = new LocEvaluation(newTracking, evaluationNote, newComplexityId, newQualityiD);
        DaoLocEvaluation.addLocEvaluation(l);
        int locId = l.getEvaluationId();
        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, "a.evaluation_id", String.valueOf(locId), id);
    }

    public static void showPaginationTrainer(int id) throws SQLException, ClassNotFoundException {
        int count = DaoLocEvaluation.countLocEvaluationTrainer("a.evaluation_note", "", id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, "a.evaluation_note", "", id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, "a.evaluation_note", "", id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to LocEvaluation List");

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
                        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, "a.evaluation_note", "", id);
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
                        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, "a.evaluation_note", "", id);
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

    public static void showPaginationStudent(int id) throws SQLException, ClassNotFoundException {
        int count = DaoLocEvaluation.countLocEvaluationStudent("a.evaluation_note", "", id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoLocEvaluation.locEvaluationDisplayByPageStudent(1, "a.evaluation_note", "", id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoLocEvaluation.locEvaluationDisplayByPageStudent(1, "a.evaluation_note", "", id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to LocEvaluation List");

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
                        DaoLocEvaluation.locEvaluationDisplayByPageStudent(1, "a.evaluation_note", "", id);
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
                        DaoLocEvaluation.locEvaluationDisplayByPageStudent(1, "a.evaluation_note", "", id);
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

    @SuppressWarnings("empty-statement")
    public void evaluationUpdate(int id) throws SQLException, ClassNotFoundException {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        showPaginationTrainer(id);
        int locId = Inputter.inputInt("Enter Evaluation id to update: ", 0, Integer.MAX_VALUE);
        LocEvaluation c1 = DaoLocEvaluation.ProUemail(locId);
        while (c1 == null) {
            locId = Inputter.inputInt("This Evaluation isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoLocEvaluation.ProUemail(locId);

        }

        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, "a.evaluation_id", String.valueOf(locId), id);

        int trackingId = 0;
        ;
        int op = Inputter.inputInt("Do u want to update Tracking(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("Your Tracking List" + DaoLocEvaluation.ListTrackingTrainer(id));
            trackingId = Inputter.inputInt("input Tracking you want to add : ", 0, Integer.MAX_VALUE);
            while (DaoIssue.SearchClassID(trackingId) == false) {
                trackingId = Inputter.inputInt("this Tracking does not exist, please try again: ", 0, Integer.MAX_VALUE);
            }
        }
        if (op == 2) {
            trackingId = c1.getTrackingId();
        }

        Date evaluationTime = c1.getEvaluationTime();

        String evaluationNote = null;
        op = Inputter.inputInt("Do u want to update Evaluation Note(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            evaluationNote = Inputter.inputNonBlankStr("input new Evaluation Note : ");
        };
        if (op == 2) {
            evaluationNote = c1.getEvaluationNote();
        }
        int complexId = 0;
        op = Inputter.inputInt("Do u want to update conplexity(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            complexId = Inputter.inputInt("input Complexity[1-Simple],[2-Medium],[3-Hard] :", 1, 3);
        };
        if (op == 2) {
            complexId = c1.getComplexityId();
        }
        int qualityId = 0;
        op = Inputter.inputInt("Do u want to update Quality(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            qualityId = Inputter.inputInt("input Quality id : :", 1, 3);
        };
        if (op == 2) {
            qualityId = c1.getComplexityId();
        }
        LocEvaluation c2 = new LocEvaluation(trackingId, evaluationNote, complexId, qualityId, locId);
        DaoLocEvaluation.updateLocEvaluation(c2);

        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, "a.evaluation_id", String.valueOf(locId), id);

    }

    @SuppressWarnings("empty-statement")
    public void evaluationDelete(int id) throws SQLException, ClassNotFoundException {
        showPaginationStudent(id);
        int locId = Inputter.inputInt("Enter Evaluation id to Delete: ", 0, Integer.MAX_VALUE);
        LocEvaluation c1 = DaoLocEvaluation.ProUemail(locId);
        while (c1 == null) {
            locId = Inputter.inputInt("This Evaluation isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoLocEvaluation.ProUemail(locId);

        }

        DaoLocEvaluation.deleteLocEvaluation(locId);

    }

    @SuppressWarnings("empty-statement")

    public void searchStudent(int id) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nFilter by--------- ");
            System.out.println("1. Complexity      2.Quality_id");
            System.out.println("3. Back to ClassUserList");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1:
                    searchIntTrainer("Enter Complexity( [1-Simple], [2-Medium], [3-Hard] :", "a.complexity_id", 1, 3, id);
                    break;
                case 2:
                    searchIntTrainer("Enter Quality :", "a.quality_id", 0, Integer.MAX_VALUE, id);
                    break;

            }
            if (option == 3) {
                return;
            }
        }
    }

    public void searchTrainer(int id) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nSearch by--------- ");
            System.out.println("1. Complexity ID      2.quality ID");
            System.out.println("3. Back to LocEvaluationList");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1:
                    searchIntTrainer("Enter Complexity( [1-Simple], [2-Medium], [3-Hard] :", "a.complexity_id", 1, 3, id);
                    break;
                case 2:
                    searchIntTrainer("Enter Quality :", "a.quality_id", 0, Integer.MAX_VALUE, id);
                    break;

                default:
                    break;
            }
            if (option == 5) {
                return;
            }
        }
    }


    public void searchIntTrainer(String msg, String field, int min, int max, int id) throws SQLException, ClassNotFoundException {
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoLocEvaluation.countLocEvaluationTrainer(field, String.valueOf(filter), id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, field, String.valueOf(filter), id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, field, String.valueOf(filter), id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to LocEvaluation List");

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
                        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, field, String.valueOf(filter), id);
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
                        DaoLocEvaluation.locEvaluationDisplayByPageTrainer(1, field, String.valueOf(filter), id);
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

    public void searchIntStudent(String msg, String field, int min, int max, int id) throws SQLException, ClassNotFoundException {
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoLocEvaluation.countLocEvaluationStudent(field, String.valueOf(filter), id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoLocEvaluation.locEvaluationDisplayByPageStudent(1, field, String.valueOf(filter), id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoLocEvaluation.locEvaluationDisplayByPageStudent(1, field, String.valueOf(filter), id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to LocEvaluation List");

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
                        DaoLocEvaluation.locEvaluationDisplayByPageStudent(1, field, String.valueOf(filter), id);
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
                        DaoLocEvaluation.locEvaluationDisplayByPageStudent(1, field, String.valueOf(filter), id);
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

    public void searchStringTrainer(String msg, String field, int id) throws SQLException, ClassNotFoundException {
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoIssue.countIssueTrainer(field, filter, id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoIssue.IssueDisplayByPageTrainer(1, field, filter, id);

        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoIssue.IssueDisplayByPageTrainer(count, field, filter, id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Issue List");

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
                        DaoIssue.IssueDisplayByPageTrainer(noPage, field, filter, id);
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
                        DaoIssue.IssueDisplayByPageTrainer(noPage, field, filter, id);
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
}
