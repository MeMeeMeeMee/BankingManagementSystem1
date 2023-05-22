package BankingManagementSystem;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Bankingapplication1 {
    public static void main(String[] args) {
        int option = 0;
        // สร้างตัวแปร 3 ตัว เพื่อรับค่าจากผู้ใช้ แล้วส่งไปที่ account
        int number ;
        String name;
        Double balance;
        Double amount;

        Bank bank = new Bank(); //สร้าง object ของ class bank
        Account account; //ประกาศตัวแปร
        Scanner scan = new Scanner(System.in);
        while(option != 6){//6 จบ
            System.out.println("Main Menu");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Exiting Account");
            System.out.println("4. Deposit");
            System.out.println("5. withdraw");
            System.out.println("6. Exit");
            System.out.println();

            System.out.print("Enter your choice:");
            option = scan.nextInt(); //รับค่า option
            scan.nextLine(); //


        switch(option){ // switch case ต้องมี break เสมอ
            case 1:
                bank.listAccounts();
                break;
            case 2:
                System.out.print("Enter Account Name: ");
                name = scan.nextLine(); //แสดงค่า String

                System.out.print("Enter Initial Balance: "); //ยอดคงเหลือเบื้องต้น
                balance = scan.nextDouble(); //แสดงค่า Double

                number = ganerateAccountNumber(); //แสดงค่า Random

              account = new Account(number,name,balance); //object ของ class account
                bank.openAccount(account); //สาารถเปิดบัญชีได้แล้ว
                break;
            case 3 :
                System.out.print("Enter Account Number: ");
                number = scan.nextInt(); // ใช้้ nextInt เพราะ number กำหนดไว้เป็น Int
                bank.closeAccount(number); // number คือ หมายเลขบัญชี เอามาจากผู้ใช้
                break;
            case 4 :
                System.out.print("Enter Account Number: ");
                number = scan.nextInt();

                System.out.print("Enter Amount: ");
                amount = scan.nextDouble(); //รับ amount จากผู้ใช้ ให้ผู้ใช้กรอก
                bank.depositMoney(number, amount); // ฝากเงินเข้าไปให้บัญชี
                break;
            case 5 :
                System.out.print("Enter Account Number: ");
                number = scan.nextInt();

                System.out.print("Enter Amount: ");
                amount = scan.nextDouble(); //รับ amount จากผู้ใช้ ให้ผู้ใช้กรอก
                bank.withdrawMoney(number, amount); // ถอดเงินออกไปให้บัญชี
                break;
        }
            System.out.println();
        }
    }

    public static int ganerateAccountNumber(){   //Random เลข 6 หลัก
        Random rand = new Random();
        int number = 100000 + rand.nextInt(900000);
        return number;
    }
}
