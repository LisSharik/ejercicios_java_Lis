import javax.swing.*;

public class Menu {
    Inventary objInventary = new Inventary();

    public  void inventaryMenu(){
        boolean flag = true;
        try {
            do {
                String option = JOptionPane.showInputDialog("""
                       =========== Enter an option ===========
                        1. Add product
                        2. Show list products
                        3. Delete a product
                        4. Search product
                        5. Exit
                        """);

                if (option == null){
                    JOptionPane.showMessageDialog(null,"FINALIZED");
                    flag = false;
                }else {

                    switch (option){
                        case "1":
                            String nameProduct = JOptionPane.showInputDialog("Enter the name of product");
                            double priceProduct = Double.parseDouble(JOptionPane.showInputDialog("Enter the price of product"));
                            String categoryProduct = JOptionPane.showInputDialog("Enter the category of product");
                            String brandProduct = JOptionPane.showInputDialog("Enter the product of brand");

                            objInventary.addProduct(nameProduct,priceProduct,categoryProduct,brandProduct);
                            break;

                        case "2":
                            objInventary.listProducts();
                            break;

                        case "3":
                            objInventary.deleteProduct();
                            break;

                        case "4":
                            String searchProduct = JOptionPane.showInputDialog("Enter the category or name of the product to search");

                            JOptionPane.showMessageDialog(null,objInventary.searchProduct(searchProduct));
                            break;

                        case "5":
                            flag = false;
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,"Invalid Option");
                            break;

                    }
                }



            }while (flag == true);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error");
        }
    }




}
