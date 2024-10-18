package com.resp.reqy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

public class PostRequestHandler {
	public void sendPostRequest(String urlStr, Map<String, String> formdata, boolean verboseMode) {
		StringBuilder responseHeaders = new StringBuilder();
		StringBuilder response = new StringBuilder();
		try {
			URI uri = new URI(urlStr);
			String host = uri.getHost();
			String path = uri.getPath();
			int port = uri.getPort() == -1 ? 80 : uri.getPort();
			
			// Converting to key=value&key2=value2 format
			StringBuilder dataBuilder = new StringBuilder();
			for (Map.Entry<String, String> entry: formdata.entrySet()) {
				if (dataBuilder.length() > 0) {
					dataBuilder.append("&");
				}
				
//				dataBuilder.append(entry.getKey()).append("=").append(entry.getValue());
				dataBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
	            .append("=")
	            .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
			String data = dataBuilder.toString();
			
			String request = "POST " + path + " HTTP/1.1\r\n" +
						"Host: " + host + "\r\n" +
						"Content-Type: application/x-www-form-urlencoded\r\n" +
						"Content-Length: " + data.length() + "\r\n" +
						"Connection: close\r\n\r\n" +
						data;
			
			try (Socket socket = new Socket(host, port)) { 
			
				// Sending request
				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output, true);
				writer.print(request);
				writer.flush();
				
				// Receiving response
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String inpLine;
				while ((inpLine = in.readLine()) != null) {
					if (inpLine.isEmpty()) {
						break; // End of headers
					}
					
					responseHeaders.append(inpLine).append("\n"); // Response headers in one string
				}
				while ((inpLine = in.readLine()) != null) {
					response.append(inpLine).append("\n"); // Response body in separate string
				}
				
				if (verboseMode) {
					System.out.println(responseHeaders.toString());
				}
				
				System.out.println(response.toString());
				
				// Close connections
				in.close();
				writer.close();
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to make POST request.");
		}
	}
}
