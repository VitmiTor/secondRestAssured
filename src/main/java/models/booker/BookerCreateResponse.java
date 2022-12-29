package models.booker;

import base.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookerCreateResponse extends BaseModel {
    public static final String schemaJson = "booker/createBookerSchema.json";
    @JsonProperty("bookingid")
    private String bookingID;
    @JsonProperty("booking")
    private BookerResponse bookerResponse;

    public String getBookingID() {
        return bookingID;
    }

    public BookerResponse getBookerResponse() {
        return bookerResponse;
    }
}
