/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import view_inputs.Inputter;
import dao.DaoUser;
import dao.DaoClass;
import dao.DaoSubject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import model.Class;
import model.Subject;

/**
 *
 * @author Admin
 */
public class ClassController {
    
    public void addClass(int id) throws SQLException, ClassNotFoundException {
        String ClassCode = Inputter.inputPattern("Enter class code(IS000): ", "Wrong Class Code format! Please re-Enter: ", "[A-Za-z]{2}[\\d]{3}");
        
        System.out.println("List Trainer: " + DaoUser.RoleList(2));
        int TrainerId = Inputter.inputInt("Enter trainer id: ", 0, Integer.MAX_VALUE);
        while (DaoUser.SearchRolebyId(TrainerId) != 2) {
            TrainerId = Inputter.inputInt("This ID isn't Trainer,Re-Enter: ", 0, Integer.MAX_VALUE);
        }
        System.out.println("List Sub: " + DaoSubject.SubList(id));
        int SubId = Inputter.inputInt("Enter Subject Id: ", 0, Integer.MAX_VALUE);
        while (!DaoSubject.CheckSubjectID(SubId)) {
            SubId = Inputter.inputInt("This Subject doesn't exist,Re-Enter Subject Id: ", 0, Integer.MAX_VALUE);
        }
        int year = Inputter.inputInt("Enter Class Year: ", 2000, 3000);
        String Term = Inputter.inputBlankStr("Enter Term of class: ");
        int bl5 = Inputter.inputInt("Enter Block 5 check(2-No,1-Yes): ", 1, 2);
        int status = Inputter.inputInt("Enter status(1-Active,2-Closed,3-Cancelled): ", 1, 3);
        Class n = new Class(0, ClassCode, TrainerId, SubId, year, Term, bl5, status);
        DaoClass.insertClass(n);
    }
    
    public static void showPagination(int id, String owner) throws SQLException, ClassNotFoundException {
        int count = DaoClass.countClass("a.class_id", "", id, owner);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoClass.showClassByPage(1, "a.class_id", "", id, owner);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoClass.showClassByPage(noPage, "a.class_id", "", id, owner);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Class Menu");
            
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
                        DaoClass.showClassByPage(noPage, "a.class_id", "", id, owner);
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
                        DaoClass.showClassByPage(noPage, "a.class_id", "", id, owner);
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
    
    public void search(int id, String owner) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nSearch by--------- ");
            System.out.println("1. Class Code");
            System.out.println("2. Year");
            System.out.println("3. Back to Class Menu");
            
            System.out.print("Your option: ");
            
            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    String filter = Inputter.inputNonBlankStr("Enter a part of Class Code(IS000): ");
                    searchString("a.class_code", filter, id, owner);
                }
                break;
                case 2: {
                    int filter = Inputter.inputInt("Enter a Year: ", 2000, Integer.MAX_VALUE);
                    searchInt("a.class_year", filter, id, owner);
                }
                break;
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }
    
    public void listByFilter(int id, String owner) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nFilter by--------- ");
            System.out.println("1. Trainer    3.Staus");
            System.out.println("2. Subject");
            System.out.println("4. Back to ClassList");
            
            System.out.print("Your option: ");
            
            option = Inputter.inputInt("", 1, 4);
            switch (option) {
                case 1:
                    System.out.println("List Trainer: " + DaoUser.RoleList(2));
                    int trainer = Inputter.inputInt("Enter ID of Trainer: ", 0, Integer.MAX_VALUE);
                    searchInt("a.trainer_id", trainer, id, owner);                    
                    break;
//                                    case 2: break;
//                                    case 3: searchString("Enter a part of Sub Code: ", "c.subject_code",id,owner);break;
                case 2:
                    System.out.println("List Sub: " + DaoSubject.SubList(id));
                    int subjet = Inputter.inputInt("Enter a part of Sub Code: ", 0, Integer.MAX_VALUE);
                    searchInt("a.subject_id", subjet, id, owner);
                    break;
                case 3:                    
                    int status = Inputter.inputInt("Enter Status(1-Active,2-Closed,3-Cancelled): ", 1, 3);
                    searchInt("a.status", status, id, owner);
                    break;
                default:
                    break;
            }
            if (option == 4) {
                return;
            }
        }
    }
    public void listByFilterForTrainer(int id, String owner) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nFilter by--------- ");
            System.out.println("1. Subject");
            System.out.println("2. Staus");
            System.out.println("3. Back to ClassList");
            
            System.out.print("Your option: ");
            
            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1:
                    System.out.println("List Sub: " + DaoSubject.SubList());
                    int subjet = Inputter.inputInt("Enter a part of Sub Code: ", 0, Integer.MAX_VALUE);
                    searchInt("a.subject_id", subjet, id, owner);
                    break;
                case 2:                    
                    int status = Inputter.inputInt("Enter Status(1-Active,2-Closed,3-Cancelled): ", 1, 3);
                    searchInt("a.status", status, id, owner);
                    break;
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }
    public void searchString(String field, String filter, int id, String owner) throws SQLException, ClassNotFoundException {
        
        int count = DaoClass.countClass(field, filter, id, owner);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoClass.showClassByPage(1, field, filter, id, owner);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoClass.showClassByPage(noPage, field, filter, id, owner);
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
                        DaoClass.showClassByPage(noPage, field, filter, id, owner);
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
                        DaoClass.showClassByPage(noPage, field, filter, id, owner);
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
    
    public void listFilterString(String msg, String field, int id, String owner) throws SQLException, ClassNotFoundException {
        String filter = Inputter.inputNonBlankStr(msg);
        
        int count = DaoClass.countClass(field, filter, id, owner);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoClass.showClassByPage(1, field, filter, id, owner);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoClass.showClassByPage(noPage, field, filter, id, "c.author_id");
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
                        DaoClass.showClassByPage(noPage, field, filter, id, owner);
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
                        DaoClass.showClassByPage(noPage, field, filter, id, owner);
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
    
    public void searchInt(String field, int filter, int id, String owner) throws SQLException, ClassNotFoundException {
        
        int count = DaoClass.countClass(field, String.valueOf(filter), id, owner);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoClass.showClassByPage(1, field, String.valueOf(filter), id, owner);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoClass.showClassByPage(noPage, field, String.valueOf(filter), id, owner);
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
                        DaoClass.showClassByPage(noPage, field, String.valueOf(filter), id, owner);
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
                        DaoClass.showClassByPage(noPage, field, String.valueOf(filter), id, owner);
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
    
    public void updateClass(int id) throws SQLException, ClassNotFoundException {
        int classId = Inputter.inputInt("Enter Class id to update: ", 0, Integer.MAX_VALUE);
        Class c1 = DaoClass.ProClassId(classId);
        while (c1 == null) {
            classId = Inputter.inputInt("This class isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoClass.ProClassId(classId);
        }
        String[] TypeStatus = {"Active", "Closed","Cancel"};
        String[] TypeBl5={"No","Yes"};
        System.out.printf("%-10s%-15s%-20s%-10s%-10s%-20s%-10s%-10s\n",
                "ID",
                "ClassCode",
                "TrainName",
                "SubCode",
                "Year",
                "Term",
                "Bl_5",
                "Status");
        System.out.println(c1);
        System.out.println("ID: "+String.valueOf(c1.getClass_id()));
        System.out.println("Class Code: "+c1.getClass_code());
        System.out.println("Subject Code: "+c1.getSubname());
        System.out.println("Year: "+String.valueOf(c1.getClass_year()));
        System.out.println("Term: "+c1.getTerm());
        System.out.println("Block 5: "+TypeBl5[c1.getBl5()-1]);
        System.out.println("Status: "+TypeBl5[c1.getStatus()-1]);
        String ClassCode = null;
        int op = Inputter.inputInt("Do u want to update Class Code(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            ClassCode = Inputter.inputPattern("Enter class code(IS000): ", "Wrong Class Code format! Please re-Enter: ", "[A-Za-z]{2}[\\d]{3}");
        }
        if (op == 2) {
            ClassCode = c1.getClass_code();
        }
        

        
        int SubId = 0;
        op = Inputter.inputInt("Do u want to update Subject Code(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("List Class: " + DaoSubject.SubList(id));
            SubId = Inputter.inputInt("Enter Subject Code: ", 0, Integer.MAX_VALUE);
            while (!DaoSubject.CheckSubjectID(SubId)) {
                SubId = Inputter.inputInt("This Subject doesn't exist,Re-Enter Subject Code: ", 0, Integer.MAX_VALUE);
            }
        }
        if (op == 2) {
            SubId = c1.getSub_id();
        }
        
                int TrainerId = 0;
        op = Inputter.inputInt("Do u want to update Trainer(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            System.out.println("List Trainer: " + DaoUser.RoleList(2));
            TrainerId = Inputter.inputInt("Enter trainer id: ", 0, Integer.MAX_VALUE);
            while (DaoUser.SearchRolebyId(TrainerId) != 2) {
                TrainerId = Inputter.inputInt("This ID isn't Trainer,Re-Enter: ", 0, Integer.MAX_VALUE);
            }
        }
        if (op == 2) {
            TrainerId = c1.getTrainer_id();
        }
        
        int year = 0;
        op = Inputter.inputInt("Do u want to update Year(1-Y,2-N): ", 1, 3);
        if (op == 1) {
            year = Inputter.inputInt("Enter Class Year: ", 2000, 3000);
        }
        if (op == 2) {
            year = c1.getClass_year();
        }
        
        String Term = null;
        op = Inputter.inputInt("Do u want to update Term(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            Term = Inputter.inputBlankStr("Enter Term of class: ");
        }
        if (op == 2) {
            Term = c1.getTerm();
        }
        
        int bl5 = 0;
        op = Inputter.inputInt("Do u want to update bl5(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            bl5 = Inputter.inputInt("Enter Block 5 check(2-No,1-Yes): ", 1, 2);
        }
        if (op == 2) {
            bl5 = c1.getBl5();
        }
        
        int status = c1.getStatus();
        Class c2 = new Class(classId, ClassCode, TrainerId, SubId, year, Term, bl5, status);
        DaoClass.UpdateClass(c2);
        
    }
    
    public void updateStatusClass(int st) throws SQLException, ClassNotFoundException {
        System.out.println("Opened Class: " + DaoClass.StatusOnlList());
        int Id = Inputter.inputInt("Enter Class ID need to change: ", 0, Integer.MAX_VALUE);
        while (!DaoClass.SearchCIDonl(Id)) {
            Id = Inputter.inputInt("This Class isn't exist or opened, re-enter: ", 0, Integer.MAX_VALUE);
        }
        DaoClass.UpdateStatus(st, Id);
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ClassController a = new ClassController();
        a.listByFilter(4, "c.author_id");
//        a.updateStatusClass(2);
    }
}
