
package projectx;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class GUIRegisterStudent extends JFrame implements ActionListener {
    private JButton b1,b2;
    JLabel lid,lfn,lln,lb,ls1,ls2,lc;
    JTextField tid,tfn,tln,td,tm,ty;
    JComboBox oc;

    public GUIRegisterStudent() {
        this.setTitle("Register Student");
        this.setLocation(300,350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        b1=new JButton("Register");
        b2=new JButton("Cancel");

        lid= new JLabel("id:  ");
        lfn= new JLabel("First Name:  ");
        lln= new JLabel("Last Name:  ");
        lb= new JLabel("Birthday:  ");
        ls1= new JLabel("/");
        ls2= new JLabel("/");
        lc= new JLabel("cource:  ");


        tid= new JTextField(8);
        tfn= new JTextField(8);
        tln= new JTextField(8);
        td= new JTextField(2);
        tm= new JTextField(2);
        ty= new JTextField(4);

        oc = new JComboBox(GUIMenu.courses);


        JPanel p=(JPanel) this.getContentPane();
        p.setLayout(new GridLayout(6,1));
        JPanel p1= new JPanel();
        JPanel p2= new JPanel();
        JPanel p3= new JPanel();
        JPanel p4= new JPanel();
        JPanel p5= new JPanel();
        JPanel p6= new JPanel();


        p1.add(lid);
        p1.add(tid);
        p.add(p1);

        p2.add(lfn);
        p2.add(tfn);
        p.add(p2);

        p3.add(lln);
        p3.add(tln);
        p.add(p3);

        p4.add(lb);
        p4.add(td);
        p4.add(ls1);
        p4.add(tm);
        p4.add(ls2);
        p4.add(ty);
        p.add(p4);

        p5.add(lc);
        p5.add(oc);
        p.add(p5);

        p6.add(b1);
        p6.add(b2);
        p.add(p6);


        this.pack();
        this.setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b= (JButton)e.getSource();

        if(b==b1){
            try{
                 if(
                         (tid.getText().matches("[0-9]+"))
                                 &&(tfn.getText().matches("[A-Za-z]+"))
                                 &&(tln.getText().matches("[A-Za-z]+"))
                                 &&(td.getText().matches("[0-9]+"))
                                 &&(tm.getText().matches("[0-9]+"))
                                 &&(ty.getText().matches("[0-9]+"))
                 ){
                    int id = Integer.parseInt(tid.getText());
                    int d = Integer.parseInt(td.getText());
                    int m = Integer.parseInt(tm.getText());
                    int y = Integer.parseInt(ty.getText());
                    if((d>0 && d<=31)&&(m>0 && m<=12)&&(y>0)){

                        String x = oc.getSelectedItem().toString();
                        if (x=="CS221"){
                            Save(x,id,d,m,y,tfn.getText(),tln.getText());
                        } else if (x=="CS181") {
                            Save(x,id,d,m,y,tfn.getText(),tln.getText());
                        } else if (x=="CS182") {
                            Save(x,id,d,m,y,tfn.getText(),tln.getText());
                        } else if (x=="CS342") {
                            Save(x,id,d,m,y,tfn.getText(),tln.getText());
                        }

                        this.setVisible(false);
                    }else{
                        throw new DateException();
                    }

                }else {
                     if(
                             (tid.getText().equals(""))||(tfn.getText().equals(""))
                                     ||(tln.getText().equals(""))||(td.getText().equals(""))
                                     ||(tm.getText().equals(""))||(ty.getText().equals(""))
                     ){
                         throw new EmptyException();
                     }
                    throw new WrongInputException();
                }
            } catch (EmptyException ex){
                JOptionPane.showMessageDialog(null,"there is no input in one -more of the brackets.\n check again.","Error",JOptionPane.ERROR_MESSAGE);
            }catch (WrongInputException ex){
                JOptionPane.showMessageDialog(null,"the inputs you wrote are wrong .\n check if you put a negative or an alphabet.","Error",JOptionPane.ERROR_MESSAGE);
            }catch (DateException ex){
                JOptionPane.showMessageDialog(null,"wrong date.\n check the date again.","Error",JOptionPane.ERROR_MESSAGE);
            }

        }else{
            this.setVisible(false);
        }

    }

    public void Save(String name, int id,int d,int m,int y,String fname,String lname) {
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://C://javaprojects//Databaseproject.accdb");
            Statement st = c.createStatement();
            String rq = "INSERT INTO Student (id,firstname,lastname,d,m,y,course,mark) VALUES ("+id+",'"+fname+"','"+lname+"',"+d+","+m+","+y+",'"+name+"',null)";
            st.executeUpdate(rq);
            c.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
