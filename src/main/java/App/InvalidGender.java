package App;

public class InvalidGender extends RuntimeException{
    public InvalidGender(){
        super("App.InvalidGender exception. Nie wlasciwy format gender!");
    }
}
