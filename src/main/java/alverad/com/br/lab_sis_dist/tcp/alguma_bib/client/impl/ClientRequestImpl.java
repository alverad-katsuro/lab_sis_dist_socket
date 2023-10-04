package alverad.com.br.lab_sis_dist.tcp.alguma_bib.client.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import alverad.com.br.lab_sis_dist.tcp.alguma_bib.client.ClientRequest;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.config.ConfigClientRequest;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.request.TcpRequest;

public class ClientRequestImpl implements ClientRequest {

    private static ClientRequestImpl clientRequestImpl;

    private ObjectMapper objectMapper;

    private ConfigClientRequest configClientRequest;

    private ModelMapper mapper;

    private ClientRequestImpl() {
    }

    public static synchronized ClientRequestImpl getInstance() {
        if (clientRequestImpl == null) {
            clientRequestImpl = new ClientRequestImpl();
        }

        return clientRequestImpl;
    }

    @Override
    public void init(ConfigClientRequest configClientRequest) {
        this.configClientRequest = configClientRequest;
        this.mapper = new ModelMapper();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T, R> R execute(TcpRequest<T> tcpRequest, Class<R> typeReturn) throws UnknownHostException, IOException {
        if (configClientRequest == null) {
            throw new RuntimeException("Não foi inicializado o client");
        }

        Socket clientSocket = new Socket(configClientRequest.url(), configClientRequest.port());
        DataOutputStream sentToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader reciveFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentToServer.writeBytes(objectMapper.writeValueAsString(tcpRequest) + "\n");

        final R result = mapper.map(reciveFromServer.readLine(), typeReturn);

        clientSocket.close();

        return result;
    }

}
