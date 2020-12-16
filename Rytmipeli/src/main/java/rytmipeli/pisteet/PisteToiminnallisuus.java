package rytmipeli.pisteet;
import java.util.*;
    import java.io.*;

    public class PisteToiminnallisuus {
        // Sparar alla poäng i filen "scores.dat", filen skapas här
        private static final String HIGHSCORE_FILE = "scores.txt";

        // En arraylista med typen score
        ArrayList<Piste> pisteet;

        // Startar en in och en utström för att skicka och ladda in information från
        // filen
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        public PisteToiminnallisuus() {
            // För att kunna arbeta med listan behövs en variabel dvs. Arraylistan
            // scores
            pisteet = new ArrayList<Piste>();
            // Det är med classen Arraylist<score> getScores() som arrayen skapas
            // forts
            pisteet = getScores();
        }

        public ArrayList<Piste> getScores() {
            // ...forts-> och här används loadScoreFil() och sort(), returnerar
            // värdet scores som används i HighscoreManager
            loadScoreFile();
            sort();
            return pisteet;
        }

        private void sort() {
            Collections.sort(pisteet);
        }

        public void addScore(String name, int score) {

            loadScoreFile();
            pisteet.add(new Piste(name, score));
            updateScoreFile();
        }

        public void loadScoreFile() {
            //Se updateScoreFile
            try {
                inputStream = new ObjectInputStream(new FileInputStream(
                        HIGHSCORE_FILE));
                pisteet = (ArrayList<Piste>) inputStream.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("");
            } catch (IOException e) {
                System.out.println("io problem: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("cnf problem: " + e.getMessage());
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (IOException e) {
                    System.out.println("io problem: " + e.getMessage());
                }
            }
        }

        public void updateScoreFile() {
            try {
                // Med serializerade Score.java så används outputStream för att
                // skicka till HIGHSCORE_FILE som är scores.dat

                outputStream = new ObjectOutputStream(new FileOutputStream(
                        HIGHSCORE_FILE));
                outputStream.writeObject(pisteet);
            }
            // resten är catch som kan göras om för att visa felmeddelanden, jag
            // gjorde bara en basic.
            catch (FileNotFoundException e) {
                System.out.println("File Not Found, run the program again to fix.");
            } catch (IOException e) {
                System.out.println("IO problem, run the program again to fix.");
            }

            finally {
                try {
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                        // Detta displayar poängen så länge det finns något att
                        // displaya
                    }
                } catch (IOException e) {
                    System.out
                            .println("Update problem, run the program again to fix.");
                }
            }
        }

        public String getHighscoreString() {
            // Här skapas själva texten som ska visas, nästan slutstation för
            // highscoret

            // Skapar Stringen highscoreString och sätter en maxgräns för hur många
            // highscores som ska vara med.
            String highscoreString = "";
            int max = 10;

            // Sammanställer poängen
            ArrayList<Piste> pisteet;
            pisteet = getScores();

            // while loop som printar ut ett highscore i taget, när i < max så
            // skrivs scores1,scores2 etc ut och plussar
            // på i och processen repeteras tills i = x, då returneras värdet som är
            // highscoreString och kan användas i tamain.
            int i = 0;
            int x = pisteet.size();
            if (x > max) {
                x = max;
            }

            while (i < x) {
                highscoreString += (i + 1) + ".\t" + pisteet.get(i).getNimi() + "  "
                        + pisteet.get(i).getPisteet() + "\n";
                i++;
            }

            return highscoreString;
        }

   
    }
