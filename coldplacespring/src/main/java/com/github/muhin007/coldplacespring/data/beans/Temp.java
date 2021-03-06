package com.github.muhin007.coldplacespring.data.beans;


public class Temp {

    private int id;
    private String city;
    private int temp;
    private String date;

    public int getID() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getTemp() {
        return temp;
    }

    public String getDate() {
        return date;
    }


    public void setID(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return id + " " + city + " " + temp + " " + date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temp temp = (Temp) o;
        return city.equals(temp.city);
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + temp;
        return result;
    }

}