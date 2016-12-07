import java.awt.*;
//import javax.swing.*;


@SuppressWarnings("serial")
public class CharacterTile extends MahJong.MahJongBoard.MahJongModel.Tile{
	
	protected char symbol;
	//private static Dimension WINDOW_SIZE = new Dimension(400,400);
	
	public CharacterTile(char symbol){
		this.setPreferredSize(new Dimension(tileWidth,tileHight));
		this.symbol = symbol;
		setToolTipText(toString());
	}//end constructor
	

		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			
			
			Font font = g.getFont();
			font = font.deriveFont(30F);
			
			g.setFont(font);


			
			switch(symbol){
			case '1': 	g.drawString("\u4E00", 25, 35);
						g.setColor(Color.BLUE);
					  	g.drawString("\u842C", 25, 70);
					  	font = font.deriveFont(13F); g.setFont(font);
					  	g.drawString(symbol+"", 55, 15);break;
					  	
			case '2':	g.drawString("\u4E8C", 25, 35);
						g.setColor(Color.BLUE);
						g.drawString("\u842C", 25, 70);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);break;
						
			case '3': 	g.drawString("\u4E09", 25, 35);
						g.setColor(Color.BLUE);
						g.drawString("\u842C", 25, 70);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);break;
						
			case '4': 	g.drawString("\u56DB", 25, 35);
						g.setColor(Color.BLUE);
						g.drawString("\u842C", 25, 70);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);break;
						
			case '5': 	g.drawString("\u4E94", 25, 35);
						g.setColor(Color.BLUE);
						g.drawString("\u842C", 25, 70);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);break;
						
			case '6': 	g.drawString("\u516D", 25, 35);
						g.setColor(Color.BLUE);
						g.drawString("\u842C", 25, 70);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);break;
						
			case '7': 	g.drawString("\u4E03", 25, 35);
						g.setColor(Color.BLUE);
						g.drawString("\u842C", 25, 70);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);break;
						
			case '8': 	g.drawString("\u516B", 25, 35);
						g.setColor(Color.BLUE);
						g.drawString("\u842C", 25, 70);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);break;
						
			case '9': 	g.drawString("\u4E5D", 25, 35);
						g.setColor(Color.BLUE);
						g.drawString("\u842C", 25, 70);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);break;
						
			case 'N': 	font = font.deriveFont(45F);g.setFont(font);
						g.drawString("\u5317", 20, 60);
						g.setColor(Color.BLUE);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);
						break;
						
			case 'E':	font = font.deriveFont(45F);g.setFont(font);
						g.drawString("\u6771", 20, 60);
						g.setColor(Color.BLUE);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);
						break;
						
			case 'W': 	font = font.deriveFont(45F);g.setFont(font);
						g.drawString("\u897F", 20, 60);
						g.setColor(Color.BLUE);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);
						break;
						
			case 'S': 	font = font.deriveFont(45F);g.setFont(font);
						g.drawString("\u5357", 20, 60);
						g.setColor(Color.BLUE);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);
						break;
						
			case 'C': 	font = font.deriveFont(45F);g.setFont(font);
						g.setColor(Color.RED);
						g.drawString("\u4E2D", 20, 60);
						g.setColor(Color.BLUE);
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);
						break;
						
			case 'F': 	font = font.deriveFont(45F);g.setFont(font);
						g.setColor(Color.GREEN);
						g.drawString("\u767C", 20, 60);
						g.setColor(Color.BLUE);						
						font = font.deriveFont(13F); g.setFont(font);
						g.drawString(symbol+"", 55, 15);
						break;
			}
	
		
	}
	
	

		public MahJong.MahJongBoard.MahJongModel.Tile copy(MahJong.MahJongBoard.MahJongModel.Tile t){
			 MahJong.MahJongBoard.MahJongModel.Tile tile = new  MahJong.MahJongBoard.MahJongModel.Tile();
			tile = super.copy(t);
			return tile;
		}
	
	
	@Override
	public boolean matches(MahJong.MahJongBoard.MahJongModel.Tile other){
		
		 if(super.matches(other)){

			 CharacterTile ct = (CharacterTile) other;
			 
			 if(ct.symbol == symbol)
				 return true;
		 }
		 
		 
		 return false;
	        
	}
	
	@Override
	public String toString(){
		
		switch(symbol){
		case '1': return "Character 1";
		case '2': return "Character 2";
		case '3': return "Character 3";
		case '4': return "Character 4";
		case '5': return "Character 5";
		case '6': return "Character 6";
		case '7': return "Character 7";
		case '8': return "Character 8";
		case '9': return "Character 9";
		case 'N': return "North Wind";
		case 'E': return "East Wind";
		case 'W': return "West Wind";
		case 'S': return "South Wind";
		case 'C': return "Red Dragon";
		case 'F': return "Green Dragon";
		default: return "";	
		}
	}//end toString
	
//	public static void main(String[] args)
//	{
//		JFrame		frame = new JFrame();
//		JPanel		tiles = new JPanel();
//		JScrollPane	scroller = new JScrollPane(tiles);
//
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Character Tiles");
//		frame.add(scroller);
//
//		// Try something like this if your tiles don't fit on the screen.
//		// Replace "tile width" and "tile height" with your values.
//		//scroller.setPreferredSize(new Dimension(8 * tile width, 40 + tile height));
//
//		tiles.add(new CharacterTile('1'));
//		tiles.add(new CharacterTile('2'));
//		tiles.add(new CharacterTile('3'));
//		tiles.add(new CharacterTile('4'));
//		tiles.add(new CharacterTile('5'));
//		tiles.add(new CharacterTile('6'));
//		tiles.add(new CharacterTile('7'));
//		tiles.add(new CharacterTile('8'));
//		tiles.add(new CharacterTile('9'));
//		tiles.add(new CharacterTile('N'));
//		tiles.add(new CharacterTile('E'));
//		tiles.add(new CharacterTile('W'));
//		tiles.add(new CharacterTile('S'));
//		tiles.add(new CharacterTile('C'));
//		tiles.add(new CharacterTile('F'));
//
//		frame.pack();
//		frame.setVisible(true);
//	}//end main

}//end charactertile class
