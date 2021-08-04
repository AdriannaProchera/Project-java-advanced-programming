package App;

public class InvalidNameAndSurname extends RuntimeException{
    public InvalidNameAndSurname(){
        super("App.InvalidNameAndSurname exception. Nie wlasciwy formaty nameAndSurname!");
    }
}
