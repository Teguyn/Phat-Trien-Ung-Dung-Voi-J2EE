/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bt1;

import java.util.Scanner;

/**
 *
 * @author xnguy
 */
public class book {
    private int id;
    private String title;
    private String author;
    private long price;
    
    public book(){

}

public book ( int id, String title, String author, long price ){
    this.id = id;
    this.title = title;
    this.author = author;
    this.price = price;
    
}

public int getId(){
    return id;
}

public void setId(int id){
    this.id = id;
}

public String getTitle(){
    return title;
    
}

public void setTitle(String title){
    this.title = title;
} 

public String getAuthor(){
    return author ;
}

public void setAuthor(String author){
    this.author =author;
}

public long getPrice(){
    return price;
} 

public void setPrice(long price){
    this.price = price;
}
    
public void input(Scanner x){
    System.out.print("Nhap Ma Sach: ");
    this.id = Integer.parseInt(x.nextLine());
    System.out.print("Nhap Ten Sach: ");
    this.title = x.nextLine();
    System.out.print("Nhap tac gia: ");
    this.author = x.nextLine();
    System.out.print("Nhap don gia: ");
    this.price = Long.parseLong(x.nextLine());
}


public void output(){
    String msg = """
                            BOOK: id= %d, title=%s, author = %s, price= %d""".formatted(id, title, author, price);
    System.out.println(msg);
}
}
