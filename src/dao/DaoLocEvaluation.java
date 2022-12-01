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
import static java.util.Locale.filter;
import model.ClassUser;
import model.Tracking;
import model.Issue;
import model.LocEvaluation;

/**
 *
 * @author AnhMinh
 */
public class DaoLocEvaluation {

    public static void addLocEvaluation(LocEvaluation l) throws SQLException, ClassNotFoundException {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "insert into loc_evaluation(tracking_id,evaluation_time,evaluation_note,complexity_id,quality_id) \n"
             + "value ( ?" + ",now()" + ", ?" + ",?" + ",?);";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, l.getTrackingId());

            pstm.setString(2, l.getEvaluationNote());
            pstm.setInt(3, l.getComplexityId());
            pstm.setInt(4, l.getQualityId());

            int rs = pstm.executeUpdate();
            System.out.println("Insert Sucessfully!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static void updateLocEvaluation(LocEvaluation l) throws SQLException, ClassNotFoundException {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "Update loc_evaluation \n"
             + "Set tracking_id = ?"
             + ",evaluation_note = ?"
             + ",complexity_id = ?"
             + ",quality_id = ?\n"
             + "WHERE evaluation_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, l.getTrackingId());

            pstm.setString(2, l.getEvaluationNote());
            pstm.setInt(3, l.getComplexityId());

            pstm.setInt(4, l.getQualityId());
            pstm.setInt(5, l.getEvaluationId());

            int rs = pstm.executeUpdate();
            System.out.println(pstm);
            System.out.println("Update complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static void deleteLocEvaluation(int id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String sql = "delete from loc_evaluation \n"
             + "WHERE evaluation_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            int rs = pstm.executeUpdate();
            System.out.println("Evaluation deleted!");

        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static void locEvaluationDisplayByPageTrainer(int n, String field, String filter, int id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.evaluation_id asc) as rn,\n"
             + "a.evaluation_id,"
             + "a.tracking_id,"
             + "a.evaluation_time,"
             + "a.evaluation_note,"
             + "a.complexity_id,"
             + "a.quality_id"
             + " from loc_evaluation as a \n"
             + "left outer join tracking as b on a.tracking_id = b.tracking_id\n"
             + "left outer join team as c on b.team_id = c.team_id "
             + "left outer join class as d on c.class_id = d.class_id "
             + "where d.trainer_id = " + String.valueOf(id) + " and " + field + " like ?  )\n"
             + "select * from t where rn between ? and ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);

            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n",
             "Id", "Tracking ", "Time ", "Note", "Complex ", "quality");
            LocEvaluation l;
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                l = new LocEvaluation(rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
                System.out.println(rs.getInt(2) + "\t       " + l);

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static void locEvaluationDisplayByPageStudent(int n, String field, String filter, int id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.evaluation_id asc) as rn,\n"
             + "a.evaluation_id,"
             + "a.tracking_id,"
             + "a.evaluation_time,"
             + "a.evaluation_note,"
             + "a.complexity_id,"
             + "a.quality_id"
             + " from loc_evaluation as a \n"
             + "left outer join tracking as b on a.tracking_id = b.tracking_id\n"
             + "left outer join team as c on b.team_id = c.team_id "
             + "left outer join class_user as d on c.class_id = d.class_id "
             + "where d.user_id = " + String.valueOf(id) + " and " + field + " like ?  )\n"
             + "select * from t where rn between ? and ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);

            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n",
             "Id", "Tracking ", "Time ", "Note", "Complex ", "quality");
            LocEvaluation l;
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                l = new LocEvaluation(rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
                System.out.println(rs.getInt(2) + "\t       " + l);

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static int countLocEvaluationStudent(String field, String filter, int id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from loc_evaluation as a left outer join tracking as b on a.tracking_id = b.tracking_id\n"
             + " left outer join team as c on b.team_id = c.team_id "
             + " left outer join class_user as d on c.class_id = d.class_id "
             + " where d.user_id = " + String.valueOf(id) + " and " + field + " like ?;";
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

    public static int countTeamStudent(String field, String filter) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();
            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from loc_evaluation as a left outer join tracking as b on a.tracking_id = b.tracking_id\n"
             + " left outer join team as c on b.team_id = c.team_id "
             + " left outer join class_user as d on c.class_id = d.class_id "
             + " where " + field + " like ?;";
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
    public static int countTeamTrainer(String field, String filter) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();
            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from loc_evaluation as a left outer join class as b on a.class_id = b.class_id\n"
             + " left outer join team as c on a.team_id = c.team_id "
             + " left outer join milestone as d on a.milestone_id = d.milestone_id "
             + "left outer join functions as e on a.function_id = e.function_id "
             + "left outer join user as f on a.assignee_id = f.user_id " + " " + field + " like ?;";
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

    public static int countLocEvaluationTrainer(String field, String filter, int id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from loc_evaluation as a left outer join tracking as b on a.tracking_id = b.tracking_id\n"
             + " left outer join team as c on b.team_id = c.team_id "
             + " left outer join class as d on c.class_id = d.class_id "
             + " where d.Trainer_id = " + String.valueOf(id) + " and " + field + " like ?;";
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

    public static LocEvaluation ProUemail(int id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "select * from loc_evaluation \n"
             + "where evaluation_id = ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                LocEvaluation l = new LocEvaluation(rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
                return l;
            }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }

    public static List<String> ListTrackingTrainer(int id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select a.tracking_id,b.topic_code,c.class_code from tracking as a join team as b on a.team_id = b.team_id  "
             + "  join class  c on b.class_id = c.class_id"
             + "   where c.trainer_id = ? and a.status !=0;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.

                list.add(rs.getInt(1) + "-" + rs.getString(2) + "-" + rs.getString(3));

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

    public static boolean SearchTracking(int trackingId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from tracking where tracking_id = ?; ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, trackingId);

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

    public static boolean SearchClassID(int trackingId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from Tracking where tracking_id = ?; ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, trackingId);

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
