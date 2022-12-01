/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import view_inputs.Inputter;
import dao.DaoCriteria;
import dao.DaoIteration;
import dao.DaoSubject;
import java.sql.SQLException;
import java.util.regex.Pattern;
import model.Criteria;


/**
 *
 * @author Admin
 */
public class CriteriaController {

    public void addCriteria() throws SQLException, ClassNotFoundException {

        System.out.println("List Subject: " + DaoSubject.SubList());
        int subId = Inputter.inputInt("Enter Subject Id: ", 0, Integer.MAX_VALUE);
        while (!DaoSubject.CheckSubjectID(subId)) {
            subId = Inputter.inputInt("This Subject doesn't exist,Re-Enter Subject Id: ", 0, Integer.MAX_VALUE);
        }
<<<<<<< HEAD
        //?/
        int IterId = Inputter.inputInt("Enter Iteration Id: ", 0, Integer.MAX_VALUE);
        while (!DaoIteration.CheckIterID(IterId)) {
            IterId = Inputter.inputInt("This Iteration already exist,Re-Enter Iteration Id: ", 0, Integer.MAX_VALUE);
=======

        int iterId = Inputter.inputInt("Enter Iteration Id: ", 0, Integer.MAX_VALUE);
        while (!DaoIteration.checkIterID(iterId)) {
            iterId = Inputter.inputInt("This Iteration already exist,Re-Enter Iteration Id: ", 0, Integer.MAX_VALUE);
>>>>>>> eafa52c2d66ebf1a7f5b2fb383a827711fedb850
        }

        String criterName = Inputter.inputBlankStr("Enter name of criteria: ");
        int evaWei = Inputter.inputInt("Enter Evaluation Weight(10-100): ", 10, 100);

<<<<<<< HEAD
        int teameva = Inputter.inputInt("Enter TeamEvaluation  check(0-No,1-Yes): ", 1, 2);
        int critorder = Inputter.inputInt("Enter criteria Order: ", 0, Integer.MAX_VALUE);
        int maxloc = Inputter.inputInt("Enter Max Loc: ", 0, Integer.MAX_VALUE);
=======
        int teameva = Inputter.inputInt("Enter TeamEvaluation  check(2-No,1-Yes): ", 1, 2);
        int critOrder = Inputter.inputInt("Enter criteria Order: ", 0, Integer.MAX_VALUE);
        int maxLoc = Inputter.inputInt("Enter Max Loc: ", 0, Integer.MAX_VALUE);
>>>>>>> eafa52c2d66ebf1a7f5b2fb383a827711fedb850
        int status = Inputter.inputInt("Enter status(1-Active,2-inactive): ", 1, 2);
        Criteria n = new Criteria(0, subId, iterId, criterName, evaWei, teameva, critOrder, maxLoc, status);
        DaoCriteria.insertCriteria(n);
    }

    public static void showPagination() throws SQLException, ClassNotFoundException {
        int count = DaoCriteria.countCriteria("a.criteria_id", "");
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoCriteria.criteriaDisplayByPage(1, "a.criteria_id", "");
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoCriteria.criteriaDisplayByPage(noPage, "a.criteria_id", "");
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Criteria List");

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
                        DaoCriteria.criteriaDisplayByPage(noPage, "a.criteria_id", "");
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
                        DaoCriteria.criteriaDisplayByPage(noPage, "a.criteria_id", "");
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

    public void search() throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nSearch by--------- ");
            System.out.println("1. Sub Code      3.Iteration Name");
            System.out.println("2.Status");
            System.out.println("4. Back to CriteriaList");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 4);
            switch (option) {
                case 3:
                    searchString("Enter a part of Iteration name: ", "b.iteration_name");
                    break;
                case 1:
                    searchString("Enter a part of Sub Code: ", "c.subject_code");
                    break;
                case 2:
                    searchInt("Enter Status(1-Active,0-inactive): ", "a.status", 0, 1);
                    break;
                default:
                    break;
            }
            if (option == 4) {
                return;
            }
        }
    }

    public void searchString(String msg, String field) throws SQLException, ClassNotFoundException {
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoCriteria.countCriteria(field, filter);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoCriteria.criteriaDisplayByPage(1, field, filter);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoCriteria.criteriaDisplayByPage(noPage, field, filter);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Search by another filter ");

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
                        DaoCriteria.criteriaDisplayByPage(noPage, field, filter);
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
                        DaoCriteria.criteriaDisplayByPage(noPage, field, filter);
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

    public void searchInt(String msg, String field, int min, int max) throws SQLException, ClassNotFoundException {
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoCriteria.countCriteria(field, String.valueOf(filter));
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoCriteria.criteriaDisplayByPage(1, field, String.valueOf(filter));
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoCriteria.criteriaDisplayByPage(noPage, field, String.valueOf(filter));
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Search by another filter");

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
                        DaoCriteria.criteriaDisplayByPage(noPage, field, String.valueOf(filter));
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
                        DaoCriteria.criteriaDisplayByPage(noPage, field, String.valueOf(filter));
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

    public void criteriaDetail() throws SQLException, ClassNotFoundException {
        int Id = Inputter.inputInt("Enter Criteria id to update: ", 0, Integer.MAX_VALUE);
        Criteria c1 = DaoCriteria.proCriteriaId(Id);
        while (c1 == null) {
            Id = Inputter.inputInt("This criteria isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoCriteria.proCriteriaId(Id);
        }
        System.out.printf("%-10s%-15s%-15s%-20s%-25s%-20s%-20s%-10s%-15s\n",
                "ID",
                "Subject",
                "Iteration",
                "Criteria Name",
                "Evaluation Weight",
                "Team Evaluation",
                "Criteria Order",
                "Max loc",
                "Status");
        System.out.println(c1);

        int subId = c1.getSubId();
        int op = 0;
        op = Inputter.inputInt("Do u want to update Subject (1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("List Subject: " + DaoSubject.SubList());
            subId = Inputter.inputInt("Enter Subject Code: ", 0, Integer.MAX_VALUE);
            while (!DaoSubject.CheckSubjectID(subId)) {
                subId = Inputter.inputInt("This Subject doesn't exist,Re-Enter Subject Code: ", 0, Integer.MAX_VALUE);
            }
        }
        int iterID = c1.getIterationID();
        op = Inputter.inputInt("Do u want to update Iteration (1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("List Iteration: " + DaoIteration.iterListBySubject(subId));
            iterID = Inputter.inputInt("Enter  Iteration: ", 0, Integer.MAX_VALUE);
            while (!DaoIteration.checkIterID(iterID)) {
                iterID = Inputter.inputInt("This Iteration doesn't exist,Re-Enter Iteration ID: ", 0, Integer.MAX_VALUE);
            }
        }

        String critName = null;
        op = Inputter.inputInt("Do u want to update Criteria Name(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            critName = Inputter.inputBlankStr("Enter Criteria Name:");
        }
        if (op == 2) {
            critName = c1.getCriteriaName();
        }

        int evaWei = 0;
        op = Inputter.inputInt("Do u want to update evaluation weight(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            evaWei = Inputter.inputInt("Enter evaluation weight: ", 10, 100);
        }
        if (op == 2) {
            evaWei = c1.getEvaluationWeight();
        }

        int teamEva = 0;
        op = Inputter.inputInt("Do u want to team evaluation (1-Y,2-N): ", 1, 2);
        if (op == 1) {
            teamEva = Inputter.inputInt("Enter team evaluation  check(2-No,1-Yes): ", 1, 2);
        }
        if (op == 2) {
            teamEva = c1.getTeamEvaluation();
        }

        int critOrder = 0;
        op = Inputter.inputInt("Do u want to update Criteria Order(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            critOrder = Inputter.inputInt("Enter evaluation weight: ", 0, Integer.MAX_VALUE);
        }
        if (op == 2) {
            critOrder = c1.getCriteriaOrder();
        }
        int maxloc = 0;
        op = Inputter.inputInt("Do u want to update Max Loc(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            maxloc = Inputter.inputInt("Enter Max Loc: ", 0, Integer.MAX_VALUE);
        }
        if (op == 2) {
            maxloc = c1.getMaxLoc();
        }

    int status = c1.getStatus();
    Criteria c2 = new Criteria(Id, subId, iterID, critName, evaWei, teamEva, critOrder, maxloc, status);

    op  = Inputter.inputInt("Do u want to update Criteria(1-Y,2-N): ", 1, 2);
    if (op

    
        == 1) {
            DaoCriteria.updateCriteria(c2);
            c2 = DaoCriteria.proCriteriaId(Id);
            System.out.println(c2);
    }
    if (op

    
        == 2) {
            return;
    }
}

public void aCStatusClass(int st) throws SQLException, ClassNotFoundException {
        System.out.println("Closed Criteria : " + DaoCriteria.statusOnlList(0));
        int Id = Inputter.inputInt("Enter Criteria ID need to Activate: ", 0, Integer.MAX_VALUE);
        while (!DaoCriteria.search(Id)) {
            Id = Inputter.inputInt("This Criteria isn't exist  re-enter: ", 0, Integer.MAX_VALUE);
        }
        DaoCriteria.updateStatus(st, Id);
    }
public void iACStatusClass(int st) throws SQLException, ClassNotFoundException {
        System.out.println("Opened Criteria : " + DaoCriteria.statusOnlList(1));
        int Id = Inputter.inputInt("Enter Criteria ID need to DeActivate: ", 0, Integer.MAX_VALUE);
        while (!DaoCriteria.search(Id)) {
            Id = Inputter.inputInt("This Criteria isn't exist re-enter: ", 0, Integer.MAX_VALUE);
        }
        DaoCriteria.updateStatus(st, Id);
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CriteriaController a = new CriteriaController();
        a.iACStatusClass(0)
                ;
        
        
//        a.StatusClass(2);
    }
}
