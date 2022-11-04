package Handler;

import Data_Access.DataAccessException;
import Request.ClearRequest;
import Result.ClearResult;
import Service.ClearService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                //Headers reqHeaders = exchange.getRequestHeaders();

                //InputStream reqBody = exchange.getRequestBody();


                //String reqData = readString(reqBody);

                //ClearRequest request = gson.fromJson(reqData, ClearRequest.class);

                ClearService service = new ClearService();
                ClearResult result = service.clear();
                if(result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    success = true;
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
