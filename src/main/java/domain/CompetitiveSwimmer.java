package domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitiveSwimmer extends Member {

    private ArrayList<Stroke> strokes;

    public CompetitiveSwimmer(String firstName, String lastName, LocalDate birthday, boolean active, ArrayList<Stroke> strokes) {
        super(firstName, lastName, birthday, active);
        if(strokes == null || strokes.isEmpty()){
            throw new IllegalArgumentException("Aktive konkurrencesvømmere skal minimum have en disciplin registreret.");
        }
        this.strokes = strokes;
    }

    // Genskaber en svømmer med medlemsnummeret og disciplinerne fra filerne.
    public CompetitiveSwimmer(int id, String firstName, String lastName, LocalDate birthday, boolean active, ArrayList<Stroke> strokes){
        super(id, firstName, lastName, birthday, active);
        if(strokes == null || strokes.isEmpty()){
            throw new IllegalArgumentException("Aktive konkurrencesvømmere skal minimum have en disciplin regisreret");
        }
        this.strokes = strokes;
    }

    // FileHandler får svømmerens discipliner via denne version af getStrokes().
    @Override
    public ArrayList<Stroke> getStrokes() {
        return strokes;
    }

    public void addStroke(Stroke stroke){
        strokes.add(stroke);
    }

    // Typen fortæller FileHandler, at medlemmet skal genskabes som konkurrencesvømmer.
    @Override
    public String getMemberType(){
        return "COMPETITIVE";
    }

    @Override
    public String toString(){
        String result = super.toString();
        int age = this.getAgeOn(LocalDate.now());
        if (age < 18)
        {
            result += "\nHold: Junior";
        } else
        {
            result += "\nHold: Senior";
        }
        if(this.strokes.size() > 1){
            result += "\nDiscipliner: ";
        } else{
            result += "\nDisciplin: ";
        }

        for(Stroke stroke : this.strokes){
            if(this.strokes.get(this.strokes.size() - 1).equals(stroke)){
                result += stroke.toString();
                break;
            }
            result += stroke.toString() + ", ";
        }

        return result;
    }

}
