package Handler;

import Data_Access.DataAccessException;
import Request.EventIDRequest;
import Result.EventIDResult;
import Service.EventIDService;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class EventIDHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //same thing but eget the request URI after you get the string authtoken
        //make a string array and split it on the "/" (URI.split("/"))
        Gson gson = new Gson();
        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                //Gets the HTTP request headers

                Headers reqHeaders = exchange.getRequestHeaders();
                if(reqHeaders.containsKey("Authorization")) {
                    String authtoken = reqHeaders.getFirst("Authorization");
                    String urlPath = exchange.getRequestURI().toString();

                    String[] urlParts = urlPath.split("/");

                    String part3 = urlParts[2];
                    if(part3.length() > 2) {
                        EventIDRequest request = new EventIDRequest(part3, authtoken);
                        EventIDService service = new EventIDService();
                        EventIDResult result = service.eventID(request);
                        if (result.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                        } else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }
                        OutputStream resBody = exchange.getResponseBody();
                        String jsonResult = gson.toJson(result);
                        writeString(jsonResult, resBody);
                        resBody.close();
                    }
                }
            }
        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }

    }
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
