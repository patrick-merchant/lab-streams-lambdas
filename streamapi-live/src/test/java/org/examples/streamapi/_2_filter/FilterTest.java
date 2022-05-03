package org.examples.streamapi._2_filter;

import org.examples.streamapi.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.examples.streamapi.TestUtil.PEOPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    @Test
    void filterPeopleYoungerThan20_comments(){
        List<Person> filteredPeople = PEOPLE
                // We need to change a list into a stream to have access to Stream
                .stream()
                .filter(
                        // Let's pass lambda (again, a more extended version to be more similar to a method
                        (person) -> {
                            return person.getAge() < 20;
                        }
                )
                // Now it's time to go back to a List
                .toList();

        assertEquals(5, filteredPeople.size());
    }

    @Test
    void filterPeopleYoungerThan20(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getAge() < 20)
                .toList();

        assertEquals(5, filteredPeople.size());
    }


/*
    Time for some practice:
     - filter by: age < 20 && age > 30
     - filter by: firstName starts with "Z" and age < 20
     - filter by: Gender: non-binary and  age > 30
     - filter by lastName starts with "B" and age > 50
 */

    @Test
    void filterPeopleYoungerThan20AndOlderThan30(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getAge() < 20 || person.getAge() > 30)
                .toList();

        assertEquals(11, filteredPeople.size());
    }

    @Test
    void filterPeopleYoungerThan20AndFirstNameStartsWithZ(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getAge() < 20 && person.getName().startsWith("Z"))
                .toList();

        assertEquals(1, filteredPeople.size());
    }

    @Test
    void filterPeopleOlderThan30AndNonBinary(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getAge() > 30 && person.getGender().equals(Person.Gender.NON_BINARY))
                .toList();

        assertEquals(2, filteredPeople.size());
    }

    @Test
    void filterPeopleOlderThan50AndLastNameStartsWithB(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getAge() > 50 && person.getLastName().startsWith("B"))
                .toList();

        assertEquals(1, filteredPeople.size());
    }
}
