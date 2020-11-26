package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;

import java.util.List;

public abstract class UsuarioDAO {

        public abstract boolean save(Usuario newUsuario);
        public abstract List<Usuario> findAll();

    }


