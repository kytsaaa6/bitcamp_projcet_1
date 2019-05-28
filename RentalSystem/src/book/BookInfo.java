package book;

public abstract class BookInfo implements Book {

	// 책 제목
	// 저자
	// 대여여부

	public String bookName;
	public String writer;
	public String rental;
	
	public String getRental() {
		return rental;
	}

	BookInfo(String bookName, String writer) {
		this.bookName = bookName;
		this.writer = writer;
		this.rental = "대여 가능";

	}

	@Override
	public void bookInfo() {
		System.out.println("책 제목 :" + bookName);
		System.out.println("작가 :" + writer);
		System.out.println("대여 여부 :" + rental);
	}
	
	@Override
	public String toString() {
		bookInfo();
		return "";
	}

}
