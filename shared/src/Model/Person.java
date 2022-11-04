package Model;

import java.util.UUID;

public class Person {
    //write javadoc for each variable
    private String personID;
    private String associatedUsername;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;

    /**
     *
     * @param personID
     * @param username
     * @param firstname
     * @param lastname
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     */
    public Person(String personID, String username, String firstname, String lastname, String gender,
                  String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = username;
        this.firstName = firstname;
        this.lastName = lastname;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }
    public Person() {
        this.personID = UUID.randomUUID().toString();
        this.associatedUsername = null;
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
        this.fatherID = null;
        this.motherID = null;
        this.spouseID = null;
    }

    /**
     * get the personID
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * get the username
     * @return
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * get the firstname
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get the lastname
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * get the gender
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * get the FatherID
     * @return
     */
    public String getFatherID() {
        return fatherID;
    }

    /**
     * get the motherID
     * @return
     */
    public String getMotherID() {
        return motherID;
    }

    /**
     * get the spouseID
     * @return
     */
    public String getSpouseID() {
        return spouseID;
    }

    /**
     * set the personID
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * set the username
     * @param associatedUsername
     */
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    /**
     * set the firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * set the lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * set the gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * set the fatherID
     * @param fatherID
     */
    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    /**
     * set the motherID
     * @param motherID
     */
    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    /**
     * set the spouseID
     * @param spouseID
     */
    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personID.equals(person.personID) && associatedUsername.equals(person.associatedUsername) && firstName.equals(person.firstName) && lastName.equals(person.lastName) && gender.equals(person.gender) && fatherID.equals(person.fatherID) && motherID.equals(person.motherID) && spouseID.equals(person.spouseID);
    }

}
