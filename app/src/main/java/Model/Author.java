package Model;

import java.util.Date;

public class Author {
    public int Id;
    public String Nom;
    public Date DateNaiss;

    public Author(int id, String nom, Date dateNaiss) {
        Id = id;
        Nom = nom;
        DateNaiss = dateNaiss;
    }
}
