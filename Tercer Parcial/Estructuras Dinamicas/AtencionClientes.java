package com.mycompany.uaeh;

import java.util.ArrayList;
import java.util.Scanner;

public class AtencionClientes {

    static class Cliente {
        int turno;
        String nombre;

        Cliente(int turno, String nombre) {
            this.turno = turno;
            this.nombre = nombre;
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Cliente> cola = new ArrayList<>();

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

    static int buscarTurno(int turno) {
        for (int i = 0; i < cola.size(); i++) {
            if (cola.get(i).turno == turno) {
                return i;
            }
        }
        return -1;
    }

    static void registrar() {
        int turno = leerEntero("Numero de turno: ");
        if (buscarTurno(turno) != -1) {
            System.out.println("Ese numero de turno ya esta en la fila.");
            return;
        }
        String nombre = leerTexto("Nombre del cliente: ");
        cola.add(new Cliente(turno, nombre));
        System.out.println("Cliente agregado al final de la fila.");
    }

    static void atender() {
        if (cola.isEmpty()) {
            System.out.println("No hay clientes en espera.");
            return;
        }
        Cliente c = cola.remove(0);
        System.out.println("Atendiendo a: Turno " + c.turno + " - " + c.nombre);
    }

    static void verSiguiente() {
        if (cola.isEmpty()) {
            System.out.println("No hay clientes en espera.");
            return;
        }
        Cliente c = cola.get(0);
        System.out.println("Siguiente cliente: Turno " + c.turno + " - " + c.nombre);
    }

    static void mostrarPendientes() {
        if (cola.isEmpty()) {
            System.out.println("No hay clientes en espera.");
            return;
        }
        System.out.println("--- Clientes pendientes ---");
        for (int i = 0; i < cola.size(); i++) {
            System.out.println((i + 1) + ". Turno " + cola.get(i).turno + " - " + cola.get(i).nombre);
        }
    }

    static void contar() {
        System.out.println("Clientes esperando: " + cola.size());
    }

    static void cancelarTurno() {
        if (cola.isEmpty()) {
            System.out.println("No hay clientes en espera.");
            return;
        }
        int turno = leerEntero("Numero de turno a cancelar: ");
        int pos = buscarTurno(turno);
        if (pos == -1) {
            System.out.println("No existe ese turno en la fila.");
        } else {
            Cliente c = cola.remove(pos);
            System.out.println("Se cancelo el turno " + c.turno + " (" + c.nombre + ").");
        }
    }

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== ATENCION DE CLIENTES =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Atender siguiente cliente");
            System.out.println("3. Ver siguiente cliente");
            System.out.println("4. Mostrar clientes pendientes");
            System.out.println("5. Cantidad de clientes esperando");
            System.out.println("6. Cancelar un turno");
            System.out.println("0. Salir");
            opcion = leerEntero("Elige una opcion: ");

            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    atender();
                    break;
                case 3:
                    verSiguiente();
                    break;
                case 4:
                    mostrarPendientes();
                    break;
                case 5:
                    contar();
                    break;
                case 6:
                    cancelarTurno();
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