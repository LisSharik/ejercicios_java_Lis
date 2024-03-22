package entity;

import java.text.NumberFormat;

public class Book extends Author {

    private int id;
    private  String title;
    private String publicationYear;

    private double price;
    private String priceFormat;
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    private int id_autor;


    public Book(){}

    public Book(int id, String title, String publicationYear, double price,  NumberFormat numberFormat, int id_autor) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.price = price;
        this.numberFormat = numberFormat;
        this.id_autor = id_autor;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceFormat(String priceFormat) {
        this.priceFormat = priceFormat;
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getPriceFormat(){
        return priceFormat = numberFormat.format(this.price);
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Title: " + title + " - Publication date: " +
                publicationYear + " - Price: " + getPriceFormat() + " - Author name: " + getName();
    }
}
