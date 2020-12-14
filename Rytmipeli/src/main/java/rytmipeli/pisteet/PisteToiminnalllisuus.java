package rytmipeli.pisteet;

import java.sql.*;

public class PisteToiminnalllisuus {

    private Connection db;
    private PreparedStatement p;
    private ResultSet r;

    public PisteToiminnalllisuus() throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:scores.db");
    }
    
     public void luoTietokanta() throws SQLException {
        Statement s = db.createStatement();

        s.execute("CREATE TABLE Pelaajat (id INTEGER PRIMARY KEY, nimi TEXT, score INTEGER)");
        
        
    }
     
     public void lisaaTulos(String nimi, int pisteet) throws SQLException {
        p = db.prepareStatement("INSERT INTO Asiakkaat(nimi) VALUES (?)");
        p.setString(1, nimi);
        p.executeUpdate();
        System.out.println("Asiakas lisätty");
    }
}
