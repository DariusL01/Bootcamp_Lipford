package org.example;

import java.util.Scanner;

public class Book {

Scanner scanner = new Scanner(System.in);

    public Book(int id, String isbn, String title, boolean isCheckedOut, String checkedOutTo) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutTo = checkedOutTo;

    }

    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;


    public int getId() {
        return id;
    }


    public String getIsbn() {
        return isbn;
    }


    public String getTitle() {
        return title;
    }


    public boolean getIsCheckedOut() {
        return isCheckedOut;
    }


    public String getCheckedOutTo() {
        return checkedOutTo;
    }


    public void checkOut(String name) {
        if (isCheckedOut == false) {
            isCheckedOut = true;
            checkedOutTo = name;

        }
    }
    public void checkIn(){
            isCheckedOut = false;
            checkedOutTo = "";
    }


}
