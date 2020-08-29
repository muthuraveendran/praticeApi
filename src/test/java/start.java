import com.api.pratice.bitz.components.Application.ApplicationBase;

public class start extends ApplicationBase {
    start(){
    }

    public void addedValue() {
        System.out.println("Welecome to it >>>>>>>>>>>>>>>>>> ");
    };

    public static void main(String[] args) {
        start s = new start();
        s.addedValue();
    }
}
