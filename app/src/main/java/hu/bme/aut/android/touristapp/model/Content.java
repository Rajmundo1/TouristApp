package hu.bme.aut.android.touristapp.model;

import java.util.Objects;

import hu.bme.aut.android.touristapp.sqlite.table.MainTable;

public class Content {

    /*_id,
    username,
    country,
    place,
    description,
    isfavorite,
    isvisited*/

    private String username;
    private String country;
    private String place;
    private String description;
    private int isfavorite;
    private int isvisited;
    private int desireToVisit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content)) return false;
        Content content = (Content) o;
        return
                Objects.equals(getUsername(), content.getUsername()) &&
                Objects.equals(getCountry(), content.getCountry()) &&
                Objects.equals(getPlace(), content.getPlace()) &&
                Objects.equals(getDescription(), content.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getCountry(), getPlace(), getDescription(), isfavorite, isvisited, getDesireToVisit());
    }

    public Content() {
    }

    public Content(Content c){
        this.username = c.username;
        this.country = c.country;
        this.place = c.place;
        this.description = c.description;
        this.isfavorite = c.isfavorite;
        this.isvisited = c.isvisited;
        this.desireToVisit = c.desireToVisit;
    }

    public Content(String username, String country, String place, String description, int isfavorite, int isvisited, int desireToVisit) {
        this.username = username;
        this.country = country;
        this.place = place;
        this.description = description;
        this.isfavorite = isfavorite;
        this.isvisited = isvisited;
        this.desireToVisit = desireToVisit;
    }

    public Content(String country, String place, String description, int isfavorite, int isvisited, int desireToVisit) {
        this.username = null;
        this.country = country;
        this.place = place;
        this.description = description;
        this.isfavorite = isfavorite;
        this.isvisited = isvisited;
        this.desireToVisit = desireToVisit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int isIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(int isfavorite) {
        this.isfavorite = isfavorite;
    }

    public int isIsvisited() {
        return isvisited;
    }

    public void setIsvisited(int isvisited) {
        this.isvisited = isvisited;
    }

    public int getDesireToVisit() {
        return desireToVisit;
    }

    public void setDesireToVisit(int desireToVisit) {
        this.desireToVisit = desireToVisit;
    }
}
