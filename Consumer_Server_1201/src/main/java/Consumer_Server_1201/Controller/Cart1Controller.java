package Consumer_Server_1201.Controller;


import Consumer_Server_1201.entity.CommonResult;
import Consumer_Server_1201.entity.User;
import Consumer_Server_1201.feign.UserFeignClient;
import Consumer_Server_1201.loadbalance.LoadBalancerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart1")
@LoadBalancerClient(value = "provider-server", configuration = LoadBalancerConfig.class)
public class Cart1Controller {
    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    Environment environment;


    //get方法
    @LoadBalanced
    @GetMapping("/getUserById/{userId}")
    public CommonResult getUserByName(@PathVariable("userId")  Integer userId) {
        return userFeignClient.getUserById(userId);
    }

    //post方法
    @LoadBalanced
    @PostMapping("add")
    public CommonResult addUser(@RequestBody User user) {
        return userFeignClient.add(user);
    }

    //put方法
    @LoadBalanced
    @PutMapping("/changeUserById/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId")  Integer userId, @RequestBody User user) {
        userFeignClient.updateUser(userId,user);
        return ResponseEntity.ok("User updated successfully");
    }

    //delete方法
    @LoadBalanced
    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId")  Integer userId) {
        userFeignClient.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

}
//@RestController
//@RequestMapping("/cart1")
//public class Cart1Controller {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @GetMapping("/getUserById/{userId}")
//    public ResponseEntity<String> getUser(@PathVariable("userId")  Integer userId) {
//        //通过服务提供者名（provider-server1）获取Eureka Server上的元数据
//        String url = "http://provider-server1:1201/user1/getUserById/"+ userId;
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
//        return responseEntity;
//    }

//    @PostMapping("/user")
//    public ResponseEntity<String> addUser(@RequestBody User user) {
//        String url = "http://producer-service/user";
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, user, String.class);
//        return responseEntity;
//    }
//
//    @PutMapping("/user/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
//        String url = "http://producer-service/user/" + id;
//        restTemplate.put(url, user);
//        return ResponseEntity.ok("User updated successfully");
//    }
//
//    @DeleteMapping("/user/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//        String url = "http://producer-service/user/" + id;
//        restTemplate.delete(url);
//        return ResponseEntity.ok("User deleted successfully");
//    }



