package lt.vtmc.GintautasButkus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.exceptions.NoCategoryExistsException;
import lt.vtmc.GintautasButkus.models.BooksCategory;
import lt.vtmc.GintautasButkus.repositories.BooksCategoryRepository;

@Service
public class BooksCategoryService {
	
	@Autowired
	private BooksCategoryRepository booksCategoryRepository;
	
	public void addCategory(BooksCategory bookCatgeory) {
		booksCategoryRepository.save(bookCatgeory);
	}
	
	public void deleteCategory(Long id) {
		if (booksCategoryRepository.existsById(id)) {
			booksCategoryRepository.findAll().stream().filter(category -> category.getId() == id).findFirst().get();
			booksCategoryRepository.deleteById(id);
		} else {
			throw new NoCategoryExistsException("Sorry, there is no such category.");
		}
	}
	
	public ResponseEntity<BooksCategory> updateCategory(Long id, BooksCategory booksCategoryDetails) {
		BooksCategory booksCategory = booksCategoryRepository.findById(id)
				.orElseThrow(() -> new NoCategoryExistsException("No category found with this ID" + id));
		booksCategory.setCategoryName(booksCategoryDetails.getCategoryName());

		BooksCategory updatedCategory = booksCategoryRepository.save(booksCategory);
		return ResponseEntity.ok(updatedCategory);
	}

}
