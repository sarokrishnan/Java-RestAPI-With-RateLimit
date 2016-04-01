package com.saro.challenge.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

/**
 * Created by sbalakrishnan on 3/30/16.
 */
public class HotelVO {

    private String id;
    private String city;
    private int hotelId;
    private String room;
    private int price;
    @ObjectId
    @JsonProperty("_id")
    public String get_id() {
        return id;
    }
    @ObjectId
    @JsonProperty("_id")
    public void set_id(String id) {
        this.id = id;
    }

    @JsonProperty("CITY")
    public String getCity() {
        return city;
    }

    @JsonProperty("CITY")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("HOTELID")
    public int getHotelId() {
        return hotelId;
    }

    @JsonProperty("HOTELID")
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @JsonProperty("ROOM")
    public String getRoom() {
        return room;
    }

    @JsonProperty("ROOM")
    public void setRoom(String room) {
        this.room = room;
    }

    @JsonProperty("PRICE")
    public int getPrice() {
        return price;
    }

    @JsonProperty("PRICE")
    public void setPrice(int price) {
        this.price = price;
    }
}
