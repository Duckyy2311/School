package controllers;

import view_inputs.Inputter;
import connection.ConnectionUtils;
import dao.DaoSubject;
import dao.DaoSubjectSetting;
import java.sql.Connection;
import java.sql.SQLException;
import model.Subject;
import model.SubjectSetting;

/**
 *
 * @author Dinh Quoc Tung
 */
public class SubjectSettingController {
 public void addSubjectSetting(int id) throws SQLException, ClassNotFoundException{

                System.out.println("\nAdd Subject Setting--------- ");
                System.out.println("\nSubject List"+DaoSubjectSetting.subjectList(id));
                  int newSubjectID = Inputter.inputInt("Input Subject ID  you want to add: ",0,Integer.MAX_VALUE);                   
                while(DaoSubject.SearchSubjectID(newSubjectID)==false){
                newSubjectID = Inputter.inputInt("This Subject is not exist reenter: ",0,Integer.MAX_VALUE);                   
                }
                
                    int newTypeID = Inputter.inputInt("input TypeID(1-Grade,2-Complexity,3-Quality,4-Defect,5-Leakage): ", 1, 5);        
                    
                    String newTitle = Inputter.inputNonBlankStr("input new Title: ");
                    int newValue = Inputter.inputInt("input new Value(1-Zero,2-Low,3-Medium,4-High): ", 1, 4);
                    String newDisplay = Inputter.inputNonBlankStr("input new Display order: ");
                    int newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);
                    
             SubjectSetting b = new SubjectSetting(newSubjectID,newTypeID,newTitle,newValue, newDisplay, newStatus);       
                    DaoSubjectSetting.addSubjectSetting(b);
                    
                    }
    

    public void UpdateSubjectSetting(int id) throws SQLException, ClassNotFoundException {
        System.out.println("\nUpdating Subject--------- ");
        DaoSubjectSetting.settingDisplay(id);int op =0;
        int ID = Inputter.inputInt("Input Setting ID you want to update: ", 0, Integer.MAX_VALUE);
        while (DaoSubjectSetting.SearchSubjectSettingID(ID) == false) {
            ID = Inputter.inputInt("Wrong Setting ID, reenter: ", 0, Integer.MAX_VALUE);
        }
        SubjectSetting c1 =  DaoSubjectSetting.checkSubjectSettingID(ID,id);
        
        int newSubjectID = c1.getSubject_id();
        
        //updating Setting type ID
        int newTypeID = 0;
        op = Inputter.inputInt("Do u want to update Setting type(1-Y,2-N): ", 1, 2);
        if(op == 1) newTypeID = Inputter.inputInt("input new TypeID(1-Grade,2-Complexity,3-Quality,4-Defect,5-Leakage): ", 1, 5);
        if(op == 2) newTypeID = c1.getType_id();
         

        //updating Setting title   
        String newTitle = "";
        op = Inputter.inputInt("Do u want to update Setting title(1-Y,2-N): ", 1, 2);
        if(op == 1) newTitle = Inputter.inputNonBlankStr("input new Title: ");
        if(op == 2) newTitle = c1.getSetting_tittle();
         

        //updating Setting value
        int newValue = 0;
        op = Inputter.inputInt("Do u want to update Setting Value(1-Y,2-N): ", 1, 2);
        if(op == 1) newValue = Inputter.inputInt("input new Value(1-Zero,2-Low,3-Medium,4-High): ", 1, 4);
        if(op == 2) newValue = c1.getSetting_value();
         

        //updateting Display
        String newDisplay = "";
        op = Inputter.inputInt("Do u want to update Setting Display(1-Y,2-N): ", 1, 2);
        if(op == 1) newDisplay = Inputter.inputNonBlankStr("input new Display order: ");
        if(op == 2) newDisplay = c1.getDisplay_order();
         

        //updating status
        int newStatus = 0;
        op = Inputter.inputInt("Do u want to update  Status(1-Y,2-N): ", 1, 2);
        if(op == 1) newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);
        if(op == 2) newValue = c1.getSetting_value();
         

        SubjectSetting b = new SubjectSetting(ID, newSubjectID, newTypeID, newTitle, newValue, newDisplay, newStatus);
        DaoSubjectSetting.UpdateSubjectSetting(b);

       
    }

    public void InactiveSubjectSetting(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection();
        String ms = "";
        String[] TypeStatus = {"inactive", "active"};

        System.out.println("\nStatus menu-----------");
        System.out.println("1. inactivate");
        System.out.println("2. activate");
        int option = Inputter.inputInt("", 0, 3);

        if (option == 1) {
            DaoSubjectSetting.DisplayByStatus(1, id);
            int ID = Inputter.inputInt("Input Subject ID you want to change Status into inactive: ", 0, Integer.MAX_VALUE);
            while (DaoSubjectSetting.SearchSubjectSettingID(ID) == false) {

                ID = Inputter.inputInt("Wrong Setting ID, reenter: ", 0, Integer.MAX_VALUE);

            }
            DaoSubjectSetting.UpdateStatus(0, ID);

        }
        if (option == 2) {
            DaoSubjectSetting.DisplayByStatus(0,id);
            int ID = Inputter.inputInt("Input Subject ID you want to change Status into active: ", 0, Integer.MAX_VALUE);
            while (DaoSubjectSetting.SearchSubjectSettingID(ID) == false) {

                ID = Inputter.inputInt("Wrong Subject ID, reenter: ", 0, Integer.MAX_VALUE);

            }
            DaoSubjectSetting.UpdateStatus(1, ID);

        }
       
    }

    public static void ShowPagination() throws SQLException, ClassNotFoundException {
        int count = DaoSubject.countSubject();
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;

        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit)(): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoSubject.SubjectDisplayByPage(noPage, "");
        }

    }

    public static void AcStatusPagination() throws SQLException, ClassNotFoundException {
        int count = DaoSubject.countAcStatus();
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;

        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoSubject.StatusDisplayByPage(noPage, 1);
        }

    }

    public static void InStatusPagination() throws SQLException, ClassNotFoundException {
        int count = DaoSubjectSetting.countInStatus();
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;

        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoSubject.StatusDisplayByPage(noPage, 0);
        }

    }

}
