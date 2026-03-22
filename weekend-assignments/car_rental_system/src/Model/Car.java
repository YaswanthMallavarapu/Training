package Model;

public class Car {
    private int id;
    private String registration_number;
    private String chasis_number;
    private String Registration_state;
    private String brand;
    private String model;
    private String variant;

    public Car() {
    }

    public Car(String brand, String chasis_number, int id, String model, String registration_number, String registration_state, String variant) {
        this.brand = brand;
        this.chasis_number = chasis_number;
        this.id = id;
        this.model = model;
        this.registration_number = registration_number;
        this.Registration_state = registration_state;
        this.variant = variant;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getChasis_number() {
        return chasis_number;
    }

    public void setChasis_number(String chasis_number) {
        this.chasis_number = chasis_number;
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

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getRegistration_state() {
        return Registration_state;
    }

    public void setRegistration_state(String registration_state) {
        Registration_state = registration_state;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", id=" + id +
                ", registration_number='" + registration_number + '\'' +
                ", chasis_number='" + chasis_number + '\'' +
                ", Registration_state='" + Registration_state + '\'' +
                ", model='" + model + '\'' +
                ", variant='" + variant + '\'' +
                '}';
    }
}
