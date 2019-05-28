package user;

import util.Util;

public class AccountMain {

		public AccountManager manager = AccountManager.getInstance();
        boolean isExit = false;   
        public int choice;
        
        public void accountMainRun1(){
    			
        		choice = manager.insertMenu();
    			
    			switch(choice) {
    			
    			case Util.JOIN:
    				manager.createAccount();
    				
    			case Util.LOGIN:
    				manager.accountLogin();
				
    				break;
    			}

        }
		
        public void accountMainRun2(){
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
    	                
    	            case Util.QUIT2:
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

