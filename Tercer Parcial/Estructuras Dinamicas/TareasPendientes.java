package com.mycompany.uaeh;

import java.util.ArrayList;
import java.util.Scanner;

public class TareasPendientes {

    static class Tarea {
        int id;
        String descripcion;
        int prioridad;
        boolean completada;

        Tarea(int id, String descripcion, int prioridad) {
            this.id = id;
            this.descripcion = descripcion;
            this.prioridad = prioridad;
            this.completada = false;
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Tarea> tareas = new ArrayList<>();

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

    static int leerPrioridad() {
        int p;
        do {
            p = leerEntero("Prioridad (1 = Alta, 2 = Media, 3 = Baja): ");
            if (p < 1 || p > 3) {
                System.out.println("La prioridad debe ser 1, 2 o 3.");
            }
        } while (p < 1 || p > 3);
        return p;
    }

    static int buscarIndicePorId(int id) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    static String textoPrioridad(int prioridad) {
        String texto;
        switch (prioridad) {
            case 1:
                texto = "Alta";
                break;
            case 2:
                texto = "Media";
                break;
            default:
                texto = "Baja";
        }
        return texto;
    }

    static void imprimirTarea(Tarea t) {
        String estado;
        if (t.completada) {
            estado = "Completada";
        } else {
            estado = "Pendiente";
        }
        System.out.println("ID: " + t.id + " | " + t.descripcion + " | Prioridad: "
                + textoPrioridad(t.prioridad) + " | Estado: " + estado);
    }

    static Tarea crearTarea() {
        int id = leerEntero("Identificador: ");
        if (buscarIndicePorId(id) != -1) {
            System.out.println("Ya existe una tarea con ese identificador.");
            return null;
        }
        String descripcion = leerTexto("Descripcion: ");
        int prioridad = leerPrioridad();
        return new Tarea(id, descripcion, prioridad);
    }

    static void agregarInicio() {
        Tarea t = crearTarea();
        if (t != null) {
            tareas.add(0, t);
            System.out.println("Tarea agregada al inicio.");
        }
    }

    static void agregarFinal() {
        Tarea t = crearTarea();
        if (t != null) {
            tareas.add(t);
            System.out.println("Tarea agregada al final.");
        }
    }

    static void insertarEnPosicion() {
        int pos = leerEntero("Posicion donde insertar (1 a " + (tareas.size() + 1) + "): ");
        if (pos < 1 || pos > tareas.size() + 1) {
            System.out.println("Posicion invalida.");
            return;
        }
        Tarea t = crearTarea();
        if (t != null) {
            tareas.add(pos - 1, t);
            System.out.println("Tarea insertada en la posicion " + pos + ".");
        }
    }

    static void eliminarPorId() {
        int id = leerEntero("Identificador de la tarea a eliminar: ");
        int pos = buscarIndicePorId(id);
        if (pos == -1) {
            System.out.println("No se encontro la tarea.");
        } else {
            tareas.remove(pos);
            System.out.println("Tarea eliminada.");
        }
    }

    static void buscarPorDescripcion() {
        String texto = leerTexto("Descripcion a buscar: ").toLowerCase();
        boolean encontrada = false;
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).descripcion.toLowerCase().contains(texto)) {
                imprimirTarea(tareas.get(i));
                encontrada = true;
            }
        }
        if (!encontrada) {
            System.out.println("No se encontraron tareas con esa descripcion.");
        }
    }

    static void mostrarPendientes() {
        boolean hay = false;
        System.out.println("--- Tareas pendientes ---");
        for (int i = 0; i < tareas.size(); i++) {
            if (!tareas.get(i).completada) {
                System.out.print((i + 1) + ". ");
                imprimirTarea(tareas.get(i));
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay tareas pendientes.");
        }
    }

    static void marcarCompletada() {
        int id = leerEntero("Identificador de la tarea a completar: ");
        int pos = buscarIndicePorId(id);
        if (pos == -1) {
            System.out.println("No se encontro la tarea.");
        } else if (tareas.get(pos).completada) {
            System.out.println("La tarea ya estaba completada.");
        } else {
            tareas.get(pos).completada = true;
            System.out.println("Tarea marcada como completada.");
        }
    }

    static void primeraYUltima() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        System.out.println("Primera tarea:");
        imprimirTarea(tareas.get(0));
        System.out.println("Ultima tarea:");
        imprimirTarea(tareas.get(tareas.size() - 1));
    }

    static void recorrerInverso() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        System.out.println("--- Tareas en orden inverso ---");
        for (int i = tareas.size() - 1; i >= 0; i--) {
            System.out.print((i + 1) + ". ");
            imprimirTarea(tareas.get(i));
        }
    }

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== GESTION DE TAREAS PENDIENTES =====");
            System.out.println("1. Agregar tarea al inicio");
            System.out.println("2. Agregar tarea al final");
            System.out.println("3. Insertar tarea en una posicion");
            System.out.println("4. Eliminar tarea por identificador");
            System.out.println("5. Buscar tarea por descripcion");
            System.out.println("6. Mostrar tareas pendientes");
            System.out.println("7. Marcar tarea como completada");
            System.out.println("8. Mostrar primera y ultima tarea");
            System.out.println("9. Recorrer lista en orden inverso");
            System.out.println("0. Salir");
            opcion = leerEntero("Elige una opcion: ");

            switch (opcion) {
                case 1:
                    agregarInicio();
                    break;
                case 2:
                    agregarFinal();
                    break;
                case 3:
                    insertarEnPosicion();
                    break;
                case 4:
                    eliminarPorId();
                    break;
                case 5:
                    buscarPorDescripcion();
                    break;
                case 6:
                    mostrarPendientes();
                    break;
                case 7:
                    marcarCompletada();
                    break;
                case 8:
                    primeraYUltima();
                    break;
                case 9:
                    recorrerInverso();
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