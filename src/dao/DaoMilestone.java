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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Milestone;
/**
 *
 * @author Admin
 */
public class DaoMilestone {
    public static int countMS(String field,String filter,int id){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from milestone as a left outer join iteration as b on a.iteration_id=b.iteration_id\n" +
                   "left outer join class as c  on a.class_id = c.class_id where "+field+" like ? and c.trainer_id = ?;";
      PreparedStatement pstm = connection.prepareStatement(sql);
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      pstm.setString(1,"%"+filter+"%");
      pstm.setInt(2, id);
//            System.out.println(""+pstm);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
      h = rs.getInt(1);
      }
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return h;
    }
       public static void DisplayByPage(int n,String field,String filter,int id) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql ="with t as(select row_number() over (order by a.milestone_id asc) as rn,\n" +
                  "a.milestone_id,"
                + "b.iteration_name,"
                + "c.class_code,"
                + "a.from_date,"
                + "a.to_date,"
                + "a.status \n" +
                  "from milestone as a left outer join iteration as b on a.iteration_id = b.iteration_id\n" +
                                      "left outer join class as c on a.class_id = c.class_id\n" +
                  "where "+field+" like ? and c.trainer_id = ?)\n" +
                  "select * from t where rn between ? and ?;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);

      pstm.setString(1,"%"+filter+"%");
      pstm.setInt(3, start);
      pstm.setInt(4, end);
      pstm.setInt(2, id);
      ResultSet rs = pstm.executeQuery();

            System.out.printf("%-7s%-12s%-10s%-15s%-15s%-15s\n",
                "ID",
                "ClassCode",
                "IterName",                
                "FromDate",
                "ToDate",
                "Status");
      
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          Milestone ms = new Milestone(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getInt(7));
          System.out.println(ms);
          
      }
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
       
    }
    public static void insertMilestone(Milestone ms) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection();
      Statement statement = connection.createStatement();
      SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
      String sql = "INSERT INTO studentproject.milestone\n" +
                   "(iteration_id,class_id,from_date,to_date,\n" +
                   "status) VALUES\n" +
                   "(?,?,?,?,?);";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
                
        pstm.setString(1, ms.getIter_name());
        
        pstm.setString(2, ms.getClass_code());
        
        pstm.setString(3, SDF.format(ms.getFrom()));
        
        pstm.setString(4, SDF.format(ms.getTo()));     
        
        pstm.setInt(5, ms.getStatus());
        int rs = pstm.executeUpdate();
        System.out.println("Insert Success!");
        connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }
    public static Milestone ProMileID(int id) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select a.milestone_id,"
                + "b.iteration_name,"
                + "c.class_code,"
                + "a.from_date,"
                + "a.to_date,"
                + "a.status,a.iteration_id,a.class_id \n" +
                  "from milestone as a left outer join iteration as b on a.iteration_id = b.iteration_id\n" +
                                      "left outer join class as c on a.class_id = c.class_id\n" +
                  "where a.milestone_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        Milestone ms = new Milestone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6),rs.getInt(7),rs.getInt(8));
                return ms;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
    public static boolean SearchMileIDonl(int mId) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();

      String sql = "Select * from milestone where milestone_id = ? and status = 0";
       PreparedStatement pstm = connection.prepareStatement(sql); 
       pstm.setInt(1, mId);
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
    
           public static void UpdateMile(Milestone ms) throws SQLException, ClassNotFoundException{
        
    try{
         Connection connection = ConnectionUtils.getMyConnection();
      Statement statement = connection.createStatement();
      SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
      String sql =  "UPDATE studentproject.milestone\n" +
                    "SET\n" +
                    "iteration_id = ?," +
                    "class_id = ?," +
                    "from_date = ?," +
                    "to_date = ?," +
                    "status = ?" +
                    " WHERE milestone_id = ?;";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        pstm.setInt(6, ms.getId());
        pstm.setInt(1, ms.getIter_id());
        pstm.setInt(2, ms.getClass_id());
        pstm.setString(3, SDF.format(ms.getFrom()));
        pstm.setString(4, SDF.format(ms.getTo()));
        pstm.setInt(5, ms.getStatus());
        System.out.println(pstm);
        int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }            
    }

           public static List<String> StatusOnlList() throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();
      String[] TypeStatus = {"Open", "Closed","Cancel"};
      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select milestone_id,status from milestone where status = 1;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);

      ResultSet rs = pstm.executeQuery();

            
      
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          
          
          list.add(rs.getInt(1)+"-"+TypeStatus[rs.getInt(2)-1]);
          
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
           public static void UpdateStatus(int i, int ID) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection(); 
        String sql = "update milestone\n"
              +"Set status = ? "
              +"WHERE milestone_id = ?;";
       
       PreparedStatement pstm = connection.prepareStatement(sql);       
        pstm.setInt(1, i);
        pstm.setInt(2, ID);
        
        

        int  rs = pstm.executeUpdate();
            System.out.println("status change successful");
    
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }
           
}
