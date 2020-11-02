package com.example.service;

import com.example.Author;
import com.example.DBConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {
    String jsonOutput = "";

    private static Statement stmt;
    private static ResultSet results;

    public String getAllAuthorsService() throws JsonProcessingException {
        String sql_select = "SELECT * FROM author";

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(sql_select);

            List<Author> authorsList = new ArrayList<>();

            while (results.next()) {
                Author authorObject = new Author();

                setAuthor(authorObject, results);

                authorsList.add(authorObject);
            }

            ObjectMapper mapper = new ObjectMapper();
            this.jsonOutput = mapper.writeValueAsString(authorsList);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonOutput;
    }

    public String getAuthorByIdService(int author_id) {
        String sql_select = String.format("SELECT * FROM author WHERE author_id=%s", author_id);

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(sql_select);

            Author authorObject = new Author();

            if(results.next()){
                setAuthor(authorObject, results);
            }

            ObjectMapper mapper = new ObjectMapper();
            this.jsonOutput = mapper.writeValueAsString(authorObject);

        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonOutput;
    }

    public String insertAuthor(String author_name_p, int gender_p, String dob_p, String contact_p, String address_p) {
        String sql_insert = String.format("INSERT INTO author (`author_name`, `gender`, `dob`, `contact`, `address`) VALUES ('%s', '%d', '%s', '%s', '%s')", author_name_p, gender_p, dob_p, contact_p, address_p);

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            stmt.executeUpdate(sql_insert);

        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return "Inserted data successfully";
    }

    public String deleteAuthor(int author_id) {
        String sql_delete = String.format("DELETE FROM author WHERE `author_id`='%d'", author_id);

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            stmt.executeUpdate(sql_delete);

        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return "Deleted record successfully";
    }

    private static void setAuthor(Author author, ResultSet result) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        author.setAuthor_id(Integer.parseInt(result.getString("author_id")));
        author.setAuthor_name(result.getString("author_name"));
        author.setGender(Integer.parseInt(result.getString("gender")));
        author.setDob(LocalDate.parse(result.getString("dob"), formatter));
        author.setContact(result.getString("contact"));
        author.setAddress(result.getString("address"));
    }
}
