package Consumer_Server_1200.Controller;

import Consumer_Server_1200.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/cart")
@RefreshScope //开启动态刷新
public class CartController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    //获取配置信息中的name属性值
    @Value("${msg}")
    private String msg;

    @GetMapping("/getUserByName/{userName}")
    public CommonResult getUserByName(@PathVariable("userName")  String userName){
        //通过服务提供者名（provider-server）获取Eureka Server上的元数据
        List<ServiceInstance> instanceList = discoveryClient.getInstances("provider-server");
        //现在，元数据集合中只有一个服务信息
        ServiceInstance instance = instanceList.get(0);
        //使用getForObject方法调用提供者微服务
        CommonResult result = restTemplate.getForObject(
                "http://"+instance.getHost()+":"+instance.getPort()+"/user/getUserByName/"+userName, CommonResult.class);
        return result;
    }
}
