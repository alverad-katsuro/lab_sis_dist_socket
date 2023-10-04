package alverad.com.br.lab_sis_dist.tcp.alguma_bib.request;

import alverad.com.br.lab_sis_dist.tcp.alguma_bib.client.enumeration.FuncoesAvaliable;

public interface TcpRequest <T> {

    FuncoesAvaliable getMethod();

    T getData();

}
