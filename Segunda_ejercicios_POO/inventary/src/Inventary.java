import javax.swing.*;
import java.util.ArrayList;

public class Inventary {
    /*Clase Inventario: Utiliza un ArrayList de objetos Producto para gestionar el inventario.
    Implementa métodos para añadir, eliminar, y listar productos, además de buscar productos
    por nombre o categoría.*/
    private ArrayList<SpecificProduct> products = new ArrayList<>();
    private int idProduct = 0;

    public Inventary (){}

    public void addProduct(String nameProduct, Double priceProduct,String category,String brand){
        /*String nameProduct = JOptionPane.showInputDialog("Enter the name of the product");
        Double priceProduct = Double.parseDouble(JOptionPane.showInputDialog("Enter the price of the product"));*/
        this.idProduct++;

        SpecificProduct newProduct = new SpecificProduct(this.idProduct,nameProduct,priceProduct,category,brand);
        this.products.add(newProduct);
        JOptionPane.showMessageDialog(null,"The product " + nameProduct + " successfully added");
    }

    public void listProducts(){
        if (this.products.isEmpty()){
            JOptionPane.showMessageDialog(null,"the list is empty");

        }else {
            String listProducts = "";
            for (Product product :this.products){
                listProducts += "ID: " + product.getId() + " - Name: " + product.getName() + " - Price: " + product.priceFormat() + "\n";
            }
            JOptionPane.showMessageDialog(null,"List of products\n" + listProducts);
        }
    }

    public void deleteProduct(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the product id"));

        if (this.products.removeIf(product -> product.getId() == id)){
            JOptionPane.showMessageDialog(null,"The product has been removed");
        }else {
            JOptionPane.showMessageDialog(null,"The prodcut hasn't been found");
        }
    }


    public String searchProduct(String searchProduct ){
        String productFound = "";

        for (SpecificProduct specificProduct: this.products){

            if (specificProduct.getCategory().equalsIgnoreCase(searchProduct) || specificProduct.getName().equalsIgnoreCase(searchProduct) ){
               productFound += "ID: " + specificProduct.getId() + " - Name: " + specificProduct.getName()
                        +  " - Price: " + specificProduct.priceFormat() + " - Category: " + specificProduct.getCategory() + " - Brand: " + specificProduct.getBrand() + "\n";
            }
        }

        if (productFound.equals("")){
            return productFound = "The prodcut " + searchProduct + " hasn't been found";
        }
        return productFound;

    }





}
