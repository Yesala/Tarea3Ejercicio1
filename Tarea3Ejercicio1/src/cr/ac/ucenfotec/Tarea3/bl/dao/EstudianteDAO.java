package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Estudiante;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;

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

public class EstudianteDAO extends UsuarioDAO {

    @Override
    public boolean save(Usuario newUsuario) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(newUsuario.toCSVLine());
        try {
            Files.write(Paths.get("/Users/macbook/Dev/listOfEstudiantes.csv"),lines, StandardCharsets.UTF_8,StandardOpenOption.CREATE,
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
        String[] estudiantes = null;
        try {
            reader = new BufferedReader(new FileReader("/Users/macbook/Dev/listOfEstudiantes.csv"));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                estudiantes = currentLine.split(",");
                Estudiante nuevoEstudiante = new Estudiante(estudiantes[0], estudiantes[1],Integer.parseInt(estudiantes[2]),estudiantes[3],Integer.parseInt(estudiantes[4]));
                result.add(nuevoEstudiante);
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





