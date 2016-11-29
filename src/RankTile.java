
@SuppressWarnings("serial")
public class RankTile extends MahJong.MahJongBoard.MahJongModel.Tile{
	
	protected int rank;
	
	public RankTile(int rank){
		this.rank = rank;
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
