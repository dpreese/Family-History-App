package Model;

import java.util.UUID;

public class Event {
    private String eventID;
    private String associatedUsername;
    private String personID;
    private float latitude;
    private float longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;

    public Event(String eventID, String username, String personID, float latitude, float longitude, String country,
                 String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public Event() {
        this.eventID = UUID.randomUUID().toString();
        this.associatedUsername = null;
        this.personID = null;
        this.latitude = 0;
        this.longitude = 0;
        this.country = null;
        this.city = null;
        this.eventType = null;
        this.year = 0;
    }

    /**
     * set the eventID
     */
    public String getEventID() {
        return eventID;
    }
    /**
     * Set the eventID
     * @param eventID
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * get the Username
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * set the Username
     * @param userName
     */
    public void setAssociatedUsername(String userName) {
        this.associatedUsername = userName;
    }
    /**
     * get the personID
     */
    public String getPersonID() {
        return personID;
    }
    /**
     * set the personID
     */
    public void setpersonID(String personID) {
        this.personID = personID;
    }
    /**
     * get the latitude
     */
    public float getLatitude() {
        return latitude;
    }
    /**
     * set the latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    /**
     * get the longitude
     */
    public float getLongitude() {
        return longitude;
    }
    /**
     * set the longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    /**
     * get the country
     */
    public String getCountry() {
        return country;
    }
    /**
     * set the country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * get the city
     */
    public String getCity() {
        return city;
    }
    /**
     * set the city
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * get the eventType
     */
    public String getEventType() {
        return eventType;
    }
    /**
     * set the eventType
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    /**
     * get the year
     */
    public int getYear() {
        return year;
    }
    /**
     * set the year
     */
    public void setYear(int year) {
        this.year = year;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Float.compare(event.latitude, latitude) == 0 && Float.compare(event.longitude, longitude) == 0 && year == event.year && eventID.equals(event.eventID) && associatedUsername.equals(event.associatedUsername) && personID.equals(event.personID) && country.equals(event.country) && city.equals(event.city) && eventType.equals(event.eventType);
    }
}
