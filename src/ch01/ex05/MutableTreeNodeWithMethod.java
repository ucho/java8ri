package ch01.ex05;
import java.lang.reflect.Method;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class MutableTreeNodeWithMethod extends DefaultMutableTreeNode {
    
    private Object object;
    private Method method;

    public MutableTreeNodeWithMethod(Object object, Method method) {
        super();
        setUserObject(method.getName() + '(' + getParameters(method.getParameterTypes()) + ')');
        this.object = object;
        this.method = method;
    }
    
    public Method getMethod() {
        return method;
    }

    public Object getObject() {
        return object;
    }
    
    private String getParameters(Class<?>[] params) {
        if (params.length <= 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i].getSimpleName());
            if (i != params.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}