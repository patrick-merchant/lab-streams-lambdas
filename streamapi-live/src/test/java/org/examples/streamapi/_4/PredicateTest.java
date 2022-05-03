package org.examples.streamapi._4;

import org.examples.streamapi.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.examples.streamapi.TestUtil.PEOPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PredicateTest {
/*
    We used Predicates with the Stream's filter method.


    In Java, each method must belong to a Type - there are no methods on their own.
    Every lambda we pass is an implementation of a functional interface - (a functional interface is an interface with one method only).

    We can assign a lambda to a reference which sometimes may be helpful write more reusable code.

 */

    @Test
    void filterUsingAPredicate() {
        // Use lambda expression and assign it to a variable
        Predicate<Person> ageMoreThan30 = (person) -> {
            return person.getAge() > 30;
        };

        Predicate<Person> ageLessThan20 = (person) -> {
            return person.getAge() < 20;
        };

        List<Person> peopleOlderThan30AndYoungerThen20 = PEOPLE
                .stream()
                // Time to use the predicates ?
                .filter(ageLessThan20.or(ageLessThan20))
                .toList();

        assertEquals(5, peopleOlderThan30AndYoungerThen20.size());
    }

    //            Time for some practice:

//  - create the same test but this time use IntelliJ to help you get rid of unnecessary code in the lambdas
//  (e.g. return keyword)
    @Test
    void filterUsingPredicate2(){
        Predicate<Person> ageMoreThan30 = (person -> person.getAge() > 30);
        Predicate<Person> ageLessThan20 = (person -> person.getAge() < 20);
        List<Person> peopleOlderThan30AndYoungerThan20 = PEOPLE
                .stream()
                .filter(ageLessThan20.or(ageMoreThan30))
                .toList();
        assertEquals(11, peopleOlderThan30AndYoungerThan20.size());

    }

// - use FilterTest exercises again, but this time with the help of predefined predicates
@Test
void filterPeopleYoungerThan20(){
    Predicate<Person> ageLessThan20 = (person -> person.getAge() < 20);
    List<Person> filteredPeople = PEOPLE.stream().filter(ageLessThan20).toList();

    assertEquals(5, filteredPeople.size());
}

    @Test
    void filterPeopleYoungerThan20AndFirstNameStartsWithZ(){
        Predicate<Person> ageLessThan20 = (person -> person.getAge() < 20);
        Predicate<Person> nameStartsWithZ = (person -> person.getName().startsWith("Z"));
        List<Person> filteredPeople = PEOPLE.stream().filter(ageLessThan20.and(nameStartsWithZ)).toList();

        assertEquals(1, filteredPeople.size());
    }

    @Test
    void filterPeopleOlderThan30AndNonBinary(){
        Predicate<Person> ageMoreThan30 = (person -> person.getAge() > 30);
        Predicate<Person> genderNonBinary = (person -> person.getGender().equals(Person.Gender.NON_BINARY));
        List<Person> filteredPeople = PEOPLE.stream().filter(ageMoreThan30.and(genderNonBinary)).toList();

        assertEquals(2, filteredPeople.size());
    }

    @Test
    void filterPeopleOlderThan50AndLastNameStartsWithB(){
        Predicate<Person> ageMoreThan50 = (person -> person.getAge() > 50);
        Predicate<Person> lastNameStartsWithB = (person -> person.getLastName().startsWith("B"));
        List<Person> filteredPeople = PEOPLE.stream().filter(ageMoreThan50.and(lastNameStartsWithB)).toList();

        assertEquals(1, filteredPeople.size());
    }


//   - extras: define some predicates in Person class (public static) and use them in the test
@Test
void filterUsingStaticPredicates(){

    List<Person> peopleOlderThan25AndNameEndsWithS = PEOPLE
            .stream()
            .filter(Person.ageMoreThan25.and(Person.lastNameEndsWithS))
            .toList();
    assertEquals(2, peopleOlderThan25AndNameEndsWithS.size());

}
}