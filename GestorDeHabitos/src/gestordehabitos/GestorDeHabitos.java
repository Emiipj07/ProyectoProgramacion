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
        int[] puntos = new int[dias];

        System.out.println("GESTOR DE HÁBITOS");
        System.out.println("------------------");

        for (int i = 0; i < dias; i++) {
            System.out.println("\nDía " + (i + 1));
            do {
                sueno[i] = ingresarEntero(sc, "Horas de sueño: ");
                if (sueno[i] > 24) {
                    System.out.println("No puede colocar más de 24 horas!");
                }
            } while (sueno[i] > 24);
            
            do {
                agua[i] = ingresarEntero(sc, "Agua (ml): ");
                if (agua[i] > 8000){
                    System.out.println("No puede colocar más de 8000 Ml!");
                }
            } while(agua[i] > 8000);
            do {
                ejercicio[i] = ingresarEntero(sc, "Ejercicio (min): ");
                if(ejercicio[i] > 300){
                    System.out.println("No puede colocar más de 300 minutos!");
                }
            } while(ejercicio[i] > 300);
            
            comida[i] = ingresarEntero(sc, "¿Comiste saludable? (1 = Sí / 0 = No): ");
            do{
                estres[i] = ingresarEntero(sc, "Nivel de estrés (1 a 5): ");
                if( estres[i] > 5){
                    System.out.println("No puede colocar más de 5!");
                }
            } while(estres[i] > 5);
            do{
                pantalla[i] = ingresarEntero(sc, "Horas de pantalla: ");
                if (pantalla[i] > 24){
                    System.out.println("No puede colocar más de 24 horas!");
                }
            } while(pantalla[i] > 24);

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
            System.out.println("Gracias por usar el programa. Nos vemos mañana!");
        }

        guardarArchivo(sueno, agua, ejercicio, comida, estres, pantalla, puntos, dias);

        System.out.println("\nDatos de la semana guardados en archivo.");
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
                pw.println((i + 1) + "," + sueno[i] + "," + agua[i] + "," + ejercicio[i] + "," +  "," + comida[i] + "," + estres[i] + "," + pantalla[i] + "," + puntos[i]);
            }
            archivo.close();
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar archivo." + e.getMessage());
        }
    }
    public static void MostrarResultados(){
        System.out.println("+-----------------------------------------------+");
        System.out.println("+                 RESULTADOS                    +");
        System.out.println("+-----------------------------------------------+");
        System.out.println("++");
    }
}
