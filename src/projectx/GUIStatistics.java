package projectx;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class GUIStatistics extends JFrame implements ActionListener {
    JLabel lc, lp1, lp2, lf1, lf2;
    JComboBox oc;
    JPanel p,p1,p2,p3;
    public GUIStatistics() {
        this.setTitle("Statistics");
        this.setLocation(300, 350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lc = new JLabel("cource:  ");
        lp1 = new JLabel("nb pass:  ");
        lp2 = new JLabel("");
        lf1 = new JLabel("nb failed:  ");
        lf2 = new JLabel("");
        oc = new JComboBox(GUIMenu.courses);


        p = (JPanel) this.getContentPane();
        p.setLayout(new GridLayout(3, 1));
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();


        p1.add(lc);
        p1.add(oc);
        p.add(p1);

        p2.add(lp1);
        p2.add(lp2);
        p.add(p2);

        p3.add(lf1);
        p3.add(lf2);
        p.add(p3);


        this.pack();
        this.setVisible(true);

        oc.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        oc.getSelectedItem().toString();
        String pass = "";
        String failed = "";
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://C://javaprojects//Databaseproject.accdb");
            Statement st = c.createStatement();
            String rq = "SELECT COUNT(*) FROM (SELECT * FROM Student WHERE mark >= 60) WHERE course = '"+oc.getSelectedItem().toString()+"'";
            ResultSet rs = st.executeQuery(rq);
            while (rs.next())
                pass = rs.getString(1);
            rq = "SELECT COUNT(*) FROM (SELECT * FROM Student WHERE mark < 60) WHERE course = '"+oc.getSelectedItem().toString()+"'";
            rs = st.executeQuery(rq);
            while (rs.next())
                failed = rs.getString(1);
            c.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        p2.remove(lp2);
        p3.remove(lf2);
        lp2 = new JLabel(pass);
        lf2 = new JLabel(failed);
        p2.add(lp2);
        p3.add(lf2);
        pack();
        setVisible(true);
    }
}
