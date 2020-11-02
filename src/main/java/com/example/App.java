package com.example;

import com.example.rest.BookRestService;
import javax.ws.rs.core.Application;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public class App extends Application
{
    private Set<Object> singletons = new HashSet<Object>();
    public App() {
        // Register our hello service
        singletons.add(new BookRestService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
