package Provider_Server_1101.controller;

import Provider_Server_1101.entity.CommonResult;
import Provider_Server_1101.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user1")
public class User1Controller {

    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId")  Integer userId){
        //模拟返回业务数据
        return new CommonResult(200,"success",new User(userId,"张三","123"));
    }
}
