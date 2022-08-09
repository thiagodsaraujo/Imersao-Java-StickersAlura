import java.io.InputStream;
import java.net.URL;
import java.util.List;

@lombok.NoArgsConstructor
@lombok.Data
public class App {


    @com.fasterxml.jackson.annotation.JsonProperty("items")
    private List<ItemsDTO> items;
    @com.fasterxml.jackson.annotation.JsonProperty("errorMessage")
    private String errorMessage;

    public static void main(String[] args) throws Exception{

        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        String url = "https://imdb-api.com/en/API/Top250Movies/k_qel4efq8";

//        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
//        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        var geradora = new GeradoraDeFigurinhas();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
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

