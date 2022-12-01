package dao;

import connection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Function;
/**
 *
 * @author Dinh Quoc Tung
 */
import model.Function;

public class DaoFunction {

    public static void insertFunction(Function ft) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO studentproject.function\n"
                    + "(team_id,function_name,feature_id,access_roles,description,complexity_id,owner_id,priority,status)\n"
                    + "VALUES (?,?,?,?,?,?,?);";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, ft.getTeam_id());

            pstm.setString(2, ft.getFunction_name());

            pstm.setInt(3, ft.getFeature_id());

            pstm.setString(4, ft.getAccess_role());

            pstm.setString(5, ft.getDescription());

            pstm.setString(6, ft.getComplexity_id());
            pstm.setString(7, ft.getOwner_id());
            pstm.setInt(8, ft.getPriority());

            pstm.setInt(9, ft.getStatus());

            int rs = pstm.executeUpdate();
            System.out.println("Update Success!");
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        displayByPageForTrain(1, "a.function_id", "", 3);
    }

    public static int countFunctionForTrainer(String field, String filter, int id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

//            String sql = "select count(*) from class as a left outer join user as b on a.trainer_id = b.user_id\n"
//                    + "						 left outer join subject as c on a.subject_id = c.subject_id where " + field + " like ?;";
            String sql = "select count(*) from studentproject.function as a inner join team as b on a.team_id=b.team_id\n"
                    + "inner join feature as c on a.feature_id=c.feature_id\n"
                    + "inner join class as d on b.class_id=d.class_id\n"
                    + "where d.trainer_id = " + String.valueOf(id) + " and " + field + " like ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            pstm.setString(1, "%" + filter + "%");

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

    public static void displayByPageForTrain(int n, String field, String filter, int id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(\n"
                    + "select row_number() over (order by a.function_id asc) as rn,"
                    + "a.function_id,"
                    + "a.function_name,"
                    + "b.topic_name,"
                    + "c.feature_name,"
                    + "a.access_roles,"
                    + "a.description,"
                    + "e.full_name,"
                    + "a.status\n"
                    + "from studentproject.function as a inner join team as b on a.team_id=b.team_id\n"
                    + "inner join feature as c on a.feature_id=c.feature_id\n"
                    + "inner join class as d on b.class_id=d.class_id\n"
                    + "inner join user as e on a.owner_id=e.user_id\n"
                    + "where d.trainer_id = " + String.valueOf(id) + " and " + field + " like ?)\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);
            ResultSet rs = pstm.executeQuery();
            Function function = new Function();
            System.out.printf("|%-5s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-10s\n",
                    "ID",
                    "FunctionName",
                    "TopicName",
                    "FeatureName",
                    "AccessRoles",
                    "Description",
                    "OwnerName",
                    "Status");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                function = new Function(rs.getInt(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(8), rs.getString(7), rs.getInt(9));
                System.out.println(function);

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static int countFunctionForStu(String field, String filter, int id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

//            String sql = "select count(*) from class as a left outer join user as b on a.trainer_id = b.user_id\n"
//                    + "						 left outer join subject as c on a.subject_id = c.subject_id where " + field + " like ?;";
            String sql = "select count(*) from studentproject.function as a \n"
                    + "left outer join team as b on a.team_id=b.team_id\n"
                    + "left outer join feature as c on a.feature_id=c.feature_id\n"
                    + "left outer join class_user as d on a.team_id=d.team_id\n"
                    + "where d.user_id = " + String.valueOf(id) + " and " + field + " like ? group by a.function_id;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            pstm.setString(1, "%" + filter + "%");
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

    public static void displayByPageForStu(int n, String field, String filter, int id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(\n"
                    + "select row_number() over (order by a.function_id asc) as rn,"
                    + "a.function_id,"
                    + "a.function_name,"
                    + "b.topic_name,"
                    + "c.feature_name,"
                    + "a.access_roles,"
                    + "a.description,"
                    + "e.full_name,"
                    + "a.status\n"
                    + "from studentproject.function as a inner join team as b on a.team_id=b.team_id\n"
                    + "left outer join feature as c on a.feature_id=c.feature_id\n"
                    + "left outer join class_user as d on a.team_id=d.team_id\n"
                    + "inner join user as e on a.owner_id=e.user_id\n"
                    + "where d.user_id = " + String.valueOf(id) + " and " + field + " like ?)\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);

            ResultSet rs = pstm.executeQuery();
            Function function = new Function();
            System.out.printf("|%-5s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-10s\n",
                    "ID",
                    "FunctionName",
                    "TopicName",
                    "FeatureName",
                    "AccessRoles",
                    "Description",
                    "OwnerName",
                    "Status");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                function = new Function(rs.getInt(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(8), rs.getString(7), rs.getInt(9));

                System.out.println(function);

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

   public static Function ProFunctionId(int id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "select a.function_id,a.team_id,b.topic_name,a.function_name,a.feature_id,c.feature_name,a.access_role,a.description,a.complexity_id,a.owner_id,a.priority,a.status \n"
                    + "from function as a \n"
                    + "left outer join team as b on a.team_id = b.team_id\n"
                    + "left outer join feature as c on a.feature_id = c.feature_id \n"
                    + "where a.function_id = ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Function c = new model.Function(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12));
                return c;
            }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }

    public static void UpdateFunction(model.Function c) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();

            String sql = "UPDATE studentproject.function\n"
                    + "SET "
                    + "team_id = ?,"
                    + "function_name = ?,"
                    + "feature_id = ?,"
                    + "access_roles = ?,"
                    + "description = ?,"
                    + "complexity_id = ?,"
                    + "owner_id = ?,"
                    + "priority = ?,"
                    + "status = ?\n"
                    + "WHERE function_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(10, c.getFunction_id());
            pstm.setInt(1, c.getTeam_id());
            pstm.setString(2, c.getFunction_name());
            pstm.setInt(3, c.getFeature_id());
            pstm.setString(4, c.getAccess_role());
            pstm.setString(5, c.getDescription());
            pstm.setString(6, c.getComplexity_id());
            pstm.setString(7, c.getOwner_id());
            pstm.setInt(8, c.getPriority());
            pstm.setInt(9, c.getStatus());
            System.out.println(pstm);
            int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static void UpdateStatus(int i, String ID) throws SQLException, ClassNotFoundException{
        try{
        Connection connection = ConnectionUtils.getMyConnection(); 
        String sql = "update function as a join team as b on a.team_id = b.team_id\n"
              +"Set a.status = ? "
              +"WHERE b.topic_name = ?;";
       
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

    public static List<String> StatusOnlList() throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select function_id,function_name from class where status = 1;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);

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

    public static boolean SearchFeatureDonl(int CId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from class where feature_id = ? and status = 1";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, CId);
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

    public static boolean searchTeamTopic( String topic) throws SQLException, ClassNotFoundException{
        try{
       Connection connection = ConnectionUtils.getMyConnection();
   
     
      
      String sql = "Select * from function as a join team as b on a.team_id = b.team_id where  topic_name = ? ; ";
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
    
    public static boolean CheckClID(int classId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String sql = "Select * from functions where function_id = ? and status = 1";
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

    public static List<String> teamOfflList() throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.team_id, a.topic_code from team as a inner join class as b on a.class_id = b.class_id where a.status = 1 and b.trainer_id = 2;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);

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

    public static List<String> featureOfflList() throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select * from feature as a join team as b on a.team_id = b.team_id\n"
                    + "join class as c on b.class_id = c.class_id where a.status = 1 and c.trainer_id = 2;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);

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

    public static int countAcStatus(String status) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from studentproject.function where status = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, "%" + status + "%");

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

    public static void StatusDisplayByPage(int n, String status) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.function_id asc) as rn,\n"
                    + "b.topic_name,"
                    + "a.function_name,"
                    + "c.feature_name,"
                    + "a.access_roles,"
                    + "a.description,"
                    + "a.complexity_id,"
                    + "a.owner_id,"
                    + "a.priority,"
                    + "a.status from function as a \n"
                    + "left outer join team as b on a.team_id = b.team_id "
                    + "left outer join feature as c on a.feature_id = c.feature_id "
                    + "where a.status like ? )\n"
                    + "select * from t where rn between ? and ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + status + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);

            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-15s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                    "team_topic ",
                    "function_name",
                    "feature_name",
                    "access_roles",
                    "description ",
                    "complexity_id",
                    "owner_id",
                    "priority ",
                    "status ");
            Function w = new Function();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setTopic_name(rs.getString(2));
                w.setFunction_name(rs.getString(3));
                w.setFeature_name(rs.getString(4));;
                w.setAccess_role(rs.getString(5));
                w.setDescription(rs.getString(6));
                w.setComplexity_id(rs.getString(7));
                w.setOwner_id(rs.getString(8));
                w.setPriority(rs.getInt(9));
                w.setStatus(rs.getInt(10));
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

}
