/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import view_inputs.Inputter;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import connection.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.util.List;
import model.Iteration;

/**
 *
 * @author Admin
 */
public class DaoIteration {

    public static void insertIteration(Iteration cl) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");

            Statement statement = connection.createStatement();

            String sql = "INSERT INTO ispproject.iteration\n"
                    + "(iteration_id,subject_id,iteration_name,duration,status)\n"
                    + "VALUES (?,?,?,?,?);";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, cl.getIterId());

            pstm.setInt(2, cl.getSubid());

            pstm.setString(3, cl.getIterName());

            pstm.setString(4, SDF.format(cl.getDuration()));

            pstm.setInt(5, cl.getStatus());

            int rs = pstm.executeUpdate();
            System.out.println("Insert Success!");
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
    }

    public static int countIter(String field, String filter) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(*) from iteration as a left outer join subject as b on a.subject_id = b.subject_id\n"
                    + "	 where " + field + " like ?;";
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

    public static List<String> iterList() throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select iteration_id,iteration_name from iteration where status = 1;";

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
    public static List<String> iterListBySubject(int Id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select  b.subject_code,a.iteration_id,a.iteration_name from iteration a join subject b  on a.subject_id = b.subject_id  where a.status = 1 and a.subject_id =?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, Id );
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.          
                list.add(rs.getInt(2) + "-" + rs.getString(3) + "-" + rs.getString(1));

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

    public static void iterationDisplayByPage(int n, String field, String filter) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.iteration_id asc) as rn,\n"
                    + "a.iteration_id,"
                    + "b.subject_code,"
                    + "a.iteration_name,"
                    + "a.duration,"
                    + "a.status from iteration as a \n"
                    + "left outer join subject as b on a.subject_id = b.subject_id\n"
                    + "where " + field + " like ? )\n"
                    + "select * from t where rn between ? and ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, end);

            ResultSet rs = pstm.executeQuery();
            Iteration c = new Iteration();
            System.out.printf("%-7s%-12s%-12s%-15s%-15s\n",
                    "ID",
                    "Subject",
                    "Iter Name",
                    "Duration",
                    "Status");

            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                c = new Iteration(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
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

    public static Iteration proIterId(int id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "select a.Iteration_id,b.subject_code,a.iteration_name,a.duration,a.status \n"
                    + "from iteration as a \n"
                    + "left outer join subject as b on a.subject_id = b.subject_id\n"
                    + "where a.iteration_id = ?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Iteration c = new Iteration(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                return c;
            }
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }

    public static void updateIteration(Iteration c) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = ConnectionUtils.getMyConnection();
            SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
            Statement statement = connection.createStatement();

            String sql = "UPDATE ispproject.iteration\n"
                    + "SET "
                    + "subject_id = ?,"
                    + "iteration_name=?,"
                    + "duration = ?,"
                    + "status = ?\n"
                    + "WHERE iteration_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(5, c.getIterId());
            pstm.setInt(1, c.getSubid());
            pstm.setString(2, c.getIterName());
            pstm.setString(3, SDF.format(c.getDuration()));
            pstm.setInt(4, c.getStatus());
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
            String sql = "update Iteration\n"
                    + "Set status = ? "
                    + "WHERE iteration_id = ?;";

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

            String sql = "select iteration_id,iteration_name from iteration where status = ?;";

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

    public static boolean searchIterDonl(int CId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from iteration where iteration_id = ? and status = 1";
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
    public static boolean search(int CId) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from iteration where iteration_id = ?";
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

    public static boolean checkIterID(int Id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            String sql = "Select * from iteration where iteration_id = ? and status = 1";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, Id);

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
