/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bt1;
import java.util.ArrayList;
import java.util.Comparator;
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
            try {
                chon = Integer.parseInt(x.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }
            
            switch (chon) {
                case 1 -> {
                    book newBook = new book();
                    try {
                        newBook.input(x);
                        listBook.add(newBook);
                    } catch (Exception e) {
                        System.out.println("Loi nhap du lieu (vui long nhap so cho ID/Gia): " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Nhap vao ma sach can xoa:");
                    int bookid = Integer.parseInt(x.nextLine());
                    book find = listBook.stream().filter(p -> p.getId() == bookid).findFirst().orElse(null);
                    if (find != null) {
                        listBook.remove(find);
                        System.out.println("Da xoa sach thanh cong");
                    } else {
                        System.out.println("Khong tim thay sach co ma: " + bookid);
                    }
                }
                case 3 -> {
                    System.out.print("Nhap vao ma sach can dieu chinh:");
                    int bookid = Integer.parseInt(x.nextLine());
                    book find = listBook.stream().filter(p -> p.getId() == bookid).findFirst().orElse(null);
                    
                    if (find != null) {
                        System.out.println("--- Cap nhat thong tin (Enter de giu nguyen) ---");
                        
                        System.out.print("Ten sach (" + find.getTitle() + "): ");
                        String newTitle = x.nextLine();
                        if (!newTitle.isEmpty()) find.setTitle(newTitle);
                        
                        System.out.print("Tac gia (" + find.getAuthor() + "): ");
                        String newAuthor = x.nextLine();
                        if (!newAuthor.isEmpty()) find.setAuthor(newAuthor);
                        
                        System.out.print("Gia (" + find.getPrice() + "): ");
                        String newPriceStr = x.nextLine();
                        if (!newPriceStr.isEmpty()) find.setPrice(Long.parseLong(newPriceStr));
                        
                        System.out.println("Cap nhat thanh cong!");
                    } else {
                        System.out.println("Khong tim thay sach.");
                    }
                }
                case 4 -> {
                    System.out.println("\n Xuat thong tin danh sach: ");
                    listBook.forEach(p -> p.output());
                }
                case 5 -> {
                    System.out.println("--- Danh sach sach Lap trinh ---");
                    listBook.stream().filter(b -> b.getTitle().toLowerCase().contains("lap trinh") || b.getTitle().toLowerCase().contains("lập trình")).forEach(book::output);
                }
                case 6 -> {
                    System.out.println("--- Sach co gia cao nhat ---");
                    listBook.stream().max(Comparator.comparingLong(book::getPrice)).ifPresent(book::output);
                }
                case 7 -> {
                    System.out.print("Nhap ten tac gia can tim: ");
                    String authorName = x.nextLine().toLowerCase();
                    listBook.stream().filter(b -> b.getAuthor().toLowerCase().contains(authorName)).forEach(book::output);
                }
                case 0 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Chuc nang khong hop le!");
            }
        } while (chon != 0);
    }
    
}
