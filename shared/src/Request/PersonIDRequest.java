package Request;

public class PersonIDRequest {
    private String personID;
    private String authtoken;

    public PersonIDRequest() {
        this.authtoken = null;
        authtoken = null;
    }

    public PersonIDRequest(String personID, String authtoken) {
        this.personID = personID;
        this.authtoken = authtoken;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
