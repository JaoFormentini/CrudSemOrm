package wydem.facimp.sistema.pooJava.projeto;

public class celular {
    private int id;
    private String name;
    private String model;
    private int year;
    private double value;
    private String description;
    private int amount;

    public celular(String name, String model, int year, double value, String description, int amount) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.value = value;
        this.description = description;
        this.amount = amount;
    }

    public celular(int id, String name, String model, int year, double value, String description, int amount) {
        this(name, model, year, value, description, amount);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s %s (%d) | R$%.2f | Qtd: %d | %s",
                id, name, model, year, value, amount, description);
    }

}
