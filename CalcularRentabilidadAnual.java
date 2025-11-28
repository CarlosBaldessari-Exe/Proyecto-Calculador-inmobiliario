import java.util.Scanner;

public class CalcularRentabilidadAnual implements MenuOption {
    @Override
    public void run(Scanner sc) {
        try {
            System.out.print("Ingrese el valor mensual del alquiler: ");
            double valorMensual = sc.nextDouble();
            double rentAnual = valorMensual * 12;
            System.out.println("Renta anual del alquiler: " + rentAnual);
        } catch (Exception e) {
            System.out.println("Entrada no válida en cálculo de renta anual.");
            if (sc.hasNext()) sc.next();
        }
    }
}