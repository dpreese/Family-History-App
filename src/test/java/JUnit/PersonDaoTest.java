package JUnit;

import Data_Access.DataAccessException;
import Data_Access.Database;
import Data_Access.PersonDao;
import Model.Event;
import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoTest {
    private Database db;
    private Person bestPerson;
    private PersonDao pDao;


    @BeforeEach
    public void setUp() throws DataAccessException
    {
        db = new Database();
        bestPerson = new Person("abc123", "dpreese", "Derek",
                "Reese", "m", "fath3r", "moth3r",
                "spous3");
        Connection conn = db.getConnection();
        pDao = new PersonDao(conn);
        pDao.clear();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insert(bestPerson);
        assertThrows(DataAccessException.class, ()-> pDao.insert(bestPerson));
    }

    @Test
    public void findPass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person comparetest = pDao.find(bestPerson.getPersonID());
        assertNotNull(comparetest);
        assertEquals(bestPerson, comparetest);
    }

    @Test
    public void findFail() throws DataAccessException {
        assertNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void clearPass() throws DataAccessException{
        pDao.insert(bestPerson);
        pDao.find(bestPerson.getPersonID());
        pDao.clear();
        assertNull(pDao.find(bestPerson.getPersonID()));
    }
}
