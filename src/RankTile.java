
@SuppressWarnings("serial")
public class RankTile extends MahJong.MahJongBoard.MahJongModel.Tile{
	
	protected int rank;
	
	public RankTile(int rank){
		this.rank = rank;
	}
	
	public MahJong.MahJongBoard.MahJongModel.Tile copy(MahJong.MahJongBoard.MahJongModel.Tile t){
		 MahJong.MahJongBoard.MahJongModel.Tile tile = new  MahJong.MahJongBoard.MahJongModel.Tile();
		tile = super.copy(t);
		return tile;
	}
	
	@Override
	public boolean matches(MahJong.MahJongBoard.MahJongModel.Tile other){
		 
		 if(super.matches(other)){
		 
			 RankTile rt = (RankTile) other;
			 if(rt.rank == this.rank)
		        return true;
		 }
		 
		 return false;    
	}
	

}
