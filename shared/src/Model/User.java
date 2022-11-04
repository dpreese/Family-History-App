package Model;

public class User {
    /**
     * unique username that cannot be NULL
     */
    private String username;
    /**
     * unique password that cannot be NULL
     */
    private String password;
    /**
     * email address associated with the user that cannot be NULL
     */
    private String email;
    /**
     * first name of the user that cannot be NULL
     */
    private String firstName;
    /**
     * last name of the user that cannot be NULL
     */
    private String lastName;
    /**
     * a single character value either 'f' or 'm' to represent gender
     */
    private String gender;
    /**
     * a uniquely created string of characters will be generated to create the persionID
     */
    private String personID;

    public User(String username, String password, String email, String firstname, String lastname,
                String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstname;
        this.lastName = lastname;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * get the username
     * @return
     */
    public String getUsername() {
        return username;
    }
    /**
     * get the password
     * @return
     */
    public String getPassword() {
        return password;
    }
    /**
     * get the email
     * @return
     */
    public String getEmail() {
        return email;
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
     * get the personID
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * set the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * set the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * set the email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set the firstname
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * set the lastname
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
     * set the personID
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && email.equals(user.email) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && gender.equals(user.gender) && personID.equals(user.personID);
    }

}
