package dvd;

import java.util.*;

public class DvdManager implements DvdManagement {

	public static Scanner choice = new Scanner(System.in);

	String dvdList[] = { "Action", "Fear", "Comedy" };
	int uiqueNum = 0;
	private ArrayList<DvdInfo> dvd;// dvd목록 배열
	private ArrayList<DvdInfo> sr;//검색결과 배열
	
	public ArrayList<DvdInfo> getDvd(){
		return dvd;
	}
	
	public ArrayList<DvdInfo> getSearchResult(){
		return sr;
	}
	
	//싱글톤 추가
	private DvdManager() {
		dvd = new ArrayList<DvdInfo>(30);// dvd목록 배열
		sr = new ArrayList<DvdInfo>();//검색결과 배열
	}
	
	private static DvdManager m = new DvdManager();
	
	public static DvdManager getInstance() {
		if(m == null) {
			m = new DvdManager();
		}
		return m;
	}
	
	
	
	
	int select; //예외처리할 변수
	int swNum = 0;//// 검색메뉴에 처음 접근하는지 구별하는 숫자: 0이면 검색메뉴로 처음 접근 1이면 삭제메뉴로 접근
	String dvdName = "";  //dvd이름 저장할 변수
	

	public int printMenu() { //메뉴 메서드
		System.out.println("=======선택=======");
		System.out.println("메뉴를 선택해 주세요.");
		System.out.printf("%d.dvd등록%n%d.dvd삭제%n%d.dvd검색%n%d.전체정보%n%d.종료%n", Menu.INSERT, Menu.DELET, Menu.SERCH,
				Menu.ALLLIST, Menu.QUIT);
		System.out.println("=================");
		// 예외처리 메서드호출
		inputException();
		choice.nextLine();
				
		
		return select;
	}

	private void inputException() {// 메뉴 예외처리 메서드
		try {
			select = choice.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("잘못입력하셨습니다. 다시입력하세요.");
			select = 0;
			return;	
		}
	}
	private void inputException2() {// 메뉴 예외처리 메서드
		try {
			select = choice.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("잘못입력하셨습니다. 다시입력하세요.");
			select = 0;
			insertDvd();
			select = choice.nextInt();
		}
			return;	
	}


	@Override
	public void insertDvd() { //dvd입력 메서드
		System.out.println("dvd 종류를 선택해주세요.");
		System.out.println("===================");
		for (int i = 1; i <= dvdList.length; i++) {// 고유번호 배열for문
			System.out.printf("%d. %s%n", i, dvdList[i - 1]);// 0부터시작해서 -1
		}
		int select = choice.nextInt();
		choice.nextLine();
		
			
		System.out.println("===================");
		System.out.println("dvd이름을 입력해주세요.");
		String dName = choice.nextLine();

		System.out.println("평점을 입력하세요(1~5점)");

		int dGrade=choice.nextInt();
		for(int i=1; i<=dGrade;i++) {
			System.out.print("★");
		}
			
			
			
		System.out.println();
		String type = dvdList[select - 1];// type 에 고유번호 저장

		String uNum = "DVD" + type + uiqueNum++;// ++고유번호+1..자동생성
		if (select == 1) {//장르별 저장 
			dvd.add(new DvdAction(dName, uNum, type,dGrade));
		} else if (select == 2) {
			dvd.add(new DvdComedy(dName, uNum, type,dGrade));
		} else if (select == 3) {
			dvd.add(new DvdFear(dName, uNum, type,dGrade));
		} 
		System.out.println("dvd가 저장되었습니다.");
		System.out.println("====================");
		}
	
		

	@Override
	public void deletDvd() {//삭제 메서드
		searchDvd();
		if (sr.size() < 1) {
			swNum = 0;
			return;
		
		}
		System.out.println("삭제할 번호를 선택해주세요.");
		int select = choice.nextInt();
			
		dvd.remove(sr.get(select - 1));// 삭제 remove
		System.out.println("선택하신 dvd가 삭제되었습니다.");
		System.out.println("1.계속삭제   2.종료(메뉴)	");
		select = choice.nextInt();
		if (select == 1) {
			swNum = 1;
			deletDvd();
		} else if (select == 2) {
			System.out.println("종료합니다.");
			swNum = 0;
		}

	}

	@Override
	public void searchDvd() { //검색 메서드
		sr.clear();
		
		if (swNum == 0) {
			System.out.println("dvd이름을 검색해주세요");
			dvdName = choice.nextLine();   //입력한 문자열을 dvdName에저장
		}
		int j = 1;
		for (int i = 0; i < dvd.size(); i++) {
			if (dvd.get(i).dName.equals(dvdName)) {
				System.out.println("======[" + j++ + "]=====");
				dvd.get(i).showData();
				sr.add(dvd.get(i)); // 고유넘버
			}
		}
		if (sr.size() < 1) {
			System.out.println("검색하신 dvd가 없습니다.");
			System.out.println("====================");
			return;
		}
	}
	@Override
	public void allListDvd() {//전체정보 메서드
		for (int i = 0; i < dvd.size(); i++) {
			System.out.println("=====[" + (i + 1) + "]=====");
			dvd.get(i).showData();
		}
	}
}
