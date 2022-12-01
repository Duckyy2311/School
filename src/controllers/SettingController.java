/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import connection.ConnectionUtils;
import dao.DaoSetting;
import view_inputs.Inputter;
import static java.nio.file.Files.delete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Setting;


/**
 *
 * @author AnhMinh
 */
public class SettingController extends Setting{
    
     
     public void UpdateSetting() throws SQLException, ClassNotFoundException{
            System.out.println("\nUpdating Setting--------- ");
            DaoSetting.settingDisplay();
            int op = 0;
            int ID=Inputter.inputInt("Input Setting ID you want to update: ",0,Integer.MAX_VALUE);
            while(DaoSetting.SearchSettingID(ID)==false){
                ID = Inputter.inputInt("Wrong Setting ID, reenter: ",0,Integer.MAX_VALUE);
            }
            Setting c1 = DaoSetting.checkSetting(ID);
                    
                    //updating Setting type ID
                    int newTypeID = 0;
                    op = Inputter.inputInt("Do u want to update Setting Type(1-Y,2-N): ", 1, 2);
                    if(op == 1){ 
                    newTypeID = Inputter.inputInt("input new TypeID(1-WP,2-Q&A,3-Task,4-Defect,5-Leakage): ",1,5);  
                    }
                    if(op == 2) newTypeID = c1.getTypeId();
                                      

                    //updating Setting title                
                    String newTitle = "";
                    op = Inputter.inputInt("Do u want to update Setting Title(1-Y,2-N): ", 1, 2);
                    if(op == 1){ 
                    newTitle = Inputter.inputNonBlankStr("input new Title: "); 
                    }
                    if(op == 2) newTitle = c1.getSettingTittle();
                     
                    
                    //updating Setting value
                    int newValue = 0;
                    op = Inputter.inputInt("Do u want to update Setting Value(1-Y,2-N): ", 1, 2);
                    if(op == 1){ 
                    newValue = Inputter.inputInt("input new Value(1-Zero,2-Low,3-Medium,4-High): ",1,4);    
                    }
                    if(op == 2) newValue = c1.getSettingValue();
                                            
                    
                    //updateting Display
                     String newDisplay = "";
                    op = Inputter.inputInt("Do u want to update Display order:(1-Y,2-N): ", 1, 2);
                    if(op == 1){ 
                    newDisplay = Inputter.inputNonBlankStr("input new Display order: ");
                    }
                    if(op == 2) newDisplay = c1.getDisOrder();
                     

                    //updating status
                    int newStatus = Inputter.inputInt("input new Status(0-inactive,1-actice): ", 0, 1);  
                    
             Setting b = new Setting(ID, newTypeID, newTitle, newValue, newDisplay, newStatus);       
                    DaoSetting.UpdateSetting(b);
           
        
              
                }
    
    public static void InactiveSetting() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection(); 
        String ms = "";
        String[] TypeStatus = {"inactive", "active"};
       
        System.out.println("\nStatus menu-----------");
                        System.out.println("1. inactivate");
                        System.out.println("2. activate");
                        int option = Inputter.inputInt("", 0, 3);
                        

            if(option==1){
                DaoSetting.DisplayByStatus(1);
                int ID=Inputter.inputInt("Input Setting ID you want to change Status into inactive: ",0,Integer.MAX_VALUE);
            while(DaoSetting.SearchSettingID(ID)==false){
                
                ID = Inputter.inputInt("Wrong Setting ID, reenter: ",0,Integer.MAX_VALUE);
                
            }
                DaoSetting.UpdateStatus(0, ID);
        
        
        
        
    }
            if(option==2){
            DaoSetting.DisplayByStatus(0);
            int ID= Inputter.inputInt("Input Setting ID you want to change Status into active: ",0,Integer.MAX_VALUE);
            while(DaoSetting.SearchSettingID(ID)==false){
                
                ID = Inputter.inputInt("Wrong Setting ID, reenter: ",0,Integer.MAX_VALUE);
                
            }
            DaoSetting.UpdateStatus(1, ID);
                     

      
         
    }
    

}
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SettingController b = new SettingController();
        b.UpdateSetting();
    }
}
