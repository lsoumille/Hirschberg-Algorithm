import java.lang.Integer;import java.lang.String;import java.lang.StringBuilder; /**
 * Created by user on 13/02/16.
 */
public class Hirschberg {

    public Hirschberg() {
    }

    /**
     * retourne l'indice k pour couper le mot x
     * @param upScores
     * @param downScores
     * @return
     */
    public int getIndToCut(int[] upScores, int[] downScores){
        Integer max = null;
        Integer ind = null;
        for(int i = 0, j = downScores.length - 1 ; i < upScores.length ; ++i , --j ){
            if(max == null || upScores[i] + downScores[j] > max){
                max = upScores[i] + downScores[j];
                ind = i;
            }
        }
        return ind;
    }

    /**
     * applique l'algo d'Hirschberg
     * @param wordx
     * @param wordy
     * @return
     */
    public String[] processHirschberg(String wordx, String wordy){
        String[] tab = new String[2];
        tab[0] = tab[1] = "";
        if(wordx.length() == 0){
            //on remplit X de -
            for(int i = 0 ; i < wordy.length() ; ++i){
                tab[0] += "-";
                tab[1] += wordy.charAt(i);
            }
        } else if(wordy.length() == 0) {
            //on remplit Y de -
            for(int i = 0 ; i < wordx.length() ; ++i){
                tab[0] += wordx.charAt(i);
                tab[1] += "-";
            }
        } else if (wordx.length() == 1 || wordy.length() == 1) {
            NeedlemanWunsch nw = new NeedlemanWunsch(wordx, wordy);
            nw.fillScoreArray();
            tab = nw.getAlignement();
        } else {
            int lengthy = wordy.length();
            int decoupageY = (lengthy / 2);
            NeedlemanWunsch nw = new NeedlemanWunsch();
            int[] scoresUp = nw.getLastLine(wordx,wordy.substring(0, decoupageY));
            StringBuilder strY = new StringBuilder();
            strY.append(wordy.substring(decoupageY));
            StringBuilder strX = new StringBuilder();
            strX.append(wordx);
            int[] scoresDown = nw.getLastLine(strX.reverse().toString(), strY.reverse().toString());
            //on détermine k
            int decoupagex = getIndToCut(scoresUp, scoresDown);
            //On applique la récursivité
            tab = concatTableau(processHirschberg(wordx.substring(0,decoupagex), wordy.substring(0, decoupageY)),
                    processHirschberg(wordx.substring(decoupagex),wordy.substring(decoupageY)));
        }
        return tab;
    }

    /**
     * concatene les strings pour les cases de memes indices
     * @param tab1
     * @param tab2
     * @return
     */
    private String[] concatTableau(String[] tab1, String[] tab2){
        for(int i = 0 ; i < tab1.length ;++i)
            tab1[i] +=  tab2[i];
        return tab1;
    }
}
