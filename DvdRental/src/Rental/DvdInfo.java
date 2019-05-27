package Rental;

public abstract class DvdInfo implements Dvd{
		//1검색,2정보,3추가,4삭제,5종료
		//1검색: (1)dvd 제목과, (2)장르를 선택한다.
		//장르를 액션(action),공포(fear),코믹
		
	String dName;
	String dUnique;
	String rental;
	int grade;
	
	public DvdInfo(String dName, String dUnique,int grade) {
		this.dName = dName;
		this.dUnique= dUnique;
		this.grade=grade;
		this.rental="대여가능";
	}
	
	public void showData() {
		System.out.println("제목: "+dName);
		System.out.println("고유번호: "+dUnique);
		System.out.println("대여여부: "+rental+"합니다.");
		System.out.print("평점은:");
		for(int i=1; i<=grade;i++) {
			System.out.print("★");
		}
		System.out.println();
		
	}

	}
	

	
	

