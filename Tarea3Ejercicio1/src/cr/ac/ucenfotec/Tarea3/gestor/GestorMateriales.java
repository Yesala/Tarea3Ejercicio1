package cr.ac.ucenfotec.Tarea3.gestor;

import cr.ac.ucenfotec.Tarea3.bl.dao.*;
import cr.ac.ucenfotec.Tarea3.bl.entidades.*;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoMaterial;

import java.util.ArrayList;
import java.util.List;

public class GestorMateriales {

    private AudioDAO audioDAO = new AudioDAO();
    private TextoDAO textoDAO = new TextoDAO();
    private VideoDAO videoDAO = new VideoDAO();
    private OtrosDAO otrosDAO = new OtrosDAO();

    public boolean save(Material nuevoMaterial){
        try{
            MaterialDAO objPersistente = determinarObjetoDAO(nuevoMaterial);
            return objPersistente.save(nuevoMaterial);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    private MaterialDAO determinarObjetoDAO(Material nuevoMaterial) throws Exception {
        if(nuevoMaterial instanceof Texto){
            return this.textoDAO;
        }
        if(nuevoMaterial instanceof Audio){
            return this.audioDAO;
        }
        if(nuevoMaterial instanceof Video){
            return this.videoDAO;
        }
        if(nuevoMaterial instanceof Otro){
            return this.otrosDAO;
        }
        throw new Exception("Material Desconocido");
    }

    public List<Material> listAll(Material tipoMaterial){
        try{
            MaterialDAO objPersistente = determinarObjetoDAO(tipoMaterial);
            return objPersistente.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<Material>();
    }

    private MaterialDAO determinarObjetoDAO(TipoMaterial tipo) throws Exception {
        if(TipoMaterial.TEXTO.equals(tipo)){
            return this.textoDAO;
        }
        if(TipoMaterial.AUDIO.equals(tipo)){
            return this.audioDAO;
        }
        if(TipoMaterial.VIDEO.equals(tipo)){
            return this.videoDAO;
        }
        if(TipoMaterial.OTRO.equals(tipo)){
            return this.otrosDAO;
        }
        throw new Exception("Tipo de Material Desconocido");
    }

}





