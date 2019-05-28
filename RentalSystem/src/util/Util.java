package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Util {
	
	Scanner keyboard = new Scanner(System.in);
	
	
	public int INSERT = 1, DELET = 2, SERCH = 3, ALLLIST = 4, QUIT = 5;
	public int BORROW = 1, RETURN = 2, BORROWINFO=3;
	public int BOOK = 1, DVD = 2, GAME = 3, USER= 4;
	
	public int USERRETURN = 3, SEARCHALL = 4;
	
	
	
    int LOGIN = 1;
    int JOIN = 2;
	
    int SEARCH = 2;
    int DELETE = 3;
    int MODIFY = 4;
    int SEARCH_ALL = 5;
    int QUIT2 = 6;
 
    public static int inputInt(){                                    //정수 예외처리하는 유틸리티
        int d = 0;
        while(true) {
            try {
                d = keyboard.nextInt();
                break;
            }catch(InputMismatchException e) {
            	keyboard.nextLine();
                System.out.println("정수를 입력하세요.");
                System.out.println("재 입력.");
            }
        }
        
        return d;
    }
	
}
