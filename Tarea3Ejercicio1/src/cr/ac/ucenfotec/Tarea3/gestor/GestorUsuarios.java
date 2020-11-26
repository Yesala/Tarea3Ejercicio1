package cr.ac.ucenfotec.Tarea3.gestor;

import cr.ac.ucenfotec.Tarea3.bl.dao.AdministrativoDAO;
import cr.ac.ucenfotec.Tarea3.bl.dao.EstudianteDAO;
import cr.ac.ucenfotec.Tarea3.bl.dao.ProfesorDAO;
import cr.ac.ucenfotec.Tarea3.bl.dao.UsuarioDAO;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Administrativo;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Estudiante;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Profesor;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    private EstudianteDAO estudianteDAO = new EstudianteDAO();
    private ProfesorDAO profesorDAO = new ProfesorDAO();
    private AdministrativoDAO administrativoDAO = new AdministrativoDAO();

    public boolean save(Usuario nuevoUsuario){
        try{
            UsuarioDAO objPersistente = determinarObjetoDAO(nuevoUsuario);
            return objPersistente.save(nuevoUsuario);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    private UsuarioDAO determinarObjetoDAO(Usuario nuevoUsuario) throws Exception {
        if(nuevoUsuario instanceof Estudiante){
            return this.estudianteDAO;
        }
        if(nuevoUsuario instanceof Profesor){
            return this.profesorDAO;
        }
        if(nuevoUsuario instanceof Administrativo){
            return this.administrativoDAO;
        }
        throw new Exception("Usuario Desconocido");
    }

    public List<Usuario> listAll(Usuario tipoUsuario){
        try{
            UsuarioDAO objPersistente = determinarObjetoDAO(tipoUsuario);
            return objPersistente.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<Usuario>();
    }

    private UsuarioDAO determinarObjetoDAO(TipoUsuario tipo) throws Exception {
        if(TipoUsuario.ESTUDIANTE.equals(tipo)){
            return this.estudianteDAO;
        }
        if(TipoUsuario.PROFESOR.equals(tipo)){
            return this.profesorDAO;
        }
        if(TipoUsuario.ADMINISTRATIVO.equals(tipo)){
            return this.administrativoDAO;
        }
        throw new Exception("Tipo de Usuario Desconocido");
    }

}
