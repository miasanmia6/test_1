package Gateway_Server_1400.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器
 *
 */
@Component
public class LoginFilter implements GlobalFilter {
    /**
     * 执行过滤器中的业务逻辑。
     * ServerWebExchange：相当于请求响应的上下文。
     * GatewayFilterChain：网关过滤的链表，用于过滤器的链式调用。
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求参数
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(token==null) {
            //响应HTTP状态码（401：没有访问权限）
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //请求结束
            return exchange.getResponse().setComplete();
        }
        //继续执行过滤器链中的下一个资源
        return chain.filter(exchange);
    }
}