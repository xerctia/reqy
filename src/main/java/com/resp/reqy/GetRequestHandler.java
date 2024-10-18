package com.resp.reqy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URL;

public class GetRequestHandler {
	public void sendGetRequest(String urlStr, boolean verboseMode) {
		StringBuilder response = new StringBuilder();
		StringBuilder responseHeaders = new StringBuilder();
		try {
			URI uri = new URI(urlStr);
			URL url = uri.toURL();
			
			String host = url.getHost();
			String path = url.getPath();
			
			int port = uri.getPort() != -1 ? uri.getPort() : 80;
			
			// Socket connection
			Socket socket = new Socket(host, port);
			
			String request =    "GET " + path + " HTTP/1.1\r\n"+
								"Host: " + host + "\r\n"+
								"Connection: close\r\n\r\n";
			
			// Send request
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			writer.print(request);
			writer.flush();
			
			// Read response
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.isEmpty()) {
					break; // End of headers
				}
				
				responseHeaders.append(inputLine).append("\n"); // Response headers in one string
			}
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine).append("\n"); // Response body in separate string
			}
			
			if (verboseMode) {
				System.out.println(responseHeaders.toString());
			}
			
			System.out.println(response.toString());
			
			// Close connections
			in.close();
			writer.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to make GET request.");
		}
	}
}
