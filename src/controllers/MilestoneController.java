/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoClass;
import dao.DaoMilestone;
import dao.DaoIter;
import dao.DaoUser;
import view_inputs.Inputter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Milestone;

/**
 *
 * @author Admin
 */
public class MilestoneController {

    public void AddMilestone(int id) throws SQLException, ClassNotFoundException {
        System.out.println("Class List: " + DaoClass.StatusOnlList(id));
        int ClassId = Inputter.inputInt("Enter Class Id: ", 0, Integer.MAX_VALUE);
        while (!DaoClass.SearchCIDonl(ClassId)) {
            ClassId = Inputter.inputInt("ClassID isn't exist,re-enter: : ", 0, Integer.MAX_VALUE);
        }
        System.out.println("List Iteraiton: " + DaoIter.IterListByClass(ClassId));
        int    iterId = Inputter.inputInt("Enter Iter id: ", 0, Integer.MAX_VALUE);
            while (!DaoIter.checkIterID(iterId)) {
                iterId = Inputter.inputInt("This Iteration doesn't exist,Re-Enter: ", 0, Integer.MAX_VALUE);
            }
        Date FromDate = Inputter.getDateFur("Input date start(yyyy/mm/dd): ");
        Date ToDate = Inputter.getDateFur("Input date end(yyyy/mm/dd): ");
        int status = Inputter.inputInt("Enter status(1-Opened,2-Closed,3-Canceled): ", 1, 3);

        Milestone ms = new Milestone(iterId, iterId, ClassId, FromDate, ToDate, status);

        DaoMilestone.insertMilestone(ms);

    }

    public void ShowPagination(int id) throws SQLException, ClassNotFoundException {
        int count = DaoMilestone.countMS("a.milestone_id", "", id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoMilestone.DisplayByPage(1, "a.milestone_id", "", id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoMilestone.DisplayByPage(noPage, "a.milestone_id", "", id);
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
                        DaoMilestone.DisplayByPage(noPage, "a.milestione_id", "", id);
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
                        DaoMilestone.DisplayByPage(noPage, "a.milestione_id", "", id);
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
            System.out.println("2. Class Code");
            System.out.println("3. Back");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 6);
            switch (option) {
                case 1:
                    System.out.println("List Iteraiton: " + DaoIter.IterList());
                    int iterId = Inputter.inputInt("Enter Iter id: ", 0, Integer.MAX_VALUE);
                    fillInt("b.iteration_id", iterId, id);
                    break;
                case 2:
                    System.out.println("List Trainer: " + DaoUser.RoleList(2));
                    int trainer = Inputter.inputInt("Enter ID of Trainer: ", 0, Integer.MAX_VALUE);
                    fillInt("c.class_code", trainer, id);
                    break;
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }

//    public void fillString(String msg, String field, int id) throws SQLException, ClassNotFoundException {
//        String filter = Inputter.inputNonBlankStr(msg);
//
//        int count = DaoMilestone.countMS(field, filter,id);
//        int countPage = count / 5;
//        if (count % 5 != 0) {
//            countPage++;
//        }
//        int noPage;
//        DaoMilestone.DisplayByPage(1, field, filter,id);
//        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
//        if (noPage == 0) {
//            return;
//        } else {
//            DaoMilestone.DisplayByPage(noPage, field, filter,id);
//        }
//        int option;
//        while (true) {
//            if (noPage == 0) {
//                return;
//            }
//            System.out.println("\nShow--------- ");
//            System.out.println("1. Next page");
//            System.out.println("2. Pre page");
//            System.out.println("3. Back");
//
//            System.out.print("Your option: ");
//
//            option = Inputter.inputInt("", 1, 3);
//            switch (option) {
//                case 1: {
//                    noPage++;
//                    if (noPage > countPage) {
//                        System.out.println("There is no more page!");
//                        noPage--;
//                        break;
//                    } else {
//                        DaoMilestone.DisplayByPage(noPage, field, filter,id);
//                        break;
//                    }
//                }
//                case 2: {
//                    noPage--;
//                    if (noPage <= 0) {
//                        System.out.println("There is no more page!");
//                        noPage++;
//                        break;
//                    } else {
//                        DaoMilestone.DisplayByPage(noPage, field, filter,id);
//                        break;
//                    }
//                }
//                default:
//                    break;
//            }
//            if (option == 3) {
//                return;
//            }
//        }
//    }
    public void fillInt(String field, int filter, int id) throws SQLException, ClassNotFoundException {

        int count = DaoMilestone.countMS(field, field, id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoMilestone.DisplayByPage(1, field, String.valueOf(filter), id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoMilestone.DisplayByPage(noPage, field, String.valueOf(filter), id);
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
                        DaoMilestone.DisplayByPage(noPage, field, String.valueOf(filter), id);
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
                        DaoMilestone.DisplayByPage(noPage, field, String.valueOf(filter), id);
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

    public void updateMilestone() throws SQLException, ClassNotFoundException {
        int Id = Inputter.inputInt("Enter Milestone id to update: ", 0, Integer.MAX_VALUE);
        String[] TypeStatus = {"Open", "Closed","Cancel"};
        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
        Milestone ms1 = DaoMilestone.ProMileID(Id);
        while (ms1 == null) {
            Id = Inputter.inputInt("This Milestone isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            ms1 = DaoMilestone.ProMileID(Id);
        }
 
        System.out.println("ID: "+ String.valueOf(ms1.getId()));
        System.out.println("Class Code: "+ms1.getClass_code());
        System.out.println("Iter: "+ ms1.getIter_name());
        System.out.println("From: "+ SDF.format(ms1.getFrom()));
        System.out.println("Due to: "+ SDF.format(ms1.getTo()));
        System.out.println("Satus: "+ TypeStatus[ms1.getStatus()-1]);
        
        int ClassId = 0;
        int op = Inputter.inputInt("Do u want to update Class(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("List Class: " + DaoClass.StatusOnlList());
            ClassId = Inputter.inputInt("Enter Class ID: ", 0, Integer.MAX_VALUE);
            while (!DaoClass.CheckClID(ClassId)) {
                ClassId = Inputter.inputInt("This class doesn't exist,Re-Enter Subject Code: ", 0, Integer.MAX_VALUE);
            }
        }
        if (op == 2) {
            ClassId = ms1.getClass_id();
        }
        int IterId = 0;
        op = Inputter.inputInt("Do u want to update Iteration(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("List Iteraiton: " + DaoIter.IterListByClass(ClassId));
            IterId = Inputter.inputInt("Enter Iter id: ", 0, Integer.MAX_VALUE);
            while (!DaoIter.checkIterID(IterId)) {
                IterId = Inputter.inputInt("This Iteration doesn't exist,Re-Enter: ", 0, Integer.MAX_VALUE);
            }
        }
        if (op == 2) {
            IterId = ms1.getIter_id();
        }

        Date from = null;
        op = Inputter.inputInt("Do u want to update Form Date(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            from = Inputter.getDateFur("Enter new day start: ");
        }
        if (op == 2) {
            from = ms1.getFrom();
        }

        Date to = null;
        op = Inputter.inputInt("Do u want to update To Date(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            to = Inputter.getDateFur("Enter new day start: ");
        }
        if (op == 2) {
            to = ms1.getTo();
        }

        int status = ms1.getStatus();
        Milestone ms2 = new Milestone(Id, IterId, ClassId, from, to, status);

        op = Inputter.inputInt("Do u want to update Class(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            DaoMilestone.UpdateMile(ms2);
        }
        if (op == 2) {
            return;
        }
    }

    public void StatusClass(int st) throws SQLException, ClassNotFoundException {
        System.out.println("Opened Milestopne: " + DaoMilestone.StatusOnlList());
        int Id = Inputter.inputInt("Enter Milestone ID need to change: ", 0, Integer.MAX_VALUE);
        while (!DaoMilestone.SearchMileIDonl(Id)) {
            Id = Inputter.inputInt("This Milestone isn't exist or opened, re-enter: ", 0, Integer.MAX_VALUE);
        }
        DaoMilestone.UpdateStatus(Id, st);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MilestoneController s = new MilestoneController();

    }

}
