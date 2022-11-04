package Service;
import Data_Access.AuthTokenDao;
import Data_Access.DataAccessException;
import Data_Access.Database;
import Data_Access.UserDao;
import Model.AuthToken;
import Model.User;
import Request.LoginRequest;
import Result.LoginResult;
import Result.RegisterResult;

public class LoginService {

    public LoginService() {

    }

    /**
     * Functionality is to login a user or throw an error if the login result doesn't return properly
     * @param request
     * @return
     */

    //Login when used properly is working and saving users to database, however when trying a failed login attempt it still
    //acts as though it was correct when the password was incorrect or if the username was incorrect.
    public LoginResult login(LoginRequest request) throws DataAccessException {
        Database db = new Database();
        UserDao userD = new UserDao(db.getConnection());
        //AuthTokenDao authDao = new AuthTokenDao(db.getConnection());
        User foundUser;
        foundUser = userD.find(request.getUsername());
        AuthToken token = new AuthToken();
        try {
            if(request.getUsername() == null || request.getPassword() == null) {
                db.closeConnection(false);
                return new LoginResult("Error: Request property missing or has invalid value", false);
            }
            if(foundUser == null) {
                db.closeConnection(false);
                return new LoginResult("Error: User not found", false);
            }
            if(!foundUser.getUsername().equals(request.getUsername()) || !foundUser.getPassword().equals(request.getPassword())) {
                db.closeConnection(false);
                return new LoginResult("Error: Username or Password is inncorrect", false);
            }
            AuthTokenDao authTokend = new AuthTokenDao(db.getConnection());
            token.setUsername(request.getUsername());
            authTokend.insert(token);
            db.closeConnection(true);
            return new LoginResult(token.getToken(), foundUser.getUsername(), foundUser.getPersonID(), true);
        } catch(DataAccessException e) {
            db.closeConnection(false);
            return new LoginResult("Error: Internal server error", false);
        }


        //return null;
    }
}
