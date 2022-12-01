/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import connection.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Criteria;

/**
 *
 * @author Admin
 */
public class DaoCriteria {

    public static void insertCriteria(Criteria cl) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO criteria\n"
                    + "(subject_id,iteration_id,criteria_name,evaluation_weight,team_evaluation,criteria_order,max_loc,status)\n"
                    + "VALUES (?,?,?,?,?,?,?,?);";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, cl.getIterationID());

            pstm.setInt(2, cl.getSubId());

            pstm.setString(3, cl.getCriteriaName());

            pstm.setInt(4, cl.getEvaluationWeight());

            pstm.setInt(5, cl.getTeamEvaluation());

            pstm.setInt(6, cl.getCriteriaOrder());

            pstm.setInt(7, cl.getMaxLoc());

            pstm.setInt(8, cl.getStatus());

            int rs = pstm.executeUpdate();
            System.out.println("Update Success!");
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static int countCriteria(String field, String filter) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from Criteria as a left outer join iteration as b on a.iteration_id = b.iteration_id\n"
                    + "						 left outer join subject as c on a.subject_id = c.subject_id where " + field + " like ?;";
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

    public static void criteriaDisplayByPage(int n, String field, String filter) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.criteria_id asc) as rn,\n"
                    + "a.criteria_id,"
                    + "c.subject_code,"
                    + "b.iteration_name,"
                    + "a.criteria_name,"
                    + "a.evaluation_weight,"
                    + "a.team_evaluation,"
                    + "a.criteria_order, "
                    + "a.max_loc,"
                    + "a.status from criteria as a \n"
                    + "left outer join iteration as b on a.iteration_id = b.iteration_id\n"
                    + "left outer join subject as c on a.subject_id = c.subject_id "
                    + "where " + field + " like ? )\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);

            ResultSet rs = pstm.executeQuery();
            Criteria c = new Criteria();
            System.out.printf("%-10s%-15s%-15s%-20s%-25s%-20s%-20s%-10s%-15s\n",
                    "ID",
                    "Subject",
                    "Iteration",
                    "Criteria Name",
                    "Evaluation Weight",
                    "Team Evaluation",
                    "Criteria Order",
                    "Max loc",
                    "Status");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                c = new Criteria(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
                System.out.println(c);

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static Criteria proCriteriaId(int id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "select a.criteria_id,c.subject_code,b.iteration_name,a.criteria_name,a.evaluation_weight,a.team_evaluation,a.criteria_order,a.max_loc,a.status \n"
                    + "from criteria as a \n"
                    + "left outer join iteration as b on a.iteration_id = b.iteration_id\n"
                    + "left outer join subject as c on a.subject_id = c.subject_id \n"
                    + "where a.criteria_id = ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Criteria c = new Criteria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
                return c;
            }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }

    public static void updateCriteria(Criteria c) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();

            String sql = "UPDATE criteria\n"
                    + "SET "
                    + "subject_id = ?,"
                    + "iteration_id = ?,"
                    + "criteria_name = ?,"
                    + "evaluation_weight = ?,"
                    + "team_evaluation = ?,"
                    + "criteria_order = ?,"
                    + "Max_loc = ?,"
                    + "status = ?\n"
                    + "WHERE criteria_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(9, c.getCriteriaID());
            pstm.setInt(1, c.getSubId());
            pstm.setInt(2, c.getIterationID());
            pstm.setString(3, c.getCriteriaName());
            pstm.setInt(4, c.getEvaluationWeight());
            pstm.setInt(5, c.getTeamEvaluation());
            pstm.setInt(6, c.getCriteriaOrder());
            pstm.setInt(7, c.getMaxLoc());
            pstm.setInt(8, c.getStatus());
            System.out.println(pstm);
            int rs = pstm.executeUpdate();
            System.out.println("Update complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static void updateStatus(int i, int ID) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String sql = "update criteria\n"
                    + "Set status = ? "
                    + "WHERE criteria_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, i);
            pstm.setInt(2, ID);

            int rs = pstm.executeUpdate();
            System.out.println("status change successful");

        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static List<String> statusOnlList(int Id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();
            
            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select criteria_id,criteria_name from criteria where status = ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
                        pstm.setInt(1, Id);

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
        public static List<String> listCri(int Id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();
            
            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select criteria_id,criteria_order from criteria where status = ? and iteration_id = ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
                        pstm.setInt(1, 1);
                        pstm.setInt(2, Id);
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
 public static boolean search(int cId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from criteria where criteria_id = ? ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, cId);
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
    public static boolean searchCIDonl(int cId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from criteria where criteria_id = ? and status = 1";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, cId);
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

    public static boolean checkClID(int classId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String sql = "Select * from criteria where criteria_id = ? and status = 1";
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
