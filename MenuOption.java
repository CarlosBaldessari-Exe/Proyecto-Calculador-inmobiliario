import java.util.Scanner;

public interface MenuOption {
    /**
     * Ejecuta la opción. Se recibe el Scanner para reutilizar el mismo flujo de entrada.
     */
    void run(Scanner sc);
}
