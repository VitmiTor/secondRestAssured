package models.booker;

import base.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse extends BaseModel {
    public static final String jsonSchema = "token/token.json";
    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }
}
