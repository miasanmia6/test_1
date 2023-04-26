package Consumer_Server_1201.Controller;


import Consumer_Server_1201.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart1")
public class Cart1Controller {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/getUserById/{userId}")
    public CommonResult getUserByName(@PathVariable("userId")  Integer userId){
        //通过服务提供者名（provider-server1）获取Eureka Server上的元数据
        List<ServiceInstance> instanceList = discoveryClient.getInstances("provider-server");
        //现在，元数据集合中只有一个服务信息
        ServiceInstance instance = instanceList.get(0);

        //使用getForObject方法调用提供者微服务
        CommonResult result = restTemplate.getForObject(
                "http://"+instance.getHost()+":"+instance.getPort()+"/user1/getUserById/"+userId, CommonResult.class);
        return result;
    }
}



