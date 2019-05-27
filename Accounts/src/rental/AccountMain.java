package rental;

import util.Util;

public class AccountMain {

	public static void main(String[] args) {
		
		AccountManager manager = new AccountManager();
        boolean isExit = false;   
		
		while(true) {
			
			int choice = manager.insertMenu();
			
			
			switch(choice) {
			
			case Util.JOIN:
				manager.createAccount();
			case Util.LOGIN:
				manager.accountLogin();
				break;
			}
			
			while(true) {
				int choice2 = manager.detailMenu();
				
				switch(choice2) {
				case Util.INSERT:
					manager.createAccount();
					break;
				case Util.SEARCH:
					manager.searchAccount();
					break;
					
	            case Util.DELETE:
	                manager.deleteAccount();
	                break;
	                
	            case Util.MODIFY:
	                manager.updateAccount();
	                break;
	                
	            case Util.SEARCH_ALL:
	                manager.showAll();
	                break;
	                
	            case Util.QUIT:
	                System.out.println("프로그램 종료.");
	                isExit = true;
	                break;
	            }
				if(isExit) {
					isExit = false;
					break;

				}
			
			}
			

		}
		
	}

}
