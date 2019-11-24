package hu.bme.aut.android.touristapp.model;

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

    public Content() {
    }

    public Content(String username, String country, String place, String description, int isfavorite, int isvisited) {
        this.username = username;
        this.country = country;
        this.place = place;
        this.description = description;
        this.isfavorite = isfavorite;
        this.isvisited = isvisited;
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
}
