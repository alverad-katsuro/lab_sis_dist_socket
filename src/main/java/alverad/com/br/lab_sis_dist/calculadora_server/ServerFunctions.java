package alverad.com.br.lab_sis_dist.calculadora_server;

import alverad.com.br.lab_sis_dist.calculadora_server.dto.TwoValues;
import alverad.com.br.lab_sis_dist.calculadora_server.request.TcpRequest;
import alverad.com.br.lab_sis_dist.calculadora_server.request.TwoValuesRequest;

public class ServerFunctions {

    public static <T> Object escolheFunc(TcpRequest<T> request) {

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

    private static <T> Double divisao(TcpRequest<T> request) {
        if (request instanceof TwoValuesRequest two) {
            final TwoValues data = two.getData();
            return Double.valueOf(data.getX()) / data.getY();
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
