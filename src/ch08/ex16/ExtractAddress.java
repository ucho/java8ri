package ch08.ex16;

import static org.junit.Assert.assertArrayEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ExtractAddress {

    public String[] extract(String input) {
        String regex = "(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2})\\s+(?<zipcode>\\d{5}(-\\d{4})?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            return new String[0];
        }
        String[] result = new String[3];
        result[0] = matcher.group("city");
        result[1] = matcher.group("state");
        result[2] = matcher.group("zipcode");
        return result;
    }

    @Test
    public void testExtract() {
        assertArrayEquals(new String[] { "Mountain View", "CA", "94043" }, extract("Mountain View, CA 94043"));
        assertArrayEquals(new String[] { "Apple Valley", "CA", "92308-1234" }, extract("Apple Valley, CA 92308-1234"));
    }
}
