package com.webserver;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import com.sun.net.httpserver.*;

public class FileHandler implements HttpHandler{
	
	private File file;
	private String fileName;
	
	public FileHandler(String filename){
		file = new File(filename);
		fileName = filename;
	}
	
	@Override
	public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200, file.length());
        OutputStream os = he.getResponseBody();
        ///System.out.println("response: " + getResponseBytes()[0]);
        os.write(getResponseBytes());
        os.close();
		
	}
	
	protected byte[] getResponseBytes() {
		int size = (int) file.length();
		byte[] bytearray = new byte[size];
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			bis.read(bytearray, 0, bytearray.length);
			bis.close();
		} catch (IOException ioexc) {
			System.err.println("error reading file: " + fileName);
			System.err.println(ioexc.getMessage());
		}
		return bytearray;
	}

}
