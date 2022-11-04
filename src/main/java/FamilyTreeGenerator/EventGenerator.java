package FamilyTreeGenerator;

import Model.Event;
import Model.Person;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

public class EventGenerator {
    private String username;
    private ArrayList<Event> eventArray;
    private Random selector = new Random();

    public EventGenerator(String username) {
        this.username = username;
        this.eventArray = new ArrayList<Event>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Event> getEventArray() {
        return eventArray;
    }

    public void setEventArray(ArrayList<Event> eventArray) {
        this.eventArray = eventArray;
    }

    public Event createLocation(Event event) {
        int randomInt;
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/locations.json");
            locationData data = gson.fromJson(reader, locationData.class);
            randomInt = selector.nextInt(data.locationDataSize());

            event.setCountry(data.getData(randomInt).country);
            event.setCity(data.getData(randomInt).city);
            event.setLatitude(data.getData(randomInt).latitude);
            event.setLongitude(data.getData(randomInt).longitude);

            return event;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Event();
    }

    public void createBirth(Person person, int currentYear) {
        Event birth = new Event();
        birth = createLocation(birth);
        birth.setAssociatedUsername(username);
        birth.setpersonID(person.getPersonID());
        birth.setEventType("Birth");
        birth.setYear(currentYear);

        eventArray.add(birth);
    }

    public void createDeath(Person person, int year) {
        Event death = new Event();
        int maxAge = 85;
        int deathYear = year + maxAge;
        death = createLocation(death);
        /*if(deathYear > 2021) {
            deathYear = 2021;
            //death.setYear(deathYear);
        }*/
        death.setYear(deathYear);
        death.setEventType("Death");
        death.setpersonID(person.getPersonID());
        death.setAssociatedUsername(username);

        eventArray.add(death);
    }

    public void createMarriage(Person person, Person spouse, int currentYear) {
        Event marriage = new Event();
        Event marriage2 = new Event();
        int marriageYear = currentYear + 20;

        marriage = createLocation(marriage);
        marriage.setYear(marriageYear);
        marriage.setAssociatedUsername(username);
        marriage.setpersonID(person.getPersonID());
        marriage.setEventType("Marriage");

        marriage2.setYear(marriageYear);
        marriage2.setAssociatedUsername(username);
        marriage2.setpersonID(spouse.getPersonID());
        marriage2.setEventType("Marriage");

        marriage2.setLongitude(marriage.getLongitude());
        marriage2.setLatitude(marriage.getLatitude());
        marriage2.setCity(marriage.getCity());
        marriage2.setCountry(marriage.getCountry());

        eventArray.add(marriage);
        eventArray.add(marriage2);
    }
    public void createEvent(Person person, String eventType, int currentYear) {
        Event event = new Event();
        int eventYear = currentYear + selector.nextInt(10);
        event = createLocation(event);
        event.setYear(eventYear);
        event.setEventType(eventType);
        event.setpersonID(person.getPersonID());
        event.setAssociatedUsername(username);

        eventArray.add(event);
    }
}

class Location {
    String country;
    String city;
    float latitude;
    float longitude;

    public Location(String country, String city, float latitude, float longitude) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

class locationData {
    Location[] data;

    public locationData(Location[] data) {
        this.data = data;
    }
    public int locationDataSize() {
        return data.length;
    }
    public Location getData(int index) {
        return data[index];
    }
}
