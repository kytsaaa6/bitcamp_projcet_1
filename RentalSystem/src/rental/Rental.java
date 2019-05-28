package rental;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import book.BookInfo;
import book.BookManage;
import dvd.DvdInfo;
import dvd.DvdManager;
import game.GameInfo;
import game.GameManage;
import user.*;
import util.Util;

public class Rental implements RentalInterface{
	
	private HashMap<BookInfo,String[]> rentalBookInfo;
	private HashMap<GameInfo,String[]> rentalGameInfo;
	private HashMap<DvdInfo,String[]> rentalDVDInfo;
		
	private AccountManager account = AccountManager.getInstance();
	private GameManage game = GameManage.getInstance();
	private DvdManager dvd = DvdManager.getInstance();
	private BookManage book = BookManage.getInstance();


	private int userindex;
	

	private SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	
	private Rental(){
		rentalBookInfo = new HashMap<BookInfo,String[]>();
		rentalGameInfo = new HashMap<GameInfo,String[]>();
		rentalDVDInfo = new HashMap<DvdInfo,String[]>();
		
	}
	
	private static Rental r;
	
	public static Rental getInstance() {
		if(r == null) {
			r = new Rental();
		}
		return r;
	}
	
		
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
			book.searchBook();
			if(book.getSearchResult().size() < 1) {
				System.out.println("검색 결과가 없습니다.");
				return;
			}
			
			System.out.println("대여할 상품 번호를 입력하세요.");
			int borrowSelect = Util.keyboard.nextInt();
			
			BookInfo selected = book.getSearchResult().get(borrowSelect-1);
			
			
			if(selected.getRental().equals("대여 가능")) {
				selected.rental = "대여 중";

				rentalBookInfo.put(selected, new String[]{ account.getLoginId() ,	format.format(new Date()) });
				
				for(int i = 0; i< account.getMembers().size(); i++) {
					if(account.getMembers().get(i).getID().equals(account.getLoginId())){
						account.getMembers().get(i).addInfo(selected);
					}
				}
				
				
				System.out.println("대여 되었습니다.");
				
				
			}else {
				System.out.println("대여중 입니다.");
				System.out.println("대여자: "+rentalGameInfo.get(selected)[0]+
						"대여일: "+rentalGameInfo.get(selected)[1]);
			}
			
			
			break;
		case Util.DVD:
			dvd.searchDvd();
			if(dvd.getSearchResult().size() < 1) {
				return;
			}
			
			System.out.println("대여할 상품 번호를 입력하세요.");
			borrowSelect = Util.keyboard.nextInt();
			
			DvdInfo selected2 = dvd.getSearchResult().get(borrowSelect-1);
			

			
			if(selected2.getRental().equals("대여 가능")) {
				selected2.rental = "대여 중";
				
				rentalDVDInfo.put(selected2, new String[]{ account.getLoginId() ,	format.format(new Date()) });
				
				
				
				for(int i = 0; i< account.getMembers().size(); i++) {
					if(account.getMembers().get(i).getID().equals(account.getLoginId())){
						account.getMembers().get(i).addInfo(selected2);
					}
				}
				
				System.out.println("대여 되었습니다.");
				
				
			}else {
				System.out.println("대여중 입니다.");
				System.out.println("대여자: "+rentalGameInfo.get(selected2)[0]+
						"대여일: "+rentalGameInfo.get(selected2)[1]);
			}
			
			
			break;
			
		case Util.GAME:
			game.searchGame();
			if(game.getSearchResult().size() < 1) {
				return;
			}
			
			System.out.println("대여할 상품 번호를 입력하세요.");
			borrowSelect = Util.keyboard.nextInt();
			
			GameInfo selected3 = game.getSearchResult().get(borrowSelect-1);
			
			
			if(selected3.rental.equals("대여 가능")) {
				selected3.rental = "대여 중";
				
				rentalGameInfo.put(selected3, new String[]{ account.getLoginId() ,format.format(new Date()) });
				
				for(int i = 0; i< account.getMembers().size(); i++) {
					if(account.getMembers().get(i).getID().equals(account.getLoginId())){
						account.getMembers().get(i).addInfo(selected3);
					}
				}
				System.out.println("대여 되었습니다.");
				
				
			}else {
				System.out.println("대여중 입니다.");
				System.out.println("대여자: "+rentalGameInfo.get(selected3)[0]+
						"대여일: "+rentalGameInfo.get(selected3)[1]);
			}
			
			break;

		}

		
	}
	
	@Override
	public void returnBack() {
		
		
		borrowInfo();
		
		System.out.println("반납할 대상의 번호를 선택하세요. ");
		
		int selectNumber = Util.keyboard.nextInt();
		

		if(account.getMembers().get(userindex).getinfo().get(selectNumber) instanceof GameInfo) {
			
			rentalGameInfo.get(account.getMembers().remove(userindex).getinfo().get(selectNumber));
			
			for(int i = 0; i< game.getGame().size(); i++) {
				if(account.getMembers().get(userindex).getinfo().get(selectNumber).equals( game.getGame().get(i)) ) {
					 game.getGame().get(i).rental = "대여 가능";
				}
			}
			
			
		}else if(account.getMembers().get(userindex).getinfo().get(selectNumber) instanceof BookInfo) {
			
			rentalBookInfo.get(account.getMembers().remove(userindex).getinfo().get(selectNumber));

			
			for(int i = 0; i<book.getBook().size(); i++) {
				if(account.getMembers().get(userindex).getinfo().get(selectNumber).equals( book.getBook().get(i)) ) {
					book.getBook().get(i).rental = "대여 가능";
				}
			}
			
		}else if(account.getMembers().get(userindex).getinfo().get(selectNumber) instanceof DvdInfo) {
			
			rentalDVDInfo.get(account.getMembers().remove(userindex).getinfo().get(selectNumber));

			for(int i = 0; i< dvd.getDvd().size(); i++) {
				if(account.getMembers().get(userindex).getinfo().get(selectNumber).equals( dvd.getDvd().get(i)) ) {
					dvd.getDvd().get(i).rental = "대여 가능";
				}
			}
		}
		
		account.getMembers().get(userindex).getinfo().remove(selectNumber);
		
		System.out.println("반납 되었습니다.");
		
	}
	
	
	@Override
	public void borrowInfo() {
		System.out.println("사용자가 대여중인 목록입니다.");
		
		for(int i = 0; i< account.getMembers().size(); i++) {
			if(account.getMembers().get(i).getID().equals(account.getLoginId())){
				userindex = i;
				for(int k = 0; k<account.getMembers().get(i).getinfo().size(); k++) {
					System.out.println("======["+k+"]======");
					account.getMembers().get(userindex).getinfo().get(k).toString();
					if(account.getMembers().get(userindex).getinfo().get(k) instanceof GameInfo) {
						System.out.println(rentalGameInfo.get(account.getMembers().get(userindex).getinfo().get(k))[0]);
						System.out.println(rentalGameInfo.get(account.getMembers().get(userindex).getinfo().get(k))[1]);						
					}else if(account.getMembers().get(userindex).getinfo().get(k) instanceof BookInfo) {
						System.out.println(rentalBookInfo.get(account.getMembers().get(userindex).getinfo().get(k))[0]);
						System.out.println(rentalBookInfo.get(account.getMembers().get(userindex).getinfo().get(k))[1]);			
					}else if(account.getMembers().get(userindex).getinfo().get(k) instanceof DvdInfo) {
						System.out.println(rentalDVDInfo.get(account.getMembers().get(userindex).getinfo().get(k))[0]);
						System.out.println(rentalDVDInfo.get(account.getMembers().get(userindex).getinfo().get(k))[1]);			
					}
					
					System.out.println("=================");
				}
			}
		}
		
	}
	
}
