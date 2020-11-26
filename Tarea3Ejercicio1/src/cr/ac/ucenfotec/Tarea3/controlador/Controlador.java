package cr.ac.ucenfotec.Tarea3.controlador;

import cr.ac.ucenfotec.Tarea3.bl.entidades.*;
import cr.ac.ucenfotec.Tarea3.bl.tipos.*;
import cr.ac.ucenfotec.Tarea3.gestor.GestorMateriales;
import cr.ac.ucenfotec.Tarea3.gestor.GestorUsuarios;
import cr.ac.ucenfotec.Tarea3.iu.IU;

import java.time.LocalDate;
import java.util.List;

public class Controlador {

    private IU iu = new IU();
    private GestorMateriales gestorMateriales = new GestorMateriales();
    private GestorUsuarios gestorUsuarios = new GestorUsuarios();

    public void ejecutarPrograma() {
        int opcion = 0;
        do {
            iu.mostrarMenu();
            opcion = iu.leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 10);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registroEstudiante();
                break;
            case 2:
                registroProfesor();
                break;
            case 3:
                registroAdministrativo();
                break;
            case 4:
               listarUsuarios();
                break;
            case 5:
                registroTextos();
                break;
            case 6:
                registroAudio();
                break;
            case 7:
                registroVideo();
                break;
            case 8:
                registroOtros();
                break;
            case 9:
                listarMateriales();
                break;
            case 10:
                break;
            default:
                iu.imprimirMensaje("Opcion desconocida");
         }
    }

    private void registroEstudiante() {
        iu.imprimirMensaje("Nombre del estudiante: ");
        String nombre = iu.leerTexto();
        iu.imprimirMensaje("Apellido del estudiante: ");
        String apellido = iu.leerTexto();
        iu.imprimirMensaje("Numero de carnet: ");
        int numeroCarne = iu.leerEntero();
        iu.imprimirMensaje("Carrera que cursa: ");
        String carrera = iu.leerTexto();
        iu.imprimirMensaje("Creditos de la carrera: ");
        int creditos = iu.leerEntero();
        Estudiante estudiante = new Estudiante(nombre, apellido, numeroCarne,carrera,creditos);
        this.gestorUsuarios.save(estudiante);
    }

    private void registroProfesor() {
        iu.imprimirMensaje("Nombre del profesor: ");
        String nombre = iu.leerTexto();
        iu.imprimirMensaje("Apellido del profesor ");
        String apellido = iu.leerTexto();
        iu.imprimirMensaje("Numero de cedula ");
        int numeroCedula = iu.leerEntero();
        iu.imprimirMensaje("Tipo de Contrato: TIEMPO_COMPLETO, MEDIO_TIEMPO");
        String tipoContrato = iu.leerTexto();
        TipoContrato contratoProfesor = TipoContrato.valueOf(tipoContrato);
        iu.imprimirMensaje("Fecha de Contratación:");
        String fechaInicio = iu.leerTexto();
        LocalDate fInicio = obtenerFecha(fechaInicio);
        Profesor profesor = new Profesor(nombre,apellido,numeroCedula,contratoProfesor,fInicio);
        this.gestorUsuarios.save(profesor);
    }

    private LocalDate obtenerFecha(String fecha) {
        return LocalDate.parse(fecha);
    }

    private void registroAdministrativo() {
        iu.imprimirMensaje("Nombre del colaborador: ");
        String nombre = iu.leerTexto();
        iu.imprimirMensaje("Apellido del colaborador: ");
        String apellido = iu.leerTexto();
        iu.imprimirMensaje("Numero de cedula ");
        int numeroCedula = iu.leerEntero();
        iu.imprimirMensaje("Tipo de Nombramiento: A, B, C");
        String tipoNombramiento = iu.leerTexto();
        TipoNombramiento contratoAdministrativo = TipoNombramiento.valueOf(tipoNombramiento);
        iu.imprimirMensaje("Cantidad de horas semanales asignadas ");
        float horasSemAsignadas = iu.leerEntero();
        Administrativo administrativo = new Administrativo(nombre,apellido,numeroCedula,contratoAdministrativo,horasSemAsignadas);
        this.gestorUsuarios.save(administrativo);
    }

    private void listarUsuarios() {
        iu.imprimirMensaje("Elija la lista a imprimir: 1. Estudiantes, 2. Profesores, 3, Administradores");
        Usuario tipoUsuario = new Usuario();
        int escogencia = iu.leerEntero();
        if (escogencia == 1) {
            tipoUsuario = new Estudiante();
        }
        else if (escogencia == 2) {
            tipoUsuario = new Profesor();
        }
        else if (escogencia == 3) {
            tipoUsuario = new Administrativo();
        }
        List<Usuario> usuarios = gestorUsuarios.listAll(tipoUsuario);
        for (Usuario unUsuario: usuarios) {
            iu.imprimirMensaje(unUsuario.toCSVLine());
        }
    }

    private void registroTextos() {
        boolean False = true;
        iu.imprimirMensaje("Signatura del texto: ");
        String signatura = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Compra:");
        String fechaCompra = iu.leerTexto();
        LocalDate fCompra = obtenerFecha(fechaCompra);
        iu.imprimirMensaje("Disponibilidad del texto: ");
        boolean restringido = False;
        iu.imprimirMensaje("Tema del texto:");
        String tema= iu.leerTexto();
        iu.imprimirMensaje("Titulo:");
        String titulo = iu.leerTexto();
        iu.imprimirMensaje("Nombre del autor:");
        String nombreAutor = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Publicacion:");
        String fechaPublicacion = iu.leerTexto();
        LocalDate fPublicacion = obtenerFecha(fechaPublicacion);
        iu.imprimirMensaje("Numero de páginas del texto: ");
        int numeroPagina = iu.leerEntero();
        iu.imprimirMensaje("Idioma:");
        String idioma = iu.leerTexto();
        Texto texto = new Texto(signatura,fCompra,restringido,tema,titulo,nombreAutor,fPublicacion,numeroPagina,idioma);
        this.gestorMateriales.save(texto);
    }

    private void registroAudio() {
        boolean False = true;
        iu.imprimirMensaje("Signatura del audio: ");
        String signatura = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Compra:");
        String fechaCompra = iu.leerTexto();
        LocalDate fCompra = obtenerFecha(fechaCompra);
        iu.imprimirMensaje("Disponibilidad del audio: ");
        boolean restringido = False;
        iu.imprimirMensaje("Tema del audio:");
        String tema= iu.leerTexto();
        iu.imprimirMensaje("Tipo de Audio: CASSETTE, CD, DVD");
        String formatoAudio  = iu.leerTexto();
        TipoAudio tipoAudio = TipoAudio.valueOf(formatoAudio);
        iu.imprimirMensaje("Duracion del audio");
        float duracion = iu.leerFloat();
        iu.imprimirMensaje("Idioma del audio");
        String idioma = iu.leerTexto();
        Audio audio = new Audio(signatura,fCompra,restringido,tema,tipoAudio,duracion,idioma);
        this.gestorMateriales.save(audio);
    }

    private void registroVideo() {
        boolean False = true;
        iu.imprimirMensaje("Signatura del video: ");
        String signatura = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Compra:");
        String fechaCompra = iu.leerTexto();
        LocalDate fCompra = obtenerFecha(fechaCompra);
        iu.imprimirMensaje("Disponibilidad del video: ");
        boolean restringido = False;
        iu.imprimirMensaje("Tema del video:");
        String tema= iu.leerTexto();
        iu.imprimirMensaje("Tipo de Video: VHS, VCD, DVD");
        String formatoVideo = iu.leerTexto();
        TipoVideo tipoVideo = TipoVideo.valueOf(formatoVideo);
        iu.imprimirMensaje("Duracion del video");
        float duracion = iu.leerFloat();
        iu.imprimirMensaje("Idioma del video");
        String idioma = iu.leerTexto();
        iu.imprimirMensaje("Nombre del director del video");
        String director = iu.leerTexto();
        Video video = new Video(signatura,fCompra,restringido,tema,tipoVideo,duracion,idioma,director);
        this.gestorMateriales.save(video);
    }

    private void registroOtros() {
        boolean False = true;
        iu.imprimirMensaje("Signatura del material: ");
        String signatura = iu.leerTexto();
        iu.imprimirMensaje("Fecha de Compra:");
        String fechaCompra = iu.leerTexto();
        LocalDate fCompra = obtenerFecha(fechaCompra);
        iu.imprimirMensaje("Disponibilidad del material: ");
        boolean restringido = False;
        iu.imprimirMensaje("Tema del material:");
        String tema= iu.leerTexto();
        iu.imprimirMensaje("Descripcion del material: ");
        String descripcion = iu.leerTexto();
        Otro otro = new Otro(signatura,fCompra,restringido,tema,descripcion);
        this.gestorMateriales.save(otro);
    }

    private void listarMateriales() {
        iu.imprimirMensaje("Elija la lista a imprimir: 1. Textos, 2. Audio, 3, Video, 4. Otros Materiales");
        Material tipoMaterial= new Material();
        int escogencia = iu.leerEntero();
        if (escogencia == 1) {
            tipoMaterial = new Texto();
        }
        else if (escogencia == 2) {
            tipoMaterial = new Audio();
        }
        else if (escogencia == 3) {
            tipoMaterial = new Video();
        }
        else if (escogencia == 4) {
            tipoMaterial = new Otro();
        }
        List<Material> materiales = gestorMateriales.listAll(tipoMaterial);
        for (Material unMaterial: materiales) {
            iu.imprimirMensaje(unMaterial.toCSVLine());
        }
    }
}
