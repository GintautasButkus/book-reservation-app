package lt.vtmc.GintautasButkus.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.models.ERole;
import lt.vtmc.GintautasButkus.models.Role;
import lt.vtmc.GintautasButkus.models.User;
import lt.vtmc.GintautasButkus.payloadRequest.LoginRequest;
import lt.vtmc.GintautasButkus.payloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.payloadResponse.MessageResponse;
import lt.vtmc.GintautasButkus.payloadResponse.UserInfoResponse;
import lt.vtmc.GintautasButkus.repositories.RoleRepository;
import lt.vtmc.GintautasButkus.repositories.UserRepository;
import lt.vtmc.GintautasButkus.security.jwt.JwtUtils;
import lt.vtmc.GintautasButkus.security.services.UserDetailsImpl;



@Service
public class UserService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder encoder;
	
	
	
//	********************** REGISTER NEW USER [NEW ADMIN REGISTRY DISALLOWED, ONLY USER ***************************************
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail()).booleanValue()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null || signUpRequest.getRole().contains("admin")) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "":
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);

					break;
				

				default:
					Role userDefaultRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userDefaultRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


//	*********************** LOGIN ************************************************

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
				new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}
	

//	******************* GET LOGGED-in USER ID ***********************************************
	public String getUsername() {
		String username = null;
		Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (authentication instanceof UserDetails) {
			username = ((UserDetails) authentication).getUsername();
		} else {
			username = authentication.toString();
		}
		return username;
	}
	

}
