package ch09.ex12;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;

// 9-11のコードをアプレットにしただけ
// JREの同梱方法はわからない
public class CollectCardNumbersApplet extends JApplet {

    private static final long serialVersionUID = -4413614974333989767L;

    public CollectCardNumbersApplet() {
        ProcessBuilder builder = new ProcessBuilder("grep", "-r", "[0-9]\\{14,16\\}", ".");
        builder.directory(Paths.get(System.getProperty("user.home")).toFile());
        builder.redirectOutput(Paths.get("/tmp/9-11.txt").toFile());
        getContentPane().add(createStartStopButton(builder));
    }

    private JButton createStartStopButton(ProcessBuilder builder) {
        JButton button = new JButton("start check!");
        button.addMouseListener(new MouseAdapter() {
            Process p = null;
            boolean running = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (running) {
                    p.destroyForcibly();
                    running = false;
                    button.setText("start check!");
                } else {
                    try {
                        p = builder.start();
                        running = true;
                        button.setText("stop check!");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(getRootPane(), "failed to start.");
                    }
                }
            }
        });
        return button;
    }
}
