package gestordehabitos;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GestorDeHabitos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dias = 7;

        double[] sueno = new double[dias];
        double[] agua = new double[dias];
        double[] ejercicio = new double[dias];
        int[] pasos = new int[dias];
        int[] comida = new int[dias];
        int[] estres = new int[dias];
        double[] pantalla = new double[dias];
        int[] puntos = new int[dias];

        System.out.println("GESTOR DE HÁBITOS");
        System.out.println("------------------");

        for (int i = 0; i < dias; i++) {
            System.out.println("\nDía " + (i + 1));
            sueno[i] = ingresarDecimales(sc, "Horas de sueño: ");
            agua[i] = ingresarDecimales(sc, "Agua (ml): ");
            ejercicio[i] = ingresarDecimales(sc, "Ejercicio (min): ");
            pasos[i] = ingresarEntero(sc, "Pasos caminados: ");
            comida[i] = ingresarEntero(sc, "¿Comiste saludable? (1 = Sí / 0 = No): ");
            estres[i] = ingresarEntero(sc, "Nivel de estrés (1 a 5): ");
            pantalla[i] = ingresarDecimales(sc, "Horas de pantalla: ");

            puntos[i] = 0;
            if (sueno[i] >= 8) {
                puntos[i]++;
            }
            if (agua[i] >= 2000) {
                puntos[i]++;
            }
            if (ejercicio[i] >= 30) {
                puntos[i]++;
            }
            if (pasos[i] >= 8000) {
                puntos[i]++;
            }
            if (comida[i] == 1) {
                puntos[i]++;
            }
            if (pantalla[i] <= 6) {
                puntos[i]++;
            }
        }

        guardarArchivo(sueno, agua, ejercicio, pasos,comida, estres, pantalla, puntos, dias);

        System.out.println("\nDatos de la semana guardados en archivo.");
    }

    public static double ingresarDecimales(Scanner sc, String mensaje) {
        double valor = 0;
        boolean bien = false;

        while (!bien) {
            try {
                System.out.print(mensaje);
                valor = sc.nextDouble();
                bien = true;
            } catch (Exception e) {
                System.out.println("Error: ingrese un número.");
                sc.next();
            }
        }
        return valor;
    }

    public static int ingresarEntero(Scanner sc, String mensaje) {
        int valor = 0;
        boolean bien = false;

        while (!bien) {
            try {
                System.out.print(mensaje);
                valor = sc.nextInt();
                bien = true;
            } catch (Exception e) {
                System.out.println("Error: ingrese un número entero.");
                sc.next();
            }
        }
        return valor;
    }

    public static void guardarArchivo(double[] sueno, double[] agua, double[] ejercicio, int[] pasos, int[] comida, int[] estres, double[] pantalla, int[] puntos, int dias) {
        try {
            FileWriter archivo = new FileWriter("data/Habitos.txt", true);
            PrintWriter pw = new PrintWriter(archivo);

            for (int i = 0; i < dias; i++) {
                pw.println((i + 1) + "," + sueno[i] + "," + agua[i] + "," + ejercicio[i] + "," + pasos[i] + "," + comida[i] + "," + estres[i] + "," + pantalla[i] + "," + puntos[i]);
            }
            archivo.close();
            pw.close(); 
        } catch (Exception e) {
            System.out.println("Error al guardar archivo." + e.getMessage());
        }
    }
}
