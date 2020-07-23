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







//Integer.valueOf(id),String_name, String_email, String_date,String_description,Image_AWS_URL,Integer.valueOf(String_people),Integer.valueOf(String_price)

    public event(int id,String fullname, String email, String eventdate, String description, String eventbanner, Integer noofpeople, Integer ticketprice) {
    this.id= id;
    this.fullname = fullname;
        this.email = email;
        this.eventdate = eventdate;
        this.description = description;
        this.eventbanner = eventbanner;
        this.noofpeople = noofpeople;
        this.ticketprice = ticketprice;

    }



    public event(String fullname, String email, String eventdate, String description, String eventbanner, Integer noofpeople, Integer ticketprice) {
        this.fullname = fullname;
        this.email = email;
        this.eventdate = eventdate;
        this.description = description;
        this.eventbanner = eventbanner;
        this.noofpeople = noofpeople;
        this.ticketprice = ticketprice;

    }



}
