/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import view_inputs.Inputter;
import dao.DaoIteration;
import dao.DaoSubject;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.regex.Pattern;
import model.Iteration;

/**
 *
 * @author Admin
 */
public class IterationController {

    public void addIteration() throws SQLException, ClassNotFoundException {
        int iterationID = Inputter.inputInt("Enter Iteration id :", 0, Integer.MAX_VALUE);
        while (DaoIteration.checkIterID(iterationID) == true) {
            iterationID = Inputter.inputInt("This iteration id already exist , enter another iteration id:", 0, Integer.MAX_VALUE);
        }

        System.out.println("List Subject: " + DaoSubject.SubList());
        int subId = Inputter.inputInt("Enter Subject Id: ", 0, Integer.MAX_VALUE);
        while (!DaoSubject.CheckSubjectID(subId)) {
            subId = Inputter.inputInt("This Subject doesn't exist,Re-Enter Subject Id: ", 0, Integer.MAX_VALUE);
        }
        int newDate = Inputter.inputInt("input duration", 0, Integer.MAX_VALUE);
        String iterationName = Inputter.inputNonBlankStr("Enter iteration name:");
        int status = Inputter.inputInt("Enter status(1-Active,2-Deactive): ", 1, 2);
        Iteration n = new Iteration(iterationID, subId, iterationName,  newDate, status);
        DaoIteration.insertIteration(n);

    }

    public static void showPagination() throws SQLException, ClassNotFoundException {
        int count = DaoIteration.countIter("a.iteration_id", "");
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoIteration.iterationDisplayByPage(1, "a.iteration_id", "");
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoIteration.iterationDisplayByPage(noPage, "a.iteration_id", "");
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Iteration List");

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
                        DaoIteration.iterationDisplayByPage(noPage, "a.iteration_id", "");
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
                        DaoIteration.iterationDisplayByPage(noPage, "a.iteration_id", "");
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

    public void listFillter() throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nSearch by--------- ");
            System.out.println("1. Sub Code      2.Status ");
            System.out.println("3. Iteration Name      4.Back to IterationList  ");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 4);
            switch (option) {
                case 1:
                    searchString("Enter a part of Sub Code: ", "b.subject_code");
                    break;
                case 2:
                    searchInt("Enter Status(1-Active,0-inactive): ", "a.status", 0, 1);
                    break;
                case 3:
                    searchString("Enter a I"
                            + "teration name:(iter1,2,3,4)", "a.iteration_name");
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

        int count = DaoIteration.countIter(field, filter);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoIteration.iterationDisplayByPage(1, field, filter);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoIteration.iterationDisplayByPage(noPage, field, filter);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Iteration List");

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
                        DaoIteration.iterationDisplayByPage(noPage, field, filter);
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
                        DaoIteration.iterationDisplayByPage(noPage, field, filter);
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

        int count = DaoIteration.countIter(field, String.valueOf(filter));
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoIteration.iterationDisplayByPage(1, field, String.valueOf(filter));
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoIteration.iterationDisplayByPage(noPage, field, String.valueOf(filter));
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Iteration List");

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
                        DaoIteration.iterationDisplayByPage(noPage, field, String.valueOf(filter));
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
                        DaoIteration.iterationDisplayByPage(noPage, field, String.valueOf(filter));
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

    public void iterationDetail() throws SQLException, ClassNotFoundException {
        int Id = Inputter.inputInt("Enter Iteration id to update: ", 0, Integer.MAX_VALUE);
        Iteration c1 = DaoIteration.proIterId(Id);
        while (c1 == null) {
            Id = Inputter.inputInt("This iteration isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoIteration.proIterId(Id);
        }
        System.out.printf("%-7s%-12s%-12s%-15s%-10s\n",
                "ID",
                "Subject",
                "ITerName",
                "Duration",
                "Status");
        System.out.println(c1);

        int subject = 0;
        int op = Inputter.inputInt("Do u want to update subject (1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("List Subject: " + DaoSubject.SubList());
            subject = Inputter.inputInt("Enter Subject Id: ", 0, Integer.MAX_VALUE);

            while (!DaoSubject.CheckSubjectID(subject)) {
                subject = Inputter.inputInt("This Subject doesn't exist,Re-Enter Subject Id: ", 0, Integer.MAX_VALUE);
            }
        }
        if (op == 2) {
            subject = c1.getSubid();
        }

        String newIterName = null;
        op = Inputter.inputInt("Do u want to update itername (1-Y,2-N): ", 1, 2);
        if (op == 1) {

            newIterName = Inputter.inputBlankStr("Enter newiter name");
        }

        if (op == 2) {
            newIterName = c1.getIterName();
        }

        int date = 0;

        op = Inputter.inputInt("Do u want to update Duration (1-Y,2-N): ", 1, 2);
        if (op == 1) {
            date = Inputter.inputInt("Enter duration : ",0,Integer.MAX_VALUE);
        }
        if (op == 2) {
            date = c1.getDuration();
        }

        int status = c1.getStatus();
        Iteration c2 = new Iteration(Id, subject, newIterName, date, status);
        op = Inputter.inputInt("Do u want to update Iteration(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            DaoIteration.updateIteration(c2);
            c2 = DaoIteration.proIterId(Id);
            System.out.println(c2);
        }
        if (op == 2) {
            return;
        }
    }
public void acStatusIteration(int st) throws SQLException, ClassNotFoundException {
        System.out.println("Closed Iteration : " + DaoIteration.statusOnlList(0));
        int Id = Inputter.inputInt("Enter Criteria ID need to Activate: ", 0, Integer.MAX_VALUE);
        while (!DaoIteration.search(Id)) {
            Id = Inputter.inputInt("This Iteration isn't exist , re-enter: ", 0, Integer.MAX_VALUE);
        }
        DaoIteration.updateStatus(st, Id);
    }
public void iACStatusIteration(int st) throws SQLException, ClassNotFoundException {
        System.out.println("Opened Iteration : " + DaoIteration.statusOnlList(1));
        int Id = Inputter.inputInt("Enter Criteria ID need to DeActivate: ", 0, Integer.MAX_VALUE);
        while (!DaoIteration.search(Id)) {
            Id = Inputter.inputInt("This Iteration isn't exist , re-enter: ", 0, Integer.MAX_VALUE);
        }
        DaoIteration.updateStatus(st, Id);
    }
    

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        IterationController a = new IterationController();
        a.addIteration();
//        a.StatusClass(2);
    }
}
