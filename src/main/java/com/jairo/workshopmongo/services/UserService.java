package com.jairo.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jairo.workshopmongo.domain.User;
import com.jairo.workshopmongo.dto.UserDTO;
import com.jairo.workshopmongo.repository.UserRepository;
import com.jairo.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);;
	}
	
	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return userRepository.save(newUser);
	}
	
	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		
	}

	public User fromDto(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
