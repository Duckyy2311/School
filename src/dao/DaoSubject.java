/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import controllers.SubjecController;
import view_inputs.Inputter;
import connection.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Subject;
/**
 *
 * @author AnhMinh
 */
public class DaoSubject {
     @SuppressWarnings("empty-statement")
     
     public static void AuthorDisplay() throws SQLException, ClassNotFoundException{
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "Select user_id, full_name from user";

      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      ResultSet rs = statement.executeQuery(sql);
      Subject w = new Subject();
        System.out.printf("%-20s%-20s\n", 
                "AuthorID","Name");

      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setAuthId(rs.getInt(1)) ;
          
          System.out.printf("%-20s%-20s\n",
                  w.getAuthId(), rs.getString(2));
          
      }
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        
    }
     
    public static boolean SearchSubjectID(int SettingId) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from subject where subject_id = ? ";
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
    public static boolean CheckSubjectID(int SettingId) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from subject where subject_id = ? and status = 1";
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
    public static void UpdateSubject(Subject b) throws SQLException, ClassNotFoundException{
        
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "Update subject \n"
              +"Set subject_code = ?"
              +",subject_name = ?" 
              +",author_id = ?"
              +",status = ?\n"
              +"WHERE subject_id = ?;";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setString(1, b.getSubCode());
        
        pstm.setString(2, b.getSubName());
        
        pstm.setInt(3, b.getAuthId());     
        
        pstm.setInt(4, b.getStatus());     
        
        pstm.setInt(5, b.getSubId());
        
        int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
            
    }
    public static void AddSubject(Subject b) throws SQLException, ClassNotFoundException{
        
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "insert into subject \n"
              +"value ( ?"+",?" +", ?"+",?"+",?);";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setString(1, b.getSubCode());
        
        pstm.setString(2, b.getSubName());
        
        pstm.setInt(3, b.getAuthId());     
        
        pstm.setInt(4, b.getStatus());     
        
        pstm.setInt(5, b.getSubId());
        
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
       String ms = "Select subject_id, status from subject"
                        + "\nwhere status = ? ; ";
                        PreparedStatement pstm = connection.prepareStatement(ms);
                        pstm.setInt(1, i);
                        ResultSet rs = pstm.executeQuery();
                        Subject w = new Subject();
      
                    System.out.printf("%-10s%-10s\n",
                             "ID","Status");
                   while (rs.next()) {
                       w.setSubId(rs.getInt(1)) ;  
                       w.setStatus(rs.getInt(2));

                      System.out.printf("%-10s%-10s\n",
                             w.getSubId(),TypeStatus[w.getStatus()]);
                      
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
        String sql = "update subject\n"
              +"Set status = ? "
              +"WHERE subject_id = ?;";
       
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
    public static void SubjectDisplayByPage(int n, String Subname) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with x as(select ROW_NUMBER() over (order by subject_id asc) as ss, "
              + "subject_code,"
              + "subject_name,"
              + "author_id,"
              + "status "
              + " from studentproject.subject where subject_id like ? ) \n"
              + "select * from x where ss between ? and ? ;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+Subname+"%");
      pstm.setInt(2, start);
      pstm.setInt(3, end);

      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-10s%-20s%-20s%-20s%-20s\n",
                "SubjectID",
                "Subject Code",
                "Subject Name",
                "AuthorID",
                "Status");
            Subject w = new Subject();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setSubId(rs.getInt(1)) ;
          w.setSubCode(rs.getString(2)) ;
          w.setSubName(rs.getString(3));;
          w.setAuthId(rs.getInt(4)) ;
          w.setStatus(rs.getInt(5));
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DaoSubject.AuthorDisplay();
    }
    public static void AuthorDisplayByPage(int n, int AuthorId) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with x as(select ROW_NUMBER() over (order by subject_id asc) as ss, "
              + "subject_code,"
              + "subject_name,"
              + "author_id,"
              + "status "
              + " from studentproject.subject where author_id like ? ) \n"
              + "select * from x where ss between ? and ? ;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, AuthorId);
      pstm.setInt(2, start);
      pstm.setInt(3, end);
      System.out.println(""+pstm);
      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-10s%-20s%-20s%-20s%-20s\n",
                "SubjectID",
                "Subject Code",
                "Subject Name",
                "AuthorID",
                "Status");
            Subject w = new Subject();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setSubId(rs.getInt(1)) ;
          w.setSubCode(rs.getString(2)) ;
          w.setSubName(rs.getString(3));;
          w.setAuthId(rs.getInt(4)) ;
          w.setStatus(rs.getInt(5));
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
    public static int countSubjectByAuthorID(int ID){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.subject where author_id = ?;";
      PreparedStatement pstm = connection.prepareStatement(sql);
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      pstm.setInt(1,ID);
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
    public static int countSubject(){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.subject;";

      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
      h=rs.getInt(1);   
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
   
     public static void StatusDisplayByPage(int n, int status) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with x as(select ROW_NUMBER() over (order by status asc) as ss, "
              + "subject_code,"
              + "subject_name,"
              + "author_id,"
              + "status "
              + " from studentproject.subject where status like ? ) \n"
              + "select * from x where ss between ? and ? ;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1,status);
      pstm.setInt(2, start);
      pstm.setInt(3, end);
      System.out.println(""+pstm);
      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-10s%-20s%-20s%-20s%-20s\n",
                "SubjectID",
                "Subject Code",
                "Subject Name",
                "AuthorID",
                "Status");
            Subject w = new Subject();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setSubId(rs.getInt(1)) ;
          w.setSubCode(rs.getString(2)) ;
          w.setSubName(rs.getString(3));;
          w.setAuthId(rs.getInt(4)) ;
          w.setStatus(rs.getInt(5));
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
    
     
    
    public static int countAcStatus(){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.subject where status = ?;";

      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1,1);
      System.out.println(""+pstm);
      ResultSet rs = pstm.executeQuery();
//      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
      h=rs.getInt(1);   
      }

      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return h;
    }
   
    public static int countInStatus(){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.subject where status = ?;";

      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1,0);
      System.out.println(""+pstm);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
      h=rs.getInt(1);   
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
    public static List<String> SubList(int id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select subject_id,subject_code from subject where status = 1 and author_id = ?;";
      
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
    public static List<String> SubList() throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select subject_id,subject_code from subject where status = 1;";
      
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
}
