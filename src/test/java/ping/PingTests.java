package ping;

import api.PingApi;
import base.BaseTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class PingTests extends BaseTest {
    private PingApi pingApi;

    @Test
    public void healthCheckTest() {
        pingApi = new PingApi(false);
        final var response = pingApi.healthCheck();

        response.then().assertThat()
                .statusCode(201)
                .time(lessThan(3000L));
    }
}
