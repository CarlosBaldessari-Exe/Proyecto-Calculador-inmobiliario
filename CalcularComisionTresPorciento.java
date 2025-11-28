import java.util.Scanner;

public class CalcularComisionTresPorciento implements MenuOption {
    @Override
    public void run(Scanner sc) {
        try {
            System.out.print("Ingrese el valor de la casa: ");
            double valorCasa = sc.nextDouble();
            double comisionCasa = valorCasa * Constants.COMISION_3_PORC;
            double comisionCasaIVA = comisionCasa * Constants.IVA;
            System.out.println("Comisión inmobiliaria (3% sobre el precio de la casa): " + comisionCasa);
            System.out.println("IVA sobre comisión: " + comisionCasaIVA);
            System.out.println("Total comisión + IVA: " + (comisionCasa + comisionCasaIVA));
            System.out.println("Tu comisión Mariano es: " + (comisionCasa + comisionCasaIVA) * Constants.COMISION_PERSONAL_MARIANO);
        } catch (Exception e) {
            System.out.println("Entrada no válida en cálculo de comisión del 3%.");
            if (sc.hasNext()) sc.next();
        }
    }
}
