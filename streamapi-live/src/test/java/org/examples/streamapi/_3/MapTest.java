package org.examples.streamapi._3;


import org.examples.streamapi.model.Person;
import org.examples.streamapi.model.PersonDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.examples.streamapi.TestUtil.PEOPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {

    @Test
    void mapPersonObjectsToStrings_comments() {
        List<String> names = PEOPLE
                .stream()
                .map(
                        (person) -> {
                            return person.getName();
                        }
                )
                .toList();
        // Certainly not the best way to check the returned list, but it will do for this case ;)
        assertEquals("Alec", names.get(0));
        assertEquals("Alivia", names.get(19));
        assertEquals(20, names.size());
    }

    @Test
    void mapPersonObjectsToStrings() {

        List<String> names = PEOPLE
                .stream()
                /*
                    The syntax can get even crazier - but this is just a syntax sugar!
                    It's the same as in the first example, so use the extended (the most obvious version)
                    and then use IntelliJ to get to the most straightforward ( at the beginning, the craziest) syntax.
                 */
                .map(Person::getName)
                .toList();

        assertEquals("Alec", names.get(0));
        assertEquals("Alivia", names.get(19));
        assertEquals(20, names.size());
    }

    /*
    Time for some practice:
     - map to List<Integers> (age)
     - map to List<String> (lastName)
     - map to List<Person.GENDER>

     extras: map List<Person> to List<PersonDTO>
 */

    @Test
    void mapPersonAgeToIntegerList(){
        List<Integer> ages = PEOPLE
                .stream()
                .map(Person::getAge)
                .toList();

        assertEquals(20, ages.get(0));
        assertEquals(57, ages.get(4));
        assertEquals(47, ages.get(16));
    }

    @Test
    void mapPersonLastNameToStringList(){
        List<String> lastNames = PEOPLE
                .stream()
                .map(Person::getLastName)
                .toList();

        assertEquals("Kirkpatrick", lastNames.get(1));
        assertEquals("Parrish", lastNames.get(19));
        assertEquals("O'Sullivan", lastNames.get(15));
    }

    @Test
    void mapPersonObjectsToGenderList(){
        List<Person.Gender> genders = PEOPLE
                .stream()
                .map(Person::getGender)
                .toList();

        assertEquals(Person.Gender.UNKNOWN, genders.get(2));
        assertEquals(Person.Gender.MALE, genders.get(9));
        assertEquals(Person.Gender.NON_BINARY, genders.get(4));
    }

    @Test
    void mapPersonListToPersonDTOList(){
        List<PersonDTO> people = PEOPLE
                .stream()
                .map((person) -> {
                    return new PersonDTO(person.getName(), person.getLastName());
                })
                .toList();
        assertEquals("AlecGunn", people.get(0).getName() + people.get(0).getLastName());
        assertEquals("ElspethHumphries", people.get(5).getName() + people.get(5).getLastName());

    }
}





