package Model;

public class CarDetails {
    private int id;
    private int year_of_purchase;
    private int milage;

    public CarDetails() {
    }

    public CarDetails(int id, int milage, int year_of_purchase) {
        this.id = id;
        this.milage = milage;
        this.year_of_purchase = year_of_purchase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public int getYear_of_purchase() {
        return year_of_purchase;
    }

    public void setYear_of_purchase(int year_of_purchase) {
        this.year_of_purchase = year_of_purchase;
    }

    @Override
    public String toString() {
        return "CarDetails{" +
                "id=" + id +
                ", year_of_purchase=" + year_of_purchase +
                ", milage=" + milage +
                '}';
    }
}
