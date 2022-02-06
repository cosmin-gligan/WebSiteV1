package siit.model;

public class Product {
    private final int id;
    private String name;
    private Number weight;
    private Number price;
    private final String mu = "PCE"; // unitatea de masura, trebuia sa fie in DB (PCE = piece )

    public Product(int id, String name, Number weight, Number price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
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

    public Number getWeight() {
        return weight;
    }

    public void setWeight(Number weight) {
        this.weight = weight;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public String getMu() {
        return mu;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + getName() + '\'' +
                ", weight=" + getWeight() +
                ", price=" + getPrice() +
                ", mu=" + getMu() +
                '}';
    }
}
