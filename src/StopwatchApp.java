import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchApp extends JFrame {
    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private Timer timer;
    private long startTime;

    public StopwatchApp() {
        setTitle("Stopwatch App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("00:00:00.000");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(timeLabel);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });
        add(stopButton);

        pack();
        setLocationRelativeTo(null);
    }

    private void start() {
        startTime = System.currentTimeMillis();
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                updateTime(elapsedTime);
            }
        });
        timer.start();
    }

    private void stop() {
        timer.stop();
    }

    private void updateTime(long elapsedTime) {
        long milliseconds = elapsedTime % 1000;
        long seconds = (elapsedTime / 1000) % 60;
        long minutes = (elapsedTime / 60000) % 60;
        long hours = (elapsedTime / 3600000) % 24;
        String time = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
        timeLabel.setText(time);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StopwatchApp app = new StopwatchApp();
                app.setVisible(true);
            }
        });
    }
}
