package StudentManage;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author MẫnMẫn
 */
public class hocSinh implements Serializable{
    
    
    private String name;
    private int age;
    private String gender;
    private String school;
    public float mark;
    
    public static ArrayList<hocSinh> list = new ArrayList<>();
    
    public hocSinh(String name, int age, String gender, String school, float mark) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.school = school;
        this.mark = mark;
    }

    public hocSinh() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
    
    public void addHs(hocSinh s){
        this.list.add(s);
    }

    @Override
    public String toString() {
        return "hocSinh{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", school='" + school + '\'' +
                ", mark=" + mark +
                '}';
    }
}
