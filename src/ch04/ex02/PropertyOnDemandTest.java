package ch04.ex02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

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
    public void testSetAndGetBeforeProperty() {
        assertEquals("", target.getText());
        target.setText(message);
        assertEquals(message, target.getText());
    }

    @Test
    public void testSetAndGetAfterProperty() {
        target.setText(message);
        assertEquals(message, target.getText());
        assertEquals(message, target.textProperty().get());
        assertEquals(message, target.getText());
        target.setText(message2);
        assertEquals(message2, target.textProperty().get());
        assertEquals(message2, target.getText());
    }
}
