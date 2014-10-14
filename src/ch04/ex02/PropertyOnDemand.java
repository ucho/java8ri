package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PropertyOnDemand {
    private String textRaw = "";
    private StringProperty textProp;

    public final StringProperty textProperty() {
        if (textProp == null) {
            textProp = new SimpleStringProperty(textRaw);
        }
        return textProp;
    }

    public final void setText(String newValue) {
        if (textProp == null) {
            textRaw = newValue;
        } else {
            textProp.set(newValue);
        }
    }

    public final String getText() {
        if (textProp == null) {
            return textRaw;
        } else {
            return textProp.get();
        }
    }
}
