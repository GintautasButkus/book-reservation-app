package lt.vtmc.GintautasButkus.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books")
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String booksName;

	@NotBlank
	@Size(min = 13)
	@Size(max = 13)
	private String isbn;
	
	@Column
	private String photo;
	
	@Column
	private int pageAmount;
	
	@Column
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private BooksCategory booksCategory;
	
	public Books() {}

	public Books(Long id, @NotBlank @Size(max = 20) String booksName,
			@NotBlank @Size(min = 13) @Size(max = 13) String isbn, String photo, int pageAmount, String status,
			BooksCategory booksCategory) {
		this.id = id;
		this.booksName = booksName;
		this.isbn = isbn;
		this.photo = photo;
		this.pageAmount = pageAmount;
		this.status = status;
		this.booksCategory = booksCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBooksName() {
		return booksName;
	}

	public void setBooksName(String booksName) {
		this.booksName = booksName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getPageAmount() {
		return pageAmount;
	}

	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
	}

	public BooksCategory getBooksCategory() {
		return booksCategory;
	}

	public void setBooksCategory(BooksCategory booksCategory) {
		this.booksCategory = booksCategory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	

}
