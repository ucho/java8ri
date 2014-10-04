package ch03.ex09;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

public class TestLexicographicComparator {

    @Test
    public void testLexicographicComparator() {
        Comparator<Object> comp = ComparatorUtil.lexicographicComparator("lastname", "firstname");
        assertTrue(comp.compare(new Person("Taro", "Yamada"), new Person("Taro", "Yamada")) == 0);
        assertTrue(comp.compare(new Person("Taro", "Yamada"), new Person("Jiro", "Yamada")) > 0);
        assertTrue(comp.compare(new Person("Taro", "Yamada"), new Person("Taro", "Tanaka")) > 0);
        assertTrue(comp.compare(new Person("Jiro", "Yamada"), new Person("Taro", "Yamada")) < 0);
        assertTrue(comp.compare(new Person("Taro", "Tanaka"), new Person("Taro", "Yamada")) < 0);
    }
}

class Person {
    String firstname;
    String lastname;

    Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}