package Data_Access;
import java.sql.*;
import Model.*;

import java.sql.Connection;
import java.util.ArrayList;

public class EventDao {

    /**
     * a connection variable
     */
    private Connection conn;

    /**
     * a constructor
     * @param conn
     */
    public EventDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Function to insert
     * @param event
     * @throws //Data_Access.DataAccessException
     */
    public void insert(Event event) throws DataAccessException{
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Event (eventID, username, personID, latitude, longitude, " +
                "country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting event into the database");
        }

    }

    public Event find(String eventID) throws DataAccessException{
        Event event;
        ResultSet rs = null;
        String sql = "SELECT * FROM Event WHERE eventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("eventID"), rs.getString("username"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                return event;
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

    public void clear() throws DataAccessException{
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Event";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing Event tables");
        }

    }
    public ArrayList<Event> findAll(String associatedUsername) throws DataAccessException{
        Event event;
        ArrayList<Event> eventArray = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM Event WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);
            rs = stmt.executeQuery();
            while (rs.next()) {
                event = new Event(rs.getString("eventID"), rs.getString("username"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"), rs.getInt("year"));
                eventArray.add(event);
            }
            if(eventArray.size() == 0) {
                return null;
            }
            return eventArray;
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
        //return null;
    }

    public boolean clearData(String associatedUsername) throws DataAccessException {
        String sql = "DELETE FROM Event WHERE username ='"+associatedUsername+"';";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error clearing event data with associatedUsername");
            return false;
            //throw new DataAccessException("SQL Error encountered while clearing tables");
        }
        return true;
    }

    /*public void clear2(String username) {

    }*/
}
