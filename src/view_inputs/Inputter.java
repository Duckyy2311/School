/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_inputs;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Inputter {

    
    public static Scanner sc = new Scanner(System.in);
    // Nhap so trong khoang min - max
    public static int inputInt(String msg, int min, int max){
 
        int data = 0;
        do{
           try{
                System.out.print(msg);
                data = Integer.parseInt(sc.nextLine());
                if(data<min || data >max)
                    System.err.println("Please enter number between "+min +" -> "+max);
            }catch(Exception e){
                System.err.println("Please enter int number");
            }
        } while (data<min || data >max);
       
        return data;
    }
    
    //nhap 1 so thuc
    public static double inputDouble(String msg,double min,double max){
        double data = 0;
        do{
           try{
                System.out.print(msg);
                data = Double.parseDouble(sc.nextLine());
                if(data<min || data >max)
                    System.err.println("Please enter number between "+min +" -> "+max);
            }catch(Exception e){
                System.err.println("Please enter double number");
            }
        } while (data<min || data >max);                 
        return data;
    }
    
    
    //nhap xau nonBlank
    public static String inputNonBlankStr(String msg){
        String data;
        do{
            System.out.print(msg);
            data = sc.nextLine().trim();
        }
        while (data.length()==0);
        return data;
    }
    public static String inputBlankStr(String msg){
        String data;
        do{
            System.out.print(msg);
            data = sc.nextLine();
        }
        while (data.trim().length()==0);
        return data;
    }
    public static boolean inputNonBlankStrDup(String msg,String dup){
        String data;        
            System.out.print(msg);
            data = sc.nextLine().trim();
        if (data.equals(dup)) 
        return true;
        else return false;
    }
    
    //nhap xau theo mau
    public static String inputPattern(String msg,String msg2, String pattern){
        String data;
        do{
            System.out.print(msg);
            data = sc.nextLine().trim();
            if(!data.matches(pattern))
                System.err.println(msg2);
        }
        while(!data.matches(pattern));
        return data;
    }        
    //nhap date theo dinh dang
    public static Date getDate(String msg) {
        Date d = null;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        SDF.setLenient(false);
        //true: 30/2 -> 1/3
        //false: 30/2 -> false
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                d = SDF.parse(sc.nextLine());
                if (checkAge(d) == false) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.err.print("Invalid date, please input again: ");
            }
        }
        return d;
    }
    
    public static boolean checkAge(Date xDOB) throws ParseException {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd"); // parse Str -> Date SDF.parse("String)
                                                                   //SDF.format(Date) tra ve String
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        // LocalDateTime -> String theo format
        Date today = SDF.parse(dtf.format(now));
        if (today.after(xDOB)) {
            return true;
        } else {
            return false;
        }
    }
    public static Date getDateFur(String msg) {
        Date d = null;
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        SDF.setLenient(false);
        //true: 30/2 -> 1/3
        //false: 30/2 -> false
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                d = SDF.parse(sc.nextLine());
                break;
            } catch (Exception e) {
                System.err.print("Invalid date, please input again: ");
            }
        }
        return d;
    }    



    
}
