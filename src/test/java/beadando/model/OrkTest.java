package beadando.model;

import beadando.model.exceptions.invalidHealth;
import org.junit.Test;

import java.time.LocalDate;

public class OrkTest {

    @Test
    public void testConstructor(){
        Ork ork = new Ork("Ork1", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());

        System.out.println(ork);
    }

    @Test (expected = invalidHealth.class)
    public void testSetHealth() throws invalidHealth {
        Ork ork = new Ork("Ork1", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());

        ork.setHealth(54);
        System.out.println(ork);
    }


}