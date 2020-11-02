package com.example.rest;

import com.example.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/")
public class BookRestService {
    BookService bookService = new BookService();
    @GET
    @Path("/hello")
    public Response hello() {
        return Response.status(200).entity("hello").build();
    }

    @GET
    @Path("/getAllBooks")
    public Response getAllBooks() {
        try {
            return Response.status(200).entity(this.bookService.getAllBooksService()).build();
        } catch (JsonProcessingException | SQLException e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @GET
    @Path("/getBookById?id")
    public Response getBookById(int id) {
        try {
            return Response.status(200).entity(this.bookService.getBookByIdService(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }
}
