package ch08.ex14;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Objects;

import org.junit.Test;

// 問題には「もっと役立つエラーメッセージ」とあるが、Objects.requireNonNullができることは
// nullチェックしてNullPointerExceptionをthrowsすることと等価のはず。
// よって、本質的には「もっと役立つエラーメッセージ」を投げることはできないのでは？ と思う。
// このメソッドの利点は、nullチェックをインラインで書けることであるように思う。
// よって解答としてはこのメソッドを使うことによって、たしかに短くなるよね、ということだけを示している。
class User8 {
    private static int nextID = 1;
    final private int id;
    final private String name;

    public User8(String name) {
        this.id = nextID++;
        this.name = Objects.requireNonNull(name, () ->
                "name must not be null. id=" + id);
    }

    public String getName() {
        return name;
    }
}

class User7 {
    private static int nextID = 1;
    final private int id;
    final private String name;

    public User7(String name) {
        this.id = nextID++;
        if (name == null) {
            throw new NullPointerException("name must not be null. id=" + id);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class RequireNonNullExample {

    @Test
    public void test() {
        NullPointerException e1 = null;
        NullPointerException e2 = null;
        try {
            new User8(null);
            fail();
        } catch (NullPointerException e) {
            e1 = e;
        }
        try {
            new User7(null);
            fail();
        } catch (NullPointerException e) {
            e2 = e;
        }
        assertEquals(e1.getClass(), e2.getClass());
        assertEquals(e1.getMessage(), e2.getMessage());
    }
}
