package Consumer_Server_1200.Controller;

import Consumer_Server_1200.entity.CommonResult;
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
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/getUserByName/{userName}")
    public CommonResult getUserByName(@PathVariable("userName")  String userName){
        //ͨ�������ṩ������provider-server����ȡEureka Server�ϵ�Ԫ����
        List<ServiceInstance> instanceList = discoveryClient.getInstances("provider-server");
        //���ڣ�Ԫ���ݼ�����ֻ��һ��������Ϣ
        ServiceInstance instance = instanceList.get(0);
        //ʹ��getForObject���������ṩ��΢����
        CommonResult result = restTemplate.getForObject(
                "http://"+instance.getHost()+":"+instance.getPort()+"/user/getUserByName/"+userName, CommonResult.class);
        return result;
    }
}
