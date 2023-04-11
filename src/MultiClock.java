import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MultiClock extends JFrame implements ActionListener, Runnable {
    JButton createClock = new JButton("new Clock");
    JLabel clock = new JLabel();
    Thread thread;

public MultiClock(){
    Container container = this.getContentPane();
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    clock = new JLabel(simpleDateFormat.format(calendar.getTime()),JLabel.CENTER);
    clock.setFont(new Font(clock.getFont().getName(), Font.PLAIN,40));
    clock.setForeground(Color.RED);
    container.add(createClock,"North");
    container.add(clock);
    this.pack();
    this.setVisible(true);
    createClock.addActionListener(this);
    Thread thread = new Thread(this);
    thread.start();
}
public void tick(){
    try{
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        clock.setText(simpleDateFormat.format(calendar.getTime()));
    }catch (Exception e){
        e.printStackTrace();
    }
}
public void  run(){
    while (true){
        tick();
    }
}

public void actionPerformed(ActionEvent e){
    Thread thread = new Thread(new MultiClock());
    thread.start();
}

public static void main(String[] args) {
        new MultiClock();
    }
}