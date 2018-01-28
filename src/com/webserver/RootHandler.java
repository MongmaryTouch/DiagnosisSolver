package com.webserver;

import java.io.*;
import java.util.*;

import com.sun.net.httpserver.*;

public class RootHandler implements HttpHandler{
	@Override

    public void handle(HttpExchange he) throws IOException {
			int port = 8080;
            String response = "<h1>Welcome to the website :)</h1>" + "<h1>Port: " + port + "</h1>";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
    }
}
