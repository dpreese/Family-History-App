package Service;
import Data_Access.*;
import Model.AuthToken;
import Model.Event;
import Request.EventRequest;
import Result.EventResult;
import Result.PersonResult;

import java.util.ArrayList;

public class EventService {

    public EventService() {

    }

    /**
     * Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
     * @param request
     * @return
     */
    public EventResult event(EventRequest request) throws DataAccessException {
        Database db = new Database();
        EventDao eventD = new EventDao(db.getConnection());
        AuthTokenDao tokenD = new AuthTokenDao(db.getConnection());
        AuthToken authtoken = tokenD.find(request.getAuthtoken());
        ArrayList<Event> events;

        try {
            if(authtoken != null) {
                events = eventD.findAll(authtoken.getUsername());
                db.closeConnection(true);
                return new EventResult(events, true);
            }
            else {
                db.closeConnection(false);
                return new EventResult("Error: Invalid Auth Token", false);
            }
        } catch(DataAccessException e) {
            db.closeConnection(false);
            return new EventResult("Error: Internal Server Error", false);
        }
    }
}
