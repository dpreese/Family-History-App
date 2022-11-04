package Handler;

import Data_Access.DataAccessException;
import Request.FillRequest;
import Result.FillResult;
import Service.FillService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        //boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                //Gets the HTTP request headers

                //Headers reqHeaders = exchange.getRequestHeaders();
                    String urlPath = exchange.getRequestURI().toString();

                    String[] urlParts = urlPath.split("/");

                    String part3 = urlParts[2];
                    //String part4 = urlParts[3];
                    String part4 = "4";
                    if(/*part3.length() > 2 && part4.length() == 0*/ urlParts.length == 4) {
                        part4 = urlParts[3];

                    }
                    FillRequest request = new FillRequest(part3, Integer.parseInt(part4));
                    FillService service = new FillService();
                    FillResult result = service.fill(request);
                    if (result.isSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    } else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }
                    OutputStream resBody = exchange.getResponseBody();
                    String jsonResult = gson.toJson(result);
                    writeString(jsonResult, resBody);
                    resBody.close();
                    /*else if (part3.length() > 2 && part4.length() == 1) {
                        FillRequest request = new FillRequest(part3, Integer.parseInt(part4));
                        FillService service = new FillService();
                        FillResult result = service.fill(request);
                        if (result.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                        } else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }
                        OutputStream resBody = exchange.getResponseBody();
                        String jsonResult = gson.toJson(result);
                        writeString(jsonResult, resBody);
                        resBody.close();
                    }*/
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
