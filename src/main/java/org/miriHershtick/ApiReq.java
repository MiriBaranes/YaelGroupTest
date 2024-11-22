package org.miriHershtick;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiEntity {
    private final String url;
    private int statusCode;
    private String responseBody;
    private boolean failed;

    public ApiEntity(String baseUrl) {
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
            failed=true;
            System.out.println(e.getMessage());
        }
    }

    public boolean isFailed() {
        return failed;
    }

    public String getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
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
