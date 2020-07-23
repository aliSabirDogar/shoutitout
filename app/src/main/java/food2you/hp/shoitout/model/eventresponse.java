package food2you.hp.shoitout.model;

import com.google.gson.annotations.SerializedName;

public class eventresponse {

    @SerializedName("success")
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    event data;


}
