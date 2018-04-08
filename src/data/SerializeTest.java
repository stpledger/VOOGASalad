package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

    public static class Person {
        private String firstName;
        private String lastName;
        private int index;
        private List<Dog> dogs;

        public Person(String f, String l, int id, List<Dog> dogs ){
            firstName = f;
            lastName = l;
            index = id;
            this.dogs=dogs;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", index='" + index + '\'' +
                    ", dogs=" + dogs +
                    '}';
        }
    }

    public static class Dog{
        private String type;
        private String name;

        public Dog(String type, String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }


    }

    public static void main(String[] args)
    {
        ArrayList<Dog> andysDogs = new ArrayList<>();
        andysDogs.add( new Dog("Lab", "Goldee"));
        andysDogs.add(new Dog("Welsh", "Blue"));
        ArrayList<Person> people = new ArrayList<>();

        people.add( new Person("Andy", "Dufrene", 2, andysDogs));
        people.add(new Person("Conrad", "Mitchell", 1,null));

        XStream xStream = new XStream(new DomDriver());
        xStream.alias("Person",Person.class);
        xStream.alias("Dog",Dog.class);

        String xml= xStream.toXML(people);

        // System.out.print(xml);

        people.clear();
        System.out.print(people);

        people =(ArrayList<Person>) xStream.fromXML(xml);
        System.out.print(people);


    }

}

