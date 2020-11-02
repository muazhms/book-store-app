package com.example;

import com.example.service.AuthorService;
import com.example.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.core.Application;
import java.sql.SQLException;
import java.util.Scanner;

public class App extends Application
{
    public static void main(String[] args) throws JsonProcessingException, SQLException {

        BookService bookService = new BookService();
        AuthorService authorService = new AuthorService();

        boolean exitSystem = false;
        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to the book store!");

        while (!exitSystem){
            System.out.println("Select what you want to do");
            System.out.println("a: View all books");
            System.out.println("b: View all authors");
            System.out.println("c: View book by id");
            System.out.println("d: View author by id");
            System.out.println("e: Insert author");
            System.out.println("f: Insert book");
            System.out.println("g: Delete author");
            System.out.println("h: Delete book");
            System.out.println("q: Exit");

            String userAction = userInput.nextLine();

            switch(userAction) {
                case "a":
                    System.out.println(bookService.getAllBooksService());
                    break;
                case "b":
                    System.out.println(authorService.getAllAuthorsService());
                    break;
                case "c":
                    System.out.println("Enter book id");
                    int book_id = userInput.nextInt();
                    System.out.println(bookService.getBookByIdService(book_id));
                    break;
                case "d":
                    System.out.println("Enter author id");
                    int author_id = userInput.nextInt();
                    System.out.println(authorService.getAuthorByIdService(author_id));
                    break;
                case "e":
                    System.out.println("Enter author details in following order. Press enter after each detail");
                    System.out.println("Author name, date of birth(YYYY-MM-DD), contact number, address, gender(1 = male, 2 = female)");
                    String author_name = userInput.nextLine();
                    String dob = userInput.nextLine();
                    String contact = userInput.nextLine();
                    String address = userInput.nextLine();
                    int gender = userInput.nextInt();
                    System.out.println(authorService.insertAuthor(author_name, gender, dob, contact, address));
                    break;
                case "f":
                    System.out.println("Enter book details in following order. Press enter after each detail");
                    System.out.println("Book name, description, price(double), edition(integer), author id(integer)");
                    String book_name = userInput.nextLine();
                    String description = userInput.nextLine();
                    Double price = userInput.nextDouble();
                    int edition = userInput.nextInt();
                    int author_id_f = userInput.nextInt();
                    System.out.println(bookService.insertBook(book_name, description, price, edition, author_id_f));
                    break;
                case "g":
                    System.out.println("Enter author id to delete");
                    int author_id_del = userInput.nextInt();
                    System.out.println(authorService.deleteAuthor(author_id_del));
                    break;
                case "h":
                    System.out.println("Enter book id to delete");
                    int book_id_del = userInput.nextInt();
                    System.out.println(bookService.deleteBook(book_id_del));
                    break;
                case "q":
                    exitSystem = true;
                    break;
                default:
                    // code block
            }
        }


    }
}
