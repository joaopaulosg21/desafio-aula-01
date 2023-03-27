import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI uri = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        JsonParser parser = new JsonParser();
        List<Map<String,String>> filmes = parser.parse(body);
        
        for(Map<String,String> filme : filmes) {

            System.out.println("\u001b[1m \u001b[34m Title: \u001b[m" + filme.get("title"));
            
            System.out.println("\u001b[1m \u001b[34m Poster: \u001b[m" + filme.get("image"));

            int stars = (int) Double.parseDouble(filme.get("imDbRating"));
            String color = changeColor(stars);

            System.out.print("\u001b[1m " +color+ "Rating: " + filme.get("imDbRating")+" (");
            for(int i=0; i<stars ;i++) {
                System.out.print("\u2B50");
            }
            System.out.println(")\u001b[m\n");
        }
    }

    public static String changeColor(int number) {
        switch(number) {
            case 9:
                return "\u001b[42m";
            case 8:
                return "\u001b[43m";
            case 7:
                return "\u001b[41m";
            default:
                return "\u001b[47m";
        }
        
    }
} 
