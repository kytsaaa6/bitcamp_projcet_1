package game;

public class Fps extends GameInfo{
	
	String type;
	
	public Fps(String uniqueNumber,String gameName,String type) {
		super(uniqueNumber,gameName);
		this.type =type;
	}
	public void gameInfo() {
		super.gameInfo();
		System.out.println("게임 종류 : "+type);
		
		
	}
	
}
