package HumanResourceManageMent;
import java.io.Serializable;

public class HRM implements Serializable {
    
    String name;
    int DB;
    int quantity;
    String unit;
    public HRM(){

    }
    public HRM(String name, int DB, int quantity, String unit){
        this.name= name;
        this.DB = DB;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "HRM{" +
                "name='" + name + '\'' +
                ", DB=" + DB +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                '}';
    }
}
