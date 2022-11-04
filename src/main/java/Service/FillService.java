package Service;
import Data_Access.*;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Result.FillResult;
import FamilyTreeGenerator.*;
import Result.LoadResult;

public class FillService {


    /**
     * Populates the server's database with generated data for the specified user name.
     The required "username" parameter must be a user already registered with the server. If there is
     any data in the database already associated with the given user name, it is deleted. The
     optional “generations” parameter lets the caller specify the number of generations of ancestors
     to be generated, and must be a non-negative integer (the default is 4, which results in 31 new
     persons each with associated events).
     * @param request
     * @return
     */
    public FillResult fill(FillRequest request) throws DataAccessException {
        Database db = new Database();
        UserDao userD = new UserDao(db.getConnection());
        EventDao eventD = new EventDao(db.getConnection());
        PersonDao personD = new PersonDao(db.getConnection());
        User user = userD.find(request.getUsername());
        TreeGenerator tree = new TreeGenerator();

        try {
            if (request.getGeneration() != 0) {
                if (user == null) {
                    db.closeConnection(false);
                    return new FillResult("Error: User not found", false);
                } else if ((!personD.clearData(request.getUsername())) || !(eventD.clearData(request.getUsername()))) {
                    db.closeConnection(false);
                    return new FillResult("Error: Could not clear event and person data", false);
                } else {
                    GenerationsGenerator generations = tree.startGenerations(uTOp(user), request.getGeneration());
                    for(int i = 0; i < generations.getPersonArray().size(); i++) {
                        personD.insert(generations.getPersonArray().get(i));
                    }
                    for(int i = 0; i < generations.getEventArray().size(); i++) {
                        eventD.insert(generations.getEventArray().get(i));
                    }
                    db.closeConnection(true);
                    return new FillResult("Successfully added " + generations.getPersonArray().size() + " persons and " +
                            generations.getEventArray().size() + " events to the database", true);
                }
            } else {
                db.closeConnection(false);
                return new FillResult("Error: Invalid generations", false);
            }

        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new FillResult("Error: Interal server error", false);
        }
    }

    public Person uTOp(User user) {
        Person person = new Person();
        person.setAssociatedUsername(user.getUsername());
        person.setFirstName(user.getFirstName());
        person.setLastName(user.getLastName());
        person.setGender(user.getGender());

        return person;
    }
}
