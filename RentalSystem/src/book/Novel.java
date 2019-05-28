package book;

public class Novel extends BookInfo {

	String type;

	public Novel(String bookName, String writer, String type) {
		super(bookName, writer);
		this.type = type;
	}

	@Override
	public void bookInfo() {
		super.bookInfo();
		System.out.println("책 종류 :" + type);
	}

}