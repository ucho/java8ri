package ch04.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PropertyOnDemand {
    private StringProperty textProp;

    public final StringProperty textProperty() {
        if (textProp == null) {
            textProp = new SimpleStringProperty();
        }
        return textProp;
    }

    public final void setText(String newValue) {
        if (textProp == null) {
            textProp = new SimpleStringProperty(newValue);
        } else {
            textProp.set(newValue);
        }
    }

    public final String getText() {
        if (textProp == null) {
            return "";
        } else {
            return textProp.get();
        }
    }

}
