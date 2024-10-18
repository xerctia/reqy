package com.resp.reqy;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.*;

//import com.sun.tools.javac.parser.ReferenceParser.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	if (args.length == 0) {
            System.out.println("Enter an URL to send a request.\nType -H or --help for more information.");
            return;
        }
    	
        // Create cli options
    	Options options = new Options();
    	
    	Option urlOpt = new Option("U", "url", true, "URL to send the request to");
        options.addOption(urlOpt);
        
        Option verboseOpt = new Option("V", "verbose", false, "Enable/disable verbose mode");
        options.addOption(verboseOpt);
        
        Option helpOpt = new Option("H", "help", false, "Know how to use the command");
        options.addOption(helpOpt);
        
        Option methodOpt = new Option("M", "method", true, "Method to send request (GET, POST etc.)");
        methodOpt.setRequired(false);
        options.addOption(methodOpt);
        
        Option fieldOpt = new Option("F", "field", true, "Form data field (e.g., -F name=value)");
        fieldOpt.setRequired(false);
        fieldOpt.setArgs(Option.UNLIMITED_VALUES); // Allow multiple -F flags
        options.addOption(fieldOpt);
    	
    	// parse the arguments
    	CommandLineParser parser = new DefaultParser();
    	
    	try {
    		CommandLine cmd = parser.parse(options, args);
    		
    		if (cmd.hasOption("H")) {
    		    System.out.println("Command: reqy");
    		    System.out.println("\t-U or --url: URL to send the request to.");
    		    System.out.println("\t-M or --method: Method to send request (GET, POST etc).");
    		    System.out.println("\t-F or --field: Form data field (eg, -F field=value. Multiple fields can be specified by corresponding usage of -F flags.");
    		    System.out.println("\t-V or --verbose: Enable verbose mode and receive the response headers.");
    		    System.out.println("\t-H or --help: Know more about the usage of reqy.");
    		    System.out.println("\nSample usage: reqy -U http://example.com -M POST -V -F field1=value1 -F field2=value2");
    		    return;
    		}
    		
    		// Request method
    		String method= cmd.getOptionValue("M", "GET").toUpperCase();
    		String[] fields = cmd.getOptionValues("F");
    		boolean verboseMode = cmd.hasOption("V") == true ? true : false;
    		
    		Map<String, String> formdata = new HashMap<>();
    		
    		if (fields != null) {
    			for (String field: fields) {
    				String[] parts = field.split("=", 2);
    				if (parts.length == 2) {
    					formdata.put(parts[0], parts[1]); // Add fields as key-value pairs
    				}
    			}
    		}
    		
    		// check if -U or --url is provided
    		if (cmd.hasOption("U")) {
    			String url = cmd.getOptionValue("U");
    			System.out.println(method + " request : " + url + "\n");
    			
    			if (method.equals("GET")) {
    				GetRequestHandler getHandler = new GetRequestHandler();
        			getHandler.sendGetRequest(url, verboseMode);
//        			System.out.println(response);
    			} else if (method.equals("POST")) {
    				PostRequestHandler postHandler = new PostRequestHandler();
    				postHandler.sendPostRequest(url, formdata, verboseMode);
//    				System.out.println(response);
    			} 
//    			else {
//    				System.out.println("Unsupported method type.");
//    			}
    		}
    	} catch (ParseException e) {
    		System.err.println("Error parsing command line arguments");
    		e.printStackTrace();
    	}
    }
}
