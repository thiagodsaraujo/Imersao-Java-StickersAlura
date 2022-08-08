import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@lombok.NoArgsConstructor
@lombok.Data
public class App {


    @com.fasterxml.jackson.annotation.JsonProperty("items")
    private List<ItemsDTO> items;
    @com.fasterxml.jackson.annotation.JsonProperty("errorMessage")
    private String errorMessage;

    public static void main(String[] args) throws Exception{

        // 1º - Pegar os dados do IMDB - Fazer conexão HTTPS e buscar os top 250 filmes;
        String url = "https://imdb-api.com/en/API/Top250Movies/k_qel4efq8";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

//        2º - Extrair os dados que interessam (titulo, poster, classificação); ( Parsear os dados)
        // O ideal seria usar alguma biblioteca

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

//          3º - Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String,String> filme: listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            try{
            String nomeArquivo = titulo + ".png";
            InputStream inputStream = new URL(urlImagem).openStream();
            geradora.cria(inputStream, nomeArquivo);
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(titulo);
            System.out.println();
        }

    }

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class ItemsDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("id")
        private String id;
        @com.fasterxml.jackson.annotation.JsonProperty("rank")
        private String rank;
        @com.fasterxml.jackson.annotation.JsonProperty("title")
        private String title;
        @com.fasterxml.jackson.annotation.JsonProperty("fullTitle")
        private String fullTitle;
        @com.fasterxml.jackson.annotation.JsonProperty("year")
        private String year;
        @com.fasterxml.jackson.annotation.JsonProperty("image")
        private String image;
        @com.fasterxml.jackson.annotation.JsonProperty("crew")
        private String crew;
        @com.fasterxml.jackson.annotation.JsonProperty("imDbRating")
        private String imDbRating;
        @com.fasterxml.jackson.annotation.JsonProperty("imDbRatingCount")
        private String imDbRatingCount;
    }
}

