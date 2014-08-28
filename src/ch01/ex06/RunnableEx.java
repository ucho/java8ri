package ch01.ex06;

public interface RunnableEx {
    void run() throws Exception;

    static Runnable uncheck(RunnableEx ex) {
        return () -> {
            try {
                ex.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static void main(String[] args) {
        new Thread(RunnableEx.uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
        })).start();
    }
}