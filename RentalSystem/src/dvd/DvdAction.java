package dvd;

public class DvdAction extends DvdInfo {
	
	String type;

	public DvdAction(String dName, String dUnique,String type,int grade) {
		super(dName, dUnique,grade);
		this.type = type;
	}

	@Override
	public void showData() {
		super.showData();
		System.out.println("장르:"+type);
	}

	}
