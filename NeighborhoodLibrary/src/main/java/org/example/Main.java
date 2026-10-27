package org.example;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean noBookCheckedOut = true;
        Scanner scanner = new Scanner(System.in);


        Book[] books = {
                new Book(1, "9780547928227", "The Hobbit", false, ""),
                new Book(2, "9780061120084", "To Kill a Mockingbird", true, "John Smith"),
                new Book(3, "9780439708180", "Harry Potter and the Sorcerer's Stone", false, ""),
                new Book(4, "9780307277671", "The Road", false, ""),
                new Book(5, "9780385490818", "Fight Club", true, "Sarah Jones"),

                // Science Fiction
                new Book(6, "9780553293357", "Dune", false, ""),
                new Book(7, "9780345339683", "The Fellowship of the Ring", true, "Mike Davis"),
                new Book(8, "9780441013593", "Neuromancer", false, ""),
                new Book(9, "9780765377067", "The Three-Body Problem", false, ""),
                new Book(10, "9780345391803", "The Hitchhiker's Guide to the Galaxy", true, "Emily Brown"),

                // Mystery/Thriller
                new Book(11, "9780062073488", "Gone Girl", false, ""),
                new Book(12, "9780307454546", "The Girl with the Dragon Tattoo", false, ""),
                new Book(13, "9780062678416", "Murder on the Orient Express", true, "Chris Wilson"),
                new Book(14, "9780525536291", "The Silent Patient", false, ""),
                new Book(15, "9780307588371", "The Da Vinci Code", false, ""),

                // Nonfiction
                new Book(16, "9781455586691", "Atomic Habits", true, "Jessica Lee"),
                new Book(17, "9781501124020", "Educated", false, ""),
                new Book(18, "9780812981605", "Sapiens", false, ""),
                new Book(19, "9780062316110", "The Alchemist", true, "David Clark"),
                new Book(20, "9781982137274", "The Psychology of Money", false, "")
        };

        while (noBookCheckedOut == true) {
            System.out.println("\n=== BOOK STORE HOME SCREEN ===");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    System.out.println("Available Books" + "\n");
                    for (int i = 0; i < books.length; i++) {
                        if (!books[i].getIsCheckedOut()) {
                            System.out.println("Title: " + books[i].getTitle() + ", " + "ID: " + books[i].getId() + ", " + "ISBN: " + books[i].getIsbn());
                        }

                    }
                    System.out.println("Select a book by its ID number or press 0 to exit to main menu.");
                    int checkOutReply = scanner.nextInt();
                    scanner.nextLine(); // consume leftover newline
                    if(checkOutReply == 0){
                        break;
                    }


                    boolean foundBook = false;

                    for (int i = 0; i < books.length; i++) {

                        if (books[i].getId() == checkOutReply && !books[i].getIsCheckedOut()) {

                            System.out.println("Enter your name: ");
                            String checkedOutBy = scanner.nextLine();

                            books[i].checkOut(checkedOutBy);

                            System.out.println(
                                    books[i].getTitle() + " checked out successfully."
                            );
                            System.out.println("Thank you, " + checkedOutBy + ", see you soon!");

                            foundBook = true;
                            break;
                        }
                    }

                    if (!foundBook) {
                        System.out.println("Invalid ID or book is already checked out.");
                    }
                        noBookCheckedOut = true;
                        break;

                case 2:
                    System.out.println("These books are currently checked out:\n");

                    for (int i = 0; i < books.length; i++) {
                        if (books[i].getIsCheckedOut()) {
                            System.out.println("Title: " + books[i].getTitle()
                                    + ", ID: " + books[i].getId()
                                    + ", ISBN: " + books[i].getIsbn());
                        }
                    }

                    System.out.println("\nEnter the ID of the book to check in.");
                    System.out.println("Enter 0 to return to the main menu.");

                    int checkInId = scanner.nextInt();

                    if (checkInId == 0) {
                        break;
                    }

                    boolean foundCheckedInBook = false;

                    for (int i = 0; i < books.length; i++) {

                        if (books[i].getId() == checkInId &&
                                books[i].getIsCheckedOut()) {

                            books[i].checkIn();

                            System.out.println(
                                    books[i].getTitle()
                                            + " has been checked in successfully."
                            );

                            foundCheckedInBook = true;
                            break;
                        }
                    }

                    if (!foundCheckedInBook) {
                        System.out.println(
                                "Invalid ID or the book is already checked in."
                        );
                    }

                    break;


                case 3:
                    System.out.println("Thank you. Come again!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose another option.");


            }
        }
    }
}






