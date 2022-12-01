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
import model.MemberEvaluation;

/**
 *
 * @author Dinh Quoc Tung
 */
public class DaoMemberEvaluation {

    public static void addMemberEvaluation(MemberEvaluation b) throws SQLException, ClassNotFoundException {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "insert into member_evaluation \n"
                    + "value ( ?" + ",?" + ", ?" + ",?" + ",? " + ",?);";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, b.getMember_eval_id());

            pstm.setInt(2, b.getEvaluation_id());

            pstm.setInt(3, b.getCriteria_id());

            pstm.setInt(4, b.getConverted_loc());

            pstm.setDouble(5, b.getGrade());

            pstm.setString(6, b.getNote());

            int rs = pstm.executeUpdate();
            System.out.println("Complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static void updateMemberEvaluation(MemberEvaluation b) throws SQLException, ClassNotFoundException {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "Update member_evaluation \n"
                    + "Set evaluation_id = ?"
                    + ",criteria_id = ?"
                    + ",converted_loc = ?"
                    + ",grade = ?"
                    + ",note = ?"
                    + "WHERE member_eval_id = ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, b.getEvaluation_id());

            pstm.setInt(2, b.getCriteria_id());

            pstm.setInt(3, b.getConverted_loc());

            pstm.setDouble(4, b.getGrade());

            pstm.setString(5, b.getNote());

            pstm.setInt(6, b.getMember_eval_id());

            int rs = pstm.executeUpdate();
            System.out.println(pstm);
            System.out.println("Update complete!");
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static void memberEvaluationDisplayByPage(int n, String field, String filter, int id, String team_id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.member_eval_id asc) as rn,\n"
                    + "a.member_eval_id,"
                    + "a.evaluation_id,"
                    + "b.criteria_name as criteria,"
                    + "a.converted_loc,"
                    + "a.grade,"
                    + "a.note "
                    + "from member_evaluation as a \n"
                    + "join  criteria as b on a.criteria_id = b.criteria_id \n"
                    + "join  iteration_evaluation as f on a.evaluation_id = f.evaluation_id "
                    + "left outer join class_user as d on f.user_id = d.user_id "
                    + "join class as g on f.class_id = g.class_id "
                    + "where g.trainer_id = " + String.valueOf(id) + " and " + field + " like ? and a.team_id like ?)\n"
                    + "select distinct member_eval_id,evaluation_id,criteria,grade,note from t where rn between ? and ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setString(2, "%" + team_id + "%");
            pstm.setInt(3, start);
            pstm.setInt(4, end);

            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-20s%-15s%-20s%-20s%-15s%-20s\n",
                    "member evaluation",
                    "evaluation ",
                    "criteria",
                    "converted loc",
                    "grade",
                    "note ");
            MemberEvaluation w;
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w = new MemberEvaluation(rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getString(6));
                System.out.println(rs.getInt(1) + "\t            " + w);

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static MemberEvaluation checkMemberEvaluation(String field, String filter, int id, String num) throws SQLException, ClassNotFoundException {

        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.member_eval_id asc) as rn,\n"
                    + "a.member_eval_id,"
                    + "a.evaluation_id,"
                    + "b.criteria_name as criteria,"
                    + "a.converted_loc,"
                    + "a.grade,"
                    + "a.note, "
                    + "from member_evaluation as a \n"
                    + "join  criteria as b on a.criteria_id = b.criteria_id \n"
                    + "join  iteration_evaluation as f on a.evaluation_id = f.evaluation_id "
                    + "left outer join class_user as d on f.user_id = d.user_id "
                    + "join class as g on f.class_id = g.class_id "
                    + "where g.trainer_id = " + String.valueOf(id) + " and " + field + " like ? )\n"
                    + "select distinct * from t where rn like ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setString(2, "%" + num + "%");
            ResultSet rs = pstm.executeQuery();

            MemberEvaluation w;
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w = new MemberEvaluation(rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getString(6));
//                w.setTeam_id(rs.getInt(8));
                return w;
            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }
    
    public static MemberEvaluation checkMember(int id) throws SQLException, ClassNotFoundException {
        try{
        Connection connection = ConnectionUtils.getMyConnection();

        String sql = "select * from member_evaluation \n" +
                    "where member_eval_id = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
        MemberEvaluation c = new MemberEvaluation(rs.getInt(2), rs.getInt(3), rs.getInt(4),rs.getDouble(5), rs.getString(6));
        c.setMember_eval_id(rs.getInt(1));
                return c;}
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }

    public static MemberEvaluation checkTeamEvaluationStudent(String field, String filter, int id, String num) throws SQLException, ClassNotFoundException {

        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.member_eval_id asc) as rn,\n"
                    + "a.team_eval_id,"
                    + "a.evaluation_id,"
                    + "b.criteria_name as criteria,"
                    + "a.grade,"
                    + "a.note, "
                    + "from member_evaluation as a \n"
                    + "join  criteria as b on a.criteria_id = b.criteria_id \n"
                    + "join  iteration_evaluation as f on a.evaluation_id = f.evaluation_id "
                    + "left outer join class_user as d on f.user_id = d.user_id "
                    + "join class as g on f.class_id = g.class_id "
                    + "where f.user_id = " + String.valueOf(id) + " and " + field + " like ? )\n"
                    + "select distinct * from t where rn like ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setString(2, "%" + num + "%");
            ResultSet rs = pstm.executeQuery();

            MemberEvaluation w;
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w = new MemberEvaluation(rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getString(6));
//          w.setTeam_id(rs.getInt(8));
                return w;
            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }
        return null;
    }

    public static void memberEvaluationDisplayByPageStudent(int n, String field, String filter, int id, String team_id) throws SQLException, ClassNotFoundException {
        int start = (n - 1) * 5 + 1;
        int end = start + 4;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "with t as(select row_number() over (order by a.member_eval_id asc) as rn,\n"
                    + "a.member_eval_id,"
                    + "a.evaluation_id,"
                    + "b.criteria_name as criteria,"
                    + "a.converted_loc,"
                    + "a.grade,"
                    + "a.note "
                    + "from team_evaluation as a \n"
                    + "join  criteria as b on a.criteria_id = b.criteria_id \n"
                    + "join  iteration_evaluation as f on a.evaluation_id = f.evaluation_id "
                    + "left outer join class_user as d on f.user_id = d.user_id "
                    + "join class as g on f.class_id = g.class_id "
                    + "where f.user_id = " + String.valueOf(id) + " and " + field + " like ? and a.team_id like ?)\n"
                    + "select distinct member_eval_id,evaluation_id,criteria,grade,note from t where rn between ? and ?;";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + filter + "%");
            pstm.setString(2, "%" + team_id + "%");
            pstm.setInt(3, start);
            pstm.setInt(4, end);

            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-20s%-15s%-20s%-20s%-15s%-20s\n",
                    "team evaluation",
                    "evaluation ",
                    "criteria",
                    "converted loc",
                    "grade",
                    "note ");
            MemberEvaluation w;
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                w = new MemberEvaluation(rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getString(6));
                System.out.println(rs.getInt(1) + "\t            " + w);

            }
            // Đóng kết nối
            connection.close();
        } catch (SQLException exp) {
            System.out.println("Exception: " + exp.getMessage());
        } catch (ClassNotFoundException exp) {
            System.out.println("Can't connect to DB: " + exp.getMessage());
        }

    }

    public static List<String> evaluationOfflList(int id, int team_id, int class_id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = " select DISTINCT  b.evaluation_id, d.class_code from member_evaluation as a join  iteration_evaluation as b on a.evaluation_id = b.evaluation_id\n"
                    + "join class_user as c on b.user_id = c.user_id join class as d on b.class_id = d.class_id \n"
                    + " where  b.user_id = ? and b.class_id =?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, class_id);
//      pstm.setInt(2, team_id);
//      pstm.setInt(3, class_id);
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

    public static List<String> searchCriteriaIDTrainer(int id, int team_id, int class_id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "  select  a.criteria_id, b.criteria_name from member_evaluation as a join  criteria as b on a.criteria_id = b.criteria_id join  iteration_evaluation as f on a.evaluation_id = f.evaluation_id\n"
                    + "join class_user as c on f.user_id = c.user_id join class as d on f.class_id = d.class_id join team as e on a.team_id = e.team_id\n"
                    + " where  d.trainer_id = ?  and f.class_id like ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, class_id);
//      pstm.setInt(2, team_id);
//      pstm.setInt(3, class_id);
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

    public static List<String> searchCriteriaIDStudent(int id, int team_id, int class_id) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "  select  a.criteria_id, b.criteria_name from member_evaluation as a join  criteria as b on a.criteria_id = b.criteria_id join  iteration_evaluation as f on a.evaluation_id = f.evaluation_id\n"
                    + "join class_user as c on f.user_id = c.user_id join class as d on f.class_id = d.class_id join team as e on a.team_id = e.team_id\n"
                    + " where  d.trainer_id = ?  and f.class_id like ?;";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, class_id);
//      pstm.setInt(2, team_id);
//      pstm.setInt(3, class_id);
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

    public static boolean searchEvaluation(int id) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = ConnectionUtils.getMyConnection();

            String sql = "Select * from iteration_evaluation where evaluation_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
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

    public static int countStudent(String field, String filter, int id, String team_id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();
            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select count(distinct a.member_eval_id) from member_evaluation as a\n"
                    + "join  criteria as b on a.criteria_id = b.criteria_id \n"
                    + " join  iteration_evaluation as f on a.evaluation_id = f.evaluation_id "
                    + " left outer join class_user as d on f.user_id = d.user_id "
                    + " join class as g on f.class_id = g.class_id "
                    + " where f.user_id = " + String.valueOf(id) + " and " + field + " like ? ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            pstm.setString(1, "%" + filter + "%");
            pstm.setString(2, "%" + team_id + "%");
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

    public static int countMemberEvaluationTrainer(String field, String filter, int id, String team_id) {
        int h = 0;
        try {
            // Lấy ra đối tượng Connection kết nối vào DB.
            Connection connection = ConnectionUtils.getMyConnection();

            // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "select distinct count(distinct a.member_eval_id) from member_evaluation as a\n"
                    + "join  criteria as b on a.criteria_id = b.criteria_id \n"
                    + " join  iteration_evaluation as f on a.evaluation_id = f.evaluation_id "
                    + " left outer join class_user as d on f.user_id = d.user_id "
                    + " join class as g on f.class_id = g.class_id "
                    + " where g.trainer_id = " + String.valueOf(id) + " and " + field + " like ? ";
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            pstm.setString(1, "%" + filter + "%");
            pstm.setString(2, "%" + team_id + "%");

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
