package HumanResourceManageMent;
import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {
    public static ArrayList<HRM> list;
    public Company() {
        list = new ArrayList<>();
    }


    public void addHRM(HRM hrm){
        list.add(hrm);
    }

    @Override
    public String toString() {
        return "Company{" +
                "list=" + list +
                '}';
    }
}