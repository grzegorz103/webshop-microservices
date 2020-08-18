package service.config.auth0;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Auth0ManagementEndpoint {

    @Value("${auth0.client.id}")
    private String clientId;

    @Value("${auth0.client.secret}")
    private String clientSecret;

    private String getAccessToken() throws UnirestException {
        return Unirest.post("https://dev-brcvsf2g.eu.auth0.com/oauth/token")
                .header("content-type", "application/x-www-form-urlencoded")
                .body("grant_type=client_credentials&client_id=" + this.clientId + "&client_secret=" + this.clientSecret + "&audience=https://dev-brcvsf2g.eu.auth0.com/api/v2/")
                .asJson()
                .getBody()
                .getObject()
                .getString("access_token");
    }

    public Object getUsers() {
        try {
            List<Object> users = new ArrayList<>();
            return Unirest.get("https://dev-brcvsf2g.eu.auth0.com/api/v2/users")
                    .header("Authorization", "Bearer " + this.getAccessToken())
                    .asJson()
                    .getBody()
                    .getArray()
                    .toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException();
    }

}
