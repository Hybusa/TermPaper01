import kuzLib.*;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class Main {
    static final String API_KEY = "34e61b4b4a5641aea9f2a87773283c46";
    static final int APINum = 50;

    public static void main(String[] args) {

        EmployeeBook book1 = new EmployeeBook();
        JSONArray jArray = getRandomFullName(APINameType.FULL_NAME, APINum);
        for (int i = 0; i < APINum; i++) {
            book1.addEmployee(new Employee(jArray.getString(i),
                    Department.getRandomDepartment(),
                    kuz.getRandomIntInRange(100_000, 500_000)));
        }

        book1.printAllEmployees();

        /*
        book1.addEmployee(null);
        System.out.println(book1.getAvarageSalary(Department.DESIGN));
        book1.printAllEmployees(Department.DESIGN);
        System.out.println(book1.getPayrollExpense());
*/
        //book1.getMinSalary();
    /*
            Employee employee=null;
            if(book1.getEmployeeByID(5).isPresent())
            {
                employee =  new Employee(book1.getEmployeeByID(5).get());
            }*/
        //Checking removers
    /*


            book1.removeEmployee(employee.getEmployeeFullName());

            book1.removeEmployee(3);

            book1.removeEmployee(3);

            book1.removeEmployee(5);

            book1.removeEmployee(employee.getEmployeeFullName());

           */
        //Checking changers
    /*        System.out.println("_________________________________________");
            book1.changeSalaryByName(employee.getEmployeeFullName(),190_000);
            book1.printAllEmployees();
            System.out.println("_________________________________________");
            book1.indexSalary(10);
            book1.printAllEmployees();
            System.out.println("_________________________________________");
            book1.indexSalary(Department.CONTENT, 10);
            book1.printAllEmployees();
            System.out.println("_________________________________________");

            book1.removeEmployee(3);

            if(book1.getEmployeeByID(3).isPresent())
                book1.changeSalaryByName(book1.getEmployeeByID(3).get().getEmployeeFullName(),50_000);
            else
                System.out.println("No such Employee");

            book1.changeDepartmentByName(employee.getEmployeeFullName(), Department.MANAGEMENT);
            book1.printAllEmployees();
            System.out.println("_________________________________________");
        book1.printAllEmployees();
        System.out.println("_________________________________________");
        book1.indexSalary(Department.DEVELOPMENT, 10);
        book1.printAllEmployees(Department.DEVELOPMENT);*/

    /*
        System.out.println(book1.getPayrollExpense());
        printOptional(book1.getMinSalary());
        printOptional(book1.getMaxSalary());
        printOptional(book1.getMaxSalary(Department.DEVELOPMENT));
        System.out.println(book1.getAvarageSalary());
        System.out.println(book1.getAvarageSalary(Department.DESIGN));*/
/*

        book1.printEmployeesBelowSalary(200_000);
        System.out.println("_______________________________");
        book1.printEmployeesAboveSalary(200_000);
*/

    }

    public static JSONArray getRandomFullName(APINameType type, int i) {

        URI uri;
        try {
            uri = new URI("https://randommer.io/api/Name?nameType=" + type + "&quantity="+i);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Wrong URI format");
        }

        return formAndSendHTTPRequest(uri);
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

    public static void printOptional(Optional object)
    {
        if(object.isPresent())
            System.out.println(object.get());
        else
            System.out.println("No such element");
    }
}


