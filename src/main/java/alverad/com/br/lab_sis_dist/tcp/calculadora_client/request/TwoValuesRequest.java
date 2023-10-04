package alverad.com.br.lab_sis_dist.tcp.calculadora_client.request;

import alverad.com.br.lab_sis_dist.tcp.calculadora_client.client.enumeration.FuncoesAvaliable;
import alverad.com.br.lab_sis_dist.tcp.calculadora_client.dto.TwoValues;

public class TwoValuesRequest implements TcpRequest<TwoValues> {

    private TwoValues data;

    private FuncoesAvaliable method;

    public TwoValuesRequest() {
    }

    public TwoValuesRequest(TwoValues data, FuncoesAvaliable method) {
        this.data = data;
        this.method = method;
    }

    @Override
    public TwoValues getData() {
        return data;
    }

    @Override
    public FuncoesAvaliable getMethod() {
        return method;
    }

    public void setData(TwoValues data) {
        this.data = data;
    }


    public void setMethod(FuncoesAvaliable method) {
        this.method = method;
    }

}
