package models;
public class Business {
    private String id;
    private String name;
    private String address;
    private String city;
    private String state;

    public Business(String id, String name, String address, String city, String state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Business [ID=" + id + ", Name=" + name + ", Address=" + address + ", City=" + city + ", State=" + state + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return id.equals(business.id);
    }

    /*@Override
    public int hashCode() {
        return id.hashCode();
    }*/
}
