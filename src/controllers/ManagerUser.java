/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Admin
 */
import model.User;
import view_inputs.Inputter;
import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import connection.ConnectionUtils;
import dao.DaoUser;
import static dao.DaoUser.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.xml.bind.DatatypeConverter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import sun.security.util.Password;

public class ManagerUser {

    // ham ma hoa
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String PassConvert(String Pass) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                Pass.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    // ham Login
    public boolean LoginCheck(String Email, String Pass) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        while (CheckPass(Email, PassConvert(Pass)) == false) {
            System.out.println("UserID or Password is wrong,do again");
            Email = Inputter.inputPattern("Enter Email (Ducky@gmail.com): ", "Wrong Email format! Please re-Enter: ", "^[a-zA-Z][a-zA-Z0-9]+@[a-z]+(\\.[a-z]+){1,6}$");
            Pass = Inputter.inputNonBlankStr("Enter Password: ");
        }
        if (CheckPass(Email, PassConvert(Pass))) {
            return true;
        }
        return false;
    }

    //mail
    public static boolean checkMail(String mail) throws MessagingException, UnsupportedEncodingException {
        Random generator = new Random();
        String check = "";
        for (int i = 0; i < 6; i++) {
            check += generator.nextInt(10);
        }
        EmailController E = new EmailController();
        try {
            E.Sendmail(mail, check);
        } catch (Exception e) {
            System.err.println("Exception: can not found your email or your system has fail");
        }

        String recheck = Inputter.inputBlankStr("Enter your code: ");
        if (recheck.equals(check)) {
            return true;
        } else {
            System.out.println("Your code is wrong, a new mail has sent to your mail");
            return checkMail(mail);
        }
    }

    public void Register() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, MessagingException, UnsupportedEncodingException {
        String Remail;
        while (true) {
            String newEmail = Inputter.inputPattern("Enter Email (Ducky@gmail.com): ", "Wrong Email format! Please re-Enter: ", "^[a-zA-Z][a-zA-Z0-9]+@[a-z]+(\\.[a-z]+){1,6}$");
            if (SearchUemail(newEmail) == true) {
                System.out.println("UserId is exist, do again:");
            } else {
                Remail = newEmail;
                break;
            }
        }
        String Rpass = null;
        while (true) {
            String PassWord = Inputter.inputPattern("Enter Password(at least 8 char): ", "Password is not Strong enough, re-enter: ", "^[A-Za-z\\d]{8,}$");
            if (Inputter.inputNonBlankStrDup("Re-enter Password: ", PassWord)) {
                Rpass = PassConvert(PassWord);
                break;
            } else {
                System.err.println("Wrong Re-Pass,do again: ");
            }
        }

        String RollNum = Inputter.inputPattern("Enter Roll number ('HE160570'): ", "Wrong RollNumber format! Please re-Enter: ", "[A-Za-z]{2}[\\d]{6}");
        String Fullname = Inputter.inputBlankStr("Enter Full Name: ");
        int Gender = Inputter.inputInt("Input Gender(1-Male,2-Female): ", 1, 2);
        Date DOB = Inputter.getDate("Enter DOB(yyyy/MM/dd): ");

        String Mobile = Inputter.inputPattern("Enter phone number (9 to 11 num): ", "Wrong PhoneNumber format! Please re-Enter: ", "[\\d]{9,11}");
        String AvtLink = Inputter.inputNonBlankStr("Input Avatar link: ");
        String FbLink = Inputter.inputNonBlankStr("Input Fb link: ");
        int RoleId = Inputter.inputInt("Enter Role Id(1-Stud, 2-Train,3-Admin,4-Mana): ", 1, 4);
        int Status = Inputter.inputInt("Enter Status(1-Onl,0-Off): ", 0, 1);
        User Us = new User(0, RollNum, Fullname, Gender, DOB, Remail, Mobile, AvtLink, FbLink, RoleId, Rpass, Status);
        System.out.println("sending code to your mail!");
//        if(checkMail(Remail))
        insertUs(Us);
    }

    public void ChangePass(String Email) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        String oldPass = Inputter.inputNonBlankStr("Enter old password: ");
        while (CheckPass(Email, PassConvert(oldPass)) == false) {
            System.out.println("Old Password is wrong,do again.");
            oldPass = Inputter.inputNonBlankStr("Enter old Password: ");
        }
        String newPass=null;
        if (CheckPass(Email, PassConvert(oldPass))) {
            do{
            newPass = Inputter.inputNonBlankStr("Enter new password: ");
            String rePass = Inputter.inputNonBlankStr("Re-enter new password: ");
            if(newPass.equalsIgnoreCase(rePass)) break;
            }while (true);                         
            DaoUser.updatePass(Email, PassConvert(newPass));
        }
    }

    public void resetPass() throws SQLException, ClassNotFoundException, MessagingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String Email = Inputter.inputPattern("Enter Email (Ducky@gmail.com): ", "Wrong Email format! Please re-Enter: ", "^[a-zA-Z][a-zA-Z0-9]+@[a-z]+(\\.[a-z]+){1,6}$");
        while (SearchUemail(Email) == false) {
            System.out.println("Email is not exist, try again!");
            Email = Inputter.inputPattern("Enter Email (Ducky@gmail.com): ", "Wrong Email format! Please re-Enter: ", "^[a-zA-Z][a-zA-Z0-9]+@[a-z]+(\\.[a-z]+){1,6}$");
        }
        System.out.println("Email found, sending code!");
        if (checkMail(Email) == true) {
            String Rpass = null;
            while (true) {
                String PassWord = Inputter.inputPattern("Enter Password(at least 8 char): ", "Password is not Strong enough, re-enter: ", "^[A-Za-z\\d]{8,}$");
                if (Inputter.inputNonBlankStrDup("Re-enter Password: ", PassWord)) {
                    Rpass = PassConvert(PassWord);
                    break;

                } else {
                    System.err.println("Wrong Re-Pass,do again: ");
                }
            }
            updatePass(Email, Rpass);
        }
    }

    public void findProUser(String email) throws SQLException, ClassNotFoundException {
        User user = DaoUser.findProUemail(email);
        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
        String[] TypeStatus = {"Off", "Onl"};
        String[] TypeGender = {"Male", "Female", "Other"};
        String[] TypeRole = {"Student", "Trainer", "Admin", "Manager"};
        System.out.println("ID: " + String.valueOf(user.getUserId()));
        System.out.println("Role Number: " + user.getRollNumber());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Gender: "+ TypeGender[user.getGender()]);
        System.out.println("Date of Bitrh: "+ SDF.format(user.getDOB()));
        System.out.println("Email: "+ user.getEmail());
        System.out.println("Mobile: "+user.getMobile());
        System.out.println("Avatar Link: "+ user.getAvtLink());
        System.out.println("Facebook Link: "+ user.getFbLink());
        System.out.println("Role: "+TypeRole[user.getRoleId()-1]);
        System.out.println("Status: "+TypeStatus[user.getStatus()]);
    }
}
