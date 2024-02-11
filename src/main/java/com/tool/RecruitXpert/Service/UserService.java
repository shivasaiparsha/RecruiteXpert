package com.tool.RecruitXpert.Service;

import com.tool.RecruitXpert.DTO.UserDTO.SignUserDto;
import com.tool.RecruitXpert.DTO.UserDTO.UpdateUserStatus;
import com.tool.RecruitXpert.DTO.UserDTO.UserRequest;
import com.tool.RecruitXpert.DTO.UserDTO.UserResponse;
import com.tool.RecruitXpert.Entities.Recruiter;
import com.tool.RecruitXpert.Entities.User;
import com.tool.RecruitXpert.Enums.Status;
import com.tool.RecruitXpert.Exceptions.UserNotFoundException;
import com.tool.RecruitXpert.Repository.UserRepository;
import com.tool.RecruitXpert.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String signUp(SignUserDto signUp) throws Exception{

//        validation : check unique email
        boolean check = userRepository.existsByEmail(signUp.getEmail());
        if(check) throw new RuntimeException("Email already present");

        User user = new User(signUp.getEmail(), signUp.getPassword());
        userRepository.save(user);
        return "signup successfully";
    }

    public String logIn(SignUserDto login){
        User user = userRepository.findByEmail(login.getEmail()).get();

        if(user.getEmail().equals(login.getEmail()) &&
                user.getPassword().equals(login.getPassword()))
            return "successful login";

        else return "Incorrect organisation, email or password";
    }


    public UserResponse addUser(UserRequest userRequest) {
        User user = UserTransformer.UserRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserTransformer.UserToUserResponse(savedUser);
    }

    public String deleteUser(int id) {
            Optional<User> optionalUser = userRepository.findById(id);
            if(!optionalUser.isPresent()){
                throw new UserNotFoundException("User not Found");
            }
            userRepository.deleteById(id);
            return "User Successfully Deleted";
    }

    public String updateUserStatus(UpdateUserStatus updateUserStatus) {
        Optional<User> optionalUser = userRepository.findById(updateUserStatus.getId());
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User not Found");
        }
           User user = new User();
           user.setStatus(updateUserStatus.getStatus());
           userRepository.save(user);
           return "Status Updated Succesfully";
    }

    public String updateUser(UserRequest userRequest) {
        User user = UserTransformer.UserRequestToUser(userRequest);
        userRepository.save(user);
        return "Updated Successfully !!";

    }

    public List<User> getListForNullStatus() {
        List<User> list = userRepository.findAll();
        List<User> ans = new ArrayList<>();

        for(User user : list){
            if(user.getStatus() == null)
                ans.add(user);
        }
        return ans;
    }

    public String updateStatus(UpdateUserStatus update) {
        Optional<User> op = userRepository.findById(update.getId());
        if(!op.isPresent()) throw new RuntimeException("please update correct recruiter");

        User user = op.get();
        user.setStatus(update.getStatus());
        userRepository.save(user);
        return "Status updated successful";
    }

    public List<User> getApprovedList() {

        List<User> list = userRepository.findAll();
        List<User> ans = new ArrayList<>();

        for(User user : list){
            if(user.getStatus()!=null && user.getStatus().equals(Status.APPROVED))
                ans.add(user);

        }
        return ans;
    }
}
