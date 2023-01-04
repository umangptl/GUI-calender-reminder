import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;


public class AddCalEvent extends JFrame {

    public AddCalEvent() {

        //set frame
        setBounds(350, 250, 450, 300);
        setTitle("Add CalenderEvent");
        setResizable(false);
        setUndecorated(true); // disable top close only option save and cancel

        JPanel addCalEvent = new JPanel(null);

        JLabel lblCal = new JLabel("Title");
        lblCal.setFont(new Font("Calibre", Font.PLAIN, 18));
        JLabel lbl2Date = new JLabel("Pick Date and Time");
        lbl2Date.setFont(new Font("Calibre", Font.PLAIN, 15));
        JButton calSave = new JButton("Save");
        JButton calCancel = new JButton("Cancel");
        JTextField CName = new JTextField();

        //set date and time picker
        Date date = new Date();
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setFormats(DateFormat.getDateTimeInstance( ));
        dateTimePicker.setTimeFormat(DateFormat.getTimeInstance( ));
        dateTimePicker.setDate(date);

        //Set border
        addCalEvent.setBorder(BorderFactory.createTitledBorder(""));

        //cancel button
        calCancel.addActionListener(e -> {
            this.dispose();
        });

        //Add controls to pane
        setContentPane(addCalEvent);
        addCalEvent.add(lblCal);
        addCalEvent.add(lbl2Date);
        addCalEvent.add(calSave);
        addCalEvent.add(calCancel);
        addCalEvent.add(CName);
        addCalEvent.add(dateTimePicker);

        //Set bounds
        lblCal.setBounds(50, 50, 90, 18);
        CName.setBounds(45,70,200,30);
        lbl2Date.setBounds(50,120,150,15);
        calSave.setBounds(150,210,100,40);
        calCancel.setBounds(250,210,100,40);
        dateTimePicker.setBounds(45,140,250,30);

    }
}