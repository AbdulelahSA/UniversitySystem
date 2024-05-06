
package projectx;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;



public class GUIAddMarks extends JFrame  implements ActionListener{
    private JButton b1,b2;

    JLabel lc,ls,lm,l100;
    JComboBox oc,os;
    JTextField tm;
    JPanel p,p1,p2,p3,p4;



    public GUIAddMarks() {
        this.setTitle("Add Marks");
        this.setLocation(300,350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        b1=new JButton("Add");
        b2=new JButton("Cancel");

        lc= new JLabel("Courses: ");
        ls= new JLabel("Student: ");
        lm= new JLabel("Mark: ");
        l100= new JLabel("/100");

        oc= new JComboBox(GUIMenu.courses);

        os= new JComboBox();//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

        tm= new JTextField(3);


        p=(JPanel) this.getContentPane();
        p.setLayout(new GridLayout(4,1));

        p1= new JPanel();
        p2= new JPanel();
        p3= new JPanel();
        p4= new JPanel();

        p1.add(lc);
        p1.add(oc);
        p.add(p1);

        p2.add(ls);
        p2.add(os);
        p.add(p2);

        p3.add(lm);
        p3.add(tm);
        p3.add(l100);
        p.add(p3);

        p4.add(b1);
        p4.add(b2);
        p.add(p4);

        this.pack();
        this.setVisible(true);
        oc.addActionListener(new c5());
        b1.addActionListener(this);
        b2.addActionListener(this);
    }
    class c5 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int n = numberOfStudents(oc.getSelectedItem().toString());
            String sarr[] = new String[n];
            try {
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://C://javaprojects//Databaseproject.accdb");
                Statement st = c.createStatement();
                String rq = "SELECT firstname FROM Student WHERE course = '"+oc.getSelectedItem().toString()+"'";
                ResultSet rs = st.executeQuery(rq);
                int i = 0;
                while (rs.next())
                    sarr[i++] = rs.getString(1);

                c.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            p2.remove(os);
            os= new JComboBox(sarr);
            p2.add(os);

            pack();
            setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b= (JButton)e.getSource();
        if(b==b1){
            try{
                if((os.getSelectedItem()==null)||(tm.getText().equals(""))){
                    throw new EmptyException();
                }else{
                    int mark = Integer.parseInt(tm.getText());
                    try {
                        Connection c = DriverManager.getConnection("jdbc:ucanaccess://C://javaprojects//Databaseproject.accdb");
                        Statement st = c.createStatement();
                        String rq = "UPDATE Student SET mark = "+mark+" WHERE firstname = '"+os.getSelectedItem().toString()+"'";
                        int rs = st.executeUpdate(rq);
                        c.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } catch (EmptyException ex){
                JOptionPane.showMessageDialog(null,"there is no input in one -more of the brackets.\n check again.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            this.setVisible(false);
        }
        this.setVisible(false);
    }

    public int numberOfStudents(String name) {
        int x = 0;
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://C://javaprojects//Databaseproject.accdb");
            Statement st = c.createStatement();
            String rq = "SELECT COUNT(*) FROM Student WHERE course = '"+name+"'";
            ResultSet rs = st.executeQuery(rq);
            while (rs.next())
                x = Integer.parseInt(rs.getString(1));

            c.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return x;
    }

}
