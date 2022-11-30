package se.lexicon.data.impl;


import se.lexicon.data.DataStorage;
import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl() {
        personList = PersonGenerator.getInstance().generate(1000);
    }

    public static DataStorage getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
        for (Person person : personList)
            if (filter.test(person))
                return person;

        return null;

    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {

      /*  for (Person person : personList)
            if (filter.test(person)) return personToString.apply(person); */

        Optional<String> found = personList.stream().filter(filter).findFirst().map(personToString);
        return found.orElse("Not found");


    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter,
                                                   Function<Person, String> personToString) {


        return personList.stream().filter(filter).map(personToString).collect(Collectors.toList());

    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer) {
      /*  for (Person person : personList)
            if (filter.test(person)) consumer.accept(person); */


        personList.stream().filter(filter).forEach( consumer);

    }


    @Override
    public List<Person> findAndSort(Comparator<Person> comparator) {
        return personList.stream().sorted(comparator).collect(Collectors.toList());

    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator) {

       return personList.stream().filter(filter).sorted(comparator).collect(Collectors.toList());


    }
}
