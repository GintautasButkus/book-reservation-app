package lt.vtmc.GintautasButkus.models;

public enum EBookStatus {
	BOOK_RESERVED("Rezervuota"),
	BOOK_NOT_RESERVED("Nerezervuota");

	 
	private final String label;
	
	private EBookStatus(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
}
