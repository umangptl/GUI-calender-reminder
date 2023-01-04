import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

public class AddRemEvent extends JFrame {

    public AddRemEvent() {

        //set frame
        setBounds(350, 250, 450, 300);
        setTitle("Add Reminder");
        setResizable(false);
        setUndecorated(true);

        JPanel addRemEvent = new JPanel(null);

        JLabel lblRem = new JLabel("Title");
        lblRem.setFont(new Font("Calibre", Font.PLAIN, 18));
        JLabel lblDate = new JLabel("Pick Date");
        lblDate.setFont(new Font("Calibre", Font.PLAIN, 15));
        JButton remSave = new JButton("Save");
        JButton remCancel = new JButton("Cancel");
        JTextField RName = new JTextField();

        //set date and time picker
        Date date = new Date();
        DateTimePicker datePicker = new DateTimePicker();
        datePicker.setFormats(DateFormat.getDateTimeInstance( ));
        datePicker.setTimeFormat(DateFormat.getTimeInstance( ));
        datePicker.setDate(date);

        //Set border
        addRemEvent.setBorder(BorderFactory.createTitledBorder(""));

        //cancel button
        remCancel.addActionListener(e -> {this.dispose();});

        //Add controls to pane
        setContentPane(addRemEvent);
        addRemEvent.add(lblRem);
        addRemEvent.add(RName);
        addRemEvent.add(remSave);
        addRemEvent.add(remCancel);
        addRemEvent.add(lblDate);
        addRemEvent.add(datePicker);

        //Set bounds
        lblRem.setBounds(50, 50, 90, 15);
        RName.setBounds(45,70,200,30);
        lblDate.setBounds(50,120,90,15);
        remSave.setBounds(150,210,100,40);
        remCancel.setBounds(250,210,100,40);
        datePicker.setBounds(45,140,250,30);

    }
}
