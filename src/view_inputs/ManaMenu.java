/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_inputs;

import controllers.ClassController;
import controllers.CriteriaController;
import controllers.IterationController;
import controllers.SubjectSettingController;
import dao.DaoSubjectSetting;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ManaMenu {

    public static void viewMana(int id) throws SQLException, ClassNotFoundException {
        int option;

        while (true) {
            System.out.println("\nDashBoard----Author----- ");
            System.out.println("1. Subject Setting List");
            System.out.println("2. Iteration List");
            System.out.println("3. Criteria List");
            System.out.println("4. Class List");
            System.out.println("5. Back to home");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 6);
            switch (option) {
                case 1:
                    subjectSettingList(id);
                    break;
                case 2:
                    listIteration();
                    break;
                case 3:
                    listCriteria();
                    break;
                case 4:
                    listClass(id);
                    break;
                default:
                    break;
            }
            if (option == 5) {
                break;
            }
        }
    }

    public static void listClass(int id) throws SQLException, ClassNotFoundException {
        int option;
        ClassController classControl = new ClassController();
        classControl.showPagination(id, "c.author_id");
        while (true) {
            System.out.println("\nClassList--------- ");
            System.out.println("1. Show Class List");
            System.out.println("2. Search");
            System.out.println("3. List by Filter");
            System.out.println("4. Update Class");
            System.out.println("5. Add Class");
            System.out.println("6. Close Class");
            System.out.println("7. Cancel Class");
            System.out.println("8. Back to Dashboard");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 8);
            switch (option) {
                case 1:
                    classControl.showPagination(id, "c.author_id");
                    break;
                case 2:
                    classControl.search(id, "c.author_id");
                    break;
                case 3:
                    classControl.listByFilter(id, "c.author_id");
                    break;
                case 4:
                    classControl.updateClass(id);
                    break;
                case 5:
                    classControl.addClass(id);
                    break;
                case 6:
                    classControl.updateStatusClass(2);
                    break;
                case 7:
                    classControl.updateStatusClass(3);
                    break;
                default:
                    break;
            }
            if (option == 7) {
                return;
            }
        }
    }

    public static void subjectSettingList(int id) throws SQLException, ClassNotFoundException {
        DaoSubjectSetting.settingDisplay(id);

        int option;
        while (true) {

            System.out.println("\nSubjectSettingList Menu--------- ");
            System.out.println("1. Add Subject_Setting");
            System.out.println("2. Update Subject_Setting");
            System.out.println("3. active/inactive Subject_Setting");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            SubjectSettingController s = new SubjectSettingController();
            option = Inputter.inputInt("", 0, 3);
            switch (option) {
                case 1:
                    s.addSubjectSetting(id);
                    break;
                case 2:
                    s.UpdateSubjectSetting(id);
                    break;
                case 3:
                    s.InactiveSubjectSetting(id);
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

    public static void listIteration() throws SQLException, ClassNotFoundException {
        int option;
        IterationController iterControl = new IterationController();
        while (true) {
            System.out.println("\nIterationList--------- ");
            System.out.println("1. Show Iteration List");
            System.out.println("2. Search");
            System.out.println("3. Iteration Detail");
            System.out.println("4. Add Iteration");
            System.out.println("5. Activate Iteration");
            System.out.println("6. Deactivate Iteration");
            System.out.println("7. Back to Dashboard");
            System.out.print("Your option: ");

            option = Inputter.inputInt("", 0, 7);
            switch (option) {
                case 1:
                    iterControl.ShowPagination();
                    break;
                case 2:
                    iterControl.listFillter();
                    break;
                case 3:
                    iterControl.IterationDetail();
                    break;
                case 4:
                    iterControl.AddIteration();
                    break;
                case 5:
                    iterControl.ACStatusIteration(1);
                    break;
                case 6:
                    iterControl.IACStatusIteration(0);
                    break;
                default:
                    break;
            }
            if (option == 7) {
                return;
            }
        }
    }

    public static void listCriteria() throws SQLException, ClassNotFoundException {
        int option;
        CriteriaController criterControl = new CriteriaController();
        while (true) {
            System.out.println("\nCriteriaList--------- ");
            System.out.println("1. Show Criteria List");
            System.out.println("2. Search");
            System.out.println("3. Criteria Detail");
            System.out.println("4. Add Criteria");
            System.out.println("5. Active Criteria");
            System.out.println("6. Deactive Criteria");

            System.out.println("7.Back to Dashboard");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 0, 7);
            switch (option) {
                case 1:
                    criterControl.ShowPagination();
                    break;
                case 2:
                    criterControl.Search();
                    break;
                case 3:
                    criterControl.CriteriaDetail();
                    break;
                case 4:
                    criterControl.AddCriteria();
                    break;
                case 5:
                    criterControl.ACStatusClass(1);
                    break;
                case 6:
                    criterControl.IACStatusClass(0);
                    break;
                default:
                    break;
            }
            if (option == 7) {
                return;
            }
        }
    }
}
