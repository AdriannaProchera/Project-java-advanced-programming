import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmailCreator {

    public static BufferedReader bufferedReader;
    public static BufferedWriter bufferedWriter2;
    public static BufferedReader bufferedReader2;

    public List<Person> reader(String filename){
        List<Person> people = new ArrayList<>();
        try{
            String str;
            bufferedReader = new BufferedReader(new FileReader(filename));
            while((str = bufferedReader.readLine()) != null) {
                String[] tab = str.split(", ");
                Person.Email email = new Person.Email(tab[0]);
                String personName = tab[1];
                String genderString = tab[2];
                double amount = Double.parseDouble(tab[3]);
                char genderChar = genderString.charAt(0);
                LocalDate deadline = LocalDate.parse(tab[4]);
                Person person = new Person(email, personName, genderChar, amount, deadline);
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

            String tmpNewFilePath = "/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/java/"+"tmp.txt";
            String newFilePath = "/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/java/" +element.getEmail()+".txt";
            FileInputStream instream = null;
            FileOutputStream outstream = null;
            File infile;
            File outfile =new File(tmpNewFilePath);
            if(element.getGender() =='K'){
                infile =new File("/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/java/patternWoman.txt");

            }else{
                infile =new File("/Users/adrianna/Documents/Poject_java_advanced_programming/src/main/java/patternMan.txt");
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

            bufferedReader2 = new BufferedReader(new FileReader(tmpNewFilePath));
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
