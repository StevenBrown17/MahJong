import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

import com.sun.javafx.image.impl.ByteIndexed.Getter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;






@SuppressWarnings("serial")
public class MahJong extends JFrame implements MouseListener{

	
	public static int gameNum, gameScore=0;
	public boolean tournamentMode=false;
	public boolean soundBool=true;
	public static boolean beginning=true;
	public static MouseListener mouseArray[];
	public MahJongBoard.MahJongModel.Tile t1= new MahJongBoard.MahJongModel.Tile(),t2= new MahJongBoard.MahJongModel.Tile(), usedTile= new MahJongBoard.MahJongModel.Tile();
	public static ButtonGroup	group = new ButtonGroup();
	//ScrollPane	scrollPane = new ScrollPane();
	
	
	private	Border			selected = BorderFactory.createLineBorder(Color.RED,5);
	private	JPanel			centerPanel = new JPanel();

	
	public MahJong() {
		
		add(centerPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300,965);
		Random rand = new Random();
		gameNum = rand.nextInt(501);
		setTitle("MahJong Solitare - Game Number: "+ gameNum);
		
		ScrollPane start = new ScrollPane();	
		

		
		JMenuBar menu = new JMenuBar();
		
		///////////////////////////////////////////////////////////////////////
		JMenu		tournament = new JMenu("Tournament Mode");
		tournament.setMnemonic('T');
		
		StopWatch stopWatch = new StopWatch();
		JRadioButtonMenuItem	on =
			new JRadioButtonMenuItem("On");
		group.add(on);
		tournament.add(on);
		on.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		on.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{ 	
						Random rand = new Random();
						gameNum = rand.nextInt(501);
						setTitle("MahJong Solitare - Game Number: "+ gameNum);
						getContentPane().removeAll();
						newGame();
						tournamentMode=true; 
						add(start, BorderLayout.SOUTH);
						stopWatch.resetTime();
						add(stopWatch, BorderLayout.EAST);
						StopWatch.score.setText("Game Score: 0");
						stopWatch.startTime();
						add(new MahJongBoard());
						revalidate();
						repaint();
					}
				});

		JRadioButtonMenuItem	off =
			new JRadioButtonMenuItem("Off", true);
		group.add(off);
		tournament.add(off);
		off.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		off.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{ tournamentMode=false; 
						Random rand = new Random();
						gameNum = rand.nextInt(501);
						setTitle("MahJong Solitare - Game Number: "+ gameNum);
						getContentPane().removeAll();
						newGame();
						add(start, BorderLayout.SOUTH);
						//add(stopWatch, BorderLayout.EAST);
						add(new MahJongBoard());
						revalidate();
						repaint();
					}
				});
		
		
		///////////////////////////////////////////////////////////////////////Game menu
		JMenu game = new JMenu("Game");
		game.setMnemonic('G');
		menu.add(game);
		JMenuItem exit = new JMenuItem("Exit", 'E');
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		game.add(exit);
		
		JMenuItem newGame = new JMenuItem("New Game", 'N');
		game.add(newGame);
		newGame.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Random rand = new Random();
				gameNum = rand.nextInt(501);
				setTitle("MahJong Solitare - Game Number: "+ gameNum);
				getContentPane().removeAll();
				newGame();
				off.setSelected(true);
				add(start, BorderLayout.SOUTH);
				//add(stopWatch, BorderLayout.EAST);
				add(new MahJongBoard());
				revalidate();
				repaint();
			}
		});
		game.add(newGame);
		
		JMenuItem restart = new JMenuItem("Restart",'R');
		restart.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setTitle("MahJong Solitare - Game Number: "+ gameNum);
				getContentPane().removeAll();
				newGame();
				off.setSelected(true);
				add(start, BorderLayout.SOUTH);
				//add(stopWatch, BorderLayout.EAST);
				add(new MahJongBoard());
				revalidate();
				repaint();	
			}
		});
		game.add(restart);
		
		JMenuItem gameNumber = new JMenuItem("Game Number",'G');
		gameNumber.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				gameNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter game number: ", "Game Number",JOptionPane.QUESTION_MESSAGE));
				setTitle("MahJong Solitare - Game Number: "+ gameNum);
				getContentPane().removeAll();
				newGame();
				off.setSelected(true);
				add(start, BorderLayout.SOUTH);
				//add(stopWatch, BorderLayout.EAST);
				add(new MahJongBoard());
				revalidate();
				repaint();
				}catch(NumberFormatException ne){JOptionPane.showMessageDialog(null, "Please enter a number");}
				
			}
		});
		game.add(gameNumber);
	
		///////////////////////////////////////////////////Move Menu
		JMenu move = new JMenu("Move");
		move.setMnemonic('M');
		menu.add(move);
		
		JMenuItem undo = new JMenuItem("Undo", 'U');
		move.add(undo);	
		undo.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
		undo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{	
					if(!tournamentMode)
						undoMove();
					else
						tournamentAlert();
				}catch(ArrayIndexOutOfBoundsException ex){System.out.println("no moves to undo");}
			}
		});
		
		move.add(undo);
		
		JMenuItem redo = new JMenuItem("Redo", 'Y');
		move.add(redo);	
		redo.setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
		redo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{	
					if(!tournamentMode)
						redoMove();
					else
						tournamentAlert();
				}catch(ArrayIndexOutOfBoundsException ex){System.out.println("no moves to redo");}
			}
		});
		
		move.add(redo);
		//////////////////////////////////////////////////////SOund Menu
		ButtonGroup	soundGroup = new ButtonGroup();
		JMenu		sound = new JMenu("Sound");
		sound.setMnemonic('S');
		menu.add(sound);

		JRadioButtonMenuItem	soundOn =
			new JRadioButtonMenuItem("On",true);
		soundGroup.add(soundOn);
		sound.add(soundOn);
		//soundOn.setAccelerator(KeyStroke.getKeyStroke("ctrl "));
		soundOn.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e)
					{ 	soundBool=true; 

					}
				});

		JRadioButtonMenuItem	soundOff =
			new JRadioButtonMenuItem("Off");
		soundGroup.add(soundOff);
		sound.add(soundOff);
		soundOff.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		soundOff.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent e){ 
					soundBool=false; 
					}
				});
		
		
		//////////////////////////////////////////////////////////////Tournament
		JMenu help = new JMenu("Help");
		help.setMnemonic('H');
		menu.add(help);
		
		JMenuItem operations = new JMenuItem("Operations", 'O');
		help.add(operations);	
		operations.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		operations.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				operations();
				
			}
		});
		help.add(operations);
		
		JMenuItem rules = new JMenuItem("Game Rules", 'G');
		help.add(rules);	
								  
				
					 
		rules.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		rules.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String link = "https://www.kristanix.com/mahjong/mahjong_rules.php";
				openWebpage(link);
			}
		});
		help.add(rules);
				
									 
							
										
											   
							
							  
				   
				
	  
	   

							
										 
				 
					  
													   
											
												
							 
								 
								  
															
								   
				
									 
										  
							  
				   
				
	  
	   
																			
		
		menu.add(help);
////////////////////////////////////////////////////////////////////////////
		menu.add(tournament);
		setJMenuBar(menu);
		add(start, BorderLayout.SOUTH);
		//add(stopWatch, BorderLayout.EAST);
		add(new MahJongBoard());
		beginning = false;
		
			

  
			
	
		for(int i=0;i<MahJongBoard.obList.size();i++){
			MahJongBoard.obList.get(i).addMouseListener(this);
		}
		
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(screenSize.getWidth()-getWidth())/2, (int)(screenSize.getHeight() - getHeight())/2);
		

		
		setVisible(true);
		
	}//end constructor
	
	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void operations(){
		JOptionPane.showMessageDialog(this, "UNDO - ctrl \'Z\' \n REDO - ctrl \'Y\'\n NEW GAME - ctrl \'N\'\nRESTART GAME - ctrl \'R\'\nSOUND OFF - ctrl \'S\'\nOPERATIONS - ctrl \'O\'\nGAME RULES - ctrl \'G\'\nTOURNAMENT MODE ON - ctrl \'T\'\nTOURNAMENT MODE OFF - ctrl \'F\'");
	}
	
	
	private void tournamentAlert(){
		StopWatch.stopTime();
		JOptionPane.showMessageDialog(this, "Cannot UNDO in tournament mode!");
		StopWatch.startTime();
	}
	
	private void winMessage(){
		if(!tournamentMode)
			JOptionPane.showMessageDialog(this, "YOU WIN! Congrats!");
		else
			JOptionPane.showMessageDialog(this, "You cleared the board in "+StopWatch.timeLbl.getText()+"!! You Win!");
		
		newGame();
	}
	
	public void undoMove(){ 	
		
		int size = MahJongBoard.removedList.size(), index;
		
		index = MahJongBoard.removedList.get(size-1).getListIndex();
		MahJongBoard.obList.get(index).setVisible(true);
		MahJongBoard.redo.add(MahJongBoard.obList.get(index));

		ScrollPane.discard[1].remove(MahJongBoard.removed.get(index));
		//ScrollPane.discard[0].add(MahJongBoard.removed.get(index),0);
		//MahJongBoard.removed.get(index).setVisible(false);
		MahJongBoard.removedList.remove(size-1);
		

		size = MahJongBoard.removedList.size();
		index = MahJongBoard.removedList.get(size-1).getListIndex();
		MahJongBoard.obList.get(index).setVisible(true);
		MahJongBoard.redo.add(MahJongBoard.obList.get(index));
		ScrollPane.discard[0].remove(MahJongBoard.removed.get(index));
		//ScrollPane.discard[1].add(MahJongBoard.removed.get(index),0);
		//MahJongBoard.removed.get(index).setVisible(false);
		MahJongBoard.removedList.remove(size-1);

		
		revalidate();
		repaint();

	}
	
	public void redoMove(){
		
		int size = MahJongBoard.redo.size(), index;
		index = MahJongBoard.redo.get(size-1).getListIndex();
		MahJongBoard.obList.get(index).setVisible(false);
		MahJongBoard.removedList.add(MahJongBoard.obList.get(index));
		MahJongBoard.redo.remove(size-1);
		ScrollPane.discard[0].add(MahJongBoard.removed.get(index));
		
		
		size = MahJongBoard.redo.size();
		index = MahJongBoard.redo.get(size-1).getListIndex();
		MahJongBoard.obList.get(index).setVisible(false);
		MahJongBoard.removedList.add(MahJongBoard.obList.get(index));
		MahJongBoard.redo.remove(size-1);
		ScrollPane.discard[1].add(MahJongBoard.removed.get(index));
 
																																	 
		
		revalidate();
		repaint();
		
	}
	
	public void newGame(){
		
		ScrollPane.discard[0].removeAll();
		ScrollPane.discard[0].repaint();
		ScrollPane.discard[1].removeAll();
		ScrollPane.discard[1].repaint();
		
		MahJongBoard.removed.clear();
		for(int i=0;i<MahJongBoard.obList.size();i++)MahJongBoard.obList.get(i).setVisible(true);
		for(int i=0;i<MahJongBoard.obList.size();i++)MahJongBoard.obList.get(i).tileVisible=true;
		new MahJongBoard();
		MahJongBoard.removedList.clear();
		gameScore=0;
		tournamentMode=false;
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e){
		
		t1.setBorder(null);
		
		MahJongBoard.MahJongModel.Tile t = (MahJongBoard.MahJongModel.Tile)e.getSource();		

																	
		
		t1=t;
		t1.setBorder(selected);
		
		//usedTile =t;
			
		if(t1.matches(t2) && t1.listIndex != t2.listIndex){
			
			boolean condition1 = checkConditions(t1);
			boolean condition2 = checkConditions(t2);

			if(condition1 && condition2){
				gameScore++;
				gameScore++;
				StopWatch.score.setText("Game Score: "+gameScore);
				t1.setBorder(null);
	
				t1.setTileVisible(false);//local tile var
				t2.setTileVisible(false);//local tile var
				t1.setVisible(false);
				t2.setVisible(false);
						  
														  
				
				MahJongBoard.removedList.add(t1);
				MahJongBoard.removedList.add(t2);

										
									  
										
									  
				if(!tournamentMode){
					ScrollPane.discard[0].add(MahJongBoard.removed.get(t1.getListIndex()));	
					ScrollPane.discard[1].add(MahJongBoard.removed.get(t2.getListIndex()));
					//add(t1);
				}
				
				revalidate();
				repaint();	
				t2 = new MahJongBoard.MahJongModel.Tile();

				if(soundBool)
					PlayClip.playClip();
				
				if(MahJongBoard.removedList.size()==144){
					
					StopWatch.stopTime();
					winMessage();
				}
				
			}
		}
		else
			t2=t;
		
	}//end mouse pressed
	
	//true means tile can be removed
	public boolean checkConditions(MahJongBoard.MahJongModel.Tile t){
		int index = t.listIndex;
		
		if(index == 141 | index == 139 | index == 140 | index == 142 && MahJongBoard.obList.get(143).isTileVisible() == true){
			return false;
		}
		
		if(index == 41 | index == 53  && MahJongBoard.obList.get(54).isTileVisible() == true){
			return false;
		}
		
		if(index == 54 && MahJongBoard.obList.get(55).isTileVisible() == true){
			return false;
		}
		
		if(index == 30 | index == 42  && MahJongBoard.obList.get(56).isTileVisible() == true){
			return false;
		}

		try{
			MahJongBoard.MahJongModel.Tile temp1 =MahJongBoard.obList.get(index-1);
			MahJongBoard.MahJongModel.Tile temp2 =MahJongBoard.obList.get(index+1);

  
		if(temp1.isTileVisible() && temp2.isTileVisible() && temp1.row == t.row && temp2.row == t.row){
																  
				 
	
		
																				 
				  
																				 
			return false;
	
		}
  
		}catch(ArrayIndexOutOfBoundsException e){}
		catch(IndexOutOfBoundsException i){}

		
		return true;
	}//end checkConditions


	
		public static void main(String[] args){			
			
				 
   
   
   
			new MahJong();	
			
		}//end main
		
		
		
	
	public static class MahJongBoard extends JPanel{
		
		static String[] seasons={"Spring","Summer","Fall","Winter"}; //one instance
		static String[] flowers={"Chrysanthemum", "Orchid", "Plum", "Bamboo"}; //one instance
		static int[] circles={1,2,3,4,5,6,7,8,9,
					   1,2,3,4,5,6,7,8,9,
					   1,2,3,4,5,6,7,8,9,
					   1,2,3,4,5,6,7,8,9}; //four instances
		static char[] characters={'1','2','3','4','5','6','7','8','9','N','E','W','S','C','F',
							 '1','2','3','4','5','6','7','8','9','N','E','W','S','C','F',
							 '1','2','3','4','5','6','7','8','9','N','E','W','S','C','F',
							 '1','2','3','4','5','6','7','8','9','N','E','W','S','C','F'}; //four instances
		static int[] bamboo = {2,3,4,5,6,7,8,9,
						2,3,4,5,6,7,8,9,
						2,3,4,5,6,7,8,9,
						2,3,4,5,6,7,8,9};//four instances
		
		
		
		public static ObservableList<MahJongModel.Tile> obList = FXCollections.observableArrayList();
		public static final ObservableList<MahJongModel.Tile> original = FXCollections.observableArrayList();
		public static ObservableList<MahJongModel.Tile> removedList = FXCollections.observableArrayList();
		public static ObservableList<MahJongModel.Tile> removed = FXCollections.observableArrayList();
		public static ObservableList<MahJongModel.Tile> redo = FXCollections.observableArrayList();
		
		 
		
		public static void addAndShuffle(){

			
			
			if(beginning){
				for(int i=0; i<seasons.length;i++){obList.add(new SeasonTile(seasons[i]));}//adding tile objects to observable list
				for(int i=0; i<flowers.length;i++){obList.add(new FlowerTile(flowers[i]));}
				for(int i=0; i<circles.length;i++){obList.add(new CircleTile(circles[i]));}
				for(int i=0; i<characters.length;i++){obList.add(new CharacterTile(characters[i]));}
				for(int i=0; i<bamboo.length;i++){obList.add(new BambooTile(bamboo[i]));}
				for(int i=0;i<4;i++){obList.add(new Bamboo1Tile()); obList.add(new WhiteDragonTile());}
				
				for(int i=0; i< obList.size();i++){original.add(obList.get(i));}	
			}
			else{
				obList.clear();
				for(int i=0; i< original.size();i++){obList.add(original.get(i));}	
			}
			Random rand = new Random();
			
			rand.setSeed(gameNum);
									  
			Collections.shuffle(obList, rand);

			
		}
		
//		@Override
//		public Object clone() throws CloneNotSupportedException{
//			MahJongModel.Tile tile = (MahJongModel.Tile)super.clone();
//			tile.listIndex = (int)listIndex.clone();
//			
//			return tile;
//		}
		
		static public Object deepCopy(Object oldObj) throws Exception
		   {
		      ObjectOutputStream oos = null;
		      ObjectInputStream ois = null;
		      try
		      {
		         ByteArrayOutputStream bos = 
		               new ByteArrayOutputStream(); // A
		         oos = new ObjectOutputStream(bos); // B
		         // serialize and pass the object
		         oos.writeObject(oldObj);   // C
		         oos.flush();               // D
		         ByteArrayInputStream bin = 
		               new ByteArrayInputStream(bos.toByteArray()); // E
		         ois = new ObjectInputStream(bin);                  // F
		         // return the new object
		         return ois.readObject(); // G
		      }
		      catch(Exception e)
		      {
		         System.out.println("Exception in ObjectCloner = " + e);
		         throw(e);
		      }
		      finally
		      {
		         oos.close();
		         ois.close();
		      }
		   }//end deepCopy
		
		public MahJongBoard() {	

					setLayout(null);
					
					
					JPanel p = new JPanel();
					JLabel label = new JLabel("Hello");
					label.setLocation(10, 100);
					label.setVisible(true);
					p.add(label = new JLabel("Hello", JLabel.RIGHT));
					p.setLocation(10, 100);
					p.setBackground(Color.RED);
					add(p);
										
									
					addAndShuffle();
					
					
					for(int i=0; i<obList.size();i++)
						obList.get(i).setListIndex(i);
					
					//t = new SeasonTile("Spring");
					//t.setLocation(0,0);
					int defaultX=175, defaultY=35;
					int x=defaultX,y=defaultY, width = MahJongModel.Tile.tileWidth, height = MahJongModel.Tile.tileHight, count=0;
					
					////BUILD TOP DOWN
					
					/************************************************OFFSET LAYER LAYER****************************************************************/
					x=defaultX+(width*5+20);
					obList.get(143).setLocation(x+(width-10), y+height*3-30);
					

					
					obList.get(143);
					add(obList.get(143));
					/************************************************END OFFSET LAYER LAYER****************************************************************/
	
					/************************************************2x2 LAYER LAYER****************************************************************/
					
					x=defaultX+(width*5-20);
					count = 139;
					while(count<141){obList.get(count).setLocation(x+(width-10), y+height*3-60); x=x+(width-10); obList.get(count).setRow(19); count++;}
					x=defaultX+(width*5-20);
					while(count<143){obList.get(count).setLocation(x+(width-10), y+height*4-70); x=x+(width-10); obList.get(count).setRow(20); count++;}

					for(int i=141;i<143;i++)add(obList.get(i));//ADDING 2x2 ROW2
					for(int i=139;i<141;i++)add(obList.get(i));//ADDING 2x2 ROW1
		
					/************************************************END 2x2 LAYER LAYER****************************************************************/
					
					
					
					/************************************************4X4 LAYER LAYER****************************************************************/
					
					x=defaultX+(width*4-20);
					count = 123;
					while(count<127){obList.get(count).setLocation(x+(width-10), y+height*2-40); x=x+(width-10); obList.get(count).setRow(15); count++;}
					x=defaultX+(width*4-20);
					while(count<131){obList.get(count).setLocation(x+(width-10), y+height*3-50); x=x+(width-10); obList.get(count).setRow(16); count++;}
					x=defaultX+(width*4-20);
					while(count<135){obList.get(count).setLocation(x+(width-10), y+height*4-60); x=x+(width-10); obList.get(count).setRow(17); count++;}
					x=defaultX+(width*4-20);
					while(count<139){obList.get(count).setLocation(x+(width-10), y+height*5-70); x=x+(width-10); obList.get(count).setRow(18); count++;}
					
					for(int i=135;i<139;i++)add(obList.get(i));//ADDING 4x4 ROW3
					for(int i=131;i<135;i++)add(obList.get(i));//ADDING 4x4 ROW3
					for(int i=127;i<131;i++)add(obList.get(i));//ADDING 4x4 ROW2
					for(int i=123;i<127;i++)add(obList.get(i));//ADDING 4x4 ROW1
		
					/************************************************END 4X4 LAYER LAYER****************************************************************/
					
					
					/************************************************6X6 LAYER LAYER****************************************************************/
						
					x=defaultX+(width*3-20);
					count = 87;
					while(count<93){obList.get(count).setLocation(x+(width-10), y+height-20); x=x+(width-10);obList.get(count).setRow(9); count++;}
					x=defaultX+(width*3-20);
					while(count<99){obList.get(count).setLocation(x+(width-10), y+height*2-30); x=x+(width-10); obList.get(count).setRow(10); count++;}
					x=defaultX+(width*3-20);
					while(count<105){obList.get(count).setLocation(x+(width-10), y+height*3-40); x=x+(width-10); obList.get(count).setRow(11); count++;}
					x=defaultX+(width*3-20);
					while(count<111){obList.get(count).setLocation(x+(width-10), y+height*4-50); x=x+(width-10); obList.get(count).setRow(12); count++;}
					x=defaultX+(width*3-20);
					while(count<117){obList.get(count).setLocation(x+(width-10), y+height*5-60); x=x+(width-10); obList.get(count).setRow(13); count++;}
					x=defaultX+(width*3-20);
					while(count<123){obList.get(count).setLocation(x+(width-10), y+height*6-70); x=x+(width-10); obList.get(count).setRow(14); count++;}
					
					for(int i=117;i<123;i++)add(obList.get(i));//ADDING 6x6 ROW6
					for(int i=111;i<117;i++)add(obList.get(i));//ADDING 6x6 ROW5
					for(int i=105;i<111;i++)add(obList.get(i));//ADDING 6x6 ROW4
					for(int i=99;i<105;i++)add(obList.get(i));//ADDING 6x6 ROW3
					for(int i=93;i<99;i++)add(obList.get(i));//ADDING 6x6 ROW2
					for(int i=87;i<93;i++)add(obList.get(i));//ADDING 6x6 ROW1
					
					
					/************************************************END 6X6 LAYER LAYER****************************************************************/
					
					
					/************************************************BOTTOM LAYER****************************************************************/
					//for(int i=count;i<12;i++){obList.get(i).setLocation(x+(width-10), y); x=x+(width-10); count++;}
					count=0;
					x=defaultX;
					while(count<12){obList.get(count).setLocation(x+(width-10), y); x=x+(width-10); obList.get(count).setRow(1); count++;} //BOTTOM ROW 1				
					x=defaultX+(width*2-20);
					while(count<20){obList.get(count).setLocation(x+(width-10), y+height-10); x=x+(width-10); obList.get(count).setRow(2); count++;}//BOTTOM ROW 2				
					x=defaultX+(width-10);
					while(count<30){obList.get(count).setLocation(x+(width-10), y+(height*2)-20); x=x+(width-10); obList.get(count).setRow(3); count++;}//BOTTOM ROW 3
					x=defaultX;
					while(count<42){obList.get(count).setLocation(x+(width-10), y+(height*3)-30); x=x+(width-10);obList.get(count).setRow(4); count++;}//BOTTOM ROW 4
					x=defaultX;
					while(count<54){obList.get(count).setLocation(x+(width-10), y+(height*4)-40); x=x+(width-10); obList.get(count).setRow(5); count++;}//BOTTOM ROW 5
					obList.get(count).setLocation(x+width-10, y+(height*3)+5); x=x+(width-10);obList.get(count).setRow(99);count++;//RIGHT 2 OFFSET
					obList.get(count).setLocation(x+width-10, y+(height*3)+5); x=x+(width-10);obList.get(count).setRow(99); count++;//LEFT OFFSET		
					x=defaultX;
					obList.get(count).setLocation(x, y+(height*3)+5); x=x+(width-10); obList.get(count).setRow(98);  count++;
					x=defaultX+(width-10);
					while(count<67){obList.get(count).setLocation(x+(width-10), y+(height*5)-50); obList.get(count).setRow(6); x=x+(width-10); count++;}//BOTTOM ROW 6
					x=defaultX+(width*2-20);
					while(count<75){obList.get(count).setLocation(x+(width-10), y+height*6-60); x=x+(width-10);obList.get(count).setRow(7); count++;}//BOTTOM ROW 7
					x=defaultX;
					while(count<87){obList.get(count).setLocation(x+(width-10), y+height*7-70); x=x+(width-10); obList.get(count).setRow(8); count++;} //BOTTOM ROW 8

					
					
					
					for(int i=75;i<87;i++)add(obList.get(i));//ADDING BOTTOM ROW					
					for(int i=67; i<75;i++)add(obList.get(i));					
					for(int i=56; i<67;i++)add(obList.get(i));					
					add(obList.get(56));//ADDING RIGHT OFFSET					
					for(int i=42;i<54;i++)add(obList.get(i));//ADDING BOTTOM ROW5
					for(int i=30;i<42;i++)add(obList.get(i));//ADDING BOTTOM ROW4
					add(obList.get(54));//ADDING LEFT OFFEST
					add(obList.get(55));//ADDING LEFT OFFSET			
					for(int i=20;i<30;i++)add(obList.get(i));//ADDING BOTTOM ROW3			
					for(int i=12;i<20;i++)add(obList.get(i));//ADDING BOTTOM ROW2	
					for(int i=0;i<12;i++)add(obList.get(i));//ADDING BOTTOM ROW1
			/************************************************END BOTTOM LAYER****************************************************************/

					
					

					
					
					for(int i=0; i<obList.size();i++){
						MahJongModel.Tile tile = new MahJongModel.Tile();
						
						try {
							tile = (MahJongModel.Tile)deepCopy(obList.get(i));
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("deep copy error");
						}
						
						removed.add(tile);
					}//end for
					
					
			
			setVisible(true);
		}//end Board constructor
		

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			ImageIcon image;
			URL url = MahJongBoard.class.getResource(
                    "resources/dragon_bg.png");
			image = new ImageIcon(url);
			image = new ImageIcon(image.getImage().getScaledInstance(1100, 700, Image.SCALE_SMOOTH));
			
			image.paintIcon(this, g, 50, 0);
		}//end paintComponent
		
		
		
		

			public static class MahJongModel {
		
			public MahJongModel() {
				
			}
			
			
			public static class Tile extends JPanel{
				
				int xLocation, yLocation, listIndex, row;
				public int getListIndex() {
					return listIndex;
				}
				public void setListIndex(int listIndex) {
					this.listIndex = listIndex;
				}
				
				public void setRow(int row){
					this.row = row;
				}
				public int getRow(){
					return this.row;
				}

				boolean tileVisible=true;


				public static int[] left1x = 	{5,10,10,5};
				public static int[] left1y = 	{10,0,80,85};
				public static int[] left2x = 	{0,5,5,0};
				public static int[] left2y = 	{20,10,85,90};
				public static int[] bot1x = 	{5,10,70,60};
				public static int[] bot1y = 	{85,80,80,85};
				public static int[] bot2x = 	{0,5,60,50};
				public static int[] bot2y = 	{90,85,85,90};
				
				public static int[] topx = {10};
				public static int[] topy = {10};
				public static int[] rightx={60,70,70,60};
				public static int[] righty={0,0,50,60};
				
				
				public static Polygon LEFT1 = 	new Polygon(left1x,left1y,4);
				public static Polygon LEFT1OUT = 	new Polygon(left1x,left1y,4);
				public static Polygon LEFT2 = 	new Polygon(left2x,left2y,4);
				public static Polygon LEFT2OUT = 	new Polygon(left2x,left2y,4);
				
				public static Polygon BOT1 = 		new Polygon(bot1x,bot1y,4);
				public static Polygon BOT1OUT = 	new Polygon(bot1x,bot1y,4);
				public static Polygon BOT2 = 		new Polygon(bot2x,bot2y,4);
				public static Polygon BOT2OUT = 	new Polygon(bot2x,bot2y,4);
				
				public static Polygon RIGHTSHADOW=	new Polygon(rightx,righty,4);
				
				
				public static Rectangle FACE = 		new Rectangle(10, 0, 60, 80);
				
				public static GradientPaint GRAD1 =	 	new GradientPaint(100, 20,  Color.RED, 0, 20, Color.WHITE);
				public static GradientPaint GRAD2 = 	new GradientPaint(110, 10, Color.RED, 0, 20, Color.WHITE);
				
				public final static int tileWidth = (int) (FACE.getWidth()+10);
				public static int tileHight = (int) (FACE.getHeight()+11);
				
				public Tile(){
					this.setPreferredSize(new Dimension(tileWidth,tileHight));
					this.setSize(new Dimension(tileWidth,tileHight));
					setToolTipText(toString());
					setOpaque(false);
					
				}
				

				
					public int getxLocation() {
					return xLocation;
				}

				public void setxLocation(int xLocation) {
					this.xLocation = xLocation;
				}


				public int getyLocation() {
					return yLocation;
				}

				public void setyLocation(int yLocation) {
					this.yLocation = yLocation;
				}
				
				public boolean isTileVisible() {
					return tileVisible;
				}


				public void setTileVisible(boolean tileVisible) {
					this.tileVisible = tileVisible;
				}


					public void paintComponent(java.awt.Graphics g){
						super.paintComponent(g);
								
						Graphics2D g2 = (Graphics2D)g;
						
						g2.setPaint(GRAD1);
						g2.fill(LEFT1);
						g2.setPaint(Color.BLUE);
						g2.fill(LEFT2);
						g2.fill(BOT2);
						
						g2.setPaint(GRAD1);
						g2.fill(BOT1);
						g2.setPaint(GRAD2);
						g2.fill(FACE);
						
						g2.setPaint(Color.BLACK);
						g2.drawRoundRect(10, 0, 59, 79, 5, 5);
						g2.draw(LEFT1OUT);
						g2.draw(LEFT2OUT);
						g2.draw(BOT1OUT);
						g2.draw(BOT2OUT);
												
						
						
//						Composite cOld = g2.getComposite();
//						Composite cNew = ((AlphaComposite)cOld).derive(0.25F);
//						
//						//g2.setComposite(cNew);
//						
//						//g2.setClip(RIGHTSHADOW);
//						g2.fillPolygon(RIGHTSHADOW);
				
					}//end paintcomponent
				

				/**
				 * This will compare to objects to see if they are the same
				 * @param other
				 * 
				 * @return
				 * TIle Object
				 */
				public boolean matches(Tile other){
					
					if(this.getClass() == other.getClass())
						return true;
					else
						return false;
				}//end matches
				
				
				public Tile copy(Tile t){

					Tile tile = new  Tile();
					tile = t;
					tile.setTileVisible(true);
					return tile;
				}

				
			}//end tile class
			
			
			
		
			}//end MahJongModel class




		
		

	}//end MahJongBoard Class
		

}//end MahJong
