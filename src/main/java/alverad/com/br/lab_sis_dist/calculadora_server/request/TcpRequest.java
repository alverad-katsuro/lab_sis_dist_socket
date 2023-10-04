package alverad.com.br.lab_sis_dist.calculadora_server.request;

import alverad.com.br.lab_sis_dist.calculadora_server.enumeration.FuncoesAvaliable;

public interface TcpRequest<T> {

    FuncoesAvaliable getMethod();

    T getData();

}
