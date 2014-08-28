package ch01.ex05;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class MutableTreeNodeObjectRepresent extends DefaultMutableTreeNode {

    private Object object;

    public MutableTreeNodeObjectRepresent(Object obj) {
        super(obj.getClass().getCanonicalName());
        this.object = obj;
        if (obj.getClass().isArray()) {
            createChildrenAsArray(obj);
        } else {
            createChildrenAsObject(obj);
        }
    }

    private void createChildrenAsArray(Object obj) {
        Object[] array = (Object[]) obj;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                add(new MutableTreeNodeObjectRepresent(array[i]));
            } else {
                add(new ArrayNullElementRepresent(array.getClass().getComponentType()));
            }
        }
    }

    private void createChildrenAsObject(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Arrays.<Field> sort(fields, (o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
        for (Field field : fields) {
            field.setAccessible(true);
            add(new MutableTreeNodeWithField(obj, field));
        }
        Method[] methods = obj.getClass().getMethods();
        Arrays.<Method> sort(methods, (o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
        for (Method method : methods) {
            add(new MutableTreeNodeWithMethod(obj, method));
        }
    }

    public Object getObject() {
        return object;
    }
}