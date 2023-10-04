package alverad.com.br.lab_sis_dist;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.fasterxml.jackson.databind.ObjectMapper;

import alverad.com.br.lab_sis_dist.calculadora_server.ServerFunctions;
import alverad.com.br.lab_sis_dist.calculadora_server.request.TwoValuesRequest;

class UDPServerExample {

    public static void main(String argv[]) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TwoValuesRequest requestRecived;

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        DatagramSocket serverSocket = new DatagramSocket(9876);

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            serverSocket.receive(receivePacket);

            String clientSentence = new String(receivePacket.getData());

            requestRecived = objectMapper.readValue(clientSentence, TwoValuesRequest.class);

            Object result = ServerFunctions.escolheFunc(requestRecived);

            InetAddress IPAddress = receivePacket.getAddress();

            int port = receivePacket.getPort();

            sendData = objectMapper.writeValueAsString(result).getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,
                    port);

            serverSocket.send(sendPacket);

        }
    }

}