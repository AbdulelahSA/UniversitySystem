
package projectx;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class GUIReservation extends JFrame implements ActionListener{
    JButton b1;

    JLabel lid,lds,las,lm;
    JTextField tid,tds,tas,tm;
    JPanel p,p1,p2,p3,p4,p5;
    String TrainId,DepartureStation,ArrivalStation,Path;
    int MaxPassenger,NP;
    public GUIReservation(String DepartureStation,String ArrivalStation,int NP,String Path) {
        this.setTitle("Reservation Page");
        this.setLocation(300,350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.DepartureStation = DepartureStation;
        this.ArrivalStation = ArrivalStation;
        this.NP = NP;
        this.Path = Path;
        try {
            Connection c = DriverManager.getConnection(this.Path);
            Statement st = c.createStatement();
            //rename columns _________
            String rq = "SELECT * FROM Trains WHERE DepartureStation = '"+this.DepartureStation+"' AND ArrivalStation = '"+this.ArrivalStation+"'";
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                this.TrainId = rs.getString(1) ;
                this.MaxPassenger = Integer.parseInt(rs.getString(4)) ;
            }
            c.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        b1=new JButton("Confirm");

        lid= new JLabel("Train id: ");
        lds= new JLabel("departure station: ");
        las= new JLabel("arrival station: ");
        lm= new JLabel("Max Passengers: ");

        tid = new JTextField(this.TrainId);
        tds = new JTextField(this.DepartureStation);
        tas = new JTextField(this.ArrivalStation);
        tm = new JTextField(""+this.MaxPassenger);

        tid.setEditable(false);
        tds.setEditable(false);
        tas.setEditable(false);
        tm.setEditable(false);

        p=(JPanel) this.getContentPane();
        p.setLayout(new GridLayout(5,1));

        p1= new JPanel();
        p1.setLayout(new GridLayout(1,2));
        p2= new JPanel();
        p2.setLayout(new GridLayout(1,2));
        p3= new JPanel();
        p3.setLayout(new GridLayout(1,2));
        p4= new JPanel();
        p4.setLayout(new GridLayout(1,2));
        p5= new JPanel();

        p1.add(lid);
        p1.add(tid);
        p.add(p1);

        p2.add(lds);
        p2.add(tds);
        p.add(p2);

        p3.add(las);
        p3.add(tas);
        p.add(p3);

        p4.add(lm);
        p4.add(tm);
        p.add(p4);

        p5.add(b1);
        p.add(p5);

        this.pack();
        this.setVisible(true);

        b1.addActionListener(this);
    }
    public static void main(String[] args) {

        GUIReservation x = new GUIReservation("Buraydah","Unaizah",2,"jdbc:ucanaccess://C://javaprojects//Databasex.accdb");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            Connection c = DriverManager.getConnection(this.Path);
            Statement st = c.createStatement();
            //rename columns _________
            String rq = "SELECT * FROM Trains WHERE DepartureStation = '"+this.DepartureStation+"' AND ArrivalStation = '"+this.ArrivalStation+"'";
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                this.MaxPassenger = Integer.parseInt(rs.getString(4)) ;
            }
            this.MaxPassenger = this.MaxPassenger - NP;
            if(this.MaxPassenger <0){
                JOptionPane.showMessageDialog(null,"The number of passengers is more than the number\n of max passengers","Sorry",JOptionPane.ERROR_MESSAGE);
            }else {
                rq = "UPDATE Trains SET MaxPassenger = " + this.MaxPassenger + " WHERE DepartureStation = '" + this.DepartureStation + "' AND ArrivalStation = '" + this.ArrivalStation + "'";
                int ri = st.executeUpdate(rq);
                p4.remove(tm);
                tm = new JTextField(""+this.MaxPassenger);
                tm.setEditable(false);
                p4.add(tm);
                pack();
                setVisible(true);
                JOptionPane.showMessageDialog(null,"Reservation is Confirmed","Congratulations",JOptionPane.PLAIN_MESSAGE);
            }
            c.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        this.setVisible(false);


    }



}
