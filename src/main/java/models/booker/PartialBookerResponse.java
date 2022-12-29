package models.booker;

import base.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import org.testng.asserts.SoftAssert;

public class PartialBookerResponse extends BaseModel {
    @JsonProperty("firstname")
    private final String firstname;
    @JsonProperty("lastname")
    private final String lastname;

    public PartialBookerResponse() {
        var faker = new Faker();
        this.firstname = faker.name().firstName();
        this.lastname = faker.name().lastName();
    }

    public void isequalsTo(BookerResponse partialBookerResponse) {
        var softAssert = new SoftAssert();
        softAssert.assertEquals(partialBookerResponse.getFirstname(), firstname);
        softAssert.assertEquals(partialBookerResponse.getLastname(), lastname);
        softAssert.assertAll();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
