//import java.awt.FlowLayout;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
//import javax.swing.JFrame;



@SuppressWarnings("serial")
public class Bamboo1Tile extends PictureTile
{
	
	private	static ImageIcon bamboo1 = new ImageIcon();
	
	public Bamboo1Tile()
	{
		super("Sparrow");
		setToolTipText(toString());
	}

	
	@Override
	public String toString()
	{
		return "Bamboo 1";
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		URL url = Bamboo1Tile.class.getResource(
                "resources/Sparrow.png");
		
		bamboo1 = new ImageIcon(url);
		g.drawImage(bamboo1.getImage(), 15, 20,(int)(bamboo1.getIconWidth()*0.75),(int)(bamboo1.getIconHeight()*0.75), this);
	}
	
	
//	public static void main(String[] args)
//	{
//		JFrame	frame = new JFrame();
//
//		frame.setLayout(new FlowLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Bamboo 1 Tile");
//
//		frame.add(new Bamboo1Tile());
//
//		frame.pack();
//		frame.setVisible(true);
//	}//end main
}//end bamboo1 tile class
