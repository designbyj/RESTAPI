import java.util.List;

public class Root {
    private String name;
    private List<Rates> rates;
    private double value;


    public String getName() {
        return name;
    }

    public List<Rates> getRates() {
        return rates;
    }

    public double getValue() { return value; }

    public void setName(String name) {
        this.name = name;
    }

    public void setRates(List<Rates> rates) {
        this.rates = rates;
    }

    public void setValue(double value) { this.value = value; }


    @Override
    public String toString() {
        return  "Root{" +
                "name=" + name + '\'' +
                ", rates=" + value +
                '}';
    }
}
