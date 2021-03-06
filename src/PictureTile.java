//import java.awt.FlowLayout;
//import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PictureTile extends MahJong.MahJongBoard.MahJongModel.Tile{

	protected String name;
	
	public PictureTile(String name){
		
		this.name = name;	
		setToolTipText(toString());
	}
	
	@Override
	public String toString(){
		
		return name;
	}
	
	//main to test functionality
//	public static void main(String[] args)
//	{
//		JFrame	frame = new JFrame();
//
//		frame.setLayout(new FlowLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Picture Tiles");
//
//		frame.add(new Bamboo1Tile());
//
//		frame.add(new FlowerTile("Chrysanthemum"));
//		frame.add(new FlowerTile("Orchid"));
//		frame.add(new FlowerTile("Plum"));
//		frame.add(new FlowerTile("Bamboo"));
//
//		frame.add(new SeasonTile("Spring"));
//		frame.add(new SeasonTile("Summer"));
//		frame.add(new SeasonTile("Fall"));
//		frame.add(new SeasonTile("Winter"));
//
//		frame.pack();
//		frame.setVisible(true);
//	}//end main
}//end picture tile class
