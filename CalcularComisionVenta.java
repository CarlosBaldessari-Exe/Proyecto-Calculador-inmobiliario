import java.util.Scanner;

public class CalcularComisionVenta implements MenuOption {
    @Override
    public void run(Scanner sc) {
        try {
            System.out.print("Ingrese el valor de venta de la propiedad: ");
            double valorVenta = sc.nextDouble();
            System.out.print("¿Tiene una sola punta (1) o ambas puntas (2)? Ingrese 1 o 2: ");
            int puntas = sc.nextInt();
            double porcentajeComision = (puntas == 2) ? 0.06 : Constants.COMISION_VENTA;
            double comisionVenta = valorVenta * porcentajeComision;
            double comisionVentaIVA = comisionVenta * Constants.IVA;
            System.out.println("Comisión inmobiliaria: " + comisionVenta);
            System.out.println("IVA sobre comisión: " + comisionVentaIVA);
            System.out.println("Total comisión + IVA: " + (comisionVenta + comisionVentaIVA));
            System.out.println("Tu comisión Mariano es: " + (comisionVenta + comisionVentaIVA) * Constants.COMISION_PERSONAL_MARIANO);
        } catch (Exception e) {
            System.out.println("Entrada no válida en cálculo de comisión de venta.");
            if (sc.hasNext()) sc.next();
        }
    }
}
