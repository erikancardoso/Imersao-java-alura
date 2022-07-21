package imersao.java.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {


        //fazer conecção http e buscar informações na API"
        //guardando link da API que desejo acessar
        String url = "https://api.mocki.io/v2/549a5d8b";
        //crio um URI (permitir a interação com representações do recurso através de uma rede)
        URI local = URI.create(url);
        //criando um acesso http para o cliente
        HttpClient client = HttpClient.newHttpClient();
        //crio uma requisição para acessar o endereço, buscando dados, etc
        HttpRequest request = HttpRequest.newBuilder(local).GET().build();
        //crio um objeto de resposta para enviar a (requisição do cliente e receber o tipo do arquivo)
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //BodyHandler retorna uma forma de leitura de dados, string, boolean, etc
        //criar uma variavel para guardar as informações de resposta requeridas.
        String body = response.body();
        //imprimir as imformações gardadas dentro do corpo do documento
        //System.out.println(body);

        //Estrair dados (title, image, rank")
        //preciso primeiro criar um metodo que vai parsear e identificar expressoes regulares para poder colocar dentro da list
        var parser = new JsonParse(); // para não ficar repetindo pode usar o var no ligar da biblioteca
        //criar uma lista para separar os tipos de dados que preciso armazenar
        List<Map<String, String>> filmList = parser.parse(body);

        //manipular dados da api
        System.out.println("Exibir quantidade de filmes disponivel na lista "+filmList.size());
        System.out.println("\nExibir o filme por posição (0): \n"+filmList.get(0));
        System.out.println("\nExibir o Titulo do filme");
        for (Map<String, String> verFilme: filmList) {
            System.out.println(verFilme.get("title"));
        }
        System.out.println("\nExibir Titulo, Rank e imagem");
        for (Map<String, String> verTodos: filmList) {
            System.out.println(verTodos.get("title"));
            System.out.println(verTodos.get("imDbRating"));
            System.out.println(verTodos.get("image"));
            System.out.println();
        }
        System.out.println("Ordenar por classificação");


    }


}
