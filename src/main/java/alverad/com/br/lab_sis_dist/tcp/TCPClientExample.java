package alverad.com.br.lab_sis_dist.tcp;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import alverad.com.br.lab_sis_dist.tcp.calculadora_client.client.ClientRequest;
import alverad.com.br.lab_sis_dist.tcp.calculadora_client.client.impl.ClientRequestImpl;
import alverad.com.br.lab_sis_dist.tcp.calculadora_client.config.ConfigClientRequest;
import alverad.com.br.lab_sis_dist.tcp.calculadora_client.functions.CalculateClient;

public class TCPClientExample {

    public static void main(String[] args) throws UnknownHostException, IOException {
        final ConfigClientRequest config = new ConfigClientRequest("localhost", 6789);
        final ClientRequest clientRequest = ClientRequestImpl.getInstance();
        clientRequest.init(config);

        final CalculateClient calculateClient = new CalculateClient(clientRequest);

        Integer resultado = calculateClient.soma(10, 20);
        JOptionPane.showMessageDialog(null, String.format("O resultado da soma é = %d", resultado));

        Integer resultado2 = calculateClient.subtracao(10, 20);
        JOptionPane.showMessageDialog(null, String.format("O resultado da subtracao é = %d", resultado2));


        Integer resultado3 = calculateClient.multiplicacao(10, 20);
        JOptionPane.showMessageDialog(null, String.format("O resultado da multiplicacao é = %d", resultado3));

        Double resultado4 = calculateClient.divisao(10, 20);
        JOptionPane.showMessageDialog(null, String.format("O resultado da multiplicacao é = %.2f", resultado4));
    }

}
