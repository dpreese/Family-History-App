package Data_Access;
import java.sql.Connection;
import Model.*;

import java.sql.*;
import java.util.ArrayList;


public class PersonDao {
    /**
     * a connection variable
     */
    private Connection conn;

    /**
     * This is the constructor for the PersonDao
     * @param conn
     */
    public PersonDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Function to insert a person
     * @param person
     * @throws //Data_Access.DataAccessException
     */
    public void insert(Person person) throws Data_Access.DataAccessException{
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Person (personID, username, firstname, lastname, gender, " +
                "fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting Person into the database");
        }
    }

    /**
     * function to find a specific person
     * @param personID
     * @return
     */
    public Person find(String personID) throws DataAccessException {
        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM Person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("username"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
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

    public ArrayList<Person> findAll(String associatedUsername) throws DataAccessException{
        Person person;
        ArrayList<Person> personArray = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM Person WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);
            rs = stmt.executeQuery();
            while (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("username"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                personArray.add(person);
            }
            if(personArray.size() == 0) {
                return null;
            }
            return personArray;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
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

    public void clear() throws DataAccessException {
        //clears all people with associated username
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Person";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }

    public boolean clearData(String associatedUsername) throws DataAccessException {
        String sql = "DELETE FROM Person WHERE username ='"+associatedUsername+"';";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error clearing person data with associatedUsername");
            return false;
            //throw new DataAccessException("SQL Error encountered while clearing tables");
        }
        return true;
    }    /*public void clear2(String username) {

    }*/
    public void deletePerson(Person person) {

    }
}
