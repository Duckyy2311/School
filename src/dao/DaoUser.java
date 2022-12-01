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
import model.User;

/**
 *
 * @author Admin
 */
public class DaoUser {
    //ham kiem tra login
    public static boolean CheckPass(String Email, String Pass) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select email , pass_word from user where email = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Email);

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String h = rs.getString(2);            
            if (h.equals(Pass)) { 
                connection.close();
                return true;
            } 
        }
        connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return false;
    }
    
    //ham chay dang ki
    public static void insertUs(User us) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection();
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
      Statement statement = connection.createStatement();
      
      String sql = "Insert into studentproject.user \n" +
                   "(Pass_word,roll_number, full_name, gender,date_of_birth,email,mobile,avatar_link,Facebook_link,role_id,Status)\n" +
                   "values "
              + "(?, ?,?,?,?,?,?,?,?,?,?) ;";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        

        
        pstm.setString(1, us.getPassword());
        
        pstm.setString(2, us.getRollNumber());
        
        pstm.setString(3, us.getFullName());
        
        pstm.setInt(4, us.getGender());     
        
        pstm.setString(5, SDF.format(us.getDOB()));
        
        pstm.setString(6, us.getEmail());
        
        pstm.setString(7, us.getMobile());
        
        pstm.setString(8, us.getAvtLink());
        
        pstm.setString(9, us.getFbLink());
        
        pstm.setInt(10, us.getRoleId());
        
        pstm.setInt(11, us.getStatus());
        int rs = pstm.executeUpdate();
        System.out.println("Register Success!");
        connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }
        //hàm role theo ID
        public static  int Role(String UserId) throws SQLException, ClassNotFoundException {
        int h =0;    
        try{
             Connection connection = ConnectionUtils.getMyConnection();
        String sql = "select user_id , roll_ number from user where user_id = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, UserId);

        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
             h = rs.getInt(2);            
            
                connection.close();
              
        }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return h;
        
    }

    //hàm searchUmail
        public static boolean SearchUemail(String email) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "Select * from user where email = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, email);

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

    public static User findProUemail(String email) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select user_id,"
              + "roll_number,"
              + "full_name,"
              + "gender,"
              + "date_of_birth,"
              + "email,"
              + "mobile,"
              + "avatar_link,"
              + "facebook_link,"
              + "role_id,"
              + "status from studentproject.user "
              + "where user.email = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, email);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getString(6),
                rs.getString(7), rs.getString(8),rs.getString(9),rs.getInt(10),"********",rs.getInt(11));
                return u;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(DaoUser.findProId(1));
    }
    public static User findProId(int id) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select user_id,"
              + "roll_number,"
              + "full_name,"
              + "gender,"
              + "date_of_birth,"
              + "email,"
              + "mobile,"
              + "avatar_link,"
              + "facebook_link,"
              + "role_id,"
              + "status from studentproject.user "
              + "where user.user_id = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getString(6),
                rs.getString(7), rs.getString(8),rs.getString(9),rs.getInt(10),"********",rs.getInt(11));
                return u;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
    // tìm role theo mail 
    public static int searchRolebymail(String Email) throws SQLException, ClassNotFoundException {
        try{   
         Connection connection = ConnectionUtils.getMyConnection();
        
        String sql = "Select role_id from user where email = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Email);

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int h = rs.getInt(1);                          
                connection.close();
                return h;
            
        }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return 0;
    }
    public static int SearchRolebyId(int Id) throws SQLException, ClassNotFoundException {
        try{   
         Connection connection = ConnectionUtils.getMyConnection();
        
        String sql = "Select role_id from user where user_id = ? and status = 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, Id);

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int h = rs.getInt(1);                          
                connection.close();
                return h;
            
        }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return 0;
    }
    //updatePass
    public static void updatePass(String Email, String NewPass) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "Update user \n"
              +"Set pass_word = ?\n"          
              +"WHERE email = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, NewPass);
        pstm.setString(2, Email);
        int rs = pstm.executeUpdate();
            System.out.println("Change complete!");
        connection.close();
    
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
}
    //hàm update user
    public static void UpdateUser(User u) throws SQLException, ClassNotFoundException{
        
    try{
         Connection connection = ConnectionUtils.getMyConnection();
         SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
      Statement statement = connection.createStatement();
      
      String sql = "Update user \n"
              +"Set roll_number = ?"
              + ",full_name = ?"
              +",gender = ?"
              +",date_of_birth = ?" 
              +",mobile = ?"
              +",avatar_link = ?"
              +",facebook_link = ?"
              +",role_id = ?"
              +",status = ?\n"
              +"WHERE user_id = ?;";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        pstm.setString(1, u.getRollNumber());
        pstm.setString(2, u.getFullName());
        
        pstm.setInt(3, u.getGender());
        
        pstm.setString(4, SDF.format(u.getDOB()) );
        
        pstm.setString(5, u.getMobile());
        
        pstm.setString(6, u.getAvtLink());     
        
        pstm.setString(7, u.getFbLink());
        
        pstm.setInt(8, u.getRoleId());
        
        pstm.setInt(9, u.getStatus());
        pstm.setInt(10, u.getUserId());
        int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }            
    }
    //ham đếm
    public static int countUser(String Roll){
        int h=0;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select count(*) from studentproject.user where roll_number like ?;";
      PreparedStatement pstm = connection.prepareStatement(sql);
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      pstm.setString(1,"%"+Roll+"%");
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
    //ham User display
    public static void UserDisplayByPage(int n,String Roll) throws SQLException, ClassNotFoundException{
        int start=(n-1)*5+1;
        int end=start+4;
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "with x as(select ROW_NUMBER() over (order by user_id asc) as rn,"
              + "user_id,"
              + "roll_number,"
              + "full_name,"
              + "gender,"
              + "date_of_birth,"
              + "email,"
              + "mobile,"
              + "avatar_link,"
              + "facebook_link,"
              + "role_id,"
              + "status from studentproject.user where roll_number like ?) \n "
              + "select * from x where rn between ? and ? ;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1,"%"+Roll+"%");
      pstm.setInt(2, start);
      pstm.setInt(3, end);
      ResultSet rs = pstm.executeQuery();
      User u = new User();
            System.out.printf("%-10s%-15s%-20s%-10s%-15s%-20s%-10s%-20s%-20s%-10s%-10s\n",
                "User ID",
                "Roll Number",
                "Full Name",
                "Gender",
                "Date of Birth",
                "Email",
                "Mobile",
                "Avt Link",
                "Fb Link",
                "Role ID",
                "Status");
      
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          u.setUserId(rs.getInt(2));
          u.setRollNumber(rs.getString(3));
          u.setFullName(rs.getString(4));
          u.setGender(rs.getInt(5));
          u.setDOB(rs.getDate(6));
          u.setEmail(rs.getString(7));
          u.setMobile(rs.getString(8));
          u.setAvtLink(rs.getString(9));
          u.setFbLink(rs.getString(10));
          u.setRoleId(rs.getInt(11));
          u.setStatus(rs.getInt(12));
          
          System.out.println(u);
          
      }
      // Đóng kết nối
      connection.close();
      } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
       
    }
    
    public static List<String> RoleList(int Role) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<>();
        try{
          // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();

      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();

      String sql = "select user_id,full_name from user where role_id = ? and status = 1;";
      
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setInt(1, Role);

      ResultSet rs = pstm.executeQuery();

            
      
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          
          
          list.add(rs.getInt(1)+"-"+rs.getString(2));
          
      }
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
        String sql = "update user\n"
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
        public static boolean SearchUID(int UId) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from user where user_id = ? ";
       PreparedStatement pstm = connection.prepareStatement(sql); 
       pstm.setInt(1, UId);
         
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
