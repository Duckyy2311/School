/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_inputs;

import controllers.IterationController ;
import controllers.SubjecController;
import controllers.CriteriaController;
import controllers.ClassController;
import controllers.SettingController;
import controllers.SubjectSettingController;
import controllers.UserController;
import dao.DaoSetting;
import dao.DaoSubjectSetting;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class AdminMenu {
    public static void viewAdmin(int id) throws SQLException, ClassNotFoundException{
        int option;
                                while(true){
                                    System.out.println("\nDashBoard----Admin----- ");
                                    System.out.println("1. SettingList");
                                    System.out.println("2. User List");
                                    System.out.println("3. Subject List");
                                    System.out.println("4. Subject Setting List");
                                    System.out.println("5. Iteration");
                                    System.out.println("6. Criteria List");
                                    System.out.println("7. Back to home");
                                    
                                    System.out.print("Your option: ");
                        

                                option = Inputter.inputInt("",1,7);



                                switch(option){
                                    case 1: listSetting(); break;
                                    case 2: listUser(); break;
                                    case 3: SubjecController.SubjectList();break;
                                    case 4: subjectSettingList(id);break;
                                    case 5: listIteration(); break;
                                    case 6: listCriteria();break;
                                    default: break;
                                }
                                if(option==7)
                                    break;
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
                case 1:s.addSubjectSetting(id);
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
    public static void listSetting() throws SQLException, ClassNotFoundException {
      DaoSetting.settingDisplay();

        int option;
         while(true){

            System.out.println("\nSettingList Menu--------- ");
            System.out.println("1. Update Setting");
            System.out.println("2. active/inactive Setting");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            SettingController s = new SettingController();
            option = Inputter.inputInt("",0,3);
            switch(option){
                case 1: s.UpdateSetting();; break;             
                case 2: s.InactiveSetting(); break; 
                case 3: ; break;
                case 0: System.out.println("Bye!"); return;
                default: System.out.println("Again!"); break;
                
            }
        }        
    }
    public static void listUser() throws SQLException, ClassNotFoundException{
                int option;
                UserController userControl = new UserController();
                userControl.showPagination();
                                while(true){
                                    System.out.println("\nUserList--------- ");
                                    System.out.println("1. Show List");
                                    System.out.println("2. User List by Roll filter");
                                    System.out.println("3. Update User");
                                    System.out.println("4. Active User");
                                    System.out.println("5. Inactive User");
                                    System.out.println("6. Back to Dashboard");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",1,6);
                                switch(option){
                                    case 1: userControl.showPagination(); break;
                                    case 2: userControl.searchByRollNumber(); break;
                                    case 3: userControl.UpdateUser();break;
                                    case 4: userControl.ActiveUser();break;
                                    case 5: userControl.InActiveUser();
                                    case 6: break;
                                    default: break;
                                }
                                if(option==6)
                                    return;
    }
    }


     public static void listIteration() throws SQLException, ClassNotFoundException{
        int option;
        IterationController iterControl = new IterationController();
        while(true){
                                    System.out.println("\nIterationList--------- ");
                                    System.out.println("1. Show Iteration List");
                                    System.out.println("2. Search");
                                    System.out.println("3. Iteration Detail");
                                    System.out.println("4. Add Iteration");
                                    System.out.println("5. Activate Iteration");
                                    System.out.println("6. Deactivate Iteration");
                                    System.out.println("7. Back to Dashboard");
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",0,7);
                                switch(option){
                                    case 1: iterControl.ShowPagination(); break;
                                    case 2: iterControl.listFillter();break;
                                    case 3: iterControl.IterationDetail();break;
                                    case 4: iterControl.AddIteration();break;
                                    case 5: iterControl.ACStatusIteration(1);break;
                                    case 6: iterControl.IACStatusIteration(0);break;
                                    default: break;
                                }
                                if(option==7)
                                    return;
    }
    }
      public static void listCriteria() throws SQLException, ClassNotFoundException{
        int option;
        CriteriaController criterControl = new CriteriaController();
        while(true){
                                    System.out.println("\nCriteriaList--------- ");
                                    System.out.println("1. Show Criteria List");
                                    System.out.println("2. Search");
                                    System.out.println("3. Criteria Detail");
                                    System.out.println("4. Add Criteria");
                                    System.out.println("5. Active Criteria");
                                    System.out.println("6. Deactive Criteria");
                                    
                                    System.out.println("7.Back to Dashboard");
                                    
                                    System.out.print("Your option: ");
                        
                                option = Inputter.inputInt("",0,7);
                                switch(option){
                                    case 1: criterControl.ShowPagination(); break;
                                    case 2: criterControl.Search();break;
                                    case 3: criterControl.CriteriaDetail();break;
                                    case 4: criterControl.AddCriteria();break;
                                    case 5: criterControl.ACStatusClass(1);break;
                                    case 6: criterControl.IACStatusClass(0);break;
                                    default: break;
                                }
                                if(option==7)
                                    return;
    }
    }
}