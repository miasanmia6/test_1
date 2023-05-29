package Provider_Server_1101.controller;

import Provider_Server_1101.entity.CommonResult;
import Provider_Server_1101.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private List<User> userList = new ArrayList<>();

    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId")  Integer userId){
        //模拟返回业务数据
        return new CommonResult(200,"success(1101)",new User(userId,"张三","123"));
    }

    @PostMapping("/add")
    public CommonResult add(@RequestBody User adduser) {
        userList.add(adduser);
        return new CommonResult(200,"success(1101)",new User(adduser.getUserId(), adduser.getUserName(),adduser.getPassword()));
    }

    @PutMapping("/changeUserById/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId")  Integer userId, @RequestBody User user) {
        User existingUser = getUserById(userId);
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId")  Integer userId) {
        User user = getUserById(userId);
        if (user != null) {
            userList.remove(user);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

