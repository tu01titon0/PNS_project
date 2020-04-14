package com.company.client;

import com.company.model.TaiKhoan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RutTien {
  public static TaiKhoan rutTien(TaiKhoan taiKhoan, PrintWriter writer, BufferedReader reader ) throws IOException {
    Scanner scan = new Scanner(System.in);
    String soTienCanRut,result;
    while (true) {
      System.out.print("Mời bạn nhập số tiền cần rút: ");
      soTienCanRut = scan.nextLine();

      try {
        float soTien = Float.parseFloat(soTienCanRut);
        if (soTien >= taiKhoan.getSoDu()) {
          System.out.println("Bạn không đủ số dư");
        } else {
          break;
        }
      } catch (Exception e) {
        System.out.println("Nhập Lại: ");
      }
    }

    writer.println(3);
    writer.println(taiKhoan.getSoTaiKhoan());
    writer.println(soTienCanRut);
    writer.println(taiKhoan.getSoDu());


    result = reader.readLine();
    if (result.equals("success")) {
      System.out.println("Bạn đã rút tiền thành công!");
      taiKhoan.setSoDu(Double.parseDouble(reader.readLine()));
      return taiKhoan;
    } else {
      System.out.println("Rút tiền thất bại!");
    }
    return taiKhoan;
  }
}