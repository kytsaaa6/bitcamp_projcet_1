package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Util {

	public static Scanner keyboard = new Scanner(System.in);
	
    int LOGIN = 1;
    int JOIN = 2;
	
    int INSERT = 1;
    int SEARCH = 2;
    int DELETE = 3;
    int MODIFY = 4;
    int SEARCH_ALL = 5;
    int QUIT = 6;
 
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
