package FamilyTreeGenerator;


import Model.Event;
import Model.Person;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

public class TreeGenerator {
    private String username;
    private ArrayList<Person> familyTree;
    private EventGenerator eventgenerator;
    private Random selector = new Random();


    public GenerationsGenerator startGenerations(Person person, int generations) {
        username = person.getAssociatedUsername();
        eventgenerator = new EventGenerator(username);
        startTree(person, generations);

        return new GenerationsGenerator(familyTree, eventgenerator.getEventArray());
    }

    public void startTree(Person person, int generations) {
        familyTree = new ArrayList<Person>();
        familyTree.add(person);
        int startingYear = 1940;
        eventgenerator.createBirth(person, startingYear);
        startParents(person, generations - 1, startingYear);
    }

    public void startParents(Person person, int generations, int currentYear) {
        Person mom = new Person();
        Person dad = createFather(person, mom.getPersonID());

        mom = createMother(mom, dad);

        person.setMotherID(mom.getPersonID());
        person.setFatherID(dad.getPersonID());
        createParentEvents(mom, dad, currentYear-20);

        familyTree.add(mom);
        familyTree.add(dad);
        currentYear = currentYear - 40;
        if(generations != 0) {
            startParents(mom, generations-1, currentYear);
            startParents(dad, generations-1, currentYear);
        }

    }

    public Person createFather(Person person, String spouse) {
        Person dad = new Person();
        dad.setFirstName(createMaleName(dad));
        dad.setAssociatedUsername(username);
        dad.setSpouseID(spouse);
        dad.setGender("m");

        if(person.getGender().equals("m")) {
            dad.setLastName(person.getLastName());
        } else {
            dad.setLastName(createLastName(dad));
        }
        return dad;
    }

    public Person createMother(Person mom, Person dad) {
        mom.setFirstName(createFemaleName(mom));
        mom.setLastName(createLastName(mom));
        mom.setAssociatedUsername(username);
        mom.setSpouseID(dad.getPersonID());
        mom.setGender("f");
        return mom;
    }

    public void createParentEvents(Person mom, Person dad, int currentYear) {
        eventgenerator.createBirth(mom, currentYear);
        eventgenerator.createBirth(dad, currentYear);
        eventgenerator.createMarriage(mom, dad, currentYear);
        int lifetime = selector.nextInt(2);
        /*if(currentYear < 1921) {
            eventgenerator.createDeath(mom, currentYear);
            eventgenerator.createDeath(dad, currentYear);
        }*/
        /*if(lifetime == 0) {
            eventgenerator.createEvent(mom, "Had a child", currentYear);
        }*/
        //if(lifetime == 0) {
            eventgenerator.createDeath(dad, currentYear);
            //eventgenerator.createEvent(mom, "Won lottery", currentYear);
        //}
        //if(lifetime == 1) {
            eventgenerator.createDeath(mom, currentYear);
            //eventgenerator.createEvent(dad, "retired", currentYear);
        //}
        /*if(lifetime == 3) {
            eventgenerator.createEvent(dad, "Started a business", currentYear);
        }*/
    }

    public String createMaleName(Person person) {
        int randomInt;
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/mnames.json");
            nameData data = gson.fromJson(reader, nameData.class);
            randomInt = selector.nextInt(data.nameDataSize());

            person.setFirstName(data.getData(randomInt));
            return person.getFirstName();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "Something went wrong in male name";
    }

    public String createFemaleName(Person person) {
        int randomInt;
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/fnames.json");
            nameData data = gson.fromJson(reader, nameData.class);
            randomInt = selector.nextInt(data.nameDataSize());

            person.setFirstName(data.getData(randomInt));
            return person.getFirstName();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "Something went wrong in female name";
    }

    public String createLastName(Person person) {
        int randomInt;
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/snames.json");
            nameData data = gson.fromJson(reader, nameData.class);
            randomInt = selector.nextInt(data.nameDataSize());

            person.setLastName(data.getData(randomInt));
            return person.getLastName();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "Something went wrong in male name";
    }
}
class nameData {
    String[] data;

    public nameData(String[] data) {
        this.data = data;
    }
    public int nameDataSize() {
        return data.length;
    }
    public String getData(int index) {
        return data[index];
    }
}
