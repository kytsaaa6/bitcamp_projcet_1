package rental2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import rental.AccountManager;

public class Rental implements RentalInterface{
	
	private HashMap<String,String[]> rentaBooklInfo;
	private HashMap<String,String[]> rentalGameInfo;
	private HashMap<String,String[]> rentalDVDInfo;
		
	private AccountManager account = new AccountManager();
	private GameManage game = GameManage.getInstance();
	
	
	

	private SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	private Date date = new Date();
	
	private Rental(){
		rentaBooklInfo = new HashMap<String,String[]>();
		rentalGameInfo = new HashMap<String,String[]>();
		rentalDVDInfo = new HashMap<String,String[]>();
		
	}
	
	private static Rental r;
	
	public static Rental getInstance() {
		if(r == null) {
			r = new Rental();
		}
		return r;
	}
	
	
	
	int fee = 700;
	int rentalDay = 7;
	
	public int showMenu() {
		System.out.println("===================");
		System.out.println("메뉴를 선택해주세요.");
		System.out.println(Util.BORROW + ". 대여"+ Util.RETURN+ ". 반납" + Util.BORROWINFO + "대여 정보");
		System.out.println("===================");
		
		int select = Util.keyboard.nextInt();
		
		return select;	
	}
	
	public int showMenu2() {
		System.out.println("===================");
		System.out.println("카테고리를 선택해주세요.");
		System.out.println(Util.BOOK + ". 책"+ Util.DVD+ ". DVD"+ Util.GAME+ ". 게임");
		System.out.println("===================");
		
		int select = Util.keyboard.nextInt();
		
		return select;	
	}
	
	@Override
	public void borrow() {
		
		int choice = showMenu2();
		
		switch(choice) {
		case Util.BOOK:
			
			break;
		case Util.DVD:
			
			break;
			
		case Util.GAME:
			game.searchGame();
			if(game.getSearchResult().size() < 1) {
				return;
			}
			
			System.out.println("대여할 상품 번호를 입력하세요.");
			int borrowSelect = Util.keyboard.nextInt();
			
			GameInfo selected = game.getSearchResult().get(borrowSelect-1);
			
			if(selected.rental.equals("대여 가능")) {
				rentaBooklInfo.put(selected.uniqueNumber, new String[]{account.,	format.format(new Date()) });
				System.out.println("대여 되었습니다.");
				
				
			}else {
				System.out.println("대여중 입니다.");
			}
			
			
			break;

		}

		
	}
	
	@Override
	public void returnBack() {
		
		
		
	}
	
	
	@Override
	public void borrowInfo() {
		
	}
	
}
