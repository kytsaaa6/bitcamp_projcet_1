package rental;

public abstract class GameInfo implements Game{
	
	String uniqueNumber;
	String gameName;
	String rental;// 대여 여부

	
	
	GameInfo(String uniqueNumber,String gameName){
		this.uniqueNumber = uniqueNumber;
		this.gameName = gameName;
		this.rental = "대여 가능";
	}
	
	public void gameInfo() {
		System.out.println("고유 번호 : "+uniqueNumber);
		System.out.println("게임 이름 : "+gameName);
		System.out.println("대여 여부 : "+rental);
	}
	
}
