import java.util.Scanner;
import java.text.DecimalFormat;

public class CalcularRentabilidadAnual implements MenuOption {
    @Override
    public void run(Scanner sc) {
        DecimalFormat df = new DecimalFormat("#0.00");
        try {
            System.out.print("Ingrese el valor mensual del alquiler: ");
            double valorMensual = sc.nextDouble();
            double rentaAnual = valorMensual * 12;
            System.out.println("Renta anual del alquiler: " + rentaAnual);

            System.out.print("Ingrese el precio de la propiedad: ");
            double precioPropiedad = sc.nextDouble();
            if (precioPropiedad <= 0) {
                System.out.println("El precio de la propiedad debe ser mayor que 0 para calcular porcentaje.");
                return;
            }

            double rendimientoBrutoPct = (rentaAnual / precioPropiedad) * 100.0;
            System.out.println("Rentabilidad bruta anual (%): " + df.format(rendimientoBrutoPct) + "%");

            System.out.print("¿Desea calcular rentabilidad neta (restando gastos anuales)? (s/n): ");
            String resp = sc.next();
            if (resp.equalsIgnoreCase("s") || resp.equalsIgnoreCase("si")) {
                System.out.print("Ingrese los gastos anuales (mantenimiento, impuestos, etc.): ");
                double gastos = sc.nextDouble();
                double rendimientoNetoPct = ((rentaAnual - gastos) / precioPropiedad) * 100.0;
                System.out.println("Rentabilidad neta anual (%): " + df.format(rendimientoNetoPct) + "%");
            }
        } catch (Exception e) {
            System.out.println("Entrada no válida en cálculo de renta anual.");
            if (sc.hasNext()) sc.next();
        }
    }
}