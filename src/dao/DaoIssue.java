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
import model.*;

/**
 *
 * @author Acer
 */
public class DaoIssue {

    public static int countIssueForTrainer(String field, String filter, int id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

//            String sql = "select count(*) from class as a left outer join user as b on a.trainer_id = b.user_id\n"
//                    + "						 left outer join subject as c on a.subject_id = c.subject_id where " + field + " like ?;";
            String sql = "select count(*) from issue as a \n"
                    + "left outer join team as b on a.team_id = b.team_id\n"
                    + "left outer join class as c on b.class_id = c.class_id\n"
                    + "left outer join studentproject.user as d on a.assignee_id= d.user_id\n"
                    + "where c.trainer_id = ? and " + field + " like ?;";
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

            String sql = "with t as(\n"
                    + "select row_number() over (order by a.issue_id asc) as rn,"
                    + "a.issue_id,"
                    + "a.issue_title,"
                    + "b.topic_name,"
                    + "d.full_name,"
                    + "a.gitlab_url,"
                    + "a.description,"
                    + "e.function_name,"
                    + "f.milestone_name,"
                    + "a.labels,"
                    + "a.status "
                    + "from issue as a \n"
                    + "left outer join team as b on a.team_id = b.team_id\n"
                    + "left outer join class as c on b.class_id = c.class_id\n"
                    + "left outer join studentproject.user as d on a.assignee_id= d.user_id\n"
                    + "left outer join studentproject.function as e on a.function_ids = e.function_id\n"
                    + "left outer join studentproject.milestone as f on a.milestone_id = f.milestone_id\n"
                    + "where c.trainer_id = " + String.valueOf(id) + " and " + field + " like ?)\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);
            ResultSet rs = pstm.executeQuery();
            Issue issue = new Issue();
            System.out.printf("|%-5s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-12s\n",
                    "ID",
                    "IssueTitle",
                    "TopicName",
                    "AsgneeName",
                    "Git Url",
                    "Description",
                    "FunctionName",
                    "MilestoneName",
                    "Labels",
                    "Status");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                issue = new Issue(rs.getInt(2),
                        rs.getString(3),
                        rs.getString(5),
                        rs.getString(4),
                        rs.getString(7),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11));
                System.out.println(issue);

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static int countIssueForStu(String field, String filter, int id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from issue as a \n"
                    + "left outer join team as b on a.team_id = b.team_id\n"
                    + "left outer join class as c on b.class_id = c.class_id\n"
                    + "where a.team_id = ? and " + field + " like ?;";
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

    public static void displayByPageForStu(int n, String field, String filter, int id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(\n"
                    + "select row_number() over (order by a.issue_id asc) as rn,"
                    + "a.issue_id,"
                    + "a.issue_title,"
                    + "b.topic_name,"
                    + "d.full_name,"
                    + "a.gitlab_url,"
                    + "a.description,"
                    + "e.function_name,"
                    + "f.milestone_name,"
                    + "a.labels,"
                    + "a.status "
                    + "from issue as a \n"
                    + "left outer join team as b on a.team_id = b.team_id\n"
                    + "left outer join studentproject.user as d on a.assignee_id= d.user_id\n"
                    + "left outer join studentproject.function as e on a.function_ids = e.function_id\n"
                    + "left outer join studentproject.milestone as f on a.milestone_id = f.milestone_id\n"
                    + "where a.team_id = " + String.valueOf(id) + " and " + field + " like ?)\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);
            ResultSet rs = pstm.executeQuery();
            Issue issue = new Issue();
            System.out.printf("|%-5s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-12s\n",
                    "ID",
                    "IssueTitle",
                    "TopicName",
                    "AsgneeName",
                    "Git Url",
                    "Description",
                    "FunctionName",
                    "MilestoneName",
                    "Labels",
                    "Status");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                issue = new Issue(rs.getInt(2),
                        rs.getString(3),
                        rs.getString(5),
                        rs.getString(4),
                        rs.getString(7),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11));
                System.out.println(issue);

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
