package com.example.service;

import com.example.Book;
import com.example.DBConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    String jsonOutput = "";

    private static Statement stmt;
    private static ResultSet results;

    public String getAllBooksService() throws JsonProcessingException {
        String sql_select = "SELECT * FROM book";

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(sql_select);

            List<Book> booksList = new ArrayList<>();

            while (results.next()) {
                Book bookObject = new Book();

                setBook(bookObject, results);

                booksList.add(bookObject);
            }

            ObjectMapper mapper = new ObjectMapper();
            this.jsonOutput = mapper.writeValueAsString(booksList);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonOutput;
    }

    public String getBookByIdService(int book_id) {
        String sql_select = String.format("SELECT * FROM book WHERE book_id=%s", book_id);

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(sql_select);

            Book bookObject = new Book();

            if(results.next()){
                setBook(bookObject, results);
            }

            ObjectMapper mapper = new ObjectMapper();
            this.jsonOutput = mapper.writeValueAsString(bookObject);

        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonOutput;
    }

    public String insertBook(String book_name_p, String description_p, double price_p, int edition_p, int author_id_p) {
        String sql_insert = String.format("INSERT INTO book (`book_name`, `description`, `price`, `edition`, `author_id`) VALUES ('%s', '%s', '%f', '%d', '%d')", book_name_p, description_p, price_p, edition_p, author_id_p);

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            stmt.executeUpdate(sql_insert);

        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return "Inserted data successfully";
    }

    public String deleteBook(int book_id) {
        String sql_delete = String.format("DELETE FROM book WHERE `book_id`='%d'", book_id);

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            stmt.executeUpdate(sql_delete);

        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return "Deleted record successfully";
    }

    private static void setBook(Book book, ResultSet result) throws SQLException {
        book.setBook_id(Integer.parseInt(result.getString("book_id")));
        book.setBook_name(result.getString("book_name"));
        book.setDescription(result.getString("description"));
        book.setEdition(Integer.parseInt(result.getString("edition")));
        book.setPrice(Double.parseDouble(result.getString("price")));
        book.setAuthor_id(Integer.parseInt(result.getString("author_id")));
    }
}
