package alverad.com.br.lab_sis_dist.tcp.alguma_bib.functions;

import java.io.IOException;
import java.net.UnknownHostException;

import alverad.com.br.lab_sis_dist.tcp.alguma_bib.client.ClientRequest;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.client.enumeration.FuncoesAvaliable;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.dto.TwoValues;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.request.TwoValuesRequest;

public class CalculateClient {

    private final ClientRequest clientRequest;

    public CalculateClient(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }

    public Integer soma(int x, int y) throws UnknownHostException, IOException {
        final TwoValuesRequest request = new TwoValuesRequest(new TwoValues(x, y), FuncoesAvaliable.SOMA);
        return clientRequest.execute(request, Integer.class);
    }

}
