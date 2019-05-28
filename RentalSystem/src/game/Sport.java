package game;

public class Sport extends GameInfo{
	
	String type;
	
	public Sport(String uniqueNumber,String gameName,String type) {
		super(uniqueNumber,gameName);
		this.type =type;
	}
	public void gameInfo() {
		super.gameInfo();
		System.out.println("게임 종류 : "+type);
		
		
	}
}
