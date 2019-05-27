package rental;

public class AccountInfo implements Account {

	
	private String id;
	private String password;
	private String name;
	private String phoneNumber;
	
	String[] info = new String[100];
	
	// 일반회원용 생성자
	AccountInfo(String id, String password, String name, String phoneNumber) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// 회원정보,책정보 저장용도
	AccountInfo(String[] arr) {
		info = arr;
	}
	
    String getID() {
        return id;
    }
 
    String getPW() {
        return password;
    }
    
    String getName() {
        return name;
    }
 
    String getPhoneNumber() {
        return phoneNumber;
    }
    
    
    
	@Override
	public void showInfo() {
		
		System.out.println("ID : "+id+" PW : "+password+" Name : "+name+" PhoneNumber : "+phoneNumber); 
		
	}
	
	
	
}
