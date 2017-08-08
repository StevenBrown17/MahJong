//A simple clock application using javax.swing.Timer class


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopWatch extends JPanel
{
	private static Timer myTimer1;
	public static final int ONE_SEC = 1000;   //time step in milliseconds
	public static final int TENTH_SEC = 100;

	private Font myClockFont;

	public static JLabel timeLbl, score;

	private static int clockTick=0;  	//number of clock ticks; tick can be 1.0 s or 0.1 s
	private static double clockTime=0;  	//time in seconds
	private static String clockTimeString;


	public StopWatch()
	{
			
		
		setPreferredSize(new Dimension(110, 20));
		
		clockTick = 0;  		//initial clock setting in clock ticks
		clockTime = ((double)clockTick)/10.0;

		clockTimeString = new Double(clockTime).toString();
		myClockFont = new Font("Serif", Font.PLAIN, 50);
		
		score = new JLabel();
		score.setText("Game Score: 0");
		timeLbl = new JLabel();
		
		timeLbl.setFont(myClockFont);
		timeLbl.setText(clockTimeString);

		myTimer1 = new Timer(TENTH_SEC, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			clockTick++;
			clockTime = ((double)clockTick)/10.0;
			clockTimeString = new Double(clockTime).toString();
			timeLbl.setText(clockTimeString);
			//System.out.println(clockTime);
		    }
		});

		
		add(timeLbl);
		add(score);
		
		startTime();


	}//end of StopWatch constructor

	public static void startTime(){
		myTimer1.start();
	}
	public static void stopTime(){
		myTimer1.stop();

	}
	public static void resetTime(){
		clockTick = 0;
		clockTime = ((double)clockTick)/10.0;
		clockTimeString = new Double(clockTime).toString();
		timeLbl.setText(clockTimeString);
	}



}//end of public class

