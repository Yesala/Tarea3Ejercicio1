package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Profesor;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Usuario;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoContrato;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO extends UsuarioDAO {

    @Override
    public boolean save(Usuario newUsuario) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(newUsuario.toCSVLine());
        try {
            Files.write(Paths.get("/Users/macbook/Dev/listOfProfesores.csv"),lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
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
        String[] profesores = null;
        try {
            reader = new BufferedReader(new FileReader("/Users/macbook/Dev/listOfProfesores.csv"));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                profesores = currentLine.split(",");
                Profesor nuevoProfesor = new Profesor(profesores[0], profesores[1],Integer.parseInt(profesores[2]), TipoContrato.valueOf(profesores[3]), LocalDate.parse(profesores[4]));
                result.add(nuevoProfesor);
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

