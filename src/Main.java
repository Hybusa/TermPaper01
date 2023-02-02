import kuzLib.*;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    static final String API_KEY = "34e61b4b4a5641aea9f2a87773283c46";

    public static void main(String[] args) {

        EmployeeBook book1 = new EmployeeBook();

        for(int i = 0; i < 10; i++)
        {
            book1.addEmployee(new Employee(getRandomFullName(APINameType.FULL_NAME),
                    Department.getRandomDepartment(),
                    kuz.getRandomIntInRange(100_000,500_000)));
        }

        book1.printAllEmployees();



    }

    public static String getRandomFullName(APINameType type) {

        URI uri;
        try {
            uri = new URI("https://randommer.io/api/Name?nameType=" + type + "&quantity=1");
        } catch (URISyntaxException e) {
            throw new RuntimeException("Wrong URI format");
        }

        JSONArray jArray = formAndSendHTTPRequest(uri);
        return jArray.getString(0);
    }

    private static JSONArray formAndSendHTTPRequest(URI uri) {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(uri)
                .header("X-Api-Key", API_KEY).GET().build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse;
        try {
            getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException timeOut) {
            throw new RuntimeException(timeOut);
        }
        return new JSONArray(getResponse.body());
    }
}


