package com.webserver;

import java.io.*;
import java.net.URI;
import java.util.*;

import com.sun.net.httpserver.*;

public class EchoGetHandler implements HttpHandler{
	@Override

    public void handle(HttpExchange he) throws IOException {
            // parse request
			Parser parse = new Parser();
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            System.out.println("q=" + query);
            parse.parseQuery(query, parameters);

            // send response
            String response = "";
            for (String key : parameters.keySet())
                     response += key + " = " + parameters.get(key) + "\n";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());

            os.close();
    }
}
