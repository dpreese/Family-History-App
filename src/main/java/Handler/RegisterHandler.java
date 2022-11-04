package Handler;

import Data_Access.DataAccessException;
import Request.RegisterRequest;
import Service.RegisterService;
import Result.RegisterResult;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.io.InputStream;
import java.net.*;

//Does not need an authToen but does have a request body
public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        boolean success = false;


        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                //Gets the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();

                // Extract the JSON string from the HTTP request body

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();

                // Read JSON string from the input stream
                String reqData = readString(reqBody);

                // Display/log the request JSON data
                //System.out.println(reqData);

                RegisterRequest request = gson.fromJson(reqData, RegisterRequest.class);

                RegisterService service = new RegisterService();
                RegisterResult result = service.register(request);
                if(result.getSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                OutputStream resBody = exchange.getResponseBody();
                String jsonResult = gson.toJson(result);
                writeString(jsonResult, resBody);
                resBody.close();
            }
        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }

    }

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
