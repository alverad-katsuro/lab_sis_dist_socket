package alverad.com.br.lab_sis_dist.tcp;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import alverad.com.br.lab_sis_dist.tcp.alguma_bib.client.ClientRequest;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.client.impl.ClientRequestImpl;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.config.ConfigClientRequest;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.functions.CalculateClient;

public class TCPClientExample {

    public static void main(String[] args) throws UnknownHostException, IOException {
        final ConfigClientRequest config = new ConfigClientRequest("localhost", 6789);
        final ClientRequest clientRequest = ClientRequestImpl.getInstance();
        clientRequest.init(config);

        final CalculateClient calculateClient = new CalculateClient(clientRequest);

        Integer resultado = calculateClient.soma(10, 20);
        JOptionPane.showMessageDialog(null, String.format("O resultado da soma é = %d", resultado));

    }

}
