package TrafficLight;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RedYellowGreen extends JFrame implements Runnable {
    private JPanel redPanel, yellowPanel, greenPanel;
    private int time;
    public RedYellowGreen(int time) {
        //design UI
        super("Traffic Light");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.time = time;
        redPanel = createPanel(Color.RED, false);
        yellowPanel = createPanel(Color.YELLOW, true);
        greenPanel = createPanel(Color.GREEN, false);
        add(redPanel);
        add(yellowPanel);
        add(greenPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //create JPanel
    private JPanel createPanel(Color color, boolean isOn) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        //such as if else
        panel.setBackground(isOn ? color : Color.BLACK);
        return panel;
    }
    //to execute
    public void run() {
        while (true) {
            redPanel.setBackground(Color.RED);
            yellowPanel.setBackground(Color.YELLOW);
            try {
                //wait();
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            greenPanel.setBackground(Color.DARK_GRAY);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            redPanel.setBackground(Color.DARK_GRAY);
            yellowPanel.setBackground(Color.DARK_GRAY);
            greenPanel.setBackground(Color.GREEN);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //set optional time
        RedYellowGreen redYellowGreen = new RedYellowGreen(500);
        Thread thread = new Thread(redYellowGreen);
        thread.start();
    }
}

