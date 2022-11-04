package Request;

public class FillRequest {
    private String username;
    private int generation;

    public FillRequest() {
        this.username = null;
        this.generation = 0;
    }

    public FillRequest(String username, int generation) {
        this.username = username;
        this.generation = generation;
    }

    public FillRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
