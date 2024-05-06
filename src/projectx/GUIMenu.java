
package projectx;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUIMenu extends JFrame implements ActionListener{
    private JButton cr, cc, cs, dis;
    public static String courses[]= {"CS221","CS181","CS182","CS342"};
    public static String teacher[]= {"Waled Karamti","Ahmad Feki","Mohamed Alabdulatif","Ali Khaled"};

    public GUIMenu()  {
        this.setTitle("Menu");
        this.setLocation(300,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p= (JPanel) this.getContentPane();
        p.setLayout(new GridLayout(4,1));

        cr= new JButton("Register Student");
        cc= new JButton("Add Teacher");
        cs= new JButton("Add Marks");
        dis= new JButton("Statistics");

        p.add(cr);
        p.add(cc);
        p.add(cs);
        p.add(dis);

        this.pack();

        this.setVisible(true);

        cr.addActionListener(this);
        cc.addActionListener(this);
        cs.addActionListener(this);
        dis.addActionListener(this);
    }


    public static void main(String[] args) {

        GUILogin l = new GUILogin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton c= (JButton)e.getSource();

            if(c==cr) { GUIRegisterStudent gr= new GUIRegisterStudent();
            }
            if(c==cc) { GUIAddTeacher gc= new GUIAddTeacher();
            }
            if(c==cs) { GUIAddMarks gr= new GUIAddMarks();
            }
            if(c==dis) { GUIStatistics gr= new GUIStatistics();
            }



    }




}
