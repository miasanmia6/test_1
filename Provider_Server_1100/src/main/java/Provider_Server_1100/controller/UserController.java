package Provider_Server_1100.controller;

import Provider_Server_1100.entity.CommonResult;
import Provider_Server_1100.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUserByName/{userName}")
    public CommonResult<User> getUserByName(@PathVariable("userName")  String userName){
        //模拟返回业务数据
        return new CommonResult(200,"success",new User(1,userName,"123"));
    }
}
