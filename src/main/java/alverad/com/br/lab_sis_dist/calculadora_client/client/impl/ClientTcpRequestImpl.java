package alverad.com.br.lab_sis_dist.calculadora_client.client.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import alverad.com.br.lab_sis_dist.calculadora_client.client.ClientRequest;
import alverad.com.br.lab_sis_dist.calculadora_client.config.ConfigClientRequest;
import alverad.com.br.lab_sis_dist.calculadora_client.request.TcpRequest;

public class ClientTcpRequestImpl implements ClientRequest {

    private static ClientTcpRequestImpl clientTcpRequestImpl;

    private ObjectMapper objectMapper;

    private ConfigClientRequest configClientRequest;

    private ModelMapper mapper;

    private ClientTcpRequestImpl() {
    }

    public static synchronized ClientTcpRequestImpl getInstance() {
        if (clientTcpRequestImpl == null) {
            clientTcpRequestImpl = new ClientTcpRequestImpl();
        }

        return clientTcpRequestImpl;
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
