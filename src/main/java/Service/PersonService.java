package Service;

import Data_Access.*;
import Model.AuthToken;
import Request.PersonRequest;
import Result.PersonResult;
import Model.Person;

import java.util.ArrayList;

public class PersonService {

    /**
     * Returns ALL family members of the current user. The current user is
     * determined from the provided auth token.
     * @param request
     * @return
     */
    public PersonResult person(PersonRequest request) throws DataAccessException {
        Database db = new Database();
        PersonDao personD = new PersonDao(db.getConnection());
        AuthTokenDao tokenD = new AuthTokenDao(db.getConnection());
        AuthToken authtoken = tokenD.find(request.getAuthtoken());
        ArrayList<Person> persons;

        try {
            if(authtoken != null) {
                persons = personD.findAll(authtoken.getUsername());
                db.closeConnection(true);
                return new PersonResult(persons, true);
            }
            else {
                db.closeConnection(false);
                return new PersonResult("Error: Invalid Auth Token", false);
            }
        } catch(DataAccessException e) {
            db.closeConnection(false);
            return new PersonResult("Error: Internal Server Error", false);
        }
    }
}
