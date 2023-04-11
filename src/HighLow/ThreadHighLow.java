package HighLow;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ThreadHighLow extends Frame {
    private HighThread highThread;
    private LowThread lowThread;

    private  TextArea output;

    public ThreadHighLow(){
        super("Thread High Low");
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
        ThreadHighLow app = new ThreadHighLow();
        app.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
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

