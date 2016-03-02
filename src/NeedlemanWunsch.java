import java.lang.String;import java.lang.StringBuilder;import java.lang.System; /**
 * Created by user on 13/02/16.
 */
public class NeedlemanWunsch {

    public static final int gapscore = -3;
    public static final int matchscore = 2;
    public static final int mismatchscore = -1;

    private String x;
    private String y;
    private int xlen;
    private int ylen;
    private int[][] scoreArray;

    public NeedlemanWunsch() {
    }

    public NeedlemanWunsch(String a, String b) {
        x = a;
        y = b;
        xlen = x.length();
        ylen = y.length();
        scoreArray = new int[ylen + 1][xlen + 1];
    }

    /**
     * applique l'algorithme de Needleman Wunsch et place les valeurs dans la matrice
     */
    public void fillScoreArray() {
        int row, col;
        int northwest, north, west;
        int best;
        //init de la premiere colonne et de la première ligne
        for (col = 0 ; col <= xlen ; ++col)
            scoreArray[0][col] = gapscore * col;
        for (row = 0 ; row <= ylen ; ++row)
            scoreArray[row][0] = gapscore * row;
        // remplissage de la matrice
        for (row = 1; row <= ylen; ++row) {
            for (col = 1; col <= xlen; ++col) {
                if (x.charAt(col - 1) == y.charAt(row - 1))
                    northwest = scoreArray[row - 1][col - 1] + matchscore;
                else
                    northwest = scoreArray[row - 1][col - 1] + mismatchscore;
                west = scoreArray[row][col - 1] + gapscore;
                north = scoreArray[row - 1][col] + gapscore;
                best = northwest;
                //On regarde qui est le meilleur
                if (north > best) best = north;
                if (west > best) best = west;
                scoreArray[row][col] = best;
            }
        }
    }

    /**
     * retourne la derniere ligne de la matrice
     * @param x
     * @param y
     * @return
     */
    public int[] getLastLine(String x, String y){
        this.x = x;
        this.y = y;
        this.xlen = x.length();
        this.ylen = y.length();
        scoreArray = new int[ylen + 1][xlen + 1];
        fillScoreArray();
        int[] res = new int[xlen + 1];
        for(int i = 0 ; i <= xlen  ; ++i)
            res[i] = scoreArray[ylen][i];
        return res;
    }

    /**
     * retourne le meilleur alignement pour les deux mots
     * @return
     */
    public String[] getAlignement(){
        String[] tab = new String[2];
        tab[0] = tab[1] = "";
        int sizex = xlen;
        int sizey = ylen;
        while(sizex > 0 && sizey > 0){
            int score = scoreArray[sizey][sizex];
            int scoreUp = scoreArray[sizey - 1][sizex];
            int scoreLeft = scoreArray[sizey][sizex - 1];
            //int scoreDiag = scoreArray[sizey - 1][sizex - 1];
            if(score == scoreLeft + gapscore) {
                tab[1] += "-";
                tab[0] += x.charAt(--sizex);
            } else if (score == scoreUp + gapscore){
                tab[1] += y.charAt(--sizey);
                tab[0] += "-";
            } else {
                tab[1] += y.charAt(--sizey);
                tab[0] += x.charAt(--sizex);
            }
        }
        while(sizex > 0){
            tab[1] += "-";
            tab[0] += x.charAt(--sizex);
        }
        while(sizey > 0){
            tab[1] += y.charAt(--sizey);
            tab[0] += "-";
        }

        for(int i = 0 ; i < 2 ; ++i){
            tab[i] = reverseString(tab[i]);
        }
        return tab;
    }

    /**
     * retourne la string passée en paramètre inversée
     * @param str
     * @return
     */
    private String reverseString(String str){
        StringBuilder strB = new StringBuilder();
        strB.append(str);
        return strB.reverse().toString();
    }

}
