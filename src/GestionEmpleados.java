import java.util.Scanner;

public class GestionEmpleados {
    
    //Vectores

    static String[] nombres = new String[50];
    static double[] salarios = new double[50];

    //Contador de empleados que se registran
    
    static int n = 0;

    //Proceso para el menú
    static Scanner leer = new Scanner(System.in);
    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println();
            System.out.println("=============================================");
            System.out.println("        SISTEMA DE GESTION DE EMPLEADOS");
            System.out.println("      Estudiante: Santiago Velasquez C");
            System.out.println("=============================================");
            System.out.println("1. Registrar empleados");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Calcular promedio salarial");
            System.out.println("4. Modificar salario");
            System.out.println("5. Buscar empleado por nombre");
            System.out.println("6. Mostrar empleado con MENOR salario");
            System.out.println("7. Mostrar empleado con MAYOR salario");
            System.out.println("8. Salir");
            opcion = leerEntero("Seleccione una opción:");

            switch (opcion) {
                case 1:
                    registrarEmpleados();
                    break;
                case 2:
                    mostrarEmpleados();
                    break;
                case 3:
                    mostrarPromedio();
                    break;
                case 4:
                    modificarSalarios();
                    break;
                case 5:
                    buscarEmpleado();
                    break;
                case 6:
                    mostrarMenorSalario();
                    break;
                case 7:
                    mostrarMayorSalario();
                    break;
                case 8:
                    System.out.println("Hasta Pronto!!!");
                    break;
                default:
                    System.out.println("Opción no valida");
            }

        }   while (opcion !=8);
    }

    public static int leerEntero(String mensaje){
        int numero = 0;
        boolean valido =false;

        do { 
            try {
                System.out.print(mensaje);
                numero = leer.nextInt();
                leer.nextLine();
                valido = true;
            } catch (Exception e) {
                System.out.println("Error: debe ingresar un numero entero");
                leer.nextLine();
            }
        } while (!valido);

        return numero;
    }

    public static double leerDecimal(String mensaje){
        double numero = 0;
        boolean valido = false;
        
        do {
            try {
                System.out.print(mensaje);
                numero = leer.nextDouble();
                leer.nextLine();
                valido = true;
            } catch (Exception e) {
                System.out.println("Error: debe ingresar un valor numerico");
                leer.nextLine();
            }
        }   while(!valido);

        return numero;
    }

        //Proceso para la opcion 1. Registrar Empleados
    public static void registrarEmpleados(){
        int cantidad = leerEntero("Cuantos Empleados desea registrar? ");

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que 0");     
        } else if (n + cantidad > 50){
            System.out.println("No hay espacio suficiente. Espacios Disponible: " + (50 -n));
        } else {
            for (int i = 0; i < cantidad; i++){
                System.out.println();
                System.out.println("Empleado " + (n+1));
            
                System.out.println("Nombre: ");
                nombres[n] = leer.nextLine().toUpperCase();

                do {
                    salarios[n] = leerDecimal("Salario: ");
                    if (salarios[n] <= 0){
                        System.out.println("El salario debe ser mayor que 0");
                    }
                } while(salarios[n] <= 0);

                n++;
            }
            System.out.println();
            System.out.println("Registro completado. Total de empleados: " + n);
        }
    }
        //Funcion con retorno para sumar todos los salarios
    public static double calcularNominaTotal() {
        double total = 0;
        for (int i = 0; i < n; i++){
            total = total + salarios[i];
        }
        return total;
    }   
     //Proceso para la opcion 2. Mostrar Empleados
    public static void mostrarEmpleados(){
        if (n == 0){
            System.out.println("No hay empleados registrados");
        } else {
            System.out.println();
            System.out.println("-----------------------------------------------");
            System.out.printf("%5s %-25s %15s%n", "No.", "NOMBRE", "SALARIO");
            System.out.println("-----------------------------------------------");

            for (int i = 0; i < n; i++) {
                System.out.printf("%-5d %-25s %15.2f%n", (i + 1), nombres[i], salarios[i]);
            }

            System.out.println("-----------------------------------------------");
            System.out.printf("%-31s %15.2f%n", "TOTAL NOMINA:", calcularNominaTotal());
        }
    }
        //Funcion con retorno para calcular el promedio de los salarios
    public static double calcularPromedio() {
        return calcularNominaTotal() / n;
    }
        //Proceso para la opcion 3. Mostrar Promedio Salarial
    public static void mostrarPromedio(){
        if (n == 0) {
            System.out.println("No hay empleados registrados");
        } else {
            System.out.println();
            System.out.printf("Promedio salarial de %d empleados: %.2f%n", n, calcularPromedio());
        }
    }
        //Funcion con retorno para buscar un nombre y traer su posicion
    public static int buscarIndicePorNombre(String nombre){
        for (int i = 0; i < n; i++){
            if (nombres[i].equalsIgnoreCase(nombre)) {
                return i;
            } 
        }
        return -1;    
    }
        //Proceso para la opcion 5. Buscar un empleado por nombre
    public static void buscarEmpleado() {
        if (n == 0) {
            System.out.println("No hay empleados registrados");
        } else {
            System.out.print("Ingrese el nombre a buscar: ");
            String nombre = leer.nextLine();

            int pos = buscarIndicePorNombre(nombre);

            if (pos == -1) {
                System.out.println("Empleado no encontrado");
            } else {
                System.out.println();
                System.out.printf("%-25s %15s%n", "NOMBRE", "SALARIO");
                System.out.printf("%-25s %15.2f%n", nombres[pos], salarios[pos]);
            }
        }
    }
    public static void modificarSalarios(){
        if (n==0) {
            System.out.println("No hay empleados registrados");
        } else {
            System.out.println("Ingrese el nombre del empleado: ");
            String nombre = leer.nextLine();

            int pos = buscarIndicePorNombre(nombre);

            if (pos == -1){ 
                System.out.println("Empleado no encontrado");
            } else {
                System.out.printf("Salario actual de %s: %.2f%n", nombres[pos], salarios[pos]);

                double nuevoSalario;
                do { 
                    nuevoSalario = leerDecimal("Nuevo Salario: ");

                    if (nuevoSalario <= 0){
                        System.out.println("El salario debe ser mayor que 0");
                    }
                } while (nuevoSalario <= 0);

                salarios[pos] = nuevoSalario;
                System.out.println("Salario actualizado correctamente");
            }
        }
    }
        //Funciones con retorno para buscar el menor y el mayor salario entre los empleados reistrados
    public static int buscarIndiceMenorSalario() {
        int indice = 0;
        for (int i = 1; i < n; i++){
            if (salarios[i] < salarios[indice]) {
                indice = i;
            }
        }
        return indice;
    }

    public static int buscarIndiceMayorSalario(){
        int indice = 0;
        for (int i = 1; i < n; i++){
            if(salarios[i] > salarios[indice]){
                indice = i;
            }
        }
        return indice;
    }
        //Proceso para la opcion 6. Mostra el empleado con el menor salario
        
    public static void mostrarMenorSalario(){
        if (n == 0) {
            System.out.println("No hay empleados registrados");
        } else {
            int pos = buscarIndiceMenorSalario();
            System.out.println();
            System.out.println("El empleado con MENOR salario");
            System.out.printf("%-25s %15s%n", "NOMBRE", "SALARIO");
            System.out.printf("%-25s %15.2f%n", nombres[pos], salarios[pos]);
        }
    }
        //Proceso para la opcion 7. Mostrar el empleado con el mayor salario
    public static void mostrarMayorSalario(){
        if (n == 0){
            System.out.println("No hay empleados registrados");
        } else {
            int pos = buscarIndiceMayorSalario();
            System.out.println();
            System.out.println("El empleado con MAYOR salario");
            System.out.printf("%-25s %15s%n", "NOMBRE", "SALARIO");
            System.out.printf("%-25s %15.2f%n", nombres[pos], salarios[pos]);
        }
    }
}