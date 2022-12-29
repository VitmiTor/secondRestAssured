package token;


import api.TokenApi;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.booker.TokenBody;
import models.booker.TokenResponse;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class TokenTests extends BaseTest {
    private Response response;

    @Test
    public void authToken() {
        var tokenApi = new TokenApi(false);
        final var tokenResponse = new TokenBody();
        response = tokenApi.authentication(tokenResponse);

        final var responseTokenBody = response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchema(getSchema(TokenResponse.jsonSchema)))
                .extract().body().as(TokenResponse.class);

        final var uniqueToken = responseTokenBody.getToken();
        logs.debug("el token es " + uniqueToken);
        
    }

}
