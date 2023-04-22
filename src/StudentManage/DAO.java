
package StudentManage;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
public class DAO {
    private static String DB_URL = "jdbc:mysql://localhost:3306/database1";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    static Connection conn;
    
    ArrayList<hocSinh> list2 = new ArrayList<>();
    public ArrayList<hocSinh> list3 (){
        ArrayList<hocSinh> list2 = new ArrayList<>();
        String sql = "Select * from hocsinh";
        try{
            String name ="";
            int age = 0;
            String gender ="";
            String school = "";
            float mark = 0;
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1,"3");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                hocSinh hs  =  new hocSinh();
                name = rs.getString("namee"); 
                hs.setName(name);
                age  = rs.getInt("age");
                hs.setAge(age);
                gender = rs.getString("gender");
                hs.setGender(gender);
                school = rs.getString("school");
                hs.setSchool(school);
                mark = rs.getFloat("mark");
                hs.setMark(mark);
                
                list2.add(hs);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return list2;
    }
    
    
    public void xoaSinhVien() throws SQLException {
        String sql  = "DELETE FROM hocsinh WHERE namee = ?";
        PreparedStatement ps  =  conn.prepareStatement(sql);
        System.out.println("Nhap ten muon xoa: ");
        String name  =  new Scanner(System.in).nextLine();
        ps.setString(1, name);
        ps.executeUpdate();
    }
    
    public int updateSinhVien() throws SQLException{
        String sql  = "UPDATE  hocsinh SET age = ?, gender = ?, school = ?,mark =? WHERE namee=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println("Nhap ten sinh vien muon cap nhat");
        String abc = tenSinhVien(new Scanner(System.in).nextLine());
        if(abc != null){
            ps.setString(5, abc);
            System.out.println("Nhap tuoi can cap nhat");
            ps.setString(1, new Scanner(System.in).nextLine());
            System.out.println("Nhap gioi tinh can cap nhat");
            ps.setString(2, new Scanner(System.in).nextLine());
            System.out.println("Nhap truong can cap nhat");
            ps.setString(3, new Scanner(System.in).nextLine());
            System.out.println("Nhap diem can cap nhat");
            ps.setString(4, new Scanner(System.in).nextLine());
            
        }else return -1;
        return ps.executeUpdate();
        
    }
    public String tenSinhVien(String sinhvien) throws SQLException{
        String name = "";
        String sql = "SELECT namee FROM hocsinh WHERE namee = ?";
        PreparedStatement ps  = conn.prepareStatement(sql);
        ps.setString(1, sinhvien);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            name = rs.getString("namee");
            return name;
        }
        
        return null;
    }
    
    
    public DAO(){
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        if(conn != null){
//            System.out.println("true");
        }else System.out.println("False");
    }
    
    public static void main(String[] args) {
        DAO d =new DAO();
    }
    public static Connection getConnection(String dbURL, String userName, 
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    
}

