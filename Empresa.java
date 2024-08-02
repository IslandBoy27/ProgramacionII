/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProgramacionII;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jayma
 */

public class Empresa {
    static Scanner lea = new Scanner(System.in);
    static List<Empleado> empleados = new ArrayList<>();

    public static void main(String[] args) {
        int op;

        do {
            System.out.println("1- Agregar Empleado");
            System.out.println("2- Pagar Empleado");
            System.out.println("3- Lista de Empleados");
            System.out.println("4- Sub Menu especifico");
            System.out.println("5- Salir");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();

            switch(op) {
                case 1:
                    hire();
                    break;
                case 2:
                    pay();
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    submenu();
                    break;
            }
        } while(op != 5);
    }

    private static Empleado search(int cod) {
        for (Empleado emp : empleados) {
            if (emp.getCodigo() == cod) {
                return emp;
            }
        }
        return null;
    }

    private static void hire() {
        System.out.print("Tipo de Empleado (COMUN, HORA, VENTA, TEMPORAL): ");
        String tipo = lea.next().toUpperCase();

        System.out.print("Código: ");
        int codigo = lea.nextInt();

        if (search(codigo) != null) {
            System.out.println("Código ya existente.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = lea.next();

        switch (tipo) {
            case "COMUN":
                System.out.print("Salario: ");
                double salario = lea.nextDouble();
                empleados.add(new EmpleadoComun(codigo, nombre, salario));
                break;
            case "HORA":
                empleados.add(new EmpleadoPorHora(codigo, nombre));
                break;
            case "VENTA":
                System.out.print("Salario base: ");
                double salarioVenta = lea.nextDouble();
                empleados.add(new EmpleadoPorVenta(codigo, nombre, salarioVenta));
                break;
            case "TEMPORAL":
                empleados.add(new EmpleadoTemporal(codigo, nombre));
                break;
            default:
                System.out.println("Tipo de empleado no válido.");
                break;
        }
    }

    private static void pay() {
        System.out.print("Código del empleado: ");
        int codigo = lea.nextInt();

        Empleado emp = search(codigo);
        if (emp != null) {
            System.out.println("Pago: " + emp.pagar());
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void list() {
        for (Empleado emp : empleados) {
            System.out.println(emp);
        }
    }

    private static void submenu() {
        int op;
        do {
            System.out.println("1- Fecha Fin Contrato a Temporales");
            System.out.println("2- Ingresar Venta");
            System.out.println("3- Ingresar Horas de Trabajo");
            System.out.println("4- Regresar al Menu Principal");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();

            switch(op) {
                case 1:
                    setFin();
                    break;
                case 2:
                    ventas();
                    break;
                case 3:
                    horas();
                    break;
            }
        } while(op != 4);
    }

    private static void setFin() {
        System.out.print("Código del empleado: ");
        int codigo = lea.nextInt();

        Empleado emp = search(codigo);
        if (emp instanceof EmpleadoTemporal empleadoTemporal) {
            System.out.print("Año de finalización: ");
            int year = lea.nextInt();
            System.out.print("Mes de finalización: ");
            int mes = lea.nextInt();
            System.out.print("Día de finalización: ");
            int dia = lea.nextInt();

            empleadoTemporal.setFinContrato(year, mes - 1, dia); // -1 porque Calendar mes empieza en 0
        } else {
            System.out.println("Empleado no encontrado o no es temporal.");
        }
    }

    private static void ventas() {
        System.out.print("Código del empleado: ");
        int codigo = lea.nextInt();

        Empleado emp = search(codigo);
        if (emp instanceof EmpleadoPorVenta empleadoPorVenta) {
            System.out.print("Monto de la venta: ");
            double monto = lea.nextDouble();
            empleadoPorVenta.addVenta(monto);
        } else {
            System.out.println("Empleado no encontrado o no es por ventas.");
        }
    }

    private static void horas() {
        System.out.print("Código del empleado: ");
        int codigo = lea.nextInt();

        Empleado emp = search(codigo);
        if (emp instanceof EmpleadoPorHora) {
            System.out.print("Horas trabajadas: ");
            int horas = lea.nextInt();
            ((EmpleadoPorHora) emp).setHorasT(horas);
        } else {
            System.out.println("Empleado no encontrado o no es por horas.");
        }
    }
}
