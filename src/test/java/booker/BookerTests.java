package booker;

import api.BookerApi;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.booker.BookerCreateResponse;
import models.booker.BookerResponse;
import models.booker.PartialBookerResponse;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class BookerTests extends BaseTest {

    private Response response;

    @Test
    public void createBookerTest() {
        var bookerApi = new BookerApi(true);
        final var bookerRequestBody = new BookerResponse();
        response = bookerApi.createBooking(bookerRequestBody);

        final var responseBody = response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchema(getSchema(BookerCreateResponse.schemaJson)))
                .extract().body().as(BookerCreateResponse.class);

        bookerRequestBody.isEqualsTo(responseBody.getBookerResponse());

        final var bookingId = responseBody.getBookingID();

        response = bookerApi.getBooking(Integer.valueOf(bookingId));

        final var responseGetBody = response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchema(getSchema(BookerResponse.schemaJson)))
                .extract().body().as(BookerResponse.class);

        bookerRequestBody.isEqualsTo(responseGetBody);

        final var updateBookerRequestBody = new BookerResponse();

        response = bookerApi.updateBooking(Integer.valueOf(bookingId), updateBookerRequestBody);

        final var responseUpdateBody = response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchema(getSchema(BookerResponse.schemaJson)))
                .extract().body().as(BookerResponse.class);

        updateBookerRequestBody.isEqualsTo(responseUpdateBody);

        response = bookerApi.deleteBooking(Integer.valueOf(bookingId));
    }

    @Test
    public void deleteUnhappy() {
        var bookerApi = new BookerApi(true);
        response = bookerApi.deleteBooking(10000);
        response.then().assertThat()
                .statusCode(405)
                .time(lessThan(3000L));
    }

    @Test
    public void deleteNoAuth() {
        var bookerApi = new BookerApi(true);

        final var bookerRequestBody = new BookerResponse();
        response = bookerApi.createBooking(bookerRequestBody);

        final var responseBody = response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchema(getSchema(BookerCreateResponse.schemaJson)))
                .extract().body().as(BookerCreateResponse.class);

        bookerRequestBody.isEqualsTo(responseBody.getBookerResponse());

        final var bookingId = responseBody.getBookingID();

        bookerApi = new BookerApi(false);

        response = bookerApi.deleteBooking(Integer.valueOf(bookingId));

        final var responseDelete = response.then().assertThat()
                .statusCode(403)
                .time(lessThan(3000L));
    }

    @Test
    public void partialUpdate() {
        var bookerApi = new BookerApi(true);

        final var bookerRequestBody = new BookerResponse();
        response = bookerApi.createBooking(bookerRequestBody);

        final var responseBody = response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchema(getSchema(BookerCreateResponse.schemaJson)))
                .extract().body().as(BookerCreateResponse.class);

        bookerRequestBody.isEqualsTo(responseBody.getBookerResponse());

        final var bookingId = responseBody.getBookingID();

        var partialBookerRequestBody = new PartialBookerResponse();

        response = bookerApi.partialUpdateBooking(Integer.valueOf(bookingId), partialBookerRequestBody);

        final var updatePartial = response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchema(getSchema(BookerResponse.schemaJson)))
                .extract().body().as(BookerResponse.class);

        partialBookerRequestBody.isequalsTo(updatePartial);
    }
}
