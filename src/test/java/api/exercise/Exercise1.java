package api.exercise;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lambda.data.Person;
import org.junit.Test;

@SuppressWarnings({"ConstantConditions", "unused", "MismatchedQueryAndUpdateOfCollection"})
public class Exercise1 {

    enum Status {
        UNKNOWN,
        PENDING,
        COMMENTED,
        ACCEPTED,
        DECLINED
    }

    @Test
    public void acceptGreaterThan21OthersDecline() {
        Person alex = new Person("Алексей", "Мельников", 20);
        Person ivan = new Person("Иван", "Стрельцов", 24);
        Person helen = new Person("Елена", "Рощина", 22);
        Map<Person, Status> candidates = new HashMap<>();
        candidates.put(alex, Status.PENDING);
        candidates.put(ivan, Status.PENDING);
        candidates.put(helen, Status.PENDING);

        // реализация
        candidates.replaceAll((person, status) ->
            person.getAge() > 21 ? Status.ACCEPTED : Status.DECLINED );

        assertEquals(Status.ACCEPTED, candidates.get(ivan));
        assertEquals(Status.ACCEPTED, candidates.get(helen));
        assertEquals(Status.DECLINED, candidates.get(alex));
    }

    @Test
    public void acceptGreaterThan21OthersRemove() {
        Person alex = new Person("Алексей", "Мельников", 20);
        Person ivan = new Person("Иван", "Стрельцов", 24);
        Person helen = new Person("Елена", "Рощина", 22);
        Map<Person, Status> candidates = new HashMap<>();
        candidates.put(alex, Status.PENDING);
        candidates.put(ivan, Status.PENDING);
        candidates.put(helen, Status.PENDING);

        candidates.put(new Person("a", "a", 19), Status.PENDING);
        candidates.put(new Person("b", "c", 16), Status.PENDING);
        candidates.put(new Person("b", "c", 5), Status.PENDING);

        // реализация
        candidates.keySet().removeIf(person -> person.getAge() <= 21);
        candidates.replaceAll((person, status) -> Status.ACCEPTED);

        Map<Person, Status> expected = new HashMap<>();
        expected.put(ivan, Status.ACCEPTED);
        expected.put(helen, Status.ACCEPTED);
        assertEquals(expected, candidates);
    }

    @Test
    public void getStatus() {
        Person alex = new Person("Алексей", "Мельников", 20);
        Person ivan = new Person("Иван", "Стрельцов", 24);
        Person helen = new Person("Елена", "Рощина", 22);
        Map<Person, Status> candidates = new HashMap<>();
        candidates.put(alex, Status.PENDING);
        candidates.put(ivan, Status.PENDING);

        // реализация
        Function<Person, Status> getStatus = person -> candidates.getOrDefault(person, Status.UNKNOWN);

        Status alexStatus = getStatus.apply(alex);
        Status ivanStatus = getStatus.apply(ivan);
        Status helenStatus = getStatus.apply(helen);

        assertEquals(Status.PENDING, alexStatus);
        assertEquals(Status.PENDING, ivanStatus);
        assertEquals(Status.UNKNOWN, helenStatus);
    }

    @Test
    public void putToNewValuesIfNotExists() {
        Person alex = new Person("Алексей", "Мельников", 20);
        Person ivan = new Person("Иван", "Стрельцов", 24);
        Person helen = new Person("Елена", "Рощина", 22);
        Person dmitry = new Person("Дмитрий", "Егоров", 30);
        Map<Person, Status> oldValues = new HashMap<>();
        oldValues.put(alex, Status.PENDING);
        oldValues.put(dmitry, Status.DECLINED);
        oldValues.put(ivan, Status.ACCEPTED);

        Map<Person, Status> newValues = new HashMap<>();
        newValues.put(alex, Status.DECLINED);
        newValues.put(helen, Status.PENDING);

        // реализация
        oldValues.forEach(newValues::putIfAbsent);

        assertEquals(Status.DECLINED, newValues.get(alex));
        assertEquals(Status.ACCEPTED, newValues.get(ivan));
        assertEquals(Status.PENDING, newValues.get(helen));
    }
}
