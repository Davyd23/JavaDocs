package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

@Table(name="LOCATIONS")
public class Location {
    @Id(name="location_id")
    private long id;
    @Column(name="postal_code")
    private String postalCode;
    @Column(name="street_adress")
    private String streetAddress;
    @Column(name="city")
    private String city;
    @Column(name="state_province")
    private String StateProvince;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return StateProvince;
    }

    public void setStateProvince(String stateProvince) {
        StateProvince = stateProvince;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
