package realestate;

public class Property {
    private int id;
    private String location;
    private float price;
    private int bedrooms;
    private int bathrooms;
    private int squareFeet;
    private int userId;

    // Constructor
    public Property(int id, String location, float price, int bedrooms, int bathrooms, int squareFeet, int userId) {
        this.id = id;
        this.location = location;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFeet = squareFeet;
        this.userId = userId;
    }

    // Default constructor
    public Property() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public float getPrice() {
        return price;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public int getUserId() {
        return userId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", squareFeet=" + squareFeet +
                ", userId=" + userId +
                '}';
    }
}
