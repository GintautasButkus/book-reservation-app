package lt.vtmc.GintautasButkus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.vtmc.GintautasButkus.models.Books;
import lt.vtmc.GintautasButkus.models.BooksCategory;
import lt.vtmc.GintautasButkus.payloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.repositories.BooksCategoryRepository;
import lt.vtmc.GintautasButkus.services.AdminService;
import lt.vtmc.GintautasButkus.services.BookAdminService;
import lt.vtmc.GintautasButkus.services.BooksCategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = { "Admin Board" })
@Tag(name = "Admin Board", description = "Books for Admin")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;


	@Autowired
	BooksCategoryRepository booksCategoryRepository;

	@Autowired
	BooksCategoryService booksCategoryService;

	@Autowired
	BookAdminService bookAdminService;

//	**************** USER MANAGEMENT **********************

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return adminService.registerUserOrAdmin(signUpRequest);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/user/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {
		adminService.deleteUser(id);
	}

//	**************** CATEGORY **********************

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/category")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCategory(@RequestBody @Valid BooksCategory bookCategory) {
		booksCategoryService.addCategory(bookCategory);
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/category/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable Long id) {
		booksCategoryService.deleteCategory(id);
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/category/{id}")
	public ResponseEntity<BooksCategory> updateCategory(@PathVariable Long id,
			@RequestBody BooksCategory booksCategory) {
		return booksCategoryService.updateCategory(id, booksCategory);
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/category/{id}")
	public List<BooksCategory> getAllCategories() {
		return booksCategoryService.getAllCategories();
	}

//	****************** BOOKS MANAGEMENT ******************************

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/book/{categoryId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void addBook(@PathVariable Long categoryId, @RequestBody @Valid Books bookDetails) {
		bookAdminService.addBook(categoryId, bookDetails);
	}

//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/books")
	public List<Books> getAllBooks() {
		return bookAdminService.getAllBooks();
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/book/{id}/{categoryId}")
	public ResponseEntity<Books> updateBook(@PathVariable Long id,
			@RequestBody Books book, @PathVariable Long categoryId) {
		return bookAdminService.updateBook(id, book, categoryId);
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/book/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookAdminService.deleteBook(id);
	}
	
	@GetMapping("/book/{id}")
	public Books getBookById(@PathVariable Long id) {
		return bookAdminService.getBookById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PostMapping("/menu/{id}")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addNewMenu(@PathVariable Long id, String menuName, @RequestBody Menu menuDetails) {
//		menuService.addMenu(id, menuName, menuDetails);
//	}
//
////	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@DeleteMapping("/menu/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteMenu(@PathVariable Long id) {
//		menuService.deleteMenu(id);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PutMapping("/menu/{id}")
//	public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @RequestBody Menu menuDetails) {
//		return menuService.updateMenu(id, menuDetails);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PostMapping("/dish/{id}")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addDishToTheMenu(@PathVariable Long id, String dishName, String dishDescription,
//			@RequestBody Dish dishDetails) {
//		dishService.addDish(id, dishName, dishDescription, dishDetails);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@DeleteMapping("/dish/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteDish(@PathVariable Long id) {
//		dishService.deleteDish(id);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PutMapping("/dish/{id}")
//	public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dishDetails) {
//		return dishService.updateDish(id, dishDetails);
//	}
//
//	@GetMapping("/restaurants")
//	public List<Restaurant> getAllRestaurants() {
//		return adminService.getAllRestaurants();
//	}

}
