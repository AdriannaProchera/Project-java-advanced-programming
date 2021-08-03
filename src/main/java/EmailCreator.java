import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmailCreator {
    public static final String TEMPORARY_FILE = "/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/resources/"+"tmp.txt";
    public static final String EMAIL_FILE_PATH = "/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/resources/";
    public static final String PATH_WOMAN = "/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/resources/patternWoman.txt";
    public static final String PATH_MAN = "/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/resources/patternMan.txt";

    public static BufferedReader bufferedReader;
    public static BufferedWriter bufferedWriter2;
    public static BufferedReader bufferedReader2;

    public List<Person> reader(String filename){
        List<Person> people = new ArrayList<>();
        try{
            String str;
            bufferedReader = new BufferedReader(new FileReader(filename));
            while((str = bufferedReader.readLine()) != null) {
                Person person = Person.parse(str);
                System.out.println(person);
                people.add(person);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
        return people;
    }

    public void fileCreator(List<Person> people) throws IOException {
        for(Person element : people){
            String newFilePath = EMAIL_FILE_PATH + element.getEmail()+".txt";
            FileInputStream instream = null;
            FileOutputStream outstream = null;
            File infile;
            File outfile =new File(TEMPORARY_FILE);
            if(element.getGender() =='K'){
                infile =new File(PATH_WOMAN);

            }else{
                infile =new File(PATH_MAN);
            }
            instream = new FileInputStream(infile);
            outstream = new FileOutputStream(outfile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = instream.read(buffer)) > 0){
                outstream.write(buffer, 0, length);
            }
            instream.close();
            outstream.close();

            bufferedReader2 = new BufferedReader(new FileReader(TEMPORARY_FILE));
            bufferedWriter2 = new BufferedWriter(new FileWriter(newFilePath));
            String str;
            while ((str = bufferedReader2.readLine()) != null){
                String newContent = str;
                if(str.contains("<email>")){
                    newContent = newContent.replace("<email>", element.getEmail().toString());
                }
                if(str.contains("<imię i nazwisko>")){
                    newContent = newContent.replace("<imię i nazwisko>", element.getNameAndSurname());
                }
                if(str.contains("<kwota>")){
                    newContent = newContent.replace("<kwota>", String.valueOf(element.getAmount()));
                }
                if(str.contains("<data>") ){
                    newContent = newContent.replace("<data>", String.valueOf(element.getDeadline()));
                }

                if(!newContent.contains("<data>") && !newContent.contains("<kwota>") && !newContent.contains("<kwota>") && !newContent.contains("<imię i nazwisko>") &&!newContent.contains("<email>")) {
                    bufferedWriter2.write(newContent + "\n");
                    continue;
                }
                bufferedWriter2.write(newContent);
            }
            if(outfile.delete()){
                System.out.println("Usunieto plik tymczasowy i utworzono: "+ element.getEmail() +".txt");
            }

            bufferedReader2.close();
            bufferedWriter2.close();
            }
    }
}
