package BankingManagementSystem;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {
    private String name;

    public Bank(){ //Constructor เปล่าๆ

    }

    public Bank(String name) { //Constructor Bank(name : String)

        this.name = name;
    }
    public void listAccounts() {
        Connection con = BankingConnection.connect();
        Statement statement;
        try {
            statement = con.createStatement();
            String sql ="SELECT * FROM account";
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                System.out.println(results.getString(1)+" "+results.getString(2)+" "+
                        results.getString(3));
            }
        } catch (SQLException e) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    public void openAccount(Account account) {
        Connection con = BankingConnection.connect();
        String sql = "insert into account (accNumber,accName,accBalance)"
                + "values(?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeAccount(int number) {//หมายเลขบัญชี
        Connection con = BankingConnection.connect();
        String sql = "delete from account where accNumber = ?"; // ? ซ่อนแค่ตำแหน่งเดียว
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void depositMoney(int number, double amount) {
        Account account = getAccount(number); //ดึงข้อมูลของ account ออกมา รู้บัญชี รู้ปริมาณเงิน
        account.deposit(amount);
        Connection con = BankingConnection.connect();
        String sql = "update account set accBalance = ? where accNumber = ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getNumber());
            System.out.println("Balance :" + account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void withdrawMoney(int number, double amount) {
        Account account = getAccount(number); //ดึงข้อมูลของ account ออกมา รู้บัญชี รู้ปริมาณเงิน
        account.withdraw(amount);
        Connection con = BankingConnection.connect();
        String sql = "update account set accBalance = ? where accNumber = ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getNumber());
            System.out.println("Balance :" + account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Balance:" + account.getBalance());


    }
    public Account getAccount(int number) { //ต้องระบุหมายเลยบัญชี
        Connection con = BankingConnection.connect();
        String sql ="SELECT * FROM account WHERE accNumber=?";
        PreparedStatement preparedStatement;
        Account account = null;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,number);
            ResultSet result = preparedStatement.executeQuery();

            result.next();
            String accName = result.getString(2);
            Double balance = result.getDouble(3);
            account = new Account(number, accName,balance);

        } catch (SQLException e) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE,null,e);
        }



        return account;
    }



}
