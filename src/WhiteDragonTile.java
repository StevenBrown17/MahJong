import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

@SuppressWarnings("serial")
public class WhiteDragonTile extends MahJong.MahJongBoard.MahJongModel.Tile{
	
	public String toString(){
		
		return "White Dragon";
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
		Stroke solid = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{500}, 0);
		g2.setColor(Color.BLUE);
		g2.setStroke(solid);
		g.drawRect(20, 20, 40, 40);
		g2.setColor(Color.WHITE);
		g2.setStroke(dashed);
		g.drawRect(20, 20, 40, 40);
	}
	
	
	
	
	
	
}