package Handler;

import Data_Access.DataAccessException;
import Request.EventRequest;
import Result.EventResult;
import Service.EventService;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();
        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                //Gets the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                if(reqHeaders.containsKey("Authorization")) {
                    String authtoken = reqHeaders.getFirst("Authorization");
                    EventRequest request = new EventRequest(authtoken);
                    EventService service = new EventService();
                    EventResult result = service.event(request);
                    if(result.isSuccess()) {
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