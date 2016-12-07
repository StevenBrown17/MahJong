import	java.util.*;
import	java.awt.*;
import	javax.swing.*;



public class ScrollPane extends JScrollPane
{
	//private	static	Dimension	size = new Dimension(MahJong.MahJongBoard.MahJongModel.Tile.tileWidth,MahJong.MahJongBoard.MahJongModel.Tile.tileHight);

	public static	JPanel[]	discard = new JPanel[2];
	//public static	Stack<MahJong.MahJongBoard.MahJongModel.Tile>	undoStack = new Stack<MahJong.MahJongBoard.MahJongModel.Tile>();
	//private	Stack<Tile>	redoStack = new Stack<Tile>();		// optional
	public static		int		width = MahJong.MahJongBoard.MahJongModel.Tile.tileWidth + 6;
	public static		int		height = MahJong.MahJongBoard.MahJongModel.Tile.tileHight;
	public static		int		count = 0;
	public static JPanel	panel = new JPanel(new BorderLayout());

	public ScrollPane()
	{
		setPreferredSize(new Dimension(0, 2 * height+10));
		setBorder(BorderFactory.createRaisedBevelBorder());

		discard[0] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		discard[1] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		discard[0].setPreferredSize(new Dimension(0, height));
		discard[1].setPreferredSize(new Dimension(0, height));

		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		//JPanel	panel = new JPanel(new BorderLayout());
		setViewportView(panel);

		panel.add(discard[0], BorderLayout.NORTH);
		panel.add(discard[1], BorderLayout.SOUTH);
		
	}


//	public void addToUndo(MahJong.MahJongBoard.MahJongModel.Tile t1, MahJong.MahJongBoard.MahJongModel.Tile t2)
//	{
//		undoStack.push(t1);
//		undoStack.push(t2);
//
//		Dimension	size = new Dimension(++count * width, height + 6);
//		discard[0].setPreferredSize(size);
//		discard[1].setPreferredSize(size);
//
//		// This version puts the most recently added tiles on the right and scrolls
//		// a scroll pane so that the most recently added tiles are visible.
//
//		discard[0].add(t1);
//		discard[1].add(t2);
//
//		Rectangle	r = new Rectangle(count * width, 0, width, height + 6);
//		getViewport().scrollRectToVisible(r);
//
//
//		// This version puts the most recently added tiles on the left - it doesn't
//		// need to scroll - the most recently added tiles are always visible.
//
//		/*discard[0].add(t1, 0);
//		discard[1].add(t2, 0);*/
//
//		revalidate();
//		repaint();
//	}
//
//
//	public static void main(String[] args)
//	{
//		ScrollPane	demo = new ScrollPane();
//		JFrame		frame = new JFrame();
//
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add(demo);
//		frame.setSize(400, 250);
//		frame.setVisible(true);
//
//		try
//		{
//			int	pause = 2000;
//
//			demo.addToUndo(new PictureTile("Chrysanthemum"), new PictureTile("Orchid"));
//			Thread.sleep(pause);
//			demo.addToUndo(new PictureTile("Plum"), new PictureTile("Bamboo"));
//			Thread.sleep(pause);
//			demo.addToUndo(new PictureTile("Spring"), new PictureTile("Summer"));
//			Thread.sleep(pause);
//			demo.addToUndo(new PictureTile("Fall"), new PictureTile("Winter"));
//			Thread.sleep(pause);
//
//			demo.addToUndo(new CharacterTile('1'), new CharacterTile('2'));
//			Thread.sleep(pause);
//			demo.addToUndo(new CharacterTile('3'), new CharacterTile('4'));
//			Thread.sleep(pause);
//			demo.addToUndo(new CharacterTile('5'), new CharacterTile('6'));
//			Thread.sleep(pause);
//			demo.addToUndo(new CharacterTile('7'), new CharacterTile('8'));
//		}
//		catch (InterruptedException ie)
//		{
//			System.out.println(ie);
//		}
//	}
}

