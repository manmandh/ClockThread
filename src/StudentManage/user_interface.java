package StudentManage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
public class user_interface extends javax.swing.JFrame {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<hocSinh> list = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    public user_interface() {
        initComponents();
        list = new DAO().list3();
        model.setColumnIdentifiers(
                new Object[]{
                   "Name", "Age","Gender","School", "Mark" 
                }
        );
        for(hocSinh obj: list){
            model.addRow(
                    new Object[]{obj.getName(), obj.getAge(), obj.getGender(), obj.getSchool(), obj.getMark()}
            );
        }
        jTable1.setModel(model);   
    }
    public void writeFile(String filePath) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
        out.writeObject(list);
        System.out.println("Write sucessfully");
        out.close();
    }
    
    public void readFile(String filePath) throws IOException,ClassNotFoundException{
        List<hocSinh> hss;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
        hss = (List<hocSinh>)in.readObject();
        System.out.println("Read sucessfully");
        hss.forEach(System.out::println);
        in.close();
    }
    
    public void sort(){
        list.stream().sorted(Comparator.comparingDouble(s->s.mark)).forEach(System.out::println);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Age", "Gender", "School", "Mark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(25);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(user_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        user_interface us = new user_interface();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                us.setVisible(true);
            }
        });
        String filePath = "D:\\notepac doc\\fdf.txt";
        int option;
        while(true){
            System.out.println("1. In danh sach vao file");
            System.out.println("2. Hien thi danh sach ra man hinh tu file");
            System.out.println("3. Xoa sinh vien");
            System.out.println("4. Sap xep theo diem");
            System.out.println("5. Cap nhat thong tin sinh vien bang ten hoc sinh");
            option = scanner.nextInt();
            switch(option){
                case 1:{
                    try{
                    us.writeFile(filePath);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                try{
                    us.readFile(filePath);
                }catch(IOException e){
                        e.printStackTrace();
                    }
                break;
                }
                case 3:{
                    new DAO().xoaSinhVien();
                    list = new DAO().list3();
                    us.writeFile(filePath);
                    break;
                }
                case 4:{
                    us.sort();
                    break;
                }
                case 5:{
                    int i = new DAO().updateSinhVien();
                    if( i>0) {System.out.println("Cap nhat thanh cong, press 2 de xem ket qua");
                        list = new DAO().list3();
                        us.writeFile(filePath);
                    }
                    else System.out.println("Ten sinh vien khong hop le"); 
                    
                    break;
                }
                case 0: {
                    return;
                }
            }
        }
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
