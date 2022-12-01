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
import model.Feature;
import model.Team;

/**
 *
 * @author Dinh Quoc Tung
 */
public class DaoFeature {

    public static void TeamDisplay() throws SQLException, ClassNotFoundException {
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "Select team_id, class_id, topic_name from team";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            ResultSet rs = statement.executeQuery(sql);
            Team w = new Team();
            System.out.printf("%-20s%-20s%-20s\n",
                    "team_id", "class_id", "topic_name");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setTeamId(rs.getInt(1));
                w.setClass_id(rs.getInt(2));
                w.setTopicName(rs.getString(3));
                System.out.printf("%-20s%-20s%-20s\n",
                        w.getTeamId(), w.getClass_id(), w.getTopicName());

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static boolean SearchFeatureID(int featureID) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from feature where feature_id = ? ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, featureID);

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
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Team b = new Team(1,1,"","","",1);
//        DaoTeam.AddTeam(b);
//    }

    public static void AddFeature(Feature b) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "insert into Feature \n"
                    + "value ( ?" + ",?" + ", ?" + ",?);";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, b.getFeature_id());

            pstm.setInt(2, b.getTeam_id());

            pstm.setString(3, b.getFeature_name());

            pstm.setInt(4, b.getStatus());

            int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static void UpdateFeature(Feature b) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "Update feature \n"
                    + "Set team_id = ?"
                    + ",feature_name = ?"
                    + ",status = ?\n"
                    + "WHERE feature_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, b.getTeam_id());

            pstm.setString(2, b.getFeature_name());

            pstm.setInt(3, b.getStatus());

            pstm.setInt(4, b.getFeature_id());

            int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static void DisplayByStatus(int i) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String[] TypeStatus = {"inactive", "active"};
            String ms = "Select feature_id, status from subject"
                    + "\nwhere status = ? ; ";
            PreparedStatement pstm = connection.prepareStatement(ms);
            pstm.setInt(1, i);
            ResultSet rs = pstm.executeQuery();
            Feature w = new Feature();

            System.out.printf("%-10s%-10s\n",
                    "ID", "Status");
            while (rs.next()) {
                w.setFeature_id(rs.getInt(1));
                w.setStatus(rs.getInt(2));

                System.out.printf("%-10s%-10s\n",
                        w.getFeature_id(), TypeStatus[w.getStatus()]);

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
            String sql = "update Feature\n"
                    + "Set status = ? "
                    + "WHERE feature_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, i);
            pstm.setInt(2, ID);

            System.out.println(pstm);
            int rr = pstm.executeUpdate();
            System.out.println("status change successful");

        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static void FeatureDisplayByPage(int n, String Subname) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with x as(select ROW_NUMBER() over (order by feature_id asc) as ss, "
                    + " team_id "
                    + ",feature_name "
                    + ",status "
                    + " from studentproject.feature where feature_id like ? ) \n"
                    + "select * from x where ss between ? and ? ;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + Subname + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);

            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-20s%-20s%-20s%-20s\n",
                    "feature_id",
                    " team_id ",
                    "feature_name ",
                    "status ");
            Feature w = new Feature();
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setFeature_id(rs.getInt(1));
                w.setTeam_id(rs.getInt(2));
                w.setFeature_name(rs.getString(3));
                w.setStatus(rs.getInt(4));
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

    public static void TeamDisplayByPage(int n, int TeamID) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with x as(select ROW_NUMBER() over (order by feature_id asc) as ss, "
                    + " team_id "
                    + ",feature_name "
                    + ",status "
                    + " from studentproject.feature where team_id like ? ) \n"
                    + "select * from x where ss between ? and ? ;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, TeamID);
            pstm.setInt(2, start);
            pstm.setInt(3, end);
            System.out.println("" + pstm);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-20s%-20s%-20s%-20s\n",
                    "featuer_id",
                    " team_id ",
                    "feature_name ",
                    "status ");
            Feature w = new Feature();
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setFeature_id(rs.getInt(1));
                w.setTeam_id(rs.getInt(2));
                w.setFeature_name(rs.getString(3));
                w.setStatus(rs.getInt(4));
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

    public static int countFeatureByTeam(int ID) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from studentproject.feature where team_id = ?;";
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

    public static int countFeature() {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from studentproject.feature;";

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
                    + " team_id "
                    + ",feature_name "
                    + ",status "
                    + " from studentproject.feature where status like ? ) \n"
                    + "select * from x where ss between ? and ? ;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, status);
            pstm.setInt(2, start);
            pstm.setInt(3, end);
            System.out.println("" + pstm);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-10s%-20s%-20s%-20s\n",
                    "feature_id",
                    " team_id ",
                    "feature_name ",
                    "status ");
            Feature w = new Feature();
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w.setFeature_id(rs.getInt(1));
                w.setTeam_id(rs.getInt(2));

                w.setFeature_name(rs.getString(3));

                w.setStatus(rs.getInt(4));
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

            String sql = "select count(*) from studentproject.team where status = ?;";

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

            String sql = "select count(*) from studentproject.feature where status = ?;";

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
}
