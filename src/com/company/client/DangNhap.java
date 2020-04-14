package com.company.client;

import com.company.model.TaiKhoan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DangNhap {

  public static boolean dangNhap(PrintWriter writer, BufferedReader reader) throws IOException {
    String taikhoan, matkhau, result;
    Scanner scan = new Scanner(System.in);
    while (true) {
      System.out.print("Nhập Account: ");
      taikhoan = scan.nextLine();
      if (taikhoan.length() > 0) {
        break;
      } else {
        System.out.println("Phải nhập");
      }
    }
    while (true) {
      System.out.print("Nhập Password: ");
      matkhau = scan.nextLine();
      if (matkhau.length() > 0)
        break;
      else
        System.out.println("Phải nhập");
    }
     // Write data để gửi lên server
    writer.println(0);
    writer.println(taikhoan);
    writer.println(matkhau);

        // Lấy data từ server
    result = reader.readLine();
    if (result.equals("success")) {
      return true;
    }
    return false;
  }
}
