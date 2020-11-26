package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Audio;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoAudio;

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

public class AudioDAO extends MaterialDAO {

    @Override
    public boolean save(Material newMaterial) {

        ArrayList<String> lines = new ArrayList<>();
        lines.add(newMaterial.toCSVLine());
        try {
            Files.write(Paths.get("/Users/macbook/Dev/listOfAudios.csv"),lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Material> findAll() {
        ArrayList<Material> result = new ArrayList<>();
        BufferedReader reader;
        String[] datos = null;
        String[] audios = null;
        try {
            reader = new BufferedReader(new FileReader("/Users/macbook/Dev/listOfAudios.csv"));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                audios = currentLine.split(",");
                Audio nuevoAudio = new Audio(audios[0],LocalDate.parse(audios[1]),Boolean.parseBoolean(audios[2]),audios[3],TipoAudio.valueOf(audios[4]),Float.parseFloat(audios[5]),audios[6]);
                result.add(nuevoAudio);
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



