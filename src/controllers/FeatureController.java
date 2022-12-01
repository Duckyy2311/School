/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import view_inputs.Inputter;
import connection.ConnectionUtils;

import dao.DaoFeature;

import java.sql.Connection;
import java.sql.SQLException;
import model.Feature;

/**
 *
 * @author Dinh Quoc Tung
 */
public class FeatureController {

    public void SearchFeatureList() throws SQLException, ClassNotFoundException {
        int auid = Inputter.inputInt("Enter a TeamId you want to search for: ", 0, Integer.MAX_VALUE);
        int count = DaoFeature.countFeatureByTeam(auid);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoFeature.TeamDisplayByPage(1, auid);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoFeature.TeamDisplayByPage(noPage, auid);
        }
    }

    public static void FeatureList() throws SQLException, ClassNotFoundException {
        FeatureController b = new FeatureController();
        b.ShowPagination();

        int option;
        while (true) {

            System.out.println("\nTeamList Menu--------- ");
            System.out.println("1. Update Feature");
            System.out.println("2.Add new Faeture");
            System.out.println("3. Active/deactivate Feature");
            System.out.println("4. Search Feature by Team:");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            FeatureController s = new FeatureController();
            option = Inputter.inputInt("", 0, 4);
            switch (option) {
                case 1:
                    s.UpdateFeature();
                    break;
                case 2:
                    s.AddFeature();
                    break;
                case 3:
                    s.InactiveFeature();
                    break;
                case 4:
                    s.SearchFeatureList();
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        FeatureController b = new FeatureController();
        b.AddFeature();
    }
    public void AddFeature() throws SQLException, ClassNotFoundException {
        System.out.println("\nAdd Feature--------- ");
        int ID = Inputter.inputInt("Input Feature ID you want to Add: ", 0, Integer.MAX_VALUE);
        while (DaoFeature.SearchFeatureID(ID) == true) {
            ID = Inputter.inputInt("this Feature ID is already exist, please try again: ", 0, Integer.MAX_VALUE);
        }
        DaoFeature.TeamDisplay();
        int newTeamid = Inputter.inputInt("input new TeamId: ", 0, Integer.MAX_VALUE);

        String newTofeatureName = Inputter.inputNonBlankStr("input new Feature_name: ");

        int newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);

        Feature b = new Feature(ID, newTeamid, newTofeatureName, newStatus);
        DaoFeature.AddFeature(b);

        int option;
        while (true) {

            System.out.println("-----------------------------------");
            System.out.println("1. Update another Setting");
            System.out.println("2. Deactivate status");
            System.out.println("3. Search Feature by TeamID");
            System.out.println("4. Add new Feature");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            FeatureController s = new FeatureController();
            option = Inputter.inputInt("", 0, 4);
            switch (option) {
                case 1:
                    s.UpdateFeature();

                    break;
                case 2:
                    s.InactiveFeature();
                    break;
                case 3:
                    s.SearchFeatureList();
                    break;
                case 4:
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

    public void UpdateFeature() throws SQLException, ClassNotFoundException {
        System.out.println("\nUpdating Feature--------- ");
        FeatureController w = new FeatureController();

        w.ShowPagination();
        int ID = Inputter.inputInt("Input Feature ID you want to update: ", 0, Integer.MAX_VALUE);
        while (DaoFeature.SearchFeatureID(ID) == false) {
            ID = Inputter.inputInt("Wrong Feature ID, reenter: ", 0, Integer.MAX_VALUE);
        }
        DaoFeature.TeamDisplay();
        int newTeamid = Inputter.inputInt("input new TeamId: ", 0, Integer.MAX_VALUE);

        String newFeaturename = Inputter.inputNonBlankStr("input new Feature_name: ");

        int newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);

        Feature b = new Feature(ID, newTeamid, newFeaturename, newStatus);
        DaoFeature.UpdateFeature(b);

        int option;
        while (true) {

            System.out.println("-----------------------------------");
            System.out.println("1. Update another Setting");
            System.out.println("2. Active/Deactivate status");
            System.out.println("3. Search Feature by Team");
            System.out.println("4. Add a new Feature");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            FeatureController s = new FeatureController();
            option = Inputter.inputInt("", 0, 4);
            switch (option) {
                case 1:
                    s.UpdateFeature();
                    ;
                    break;
                case 2:
                    s.InactiveFeature();
                    break;
                case 3:
                    s.SearchFeatureList();
                    break;
                case 4:
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

    private void InactiveFeature() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection();
        String ms = "";
        String[] TypeStatus = {"inactive", "active"};

        System.out.println("\nStatus menu-----------");

        System.out.println("1. inactivate");
        System.out.println("2. activate");
        int option = Inputter.inputInt("", 0, 3);

        if (option == 1) {
            FeatureController.AcStatusPagination();
            int ID = Inputter.inputInt("Input Feature ID you want to change Status into inactive: ", 0, Integer.MAX_VALUE);
            while (DaoFeature.SearchFeatureID(ID) == false) {

                ID = Inputter.inputInt("Wrong Feature ID, reenter: ", 0, Integer.MAX_VALUE);

            }
            DaoFeature.UpdateStatus(0, ID);

        }
        if (option == 2) {
            FeatureController.InStatusPagination();
            int ID = Inputter.inputInt("Input Feature ID you want to change Status into active: ", 0, Integer.MAX_VALUE);
            while (DaoFeature.SearchFeatureID(ID) == false) {

                ID = Inputter.inputInt("Wrong Feature ID, reenter: ", 0, Integer.MAX_VALUE);

            }
            DaoFeature.UpdateStatus(1, ID);

        }
        int opt;
        while (true) {

            System.out.println("-----------------------------------");
            System.out.println("1. Update  Feature");
            System.out.println("2. Update another Status");
            System.out.println("3. Add a new Feature");
            System.out.println("0. Exit to main menu");
            System.out.print("Your option: ");
            FeatureController s = new FeatureController();
            opt = Inputter.inputInt("", 0, 3);
            switch (opt) {
                case 1:
                    s.UpdateFeature();
                    ;
                    break;
                case 2:
                    s.InactiveFeature();
                    break;
                case 3:
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

    public static void ShowPagination() throws SQLException, ClassNotFoundException {
        int count = DaoFeature.countFeature();
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoFeature.FeatureDisplayByPage(1, "");
        if (countPage <= 1) {
            return;
        }
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to " + countPage + "'(0-exit)(): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoFeature.FeatureDisplayByPage(noPage, "");
        }

    }

    public static void AcStatusPagination() throws SQLException, ClassNotFoundException {
        int count = DaoFeature.countAcStatus();
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoFeature.StatusDisplayByPage(1, 1);
        if (countPage <= 1) {
            return;
        }
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 2 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoFeature.StatusDisplayByPage(noPage, 1);
        }

    }

    public static void InStatusPagination() throws SQLException, ClassNotFoundException {
        int count = DaoFeature.countInStatus();
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoFeature.StatusDisplayByPage(1, 0);
        if (countPage <= 1) {
            return;
        }
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoFeature.StatusDisplayByPage(noPage, 0);
        }

    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        FeatureController.ShowPagination();
//    }
}
