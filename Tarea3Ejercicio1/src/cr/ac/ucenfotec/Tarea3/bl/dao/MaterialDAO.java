package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;

import java.util.List;

public abstract class MaterialDAO {

    public abstract boolean save(Material newMaterial);
    public abstract List<Material> findAll();

}

