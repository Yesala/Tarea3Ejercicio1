package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Texto;

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

public class TextoDAO extends MaterialDAO {

    @Override
    public boolean save(Material newMaterial) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(newMaterial.toCSVLine());
        try {
            Files.write(Paths.get("/Users/macbook/Dev/listOfTextos.csv"),lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
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
        String[] textos = null;
        try {
            reader = new BufferedReader(new FileReader("/Users/macbook/Dev/listOfTextos.csv"));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                textos = currentLine.split(",");
                Texto nuevoTexto = new Texto(textos[0], LocalDate.parse(textos[1]),Boolean.parseBoolean(textos[2]),textos[3],textos[4],textos[5],LocalDate.parse(textos[6]),Integer.parseInt(textos[7]),textos[8]);
                result.add(nuevoTexto);
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



