package Request;

public class EventIDRequest {
    private String eventID;
    private String authtoken;
    public EventIDRequest() {
        this.eventID = null;
        this.authtoken = null;
    }

    public EventIDRequest(String eventID, String authtoken) {
        this.eventID = eventID;
        this.authtoken = authtoken;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
