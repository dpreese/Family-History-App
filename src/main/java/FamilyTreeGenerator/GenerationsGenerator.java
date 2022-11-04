package FamilyTreeGenerator;


import Model.Event;
import Model.Person;

import java.util.ArrayList;

public class GenerationsGenerator {
    private ArrayList<Person> personArray;
    private ArrayList<Event> eventArray;


    public GenerationsGenerator(ArrayList<Person> personArray, ArrayList<Event> eventArray) {
        this.personArray = personArray;
        this.eventArray = eventArray;
    }

    public ArrayList<Person> getPersonArray() {
        return personArray;
    }

    public void setPersonArray(ArrayList<Person> personArray) {
        this.personArray = personArray;
    }

    public ArrayList<Event> getEventArray() {
        return eventArray;
    }

    public void setEventArray(ArrayList<Event> eventArray) {
        this.eventArray = eventArray;
    }
}
