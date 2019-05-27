package booktest;

import java.util.ArrayList;
import java.util.Scanner;

public class BookManage implements BookManagement {

	String bookList[] = new String[] { "Novel", "Poetry", "ComicBook" };
	ArrayList<BookInfo> book = new ArrayList<BookInfo>(35); // 책 목록 배열
	ArrayList<BookInfo> searchResult = new ArrayList<BookInfo>(); // 검색결과 배열
	public static Scanner keyboard = new Scanner(System.in);

	int switchNum = 0;
	String bookName = "";

	public int showMenu() {
		System.out.println("메뉴를 선택해주세요.");
		System.out.println("----------------------------");
		System.out.printf("%d.책 등록\n%d.책 삭제\n%d.책 검색\n%d.전체 책목록\n%d.종료", Util.INSERT, Util.DELET, Util.SERCH,
				Util.ALLLIST, Util.QUIT);
		System.out.println("");
		System.out.println("----------------------------");

		int select = keyboard.nextInt();
		keyboard.nextLine();

		return select;
	}

	@Override
	public void insertBook() {
		System.out.println("책 종류를 선택해주세요");
		System.out.println("----------------------------");

		for (int i = 1; i <= bookList.length; i++) {
			System.out.printf("%d. %s\n", i, bookList[i - 1]);
		}

		int select = keyboard.nextInt();
		keyboard.nextLine();

		System.out.println("----------------------------");
		System.out.println("책 제목을 입력해주세요.");
		String bookName = keyboard.nextLine();
		System.out.println("----------------------------");
		System.out.println("작가를 입력해주세요.");
		String writer = keyboard.nextLine();
		String type = bookList[select - 1];

		if (select == 1) {
			book.add(new Novel(bookName, writer, type));
		} else if (select == 2) {
			book.add(new Poetry(bookName, writer, type));
		} else if (select == 3) {
			book.add(new ComicBook(bookName, writer, type));
		} else {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
		}

		System.out.println("책정보가 저장되었습니다.");
		System.out.println("-----------------------------------");

	}

	@Override
	public void deleteBook() {
		searchBook();
		if (searchResult.size() < 1) {
			switchNum = 0;
			return;
		}
		System.out.println("삭제할 번호를 선택해주세요");
		int select = keyboard.nextInt();
		book.remove(searchResult.get(select - 1));
		System.out.println("선택하신 책이 삭제되었습니다.");
		System.out.println("1. 계속삭제 2. 종료");
		select = keyboard.nextInt();
		if (select == 1) {
			switchNum = 1;
			deleteBook();
		} else if (select == 2) {
			System.out.println("종료합니다");
			switchNum = 0;
		}

	}

	@Override
	public void searchBook() {
		searchResult.clear();

		if (switchNum == 0) {
			System.out.println("책 제목을 검색해주세요.");
			bookName = keyboard.nextLine();
		}

		int k = 1;
		for (int i = 0; i < book.size(); i++) {
			if (book.get(i).bookName.equals(bookName)) {
				System.out.println("====[ " + k++ +  "]====");
				book.get(i).bookInfo();
				searchResult.add(book.get(i));
			}
			if (searchResult.size() < 1) {
				System.out.println("검색하신 책을 찾을 수 없습니다.");
				System.out.println("-------------------------------");
				return;

			}
		}

		
	}

	@Override
	public void allListBook() {
		for (int i = 0; i < book.size(); i++) {

			System.out.println("====[" + (i + 1) + "]====");
			book.get(i).bookInfo();
		}

	}

}
