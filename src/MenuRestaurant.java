import java.util.ArrayList;
import java.util.Scanner;

class Ingrediente {
    private String nombre;
    private double cantidad;
    private String unidadMedida;

    public Ingrediente(String nombre, double cantidad, String unidadMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }
}

class Plato {
    private String nombreCompleto;
    private double precio;
    private boolean esBebida;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String nombreCompleto, double precio, boolean esBebida) {
        this.nombreCompleto = nombreCompleto;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isEsBebida() {
        return esBebida;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }
}

public class MenuRestaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Plato> platosMenu = new ArrayList<>();

        System.out.println("Ingrese la cantidad de platos:");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.println("Ingrese el nombre del plato:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el precio del plato:");
            double precio = scanner.nextDouble();
            System.out.println("¿Es bebida? (true/false):");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine();

            Plato plato = new Plato(nombre, precio, esBebida);

            if (!esBebida) {
                System.out.println("Ingrese la cantidad de ingredientes:");
                int cantidadIngredientes = scanner.nextInt();
                scanner.nextLine();

                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.println("Ingrese el nombre del ingrediente:");
                    String nombreIngrediente = scanner.nextLine();
                    System.out.println("Ingrese la cantidad del ingrediente:");
                    double cantidad = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Ingrese la unidad de medida:");
                    String unidadMedida = scanner.nextLine();

                    plato.agregarIngrediente(new Ingrediente(nombreIngrediente, cantidad, unidadMedida));
                }
            }

            platosMenu.add(plato);
        }

        System.out.println("MENÚ DEL RESTAURANTE:");
        for (Plato plato : platosMenu) {
            System.out.println("Plato: " + plato.getNombreCompleto());
            System.out.println("Precio: $" + plato.getPrecio());
            if (!plato.isEsBebida()) {
                System.out.println("Ingredientes:");
                for (Ingrediente ingrediente : plato.getIngredientes()) {
                    System.out.println(ingrediente.getNombre() + " - " + ingrediente.getCantidad() + " " + ingrediente.getUnidadMedida());
                }
            }
            System.out.println();
        }
    }
}

