package Facturacion;
import java.util.ArrayList;
import java.util.Scanner;

public class Facturacion {
    private static final String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        // Solicitar datos de la factura
        System.out.println("Ingrese la fecha de la factura:");
        factura.setFechaFactura(scanner.nextLine());

        System.out.println("Ingrese el número de la factura:");
        long numeroFactura = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer
        while (numeroFactura <= 0) {
            System.out.println("Número de factura inválido. Debe ser mayor a cero. Intente nuevamente:");
            numeroFactura = scanner.nextLong();
            scanner.nextLine();
        }
        factura.setNumeroFactura(numeroFactura);

        System.out.println("Ingrese el nombre del cliente:");
        String cliente = scanner.nextLine();
        while (cliente.isEmpty()) {
            System.out.println("El nombre del cliente no puede estar vacío. Intente nuevamente:");
            cliente = scanner.nextLine();
        }
        factura.setCliente(cliente);

        // Añadir artículos a la factura
        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese el código del artículo a facturar:");
            String codigo = scanner.nextLine();
            int index = buscarArticuloPorCodigo(codigo);

            if (index == -1) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
                continue;
            }

            String nombreArticulo = articulos[index][1];
            double precioUnitario = Double.parseDouble(articulos[index][2]);

            System.out.println("Ingrese la cantidad a facturar:");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            double descuentoItem = cantidad > 5 ? precioUnitario * 0.1 : 0;
            double subtotal = (precioUnitario - descuentoItem) * cantidad;

            DetalleFactura detalle = new DetalleFactura(codigo, nombreArticulo, cantidad, precioUnitario, descuentoItem, subtotal);
            factura.agregarDetalle(detalle);

            System.out.println("¿Desea agregar otro artículo? (S/N):");
            String respuesta = scanner.nextLine();
            continuar = respuesta.equalsIgnoreCase("S");
        }

        // Calcular el total y mostrar la factura completa
        factura.calcularMontoTotal();

        // Imprimir la factura
        System.out.println("\nFACTURA:");
        System.out.println("Fecha: " + factura.getFechaFactura());
        System.out.println("Número: " + factura.getNumeroFactura());
        System.out.println("Cliente: " + factura.getCliente());
        System.out.println("Detalles de la factura:");
        System.out.println("Código | Nombre | Cantidad | Precio Unitario | Descuento | Subtotal");

        for (DetalleFactura detalle : factura.getDetallesFactura()) {
            System.out.printf("%s | %s | %d | %.2f | %.2f | %.2f\n",
                    detalle.getCodigoArticulo(),
                    detalle.getNombreArticulo(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getDescuentoItem(),
                    detalle.getSubtotal());
        }

        System.out.printf("Total de la factura: %.2f\n", factura.getTotalCalculadoFactura());
        scanner.close();
    }

    private static int buscarArticuloPorCodigo(String codigo) {
        for (int i = 0; i < articulos.length; i++) {
            if (articulos[i][0].equals(codigo)) {
                return i;
            }
        }
        return -1;
    }
}

