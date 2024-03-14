package co.vinni.soapproyectobase;

import co.vinni.soapproyectobase.entidades.Ciudadano;
import co.vinni.soapproyectobase.repositorios.RepositorioCiudadano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;


@SpringBootApplication
public class SoapProyectobaseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SoapProyectobaseApplication.class, args);
    }

    @Autowired
    RepositorioCiudadano repoCiudadano;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar Ciudadano");
            System.out.println("2. Consultar Ciudadanos");
            System.out.println("3. Consultar Ciudadano por Número de Documento");
            System.out.println("4. Modificar Ciudadano por Número de Documento");
            System.out.println("5. Eliminar Ciudadano por Número de Documento");
            System.out.println("6. Salir");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarCiudadano();
                    break;
                case 2:
                    consultarCiudadanos();
                    break;
                case 3:
                    consultarCiudadanoPorNumeroDoc();
                    break;
                case 4:
                    modificarCiudadano();
                    break;
                case 5:
                    eliminarCiudadano();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private void agregarCiudadano() {

        System.out.println("Ingrese los datos del ciudadano:");
        System.out.println("Número de documento:");
        long numeroDoc = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Fecha de nacimiento (yyyy-MM-dd):");
        String fechaNacimientoStr = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            return;
        }


        Calendar calNacimiento = Calendar.getInstance();
        calNacimiento.setTime(fechaNacimiento);
        Calendar calHoy = Calendar.getInstance();
        int edad = calHoy.get(Calendar.YEAR) - calNacimiento.get(Calendar.YEAR);
        if (calHoy.get(Calendar.DAY_OF_YEAR) < calNacimiento.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        System.out.println("Tipo de documento:");
        String tipoDoc ;

        if (edad >= 0 && edad <= 7) {
            tipoDoc = "Registro Civil";
        } else if (edad >= 8 && edad <= 17) {
            tipoDoc = "Identidad";
        } else {
            tipoDoc = "Cédula";
        }

        System.out.println("Apellido del padre:");
        String apellidoPadre = scanner.nextLine();

        System.out.println("Apellido de la madre:");
        String apellidoMadre = scanner.nextLine();

        System.out.println("Nombre del padre:");
        String papa = scanner.nextLine();

        System.out.println("Nombre de la madre:");
        String mama = scanner.nextLine();

        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setnumeroDoc(numeroDoc);
        ciudadano.setNombre(nombre);
        ciudadano.setFechanac(fechaNacimiento);
        ciudadano.setTipodoc(tipoDoc);
        ciudadano.setEdad(edad);
        ciudadano.setPadreApellido(apellidoPadre);
        ciudadano.setMadreApellido(apellidoMadre);
        ciudadano.setPapa(papa);
        ciudadano.setMama(mama);

        repoCiudadano.save(ciudadano);
        System.out.println("Ciudadano agregado correctamente.");
    }

    private void consultarCiudadanos() {

        List<Ciudadano> ciudadanos = repoCiudadano.findAll();
        if (ciudadanos.isEmpty()) {
            System.out.println("No se encontraron ciudadanos.");
        } else {
            System.out.println("Lista de ciudadanos:");
            for (Ciudadano ciudadano : ciudadanos) {
                System.out.println("Nombre: " + ciudadano.getNombre());
                System.out.println("Fecha de nacimiento: " +ciudadano.getFechanac());
                System.out.println("Tipo de documento: " + ciudadano.getTipodoc());
                System.out.println("Nro de documento : " + ciudadano.getnumeroDoc());
                System.out.println("Padre: " + ciudadano.getPadre());
                System.out.println("Apellido del padre: " + ciudadano.getPadreApellido());
                System.out.println("Madre: " + ciudadano.getMadre());
                System.out.println("Apellido de la madre: " + ciudadano.getMadreApellido());
                System.out.println("Edad: " + ciudadano.getEdad());
                System.out.println("-----------------------");
            }
        }
    }




    private void consultarCiudadanoPorNumeroDoc() {
        System.out.println("Ingrese el número de documento del ciudadano a consultar:");
        long numeroDoc = scanner.nextLong();
        scanner.nextLine();

        List<Ciudadano> ciudadanos = repoCiudadano.findByNumeroDoc(numeroDoc);
        if (ciudadanos.isEmpty()) {
            System.out.println("No se encontró ningún ciudadano con ese número de documento.");
        } else {
            System.out.println("Ciudadano(s) encontrado(s):");
            for (Ciudadano ciudadano : ciudadanos) {
                System.out.println("Número de documento: " + ciudadano.getnumeroDoc());
                System.out.println("Nombre: " + ciudadano.getNombre());
                System.out.println("Fecha de nacimiento: " + ciudadano.getFechanac());
                System.out.println("Tipo de documento: " + ciudadano.getTipodoc());
                System.out.println("Edad: " + ciudadano.getEdad());
                System.out.println("Apellido del padre: " + ciudadano.getPadreApellido());
                System.out.println("Apellido de la madre: " + ciudadano.getMadreApellido());
                System.out.println("Nombre del padre: " + ciudadano.getPapa());
                System.out.println("Nombre de la madre: " + ciudadano.getMama());
                System.out.println("---------------------------------");
            }
        }
    }

    private void  modificarCiudadano() {

        System.out.println("Ingrese el número de documento del ciudadano a modificar:");
        long numeroDoc = scanner.nextLong();
        scanner.nextLine();

        List<Ciudadano> ciudadanos = repoCiudadano.findByNumeroDoc(numeroDoc);
        if (ciudadanos.isEmpty()) {
            System.out.println("No se encontró ningún ciudadano con ese número de documento.");
        } else {
            for (Ciudadano ciudadano : ciudadanos) {

                System.out.println("Información actual del ciudadano:");
                mostrarInformacionCiudadano(ciudadano);


                System.out.println("Ingrese los nuevos datos del ciudadano:");

                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                ciudadano.setNombre(nuevoNombre);

                System.out.println("Nuevo Apellido del padre:");
                String nuevoapellidoPadre = scanner.nextLine();
                ciudadano.setPadreApellido(nuevoapellidoPadre);

                System.out.println("Nuevo Apellido de la madre:");
                String nuevoapellidoMadre = scanner.nextLine();
                ciudadano.setMadreApellido(nuevoapellidoMadre);

                repoCiudadano.save(ciudadano);

                System.out.println("Ciudadano modificado correctamente.");
            }
        }
    }

    private void mostrarInformacionCiudadano(Ciudadano ciudadano) {
        System.out.println("Número de documento: " + ciudadano.getnumeroDoc());
        System.out.println("Nombre: " + ciudadano.getNombre());
        System.out.println("Fecha de nacimiento: " + ciudadano.getFechanac());
        System.out.println("Tipo de documento: " + ciudadano.getTipodoc());
        System.out.println("Edad: " + ciudadano.getEdad());
        System.out.println("Apellido del padre: " + ciudadano.getPadreApellido());
        System.out.println("Apellido de la madre: " + ciudadano.getMadreApellido());
        System.out.println("Nombre del padre: " + ciudadano.getPapa());
        System.out.println("Nombre de la madre: " + ciudadano.getMama());
        System.out.println("---------------------------------");
    }

    private void eliminarCiudadano() {
        System.out.println("Ingrese el número de documento del ciudadano a eliminar:");
        long numeroDoc = scanner.nextLong();
        scanner.nextLine();

        List<Ciudadano> ciudadanos = repoCiudadano.findByNumeroDoc(numeroDoc);
        if (ciudadanos.isEmpty()) {
            System.out.println("No se encontró ningún ciudadano con ese número de documento.");
        } else {
            for (Ciudadano ciudadano : ciudadanos) {
                System.out.println("Información actual del ciudadano:");
                mostrarInformacionCiudadano(ciudadano);

                System.out.println("¿Está seguro que desea eliminar este ciudadano? (S/N)");
                String confirmacion = scanner.nextLine().toUpperCase();

                if (confirmacion.equals("S")) {
                    repoCiudadano.delete(ciudadano);
                    System.out.println("Ciudadano eliminado correctamente.");
                } else {
                    System.out.println("Operación de eliminación cancelada.");
                }
            }
        }
    }
}




