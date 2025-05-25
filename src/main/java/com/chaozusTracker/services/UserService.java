package com.chaozusTracker.services;

import com.chaozusTracker.dto.ApiDelivery;
import com.chaozusTracker.dto.LoginResponse;
import com.chaozusTracker.models.userRelated.UserProfileRelated.UserProfile;
import com.chaozusTracker.models.userRelated.Users;
import com.chaozusTracker.repository.userRelated.UsersRepository;
import com.chaozusTracker.repository.userRelated.userProfileRelated.UserProfileRepository;
import com.chaozusTracker.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<Users> getAllUsers() {
        return this.usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return this.usersRepository.findById(id);
    }

    public Optional<Users> getUserByEmail (String email){
        return this.usersRepository.findByEmail(email);
    }

    public void deleteUserById(Long id){
        Users userOptional = this.usersRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " +id));
        this.usersRepository.deleteById(id);
    }

    public Users updateUsers (Users user, Long id){
        Users userOptional = this.usersRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario  no encontrado con ID: "+id));

        userOptional.setFirstName(user.getFirstName());
        userOptional.setLastName(user.getLastName());
        userOptional.setUserName(user.getUserName());
        userOptional.setEmail(user.getEmail());

        if(user.getPassword() !=null && !user.getPassword().isEmpty()) {
            userOptional.setPassword(user.getPassword());
        }

        return this.usersRepository.save(user);
    }

    public Users createUser(Users user) {
        if (this.usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Usuario ya esxiste");
        }
        Users newUser = new Users();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        newUser.setFavorito(new HashSet<>());

        Users savedUser = this.usersRepository.save(newUser);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(savedUser.getUserName());
        userProfile.setUser(savedUser);
        userProfile.setDescription("Sin descripción");
        userProfile.setFavoritos(new ArrayList<>());
        this.userProfileRepository.save(userProfile);

        savedUser.setUserProfile(userProfile);
        this.usersRepository.save(savedUser);

        return savedUser;
    }

    public ApiDelivery<LoginResponse> login(String email, String password) {
        Optional<Users> optionalUser = this.usersRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            return new ApiDelivery<>("User not found", false, 404, null, "not found");
        }

        Users user = optionalUser.get();
        if(!this.passwordEncoder.matches(password, user.getPassword())){
            return new ApiDelivery<>("Password Incorrect", false, 400, null, "password incorrect");
        }
        else{
            String token = this.createToken(email);
            LoginResponse login = new LoginResponse(user, token);
            return new ApiDelivery<>("Login success", true, 200, login, "login success" );
        }
    }

    public String createToken(String email) {
        return this.jwtUtil.generateToken(email);
    }

    public UserProfile getUserProfileByUserId (Long userId) {
        return userProfileRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Perfil de usuario no encontrado"));
    }

}
