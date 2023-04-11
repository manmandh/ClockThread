package Way1Basic;
class Dat1 extends Dat{
    public void run(){
        for(int i=1;i<=9;i+=2){
            System.out.println(i+ " ");
            try{
                Dat.sleep(1000);
            }catch (Exception e){
            }
        }
    }
}
class Dat2 extends Dat{
    public void run(){
        for(int i=2;i<=10;i+=2){
            System.out.println(i+ " ");
            try{
                Dat.sleep(1000);
            }catch (Exception e){
            }
        }
    }
}
public class PrintOddOven {
    public static void main(String[] args) {
        Dat d1 = new Dat1();
        Dat d2 = new Dat2();
        d1.start();
        d2.start();
    }
}

class Dat extends Thread{
}
