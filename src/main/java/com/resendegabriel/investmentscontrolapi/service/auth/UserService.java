package com.resendegabriel.investmentscontrolapi.service.auth;

import com.resendegabriel.investmentscontrolapi.model.auth.User;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserUpdateRequest;
import com.resendegabriel.investmentscontrolapi.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public UserResponse save(UserRequest userRequest) {
        var user = User.fromRequest(userRequest);
        userRepository.save(user);
        return UserResponse.fromEntity(user);
    }

    public UserResponse getById(Long id) {
        var user = findById(id);
        return UserResponse.fromEntity(user);
    }

    public UserResponse update(Long id, UserUpdateRequest userRequest) {
        var user = findById(id);
        user.updateData(userRequest);
        userRepository.save(user);
        return UserResponse.fromEntity(user);
    }

    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhum usuario com id " + id));
    }
}
