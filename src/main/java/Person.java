import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private final Email email;
    private final String nameAndSurname;
    private final char gender;
    private final double amount;
    private final LocalDate deadline;


    public Person(Email email, String nameAndSurname, char gender, double amount, LocalDate deadline) {

        this.email = email;

        if(nameAndSurname.isEmpty()){
            throw new InvalidNameAndSurname();
        }
        this.nameAndSurname = nameAndSurname;

        if(gender != 'K' && gender != 'M'){
            throw new InvalidGender();
        }
        this.gender = gender;

        if(amount <= 0){
            throw new InvalidAmount();
        }
        this.amount = amount;

        this.deadline = deadline;
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
                //this.email = "unknown";
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
