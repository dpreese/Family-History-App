package Service;
import Data_Access.AuthTokenDao;
import Data_Access.DataAccessException;
import Data_Access.Database;
import Data_Access.PersonDao;
import Model.AuthToken;
import Model.Person;
import Request.PersonIDRequest;
import Result.PersonIDResult;
import Result.PersonResult;

public class PersonIDService {

    public PersonIDService() {

    }

    /**
     * Returns the single Person object with the specified ID.
     * @param request
     * @return
     */
    public PersonIDResult personID(PersonIDRequest request) throws DataAccessException {
        //only returning 1 event based off of the personID
        //similar to person service but now you have to check botht he authtoken and the personID
        Database db = new Database();
        PersonDao personD = new PersonDao(db.getConnection());
        AuthTokenDao tokenD = new AuthTokenDao(db.getConnection());
        AuthToken authtoken = tokenD.find(request.getAuthtoken());
        Person person = personD.find(request.getPersonID());

        try {
            if(authtoken != null && person != null) {
                if(authtoken.getUsername().equals(person.getAssociatedUsername())) {
                    //person = personD.findAll(authtoken.getUsername());
                    db.closeConnection(true);
                    return new PersonIDResult(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(),
                            person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(),
                    true);
                }
                else {
                    db.closeConnection(false);
                    return new PersonIDResult("Error: this requested person does not belong to this user", false);
                }
            }
            else {
                db.closeConnection(false);
                return new PersonIDResult("Error: Invalid Auth Token or personID", false);
            }
        } catch(DataAccessException e) {
            db.closeConnection(false);
            return new PersonIDResult("Error: Internal Server Error", false);
        }
    }
}
