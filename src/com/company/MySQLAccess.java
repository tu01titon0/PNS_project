package com.company;  

import com.company.model.TaiKhoan;

import java.sql.*;
import java.text.MessageFormat;

public class MySQLAccess {
    private static final String userNameDB = "admin";
    private static final String passwordDB = "password";
    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

//    public static void main(String[] args) throws Exception {
//      System.out.println(capNhatSoDu("123",60000));
//    }
    
    public static TaiKhoan login(String soTaiKhoan, String matKhau) throws Exception {
      TaiKhoan taiKhoan =new TaiKhoan();
      // Tạo câu SQL query
      String sql = MessageFormat.format("select * from atm.TaiKhoan where SoTaiKhoan=\"{0}\" and MatKhau=\"{1}\"", soTaiKhoan, matKhau);
      ResultSet resultSet = connectDB().executeQuery(sql);
        if (resultSet.next()) {
            taiKhoan.setSoTaiKhoan(resultSet.getString(1));
            taiKhoan.setSoDu(resultSet.getDouble(6));
        }
        return taiKhoan;
    }

    public static boolean doiMatKhau(String soTaiKhoan, String matKhau, String matKhauMoi) throws Exception {
      // Tạo câu SQL query
      String sql = MessageFormat.format("update atm.TaiKhoan set MatKhau =\"{0}\" where SoTaiKhoan =\"{1}\" and MatKhau =\"{2}\"", matKhauMoi,soTaiKhoan,matKhau);
      int resultSet = connectDB().executeUpdate(sql);
      return resultSet == 1;
    }

    public static boolean capNhatSoDu(String soTaiKhoan, float soDu) throws Exception {
      // Tạo câu SQL query
      //update atm.TaiKhoan set SoDu =\"{0}\" where SoTaiKhoan =\"{1}\"

      String sql = MessageFormat.format("update atm.TaiKhoan set SoDu = {0, number, #.##} where SoTaiKhoan =\"{1}\" ", soDu, soTaiKhoan);
      int resultSet = connectDB().executeUpdate(sql);
      return resultSet == 1;
    }

    private static Statement connectDB() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        Connection connect = null;
        Statement statement = null;

        try {
            Class.forName(jdbcDriver);
            // Lêt nối với MySql  
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", userNameDB, passwordDB);
            statement = connect.createStatement();

        } catch (Exception e) {
            throw e;
        } finally {
        }
        return  statement;
    }
}

