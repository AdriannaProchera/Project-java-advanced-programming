import java.io.IOException;
import java.util.List;

public class EmailApp {

    public final static String INPUT_FILE = "/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/resources/input.txt";

    public static void main(String[] args) throws IOException {
        EmailCreator creator = new EmailCreator();
        System.out.println("\nLista osób: ");
        List<Person> myEmailList = creator.reader(INPUT_FILE);
        System.out.println("************************************************************************************************************");
        System.out.println();
        creator.fileCreator(myEmailList);
    }
}
