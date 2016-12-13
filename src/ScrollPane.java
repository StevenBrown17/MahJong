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

}

