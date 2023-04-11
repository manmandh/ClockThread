package ThreeThread;
//S=F(1) + F(2) + F(3)
//F1(n) = 1*2*3*...*n
//F2(n) = 1+2+3+...+n
//F3(n) = x^1+x^2+x^3+...x^n

public class ThreadThreeMath {
    FacThread t1 = new FacThread(2);
    SumThread t2 = new SumThread(3);
    SumPowThread t3 = new SumPowThread(2,1);
    public ThreadThreeMath(){
        //start 3 thread
        t1.start();
        t2.start();
        t3.start();
        try {
            //per thread execute until end
            t1.join();
            t2.join();
            t3.join();
            //print screen
            long S =t1.getResult()+ t2.getResult()+t3.getResult();
            System.out.println("\nAnswer: "+S);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       new ThreadThreeMath();
    }
}
class FacThread extends Thread{
    long gt = 1;
    int n;
    public FacThread(int k){
        n=k;
    }
    public void run(){
        for(int i=1;i<=n;i++){
            gt*=i;
        }
        System.out.println("\nF1= "+gt);
    }
    public long getResult(){
        return gt;
    }
}

class SumThread extends Thread{
    long sum=0;
    int n;
    public SumThread(int k){
        n=k;
    }
    public void run(){
        for(int i=1;i<=n;i++){
            sum+=i;
        }
        System.out.println("\nF2 = "+sum);
    }
    public long getResult(){
        return sum;
    }
}

class SumPowThread extends Thread{
    long S=0;
    int n,x;
    public SumPowThread(int y, int k){
        x=y;
        n=k;
    }
    public void run(){
        for(int i=1;i<=n;i++){
            S+=Math.pow(x,i);
        }
        System.out.println("\nF3= "+S);
    }
    public long getResult(){
        return S;
    }
}
