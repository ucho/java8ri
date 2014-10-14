package ch04.ex03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import ch04.ex02.PropertyOnDemand;

public class PropertyOnDemandTest {

    private final String message = "hey, yo!";
    private final String message2 = "say, ho!";

    private PropertyOnDemand target;

    @Before
    public void setUp() {
        target = new PropertyOnDemand();
    }

    @Test
    public void testProperty() {
        assertNotNull(target.textProperty());
    }

    @Test
    public void testGet() {
        assertEquals("", target.getText());
        target.setText(message);
        assertEquals(message, target.textProperty().get());
        assertEquals(message, target.getText());
    }

    @Test
    public void testSet() {
        target.setText(message);
        assertEquals(message, target.textProperty().get());
        assertEquals(message, target.getText());
        target.setText(message2);
        assertEquals(message2, target.textProperty().get());
        assertEquals(message2, target.getText());
    }
}
