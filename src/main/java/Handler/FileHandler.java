package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

/* TO DO
- do file handler and get test website woring
- go one by one and do a handler with its server then test them with thr wwebsite to mae sure they work
    - possibly write JUnit tests for each service as you go as they are required by the end of the project
- SIDE NOTE: If there is a request body you must GSON.FROM JSON(its built in)
    - for the response do GSON.TO JSON
*/

import java.io.IOException;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {

        // Handles HTTP requests containing the "/games/list" URL path.
        // The "exchange" parameter is an HttpExchange object, which is
        // defined by Java.
        // In this context, an "exchange" is an HTTP request/response pair
        // (i.e., the client and server exchange a request and response).
        // The HttpExchange object gives the handler access to all of the
        // details of the HTTP request (Request type [GET or POST],
        // request headers, request body, etc.).
        // The HttpExchange object also gives the handler the ability
        // to construct an HTTP response and send it back to the client
        // (Status code, headers, response body, etc.).
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // This handler returns a list of "Ticket to Ride" games that
            // are (ficticiously) currently running in the server.
            // The game list is returned to the client in JSON format inside
            // the HTTP response body.
            // This implementation is clearly unrealistic, because it
            // always returns the same hard-coded list of games.
            // It is also unrealistic in that it accepts only one specific
            // hard-coded auth token.
            // However, it does demonstrate the following:
            // 1. How to get the HTTP request type (or, "method")
            // 2. How to access HTTP request headers
            // 3. How to return the desired status code (200, 404, etc.)
            //		in an HTTP response
            // 4. How to write JSON data to the HTTP response body
            // 5. How to check an incoming HTTP request for an auth token

            try {
                // Determine the HTTP request type (GET, POST, etc.).
                // Only allow GET requests for this operation.
                // This operation requires a GET request, because the
                // client is "getting" information from the server, and
                // the operation is "read only" (i.e., does not modify the
                // state of the server).
                if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                    // Get the HTTP request headers
                    String urlPath = exchange.getRequestURI().toString();

                    if (urlPath.equals("/") || urlPath == null) {
                        urlPath = "/index.html";

                    }
                    String filePath = "web" + urlPath;
                    File file = new File(filePath);

                    if(file.exists()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        OutputStream respBody= exchange.getResponseBody();
                        Files.copy(file.toPath(), respBody);
                        respBody.close();
                    }
                    else {
                        String filePath404 = "web/HTML/404.html";
                        File file404 = new File(filePath404);
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                        OutputStream respBody= exchange.getResponseBody();
                        Files.copy(file404.toPath(), respBody);
                        respBody.close();
                    }

                }

                if(!exchange.getRequestMethod().toLowerCase().equals("get")) {
                    String filePath404 = "web/HTML/404.html";
                    File file404 = new File(filePath404);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    OutputStream respBody= exchange.getResponseBody();
                    Files.copy(file404.toPath(), respBody);
                    respBody.close();
                }
            }
            catch (IOException e) {
                // Some kind of internal error has occurred inside the server (not the
                // client's fault), so we return an "internal server error" status code
                // to the client.
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
                // Since the server is unable to complete the request, the client will
                // not receive the list of games, so we close the response body output stream,
                // indicating that the response is complete.
                exchange.getResponseBody().close();

                // Display/log the stack trace
                e.printStackTrace();
            }
        }

	/*
		The writeString method shows how to write a String to an OutputStream.

        private void writeString(String str, OutputStream os) throws IOException {
            OutputStreamWriter sw = new OutputStreamWriter(os);
            sw.write(str);
            sw.flush();
        }
    */
    }
