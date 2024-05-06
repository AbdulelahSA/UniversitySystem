
package projectx;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class GUIAddTeacher extends JFrame implements ActionListener{
    private JButton b1,b2;

    JLabel lc,lt;
    JComboBox oc,ot;

    public GUIAddTeacher() {
        this.setTitle("Add Teacher");
        this.setLocation(300,350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        b1=new JButton("Add");
        b2=new JButton("Cancel");

        lc= new JLabel("Courses: ");
        lt= new JLabel("Teacher: ");

        oc= new JComboBox(GUIMenu.courses);
        ot= new JComboBox(GUIMenu.teacher);

        JPanel p=(JPanel) this.getContentPane();
        p.setLayout(new GridLayout(3,1));

        JPanel p1= new JPanel();
        JPanel p2= new JPanel();
        JPanel p3= new JPanel();

        p1.add(lc);
        p1.add(oc);
        p.add(p1);

        p2.add(lt);
        p2.add(ot);
        p.add(p2);

        p3.add(b1);
        p3.add(b2);
        p.add(p3);

        this.pack();
        this.setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
      JButton b= (JButton)e.getSource();
        if(b==b1){
            String x = oc.getSelectedItem().toString();
            String y = ot.getSelectedItem().toString();
            try {
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://C://javaprojects//Databaseproject.accdb");
                Statement st = c.createStatement();
                String rq = "INSERT INTO Teachers (course,teacher) VALUES ('"+x+"','"+y+"')";
                st.executeUpdate(rq);
                c.close();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
        }else{
            this.setVisible(false);
        }



     }



}
