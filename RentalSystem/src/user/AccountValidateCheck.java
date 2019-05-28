package user;

public class AccountValidateCheck {

	
	// ID 일치여부 확인
	AccountInfo IdCheck(String id) {
		AccountManager manager = AccountManager.getInstance();

        for(AccountInfo idcheck : manager.getMembers()) {
            if(idcheck.getID().equals(id)) {
                return idcheck;
            }
        }
        return null;
    }
	
	// ID 조합 규칙 메서드
	int checkID(String id) {
		
		int isCheck = 1;
	    if(id.length()<5) {
	    	isCheck = 0;
	    }
	    return isCheck;
	}

	// PW 조합 규칙 메서드
	int checkPassword(String password) {
		
		int isCheck = 1;
		char[] ch = password.toCharArray();
		
		// 패스워드 9자 이상
		if (password.length()>=9) {
			
			// 패스워드 대문자 포함여부 확인
			for(int i=0; i<ch.length; i++) {
				if(ch[i] >= 65 && ch[i] <= 90) {
					isCheck = 2;
				}
			}
			// 패스워드 특수문자 포함여부 확인
			for(int i=0; i<ch.length; i++) {
				if (ch[i] >= 33 && ch[i] <= 47) {
					isCheck = 3;
				}	
			}
		} else {
				isCheck = 1;
		}
		return isCheck;
	}
    
}

