import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.ThemeType;
import com.mindfusion.scheduling.awt.AwtCalendar;


public class CalendarProgram {
    static JLabel lblMonth, lblYear, lblReminder, lblToday, lblTitle;
    static JLabel Rmd1, Rmd2, Rmd3;
    static JButton Prev, Next, addC, addR, addD;
    static JRadioButton Reminder1, Reminder2, Reminder3;
    static JTable tblCalendar;
    static JComboBox cmbYear;
    static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane scpCalendar,scpReminder;
    static JPanel pnlCalendar,pnlReminder,pnlDaily, pnlTop, pnlView;
    static int realYear, realMonth, realDay, currentYear, currentMonth;

    static AwtCalendar calendar;

    static JTextField search;

    public static void main(String[] args) {

        final String Icon_DIRECTORY = "/Users/umangpatel/Documents/intellij projects Java/CSC420FinalProject/icon/";

        //Prepare frame
        frmMain = new JFrame("CSC 420 Final Project Scheduler");
        // set frame
        frmMain.setBounds(100,75,1100,775);
        pane = frmMain.getContentPane();
        pane.setLayout(null);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Get Icon
        Image LogoIcon = new ImageIcon(Icon_DIRECTORY + "upcoming.png").getImage();
        Image Logo = LogoIcon.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
        Image SettingIcon = new ImageIcon(Icon_DIRECTORY + "setting 2.png").getImage();
        Image Setting = SettingIcon.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
        Image ProfileIcon = new ImageIcon(Icon_DIRECTORY + "profile.png").getImage();
        Image Profile = ProfileIcon.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
        Image SearchIcon = new ImageIcon(Icon_DIRECTORY + "search.png").getImage();
        Image Search = SearchIcon.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);

        //set icon
        JLabel picLogo = new JLabel(new ImageIcon(Logo));
        JLabel picSetting = new JLabel(new ImageIcon(Setting));
        JLabel picProfile = new JLabel(new ImageIcon(Profile));
        JLabel picsearch = new JLabel(new ImageIcon(Search));

        //Create controls
        lblMonth = new JLabel("January");
        lblMonth.setFont(new Font("Calibre", Font.PLAIN, 20));
        lblYear = new JLabel("Year: ");
        lblYear.setFont(new Font("Calibre", Font.PLAIN, 16));
        lblReminder = new JLabel("Reminder");
        lblReminder.setFont(new Font("Calibre", Font.PLAIN, 18));
        lblToday = new JLabel("Today");
        lblToday.setFont(new Font("Calibre", Font.PLAIN, 18));
        lblTitle = new JLabel("Scheduler");
        lblTitle.setFont(new Font("Calibre", Font.PLAIN, 30));

        //create reminder labels
        Rmd1 = new JLabel("mm/dd/yyyy");
        Rmd1.setFont(new Font("Calibre", Font.PLAIN, 10));
        Rmd2 = new JLabel("mm/dd/yyyy");
        Rmd2.setFont(new Font("Calibre", Font.PLAIN, 10));
        Rmd3 = new JLabel("mm/dd/yyyy");
        Rmd3.setFont(new Font("Calibre", Font.PLAIN, 10));

        cmbYear = new JComboBox();
        search = new JTextField("Search..");

        Prev = new JButton("<<");
        Prev.setFont(new Font("Calibre",Font.BOLD,20));
        Next = new JButton(">>");
        Next.setFont(new Font("Calibre",Font.BOLD,20));
        addC = new JButton("+");
        addC.setFont(new Font("Calibre",Font.BOLD,20));
        addR = new JButton("+");
        addR.setFont(new Font("Calibre",Font.BOLD,18));
        addD = new JButton("+");
        addD.setFont(new Font("Calibre",Font.BOLD,18));
        Reminder1 = new JRadioButton("Reminder1");
        Reminder1.setFont(new Font("Lucid Grande",Font.PLAIN, 15));
        Reminder2 = new JRadioButton("Reminder2");
        Reminder2.setFont(new Font("Lucid Grande",Font.PLAIN, 15));
        Reminder3 = new JRadioButton("Reminder3");
        Reminder3.setFont(new Font("Lucid Grande",Font.PLAIN, 15));

        mtblCalendar = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        SpringLayout springLayout = new SpringLayout();

        tblCalendar = new JTable(mtblCalendar);
        scpCalendar = new JScrollPane(tblCalendar);
        scpReminder = new JScrollPane();
        pnlCalendar = new JPanel(null);
        pnlReminder = new JPanel(null);
        pnlDaily = new JPanel(null);
        pnlTop = new JPanel(null);
        pnlView = new JPanel(springLayout);

        //Set border
        pnlTop.setBorder(BorderFactory.createTitledBorder(""));

        //Add controls to Top pane
        pane.add(pnlTop);
        pnlTop.add(lblTitle);
        pnlTop.add(search);
        pnlTop.add(picLogo);
        pnlTop.add(picsearch);
        pnlTop.add(picSetting);
        pnlTop.add(picProfile);

        //Set bounds
        pnlTop.setBounds(2, 2, 1098, 78);
        lblTitle.setBounds(125,25,300,25);
        search.setBounds(560,20,200,36);
        picLogo.setBounds(20,3,90,70);
        picsearch.setBounds(760,15,50,50);
        picSetting.setBounds(950,15,50,50);
        picProfile.setBounds(1025,15,50,50);

        // set panel color
        pnlReminder.setBackground(new Color(204,229,255));
        pnlDaily.setBackground(new Color(204,229,255));

        //Set border
        pnlCalendar.setBorder(BorderFactory.createTitledBorder(""));

        //Register action listeners
        Prev.addActionListener(new btnPrev_Action());
        Next.addActionListener(new btnNext_Action());
        addC.addActionListener(new add_calenderEvent());
        cmbYear.addActionListener(new cmbYear_Action());

        //Add controls to calender pane
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);
        pnlCalendar.add(lblYear);
        pnlCalendar.add(cmbYear);
        pnlCalendar.add(Prev);
        pnlCalendar.add(Next);
        pnlCalendar.add(addC);
        pnlCalendar.add(scpCalendar);

        //Set bounds
        pnlCalendar.setBounds(2, 80, 820, 660);
        lblMonth.setBounds(340, 25, 130, 40);
        lblYear.setBounds(660, 25, 100, 40);
        cmbYear.setBounds(700, 25, 100, 40);
        Prev.setBounds(250, 25, 60, 35);
        Next.setBounds(475, 25, 60, 35);
        addC.setBounds(15,25,60,40);
        scpCalendar.setBounds(10, 75, 800, 575);

        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;

        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (int i = 0; i < 7; i++) {
            mtblCalendar.addColumn(headers[i]);
        }

        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);

        //Single cell selection
        tblCalendar.setColumnSelectionAllowed(true);
        tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Set row/column count
        tblCalendar.setRowHeight(92);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);

        //Populate table
        for (int i = realYear - 100; i <= realYear + 100; i++) {
            cmbYear.addItem(String.valueOf(i));
        }

        //Refresh calendar
        refreshCalendar(realMonth, realYear); //Refresh calendar

        //Set border
        pnlReminder.setBorder(BorderFactory.createTitledBorder(""));

        //Register action listeners
        addR.addActionListener(new add_ReminderEvent());

        //Add controls to Reminder pane
        pane.add(pnlReminder);
        pnlReminder.add(addR);
        pnlReminder.add(Reminder1);
        pnlReminder.add(Reminder2);
        pnlReminder.add(Reminder3);
        pnlReminder.add(lblReminder);
        pnlReminder.add(Rmd1);
        pnlReminder.add(Rmd2);
        pnlReminder.add(Rmd3);
        pnlReminder.add(scpReminder);

        //Set bounds
        pnlReminder.setBounds(820, 80, 280, 330);
        addR.setBounds(245,10,25,25);
        Reminder1.setBounds(5,40,250,50);
        Reminder2.setBounds(5,80,250,50);
        Reminder3.setBounds(5,120,250,50);
        Rmd1.setBounds(30,70,250,25);
        Rmd2.setBounds(30,110,250,25);
        Rmd3.setBounds(30,150,250,25);
        lblReminder.setBounds(100,15,100,25);
        scpReminder.setBounds(5,40,270,285);

        //Set border
        pnlDaily.setBorder(BorderFactory.createTitledBorder(""));

        //Register action listeners
        addD.addActionListener( new add_DailyEvent());

        //Add controls to Daily pane
        pane.add(pnlDaily);
        pnlDaily.add(addD);
        pnlDaily.add(lblToday);

        //Set bounds
        pnlDaily.setBounds(820, 410, 280, 330);
        addD.setBounds(245,10,25,25);
        lblToday.setBounds(110,15,100,25);

        //Set border
        pnlView.setBorder(BorderFactory.createTitledBorder(""));

        //Set bounds
        pnlView.setBounds(825, 450, 270, 285);

        calendar = new AwtCalendar();
        calendar.setCurrentView(CalendarView.Timetable);
        calendar.setTheme(ThemeType.Standard);

        calendar.getTimetableSettings().getDates().clear();
        calendar.getTimetableSettings().getDates().add(DateTime.today());

        springLayout.putConstraint(SpringLayout.EAST, calendar, 0, SpringLayout.EAST, pnlView);
        springLayout.putConstraint(SpringLayout.NORTH, calendar, 0, SpringLayout.NORTH, pnlView);
        springLayout.putConstraint(SpringLayout.WEST, calendar, 0, SpringLayout.WEST, pnlView);
        springLayout.putConstraint(SpringLayout.SOUTH, calendar, 0, SpringLayout.SOUTH, pnlView);

        //Add controls to Top pane
        pnlView.add(calendar);
        pane.add(pnlView);

        //Make frame visible
        frmMain.setResizable(false);
        frmMain.setVisible(true);
    }
    public static void refreshCalendar(int month, int year){
        //Variables
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month

        //Allow/disallow buttons
        Prev.setEnabled(true);
        Next.setEnabled(true);
        if (month == 0 && year <= realYear-10){Prev.setEnabled(false);} //Too early
        if (month == 11 && year >= realYear+100){Next.setEnabled(false);} //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        // lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        //Clear table
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Draw calendar
        for (int i=1; i<=nod; i++){
            int row = (i + som - 2) / 7;
            int column  =  (i+som-2)%7;
            mtblCalendar.setValueAt(i, row, column);
        }

        //Apply renderers
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
        addC.setEnabled(true);

    }

    static class tblCalendarRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6){ //Weekend
                setBackground(new Color(204,229,255));

            }
            else{ //Week
                setBackground(new Color(255, 255, 255));
            }
            if (value != null){
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
                    setBackground(new Color(225, 225, 225));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }

    static class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 0){ //Back one year
                currentMonth = 11;
                currentYear -= 1;
            }
            else{ //Back one month
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 11){ //Forward one year
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Forward one month
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class cmbYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }

    // popup for add calendar events
    static class add_calenderEvent implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            AddCalEvent Event= new AddCalEvent();
            Event.setVisible(true);

        }
    }

    // popup for add Reminder events
    static class add_ReminderEvent implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            AddRemEvent reminder = new AddRemEvent();
            reminder.setVisible(true);

        }
    }
    // popup for add daily events same as Calendar event
    static class add_DailyEvent implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            AddCalEvent Event= new AddCalEvent();
            Event.setVisible(true);
        }
    }
}