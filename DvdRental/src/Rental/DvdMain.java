package Rental;

import java.util.*;

public class DvdMain {

	public static void main(String[] args) {
		DvdManager manager = new DvdManager();

		while(true) {
			int dvdM = manager.printMenu();//dvdM에 메뉴 메서드저장
			
			switch(dvdM) {
			case Menu.INSERT:
				manager.insertDvd();
				break;
			case Menu.DELET:
				manager.deletDvd();
				break;
			case Menu.SERCH:
				manager.searchDvd();
				break;
			case Menu.ALLLIST:
				manager.allListDvd();
				break;
			case Menu.QUIT:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
	}
}
