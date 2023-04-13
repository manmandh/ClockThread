package HighLowStop;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PriorityDemo extends Frame {
    private HighThread highThread;
    private LowThread lowThread;

    private TextArea output;

    public PriorityDemo(){
        super("Priority Demo");
        output = new TextArea(10,20);
        add(output);
        setSize(250,200);
        setVisible(true);
        highThread = new HighThread(output);
        highThread.start();
        lowThread = new LowThread(output);
        lowThread.start();
    }

    public static void main(String[] args) {
        PriorityDemo app = new PriorityDemo();
        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit( 0 );
            }
        });
    }
}

class HighThread extends Thread{
    private TextArea display;
    public  HighThread(TextArea a){
        display = a;
        setPriority(Thread.MAX_PRIORITY);
    }
    public void run(){
        for(int x=1;x<=5;x++){
            display.append("High Priority Thread!!!\n");
            try{
                Thread.sleep(10);
            }catch (Exception e){}
        }
    }
}

class LowThread extends Thread{
    private TextArea display;
    public LowThread(TextArea a){
        display = a;
        setPriority(Thread.MIN_PRIORITY);
    }
    public void run(){
        for(int y=1;y<=5;y++){
            display.append("Low Priority Thread!!!\n");
        }
    }
}


