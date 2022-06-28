package lt.vtmc.GintautasButkus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.exceptions.NoBooksException;
import lt.vtmc.GintautasButkus.models.Books;
import lt.vtmc.GintautasButkus.models.EBookStatus;
import lt.vtmc.GintautasButkus.repositories.BooksCategoryRepository;
import lt.vtmc.GintautasButkus.repositories.BooksRepository;

@Service
public class BookAdminService {

	@Autowired
	BooksRepository booksRepository;

	@Autowired
	BooksCategoryRepository bookCategoryRepository;

	public List<Books> getAllBooks() {
		return booksRepository.findAll();
	}

	public void addBook(Long categoryId, Books bookDetails) {
		Books book = bookCategoryRepository.findById(categoryId).map(category -> {
			bookDetails.setStatus(EBookStatus.BOOK_NOT_RESERVED.name());
			bookDetails.setBooksCategory(category);
			return booksRepository.save(bookDetails);
		}).orElseThrow(() -> new NoBooksException("No books exists with such ID " + categoryId));
		booksRepository.save(book);
	}

	public void deleteBook(Long id) {
		if (booksRepository.existsById(id)) {
			booksRepository.findAll().stream().filter(category -> category.getId() == id).findFirst().get();
			booksRepository.deleteById(id);
		} else {
			throw new NoBooksException("Sorry, there is no such book.");
		}
	}

	public ResponseEntity<Books> updateBook(Long id, Books bookDetails, Long booksCategory) {
		Books book = booksRepository.findById(id)
				.orElseThrow(() -> new NoBooksException("No book found with this ID" + id));
		book.setBooksCategory(bookDetails.getBooksCategory());
		book.setBooksName(bookDetails.getBooksName());
		book.setIsbn(bookDetails.getIsbn());
		book.setPageAmount(bookDetails.getPageAmount());
		book.setPhoto(bookDetails.getPhoto());
		book.setStatus(bookDetails.getStatus());
		book.setBooksCategory(bookCategoryRepository.findById(id).get());

		Books updatedBook = booksRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}
	
	public Books getBookById(Long id) {
		return booksRepository.findById(id).get();
	}

}
