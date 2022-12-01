




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
import model.ClassUser;
import model.Class;
import model.Team;
import model.User;

/**
 *
 * @author AnhMinh
 */
public class DaoClassUser {
    
    
     public static List<String> ClassOnlList() throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.class_id, b.class_code from class_user as a join class as b on a.class_id = b.class_id where a.status = 1 group by a.class_id;";
      
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
     
     public static List<String> TeamOnlList(int class_id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.team_id, b.topic_name from class_user as a join team as b on a.team_id = b.team_id join class as c on a.class_id = c.class_id where c.class_id =? and a.status = 1 group by a.team_id;";
      
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
     public static List<String> UserOnlList(int tid,int class_id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.user_id, b.full_name from class_user as a join user as b on a.user_id = b.user_id join team as c on a.team_id = c.team_id where a.team_id =? and a.class_id = ? and a.status = 1 group by a.user_id;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, tid);
      pstm.setInt(2, class_id);
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
    
    public static boolean SearchUserID( int Id) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from class_user where  user_id = ? ; ";
       PreparedStatement pstm = connection.prepareStatement(sql); 

       pstm.setInt(1, Id);
         
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
    public static boolean searchByID(String finder, int Id) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from class_user where  "+finder+" = ? ; ";
       PreparedStatement pstm = connection.prepareStatement(sql); 

       pstm.setInt(1, Id);
         
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
    
    public static void AddClassUser(ClassUser b) throws SQLException, ClassNotFoundException{
         SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "insert into class_user \n"
              +"value ( ?"+",?" +", ?"+",?"+",? "+",?"+", ?"+",?"+",? "+",?);";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setInt(1, b.getClass_id());
        
        pstm.setInt(2, b.getTeam_id());
        
        pstm.setInt(3, b.getUser_id());     
        
        pstm.setInt(4, b.getTeam_leader());  
        
        pstm.setString(5, SDF.format(b.getDropout_date()));
        
        pstm.setString(6, b.getUser_notes());
        
        pstm.setString(7, b.getOngoing_eval());
        
        pstm.setString(8, b.getFinal_press_eval());
        
        pstm.setString(9, b.getFinal_topic_eval());
        
        pstm.setInt(10, b.getStatus());
        
        int rs = pstm.executeUpdate();
            System.out.println("Complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
            
    }
    
    public static void UpdateClassUser(ClassUser b) throws SQLException, ClassNotFoundException{
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "Update class_user \n"
              +"Set class_id = ?"
              +",team_id = ?" 
              +",user_id = ?"
              +",team_leader = ?"
              +",dropout_date = ?"
              +",user_notes = ?"
              +",ongoing_eval = ?"
              +",final_pres_eval = ?"
              +",final_topic_eval = ?"
              +",status = ?\n"
              +"WHERE user_id = ?;";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setInt(1, b.getClass_id());
        
        pstm.setInt(2, b.getTeam_id());
        
        pstm.setInt(3, b.getUser_id());
        
        pstm.setInt(4, b.getTeam_leader());
        
        pstm.setString(5,SDF.format(b.getDropout_date()));     
        
        pstm.setString(6, b.getUser_notes());     
        
        pstm.setString(7, b.getOngoing_eval());
        
        pstm.setString(8, b.getFinal_press_eval());
        
        pstm.setString(9, b.getFinal_topic_eval());
        
        pstm.setInt(10, b.getStatus());
        
        pstm.setInt(11, b.getUser_id());
        
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
       String ms = "Select user_id, status from class_user"
                        + "\nwhere status = ? ; ";
                        PreparedStatement pstm = connection.prepareStatement(ms);
                        pstm.setInt(1, i);
                        ResultSet rs = pstm.executeQuery();
                        ClassUser w = new ClassUser();
      
                    System.out.printf("%-10s%-10s\n",
                             "ID","Status");
                   while (rs.next()) {
                       w.setUser_id(rs.getInt(1)) ;  
                       w.setStatus(rs.getInt(2));

                      System.out.printf("%-10s%-10s\n",
                             w.getUser_id(),TypeStatus[w.getStatus()]);
                      
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
        String sql = "update class_user\n"
              +"Set status = ? "
              +"WHERE user_id = ?;";
       
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DaoClassUser.TeamDisplayByPage(1, "a.class_id", "2");
    }
    public static void TeamDisplayByPage(int n,String field, String filter) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with t as(select row_number() over (order by a.class_id asc) as rn,\n" +
                "b.class_code,"
              + "d.full_name,"
              + "c.topic_code,"
              + "a.team_leader,"
              + "a.dropout_date,"
              + "a.user_notes,"
              + "a.ongoing_eval,"
              + "a.final_pres_eval,"
              + "a.final_topic_eval,"
              + "a.status from class_user as a \n" +
                "left outer join class as b on a.class_id = b.class_id\n" +
                "left outer join team as c on a.team_id = c.team_id "+
                "left outer join user as d on a.user_id = d.user_id "
              + "where "+field+" like ? )\n" +
                "select * from t where rn between ? and ?;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+filter+"%");
      pstm.setInt(2, start);
      pstm.setInt(3, end);

      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-10s\n",  
              "class_Code "
              ,"team topic"
              ,"fullname" 
              ,"team_leader"
              ,"dropout_date "
               ,"user_notes"
              ,"ongoing_eval " 
              ,"final_pres_eval "
              ,"final_topic_eval "
              ,"status ");
            ClassUser w ;
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w = new ClassUser(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11));
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
   
//    public static void ClassDisplayByPage(int n, int ClassID) throws SQLException, ClassNotFoundException{
//        int start=(n-1)*5+1;
//        int end=start+4;
//        try{
//          // Lấy ra đối tượng Connection kết nối vào DB.
//      Connection connection = ConnectionUtils.getMyConnection();
//
//      // Tạo đối tượng Statement.
//      Statement statement = connection.createStatement();
//
//      String sql = "with x as(select ROW_NUMBER() over (order by team_id asc) as ss, "
//              +" class_id "
//              +",topic_code " 
//              +",topic_name "
//              +",gitlab_url "
//              +",status "
//              + " from studentproject.team where class_id like ? ) \n"
//              + "select * from x where ss between ? and ? ;";
//      
//      PreparedStatement pstm = connection.prepareStatement(sql);
//      pstm.setInt(1, ClassID);
//      pstm.setInt(2, start);
//      pstm.setInt(3, end);
//      System.out.println(""+pstm);
//      ResultSet rs = pstm.executeQuery();
//        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n",
//                "SubjectID"
//              ," class_id "
//              ,"topic_code " 
//              ,"topic_name "
//              ,"gitlab_url "
//              ,"status ");
//            Team w = new Team();
//      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
//          w.setTeamId(rs.getInt(1)) ;
//          w.setClass_id(rs.getInt(2)) ;
//          w.setTopicCode(rs.getString(3));;
//          w.setTopicName(rs.getString(4)) ;
//          w.setGitlabUrl(rs.getString(5));
//          w.setStatus(rs.getInt(6));
//          System.out.println(w);
//          
//      }
//      // Đóng kết nối
//      connection.close();
//      } catch (SQLException exp) {
//            System.out.println("Exception: " + exp.getMessage());
//        } catch (ClassNotFoundException exp) {
//            System.out.println("Can't connect to DB: " + exp.getMessage());
//        }
//        
//    }
     public static int countUser(String field,String filter){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from class_user as a left outer join class as b on a.class_id = b.class_id\n" +
						" left outer join team as c on a.team_id = c.team_id "
                                                    + "  left outer join user as d on a.user_id = d.user_id where "+field+" like ?;";
      PreparedStatement pstm = connection.prepareStatement(sql);
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      pstm.setString(1,"%"+filter+"%");
            
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
    public static int countClassUser(String field,String filter){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from class_user as a left outer join user as b on a.user_id = b.user_id\n" +
"						 left outer join class as c on a.class_id = c.class_id where "+field+" like ?;";
      PreparedStatement pstm = connection.prepareStatement(sql);
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      pstm.setString(1,"%"+filter+"%");

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
   
     public static void StatusDisplayByPage(int n, int status) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with t as(select row_number() over (order by a.class_id asc) as rn,\n" +
                "b.class_code,"
              + "c.topic_code,"
              + "d.full_name,"
              + "a.team_leader,"
              + "a.dropout_date,"
              + "a.user_notes,"
              + "a.ongoing_eval,"
              + "a.final_pres_eval,"
              + "a.final_topic_eval,"
              + "a.status from class_user as a \n" +
                "left outer join class as b on a.class_id = b.class_id\n" +
                "left outer join team as c on a.team_id = c.team_id "+
                "left outer join user as d on a.user_id = d.user_id "
              + "where a.status like ? )\n" +
                "select * from t where rn between ? and ?;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1,status);
      pstm.setInt(2, start);
      pstm.setInt(3, end);
      
      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",  
              " class Code "
              ,"team Topic"
              ,"full name " 
              ,"team_leader "
              ,"dropout_date "
               ,"user_notes"
              ,"ongoing_eval " 
              ,"final_pres_eval "
              ,"final_topic_eval "
              ,"status ");
            ClassUser w = new ClassUser();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setClass_id(rs.getInt(2)) ;
          w.setTeam_id(rs.getInt(3)) ;
          w.setUser_id(rs.getInt(4));;
          w.setTeam_leader(rs.getInt(5)) ;
          w.setDropout_date(rs.getDate(6));
          w.setUser_notes(rs.getString(7));
          w.setOngoing_eval(rs.getString(8));
          w.setFinal_press_eval(rs.getString(9));
          w.setFinal_topic_eval(rs.getString(10));
          w.setStatus(rs.getInt(11));
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

      String sql = "select count(*) from studentproject.class_user where status = ?;";

      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1,1);
      
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

      String sql = "select count(*) from studentproject.class_user where status = ?;";

      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1,0);
      
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
    public static ClassUser ProUemail(int id) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select * from class_user \n" +
                    "where user_id = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        ClassUser c = new ClassUser(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9),rs.getInt(10));
                return c;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
}
