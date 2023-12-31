package alverad.com.br.lab_sis_dist.calculadora_client.client;

import java.io.IOException;
import java.net.UnknownHostException;

import alverad.com.br.lab_sis_dist.calculadora_client.config.ConfigClientRequest;
import alverad.com.br.lab_sis_dist.calculadora_client.request.TcpRequest;

public interface ClientRequest {

    public void init(ConfigClientRequest configClientRequest);

    public <T, R> R execute(TcpRequest<T> tcpRequest, Class<R> typeReturn) throws UnknownHostException, IOException;
}
