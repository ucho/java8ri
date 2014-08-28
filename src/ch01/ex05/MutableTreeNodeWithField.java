package ch01.ex05;
import java.lang.reflect.Field;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class MutableTreeNodeWithField extends DefaultMutableTreeNode {
    
    private Object object;
    private Field field;
    
    public MutableTreeNodeWithField(Object object, Field field) {
        this.object = object;
        this.field = field;
        try {
            setUserObject(field.getName() + " = " + field.get(this.object));
        } catch (Exception e) {
            setUserObject(field.getName() + " : " + e.toString());
        }
    }

    public Object getObject() {
        return object;
    }

    public Field getField() {
        return field;
    }
}