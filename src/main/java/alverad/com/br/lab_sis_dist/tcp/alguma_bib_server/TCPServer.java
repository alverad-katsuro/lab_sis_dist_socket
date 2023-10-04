package alverad.com.br.lab_sis_dist.tcp.alguma_bib_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import alverad.com.br.lab_sis_dist.tcp.alguma_bib.dto.TwoValues;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.request.TcpRequest;
import alverad.com.br.lab_sis_dist.tcp.alguma_bib.request.TwoValuesRequest;

class TCPServer {

    public static void main(String argv[]) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TwoValuesRequest requestRecived;

        String clientSentence;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            requestRecived = objectMapper.readValue(clientSentence, TwoValuesRequest.class);
            Object result = escolheFunc(requestRecived);
            outToClient.writeBytes(objectMapper.writeValueAsString(result) + "\n");
        }
    }

    private static <T> Object escolheFunc(TcpRequest<T> request) {

        switch (request.getMethod()) {
            case SOMA:
                return soma(request);
            case DIVISAO:
                return divisao(request);
            case MULTIPLICACAO:
                return multiplicacao(request);
            case SUBTRACAO:
                return subtracao(request);
            default:
                return "Escolha invalida";
        }

    }

    private static <T> Integer soma(TcpRequest<T> request) {
        if (request instanceof TwoValuesRequest two) {
            final TwoValues data = two.getData();
            return data.getX() + data.getY();
        }
        return null;
    }

    private static <T> Integer subtracao(TcpRequest<T> request) {
        if (request instanceof TwoValuesRequest two) {
            final TwoValues data = two.getData();
            return data.getX() - data.getY();
        }
        return null;
    }

    private static <T> Integer divisao(TcpRequest<T> request) {
        if (request instanceof TwoValuesRequest two) {
            final TwoValues data = two.getData();
            return data.getX() / data.getY();
        }
        return null;
    }

    private static <T> Integer multiplicacao(TcpRequest<T> request) {
        if (request instanceof TwoValuesRequest two) {
            final TwoValues data = two.getData();
            return data.getX() * data.getY();
        }
        return null;
    }
}