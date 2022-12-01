/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import connection.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Class;

/**
 *
 * @author Admin
 */
public class DaoClass {
       public static void insertClass(Class cl) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection();
      Statement statement = connection.createStatement();

      String sql = "INSERT INTO studentproject.class\n" +
    "(class_code,trainer_id,subject_id,class_year,class_term,block5_class,status)\n" +
    "VALUES (?,?,?,?,?,?,?);";


        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, cl.getClass_code());

        pstm.setInt(2, cl.getTrainer_id());

        pstm.setInt(3, cl.getSub_id());

        pstm.setInt(4, cl.getClass_year());

        pstm.setString(5, cl.getTerm());

        pstm.setInt(6, cl.getBl5());

        pstm.setInt(7, cl.getStatus());

        int rs = pstm.executeUpdate();
        System.out.println("Insert Success!");
        connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }
       public static int countClass(String field,String filter,int id,String owner){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from class as a left outer join user as b on a.trainer_id = b.user_id\n" +
"						 left outer join subject as c on a.subject_id = c.subject_id where "+owner+" = ? and "+field+" like ?;";
      PreparedStatement pstm = connection.prepareStatement(sql);
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      pstm.setString(2,"%"+filter+"%");
      pstm.setInt(1, id);

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
       public static void showClassByPage(int n,String field,String filter,int id,String owner) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with t as(select row_number() over (order by a.class_id asc) as rn,\n" +
                "a.class_id,"
              + "a.class_code,"
              + "b.full_name,"
              + "c.subject_code,"
              + "a.class_year,"
              + "a.class_term,"
              + "a.block5_class,"
              + "a.status from class as a \n" +
                "left outer join user as b on a.trainer_id = b.user_id\n" +
                "left outer join subject as c on a.subject_id = c.subject_id "
              + "where "+field+" like ? and "+owner+" = ?)\n" +
                "select * from t where rn between ? and ?;";

      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.

      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+filter+"%");
      pstm.setInt(3, start);
      pstm.setInt(4, end);
      pstm.setInt(2, id);
           
      ResultSet rs = pstm.executeQuery();
      Class c = new Class();
            System.out.printf("%-10s%-15s%-20s%-10s%-10s%-20s%-10s%-10s\n",
                "ID",
                "Class Code",
                "Trainer",
                "SubCode",
                "Year",
                "Term",
                "Bl5",
                "Status");

      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          c = new Class(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
          System.out.println(c);

      }
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }
       public static Class ProClassId(int id) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select a.class_id,a.class_code,b.full_name,c.subject_code,a.class_year,a.class_term,a.block5_class,a.status,a.trainer_id,a.subject_id \n" +
                    "from class as a \n" +
                    "left outer join user as b on a.trainer_id = b.user_id\n" +
                    "left outer join subject as c on a.subject_id = c.subject_id \n" +
                    "where a.class_id = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        Class c = new Class(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8),rs.getInt(9),rs.getInt(10));
                return c;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
       public static void UpdateClass(Class c) throws SQLException, ClassNotFoundException{

    try{
         Connection connection = ConnectionUtils.getMyConnection();
      Statement statement = connection.createStatement();

      String sql = "UPDATE studentproject.class\n" +
                    "SET " +
                    "class_code = ?," +
                    "trainer_id = ?," +
                    "subject_id = ?," +
                    "class_year = ?," +
                    "class_term = ?," +
                    "block5_class = ?," +
                    "status = ?\n" +
                    "WHERE class_id = ?;";


        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(8, c.getClass_id());
        pstm.setString(1, c.getClass_code());
        pstm.setInt(2, c.getTrainer_id());
        pstm.setInt(3, c.getSub_id());
        pstm.setInt(4, c.getClass_year());
        pstm.setString(5, c.getTerm());
        pstm.setInt(6, c.getBl5());
        pstm.setInt(7, c.getStatus());

        int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }
       public static void UpdateStatus(int i, int ID) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection();
        String sql = "update class\n"
              +"Set status = ? "
              +"WHERE class_id = ?;";

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

    public static List<String> StatusOnlList(int id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select class_id,class_code from class where status = 1 and trainer_id = ?;";

      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.

      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, id);
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
        public static List<String> StatusOnlList() throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select class_id,class_code from class where status = 1;";

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
    public static boolean SearchCIDonl(int CId) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();

      String sql = "Select * from class where class_id = ? and status = 1";
       PreparedStatement pstm = connection.prepareStatement(sql);
       pstm.setInt(1, CId);
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

    public static boolean CheckClID(int classId) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
      String sql = "Select * from class where class_id = ? and status = 1";
       PreparedStatement pstm = connection.prepareStatement(sql);
       pstm.setInt(1, classId);

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
