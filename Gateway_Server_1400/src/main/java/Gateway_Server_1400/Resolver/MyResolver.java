package Gateway_Server_1400.Resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Component
public class MyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        System.out.println("----remoteAddress : "+remoteAddress);
        InetAddress address = remoteAddress.getAddress();
        System.out.println("address : "+address);
        String hostAddress = address.getHostAddress();
        System.out.println("hostAddress : "+hostAddress);
        return Mono.just(hostAddress);
    }

}
