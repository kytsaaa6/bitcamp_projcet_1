package user;

import java.util.ArrayList;

import util.Util;

public class AccountManager {
	

    private ArrayList<AccountInfo> members;
    
    //로그인 중인 아이디 저장 변수 추가
    private String loginId = null;
    
    public String getLoginId() {
    	return loginId;
    }
    public ArrayList<AccountInfo> getMembers() {
    	return members;
    }
    
    //싱글톤 추가
    private AccountManager() {
    	members = new ArrayList<>();
    	members.add(new AccountInfo("admin","admin","abcd@abcd","1111"));
    }
    
    private static AccountManager m = new AccountManager();    
    
    
    public static AccountManager getInstance() {
    	if(m == null) {
    		m = new AccountManager();
    	}
    	return m;
    }
    
    AccountValidateCheck vaild = new AccountValidateCheck();

    // 관리자 계정
//    public AccountManager() {
//    	members.add(new AccountInfo("admin","admin","abcd@abcd","1111"));
//    }
	
    // 메인 화면
    public int insertMenu() {
        
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
        		Util.INSERT,Util.SEARCH,Util.DELETE,Util.MODIFY,Util.SEARCH_ALL,Util.QUIT2);
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
        
        while(true) {
            System.out.println("회원님의 정보를 입력해주세요.");
            System.out.print("ID :");
            id = Util.keyboard.nextLine();
            
            // ID 중복 체크
            if(vaild.IdCheck(id) != null){
                System.out.println("이미 등록된 ID 입니다.");
                continue;
            }
            // ID 길이 체크
            else if(vaild.checkID(id) == 0) {
            	System.out.println("5자 이상 입력해주세요.");
            	continue;
            }

            System.out.print("PW :");
            password = Util.keyboard.nextLine();

            // PW 길이 체크
            if(vaild.checkPassword(password) == 1) {
            	System.out.println("9자 이상 입력해주세요.");
            	continue;
            }
            
            // PW 조합 체크
            else if(vaild.checkPassword(password) != 3) {
            	System.out.println("특수문자 혹은 영어 대문자를 포함해 주세요");
            	continue;	
            }
            
            System.out.print("NAME :");
            name = Util.keyboard.nextLine();
            
            System.out.print("Phone :");
            phoneNumber = Util.keyboard.nextLine();
            //사용자로부터 입력받은 데이터로 인스턴스 생성(객체의 변수에 저장)
            
            System.out.println("회원가입이 완료 되었습니다.");
            members.add(new AccountInfo(id, password, name, phoneNumber));
            
            break;
            
        }
        
	}
	
    // 로그인 메서드
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

            // ID 일치여부 확인
            if(vaild.IdCheck(id) == null){
                System.out.println("등록되지 않은 ID입니다.");
                continue;
            // 저장된 PW 와 입력한 PW 비교
            }else if(vaild.IdCheck(id).getPW().equals(password)) {
                System.out.println("[" + vaild.IdCheck(id).getID() + "]님께서 로그인 하셨습니다.");
                loginId = id;
            }else {
                System.out.println("비밀번호가 틀렸습니다.");
                continue;
            }
            break;
        }
    }
    
    
	// 회원정보 수정
	void updateAccount() {
	}
	
	
	// 회원정보 삭제
	void deleteAccount() {
		
        Util.keyboard.nextLine();
        System.out.println("삭제하실 회원님의 ID을 입력하세요. :");
        String id = Util.keyboard.nextLine();
        
        // ID 일치여부 확인
        if(vaild.IdCheck(id)==null) {
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
            
        AccountInfo search = vaild.IdCheck(id);
        
        // ID 일치여부 확인
        if(search == null){
            System.out.println("등록된 ID가 없습니다.");
        } else {
        System.out.println("[회원정보 안내]");
        System.out.println("ID : "+ search.getID());
        System.out.println("PW : "+ search.getPW());
        System.out.println("NAME : "+ search.getName());
        System.out.println("Phone : "+ search.getPhoneNumber());
	
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
