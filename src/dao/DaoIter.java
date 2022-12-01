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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DaoIter {
    public static List<String> IterListByClass(int class_id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select c.iteration_id,c.iteration_name from subject as a left outer join class as b on a.subject_id = b.subject_id\n" +
                   "right outer join iteration as c on a.subject_id=c.subject_id where c.status = 1 and b.class_id = ? group by c.iteration_id ;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, class_id);

      ResultSet rs = pstm.executeQuery();

            
      
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.          
          list.add(rs.getInt(1)+"-"+rs.getString(2));
          
      }
            System.out.println("");
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
       return list;
    }
    public static List<String> IterList() throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select c.iteration_id,c.iteration_name,a.subject_code from subject as a left outer join class as b on a.subject_id = b.subject_id\n" +
                   "right outer join iteration as c on a.subject_id=c.subject_id where c.status = 1 group by c.iteration_id ;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);

      ResultSet rs = pstm.executeQuery();

            
      
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.          
          list.add(rs.getInt(1)+"-"+rs.getString(2));
          
      }
            System.out.println("");
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
       return list;
    }
    public static boolean checkIterID(int iterId) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from iteration where iteration_id = ? and status = 1";
       PreparedStatement pstm = connection.prepareStatement(sql); 
       pstm.setInt(1, iterId);
         
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
}
