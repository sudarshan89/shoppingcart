import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 11:54 AM
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World");

        System.out.println(new BigDecimal("100.8923").setScale(2, RoundingMode.CEILING).toPlainString());
    }
}
