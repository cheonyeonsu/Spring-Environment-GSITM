package com.mysite.sbb.user;

import java.util.Optional;

import com.mysite.sbb.DataNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public User create(String loginId, String email, String password) {
		User user = new User();
		user.setLoginId(loginId);
		user.setEmail(email);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password)); 
		this.userRepository.save(user);
		return user;
	}

	//userName 알아오기
	public User getUser(String loginId) {
		Optional<User> user = this.userRepository.findByLoginId(loginId);

		if(user.isPresent()) {
			return user.get();
		}else{
			throw new DataNotFoundException("siteuser not found");

		}
	}

}