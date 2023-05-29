package Consumer_Server_1201.feign;

import Consumer_Server_1201.entity.CommonResult;
import Consumer_Server_1201.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("provider-server")
public interface UserFeignClient {
    @GetMapping("/user/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId);

    @PostMapping("/user/add")
    public CommonResult add(@RequestBody User adduser);

    @PutMapping("/user/changeUserById/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId")  Integer userId, @RequestBody User user);

    @DeleteMapping("/user/deleteUserById/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId")  Integer userId);
}



