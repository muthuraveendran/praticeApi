import java.util.Properties;

public class PraticeProperties {
    public static String data = null;
    public static void main(String[] args) {
         data = System.getProperty(BaseFile.Environment);
        System.out.println("<<<<<< data >>>>>>>>" +  data);
        if(data.equals("QA")) {
            System.out.println("I am in QA Environment ");
        } else {
            System.out.println("I am in Another Environment");
        }
    }
}
