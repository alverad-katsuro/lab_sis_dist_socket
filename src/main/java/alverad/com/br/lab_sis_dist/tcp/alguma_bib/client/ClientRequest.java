package alverad.com.br.lab_sis_dist.tcp.alguma_bib.client;

import java.io.IOException;
import java.net.UnknownHostException;

import alverad.com.br.lab_sis_dist.tcp.alguma_bib.config.ConfigClientRequest;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.request.TcpRequest;

public interface ClientRequest {

    public void init(ConfigClientRequest configClientRequest);

    public <T, R> R execute(TcpRequest<T> tcpRequest, Class<R> typeReturn) throws UnknownHostException, IOException;
}
