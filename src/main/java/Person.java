import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private final Email email;
    private final String nameAndSurname;
    private final char gender;
    private final double amount;
    private final LocalDate deadline;

    private Person(Email email, String nameAndSurname, char gender, double amount, LocalDate deadline) {
        this.email = email;
        this.nameAndSurname = nameAndSurname;
        this.gender = gender;
        this.amount = amount;
        this.deadline = deadline;
    }

    public static Person of(Email email, String nameAndSurname, char gender, double amount, LocalDate deadline){
        if(nameAndSurname.isEmpty()){
            throw new InvalidNameAndSurname();
        }
        if(gender != 'K' && gender != 'M'){
            throw new InvalidGender();
        }
        if(amount <= 0){
            throw new InvalidAmount();
        }
        return new Person(email, nameAndSurname, gender,  amount, deadline);
    }

    public static Person parse(String input){
        String[] tab = input.split(", ");
        Person.Email email = new Person.Email(tab[0]);
        String personName = tab[1];
        String genderString = tab[2];
        double amount = Double.parseDouble(tab[3]);
        char genderChar = genderString.charAt(0);
        LocalDate deadline = LocalDate.parse(tab[4]);
        return new Person(email, personName, genderChar, amount, deadline);
    }


    public Email getEmail() {
        return email;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public char getGender() {
        return gender;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email=" + email +
                ", nameAndSurname='" + nameAndSurname + '\'' +
                ", gender=" + gender +
                ", amount=" + amount +
                ", deadline=" + deadline +
                '}';
    }

    public static class Email {

        public final Pattern EMAIL_PATTERN = Pattern
                .compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        protected String email;

        public Email(String email){
            if(email.isEmpty() || !validate(email)){
                throw new InvalidEmail();
            }else {
                this.email = email;
            }
        }

        public String getEmail() {
            return email;
        }

        public boolean validate(String str){
            Matcher matcher = EMAIL_PATTERN.matcher(str);
            return matcher.find();
        }

        @Override
        public String toString() {
            return email;
        }
    }
}
