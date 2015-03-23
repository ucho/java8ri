package ch09.ex03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Test;

public class MultipleExceptions {

    public boolean flag = false;

    // 2つの例外の共通のスーパークラスであるIOExceptionをthrows宣言する
    public void process() throws IOException {
        try {
            if (flag) {
                throw new FileNotFoundException("file not found");
            } else {
                throw new UnknownHostException("unknown host");
            }
        } catch (FileNotFoundException | UnknownHostException e) {
            throw e;
        }
    }

    @Test
    public void test() {
        MultipleExceptions target = new MultipleExceptions();

        target.flag = true;
        try {
            target.process();
            fail();
        } catch (IOException e) {
            assertTrue(e instanceof FileNotFoundException);
            assertEquals("file not found", e.getMessage());
        }

        target.flag = false;
        try {
            target.process();
            fail();
        } catch (IOException e) {
            assertTrue(e instanceof UnknownHostException);
            assertEquals("unknown host", e.getMessage());
        }
    }
}
