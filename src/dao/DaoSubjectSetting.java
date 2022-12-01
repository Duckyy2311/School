package dao;

import connection.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Setting;
import model.Setting;
import model.Subject;
import model.SubjectSetting;
import model.SubjectSetting;

/**
 *
 * @author Dinh Quoc Tung
 */
public class DaoSubjectSetting {

    public static void settingDisplay(int id) throws SQLException, ClassNotFoundException {
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "Select a.*,b.subject_name from subject_setting as a join subject as b on a.subject_id = b.subject_id"
                    + " where author_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            
            ResultSet rs = pstm.executeQuery();
            SubjectSetting w = new SubjectSetting();
            System.out.printf("%-15s%-15s%-15s%-20s%-15s%-20s%-10s\n",
                    "Setting ID",
                    "Subject name",
                    "Type_ID",
                    "Title",
                    "Value",
                    "Order",
                    "Status");
            // Duyệt trên kết quả trả về."D:\sinh vien fpt\ISP392\ISP_Project\src\Controller\SettingList.java"
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setSetting_id(rs.getInt(1));
                w.setSubject_id(rs.getInt(2));
                w.setType_id(rs.getInt(3));;
                w.setSetting_tittle(rs.getString(4));
                w.setSetting_value(rs.getInt(5));
                w.setDisplay_order(rs.getString(6));
                w.setStatus(rs.getInt(7));
                w.setSubject_name(rs.getString(8));

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
    public static void addSubjectSetting(SubjectSetting b) throws SQLException, ClassNotFoundException{

    try{
         Connection connection = ConnectionUtils.getMyConnection();
   
      Statement statement = connection.createStatement();
      
      String sql = "insert into subject_setting(subject_id,type_id,setting_title,setting_value,display_order, status) \n"
              +"value ("+"?" +", ?"+",?"+",? "+",?"+", ?"+");";
         
              
        PreparedStatement pstm = connection.prepareStatement(sql); 
        
        
        pstm.setInt(1, b.getSubject_id());
        
        pstm.setInt(2, b.getType_id());     
        
        pstm.setString(3, b.getSetting_tittle());
        
        pstm.setInt(4, b.getSetting_value());       
        
        pstm.setString(5, b.getDisplay_order());  
        
        pstm.setInt(6, b.getStatus());
        int rs = pstm.executeUpdate();
            System.out.println("Complete!");
            } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
            
    }
      public static SubjectSetting checkSubjectSettingID(int SettingId, int id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select a.*,b.subject_name from subject_setting as a join subject as b on a.subject_id = b.subject_id "
                    + "where a.setting_id = ? and b.author_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, SettingId);
            pstm.setInt(2, id);
            ResultSet rs = pstm.executeQuery();
            SubjectSetting w = new SubjectSetting();
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setSetting_id(rs.getInt(1));
                w.setSubject_id(rs.getInt(2));
                w.setType_id(rs.getInt(3));;
                w.setSetting_tittle(rs.getString(4));
                w.setSetting_value(rs.getInt(5));
                w.setDisplay_order(rs.getString(6));
                w.setStatus(rs.getInt(7));

                return w;

            }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
    
    

    public static boolean SearchSubjectSettingID(int SettingId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from subject_setting as a join subject as b on a.subject_id = b.subject_id "
                    + "where a.setting_id = ? ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, SettingId);

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

    public static void UpdateSubjectSetting(SubjectSetting b) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "Update subject_setting \n"
                    + "Set subject_id = ?"
                    + ",type_id = ?"
                    + ",setting_title = ?"
                    + ",setting_value = ?"
                    + ",display_order = ?"
                    + ",status = ?\n"
                    + "WHERE setting_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, b.getSubject_id());

            pstm.setInt(2, b.getType_id());

            pstm.setString(3, b.getSetting_tittle());

            pstm.setInt(4, b.getSetting_value());

            pstm.setString(5, b.getDisplay_order());
            pstm.setInt(6, b.getStatus());
            pstm.setInt(7, b.getSetting_id());

            int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static void DisplayByStatus(int i, int id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String[] TypeStatus = {"inactive", "active"};
            String ms = "Select a.setting_id, a.status from subject_setting as a join subject as b on a.subject_id = b.subject_id "
                    + "\nwhere a.status = ? and b.author_id = ?; ";
            PreparedStatement pstm = connection.prepareStatement(ms);
            pstm.setInt(1, i);
            pstm.setInt(2, id);
            ResultSet rs = pstm.executeQuery();
            Setting w = new Setting();

            System.out.printf("%-10s%-10s\n",
                    "ID", "Status");
            while (rs.next()) {
                w.setSettingId(rs.getInt(1));
                w.setStatus(rs.getInt(2));

                System.out.printf("%-10s%-10s\n",
                        w.getSettingId(), TypeStatus[w.getStatus()]);

            }
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static void UpdateStatus(int i, int ID) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String sql = "update subject_setting\n"
                    + "Set status = ? "
                    + "WHERE setting_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, i);
            pstm.setInt(2, ID);

            int rr = pstm.executeUpdate();
            System.out.println("status change successful");

        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static void SubjectDisplayByPage(int n, String Subname, int id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with x as(select ROW_NUMBER() over (order by setting_id asc) as ss, "
                    +"a.setting_id,"
                    + "b.subject_name,"
                    + "a.type_id,"
                    + "a.setting_title,"
                    + "a.setting_value,"
                    + "a.display_order,"
                    + "a.status "
                    + " from studentproject.subject_setting as a join subject as b on a.subject_id = b.subject_id where a.setting_id like ? ) \n"
                    + "select * from x where b.author_id = ? and ss between ? and ? ;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1,id);
            pstm.setString(2, "%" + Subname + "%");
            pstm.setInt(3, start);
            pstm.setInt(4, end);
            System.out.println("" + pstm);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-15s%-15s%-15s%-20s%-15s%-20s%-10s\n",
                    "Setting_id",
                    "Subject_name",
                    "type_id",
                    "setting_title",
                    "setting_value",
                    "display_order",
                    "Status");
            SubjectSetting w = new SubjectSetting();
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setSetting_id(rs.getInt(1));
                w.setSubject_name(rs.getString(2));
                w.setType_id(rs.getInt(3));;
                w.setSetting_tittle(rs.getString(4));
                w.setSetting_value(rs.getInt(5));
                w.setDisplay_order(rs.getString(6));
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



    public static void AuthorDisplayByPage(int n, int AuthorId) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
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
            System.out.println("" + pstm);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-10s%-20s%-20s%-20s%-20s\n",
                    "SubjectID",
                    "Subject Code",
                    "Subject Name",
                    "AuthorID",
                    "Status");
            Subject w = new Subject();
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setSubId(rs.getInt(1));
                w.setSubCode(rs.getString(2));
                w.setSubName(rs.getString(3));;
                w.setAuthId(rs.getInt(4));
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

    public static int countSubjectByAuthorID(int ID) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from studentproject.subject where author_id = ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            pstm.setInt(1, ID);
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

    public static int countSubject() {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from studentproject.subject;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            ResultSet rs = statement.executeQuery(sql);
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

    public static void StatusDisplayByPage(int n, int status) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
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
            pstm.setInt(1, status);
            pstm.setInt(2, start);
            pstm.setInt(3, end);
            System.out.println("" + pstm);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-10s%-20s%-20s%-20s%-20s\n",
                    "SubjectID",
                    "Subject Code",
                    "Subject Name",
                    "AuthorID",
                    "Status");
            Subject w = new Subject();
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setSubId(rs.getInt(1));
                w.setSubCode(rs.getString(2));
                w.setSubName(rs.getString(3));;
                w.setAuthId(rs.getInt(4));
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

    public static int countAcStatus() {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from studentproject.subject where status = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, 1);
            System.out.println("" + pstm);
            ResultSet rs = pstm.executeQuery();
//      ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                h = rs.getInt(1);
            }

            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return h;
    }

    public static int countInStatus() {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from studentproject.subject where status = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, 0);
            System.out.println("" + pstm);
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

    public static List<String> subjectList(int id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.subject_id,b.subject_code from subject_setting as a join subject as b on a.subject_id = b.subject_id"
                    + " where b.author_id = ? and b.status = 1;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.          
                list.add(rs.getInt(1) + "-" + rs.getString(2));

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
//    public static List<String> settingList(int id) throws SQLException, ClassNotFoundException{
//        List<String> list = new ArrayList<>();
//        try{
//          // Lấy ra đối tượng Connection kết nối vào DB.
//      Connection connection = ConnectionUtils.getMyConnection();
//
//      // Tạo đối tượng Statement.
//      Statement statement = connection.createStatement();
//
//      String sql = "select a.setting_id, b.setting_title from subject_setting as a join  setting as b on a.setting_id = b.setting_id "
//              + "where  b.author_id = ? and  b.status=1;";
//      
//      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
//      
//      PreparedStatement pstm = connection.prepareStatement(sql);
//      pstm.setInt(1, id);
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
//    }
}
