package Service;
import Data_Access.*;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Request.EventIDRequest;
import Result.EventIDResult;
import Result.PersonIDResult;

public class EventIDService {

    public EventIDService() {

    }

    /**
     * Returns the single Event object with the specified ID.
     * @param request
     * @return
     */
    public EventIDResult eventID(EventIDRequest request) throws DataAccessException {
        Database db = new Database();
        EventDao eventD = new EventDao(db.getConnection());
        AuthTokenDao tokenD = new AuthTokenDao(db.getConnection());
        AuthToken authtoken = tokenD.find(request.getAuthtoken());
        Event event = eventD.find(request.getEventID());

        try {
            if(authtoken != null && event != null) {
                if(authtoken.getUsername().equals(event.getAssociatedUsername())) {
                    //person = personD.findAll(authtoken.getUsername());
                    db.closeConnection(true);
                    return new EventIDResult(event.getAssociatedUsername(), event.getEventID(), event.getPersonID(),
                            event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(),
                             event.getYear(), true);
                }
                else {
                    db.closeConnection(false);
                    return new EventIDResult("Error: this requested event does not belong to this user", false);
                }
            }
            else {
                db.closeConnection(false);
                return new EventIDResult("Error: Invalid Auth Token or eventID", false);
            }
        } catch(DataAccessException e) {
            db.closeConnection(false);
            return new EventIDResult("Error: Internal Server Error", false);
        }
    }
}
