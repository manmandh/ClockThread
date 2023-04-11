package Way2Basic;

class MyThread extends Thread{
    int start;
    public MyThread(int s){
        start = s;
    }
    public static  void go(int s){
        for(int i=s;i<10;i+=2){
            System.out.println(i+ " ");
            try{
                Thread.sleep(1000);
            }catch (Exception e){}
        }
    }
    public void run(){
        go(start);
    }
}

public class OddOven {
    public static void main(String[] args) {
         Thread t1 = new MyThread(1);
         Thread t2 = new MyThread(2);
         t1.start();
         t2.start();
    }
}

