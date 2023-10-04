package alverad.com.br.lab_sis_dist.calculadora_client.dto;

public class TwoValues {

    private Integer x;

    private Integer y;

    public TwoValues() {
    }

    public TwoValues(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}
