package com.z7.collabowriteapi.security;

import com.z7.collabowriteapi.exception.InternalServerError;
import com.z7.collabowriteapi.model.UserCredentials;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubTokenValidator {

    public UserCredentials validateToken(String token) throws InternalServerError {
        RestTemplate restTemplate = new RestTemplate();

        String uri = "  https://api.github.com/user"; // or any other uri

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.github+json");
        headers.add("Authorization", token);
        headers.add("X-GitHub-Api-Version", "2022-11-28");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        //we need to only map the data that is going to be relevant during the validation or future operations
        try {
            ResponseEntity<UserCredentials> response = restTemplate.exchange(uri, HttpMethod.GET, entity, UserCredentials.class);
            return response.getBody();
        } catch (HttpClientErrorException.Unauthorized ex) {
            // Token is not valid or lacks required permissions
            //this is the only case where we can return a value in case of an exception
            return null;
        } catch (Exception ex) {
            // Other generic exception occurred (e.g., network error)
            // Handle or log the specific error scenario
            //in reality we should throw exceptions in this case
            throw new InternalServerError("internal server error");
        }
    }
}
