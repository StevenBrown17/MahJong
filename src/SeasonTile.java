import java.awt.*;
import java.net.URL;

import javax.swing.*;

@SuppressWarnings("serial")
public class SeasonTile extends PictureTile{
	
	private	static	ImageIcon[]	season = new ImageIcon[6];
	
	public SeasonTile(String name){
		super(name);
		setToolTipText(toString());
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		switch(name){
			case "Spring":
				g.drawImage(season[0].getImage(), 15, 25,(int)(season[0].getIconWidth()*0.75),(int)(season[0].getIconHeight()*0.75), this);
				break;
			case "Summer":
				g.drawImage(season[1].getImage(), 15, 17,(int)(season[1].getIconWidth()*0.75),(int)(season[1].getIconHeight()*0.75), this);
				break;
			case "Fall":
				g.drawImage(season[2].getImage(), 13, 15,(int)(season[2].getIconWidth()*0.75),(int)(season[2].getIconHeight()*0.75), this);
				break;
			case "Winter":
				g.drawImage(season[3].getImage(), 17, 20,(int)(season[3].getIconWidth()*0.75),(int)(season[3].getIconHeight()*0.75), this);
				break;

		}
	}
	
	static{
		String[]	files = { "Spring", "Summer", "Fall", "Winter"};
		
		
		
		for (int i = 0; i < files.length; i++){
			URL url = SeasonTile.class.getResource(
					"resources/" + files[i] + ".png");
				season[i] = new ImageIcon(url);
		}
	}
	
	
	
	
	

//	public static void main(String[] args)
//	{
//		JFrame	frame = new JFrame();
//
//		frame.setLayout(new FlowLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Season Tiles");
//
//		frame.add(new SeasonTile("Spring"));
//		frame.add(new SeasonTile("Summer"));
//		frame.add(new SeasonTile("Fall"));
//		frame.add(new SeasonTile("Winter"));
//
//		frame.pack();
//		frame.setVisible(true);
//	}//end main
}//end season tile class
