package cr.ac.ucenfotec.Tarea3.bl.dao;

import cr.ac.ucenfotec.Tarea3.bl.entidades.Material;
import cr.ac.ucenfotec.Tarea3.bl.entidades.Otro;

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

public class OtrosDAO extends MaterialDAO {

    @Override
    public boolean save(Material newMaterial) {

        ArrayList<String> lines = new ArrayList<>();
        lines.add(newMaterial.toCSVLine());
        try {
            Files.write(Paths.get("/Users/macbook/Dev/listOfOtrosmateriales.csv"),lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
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
        String[] otros = null;
        try {
            reader = new BufferedReader(new FileReader("/Users/macbook/Dev/listOfOtrosmateriales.csv"));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                otros = currentLine.split(",");
                Otro nuevoOtro = new Otro(otros[0],LocalDate.parse(otros[1]),Boolean.parseBoolean(otros[2]),otros[3],otros[4]);
                result.add(nuevoOtro);
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
