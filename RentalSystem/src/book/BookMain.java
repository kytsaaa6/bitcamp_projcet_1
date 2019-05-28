package book;

import util.Util;

public class BookMain {

		public BookManage bookManage = BookManage.getInstance();
		
		

		public void bookMainRun() {
			
			while (true) {
	
				int choice = bookManage.showMenu();
	
				
				switch (choice) {
				case Util.INSERT:
					bookManage.insertBook();
					
					break;
				case Util.DELET:
					bookManage.deleteBook();
					break;
				case Util.SERCH:
					bookManage.searchBook();
					break;
				case Util.ALLLIST:
					bookManage.allListBook();
					break;
				case Util.QUIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				}
			}

	}

}
