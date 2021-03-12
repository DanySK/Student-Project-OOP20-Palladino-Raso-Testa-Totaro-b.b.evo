package model.utilities;

import java.util.Arrays;
import java.util.List;

public enum Difficulty {
    /*  used to get/set difficulties  */
    EASY,
    NORMAL,
    HARD;
    
    public static List<Difficulty> difficultyList(Difficulty d){
        return Arrays.asList(Difficulty.values());
    } 
    
    /**
     * @return a {@link String} for each direction.
     */
    @Override
    public String toString() {
        switch (this) {
        case EASY:
            return "Easy";
        case NORMAL:
            return "Normal";
        case HARD:
            return "Hard";
        default:
            return "";
        }
    }
    
    
    
    
}
