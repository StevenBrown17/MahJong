import java.awt.*;
//import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CircleTile extends RankTile{
	
	
	public CircleTile(int rank){
		super(rank);
		this.setSize(new Dimension(tileWidth,tileHight));
		this.rank = rank;
		setToolTipText(toString());
	}
	
	@Override
	public String toString(){
		
		switch(rank){	
			case 1: return "Circle 1"; 
			case 2: return "Circle 2"; 
			case 3: return "Circle 3"; 
			case 4: return "Circle 4"; 
			case 5: return "Circle 5"; 
			case 6: return "Circle 6"; 
			case 7: return "Circle 7"; 
			case 8: return "Circle 8"; 
			case 9: return "Circle 9"; 	
			default: return "";	
		}		
	}//end toString()
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		
		switch(rank){
		
		case 1:
					
			g.setColor(Color.GREEN.darker());
			g.fillOval(15, 15, 50, 50);
			new Circle(30, 30, 20, Color.RED).paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
	        g2.setStroke(dashed);
	        g2.setColor(Color.green);
			g2.drawOval(20, 20, 40, 40);
			g2.dispose();
			
			
			
			break;
			
		case 2:
			new Circle(25, 13, 25, Color.GREEN.darker()).paintComponent(g);
			new Circle(25, 43, 25, Color.RED).paintComponent(g);break;
			
		case 3:
			new Circle(14, 4, 20, Color.BLUE).paintComponent(g);
			new Circle(30, 30, 20, Color.RED).paintComponent(g);
			new Circle(45, 55, 20, Color.GREEN.darker()).paintComponent(g);
			break;
			
			
		case 4:
			new Circle(15, 13, 25, Color.BLUE).paintComponent(g);
			new Circle(15, 43, 25, Color.GREEN.darker()).paintComponent(g);
			new Circle(40, 13, 25, Color.GREEN.darker()).paintComponent(g);
			new Circle(40, 43, 25, Color.BLUE).paintComponent(g)
			;break;
			
		case 5:
			new Circle(13, 5, 20, Color.BLUE).paintComponent(g);
			new Circle(13, 54, 20, Color.GREEN.darker()).paintComponent(g);
			new Circle(30, 30, 20, Color.RED).paintComponent(g);
			new Circle(47, 5, 20, Color.GREEN.darker()).paintComponent(g);
			new Circle(47, 54, 20, Color.BLUE).paintComponent(g);
			break;
		
		case 6:
			new Circle(17, 5, 20, Color.green.darker()).paintComponent(g);
			new Circle(43, 5, 20, Color.green.darker()).paintComponent(g);
			new Circle(17, 30, 20, Color.RED).paintComponent(g);
			new Circle(43, 30, 20, Color.RED).paintComponent(g);
			new Circle(43, 54, 20, Color.RED).paintComponent(g);
			new Circle(17, 54, 20, Color.RED).paintComponent(g);
			break;
		
		case 7:
			new Circle(17, 5, 15, Color.green.darker()).paintComponent(g);
			new Circle(30, 15, 15, Color.green.darker()).paintComponent(g);
			new Circle(43, 25, 15, Color.green.darker()).paintComponent(g);
			new Circle(17, 42, 15, Color.RED).paintComponent(g);
			new Circle(43, 42, 15, Color.RED).paintComponent(g);
			new Circle(17, 60, 15, Color.RED).paintComponent(g);
			new Circle(43, 60, 15, Color.RED).paintComponent(g);
			break;
			
		case 8:
			new Circle(22, 10, 15, Color.BLUE).paintComponent(g);
			new Circle(42, 10, 15, Color.BLUE).paintComponent(g);
			new Circle(22, 25, 15, Color.BLUE).paintComponent(g);
			new Circle(42, 25, 15, Color.BLUE).paintComponent(g);
			new Circle(22, 40, 15, Color.BLUE).paintComponent(g);
			new Circle(42, 40, 15, Color.BLUE).paintComponent(g);
			new Circle(22, 55, 15, Color.BLUE).paintComponent(g);
			new Circle(42, 55, 15, Color.BLUE).paintComponent(g);
			
			break;
			
		case 9:
			new Circle(11, 5, 18, Color.BLUE).paintComponent(g);
			new Circle(30, 5, 18, Color.BLUE).paintComponent(g);
			new Circle(49, 5, 18, Color.BLUE).paintComponent(g);
			new Circle(11, 30, 18, Color.RED).paintComponent(g);
			new Circle(30, 30, 18, Color.RED).paintComponent(g);
			new Circle(49, 30, 18, Color.RED).paintComponent(g);
			new Circle(11, 54, 18, Color.GREEN.darker()).paintComponent(g);
			new Circle(30, 54, 18, Color.GREEN.darker()).paintComponent(g);
			new Circle(49, 54, 18, Color.GREEN.darker()).paintComponent(g);
			break;

		}
		
	}
	
	
	
	
	
	public class Circle{

		private int x,y, rad;
		private Color color;
		
		public Circle(int x, int y, int rad, Color color) {
			super();
			this.x = x;
			this.y = y;
			this.rad = rad;
			this.color = color;
		}


		public void paintComponent(Graphics g){
			//paintComponent(g);
			
			Pancake pancake = new Pancake(x,y,rad,Color.WHITE);
			
			Graphics2D g2 = (Graphics2D)g;
			//setBackground(this.color);
			
					g2.setPaint(this.color);
					g2.fillOval(x, y, rad, rad);
					g2.setColor(Color.WHITE);
					pancake.paintComponent(g);
		
		}

	}//end Circle class
	
	
	
	public class Pancake extends Circle{//add the X on the circles

		public Pancake(int x, int y, int rad, Color color) {
			super(x, y, rad, color);
			// TODO Auto-generated constructor stub
		}
		
		public void paintComponent(Graphics g){
		
			g.drawLine(super.x+(super.rad/5), super.y+(super.rad/5), super.x+super.rad-(super.rad/5), super.y+super.rad-(super.rad/5));
			g.drawLine(super.x+(super.rad/5), super.y+super.rad-(super.rad/5), super.x+super.rad-(super.rad/5), super.y+(super.rad/5));	
		}//end paint component

	}//end circle class
	
	
	
	
//	public static void main(String[] args)
//	{
//		JFrame	frame = new JFrame();
//
//		frame.setLayout(new FlowLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Circle Tiles");
//
//		frame.add(new CircleTile(1));
//		frame.add(new CircleTile(2));
//		frame.add(new CircleTile(3));
//		frame.add(new CircleTile(4));
//		frame.add(new CircleTile(5));
//		frame.add(new CircleTile(6));
//		frame.add(new CircleTile(7));
//		frame.add(new CircleTile(8));
//		frame.add(new CircleTile(9));
//
//		frame.pack();
//		frame.setVisible(true);
//	}//end main
	
}//end circle tile class
