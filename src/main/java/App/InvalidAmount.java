package App;

public class InvalidAmount extends RuntimeException{
    public InvalidAmount(){
        super("App.InvalidAmount Exception. Nie wlasciwy format kwoty!");
    }
}
