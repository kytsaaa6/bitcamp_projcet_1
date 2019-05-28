package rental;

import java.util.ArrayList;

import util.Util;

public class AccountManager {

	
    ArrayList<AccountInfo> members = new ArrayList<>();
    
    // 관리자 계정
    public AccountManager() {
    	members.add(new AccountInfo("admin","admin","abcd@abcd","1111"));
    }
	
    // 메인 화면
    int insertMenu() {
        
        System.out.println("======================================");
        System.out.println("메뉴를 선택하세요");
        System.out.printf("%d.로그인 \n%d.회원가입 \n",Util.LOGIN,Util.JOIN);
        System.out.println("======================================");
        
        int keyboard = Util.inputInt();
 
        while(keyboard<1 || keyboard >2){
            System.out.println("1번에서 2번 사이의 값을 입력하세요.");
            keyboard = Util.inputInt();
        }
        
        return keyboard;
        
    }
    
    // 상세메뉴 화면
    int detailMenu() {
    	
        System.out.println("======================================");
        System.out.println("이용하실 메뉴를 선택하세요");
        System.out.printf("%d.회원생성 \n%d.회원검색 \n%d.회원삭제 \n%d.회원수정 \n%d.모든 회원검색 \n%d.종료 \n",
        		Util.INSERT,Util.SEARCH,Util.DELETE,Util.MODIFY,Util.SEARCH_ALL,Util.QUIT);
        System.out.println("======================================");
        
        int keyboard = Util.inputInt();
        
        return keyboard;
    }
    
	// 회원가입 메서드
	void createAccount() {
		
        //현재 입력 스캐너의 버퍼를 삭제
        Util.keyboard.nextLine();
        
        //사용자의 입력 데이터 임시 저장
        String id = null;
        String password = null;
        String name = null;
        String phoneNumber = null;
        String phoneNumber2 = null;
        
        
        System.out.println("회원님의 정보를 입력해주세요.");
        System.out.print("ID :");
        id = Util.keyboard.nextLine();
        // ID 길이가 5자 미만일 경우
        if(id.length()<5) {
        	System.out.println("5자 이상 입력해주세요.");
        	createAccount();
        }
  
        while(true) {
        System.out.print("PW :");
        password = Util.keyboard.nextLine();

            if(checkPassword(password) == 1) {
            	System.out.println("9자 이상 입력해주세요.");
            	continue;
            }
            else if(checkPassword(password) != 3) {
            	System.out.println("특수문자 혹은 영어 대문자를 포함해 주세요");
            	continue;
            }
            break;
        }
        
        System.out.print("NAME :");
        name = Util.keyboard.nextLine();
        
        System.out.print("Phone :");
        phoneNumber = Util.keyboard.nextLine();
        //사용자로부터 입력받은 데이터로 인스턴스 생성(객체의 변수에 저장)
        
        System.out.println("회원가입이 완료 되었습니다.");
        members.add(new AccountInfo(id, password, name, phoneNumber));
        
	}
	
	// 패스워드 조합 규칙
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
		} else 
				isCheck = 1;
		
		return isCheck;
	}
	
	
    // 로그인
    void accountLogin() {
        //현재 입력 스캐너의 버퍼를 삭제
        Util.keyboard.nextLine();

        String id = null;
        String password = null;
        
        System.out.println("로그인 정보를 입력해주세요.");
        while(true) {        
            System.out.print("ID :");
            id = Util.keyboard.nextLine();
            
            System.out.print("PW :");
            password = Util.keyboard.nextLine();

            AccountInfo member = IdCheck(id);
            
            if(member == null){
                System.out.println("등록되지 않은 ID입니다.");
                continue;
            }else if(member.getPW().equals(password)) {
                System.out.println("[" + member.getID() + "]님께서 로그인 하셨습니다.");
            }else {
                System.out.println("비밀번호가 틀렸습니다.");
                continue;
            }
            break;
        }
    }
	
    // 로그인 ID 체크(참조형 타입)
    AccountInfo IdCheck(String id) {
        for(AccountInfo idcheck : members) {
            if(idcheck.getID().equals(id)) {
                return idcheck;
            }
        }
        return null;
    }
    
	// 회원정보 수정
	void updateAccount() {
	}
	
	// 회원정보 삭제
	void deleteAccount() {
		
        Util.keyboard.nextLine();
        System.out.println("삭제하실 회원님의 ID을 입력하세요. :");
        String id = Util.keyboard.nextLine();
        
        AccountInfo member = IdCheck(id);

        if(member==null) {
        	System.out.println("등록되지 않은 ID입니다.");
        }
        
        for(int i=0; i<members.size(); i++) {
        	if(members.get(i).getID().equals(id)) {
        		members.remove(i);
                System.out.println("[삭제정보 안내]");
                System.out.println("삭제된 ID : "+ id);
                break;
        	} 
        }
	}
	
	// 회원정보 검색
	void searchAccount() {
		
        Util.keyboard.nextLine();
        System.out.println("검색하실 회원님의 ID을 입력하세요. ");
        String id = Util.keyboard.nextLine();
        
        AccountInfo member = IdCheck(id);
        
        if(member == null){
            System.out.println("등록된 ID가 없습니다.");
        } else {
        System.out.println("[회원정보 안내]");
        System.out.println("ID : "+ member.getID());
        System.out.println("PW : "+ member.getPW());
        System.out.println("NAME : "+ member.getName());
        System.out.println("Phone : "+ member.getPhoneNumber());
	
        }
	}

	// 모든 회원 검색
	void showAll() {
		for(AccountInfo info : members) {
			System.out.println("ID : "+info.getID()+"\tPW : "+info.getPW()+"\tNAME : "+info.getName()+"\tPhone : "+info.getPhoneNumber());
		}
//		for(int i=0; i<members.size(); i++) {
//			System.out.println("ID :"+ members.get(i).getID());
//		}
	}
	
}
