import java.util.Scanner;

public class CalcularIRPF implements MenuOption {
    @Override
    public void run(Scanner sc) {
        try {
            System.out.print("Ingrese el año de compra: ");
            int anioCompra = sc.nextInt();
            System.out.print("Ingrese el valor de venta: ");
            double valorVentaIRPF = sc.nextDouble();
            if (anioCompra < 2007) {
                System.out.println("IRPF a pagar (1.8%): " + (valorVentaIRPF * Constants.IRPF_ANT_2007));
            } else {
                System.out.print("Ingrese el valor de compra: ");
                double valorCompra = sc.nextDouble();
                double ganancia = valorVentaIRPF - valorCompra;
                System.out.println("Ganancia: " + ganancia);
                System.out.println("IRPF a pagar (12% sobre ganancia): " + (ganancia * Constants.IRPF_POST_2007));
            }
        } catch (Exception e) {
            System.out.println("Entrada no válida en cálculo de IRPF.");
            if (sc.hasNext()) sc.next();
        }
    }
}
