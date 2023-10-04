package alverad.com.br.lab_sis_dist.tcp.calculadora_client.functions;

import java.io.IOException;
import java.net.UnknownHostException;

import alverad.com.br.lab_sis_dist.tcp.calculadora_client.client.ClientRequest;
import alverad.com.br.lab_sis_dist.tcp.calculadora_client.client.enumeration.FuncoesAvaliable;
import alverad.com.br.lab_sis_dist.tcp.calculadora_client.dto.TwoValues;
import alverad.com.br.lab_sis_dist.tcp.calculadora_client.request.TwoValuesRequest;

public class CalculateClient {

    private final ClientRequest clientRequest;

    public CalculateClient(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }

    public Integer soma(int x, int y) throws UnknownHostException, IOException {
        final TwoValuesRequest request = new TwoValuesRequest(new TwoValues(x, y), FuncoesAvaliable.SOMA);
        return clientRequest.execute(request, Integer.class);
    }

    public Integer subtracao(int x, int y) throws UnknownHostException, IOException {
        final TwoValuesRequest request = new TwoValuesRequest(new TwoValues(x, y), FuncoesAvaliable.SUBTRACAO);
        return clientRequest.execute(request, Integer.class);
    }

    public Integer multiplicacao(int x, int y) throws UnknownHostException, IOException {
        final TwoValuesRequest request = new TwoValuesRequest(new TwoValues(x, y), FuncoesAvaliable.MULTIPLICACAO);
        return clientRequest.execute(request, Integer.class);
    }

    public Double divisao(int x, int y) throws UnknownHostException, IOException {
        final TwoValuesRequest request = new TwoValuesRequest(new TwoValues(x, y), FuncoesAvaliable.DIVISAO);
        return clientRequest.execute(request, Double.class);
    }

}
