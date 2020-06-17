package food2you.hp.shoitout.model;

import com.google.gson.annotations.SerializedName;

public class CreateEventModel {

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(String ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getNo_of_people() {
        return no_of_people;
    }

    public void setNo_of_people(String no_of_people) {
        this.no_of_people = no_of_people;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvent_banner() {
        return event_banner;
    }

    public void setEvent_banner(String event_banner) {
        this.event_banner = event_banner;
    }

    public CreateEventModel(String full_name, String email, String ticket_price, String no_of_people, String event_date, String description, String event_banner) {
        this.full_name = full_name;
        this.email = email;
        this.ticket_price = ticket_price;
        this.no_of_people = no_of_people;
        this.event_date = event_date;
        this.description = description;
        this.event_banner = event_banner;
    }

    @SerializedName("full_name")
    String full_name;
    @SerializedName("email")
    String email;
    @SerializedName("ticket_price")
    String ticket_price;

    @SerializedName("no_of_people")
    String no_of_people;

    @SerializedName("event_date")
    String event_date;
    @SerializedName("description")
    String description;
    @SerializedName("event_banner")
    String event_banner;
}
