package imersao.java.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Desafio {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        var uri = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println("Print to Document Body: ");

    }
}
