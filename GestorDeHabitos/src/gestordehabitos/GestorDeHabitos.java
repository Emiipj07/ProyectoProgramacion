package gestordehabitos;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GestorDeHabitos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dias = 7;

        int[] sueno = new int[dias];
        int[] agua = new int[dias];
        int[] ejercicio = new int[dias];
        int[] pasos = new int[dias];
        int[] comida = new int[dias];
        int[] estres = new int[dias];
        int[] pantalla = new int[dias];

        int puntosSueno = 0;
        int puntosAgua = 0;
        int puntosEjercicio = 0;
        int puntosPasos = 0;
        int puntosComida = 0;
        int puntosEstres = 0;
        int puntosPantalla = 0;

        System.out.println("GESTOR DE HÁBITOS");
        System.out.println("==================");

        for (int i = 0; i < dias; i++) {
            System.out.println("\nDía " + (i + 1));
            System.out.println("------------------");

            do {
                sueno[i] = ingresarEntero(sc, "Horas de sueño: ");
                if (sueno[i] > 24 || sueno[i] < 0) {
                    System.out.println("No puede colocar más de 24 horas!");
                }
            } while (sueno[i] > 24 || sueno[i] < 0);

            do {
                agua[i] = ingresarEntero(sc, "Agua (ml): ");
                if (agua[i] > 8000 || agua[i] < 0) {
                    System.out.println("No puede colocar más de 8000 ml!");
                }
            } while (agua[i] > 8000 || agua[i] < 0);

            do {
                ejercicio[i] = ingresarEntero(sc, "Ejercicio (min): ");
                if (ejercicio[i] > 300 || ejercicio[i] < 0) {
                    System.out.println("No puede colocar más de 300 minutos!");
                }
            } while (ejercicio[i] > 300 || ejercicio[i] < 0);

            do {
                pasos[i] = ingresarEntero(sc, "Pasos del día: ");
                if (pasos[i] > 50000 || pasos[i] < 0) {
                    System.out.println("No puede colocar más de 50000 pasos!");
                }
            } while (pasos[i] > 50000 || pasos[i] < 0);

            comida[i] = ingresarEntero(sc, "¿Comiste saludable? (1 = Sí / 0 = No): ");

            do {
                estres[i] = ingresarEntero(sc, "Nivel de estrés (1 a 5): ");
                if (estres[i] > 5 || estres[i] < 0) {
                    System.out.println("No puede colocar más de 5!");
                }
            } while (estres[i] > 5 || estres[i] < 0);

            do {
                pantalla[i] = ingresarEntero(sc, "Horas de pantalla: ");
                if (pantalla[i] > 24 || pantalla[i] < 0) {
                    System.out.println("No puede colocar más de 24 horas!");
                }
            } while (pantalla[i] > 24 || pantalla[i] < 0);

            if (sueno[i] >= 8) {
                puntosSueno++;
            }
            if (agua[i] >= 2000) {
                puntosAgua++;
            }
            if (ejercicio[i] >= 30) {
                puntosEjercicio++;
            }
            if (pasos[i] >= 8000) {
                puntosPasos++;
            }
            if (comida[i] == 1) {
                puntosComida++;
            }
            if (estres[i] <= 2) {
                puntosEstres++;
            }
            if (pantalla[i] <= 6) {
                puntosPantalla++;
            }
            System.out.println("Datos del día registrados correctamente");
            System.out.println();
        }
        guardarArchivo(sueno, agua, ejercicio, pasos, comida, estres, pantalla, dias);
        MostrarResultados(puntosSueno, puntosAgua, puntosEjercicio, puntosPasos, puntosComida, puntosEstres, puntosPantalla);

        System.out.println("\nGracias por usar el Gestor de Hábitos");
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

    public static void guardarArchivo(int[] sueno, int[] agua, int[] ejercicio, int[] comida, int[] estres, int[] pantalla, int[] puntos, int dias) {
        try {
            FileWriter archivo = new FileWriter("data/Habitos.txt", true);
            PrintWriter pw = new PrintWriter(archivo);

            for (int i = 0; i < dias; i++) {
                pw.println((i + 1) + "," + sueno[i] + "," + agua[i] + "," + ejercicio[i] + "," + comida[i] + "," + estres[i] + "," + pantalla[i] + "," + puntos[i]);
            }
            archivo.close();
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar archivo." + e.getMessage());
        }
    }

    public static String evaluarActividad(int puntos) {
        if (puntos <= 4) {
            return "Ups! No alcanzaste la meta semanal, debes mejorar";
        } else {
            return "FELICIDADES! Sigue así";
        }
    }

    public static void MostrarResultados(int puntosSueno, int puntosAgua, int puntosEjercicio, int puntosPasos, int puntosComida, int puntosEstres, int puntosPantalla) {
        System.out.println("+------------------------------------------------------+");
        System.out.println("|                      RESULTADOS                      |");
        System.out.println("+------------------------------------------------------+");
        System.out.println("Sueño: " + puntosSueno + "/7 - " + evaluarActividad(puntosSueno));
        System.out.println("Agua: " + puntosAgua + "/7 - " + evaluarActividad(puntosAgua));
        System.out.println("Ejercicio: " + puntosEjercicio + "/7 - " + evaluarActividad(puntosEjercicio));
        System.out.println("Pasos: " + puntosPasos + "/7 - " + evaluarActividad(puntosPasos));
        System.out.println("Comida saludable: " + puntosComida + "/7 - " + evaluarActividad(puntosComida));
        System.out.println("Estrés controlado: " + puntosEstres + "/7 - " + evaluarActividad(puntosEstres));
        System.out.println("Pantalla: " + puntosPantalla + "/7 - " + evaluarActividad(puntosPantalla));

    }
}
