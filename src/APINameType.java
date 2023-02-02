public enum APINameType {
    FULL_NAME("fullname"),
    FIRST_NAME("firstname"),
    LAST_NAME("lastname");

    public final String value;
    APINameType(String string)
    {
        this.value =  string;
    }

    public String toString()
    {
        return this.value;
    }
}
