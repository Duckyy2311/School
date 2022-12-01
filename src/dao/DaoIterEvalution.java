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
import model.*;

/**
 *
 * @author Admin
 */
public class DaoIterEvalution {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(listTeamforStu(2, 1));
    }
    public static void isertIterEval(IterationEvalution iterationEvalution) {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO studentproject.iteration_evaluation\n"
                    + "(iteration_id,class_id,team_id,user_id,bonus,grade,note)\n"
                    + "VALUES(?,?,?,?,?,?,?);";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, iterationEvalution.getIter_id());

            pstm.setInt(2, iterationEvalution.getClass_id());

            pstm.setInt(3, iterationEvalution.getTeam_id());

            pstm.setInt(4, iterationEvalution.getUser_id());

            pstm.setInt(5, iterationEvalution.getBonus());

            pstm.setDouble(6, iterationEvalution.getGrade());

            pstm.setString(7, iterationEvalution.getNote());

            int rs = pstm.executeUpdate();
            System.out.println("Insert Success!");
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static int countIterEvalforTrainer(String field, String filter, int id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

//            String sql = "select count(*) from class as a left outer join user as b on a.trainer_id = b.user_id\n"
//                    + "						 left outer join subject as c on a.subject_id = c.subject_id where " + field + " like ?;";
            String sql = "SELECT count(*) FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "where b.trainer_id = ? and " + field + " like ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            pstm.setString(2, "%" + filter + "%");
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

    

    public static void displayByPageForTrain(int n, String field, String filter, int id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as( \n"
                    + "SELECT row_number() over (order by a.evaluation_id asc) as rn,"
                    + "a.evaluation_id,"
                    + "b.class_code,"
                    + "c.topic_name,"
                    + "d.full_name,"
                    + "e.iteration_name,"
                    + "a.bonus,"
                    + "a.grade,"
                    + "a.note\n"
                    + "FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "where b.trainer_id = ? and " + field + " like ?)\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(2, "%" + filter + "%");
            pstm.setInt(3, start);
            pstm.setInt(4, end);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            Function function = new Function();
            System.out.println("-----------Evaluation List-----------");
            System.out.printf("|%-5s|%-10s|%-10s|%-15s|%-15s|%-10s|%-10s|%-15s\n",
                    "ID",
                    "ClassCode",
                    "TeamName",
                    "FullName",
                    "Iteration",
                    "Bonus",
                    "Grade",
                    "Note");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                IterationEvalution iterationEvalution = new IterationEvalution(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
                System.out.println(iterationEvalution);
            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static int countFillterIterEvalforTrainer(String field, int filter, int id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

//            String sql = "select count(*) from class as a left outer join user as b on a.trainer_id = b.user_id\n"
//                    + "						 left outer join subject as c on a.subject_id = c.subject_id where " + field + " like ?;";
            String sql = "SELECT count(*) FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "where b.trainer_id = ? and " + field + " = ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            pstm.setInt(2, filter);
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
        public static int countFillterIterEvalforStu(String field, int filter) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

//            String sql = "select count(*) from class as a left outer join user as b on a.trainer_id = b.user_id\n"
//                    + "						 left outer join subject as c on a.subject_id = c.subject_id where " + field + " like ?;";
            String sql = "SELECT count(*) FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "where " + field + " = ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            pstm.setInt(1, filter);
            System.out.println(pstm);
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
    public static void displayFillterByPageForTrain(int n, String field, int filter, int id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as( \n"
                    + "SELECT row_number() over (order by a.evaluation_id asc) as rn,"
                    + "a.evaluation_id,"
                    + "b.class_code,"
                    + "c.topic_name,"
                    + "d.full_name,"
                    + "e.iteration_name,"
                    + "a.bonus,"
                    + "a.grade,"
                    + "a.note\n"
                    + "FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "where b.trainer_id = ? and " + field + " = ?)\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(3, start);
            pstm.setInt(4, end);
            pstm.setInt(2, filter);
            ResultSet rs = pstm.executeQuery();
            Function function = new Function();
            System.out.println("-----------Evaluation List-----------");
            System.out.printf("|%-5s|%-10s|%-10s|%-10s|%-15s|%-10s|%-10s|%-15s\n",
                    "ID",
                    "ClassCode",
                    "TeamName",
                    "FullName",
                    "Iteration",
                    "Bonus",
                    "Grade",
                    "Note");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                IterationEvalution iterationEvalution = new IterationEvalution(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
                System.out.println(iterationEvalution);
            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }
    public static void displayFillterByPageForStu(int n, String field, int filter) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as( \n"
                    + "SELECT row_number() over (order by a.evaluation_id asc) as rn,"
                    + "a.evaluation_id,"
                    + "b.class_code,"
                    + "c.topic_name,"
                    + "d.full_name,"
                    + "e.iteration_name,"
                    + "a.bonus,"
                    + "a.grade,"
                    + "a.note\n"
                    + "FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "where " + field + " = ?)\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(2, start);
            pstm.setInt(3, end);
            pstm.setInt(1, filter);
            ResultSet rs = pstm.executeQuery();
            Function function = new Function();
            System.out.println("-----------Evaluation List-----------");
            System.out.printf("|%-5s|%-10s|%-10s|%-15s|%-15s|%-10s|%-10s|%-15s\n",
                    "ID",
                    "ClassCode",
                    "TeamName",
                    "FullName",
                    "Iteration",
                    "Bonus",
                    "Grade",
                    "Note");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                IterationEvalution iterationEvalution = new IterationEvalution(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
                System.out.println(iterationEvalution);
            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }
    public static boolean checkExist(int classID, int teamID, int userID, int iterID) {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from iteration_evaluation where  class_id = ? and team_id = ? and user_id = ?  and iteration_id = ?; ";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, classID);
            pstm.setInt(2, teamID);
            pstm.setInt(3, userID);
            pstm.setInt(4, iterID);

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
   
    public static int findEval(int classID, int teamID, int userID, int iterID) {
        int evalID = 0;
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select evaluation_id from iteration_evaluation where  where  class_id = ? and team_id = ? and user_id = ?  and iteration_id = ?; ";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, classID);
            pstm.setInt(2, teamID);
            pstm.setInt(3, userID);
            pstm.setInt(4, iterID);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                evalID = rs.getInt(1);
                return evalID;

            }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return evalID;
    }

    public static List<String> listIterForTrainer(int id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.iteration_id,b.iteration_name from iteration_evaluation as a "
                    + "inner join iteration as b on a.iteration_id = b.iteration_id \n"
                    + "inner join class as c on a.class_id = c.class_id where c.trainer_id = ?;";

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

    public static List<String> listClass(int id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.class_id, b.class_code from iteration_evaluation as a "
                    + "join class as b on a.class_id = b.class_id where b.trainer_id  = ? group by a.class_id;";

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
    public static List<String> listClassforStu(int id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.class_id, b.class_code from iteration_evaluation as a "
                    + "join class as b on a.class_id = b.class_id "
                    + "where a.user_id  = ? group by a.class_id;";

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
    public static List<String> listTeam(int id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.team_id, b.topic_name from iteration_evaluation as a "
                    + "join team as b on a.team_id = b.team_id "
                    + "join class as c on a.class_id = c.class_id where c.trainer_id  = ? group by a.team_id;";

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
    public static List<String> listTeam(int id,int classID) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.team_id, b.topic_name from iteration_evaluation as a "
                    + "join team as b on a.team_id = b.team_id "
                    + "join class as c on a.class_id = c.class_id where c.trainer_id  = ? and a.class_id = ? group by a.team_id;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, classID);
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
    public static List<String> listTeamforStu(int id,int classID) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.team_id, b.topic_name from iteration_evaluation as a "
                    + "join team as b on a.team_id = b.team_id "
                    + " where a.user_id  = ? and a.class_id = ? group by a.team_id;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2,classID);
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
    public static List<String> listUser(int id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.user_id, b.full_name from iteration_evaluation as a "
                    + "join user as b on a.user_id = b.user_id "
                    + "join class as c on a.class_id = c.class_id where c.trainer_id  = ? group by a.user_id;";

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
        public static List<String> listTeamClass(int id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.team_id, b.topic_name,c.class_code from iteration_evaluation as a "
                    + "join team as b on a.team_id = b.team_id "
                    + "join class as c on a.class_id = c.class_id where c.trainer_id  = ? group by a.team_id;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                list.add(rs.getInt(1) + "-" + rs.getString(2)+"-"+rs.getString(3));

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
    public static double getTeamGrade(int eval_id) throws SQLException, ClassNotFoundException {
        double teamGrade = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.grade*b.evaluation_weight/100 from team_evaluation as a\n"
                    + "join criteria as b on a.criteria_id = b.criteria_id\n"
                    + "where a.evaluation_id = ?";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, eval_id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.          
                teamGrade = rs.getDouble(1);

            }
            System.out.println("");
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return teamGrade;
    }

    public static double getMemGrade(int eval_id) throws SQLException, ClassNotFoundException {
        double memGrade = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.grade*b.evaluation_weight/100 from member_evaluation as a\n"
                    + "join criteria as b on a.criteria_id = b.criteria_id\n"
                    + "where a.evaluation_id = ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, eval_id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.          
                memGrade = rs.getDouble(1);

            }
            System.out.println("");
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return memGrade;
    }

    public static double getBonus(int id) throws SQLException, ClassNotFoundException {
        double memGrade = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select bonus/20 from iteration_evaluation where evaluation_id = ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            System.out.println(pstm);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.          
                memGrade = rs.getDouble(1);

            }
            System.out.println("");
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return memGrade;
    }

    public static IterationEvalution getIterEval(int id) throws SQLException, ClassNotFoundException {
        IterationEvalution iterEavl = null;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "SELECT a.evaluation_id,b.class_code,c.topic_name,d.full_name,e.iteration_name,f.grade,g.grade,a.bonus,a.grade,a.note,a.team_id\n"
                    + "FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "inner join member_evaluation as f on a.evaluation_id = f.evaluation_id\n"
                    + "inner join team_evaluation as g on a.evaluation_id = g.evaluation_id\n"
                    + "where a.evaluation_id = ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            System.out.println(pstm);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                IterationEvalution iterationEvalution = new IterationEvalution(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
                iterationEvalution.setTeam_id(rs.getInt(11));
                iterEavl = iterationEvalution;
            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return iterEavl;
    }
    public static IterationEvalution getIterEvalofTrainer(int id) throws SQLException, ClassNotFoundException {
        IterationEvalution iterEavl = null;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "SELECT a.evaluation_id,b.class_code,c.topic_name,d.full_name,e.iteration_name,f.grade,g.grade,a.bonus,a.grade,a.note\n"
                    + "FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "inner join member_evaluation as f on a.evaluation_id = f.evaluation_id\n"
                    + "inner join team_evaluation as g on a.evaluation_id = g.evaluation_id\n"
                    + "where b.trainer_id = ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                IterationEvalution iterationEvalution = new IterationEvalution(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
                return iterationEvalution;
            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return iterEavl;
    }
    public static IterationEvalution getIterEvalofStu(int id) throws SQLException, ClassNotFoundException {
        IterationEvalution iterEavl = null;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "SELECT a.evaluation_id,b.class_code,c.topic_name,d.full_name,e.iteration_name,f.grade,g.grade,a.bonus,a.grade,a.note\n"
                    + "FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "inner join member_evaluation as f on a.evaluation_id = f.evaluation_id\n"
                    + "inner join team_evaluation as g on a.evaluation_id = g.evaluation_id\n"
                    + "where a.user_id = ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                IterationEvalution iterationEvalution = new IterationEvalution(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
                return iterationEvalution;
            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return iterEavl;
    }
        public static int getTeamlofTrainer(int id) throws SQLException, ClassNotFoundException {
        int team = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "SELECT a.team_id\n"
                    + "FROM iteration_evaluation as a \n"
                    + "inner join class as b on a.class_id = b.class_id\n"
                    + "inner join team as c on a.team_id = c.team_id\n"
                    + "inner join user as d on a.user_id = d.user_id\n"
                    + "inner join iteration as e on a.iteration_id = e.iteration_id\n"
                    + "inner join member_evaluation as f on a.evaluation_id = f.evaluation_id\n"
                    + "inner join team_evaluation as g on a.evaluation_id = g.evaluation_id\n"
                    + "where b.trainer_id = ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                return rs.getInt(1);
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
    public static void updateEvaluation(IterationEvalution eval) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "UPDATE iteration_evaluation\n"
                    + "SET\n"
                    + "bonus = ?,\n"
                    + "grade = ?,\n"
                    + "note = ?\n"
                    + "WHERE evaluation_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, eval.getBonus());

            pstm.setDouble(2, eval.getGrade());

            pstm.setString(3, eval.getNote());

            pstm.setInt(4, eval.getEval_id());

            int rs = pstm.executeUpdate();
            System.out.println(pstm);
            System.out.println("Update complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }  
}
