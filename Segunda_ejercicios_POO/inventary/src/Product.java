import java.text.NumberFormat;

public class Product {
    /*Clase Producto: Base para todos los productos, con propiedades como id, nombre, y
    precio. Implementa getters y setters para aplicar el encapsulamiento.*/

    private int id;
    private String name;
    private double price;

    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String priceFormat(){
        return numberFormat.format(this.price);
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
