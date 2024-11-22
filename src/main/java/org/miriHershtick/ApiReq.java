package org.miriHershtick;

import lombok.Data;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Data
public class ApiReq {
    private final String url;
    private int statusCode;
    private String responseBody;


    public ApiReq(String baseUrl) {
        this.url = baseUrl;
        sendGetApiRequest();
    }

    public void sendGetApiRequest() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            this.statusCode = response.statusCode();
            this.responseBody = response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "ApiEntity{" +
                "url='" + url + '\'' +
                ", statusCode=" + statusCode +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
