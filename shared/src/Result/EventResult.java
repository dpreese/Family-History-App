package Result;
import Model.*;

import java.util.ArrayList;


public class EventResult {
    private ArrayList<Event> data;
    private String message;
    //private String authtoken;
    private boolean success;

    public EventResult(ArrayList<Event> data, boolean success) {
        this.data = data;
        this.success = success;
    }
    public EventResult(String message, boolean success) {
        //this.dataArray = dataArray;
        this.message = message;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Event> getData() {
        return data;
    }

    public void setData(ArrayList<Event> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
