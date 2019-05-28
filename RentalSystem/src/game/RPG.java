package game;

public class RPG extends GameInfo{
	
	String type;
	
	public RPG(String uniqueNumber,String gameName,String type) {
		super(uniqueNumber,gameName);
		this.type =type;
	}
	public void gameInfo() {
		super.gameInfo();
		System.out.println("게임 종류 : "+type);
		
		
	}
	
}
