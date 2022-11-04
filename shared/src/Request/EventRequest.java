package Request;

import Model.Event;

public class EventRequest {
    private Event[] dataArray;
    private String error;
    private String authtoken;

    public EventRequest(String authtoken) {
        this.authtoken = authtoken;
        //this.dataArray = null;
        //this.error = null;
    }

    public Event[] getDataArray() {
        return dataArray;
    }

    public void setDataArray(Event[] dataArray) {
        this.dataArray = dataArray;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
