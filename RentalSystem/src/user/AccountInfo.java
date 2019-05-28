package user;

import java.util.ArrayList;

public class AccountInfo implements Account {

	
	private String id;
	private String password;
	private String name;
	private String phoneNumber;
	
	private ArrayList<Object> info = new ArrayList<Object>();
	
    public void addInfo(Object a) {
    	info.add(a);
    }
    
    public ArrayList<Object> getinfo(){
    	return info;
    }
    
	// 일반회원용 생성자
	AccountInfo(String id, String password, String name, String phoneNumber) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// 회원정보,책정보 저장용도
	AccountInfo(ArrayList<Object> arr) {
		info = arr;
	}
	
    public String getID() {
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
