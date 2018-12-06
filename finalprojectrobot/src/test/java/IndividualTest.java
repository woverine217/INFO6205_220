import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndividualTest {

    @Test
    public void copyGene() {
        Individual r1 = new Individual();
        r1.randomGeno();
        Individual r2 = new Individual();
        r2.copy(r1.genoType);
        for(int i=0;i<r1.genoType.length;i++) {
            Assert.assertEquals(r1.genoType[i], r2.genoType[i]);
        }
        r1.genoType[0]=8;
        Assert.assertNotEquals(r1.genoType[0], r2.genoType[0]);

    }

    @Test
    public void testInit_Gene(){
    	Individual a = new Individual();
        a.randomGeno();
        for(int i=0;i<a.genoType.length;i++){
            Assert.assertNotEquals(8,a.genoType[i]);
        }
    }

    @Test
    public void testMoveUp(){
        Map a = new Map();
        a.init(10,10,0.5);

        Individual r = new Individual();
        r.position[0] = 3;
        r.position[1] = 3;
        r.up(a.map);
        Assert.assertTrue(r.position[0]-3 == -1);
    }
}