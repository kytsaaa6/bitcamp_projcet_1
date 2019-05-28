package book;

public class ComicBook extends BookInfo {

	String type;

	public ComicBook(String bookName, String writer, String type) {
		super(bookName, writer);
		this.type = type;
	}

	@Override
	public void bookInfo() {
		super.bookInfo();
		System.out.println("책 종류 :" + type);

	}

}
