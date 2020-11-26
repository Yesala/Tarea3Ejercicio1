package cr.ac.ucenfotec.Tarea3.bl.entidades;

import java.time.LocalDate;

public class Texto extends Material {

    private String titulo;
    private String nombreAutor;
    private LocalDate fechaPublicacion;
    private int numeroPaginas;
    private String idioma;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Texto() {

    }

    public Texto(String signatura, LocalDate fechaCompra, boolean restringido, String tema,
                 String titulo, String nombreAutor, LocalDate fechaPublicacion, int numeroPaginas, String idioma) {
        super(signatura, fechaCompra, restringido, tema);
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
        this.fechaPublicacion = fechaPublicacion;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
    }

    public Texto (String sourceLines){
        String[] datos = sourceLines.split(",");
        this.signatura = datos[0];
        this.fechaCompra = LocalDate.parse(datos[1]);
        this.restringido = Boolean.parseBoolean(datos[2]);
        this.tema = datos[3];
        this.titulo = datos[4];
        this.nombreAutor = datos[5];
        this.fechaPublicacion = LocalDate.parse(datos[6]);
        this.numeroPaginas = Integer.parseInt(datos[7]);
        this.titulo = datos[8];
    }

    @Override
    public String toCSVLine() {
        return this.signatura + "," + this.fechaCompra + "," + this.restringido + "," + this.tema + "," + this.titulo
                + "," + this.nombreAutor + "," + this.fechaPublicacion + "," + this.numeroPaginas + "," + this.idioma + ",";
    }

}
