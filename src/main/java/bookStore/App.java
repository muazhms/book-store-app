package bookStore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Retrive information from mysql DB!
 *
 */
public class App
{

    private static Statement stmt;
    private static ResultSet results;

    public static void main(String[] args) {

        String sql_select = "Select * From book";

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(sql_select);

            List<Book> booksList = new ArrayList<>();
            System.out.println("1");
            while (results.next()) {
                System.out.println("2");

                Book bookObject = new Book();

                bookObject.setBook_id(Integer.parseInt(results.getString("book_id")));
                bookObject.setBook_name(results.getString("book_name"));
                bookObject.setDescription(results.getString("description"));
                bookObject.setEdition(Integer.parseInt(results.getString("edition")));
                bookObject.setPrice(Integer.parseInt(results.getString("price")));
                bookObject.setAuthor_id(Integer.parseInt(results.getString("author_id")));

                booksList.add(bookObject);
            }

            ObjectMapper mapper = new ObjectMapper();
            String JSONOutput = mapper.writeValueAsString(booksList);
            System.out.println(JSONOutput);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
