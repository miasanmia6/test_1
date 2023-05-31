package Gateway_Server_1400.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * �Զ���ȫ�ֹ�����
 *
 */
@Component
public class LoginFilter implements GlobalFilter {
    /**
     * ִ�й������е�ҵ���߼���
     * ServerWebExchange���൱��������Ӧ�������ġ�
     * GatewayFilterChain�����ع��˵��������ڹ���������ʽ���á�
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //��ȡ�������
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(token==null) {
            //��ӦHTTP״̬�루401��û�з���Ȩ�ޣ�
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //�������
            return exchange.getResponse().setComplete();
        }
        //����ִ�й��������е���һ����Դ
        return chain.filter(exchange);
    }
}