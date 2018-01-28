package com.webserver;

import java.io.*;
import java.util.*;

import com.sun.net.httpserver.*;

public class EchoPostHandler implements HttpHandler{
	
	@Override

    public void handle(HttpExchange he) throws IOException {
            // parse request
			Parser parse = new Parser();
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
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
