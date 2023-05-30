package Consumer_Server_1201.Controller;


import Consumer_Server_1201.entity.CommonResult;
import Consumer_Server_1201.entity.User;
import Consumer_Server_1201.feign.UserFeignClient;
import Consumer_Server_1201.loadbalance.LoadBalancerConfig;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/cart1")
@LoadBalancerClient(value = "provider-server", configuration = LoadBalancerConfig.class)
public class Cart1Controller {
    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    Environment environment;


    //get����
    @LoadBalanced
    @GetMapping("/getUserById/{userId}")
    @RateLimiter(name = "backendA", fallbackMethod = "fallback")
    public CommonResult getUserByName(@PathVariable("userId") Integer userId) {
        System.out.println("���뷽��");
        CommonResult<User> result = userFeignClient.getUserById(userId);
        System.out.println("�뿪����");
        return result;
    }

    public CommonResult<User> fallback(Integer userId, Throwable e) {
        e.printStackTrace();
        System.out.println("fallback�Ѿ�����");
        return new CommonResult<>(400, "��ǰ�û��������������Ժ�����", new User());
    }

    //post����
    @LoadBalanced
    @PostMapping("add")
    public CommonResult addUser(@RequestBody User user) {
        return userFeignClient.add(user);
    }

    //put����
    @LoadBalanced
    @PutMapping("/changeUserById/{userId}")
    @CircuitBreaker(name="backendB",fallbackMethod = "fallback")
    public ResponseEntity<String> updateUser(@PathVariable("userId")  Integer userId, @RequestBody User user) {
        System.out.println("���뷽��");
        try {
            Thread.sleep(10000L);//����10��
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userFeignClient.updateUser(userId,user);
        System.out.println("�뿪����");
        return ResponseEntity.ok("User updated successfully");
    }

    public CompletableFuture<User> fallback(Integer userId,User user, Throwable e){
        e.printStackTrace();
        System.out.println("fallback�Ѿ�����");
        CompletableFuture<User> result = CompletableFuture.supplyAsync(()->{
            return new CommonResult<>(400,"��ǰ�û��������������Ժ�����",new User()).getResult();
        });
        return result;
    }

    //delete����
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
//        //ͨ�������ṩ������provider-server1����ȡEureka Server�ϵ�Ԫ����
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



