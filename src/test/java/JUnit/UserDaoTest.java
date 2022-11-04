package JUnit;

import Data_Access.DataAccessException;
import Data_Access.Database;
import Data_Access.UserDao;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;


public class UserDaoTest {
    private Database db;
    private User bestUser;
    private UserDao uDao;


    @BeforeEach
    public void setUp() throws DataAccessException
    {
        //here we can set up any classes or variables we will need for the rest of our tests
        //lets create a new database
        db = new Database();
        //and a new event with random data
        bestUser = new User("dpreese", "password", "derekpreese56@gmail.com",
                "Derek", "Reese", "m", "abc123");
        //Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Let's clear the database as well so any lingering data doesn't affect our tests
        //Then we pass that connection to the EventDAO so it can access the database
        uDao = new UserDao(conn);
        uDao.clear();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insert(bestUser);
        User compareTest = uDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.insert(bestUser);
        assertThrows(DataAccessException.class, ()-> uDao.insert(bestUser));
    }

    @Test
    public void findPass() throws DataAccessException {
        uDao.insert(bestUser);
        User comparetest = uDao.find(bestUser.getUsername());
        assertNotNull(comparetest);
        assertEquals(bestUser, comparetest);
    }

    @Test
    public void findFail() throws DataAccessException {
        assertNull(uDao.find(bestUser.getUsername()));
    }

    @Test
    public void testClear() throws DataAccessException{
        uDao.insert(bestUser);
        uDao.find(bestUser.getUsername());
        uDao.clear();
        assertNull(uDao.find(bestUser.getUsername()));
    }
}
