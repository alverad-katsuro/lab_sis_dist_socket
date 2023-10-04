package alverad.com.br.lab_sis_dist;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import alverad.com.br.lab_sis_dist.calculadora_server.ServerFunctions;
import alverad.com.br.lab_sis_dist.calculadora_server.request.TwoValuesRequest;

class TCPServerExample {

    public static void main(String argv[]) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TwoValuesRequest requestRecived;

        String clientSentence;

        ServerSocket sockettt = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = sockettt.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            requestRecived = objectMapper.readValue(clientSentence, TwoValuesRequest.class);
            Object result = ServerFunctions.escolheFunc(requestRecived);
            outToClient.writeBytes(objectMapper.writeValueAsString(result) + "\n");
        }
    }

}