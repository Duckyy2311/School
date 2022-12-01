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
import model.Team;
import model.Class;

/**
 *
 * @author AnhMinh
 */
public class DaoTeam {
    
    public static List<String> StatusOnlList() throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select team_id, topic_code from team where status = 1;";
      
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
     
     public static List<String> ClassOnlList() throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select class_id, class_code from class where status = 1;";
      
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
    public static boolean SearchTeamID(int teamID) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from team where team_id = ?; ";
       PreparedStatement pstm = connection.prepareStatement(sql); 
       pstm.setInt(1, teamID);
         
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
   
   
    
    public static void AddTeam(Team b) throws SQLException, ClassNotFoundException{
        
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "insert into Team \n"
              +"value ( ?"+",?" +", ?"+",?"+",? "+",?);";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setInt(1, b.getTeamId());
        
        pstm.setInt(2, b.getClass_id());
        
        pstm.setString(3, b.getTopicCode());     
        
        pstm.setString(4, b.getTopicName());  
        
        pstm.setString(5, b.getGitlabUrl());
        
        pstm.setInt(6, b.getStatus());
        
        int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
            
    }
    
    public static void UpdateTeam(Team b) throws SQLException, ClassNotFoundException{
        
    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "Update team \n"
              +"Set class_id = ?"
              +",topic_code = ?" 
              +",topic_name = ?"
              +",gitlab_url = ?"
              +",status = ?\n"
              +"WHERE team_id = ?;";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        pstm.setInt(1, b.getClass_id());
        
        pstm.setString(2, b.getTopicCode());
        
        pstm.setString(3, b.getTopicName());     
        
        pstm.setString(4, b.getGitlabUrl());     
        
        pstm.setInt(5, b.getStatus());
        
        pstm.setInt(6, b.getTeamId());
        
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
       String ms = "Select team_id, status from team"
                        + "\nwhere status = ? ; ";
                        PreparedStatement pstm = connection.prepareStatement(ms);
                        pstm.setInt(1, i);
                        ResultSet rs = pstm.executeQuery();
                        Team w = new Team();
      
                    System.out.printf("%-10s%-10s\n",
                             "ID","Status");
                   while (rs.next()) {
                       w.setTeamId(rs.getInt(1)) ;  
                       w.setStatus(rs.getInt(2));

                      System.out.printf("%-10s%-10s\n",
                             w.getTeamId(),TypeStatus[w.getStatus()]);
                      
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
        String sql = "update Team\n"
              +"Set status = ? "
              +"WHERE team_id = ?;";
       
       PreparedStatement pstm = connection.prepareStatement(sql);       
        pstm.setInt(1, i);
        pstm.setInt(2, ID);
        
        
        
        int  rr = pstm.executeUpdate();
            System.out.println("status change successful");
    
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
}
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DaoTeam.TeamDisplayByPage(1,"");
    }
    public static void TeamDisplayByPage(int n, String Subname) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with x as(select ROW_NUMBER() over (order by team_id asc) as ss, "
              +" team_id "
              +" ,class_id "
              +",topic_code " 
              +",topic_name "
              +",gitlab_url "
              +",status "
              + " from studentproject.team where team_id like ? ) \n"
              + "select * from x where ss between ? and ? ;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+Subname+"%");
      pstm.setInt(2, start);
      pstm.setInt(3, end);

      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n",
                "Team_id"
              ," class_id "
              ,"topic_code " 
              ,"topic_name "
              ,"gitlab_url "
              ,"status ");
            Team w = new Team();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.   
          w.setTeamId(rs.getInt(2)) ;
          w.setClass_id(rs.getInt(3)) ;
          w.setTopicCode(rs.getString(4));
          w.setTopicName(rs.getString(5)) ;
          w.setGitlabUrl(rs.getString(6));
          w.setStatus(rs.getInt(7));
          
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
   
    @SuppressWarnings("empty-statement")
    public static void ClassDisplayByPage(int n, int ClassID) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with x as(select ROW_NUMBER() over (order by team_id asc) as ss, "
              +" team_id, "
              +" class_id "
              +",topic_code " 
              +",topic_name "
              +",gitlab_url "
              +",status "
              + " from studentproject.team where class_id like ? ) \n"
              + "select * from x where ss between ? and ? ;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, ClassID);
      pstm.setInt(2, start);
      pstm.setInt(3, end);
      System.out.println(""+pstm);
      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n",
                "SubjectID"
              ," class_id "
              ,"topic_code " 
              ,"topic_name "
              ,"gitlab_url "
              ,"status ");
            Team w = new Team();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setTeamId(rs.getInt(2)) ;
          w.setClass_id(rs.getInt(3)) ;
          w.setTopicCode(rs.getString(4));;
          w.setTopicName(rs.getString(5)) ;
          w.setGitlabUrl(rs.getString(6));
          w.setStatus(rs.getInt(7));
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
    public static int countTeamByClass(int ID){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.team where class_id = ?;";
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
    public static int countTeam(){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.team;";

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

      String sql = "with x as(select ROW_NUMBER() over (order by team_id asc) as ss, "
              +"team_id,"
              +" class_id "
              +",topic_code " 
              +",topic_name "
              +",gitlab_url "
              +",status "
              + " from studentproject.team where status like ? ) \n"
              + "select * from x where ss between ? and ? ;";
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1,status);
      pstm.setInt(2, start);
      pstm.setInt(3, end);
      
      ResultSet rs = pstm.executeQuery();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                "Team_id"
              ," class_id "
              ,"topic_code " 
              ,"topic_name "
              ,"gitlab_url "
              ,"status ");
            Team w = new Team();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          w.setTeamId(rs.getInt(2)) ;
          w.setClass_id(rs.getInt(3)) ;
          w.setTopicCode(rs.getString(4));;
          w.setTopicName(rs.getString(5)) ;
          w.setGitlabUrl(rs.getString(6));
          w.setStatus(rs.getInt(7));
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

      String sql = "select count(*) from studentproject.team where status = ?;";

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

      String sql = "select count(*) from studentproject.team where status = ?;";

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
    public static Team ProUemail(int id) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select * from team \n" +
                    "where team_id = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        Team c = new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                return c;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
    public static Team findTeam(int id){
        Team team=null;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select a.team_id,b.topic_name from class_user as a "
              + "inner join team as b on a.team_id=b.team_id where user_id = ?;";

      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1,id);
      
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
      team = new Team(rs.getInt(1), 0, null, rs.getString(2), null, 1);
      }
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return team;
    }
}
