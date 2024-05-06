
package projectx;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUILogin extends JFrame implements ActionListener {

    private JButton b1,b2;
    JLabel lu,lp;
    JTextField tu;
    JPasswordField tp;
    public GUILogin() {
        this.setTitle("Login");
        this.setLocation(300,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1=new JButton("Login");
        b2=new JButton("Cancel");

        lu= new JLabel("User Name:  ");
        lp= new JLabel("Password:  ");

        tu= new JTextField(8);
        tp= new JPasswordField(8);

        JPanel p=(JPanel) this.getContentPane();

        JPanel p1= new JPanel();
        JPanel p2= new JPanel();
        JPanel p3= new JPanel();


        p1.add(lu);
        p1.add(tu);
        p.add(p1, BorderLayout.NORTH);

        p2.add(lp);
        p2.add(tp);
        p.add(p2, BorderLayout.CENTER);

        p3.add(b1);
        p3.add(b2);
        p.add(p3,BorderLayout.SOUTH);


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
                if((tu.getText().equals("admin"))&&(tp.getText().equals("admin"))){
                    GUIMenu g = new GUIMenu();
                    this.setVisible(false);
                }else {
                    if((tu.getText().equals(""))||(tp.getText().equals(""))){
                        throw new EmptyException();
                    }
                    throw new WrongInputException();
                }

            } catch (EmptyException ex){
                JOptionPane.showMessageDialog(null,"wrong input.\n you have to write something","Error",JOptionPane.ERROR_MESSAGE);
            }catch (WrongInputException ex){
                JOptionPane.showMessageDialog(null,"The username or password you typed is incorrect.\n Please try again.","Error",JOptionPane.ERROR_MESSAGE);
            }

        }else{
            System.exit(0);
        }



    }
}
