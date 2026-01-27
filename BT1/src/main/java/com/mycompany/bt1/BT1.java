/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bt1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author xnguy
 */
public class BT1 {

    public static void main(String[] args) {
            List<book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);
        String msg = """
                Chuong trinh quan ly sach
                    1. Them 1 cuon sach
                    2. Xoa 1 cuon sach
                    3. Thay doi sach
                    4. Xuat thong tin
                    5. Tim sach Lap trinh
                    6. Lay sach toi da theo gia
                    7. Tim kiem theo tac gia
                    0. Thoat
                Chon chuc nang: """;

        int chon = 0;
        do {
            System.out.printf(msg);
            chon = x.nextInt();
            switch (chon) {
                case 1 -> {
                    book newBook = new book();
                    newBook.input();
                    listBook.add(newBook);
                }
                case 2 -> {
                    System.out.print("Nhap vao ma sach can xoa:");
                    int bookid = x.nextInt();
                    // kiem tra ma sach
                    // Luu y: orElseThrow() se vang loi neu khong tim thay sach
                    book find = listBook.stream().filter(p -> p.getId() == bookid).findFirst().orElseThrow();
                    listBook.remove(find);
                    System.out.print("Da xoa sach thanh cong");
                }
                case 3 -> {
                    System.out.print("Nhap vao ma sach can dieu chinh:");
                    int bookid = x.nextInt();
                    book find = listBook.stream().filter(p -> p.getId() == bookid).findFirst().orElseThrow();
                    // Doan nay trong anh dung o day, chua co xu ly sua thong tin
                }
                case 4 -> {
                    System.out.println("\n Xuat thong tin danh sach ");
                    listBook.forEach(p -> p.output());
                }
            }
        } while (chon != 0);
    }
    
}
