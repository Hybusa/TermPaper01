import java.util.Random;

public enum Department {
    DESIGN("Design"),
    PRODUCTION("Production"),
    RELEASE("Release"),
    MANAGEMENT("Management"),
    DEVELOPMENT("Development"),
    CONTENT("Content");

    private final String string;

    private static final Random RNG = new Random();

    public static Department getRandomDepartment()  {
        Department[] departments = values();
        return departments[RNG.nextInt(departments.length)];
    }
    Department(String string)
    {

        this.string= string;
    }

    public String toString()
    {
        return this.string;
    }

}
