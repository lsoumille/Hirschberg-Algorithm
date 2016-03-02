import java.lang.String;import java.lang.System; /**
 * Created by user on 13/02/16.
 */
public class TestAlgo {

    public static void main(String[] args) {
        Hirschberg hi = new Hirschberg();
        String mot1 = "croisade";
        String mot2 = "croisee";
        String[] res = hi.processHirschberg(mot1.toUpperCase(), mot2.toUpperCase());
        for(String str : res)
            System.out.println(str);
    }
}
