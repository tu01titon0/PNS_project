package com.company;


import com.company.model.TaiKhoan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Sever {

    public final static int SERVER_PORT = 3001;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        try {
            //khởi  tạo socket
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");

            while (true) {
                try {
                    // Client kết nối đến server
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);
                    OutputStream os = socket.getOutputStream();
                    InputStream is = socket.getInputStream();

                    // Tạo Writer, Reader
                    PrintWriter writer = new PrintWriter(os, true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    // Lấy dữ liệu từ Client
                    String action = reader.readLine();
                    System.out.printf(action);

                    // Process dữ liệu hàm Perform;
                    xulyDuLieu(action, writer, reader);
                    // socket.close();
                } catch (IOException e) {
                    System.err.println(" Connection Erro: " + e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    private static void xulyDuLieu(String type, PrintWriter writer, BufferedReader reader) throws Exception {
        while (true) {
            int action = Integer.parseInt(type);
            switch (action) {
                case 0:
                    xuLylogin(writer, reader);
                    break;
                case 1:
                     break;
                case 2:
                    System.out.println("doi mat khau");
                    xuLyDoiMatKhau(writer, reader);
                     break;
                case 3:
                    System.out.println("rut tien");
                    // xuLyRutTien(writer, reader);
                     break;
                case 4:
                    System.out.println("Nap tien");
    //                    NapTien(writer, reader);
                    break;
                case 5:
                    System.out.println("Chuyen Tien");
    //                    ChuyenTien(writer, reader);
                     break;
                default:
                     break;
            }
        }
    }

    private static void xuLylogin(PrintWriter writer, BufferedReader reader) throws Exception {
        String soTaiKhoan = reader.readLine();
        String matKhau = reader.readLine();
        TaiKhoan taiKhoan = MySQLAccess.login(soTaiKhoan, matKhau);

        if (taiKhoan.getSoTaiKhoan() != null) {
            // Lấy được thông tin tài khoản từ database thành công
            // 1. Gửi "success"
            // 2. Gửi SoTaiKhoan
            // 3. Gửi SoDu
            writer.println("success");
            writer.println(taiKhoan.getSoTaiKhoan());
            writer.println(taiKhoan.getSoDu());
        } else {
            // Không có Tai Khoan
            // Gửi "fail"
            writer.println("fail");
        }

    }

    private static void xuLyDoiMatKhau(PrintWriter writer, BufferedReader reader) throws Exception {
        // Lấy dữ liệu từ client
        String action = reader.readLine();
        String soTaiKhoan = reader.readLine();
        String matKhauCu = reader.readLine();
        String matKhauMoi = reader.readLine();

        if (MySQLAccess.doiMatKhau(soTaiKhoan, matKhauCu, matKhauMoi)) {
            writer.println("success");
        } else {
            writer.println("fail");
        }
    }

    private static void xuLyRutTien(PrintWriter writer, BufferedReader reader) throws Exception {
        // Lấy dữ liệu từ client
        String action = reader.readLine();
        String soTaiKhoan = reader.readLine();
        String soTienCanRut = reader.readLine();
        String soDu = reader.readLine();

        float soDuMoi = Float.parseFloat(soDu) - Float.parseFloat(soTienCanRut);

        System.out.println(soDuMoi);

        if (MySQLAccess.capNhatSoDu(soTaiKhoan, soDuMoi)) {
            writer.println("success");
            writer.println(soDuMoi);
        } else {
            writer.println("fail");
        }
    }



    private static String NapTien(PrintWriter writer, BufferedReader reader) {
        return "";
    }

    private static String ChuyenTien(PrintWriter writer, BufferedReader reader) {
        return "";
    }
}







