package alverad.com.br.lab_sis_dist.calculadora_client.client.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import alverad.com.br.lab_sis_dist.calculadora_client.client.ClientRequest;
import alverad.com.br.lab_sis_dist.calculadora_client.config.ConfigClientRequest;
import alverad.com.br.lab_sis_dist.calculadora_client.request.TcpRequest;

public class ClientUdpRequestImpl implements ClientRequest {

    private static ClientUdpRequestImpl clientUdpRequestImpl;

    private ObjectMapper objectMapper;

    private ConfigClientRequest configClientRequest;

    private ModelMapper mapper;

    private ClientUdpRequestImpl() {
    }

    public static synchronized ClientUdpRequestImpl getInstance() {
        if (clientUdpRequestImpl == null) {
            clientUdpRequestImpl = new ClientUdpRequestImpl();
        }

        return clientUdpRequestImpl;
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
        DatagramSocket clientSocket = new DatagramSocket();

        final String sendData = objectMapper.writeValueAsString(tcpRequest);

        DatagramPacket sendPacket = new DatagramPacket(sendData.getBytes(), sendData.getBytes().length,
                InetAddress.getByName(configClientRequest.url()), configClientRequest.port());

        clientSocket.send(sendPacket);

        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String reciveFromServer = new String(receivePacket.getData(), 0, receivePacket.getLength());

        try {
            final R result = mapper.map(reciveFromServer, typeReturn);

            clientSocket.close();

            return result;

        } catch (Exception e) {
            final R result = objectMapper.readValue(reciveFromServer, typeReturn);

            clientSocket.close();

            return result;
        }
    }

}
