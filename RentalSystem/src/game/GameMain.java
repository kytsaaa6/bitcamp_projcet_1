package game;

import java.util.Scanner;

import util.Util;

public class GameMain {

		public GameManage gameManage = GameManage.getInstance();
		
		public void gameMainRun() {
			
		
			while(true) {
				
				int choice = gameManage.showMenu();
				
				switch(choice){
				case Util.INSERT:
					gameManage.insertGame();
					break;
				case Util.DELET:
					gameManage.deletGame();
					break;
				case Util.SERCH:
					gameManage.searchGame();
					break;
				case Util.ALLLIST:
					gameManage.allListGame();
					break;
				case Util.QUIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				}

			}
	}

}
