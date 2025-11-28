import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Registrar las opciones en un mapa (mantiene orden)
        Map<Integer, String> menuText = new LinkedHashMap<>();
        menuText.put(1, "Calcular comisión de venta");
        menuText.put(2, "Calcular comisión de alquiler anual");
        menuText.put(3, "Calcular comisión de alquiler temporal");
        menuText.put(4, "Calcular IRPF");
        menuText.put(5, "Calcular ITP");
        menuText.put(6, "Calcular comisión del 3% sobre el precio de la casa");
        menuText.put(7, "Calcular Rentabilidad anual");
        menuText.put(8, "Salir");

        Map<Integer, MenuOption> options = new LinkedHashMap<>();
        options.put(1, new CalcularComisionVenta());
        options.put(2, new CalcularComisionAlquilerAnual());
        options.put(3, new CalcularComisionAlquilerTemporal());
        options.put(4, new CalcularIRPF());
        options.put(5, new CalcularITP());
        options.put(6, new CalcularComisionTresPorciento());
        options.put(7, new CalcularRentabilidadAnual());
        
        while (true) {
            System.out.println("\nIngrese la opción deseada:");
            for (Map.Entry<Integer, String> e : menuText.entrySet()) {
                System.out.println(e.getKey() + ". " + e.getValue());
            }

            try {
                int opcion = sc.nextInt();
                if (opcion == 8) {
                    System.out.println("Saliendo del programa...");
                    sc.close();
                    System.exit(0);
                }

                MenuOption opt = options.get(opcion);
                if (opt != null) {
                    opt.run(sc);
                } else {
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                if (sc.hasNext()) sc.next(); // limpiar buffer
            }
        }
    }
}