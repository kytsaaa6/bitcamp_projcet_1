package Rental;

import java.util.Scanner;

public interface Menu {
	//시작메뉴
	//1.등록, 2.삭제 , 3.검색, 4.전체정보, 5.종료
	public int INSERT = 1, DELET = 2, SERCH = 3, ALLLIST = 4, QUIT = 5;
	
	public static Scanner keyboard = new Scanner(System.in);
}
