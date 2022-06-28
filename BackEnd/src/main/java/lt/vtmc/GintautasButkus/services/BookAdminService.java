package lt.vtmc.GintautasButkus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.exceptions.NoBooksException;
import lt.vtmc.GintautasButkus.exceptions.NoCategoryExistsException;
import lt.vtmc.GintautasButkus.models.Books;
import lt.vtmc.GintautasButkus.repositories.BooksRepository;

@Service
public class BookAdminService {
	
	@Autowired
	BooksRepository booksRepository;
	
	public void addBook(Books book) {
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
	

}
