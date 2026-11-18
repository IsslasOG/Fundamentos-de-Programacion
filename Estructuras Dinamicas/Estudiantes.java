package com.mycompany.uaeh;

import java.util.ArrayList;
import java.util.Scanner;

public class Estudiantes {

    static class Estudiante {
        String matricula;
        String nombre;
        double calificacion;

        Estudiante(String matricula, String nombre, double calificacion) {
            this.matricula = matricula;
            this.nombre = nombre;
            this.calificacion = calificacion;
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Estudiante> estudiantes = new ArrayList<>();

    static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El dato no puede estar vacio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Entrada invalida. " + mensaje);
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    static double leerCalificacion() {
        double cal;
        do {
            System.out.print("Calificacion (0 - 10): ");
            while (!sc.hasNextDouble()) {
                sc.next();
                System.out.print("Entrada invalida. Calificacion (0 - 10): ");
            }
            cal = sc.nextDouble();
            sc.nextLine();
            if (cal < 0 || cal > 10) {
                System.out.println("La calificacion debe estar entre 0 y 10.");
            }
        } while (cal < 0 || cal > 10);
        return cal;
    }

    static int buscarIndice(String matricula) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).matricula.equals(matricula)) {
                return i;
            }
        }
        return -1;
    }

    static void imprimirEstudiante(Estudiante e) {
        System.out.println("Matricula: " + e.matricula + " | Nombre: " + e.nombre
                + " | Calificacion: " + e.calificacion);
    }

    static void registrar() {
        String matricula = leerTexto("Matricula: ");
        if (buscarIndice(matricula) != -1) {
            System.out.println("Ya existe un estudiante con esa matricula. No se registro.");
            return;
        }
        String nombre = leerTexto("Nombre: ");
        double cal = leerCalificacion();
        estudiantes.add(new Estudiante(matricula, nombre, cal));
        System.out.println("Estudiante registrado correctamente.");
    }

    static void mostrarOrdenados() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        ArrayList<Estudiante> copia = new ArrayList<>(estudiantes);

        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).nombre.compareToIgnoreCase(copia.get(j + 1).nombre) > 0) {
                    Estudiante temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }

        System.out.println("--- Estudiantes (orden alfabetico) ---");
        for (int i = 0; i < copia.size(); i++) {
            imprimirEstudiante(copia.get(i));
        }
    }

    static void buscar() {
        String matricula = leerTexto("Matricula a buscar: ");
        int pos = buscarIndice(matricula);
        if (pos == -1) {
            System.out.println("No se encontro el estudiante.");
        } else {
            imprimirEstudiante(estudiantes.get(pos));
        }
    }

    static void modificar() {
        String matricula = leerTexto("Matricula del estudiante a modificar: ");
        int pos = buscarIndice(matricula);
        if (pos == -1) {
            System.out.println("No se encontro el estudiante.");
            return;
        }
        System.out.println("Calificacion actual: " + estudiantes.get(pos).calificacion);
        estudiantes.get(pos).calificacion = leerCalificacion();
        System.out.println("Calificacion modificada correctamente.");
    }

    static void eliminar() {
        String matricula = leerTexto("Matricula del estudiante a eliminar: ");
        int pos = buscarIndice(matricula);
        if (pos == -1) {
            System.out.println("No se encontro el estudiante.");
        } else {
            estudiantes.remove(pos);
            System.out.println("Estudiante eliminado correctamente.");
        }
    }

    static void promedioGeneral() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        double suma = 0;
        for (int i = 0; i < estudiantes.size(); i++) {
            suma += estudiantes.get(i).calificacion;
        }
        System.out.println("Promedio general del grupo: " + (suma / estudiantes.size()));
    }

    static void mayorYMenor() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        Estudiante mayor = estudiantes.get(0);
        Estudiante menor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            Estudiante e = estudiantes.get(i);
            if (e.calificacion > mayor.calificacion) {
                mayor = e;
            }
            if (e.calificacion < menor.calificacion) {
                menor = e;
            }
        }
        System.out.println("Calificacion mas alta:");
        imprimirEstudiante(mayor);
        System.out.println("Calificacion mas baja:");
        imprimirEstudiante(menor);
    }

    static void mayoresANueve() {
        boolean hay = false;
        System.out.println("--- Estudiantes con calificacion mayor a 9 ---");
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).calificacion > 9) {
                imprimirEstudiante(estudiantes.get(i));
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("Ningun estudiante tiene calificacion mayor a 9.");
        }
    }

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== ADMINISTRACION DE ESTUDIANTES =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Mostrar estudiantes (orden alfabetico)");
            System.out.println("3. Buscar por matricula");
            System.out.println("4. Modificar calificacion");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Promedio general del grupo");
            System.out.println("7. Calificacion mas alta y mas baja");
            System.out.println("8. Estudiantes con calificacion mayor a 9");
            System.out.println("0. Salir");
            opcion = leerEntero("Elige una opcion: ");

            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    mostrarOrdenados();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    modificar();
                    break;
                case 5:
                    eliminar();
                    break;
                case 6:
                    promedioGeneral();
                    break;
                case 7:
                    mayorYMenor();
                    break;
                case 8:
                    mayoresANueve();
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }
}