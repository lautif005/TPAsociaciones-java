import java.util.ArrayList;
import java.util.Scanner;

class Nota {
    private String catedra;
    private double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    public String getCatedra() {
        return catedra;
    }
}

class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return notas.size() > 0 ? suma / notas.size() : 0;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public long getLegajo() {
        return legajo;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }
}

public class CargaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.println("Ingrese la cantidad de alumnos:");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("Ingrese el nombre completo del alumno:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el legajo del alumno:");
            long legajo = scanner.nextLong();
            scanner.nextLine();

            Alumno alumno = new Alumno(nombre, legajo);

            System.out.println("Ingrese la cantidad de notas:");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Ingrese la cátedra:");
                String catedra = scanner.nextLine();
                System.out.println("Ingrese la nota del examen:");
                double notaExamen = scanner.nextDouble();
                scanner.nextLine();

                alumno.agregarNota(new Nota(catedra, notaExamen));
            }

            alumnos.add(alumno);
        }

        System.out.println("Información de los alumnos y sus promedios:");
        for (Alumno alumno : alumnos) {
            System.out.println("Nombre: " + alumno.getNombreCompleto());
            System.out.println("Legajo: " + alumno.getLegajo());
            for (Nota nota : alumno.getNotas()) {
                System.out.println("Cátedra: " + nota.getCatedra() + " - Nota: " + nota.getNotaExamen());
            }
            System.out.println("Promedio: " + alumno.calcularPromedio());
            System.out.println();
        }

        scanner.close();
    }
}

