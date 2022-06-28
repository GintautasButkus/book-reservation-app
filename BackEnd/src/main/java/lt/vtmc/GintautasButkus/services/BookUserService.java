package lt.vtmc.GintautasButkus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.exceptions.NoBooksException;
import lt.vtmc.GintautasButkus.models.Books;
import lt.vtmc.GintautasButkus.models.EBookStatus;
import lt.vtmc.GintautasButkus.models.FavoriteBooks;
import lt.vtmc.GintautasButkus.repositories.BooksRepository;
import lt.vtmc.GintautasButkus.repositories.FavoriteBooksRepository;

@Service
public class BookUserService {
	
	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private FavoriteBooksRepository favoriteBooksRepository;
	
	public List<Books> getBookByName(String name) {
		if (booksRepository.findAll().stream().filter(book -> book.getBooksName().contains(name)).findFirst().isPresent()) {
			return booksRepository.findAll().stream().filter(book -> book.getBooksName().contains(name)).collect(Collectors.toList());
		} else {
			throw new NoBooksException("No book exists with name " + name);
		}
	}
	
	public ResponseEntity<Books> reserveBook(Long bookId) {
		Books book = booksRepository.findById(bookId)
				.orElseThrow(() -> new NoBooksException("No book found with this ID" + bookId));
		book.setStatus(EBookStatus.BOOK_RESERVED.name());

		Books updatedBook = booksRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}
	
	public void favoriteBook(Long bookId, FavoriteBooks favBook) {
		FavoriteBooks favorite = booksRepository.findById(bookId).map(book -> {
			favBook.setBook(book);
			return favoriteBooksRepository.save(favBook);
		}).orElseThrow(() -> new NoBooksException("No books exists with such ID " + bookId));
		favoriteBooksRepository.save(favorite);
	}
	

}
