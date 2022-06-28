package lt.vtmc.GintautasButkus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.vtmc.GintautasButkus.models.Books;
import lt.vtmc.GintautasButkus.models.FavoriteBooks;
import lt.vtmc.GintautasButkus.payloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.services.BookUserService;
import lt.vtmc.GintautasButkus.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = { "User Board" })
@Tag(name = "User Board", description = "Books for Users")
@RestController
@RequestMapping("/api/auth/user")

public class UserController {
	
	@Autowired
	private BookUserService bookUserService;
	
	@Autowired
	private UserService userService;
	
		
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return userService.registerUser(signUpRequest);
	}
	
	
	
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/book/{nameFragment}")
	public List<Books> getBooksByName(@PathVariable String nameFragment) {
		return bookUserService.getBookByName(nameFragment);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping("/book/{id}")
	public ResponseEntity<Books> reserveBooks(@PathVariable Long id){
		return bookUserService.reserveBook(id);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/book/{bookId}")
	public void favoriteBook(@PathVariable Long bookId, @RequestBody FavoriteBooks favBook) {
		bookUserService.favoriteBook(bookId, favBook);
	}
		
	}
