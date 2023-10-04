package alverad.com.br.lab_sis_dist.calculadora_client.request;

import alverad.com.br.lab_sis_dist.calculadora_client.client.enumeration.FuncoesAvaliable;

public interface TcpRequest<T> {

    FuncoesAvaliable getMethod();

    T getData();

}
