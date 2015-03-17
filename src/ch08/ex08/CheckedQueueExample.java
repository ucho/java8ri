package ch08.ex08;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

@SuppressWarnings("rawtypes")
public class CheckedQueueExample {

    @SuppressWarnings("unchecked")
    public static void putMoreWork(Queue q, Object w) {
        q.add(w);
    }

    @Test(expected = ClassCastException.class)
    public void testPutToQueue() {
        Queue<Path> q = Collections.checkedQueue(new LinkedList<Path>(), Path.class);
        putMoreWork(q, "/tmp/hoge");
    }
}
