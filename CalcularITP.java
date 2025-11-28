import java.util.Scanner;

public class CalcularITP implements MenuOption {
    @Override
    public void run(Scanner sc) {
        try {
            System.out.print("Ingrese el valor catastral: ");
            double valorCatastral = sc.nextDouble();
            System.out.println("ITP a pagar (2%): " + (valorCatastral * Constants.ITP));
        } catch (Exception e) {
            System.out.println("Entrada no válida en cálculo de ITP.");
            if (sc.hasNext()) sc.next();
        }
    }
}
