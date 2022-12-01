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
import dao.DaoUser;
import view_inputs.Inputter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.User;

public class UserController {

    public void showPagination() throws SQLException, ClassNotFoundException {
        int count = DaoUser.countUser("");
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoUser.UserDisplayByPage(1, "");
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoUser.UserDisplayByPage(noPage, "");
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                        DaoUser.UserDisplayByPage(noPage, "");
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                        DaoUser.UserDisplayByPage(noPage, "");
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }

    public void searchByRollNumber() throws SQLException, ClassNotFoundException {
        String Roll = Inputter.inputNonBlankStr("Enter a part of RollNumber(HE000000): ");
        int count = DaoUser.countUser(Roll);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;

        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoUser.UserDisplayByPage(noPage, Roll);
        }
    }

    public void UpdateUser() throws SQLException, ClassNotFoundException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\nUpdating User--------- ");
        int ID = Inputter.inputInt("Input User ID you want to update: ", 0, Integer.MAX_VALUE);
        while (DaoUser.SearchUID(ID) == false) {

            ID = Inputter.inputInt("Wrong User ID, reenter: ", 0, Integer.MAX_VALUE);

        }
        User user = DaoUser.findProId(ID);
        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
        String[] TypeStatus = {"Off", "Onl"};
        String[] TypeGender = {"Male", "Female", "Other"};
        String[] TypeRole = {"Student", "Trainer", "Admin", "Manager"};
        System.out.println("ID: " + String.valueOf(user.getUserId()));
        System.out.println("Role Number: " + user.getRollNumber());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Gender: " + TypeGender[user.getGender()]);
        System.out.println("Date of Bitrh: " + SDF.format(user.getDOB()));
        System.out.println("Email: " + user.getEmail());
        System.out.println("Mobile: " + user.getMobile());
        System.out.println("Avatar Link: " + user.getAvtLink());
        System.out.println("Facebook Link: " + user.getFbLink());
        System.out.println("Role: " + TypeRole[user.getRoleId()]);
        System.out.println("Status: " + TypeStatus[user.getStatus()]);

        String roll_num = Inputter.inputPattern("Enter Roll number[1 HE160570](1 ..-update,2-not update): ", "Wrong RollNumber format! Please re-Enter: ", "[1][ ][A-Za-z]{2}[\\d]{6}|[2]");
        char op = roll_num.charAt(0);
        if(op=='2') roll_num = user.getRollNumber();
        else roll_num = roll_num.trim().substring(1);
        
        String full_name = Inputter.inputBlankStr("Enter Full Name(1 ..-update,2-not update): ");
        op = full_name.charAt(0);
        if(op=='2') full_name = user.getFullName();
        else full_name = full_name.trim().substring(1);
        
        String gender = Inputter.inputPattern("Input Gender[1-Male,2-Female](1 ..-update,2-not update): ","Must be 1 or 2, re-enter: ", "[1][ ][12]|[2]");
        op = gender.charAt(0);
        if(op=='2') gender = String.valueOf(user.getGender());
        else gender = gender.trim().substring(1);
        
        int op1 = Inputter.inputInt("Do u want to update date of birth(1-yes,2-no): ", 1, 2);
        Date dob = null;
        if(op1==1) dob = Inputter.getDate("Enter DOB(yyyy/MM/dd): ");
        else dob = user.getDOB();
        
        String mobile = Inputter.inputPattern("Enter phone number [9-11 num](1 ..-update,2-not update,0-null): ", "Wrong PhoneNumber format! Please re-Enter: ", "[1][ ][\\d]{9,11}|[20]");
        op = gender.charAt(0);
        if(op=='2') mobile = user.getMobile();
        if(op=='0') mobile=null;   
        else mobile = mobile.trim().substring(1);
        
        String avt_link = Inputter.inputNonBlankStr("Input Avatar link(1 ..-update,2-not update,0-null): ");
        op = avt_link.charAt(0);
        if(op=='2') avt_link = user.getAvtLink();
        if(op=='0') avt_link=null;
        else avt_link = avt_link.trim().substring(1);
        
        String fb_link = Inputter.inputNonBlankStr("Input Fb link(1 ..-update,2-not update,0-null): ");
        op = fb_link.charAt(0);
        if(op=='2') fb_link = user.getFbLink();
        if(op=='0') fb_link=null;
        else fb_link = fb_link.trim().substring(1);
        
        String role_id = Inputter.inputPattern("Enter Role Id[1-Stud, 2-Train,3-Admin,4-Mana]: ", "Must be (1,2,3,4), re-enter: ", "[1][ ][1234]|[2]");
        op = role_id.charAt(0);
        if(op=='2') role_id = String.valueOf(user.getRoleId());
        else role_id = role_id.trim().substring(1);
        
        String status = Inputter.inputPattern("Enter Role Id[1-Stud, 2-Train,3-Admin,4-Mana]: ", "Must be (1,2,3,4), re-enter: ", "[1][ ][1234]|[2]");
        op = status.charAt(0);
        if(op=='2') status = String.valueOf(user.getStatus());
        else status = status.substring(1);
        User new_user = new User(ID, roll_num, full_name, Integer.parseInt(gender), dob, "", mobile, avt_link, fb_link, Integer.parseInt(role_id), "", Integer.parseInt(status));
        DaoUser.UpdateUser(new_user);
        int option;
        while (true) {

            System.out.println("-----------------------------------");
            System.out.println("1. Update another user");
            System.out.println("2. Exit");
            System.out.print("Your option: ");
            SettingController s = new SettingController();
            option = Inputter.inputInt("", 1, 2);
            switch (option) {
                case 1:
                    UpdateUser();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Again!");
                    break;

            }
        }
    }

    public void ActiveUser() throws SQLException, ClassNotFoundException {
        System.out.println("\nUpdating User--------- ");
        int ID = Inputter.inputInt("Input User ID you want to update: ", 0, Integer.MAX_VALUE);
        while (DaoUser.SearchUID(ID) == false) {
            ID = Inputter.inputInt("Wrong User ID, reenter: ", 0, Integer.MAX_VALUE);
        }
        DaoUser.UpdateStatus(1, ID);
    }

    public void InActiveUser() throws SQLException, ClassNotFoundException {
        System.out.println("\nUpdating User--------- ");
        int ID = Inputter.inputInt("Input User ID you want to update: ", 0, Integer.MAX_VALUE);
        while (DaoUser.SearchUID(ID) == false) {
            ID = Inputter.inputInt("Wrong User ID, reenter: ", 0, Integer.MAX_VALUE);
        }
        DaoUser.UpdateStatus(0, ID);
    }
}
