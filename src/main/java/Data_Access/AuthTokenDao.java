package Data_Access;

import Model.*;

import java.sql.*;

public class AuthTokenDao {

    /**
     * a connection variable
     */
    private Connection conn;

    /**
     * a constructor
     * @param conn
     */
    public AuthTokenDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Function to insert
     * @param token
     * @throws //Data_Access.DataAccessException
     */
    public void insert(AuthToken token) throws Data_Access.DataAccessException{
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO AuthToken (token, username) VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, token.getToken());
            stmt.setString(2, token.getUsername());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting event into the database");
        }
    }

    public AuthToken find(String authtoken) throws DataAccessException {
        AuthToken token;
        ResultSet rs = null;
        String sql = "SELECT * FROM AuthToken WHERE token = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                token = new AuthToken(rs.getString("token"), rs.getString("username"));
                return token;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public void clear() throws DataAccessException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM AuthToken";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing AuthToken tables");
        }
    }
}
