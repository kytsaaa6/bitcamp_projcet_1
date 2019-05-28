package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import util.Util;


public class GameManage implements GameManagement{
	private ArrayList<GameInfo> game;
	private ArrayList<GameInfo> searchResult = new ArrayList<GameInfo>(100); // 검색해서 중복되는 게임이있을경우 이배열에들어가서 나타난다
	private int select;
	public static Scanner keyboard = new Scanner(System.in);
	private GameManage() {                                                 // 싱글톤
		game = new ArrayList<GameInfo>(100);
	}
	
	private static GameManage m = new GameManage();                        // 싱글톤
	
	
	
	public static GameManage getInstance() {                               // 싱글톤
		if(m == null) {
			m = new GameManage();
		}
		return m;
	}
	 
	public ArrayList<GameInfo> getGame(){
		return game;
	}
	public ArrayList<GameInfo> getSearchResult(){
		return searchResult;
	}
	
	String gameList[] = new String[] { "FPS","RPG","Sport"  };            // 게임종류 배열
	int uiqueNumber = 0;                                                  // 고유번호를 위한 변수
	
	
	int switchnum = 0;                 // 검색메뉴에 처음 접근하는지 구별하는 숫자: 0이면 검색메뉴로 처음 접근 1이면 삭제메뉴로 접근 
	String gameName = "";
	public void inputException() {                                     // 예외처리를 위한 메서드
		try {
			select = keyboard.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못입력하셨습니다. 다시입력하세요.");
			select = 0;
			return;
		}
	}
	public void inputException2() {                                     // 예외처리를위한 메서드2
		try {	
			select = keyboard.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			select = 0;
			keyboard.nextLine();
			insertGame();
		}
		return;
	}
	
	public int showMenu() {                                             // 메인메서드 시작시 시작메뉴 메서드
		System.out.println("메뉴를 선택해주세요.");
		System.out.println("===========================");
		System.out.printf("%d.게임 등록\n%d.게임 삭제\n%d.게임 검색\n%d.전체 게임\n%d.종 료"
				,Util.INSERT,Util.DELET,Util.SERCH,Util.ALLLIST,Util.QUIT);
		System.out.println("");
		System.out.println("===========================");
		
		
		inputException();                                               //예외처리
		keyboard.nextLine();
		
		return select;
	}
	@Override
	public void insertGame() {                                           // 삽입기능 메서드
		System.out.println("게임 종류를 선택해주세요.");
		System.out.println("===========================");
		
		for(int i = 1; i<=gameList.length; i++) {        // gameList.length -> 게임종류배열의 크기 만큼  (총 3개 - FPS,Sport,RPG)
			System.out.printf("%d. %s\n",i,gameList[i-1]);
		}
		
		inputException2();                                            // 예외처리
		keyboard.nextLine();
		
		System.out.println("======================");
		System.out.println("게임 이름을 입력해주세요.");
		String gameName = keyboard.nextLine();
		
		String type = gameList[select-1];
		
		String unumber = "GAME"+ type+ uiqueNumber++;   //고유번호가 자동으로 생성될 부분
		
		if(select == 1) {
			game.add(new Fps(unumber,gameName,type));  //rental은 게임 삽입할때 기본값이 대여 가능으로 들어감

		}else if(select == 2) {
			game.add(new RPG(unumber,gameName,type));
		}else if(select == 3) {
			game.add(new Sport(unumber,gameName,type));
		}else {
			System.out.println("선택된 번호 안에서 골라주세요.");
		}
		System.out.println("게임이 저장되었습니다.");
		System.out.println("======================");
	}
	@Override
	public void deletGame() {                                   // 게임 삭제 기능 메서드
		searchGame();                                       // 검색메서드부터 시작해서 검색한다
		if(searchResult.size() < 1) {
			switchnum = 0;
			return;
		}
		System.out.println("삭제할 번호를 선택해주세요.");
		select = keyboard.nextInt();
		game.remove(searchResult.get(select-1));            // searchResult 는 검색된 같은이름의게임 배열
		System.out.println("선택하신 게임이 삭제되었습니다.");
		System.out.println("1. 계속 삭제 2. 메뉴로 돌아가기");
		select = keyboard.nextInt();
		if(select == 1) {                                // 계속삭제를 할경우 검색이된상태로 검색기능을 거쳐서 삭제기능으로 넘어간다
			switchnum = 1;
			deletGame();
		}else if(select == 2) {
			System.out.println("종료합니다.");
			switchnum = 0;
		}
		
	}
	@Override
	public void searchGame() {                                  // 검색기능 메서드
		searchResult.clear();
		
		
		if(switchnum == 0) {
			System.out.println("게임이름을 검색해주세요.");
			gameName = keyboard.nextLine();
		}
		
		int k = 1;                                             // 검색된 게임을 번호순으로 나타낼 변수
		for(int i=0; i<game.size(); i++) {
			if(game.get(i).gameName.equals(gameName)) {
				System.out.println("=====[ "+ k++ +" ]=====");
				game.get(i).gameInfo();
				searchResult.add(game.get(i));         // 검색한 게임의이름과 같을경우 searchResult 배열에 추가
			} 
			
		}
		if(searchResult.size() < 1) {                          // 검색된 게임이 없을경우 searchResult == 0 
			System.out.println("검색하신 게임이 없습니다.");
			System.out.println("=======================");
			return;
		}
		
	}
	@Override 
	public void allListGame() {                                  // 전체정보 출력 메서드
		for(int i=0; i<game.size(); i++) {
			
				System.out.println("=====[ "+ (i+1) +" ]=====");
				game.get(i).gameInfo();
		}
		if(game.size()<1) {
			System.out.println("저장되어있는 게임이 없습니다.");
			System.out.println("=======================");
			return;
		}
	}

}
