package ch05.ex02;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class LeapYear {

    @Test
    public void testPlusOneYear() {
        // うるう日の1年後は2月の最終日になる(直感通り)
        assertEquals(LocalDate.of(2001, 2, 28), LocalDate.of(2000, 2, 29).plusYears(1));
    }

    @Test
    public void testPlusFourYears() {
        // うるう日の4年後はうるう日になる(直感通り)
        assertEquals(LocalDate.of(2004, 2, 29), LocalDate.of(2000, 2, 29).plusYears(4));
    }

    @Test
    public void testPlusOneYear4Times() {
        // うるう日に1年を4回足してもうるう日にはならない
        // うるう日の1年後はうるう日ではないので、その1年後、1年後、1年後はうるう日にはならない
        assertEquals(LocalDate.of(2004, 2, 28),
                LocalDate.of(2000, 2, 29).plusYears(1).plusYears(1).plusYears(1).plusYears(1));
    }
}
