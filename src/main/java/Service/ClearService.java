package Service;

import Data_Access.DataAccessException;
import Data_Access.Database;
import Request.ClearRequest;
import Result.ClearResult;
import Result.LoginResult;

public class ClearService {

    public ClearService() {

    }

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and generated person and event data.
     * @return
     */
    public ClearResult clear() throws DataAccessException {
        Database db = new Database();
        try {
            db.openConnection();
            db.clearTables();
            db.closeConnection(true);
            return new ClearResult("Clear Succeeded", true);

            /*
             db.closeConnection(false);
             return new ClearResult("Error: Database was not cleared", false);
            */

        } catch(DataAccessException e) {
            db.closeConnection(false);
            return new ClearResult("Internal server error", false);
        }
    }
}
