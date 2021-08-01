import java.io.IOException;
import java.util.List;

public class EmailApp {
    public static void main(String[] args) throws IOException {
        String filename = "/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/java/input.txt";
        EmailCreator creator = new EmailCreator();
        System.out.println("\nLista osób: ");
        List<Person> myEmailList = creator.reader(filename);
        System.out.println("************************************************************************************************************");
        System.out.println();
        creator.fileCreator(myEmailList);
    }
}
