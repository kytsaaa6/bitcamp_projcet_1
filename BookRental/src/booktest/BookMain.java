package booktest;

public class BookMain {

	public static void main(String[] args) {
		BookManage bookManage = new BookManage();

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
