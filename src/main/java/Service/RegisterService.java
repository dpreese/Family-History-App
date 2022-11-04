package Service;
import FamilyTreeGenerator.GenerationsGenerator;
import FamilyTreeGenerator.TreeGenerator;
import Model.*;
import Result.RegisterResult;
import Request.RegisterRequest;
import Data_Access.*;

import java.util.UUID;

public class RegisterService {

    public RegisterService() {

    }

    /**
     * Creates a new user account, generates 4 generations of ancestor data for the new
       user, logs the user in, and returns an auth token.
     * @param request
     * @return
     */
    //need to generate 4 generations can only be done when
    public  RegisterResult register(RegisterRequest request) throws DataAccessException {
        Database db = new Database();
        UserDao userD = new UserDao(db.getConnection());
        User foundUser;
        String personID = UUID.randomUUID().toString();
        PersonDao personD = new PersonDao(db.getConnection());
        EventDao eventD = new EventDao(db.getConnection());
        TreeGenerator tree = new TreeGenerator();
        User newUser = new User(request.getUserName(), request.getPassword(), request.getEmail(), request.getFirstName(),
                request.getLastName(), request.getGender(), personID);
        foundUser = userD.find(request.getUserName());
        int genNumber = 4;

        //check to see if gender was set properly
        try {
            if (foundUser == null) {
                AuthTokenDao authTokend = new AuthTokenDao(db.getConnection());
                AuthToken token = new AuthToken(newUser.getUsername());
                authTokend.insert(token);
                GenerationsGenerator generations = tree.startGenerations(uTOp(newUser), genNumber);
                newUser = new User(request.getUserName(), request.getPassword(), request.getEmail(), request.getFirstName(),
                        request.getLastName(), request.getGender(), generations.getPersonArray().get(0).getPersonID());
                userD.insert(newUser);
                for(int i = 0; i < generations.getPersonArray().size(); i++) {
                    personD.insert(generations.getPersonArray().get(i));
                }
                for(int i = 0; i < generations.getEventArray().size(); i++) {
                    eventD.insert(generations.getEventArray().get(i));
                }
                //personD.find(generations.getPersonArray().get(0).getPersonID());

                db.closeConnection(true);
                return new RegisterResult(token.getToken(), newUser.getUsername(), newUser.getPersonID(), true);
            } else {
                db.closeConnection(false);
                return new RegisterResult("Error: Username already Taken", false);
            }
        } catch(DataAccessException e) {
            db.closeConnection(false);
            return new RegisterResult("Error: Internal Server Error.", false);
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
