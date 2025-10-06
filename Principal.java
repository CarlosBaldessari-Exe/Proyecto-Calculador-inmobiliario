import java.util.Scanner;
import java.util.InputMismatchException;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Definición de constantes para los cálculos
        final double IVA = 0.22; // IVA del 22%
        final double COMISION_VENTA = 0.03; // Comisión estándar de venta (por punta)
        final double COMISION_ALQ_ANUAL = 1; // Comisión de alquiler anual (1 mes)
        final double COMISION_ALQ_TEMPORAL = 0.10; // Comisión alquiler temporal (10% IVA incluido)
        final double IRPF_ANT_2007 = 0.018; // IRPF para propiedades compradas antes de 2007
        final double IRPF_POST_2007 = 0.12; // IRPF para propiedades compradas después de 2007
        final double ITP = 0.02; // Impuesto a la Transmisión Patrimonial (2%)
        final double comisionPersonalMariano = 0.45; // Comisión personal de Mariano (45%)
        final double comisionDeLPrecioSobreCasa= 0.03; // Comisión del 3% sobre el precio de la casas
        // Bucle principal del menú
        while (true) {
            // Menú de opciones
            System.out.println("\nIngrese la opción deseada:");
            System.out.println("1. Calcular comisión de venta");
            System.out.println("2. Calcular comisión de alquiler anual");
            System.out.println("3. Calcular comisión de alquiler temporal");
            System.out.println("4. Calcular IRPF");
            System.out.println("5. Calcular ITP");
            System.out.println("6. Calcular comisión del 3% sobre el precio de la casa");
            System.out.println("7. Salir");
            int opcion = sc.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        // Cálculo de comisión de venta
                        System.out.print("Ingrese el valor de venta de la propiedad: ");
                        double valorVenta = sc.nextDouble();
                        System.out.print("¿Tiene una sola punta (1) o ambas puntas (2)? Ingrese 1 o 2: ");
                        int puntas = sc.nextInt();
                        // Si tiene ambas puntas, la comisión es del 6%, si no, del 3%
                        double porcentajeComision = (puntas == 2) ? 0.06 : 0.03;
                        double comisionVenta = valorVenta * porcentajeComision;
                        double comisionVentaIVA = comisionVenta * IVA;
                        System.out.println("Comisión inmobiliaria: " + comisionVenta);
                        System.out.println("IVA sobre comisión: " + comisionVentaIVA);
                        System.out.println("Total comisión + IVA: " + (comisionVenta + comisionVentaIVA));
                        // Cálculo de la comisión personal (45% del total)
                        System.out.println("Tu comisión Mariano es: " + (comisionVenta + comisionVentaIVA) * comisionPersonalMariano);
                    break;
                    case 2:
                        // Cálculo de comisión de alquiler anual
                        System.out.print("Ingrese el valor mensual del alquiler: ");
                        double valorAlquiler = sc.nextDouble();
                        double comisionAlqAnual = valorAlquiler * COMISION_ALQ_ANUAL;
                        double comisionAlqAnualIVA = comisionAlqAnual * IVA;
                        System.out.println("Comisión inmobiliaria (1 mes): " + comisionAlqAnual);
                        System.out.println("IVA sobre comisión: " + comisionAlqAnualIVA);
                        System.out.println("Total comisión + IVA: " + (comisionAlqAnual + comisionAlqAnualIVA));
                        System.out.println("Tu comisión Mariano es: " + ((comisionAlqAnual + comisionAlqAnualIVA) * comisionPersonalMariano));
                        break;
                    case 3:
                        // Cálculo de comisión de alquiler temporal (IVA incluido)
                        System.out.print("Ingrese el valor total del alquiler temporal: ");
                        double valorAlqTemp = sc.nextDouble();
                        double comisionAlqTemp = valorAlqTemp * COMISION_ALQ_TEMPORAL;
                        System.out.println("Comisión inmobiliaria (IVA incluido): " + comisionAlqTemp);
                        System.out.println("Tu comisión Mariano es: " + (comisionAlqTemp * comisionPersonalMariano));
                        break;
                    case 4:
                        // Cálculo de IRPF según año de compra
                        System.out.print("Ingrese el año de compra: ");
                        int anioCompra = sc.nextInt();
                        System.out.print("Ingrese el valor de venta: ");
                        double valorVentaIRPF = sc.nextDouble();
                        if (anioCompra < 2007) {
                            // Si la propiedad fue comprada antes de 2007, IRPF es 1.8% del valor de venta
                            System.out.println("IRPF a pagar (1.8%): " + (valorVentaIRPF * IRPF_ANT_2007));
                        } else {
                            // Si fue comprada después de 2007, IRPF es 12% de la ganancia
                            System.out.print("Ingrese el valor de compra: ");
                            double valorCompra = sc.nextDouble();
                            double ganancia = valorVentaIRPF - valorCompra;
                            System.out.println("Ganancia: " + ganancia);
                            System.out.println("IRPF a pagar (12% sobre ganancia): " + (ganancia * IRPF_POST_2007));
                        }
                        break;
                    case 5:
                        // Cálculo de ITP (Impuesto a la Transmisión Patrimonial)
                        System.out.print("Ingrese el valor catastral: ");
                        double valorCatastral = sc.nextDouble();
                        System.out.println("ITP a pagar (2%): " + (valorCatastral * ITP));
                        break;
                         case 6:
                    // Cálculo de la comisión del 3% sobre el precio de la casa
                    System.out.println("Ingrese el valor de la casa: ");
                    double valorCasa = sc.nextDouble();
                    double comisionCasa = valorCasa * comisionDeLPrecioSobreCasa;
                    double comisionCasaIVA = comisionCasa * IVA;
                    System.out.println("Comisión inmobiliaria (3% sobre el precio de la casa): " + comisionCasa);
                    System.out.println("IVA sobre comisión: " + comisionCasaIVA);
                    System.out.println("Total comisión + IVA: " + (comisionCasa + comisionCasaIVA));
                    System.out.println("Tu comisión Mariano es: " + (comisionCasa + comisionCasaIVA) * comisionPersonalMariano);
                    break;
                    case 7:
                        // Salida del programa
                        System.out.println("Saliendo del programa...");
                        sc.close();
                        System.exit(0);
                    default:
                        // Opción inválida
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    
                }
            } catch (InputMismatchException e) {
                // Manejo de errores de entrada
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                sc.next(); // limpiar buffer
            }
        }
    }
}