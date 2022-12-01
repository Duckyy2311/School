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
import model.Tracking;

/**
 *
 * @author AnhMinh
 */
public class DaoTracking {
    
public static List<String> funcionOfflListStudent(int id, int team_id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.function_id, b.function_name from tracking as a join  functions as b on a.function_id = b.function_id where  a.assignee_id = ? and a.team_id =? and a.milestone_id = 3 and b.status=3;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, id);
      pstm.setInt(2, team_id);
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
     
     public static List<String> teamOfflListStudent(int id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.team_id, b.topic_code from tracking as a join team as b on a.team_id = b.team_id   where a.assignee_id = ? and b.status !=6 and b.status !=7;";
      
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
     public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DaoTracking.teamOfflListStudent(14);
    }
     public static List<String> MilestoneOfflListStudent(int id, int team_id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.milestone_id, b.milestone_name from tracking as a join milestone as b on a.milestone_id=b.milestone_id    where a.assignee_id = ? and a.team_id =?;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, id);
      pstm.setInt(2, team_id);
     
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
     public static List<String> funcionOfflListTrainer(int id, int team_id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.function_id, b.function_name from tracking as a join  functions as b on a.function_id = b.function_id where  a.assigner_id = ? and a.team_id =? and a.milestone_id = 3 and b.status=3;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, id);
      pstm.setInt(2, team_id);
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
     
     public static List<String> teamOfflListTrainer(int id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.team_id, b.topic_code from tracking as a join team as b on a.team_id = b.team_id   where a.assigner_id = ? and b.status !=6 and b.status !=7;";
      
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
    
     public static List<String> MilestoneOfflListTrainer(int id, int team_id) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.milestone_id, b.milestone_name from tracking as a join milestone as b on a.milestone_id=b.milestone_id    where a.assigner_id = ? and a.team_id =?;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, id);
      pstm.setInt(2, team_id);
     
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
    
    public static boolean searchTeamTopic( String topic) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from tracking as a join team as b on a.team_id = b.team_id where  topic_code = ? ; ";
       PreparedStatement pstm = connection.prepareStatement(sql); 

       pstm.setString(1, topic);
         
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
   
    
    public static void addTracking(Tracking b) throws SQLException, ClassNotFoundException{
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "insert into tracking(team_id,milestone_id,function_id,assigner_id,assignee_id,tracking_note,updates, status) \n"
              +"value ( ?"+",?" +", ?"+",?"+",? "+",?"+", ?"+",?);";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setInt(1, b.getTeamid());
        
        pstm.setInt(2, b.getMileStoneid());
        
        pstm.setInt(3, b.getFunction_id());     
        
        pstm.setInt(4, b.getAssigner_id());
        
        pstm.setInt(5, b.getAssignee_id());       
        
        pstm.setString(6, b.getNote());
        
        pstm.setString(7, b.getUpdates());
        
        pstm.setInt(8, b.getStatus());
        
        int rs = pstm.executeUpdate();
            System.out.println("Complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
            
    }
    
    public static void updateTracking(Tracking b) throws SQLException, ClassNotFoundException{
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "Update tracking \n"
              +"Set team_id = ?"
              +",milestone_id = ?" 
              +",function_id = ?"
              +",assigner_id = ?"
              +",assignee_id = ?"
              +",tracking_note = ?"
              +",updates = ?"
              +",status = ?\n"
              +"WHERE tracking_id = ?;";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setInt(1, b.getTeamid());
        
        pstm.setInt(2, b.getMileStoneid());
        
        pstm.setInt(3, b.getFunction_id());
        
        pstm.setInt(4, b.getAssigner_id());     
        
        pstm.setInt(5, b.getAssignee_id());     
        
        pstm.setString(6, b.getNote());
        
        pstm.setString(7, b.getUpdates());
        
        pstm.setInt(8, b.getStatus());
        
        pstm.setInt(9, b.getTracking_id());
        
        int rs = pstm.executeUpdate();System.out.println(pstm);
            System.out.println("Update complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
            
    }
   
    public static void updateStatus(int i, String ID) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection(); 
        String sql = "update tracking as a join team as b on a.team_id = b.team_id\n"
              +"Set a.status = ? "
              +"WHERE b.topic_code = ?;";
       
       PreparedStatement pstm = connection.prepareStatement(sql);       
        pstm.setInt(1, i);
        pstm.setString(2, ID);
        
        
        
        int  rs = pstm.executeUpdate();
            System.out.println("status change successful");
    
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
}
    public static void deleteStatus(int i) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection(); 
        String sql = "delete from tracking\n"
              +"WHERE tracking_id = ? ;";
       
       PreparedStatement pstm = connection.prepareStatement(sql);       
        pstm.setInt(1, i);

        
        
        
        int  rs = pstm.executeUpdate();
            System.out.println("Delete successful");
    
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
}

    public static void trackingDisplayByPageStudent(int n,String field, String filter, int id) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with t as(select row_number() over (order by a.tracking_id asc) as rn,\n" +
              "a.tracking_id,"+
                "b.topic_code,"
              + "c.milestone_name,"
              + "d.function_name,"
              + "e.full_name as assigner,"
              + "f.full_name as assignee,"
              + "a.tracking_note,"
              + "a.updates,"
              + "a.status from tracking as a \n" +
                "left outer join milestone as c on a.milestone_id = c.milestone_id\n" +
                "left outer join team as b on a.team_id = b.team_id "+
                "left outer join functions as d on a.function_id = d.function_id "+
               "left outer join user as e on a.assigner_id = e.user_id "
             + "left outer join user as f on a.assignee_id = f.user_id "  
              + "where a.assignee_id = "+String.valueOf(id)+" and "+field+" like ? and a.status!= 6 and a.status!=7 )\n" +
                "select * from t where rn between ? and ?;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+filter+"%");
      pstm.setInt(2, start);
      pstm.setInt(3, end);
            
      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-15s%-15s%-20s%-15s%-20s%-15s%-15s\n",  
               "tracking_id"
              ,"team_topic "
              ,"milestone_name"
              ,"function_name" 
              ,"assigner"
              ,"assigneename "
              ,"status ");
            Tracking w ;
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w = new Tracking(rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
          w.setTracking_id(rs.getInt(2));
          System.out.printf("%-15s%-15s%-20s%-15s%-20s%-15s%-15s\n",  
               w.getTracking_id()
              ,w.getTopic_code()
              ,w.getMilestone_name()
              ,w.getFunction_name()
              ,w.getAssignee_name()
              ,w.getAssignee_name()
              ,w.getStatus() );
          
      }
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        
    }
    public static Tracking checkDisplayByPageStudent(String field, String filter, int id,String num) throws SQLException, ClassNotFoundException{


        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with t as(select row_number() over (order by a.tracking_id asc) as rn,\n" +
              "a.tracking_id,"+
                "b.topic_code,"
              + "c.milestone_name,"
              + "d.function_name,"
              + "e.full_name as assigner,"
              + "f.full_name as assignee,"
              + "a.tracking_note,"
              + "a.updates,"
              + "a.status, a.team_id from tracking as a \n" +
                "left outer join milestone as c on a.milestone_id = c.milestone_id\n" +
                "left outer join team as b on a.team_id = b.team_id "+
                "left outer join functions as d on a.function_id = d.function_id "+
               "left outer join user as e on a.assigner_id = e.user_id "
             + "left outer join user as f on a.assignee_id = f.user_id "  
              + "where a.assignee_id = "+String.valueOf(id)+" and "+field+" like ? and a.status!= 6 and a.status!=7 )\n" +
                "select * from t where rn like ?;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+filter+"%");
      pstm.setString(2,"%"+num+"%");
      
            
      ResultSet rs = pstm.executeQuery();
            Tracking w ;
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w = new Tracking(rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
          w.setTeamid(rs.getInt(11));
//          System.out.println(rs.getInt(2)+"\t       "+w);
          return w;
      }
      
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
        
    }
    
     public static void trackingDisplayByPageTrainer(int n,String field, String filter, int id) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with t as(select row_number() over (order by a.tracking_id asc) as rn,\n" +
                 "a.tracking_id,"
              +  "b.topic_code,"
              + "c.milestone_name,"
              + "d.function_name,"
              + "e.full_name as assigner,"
              + "f.full_name as assignee,"
              + "a.tracking_note,"
              + "a.updates,"
              + "a.status from tracking as a \n" +
                "left outer join milestone as c on a.milestone_id = c.milestone_id\n" +
                "left outer join team as b on a.team_id = b.team_id "+
                "left outer join functions as d on a.function_id = d.function_id "+
               "left outer join user as e on a.assigner_id = e.user_id "
             + "left outer join user as f on a.assignee_id = f.user_id "  
              + "where a.assigner_id = "+String.valueOf(id)+" and "+field+" like ? )\n" +
                "select * from t where rn between ? and ?;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+filter+"%");
      pstm.setInt(2, start);
      pstm.setInt(3, end);
        
      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-15s%-15s%-20s%-15s%-20s%-15s%-15s\n", 
              "id",
              "topic "
              ,"milestone"
              ,"function" 
              ,"assigner"
              ,"assignee "
              ,"status ");
            Tracking w ;
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w = new Tracking(rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
          System.out.println(rs.getInt(2)+"\t       "+w);
          
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
    
     public static int countTrackingStudent(String field,String filter, int id){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from tracking as a left outer join milestone as b on a.milestone_id = b.milestone_id\n" +
						" left outer join team as c on a.team_id = c.team_id "
                                                    + "left outer join functions as d on a.function_id = d.function_id "
                                                    + "left outer join user as e on a.assigner_id = e.user_id "
                                                    + "left outer join user as f on a.assignee_id = f.user_id where f.user_id = "  +String.valueOf(id)+" and "+field+" like ?;";
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
      public static int countTrackingTrainer(String field,String filter, int id){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from tracking as a left outer join milestone as b on a.milestone_id = b.milestone_id\n" +
						" left outer join team as c on a.team_id = c.team_id "
                                                    + "left outer join functions as d on a.function_id = d.function_id "
                                                    + "left outer join user as e on a.assigner_id = e.user_id "
                                                    + "left outer join user as f on a.assignee_id = f.user_id where e.user_id = "  +String.valueOf(id)+" and "+field+" like ?;";
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
    public static int countTeam(String field,String filter){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();
      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from tracking as a left outer join team as b on a.team_id = b.team_id\n" +
"						 left outer join milestone as c on a.milestone_id = c.milestone_id "
                                                + "left outer join functions as d on a.function_id = d.function_id "
                                                + "left outer join user as e on a.assigner_id = e.user_id "
                                                + "left outer join user as f on a.assignee_id = f.user_id "+" where "+field+" like ?;";
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
   
    public static void StatusDisplayByPageTrainer(int n, String status,int id) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with t as(select row_number() over (order by a.tracking_id asc) as rn,\n" +
 
               "b.topic_code,"
              + "c.milestone_name,"
              + "d.function_name,"
              + "a.assigner_id,"
              + "a.assignee_id,"
              + "a.tracking_note,"
              + "a.updates,"
              + "a.status from tracking as a \n" +
                "left outer join milestone as c on a.milestone_id = c.milestone_id\n" +
                "left outer join team as b on a.team_id = b.team_id "+
                "left outer join functions as d on a.function_id = d.function_id "
              + "left outer join user as e on a.assigner_id = e.user_id "
              + "left outer join user as f on a.assignee_id = f.user_id "
              + "where "+"e.user_id ="+id+" and  a.status like ? )\n" +
               
                "select * from t where rn between ? and ?;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+status+"%");
      pstm.setInt(2, start);
      pstm.setInt(3, end);
      
      ResultSet rs = pstm.executeQuery();
         System.out.printf("%-15s%-20s%-15s%-15s%-15s%-15s%-15s%-15s\n",  
              "topic "
              ,"milestone"
              ,"function" 
              ,"assigner_id"
              ,"assignee_id "

              ,"status ");
            Tracking w = new Tracking();
      
     
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setTopic_code(rs.getString(2)) ;
          w.setMilestone_name(rs.getString(3)) ;
          w.setFunction_name(rs.getString(4));;
          w.setAssigner_id(rs.getInt(5)) ;
          w.setAssignee_id(rs.getInt(6));
          w.setNote(rs.getString(7));
          w.setUpdates(rs.getString(8));
          w.setStatus(rs.getInt(9));
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
    
     public static void StatusDisplayByPageStudent(int n, int id) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with t as(select row_number() over (order by a.tracking_id asc) as rn,\n" +
 
               "b.topic_code,"
              + "c.milestone_name,"
              + "d.function_name,"
              + "a.assigner_id,"
              + "a.assignee_id,"
              + "a.tracking_note,"
              + "a.updates,"
              + "a.status from tracking as a \n" +
                "left outer join milestone as c on a.milestone_id = c.milestone_id\n" +
                "left outer join team as b on a.team_id = b.team_id "+
                "left outer join functions as d on a.function_id = d.function_id "
               + "left outer join user as e on a.assigner_id = e.user_id "
              + "left outer join user as f on a.assignee_id = f.user_id "
              + "where "+"f.user_id ="+id+" and  a.status < 6 )\n" +
               
                "select * from t where rn between ? and ?;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);

      pstm.setInt(1, start);
      pstm.setInt(2, end);
      
      ResultSet rs = pstm.executeQuery();
         System.out.printf("%-15s%-20s%-15s%-15s%-15s%-15s%-15s%-15s\n",  
              "team_topic "
              ,"milestone_name"
              ,"function_name" 
              ,"assigner_id"
              ,"assignee_id "
               ,"tracking_note"
              ,"updates " 
              ,"status ");
            Tracking w = new Tracking();
      
     
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setTopic_code(rs.getString(2)) ;
          w.setMilestone_name(rs.getString(3)) ;
          w.setFunction_name(rs.getString(4));;
          w.setAssigner_id(rs.getInt(5)) ;
          w.setAssignee_id(rs.getInt(6));
          w.setNote(rs.getString(7));
          w.setUpdates(rs.getString(8));
          w.setStatus(rs.getInt(9));
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
    
     
    
    public static int countStatusStudent(String status, int id){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.tracking as a join user as b on a.assignee_id = b.user_id where b.user_id = "+id+" and  a.status = ?;";

      PreparedStatement pstm = connection.prepareStatement(sql);
      
      pstm.setString(1,"%"+status+"%");
      
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
    public static int countStatusTrainer(String status, int id){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.tracking as a join user as b on a.assigner_id = b.user_id where b.user_id = "+id+" and  a.status = ?;";

      PreparedStatement pstm = connection.prepareStatement(sql);
      
      pstm.setString(1,"%"+status+"%");
      
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
    
    public static int checkTeamUser(int id){
         try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select * from tracking as a join user as b on a.assignee_id = b.user_id\n" +
                    "where b.user_id = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        int count =0;
        while(rs.next()){
        count++;
               
        }return count;}
        catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return 0;
    }
    public static Tracking checkTracking(int id) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select * from tracking \n" +
                    "where tracking_id = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        Tracking c = new Tracking(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9));
                return c;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
     public static boolean checkFunctionID(int classId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String sql = "Select * from functions where function_id = ? and status = 3";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, classId);

            ResultSet rs = pstm.executeQuery();
            if (rs.next() == true) {
                connection.close();
                return true;
            } else {
                connection.close();

                return false;
            }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return false;
    }
}
//     public static List<String> assignerOnList(int f){
//             List<String> list = new ArrayList<>();
//        try{
//          // Lấy ra đối tượng Connection kết nối vào DB.
//      Connection connection = ConnectionUtils.getMyConnection();
//
//      // Tạo đối tượng Statement.
//      Statement statement = connection.createStatement();
//
//      String sql = "select b.user_id, b.full_name from tracking as a join User as b on a.assigner_id = b.user_id  where b.role_id =2 and b.status = 1;";
//      
//      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
//      
//      PreparedStatement pstm = connection.prepareStatement(sql);
//      
//      ResultSet rs = pstm.executeQuery();
//
//            
//      
//      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
//          
//          
//          list.add(rs.getInt(1)+"-"+rs.getString(2));
//          
//      }
//            System.out.println("");
//      // Đóng kết nối
//      connection.close();
//      } catch (SQLException exp) {
//            System.out.println("Exception: " + exp.getMessage());
//        } catch (ClassNotFoundException exp) {
//            System.out.println("Can't connect to DB: " + exp.getMessage());
//        }
//       return list;
//     }
//     public static List<String> assigneeOnList(int f){
//             List<String> list = new ArrayList<>();
//        try{
//          // Lấy ra đối tượng Connection kết nối vào DB.
//      Connection connection = ConnectionUtils.getMyConnection();
//
//      // Tạo đối tượng Statement.
//      Statement statement = connection.createStatement();
//
//      String sql = "select b.user_id, b.full_name from tracking as a join User as b on a.assignee_id = b.user_id  where b.role_id =1 and b.status = 1;";
//      
//      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
//      
//      PreparedStatement pstm = connection.prepareStatement(sql);
//      
//      ResultSet rs = pstm.executeQuery();
//
//            
//      
//      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
//          
//          
//          list.add(rs.getInt(1)+"-"+rs.getString(2));
//          
//      }
//            System.out.println("");
//      // Đóng kết nối
//      connection.close();
//      } catch (SQLException exp) {
//            System.out.println("Exception: " + exp.getMessage());
//        } catch (ClassNotFoundException exp) {
//            System.out.println("Can't connect to DB: " + exp.getMessage());
//        }
//       return list;
//     }