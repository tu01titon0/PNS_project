package com.company.client;

import com.company.model.TaiKhoan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DoiMatKhau {

  public static boolean doiMatKhau(TaiKhoan taiKhoan,PrintWriter writer, BufferedReader reader) throws IOException {
     Scanner scan = new Scanner(System.in);
     String matKhauCu, matKhauMoi,xacNhanMatKhauMoi,result;

     // 1. Nhập mật khẩu cũ
     while (true) {
        System.out.print("Nhập Mật khẩu cũ: ");
        matKhauCu = scan.nextLine();
        // Kiểm tra
        // - Kiểm tra không cho nhập rỗng
        if (matKhauCu.length() > 0) {
          break;
        } else {
          System.out.println("Phải nhập");
        }
     }

     // 2. Nhập mât khẩu mới
     while (true) {
        System.out.print("Nhập Mật khẩu mới: ");
        matKhauMoi = scan.nextLine();
        if (matKhauMoi.length() > 0) {
          break;
        } else {
          System.out.println("Phải nhập");
        }
     }

     // 3. Nhập xác nhận mật khẩu mới
     while (true){
        System.out.print("Xác Nhận Mật Khẩu Mới: ");
        xacNhanMatKhauMoi = scan.nextLine();

        if (xacNhanMatKhauMoi.equals(matKhauMoi)) {
          break;
        } else {
          System.out.println("Nhập lại xác nhận mật khẩu mới");
        }
     }

     // 4. Gửi dữ liệu lên Server
    writer.println(2);
    writer.println(taiKhoan.getSoTaiKhoan());
    writer.println(matKhauCu);
    writer.println(matKhauMoi);


   // 5. Nhận dữ liệu từ Server

    result = reader.readLine();
    if (result.equals("success")) {
      System.out.println("Bạn đã đổi mật khẩu thành công:");
      return true;
    }
    return false;
  }
}
