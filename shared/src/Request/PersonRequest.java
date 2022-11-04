package Request;

public class PersonRequest {

    private String authtoken;

    public PersonRequest() {
        this.authtoken = null;
    }
    public PersonRequest(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
