package TrafficLightSp;
class TrafficLight {
    private String color = "RED";
    public synchronized void changeColor() {
        while (true) {
            try {
                if (color.equals("RED")) {
                    color = "GREEN";
                    System.out.println("Green Light!");
                    notify();
                    wait();
                } else if (color.equals("GREEN")) {
                    color = "YELLOW";
                    System.out.println("Yellow Light!");
                    notify();
                    wait();
                } else {
                    color = "RED";
                    System.out.println("Red Light!");
                    notify();
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class TrafficLightDemo {
    public static void main(String[] args) {
        final TrafficLight light = new TrafficLight();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                light.changeColor();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                light.changeColor();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                light.changeColor();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
