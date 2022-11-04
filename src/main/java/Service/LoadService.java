package Service;
import Data_Access.*;
import Request.LoadRequest;
import Result.LoadResult;

public class LoadService {

    public LoadService() {

    }

    /**
     * After clearing all the data from database this loads the user, person adn event data into the database
     * @param request
     * @return
     */
    public LoadResult load(LoadRequest request) throws DataAccessException {
        Database db = new Database();
        UserDao userD = new UserDao(db.getConnection());
        EventDao eventD = new EventDao(db.getConnection());
        PersonDao personD = new PersonDao(db.getConnection());


        try {
            db.clearTables();
            if(request.getEvents() == null) {
                db.closeConnection(false);
                return new LoadResult("Error: invalid request data on Event Array", false);
            }
            if(request.getPersons() == null) {
                db.closeConnection(false);
                return new LoadResult("Error: invalid request data on Person Array", false);
            }
            if(request.getUsers() == null) {
                db.closeConnection(false);
                return new LoadResult("Error: invalid request data on User Array", false);
            }
            for(int i = 0; i < request.getUsers().length; i++) {
                userD.insert(request.getUsers()[i]);
            }
            for(int i = 0; i < request.getPersons().length; i++) {
                personD.insert(request.getPersons()[i]);
            }
            for(int i = 0; i<request.getEvents().length; i++) {
                eventD.insert(request.getEvents()[i]);
            }
            db.closeConnection(true);
            return new LoadResult("Successfully added " + request.getUsers().length + " users, "
                     + request.getPersons().length + " persons, and " + request.getEvents().length +
                    " events to the database.", true);
        } catch(DataAccessException e) {
            db.closeConnection(false);
            return new LoadResult("Error: Interal server error", false);
        }
    }
}
