package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Video;
import cr.ac.ucenfotec.Tarea3.bl.tipos.TipoVideo;

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

public class VideoDAO extends MaterialDAO {

    @Override
    public boolean save(Material newMaterial) {

        ArrayList<String> lines = new ArrayList<>();
        lines.add(newMaterial.toCSVLine());
        try {
            Files.write(Paths.get("/Users/macbook/Dev/listOfVideos.csv"),lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
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
        String[] videos = null;
        try {
            reader = new BufferedReader(new FileReader("/Users/macbook/Dev/listOfVideos.csv"));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                videos = currentLine.split(",");
                Video nuevoVideo = new Video(videos[0],LocalDate.parse(videos[1]),Boolean.parseBoolean(videos[2]),videos[3],TipoVideo.valueOf(videos[4]),Float.parseFloat(videos[5]),videos[6],videos[7]);
                result.add(nuevoVideo);
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




