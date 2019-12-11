package com.mbhit.kyancafe.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbhit.kyancafe.entity.ConfirmationToken;
import com.mbhit.kyancafe.entity.User;
import com.mbhit.kyancafe.repository.ConfirmationRepository;
import com.mbhit.kyancafe.repository.UserRepository;


@RestController
@RequestMapping("/kyancafe")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConfirmationRepository confirmationTokenRepository;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	private String filepath="C:\\Users\\InworthIT-App\\Pictures\\";
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(User user,MultipartFile multipartfile) throws IOException {
		ResponseEntity<String> result;
		User existingUser = userRepository.findByPhoneIgnoreCase(user.getPhone());
		if (existingUser != null) {

			result = ResponseEntity.status(HttpStatus.CONFLICT).body("This Qid already exists!");

		} else {
			user.setPassword(encoder.encode(user.getPassword()));
			String img=filepath+multipartfile.getOriginalFilename();
			File file=new File(img);
			FileOutputStream fout=new FileOutputStream(file);
			fout.write(multipartfile.getBytes());
			fout.close();
			String profilepic=img;
			user.setProfilepic(profilepic);
			userRepository.save(user);
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			confirmationTokenRepository.save(confirmationToken);
			result = ResponseEntity.status(HttpStatus.CREATED).body("message:successfulRegisteration!");

		}
		

		return result;
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		ResponseEntity<String> result;
		if (token != null) {
			User user = userRepository.findByPhoneIgnoreCase(token.getUser().getPhone());
			user.setEnabled(true);
			userRepository.save(user);
			result = ResponseEntity.status(HttpStatus.CREATED).body("message:successfulRegisteration!");
		} else {
			result = ResponseEntity.status(HttpStatus.CREATED).body("message:invalid otp!");
		}

		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginUser(User user) {
		ResponseEntity<String> result = null;
		User existingUser = userRepository.findByPhoneIgnoreCase(user.getPhone());
		if (existingUser != null) {

			if (encoder.matches(user.getPassword(), existingUser.getPassword())) {
				result = ResponseEntity.status(HttpStatus.OK).body("message:Successfully logged in!");

			} else {
				result = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("message:Incorrect password. Try again.");

			}
		}

		return result;
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public ResponseEntity<String> forgotUserPassword(User user)  {
		ResponseEntity<String> result;

		User existingUser = userRepository.findByPhoneIgnoreCase(user.getPhone());
		if (existingUser != null) {
			ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
			confirmationTokenRepository.save(confirmationToken);
			
			result = ResponseEntity.status(HttpStatus.OK)
					.body("message:Request to reset password received. Check your inbox for the reset link.");

		} else {
			result = ResponseEntity.status(HttpStatus.NOT_FOUND).body("message:This email does not exist!");

		}

		return result;
	}
	@RequestMapping(value = "/confirm-reset", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> validateResetToken(@RequestParam("token") String confirmationToken) {
		ResponseEntity<String> result;

		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			User user = userRepository.findByPhoneIgnoreCase(token.getUser().getPhone());
			user.setEnabled(true);
			userRepository.save(user);

			result = ResponseEntity.status(HttpStatus.OK).body(user.getPhone());

		} else {
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message:The link is invalid or broken!");

		}

		return result;
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public ResponseEntity<String> resetUserPassword(User user) {
		ResponseEntity<String> result;

		if (user.getPhone() != null) {

			User tokenUser = userRepository.findByPhoneIgnoreCase(user.getPhone());
			tokenUser.setEnabled(true);
			tokenUser.setPassword(encoder.encode(user.getPassword()));

			userRepository.save(tokenUser);

			result = ResponseEntity.status(HttpStatus.OK)
					.body("message:assword successfully reset. You can now log in with the new credentials.");

		} else {
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message:The link is invalid or broken!");
		}

		return result;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ConfirmationRepository getConfirmationTokenRepository() {
		return confirmationTokenRepository;
	}

	public void setConfirmationTokenRepository(ConfirmationRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}
}