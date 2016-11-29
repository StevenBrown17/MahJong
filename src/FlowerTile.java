//import java.awt.FlowLayout;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
//import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FlowerTile extends PictureTile{
	
	private	static	ImageIcon[]	flower = new ImageIcon[6];
	
	public FlowerTile(String name){
		super(name);
		setToolTipText(toString());
	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		switch(name){
			case "Chrysanthemum":
				g.drawImage(flower[0].getImage(), 15, 15,(int)(flower[0].getIconWidth()*0.75),(int)(flower[0].getIconHeight()*0.75), this);
				break;
			case "Orchid":
				g.drawImage(flower[1].getImage(), 15, 15,(int)(flower[1].getIconWidth()*0.75),(int)(flower[1].getIconHeight()*0.75), this);
				break;
			case "Plum":
				g.drawImage(flower[2].getImage(), 17, 15,(int)(flower[2].getIconWidth()*0.75),(int)(flower[2].getIconHeight()*0.75), this);
				break;
			case "Bamboo":
				g.drawImage(flower[3].getImage(), 20, 16,(int)(flower[3].getIconWidth()*0.75),(int)(flower[3].getIconHeight()*0.75), this);
				break;

		}
	}
	
	static{
		String[]	files = { "Chrysanthemum", "Orchid", "Plum", "Bamboo"};
		
		
		
		for (int i = 0; i < files.length; i++){
			URL url = FlowerTile.class.getResource(
					"resources/"  + files[i] + ".png");
			flower[i] = new ImageIcon(url);
		}
	}
	
	
//	
//	public static void main(String[] args)
//	{
//		JFrame	frame = new JFrame();
//
//		frame.setLayout(new FlowLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Flower Tiles");
//
//		frame.add(new FlowerTile("Chrysanthemum"));
//		frame.add(new FlowerTile("Orchid"));
//		frame.add(new FlowerTile("Plum"));
//		frame.add(new FlowerTile("Bamboo"));
//
//		frame.pack();
//		frame.setVisible(true);
//	}//end main
}//end flower tile class
