import java.util.Scanner; 

public class MyTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        
        scanner.close(); // Not required for stdin but good practice

    }
}
