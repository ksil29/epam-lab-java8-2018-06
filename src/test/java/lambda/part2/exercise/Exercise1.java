package lambda.part2.exercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import lambda.data.Person;
import org.junit.Test;

@SuppressWarnings({"unused", "ConstantConditions"})
public class Exercise1 {

    @Test
    public void ageExtractorFromPersonUsingMethodReference() {
        Person person = new Person("Иван", "Мельников", 33);

        Function<Person, Integer> ageExtractor = Person::getAge;

        assertEquals(33, ageExtractor.apply(person).intValue());
    }

    private static boolean isSameAges(Person person1, Person person2) {
        return Objects.equals(person1.getAge(), person2.getAge());
    }

    @Test
    public void sameAgesCheckerUsingBiPredicate() {
        Person person1 = new Person("Иван", "Мельников", 33);
        Person person2 = new Person("Дмитрий", "Гущин", 33);
        Person person3 = new Person("Илья", "Жирков", 22);

        BiPredicate<Person, Person> sameAgesChecker = Exercise1::isSameAges;

        assertTrue(sameAgesChecker.test(person1, person2));
        assertFalse(sameAgesChecker.test(person1, person3));
        assertFalse(sameAgesChecker.test(person2, person3));
    }

    private static String getFullName(Person person) {
        return person.getFullName();
    }

    private static BiFunction<Person, Person, Integer> createExtractorAgeOfPersonWithTheLongestFullName(
        Function<Person, String> personToString) {
        return (person1, person2) ->
            personToString.apply(person1).length() > personToString.apply(person2).length()
                ? person1.getAge() : person2.getAge();

    }

    @Test
    public void getAgeOfPersonWithTheLongestFullName() {
        Person person1 = new Person("Иван", "Мельников", 33);
        Person person2 = new Person("Илья", "Жирков", 22);

        Function<Person, String> getFullName = Exercise1::getFullName;

        BiFunction<Person, Person, Integer> extractorAgeOfPersonWithTheLongestFullName =
            createExtractorAgeOfPersonWithTheLongestFullName(getFullName);

        assertEquals(33,
            extractorAgeOfPersonWithTheLongestFullName.apply(person1, person2).intValue());
    }
}
