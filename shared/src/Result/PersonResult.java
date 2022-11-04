package Result;
import Model.*;

import java.util.ArrayList;

public class PersonResult {
    private ArrayList<Person> data;
    private String message;
    private boolean success;

    public PersonResult() {
        this.data = null;
        this.message = null;
    }

    public PersonResult(ArrayList<Person> data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public PersonResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ArrayList<Person> getData() {
        return data;
    }

    public void setData(ArrayList<Person> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
