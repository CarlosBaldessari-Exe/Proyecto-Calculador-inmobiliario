import java.util.Scanner;

public class CalcularComisionAlquilerTemporal implements MenuOption {
    @Override
    public void run(Scanner sc) {
        try {
            System.out.print("Ingrese el valor total del alquiler temporal: ");
            double valorAlqTemp = sc.nextDouble();
            double comisionAlqTemp = valorAlqTemp * Constants.COMISION_ALQ_TEMPORAL;
            System.out.println("Comisión inmobiliaria (IVA incluido): " + comisionAlqTemp);
            System.out.println("Tu comisión Mariano es: " + (comisionAlqTemp * Constants.COMISION_PERSONAL_MARIANO));
        } catch (Exception e) {
            System.out.println("Entrada no válida en cálculo de alquiler temporal.");
            if (sc.hasNext()) sc.next();
        }
    }
}
