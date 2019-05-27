package rental;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class GameManage implements GameManagement{
	private ArrayList<GameInfo> game;
	private ArrayList<GameInfo> searchResult = new ArrayList<GameInfo>(100);
	private int select;
	public static Scanner keyboard = new Scanner(System.in);
	private GameManage() {
		game = new ArrayList<GameInfo>(100);
	}
	
	private static GameManage m = new GameManage();
	
	
	static GameManage getInstance() {
		if(m == null) {
			m = new GameManage();
		}
		return m;
	}
	 
	
	String gameList[] = new String[] { "FPS","RPG","Sport"  };
	int uiqueNumber = 0; 
	
	
	int switchnum = 0; // 검색메뉴에 처음 접근하는지 구별하는 숫자: 0이면 검색메뉴로 처음 접근 1이면 삭제메뉴로 접근 
	String gameName = "";
	public void inputException() {
		try {
			select = keyboard.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못입력하셨습니다. 다시입력하세요.");
			select = 0;
			return;
		}
	}
	public void inputException2() {
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
	
	public int showMenu() {
		System.out.println("메뉴를 선택해주세요.");
		System.out.println("===========================");
		System.out.printf("%d.게임 등록\n%d.게임 삭제\n%d.게임 검색\n%d.전체 게임\n%d.종 료"
				,Util.INSERT,Util.DELET,Util.SERCH,Util.ALLLIST,Util.QUIT);
		System.out.println("");
		System.out.println("===========================");
		
		
		inputException();
		keyboard.nextLine();
		
		return select;
	}
	@Override
	public void insertGame() {
		System.out.println("게임 종류를 선택해주세요.");
		System.out.println("===========================");
		
		for(int i = 1; i<=gameList.length; i++) {
			System.out.printf("%d. %s\n",i,gameList[i-1]);
		}
		
		inputException2();
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
	public void deletGame() {
		searchGame();
		if(searchResult.size() < 1) {
			switchnum = 0;
			return;
		}
		System.out.println("삭제할 번호를 선택해주세요.");
		select = keyboard.nextInt();
		game.remove(searchResult.get(select-1));
		System.out.println("선택하신 게임이 삭제되었습니다.");
		System.out.println("1. 계속 삭제 2. 메뉴로 돌아가기");
		select = keyboard.nextInt();
		if(select == 1) {
			switchnum = 1;
			deletGame();
		}else if(select == 2) {
			System.out.println("종료합니다.");
			switchnum = 0;
		}
		
	}
	@Override
	public void searchGame() {
		searchResult.clear();
		
		
		if(switchnum == 0) {
			System.out.println("게임이름을 검색해주세요.");
			gameName = keyboard.nextLine();
		}
		
		int k = 1;
		for(int i=0; i<game.size(); i++) {
			if(game.get(i).gameName.equals(gameName)) {
				System.out.println("=====[ "+ k++ +" ]=====");
				game.get(i).gameInfo();
				searchResult.add(game.get(i));
			}
			
		}
		if(searchResult.size() < 1) {
			System.out.println("검색하신 게임이 없습니다.");
			System.out.println("=======================");
			return;
		}
		
	}
	@Override
	public void allListGame() {
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
