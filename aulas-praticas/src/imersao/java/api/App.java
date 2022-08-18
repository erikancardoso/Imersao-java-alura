package imersao.java.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {


        //fazer conexão http e buscar informações na API"
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        URI local = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(local).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //imprimir as imformações gardadas dentro do corpo do documento
        //System.out.println(body);

        //Estrair dados (title, image, rank")

        var parser = new JsonParse();
        List<Map<String, String>> filmList = parser.parse(body);

        //manipular dados da api
        System.out.println("\n\u001b[1;34mExibir quantidade de filmes disponivel na lista \u001b[m "+filmList.size());
        System.out.println("\n\u001b[1;34mExibir o filme por posição (0): \u001b[m\n"+filmList.get(0));
        System.out.println("\n\u001b[1;34mExibir o Titulo do filme\u001b[m");
        for (Map<String, String> verFilme: filmList) {
            System.out.println(verFilme.get("title"));
        }

        System.out.println("\n \u001b[1;30m \u001b[42m Melhores Filmes \u001b[m");
        for (Map<String, String> verTodos: filmList) {
            System.out.println("\u001b[1;31m \u001b[46m "+verTodos.get("title")+" \u001b[m");
            String classifica = verTodos.get("imDbRating");
            System.out.println("\u001b[1;36m \u001b[101m \uD83D\uDC99 "+(Math.round(Float.parseFloat(classifica)))+" \uD83D\uDC99 \u001b[m");
            System.out.println("\u001b[107r4m "+verTodos.get("image")+"\u001b[m");
            System.out.println();
        }
        System.out.println("Ordenar por classificação");
    }
}
//caractere especial utf para mostrar quantidade de estrelas que contem na classificação