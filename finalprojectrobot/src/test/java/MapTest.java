import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    @Test
    public void init_map() {
        Map a = new Map();
        a.init( 15,15,0.5 );
//        a.init_map(15,15,0.5);

        for(int i=0;i<a.Column+2;i++){
            for(int j=0;j<a.Row+2;j++){
                Assert.assertTrue(a.map[0][i] == 2);
                Assert.assertTrue(a.map[i][0] == 2);
                Assert.assertTrue(a.map[a.Row+1][i] == 2);
                Assert.assertTrue(a.map[i][a.Column+1] == 2);
            }
        }
    }

//    @Test
//    public void showMap() {
//        Map a = new Map();
//        a.init(15,15,0.5);
//        a.S();
//        System.out.println();
//    }
//
//    @Test
//    public void showMapInSymbol() {
//        Map a = new Map();
//        a.init_map(15,15,0.5);
//        a.showMapInSymbol();
//    }
}