import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GATest {



    @Test
    public void testGenerationInitilization(){
        GA g = new GA(100);
        for(int i=0;i<g.population;i++){
            Assert.assertTrue(g.pop.generation[i].score ==0);
        }
    }

    @Test
    public void testSexualReproduction(){
        Individual r1 = new Individual();
        Individual r2 = new Individual();

        r1.randomGeno();
        r2.randomGeno();

        GA g = new GA(100);
        g.Crossover(r1,r2);
        for(int i=1;i<r1.genoType.length/2;i++){
            Assert.assertTrue(r1.genoType[i]==r2.genoType[i]);
        }
    }

}