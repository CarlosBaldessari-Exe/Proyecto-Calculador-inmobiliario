import java.util.Scanner;

public class CalcularComisionAlquilerAnual implements MenuOption {
    @Override
    public void run(Scanner sc) {
        try {
            System.out.print("Ingrese el valor mensual del alquiler: ");
            double valorAlquiler = sc.nextDouble();
            double comisionAlqAnual = valorAlquiler * Constants.COMISION_ALQ_ANUAL;
            double comisionAlqAnualIVA = comisionAlqAnual * Constants.IVA;
            System.out.println("Comisión inmobiliaria (1 mes): " + comisionAlqAnual);
            System.out.println("IVA sobre comisión: " + comisionAlqAnualIVA);
            System.out.println("Total comisión + IVA: " + (comisionAlqAnual + comisionAlqAnualIVA));
            System.out.println("Tu comisión Mariano es: " + ((comisionAlqAnual + comisionAlqAnualIVA) * Constants.COMISION_PERSONAL_MARIANO));
        } catch (Exception e) {
            System.out.println("Entrada no válida en cálculo de alquiler anual.");
            if (sc.hasNext()) sc.next();
        }
    }
}
