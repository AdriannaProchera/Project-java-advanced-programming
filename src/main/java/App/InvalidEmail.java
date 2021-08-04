package App;

public class InvalidEmail extends RuntimeException{
        public  InvalidEmail(){
            super("App.InvalidEmail exception.Nie wlasciwy format e-mail!");
        }
}
