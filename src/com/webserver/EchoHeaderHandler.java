package com.webserver;

import java.io.*;
import java.util.*;

import com.sun.net.httpserver.*;

public class EchoHeaderHandler implements HttpHandler{
	 @Override
     public void handle(HttpExchange he) throws IOException {
             Headers headers = he.getRequestHeaders();
             Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
             String response = "";
             for (Map.Entry<String, List<String>> entry : entries)
                      response += entry.toString() + "\n";
             he.sendResponseHeaders(200, response.length());
             OutputStream os = he.getResponseBody();
             os.write(response.toString().getBytes());
             os.close();
     }
}
