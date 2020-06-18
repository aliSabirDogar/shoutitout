package food2you.hp.shoitout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class event {

    @SerializedName("full_name")
    @Expose
    private String fullname;



    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("event_date")
    @Expose
    private String eventdate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("event_banner")
    @Expose
    private String eventbanner;

    @SerializedName("no_of_people")
    @Expose
    private Integer noofpeople;
    @SerializedName("ticket_price")
    @Expose
    private Integer ticketprice;

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose
    private Integer id;












    public event(String fullname, String email, String eventdate, String description, String eventbanner, Integer noofpeople, Integer ticketprice) {
        this.fullname = fullname;
        this.email = email;
        this.eventdate = eventdate;
        this.description = description;
        this.eventbanner = eventbanner;
        this.noofpeople = noofpeople;
        this.ticketprice = ticketprice;

    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventbanner() {
        return eventbanner;
    }

    public void setEventbanner(String eventbanner) {
        this.eventbanner = eventbanner;
    }

    public Integer getNoofpeople() {
        return noofpeople;
    }

    public void setNoofpeople(Integer noofpeople) {
        this.noofpeople = noofpeople;
    }

    public Integer getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(Integer ticketprice) {
        this.ticketprice = ticketprice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
