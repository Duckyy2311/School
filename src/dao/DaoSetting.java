/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionUtils;
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
public class DaoSetting {
    public static void settingDisplay() throws SQLException, ClassNotFoundException{
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "Select * from setting";

      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      ResultSet rs = statement.executeQuery(sql);
      Setting w = new Setting();
        System.out.printf("%-10s%-15s%-20s%-10s%-20s%-10s\n",
                "ID",
                "Setting Type",
                "Name",
                "Order",
                "Value",
                "Status");
      // Duyệt trên kết quả trả về."D:\sinh vien fpt\ISP392\ISP_Project\src\Controller\SettingList.java"
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setSettingId(rs.getInt(1)) ;
          w.setTypeId(rs.getInt(2)) ;
          w.setSettingTittle(rs.getString(3));;
          w.setSettingValue(rs.getInt(4)) ;
          w.setDisOrder(rs.getString(5));
          w.setStatus(rs.getInt(6));
          
          System.out.println(w);
          
      }
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        
    }
    public static Setting checkSetting(int id) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select * from setting \n" +
                    "where setting_id = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        Setting c = new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
                return c;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
    public static boolean SearchSettingID(int SettingId) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from setting where setting_id = ? ";
       PreparedStatement pstm = connection.prepareStatement(sql); 
       pstm.setInt(1, SettingId);
         
      ResultSet rs = pstm.executeQuery();
      if(rs.next()==true){
          connection.close();
      return true;}          
      else
      {connection.close();
      
      return false;}
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return false;
     }
    
    public static void UpdateSetting(Setting b) throws SQLException, ClassNotFoundException{
        
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "Update setting \n"
              +"Set type_id = ?"
              +",setting_title = ?"
              +",setting_value = ?" 
              +",display_order = ?"
              +",status = ?\n"
              +"WHERE setting_id = ?;";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setInt(1, b.getTypeId());
        
        pstm.setString(2, b.getSettingTittle());
        
        pstm.setInt(3, b.getSettingValue() );
        
        pstm.setString(4, b.getDisOrder());
        
        pstm.setInt(5, b.getStatus());     
        
        pstm.setInt(6, b.getSettingId());
        
        int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
            
    }
    public static void DisplayByStatus(int i) throws SQLException, ClassNotFoundException{
        try{
    Connection connection = ConnectionUtils.getMyConnection(); 
    String[] TypeStatus = {"inactive", "active"};
       String ms = "Select setting_id, status from setting"
                        + "\nwhere status = ? ; ";
                        PreparedStatement pstm = connection.prepareStatement(ms);
                        pstm.setInt(1, i);
                        ResultSet rs = pstm.executeQuery();
                        Setting w = new Setting();
      
                    System.out.printf("%-10s%-10s\n",
                             "ID","Status");
                   while (rs.next()) {
                       w.setSettingId(rs.getInt(1)) ;  
                       w.setStatus(rs.getInt(2));

                      System.out.printf("%-10s%-10s\n",
                             w.getSettingId(),TypeStatus[w.getStatus()]);
                      
    }
                   connection.close();
                   } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
}
    public static void UpdateStatus(int i, int ID) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection(); 
        String sql = "update setting\n"
              +"Set status = ? "
              +"WHERE setting_id = ?;";
       
       PreparedStatement pstm = connection.prepareStatement(sql);       
        pstm.setInt(1, i);
        pstm.setInt(2, ID);
        
        
        System.out.println(pstm);
        int  rr = pstm.executeUpdate();
            System.out.println("status change successful");
    
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
}
}
