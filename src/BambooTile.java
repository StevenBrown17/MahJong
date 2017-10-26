import java.awt.*;
//import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BambooTile extends RankTile{

	
	public BambooTile(int rank){
		super(rank);
		this.setSize(new Dimension(tileWidth,tileHight));
		this.rank = rank;
		setToolTipText(toString());
	}
	
	@Override
	public String toString(){
		
		switch(rank){
			case 2: return "Bamboo 2"; 
			case 3: return "Bamboo 3"; 
			case 4: return "Bamboo 4"; 
			case 5: return "Bamboo 5"; 
			case 6: return "Bamboo 6"; 
			case 7: return "Bamboo 7"; 
			case 8: return "Bamboo 8"; 
			case 9: return "Bamboo 9"; 	
			default: return "none";
		}
	}//end toString()
	
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//Graphics2D g2 = (Graphics2D)g;
		
		switch(rank){
		case 2:
			new Bamboo(33,10,Color.BLUE).larger(g);
			new Bamboo(33,40,Color.GREEN.darker()).larger(g);
			break;
			
		case 3:
			new Bamboo(33,10,Color.BLUE).larger(g);
			new Bamboo(50,40,Color.GREEN.darker()).larger(g);
			new Bamboo(17,40,Color.GREEN.darker()).larger(g);
			break;
			
		case 4:
			new Bamboo(17,10,Color.BLUE).larger(g);
			new Bamboo(50,10,Color.GREEN.darker()).larger(g);
			new Bamboo(50,40,Color.BLUE).larger(g);
			new Bamboo(17,40,Color.GREEN.darker()).larger(g);
			break;
			
		case 5:
			new Bamboo(17,10,Color.BLUE).larger(g);
			new Bamboo(50,10,Color.GREEN.darker()).larger(g);
			new Bamboo(33,25,Color.RED).larger(g);
			new Bamboo(50,40,Color.BLUE).larger(g);
			new Bamboo(17,40,Color.GREEN.darker()).larger(g);
			break;
			
		case 6:
			new Bamboo(17,10,Color.GREEN.darker()).larger(g);
			new Bamboo(33,10,Color.GREEN.darker()).larger(g);
			new Bamboo(50,10,Color.GREEN.darker()).larger(g);
			new Bamboo(17,40,Color.BLUE).larger(g);
			new Bamboo(33,40,Color.BLUE).larger(g);
			new Bamboo(50,40,Color.BLUE).larger(g);
			break;
			
		case 7:
			new Bamboo(33,5,Color.RED).smaller(g);
			new Bamboo(17,30,Color.GREEN.darker()).smaller(g);
			new Bamboo(33,30,Color.GREEN.darker()).smaller(g);
			new Bamboo(50,30,Color.GREEN.darker()).smaller(g);
			new Bamboo(17,55,Color.BLUE).smaller(g);
			new Bamboo(33,55,Color.BLUE).smaller(g);
			new Bamboo(50,55,Color.BLUE).smaller(g);
			break;
			
		case 8:
			
			new Bamboo(17,5,Color.GREEN.darker()).smaller(g);
			new Bamboo(50,5,Color.GREEN.darker()).smaller(g);
			new Bamboo(17,55,Color.BLUE).smaller(g);
			new Bamboo(50,55,Color.BLUE).smaller(g);
			new Bamboo(33,5,Color.GREEN.darker()).smaller(g);
			new Bamboo(23,30,Color.RED).smaller(g);
			new Bamboo(43,30,Color.RED).smaller(g);
			new Bamboo(33,55,Color.BLUE).smaller(g);
			
			break;
		
		case 9:
			new Bamboo(17,5,Color.RED).smaller(g);
			new Bamboo(33,5,Color.RED).smaller(g);
			new Bamboo(50,5,Color.RED).smaller(g);
			new Bamboo(17,30,Color.GREEN.darker()).smaller(g);
			new Bamboo(33,30,Color.GREEN.darker()).smaller(g);
			new Bamboo(50,30,Color.GREEN.darker()).smaller(g);
			new Bamboo(17,55,Color.BLUE).smaller(g);
			new Bamboo(33,55,Color.BLUE).smaller(g);
			new Bamboo(50,55,Color.BLUE).smaller(g);
			break;

		//g.fillOval(30, 30, 30, 30);
		
		}
		
	}
	
	
	

	
	public class Bamboo {

		private int x,y;
		private Color color;
		
		
		public Bamboo(int x, int y, Color color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}

		public void larger(Graphics g){
			
			Graphics2D g2 = (Graphics2D)g;
 
			g2.setColor(this.color); 
			g2.fillArc(x, y, 10, 10, 0, 180);
			g2.fillRect(x+4, y+5, 3, 20);
			g2.fillArc(x, y+20, 10, 10, 0, 180);
			g2.setColor(Color.WHITE); 
			g2.drawLine(x+5, y+7, x+5, y+20);
			g2.setColor(this.color); 
			g2.fillArc(x, y+10, 10, 10, 0, 180);
			
				
		}
		
public void smaller(Graphics g){
 
			Graphics2D g2 = (Graphics2D)g;
			 
			g2.setColor(this.color); 
			g2.fillArc(x, y, 8, 8, 0, 180);
			g2.fillRect(x+3, y+4, 3, 16);
			g2.fillArc(x, y+16, 8, 8, 0, 180);
			g2.setColor(Color.WHITE); 
			g2.drawLine(x+4, y+3, x+4, y+15);
			g2.setColor(this.color); 
			g2.fillArc(x, y+8, 8, 8, 0, 180);
			
				
		}
		

	}//end bamboo class

	
	
	
	
	public class RotatedBamboo extends Bamboo{

		public RotatedBamboo(int x, int y, Color color) {
			super(x, y, color);
			// TODO Auto-generated constructor stub
		}

	}//end rotated bamboo

	
	
	
////main to test functionality
//	
//	public static void main(String[] args)
//	{
//		JFrame	frame = new JFrame();
//
//		frame.setLayout(new FlowLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Bamboo Tiles");
//
//		frame.add(new BambooTile(2));
//		frame.add(new BambooTile(3));
//		frame.add(new BambooTile(4));
//		frame.add(new BambooTile(5));
//		frame.add(new BambooTile(6));
//		frame.add(new BambooTile(7));
//		frame.add(new BambooTile(8));
//		frame.add(new BambooTile(9));
//
//		frame.pack();
//		frame.setVisible(true);
//	}//end main
	
}//end bamboo tile class
