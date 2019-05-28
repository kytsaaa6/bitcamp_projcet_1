package dvd;

public class DvdFear extends DvdInfo {

	String type;

	public DvdFear(String dName, String dUnique, String type,int grade) {
		super(dName, dUnique,grade);
		this.type = type;
	}

	@Override
	public void showData() {
		super.showData();
		System.out.println("장르:" + type);

	}
}
