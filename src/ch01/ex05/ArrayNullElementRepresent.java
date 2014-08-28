package ch01.ex05;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class ArrayNullElementRepresent extends DefaultMutableTreeNode {
    
    private Class<?> elementClass;
    
    public ArrayNullElementRepresent(Class<?> cls) {
        super(cls.getCanonicalName() + " = " + null);
        this.elementClass = cls;
    }

    public Class<?> getElementClass() {
        return elementClass;
    }
}