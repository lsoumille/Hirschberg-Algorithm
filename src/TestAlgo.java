import java.lang.String;import java.lang.System; /**
 * Created by user on 13/02/16.
 */
public class TestAlgo {

    public static void main(String[] args) {
        Hirschberg hi = new Hirschberg();
        String[] res = hi.processHirschberg("PYTHON", "PONY");
        for(String str : res)
            System.out.println(str);
    }
}
