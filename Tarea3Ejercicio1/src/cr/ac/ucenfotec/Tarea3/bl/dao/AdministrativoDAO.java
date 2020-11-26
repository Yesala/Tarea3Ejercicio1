package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Administrativo;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoNombramiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoDAO extends UsuarioDAO {

    @Override
    public boolean save(Usuario newUsuario) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(newUsuario.toCSVLine());
        try {
            Files.write(Paths.get("/Users/macbook/Dev/listOfAdministrativos.csv"),lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Usuario> findAll() {
        ArrayList<Usuario> result = new ArrayList<>();
        BufferedReader reader;
        String[] datos = null;
        String[] administrativos = null;
        try {
            reader = new BufferedReader(new FileReader("/Users/macbook/Dev/listOfAdministrativos.csv"));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                administrativos = currentLine.split(",");
                Administrativo nuevoAdministrativo = new Administrativo(administrativos[0], administrativos[1],Integer.parseInt(administrativos[2]), TipoNombramiento.valueOf(administrativos[3]), Float.parseFloat(administrativos[4]));
                result.add(nuevoAdministrativo);
                /*currentLine = reader.readLine();*/
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}


