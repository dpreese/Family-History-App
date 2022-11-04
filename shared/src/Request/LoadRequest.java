package Request;
import Model.User;
import Model.Person;
import Model.Event;

public class LoadRequest {
    private User[] users;
    private Person[] persons;
    private Event[] events;

    public LoadRequest() {
        this.users = null;
        this.persons = null;
        this.events = null;
    }

    public LoadRequest(User[] userArray, Person[] personArray, Event[] eventArray) {
        this.users = userArray;
        this.persons = personArray;
        this.events = eventArray;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
