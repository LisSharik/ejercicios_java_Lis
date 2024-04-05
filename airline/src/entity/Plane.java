package entity;

public class Plane {
    private int id;
    private String model;
    private int capacity;

    public Plane() {}

    public Plane( String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Model: " + model + " - Capacity: " + capacity;
    }
}
