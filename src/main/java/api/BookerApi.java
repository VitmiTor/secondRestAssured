package api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import models.booker.BookerResponse;
import models.booker.PartialBookerResponse;

public class BookerApi extends BaseApi {
    private final String path = "booking";
    private final String keyParameter = "bookerID";
    private final String pathId = String.format("%s/{%s}", path, keyParameter);//booking,{bookinID}

    public BookerApi(boolean isAuth) {
        super(isAuth);
    }

    public Response createBooking(BookerResponse booking) {
        logs.info("Booking Post");
        return setResourcePath(path)
                .setRequestBody(booking)
                .apiCallManager(Method.POST);
    }

    public Response getBooking(int bookingId) {
        logs.info("Booking GET");
        return setPathParameter(keyParameter, String.valueOf(bookingId))
                .setResourcePath(pathId)
                .apiCallManager(Method.GET);
    }

    public Response updateBooking(int bookingId, BookerResponse booking) {
        logs.info("Booking PUT");
        return setPathParameter(keyParameter, String.valueOf(bookingId))
                .setResourcePath(pathId)
                .setRequestBody(booking)
                .apiCallManager(Method.PUT);
    }

    public Response partialUpdateBooking(int bookingId, PartialBookerResponse booking) {
        logs.debug("Booking Patch");
        return setPathParameter(keyParameter, String.valueOf(bookingId))
                .setResourcePath(pathId)
                .setRequestBody(booking)
                .apiCallManager(Method.PATCH);
    }

    public Response deleteBooking(int bookingId) {
        logs.info("Delete Booking");
        return setPathParameter(keyParameter, String.valueOf(bookingId))
                .setResourcePath(pathId)
                .apiCallManager(Method.DELETE);
    }
}
