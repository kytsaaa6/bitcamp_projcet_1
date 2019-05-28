package main;

import user.*;
import book.*;
import dvd.*;
import game.*;
import util.*;
import rental.*;



public class RentalSystemMain {

	public static void main(String[] args) {
		AccountMain accountMain = new AccountMain();
		BookMain bookMain  = new BookMain();
		DvdMain dvdMain = new DvdMain();
		GameMain gameMain = new GameMain();
		Rental rental = Rental.getInstance();
		

		System.out.println("=========대여 시스템을 시작합니다.=========");
		while(true){
			accountMain.accountMainRun1();
			if(accountMain.manager.getLoginId().equals("admin")) {
				System.out.println("=========관리자로 로그인 하였습니다.=========");
				System.out.println("=========관리자용 시스템으로 접속합니다.=========");

				while(true) {
					System.out.println("=========이용하실 메뉴를 선택하세요.=========");
					System.out.println(Util.BOOK+". 도서 관리\n" +  Util.DVD +". DVD 관리\n" + Util.GAME+ ". 게임 관리\n" + Util.USER + ". 회원 관리\n" +Util.QUIT +". 종료" );
					int choice = Util.keyboard.nextInt();

					if(choice == Util.BOOK) {
						System.out.println("=========도서 관리 시스템에 접속합니다.=========");
						bookMain.bookMainRun();
					}else if( choice == Util.DVD) {
						System.out.println("=========DVD 관리 시스템에 접속합니다.=========");
						dvdMain.dvdMainRun();
					}else if(choice == Util.GAME) {
						System.out.println("=========게임 관리 시스템에 접속합니다.=========");
						gameMain.gameMainRun();
					}else if(choice == Util.USER) {
						System.out.println("=========회원 관리 시스템에 접속합니다.=========");
						accountMain.accountMainRun2();
					}else if(choice == Util.QUIT) {
						System.out.println("=========시스템을 종료합니다.=========");
						break;
					}else {
						System.out.println("=========제대로 된 번호를 입력해 주세요.=========");
					}
				}


			}else {
				System.out.println("=========사용자로 로그인 하였습니다.=========");
				System.out.println("=========사용자용 시스템으로 접속합니다.=========");


				while(true) {
					System.out.println("=========이용하실 메뉴를 선택하세요.=========");
					System.out.println(Util.BORROW+". 대여\n" +  Util.RETURN +". 반납\n" + Util.USERRETURN+
							". 개인 대여정보 조회\n" + Util.SEARCHALL + ". 도서/DVD/게임 검색\n"  +Util.QUIT +". 종료" );

					int choice = Util.keyboard.nextInt();

					if(choice == Util.BORROW) {
						System.out.println("=========대여 시스템에 접속합니다.=========");
						rental.borrow();
					}else if( choice == Util.RETURN) {
						System.out.println("=========반납 시스템에 접속합니다.=========");
						rental.returnBack();
					}else if(choice == Util.USERRETURN) {
						System.out.println("=========개인 대여정보를 조회합니다.=========");
						rental.borrowInfo();
					}else if(choice == Util.SEARCHALL) {
						System.out.println("=========도서/DVD/게임 검색 시스템에 접속합니다.=========");
						System.out.println("=========검색하실 메뉴를 선택하세요.=========");
						System.out.println(Util.BOOK+". 도서 검색\n" +  Util.DVD +". DVD 검색\n" + Util.GAME+
								". 게임 검색\n" +Util.QUIT +". 종료" );
						choice = Util.keyboard.nextInt();

						if(choice == Util.BOOK) {
							System.out.println("=========도서를 검색합니다.=========");
							bookMain.bookManage.searchBook();
						}else if( choice == Util.DVD) {
							System.out.println("=========DVD를 검색합니다.=========");
							dvdMain.manager.searchDvd();
						}else if(choice == Util.GAME) {
							System.out.println("=========게임을 검색합니다.=========");
							gameMain.gameManage.searchGame();
						}else if( choice == Util.QUIT) {
							System.out.println("=========검색을 종료합니다.=========");
							break;
						}else {
							System.out.println("=========제대로 된 번호를 입력해 주세요.=========");
						}


					}else if(choice == Util.QUIT) {
						System.out.println("=========시스템을 종료합니다.=========");
						break;
					}else {
						System.out.println("=========제대로 된 번호를 입력해 주세요.=========");
					}
				}



			}
		}	
	}
	
		

}
