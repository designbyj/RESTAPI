import java.io.IOException;

public class ConnectToUrl {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        Root root = parser.parseToday("RUB");

        System.out.println(root.toString());
        root = parser.parseYestarday("RUB");
        System.out.println(root.toString());
    }
}