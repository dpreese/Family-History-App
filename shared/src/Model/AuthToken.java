package Model;

import java.util.UUID;

public class AuthToken {
    private String token;
    private String username;

    public AuthToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public AuthToken(String username) {
      this.username = username;
      this.token = UUID.randomUUID().toString();
    }
    public AuthToken() {
        this.username = null;
        this.token = UUID.randomUUID().toString();
    }

    /**
     * get the token
     * @return the token string
     */
    public String getToken() {
        return token;
    }

    /**
     * set the token
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * get the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
