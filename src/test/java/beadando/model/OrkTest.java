package beadando.model;

import beadando.model.exceptions.invalidAttackPower;
import beadando.model.exceptions.invalidDefense;
import beadando.model.exceptions.invalidHealth;
import beadando.model.exceptions.invalidRange;
import org.junit.Test;

import java.time.LocalDate;

public class OrkTest {


    @Test
    public void testConstructor(){
        Ork ork = new Ork("DummyOrk", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());

        System.out.println(ork);
    }

    @Test (expected = invalidHealth.class)
    public void testSetHealth() throws invalidHealth {
        Ork Drakthul = new Ork("Drak'thul", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());

        Drakthul.setHealth(54);
        System.out.println(Drakthul);
    }

    @Test
    public  void testSetRange() throws invalidRange {
        Ork Garad = new Ork("Garad",Weapon.SWORD, 20,7.7,
                5,19,LocalDate.of(2005,9,20));

        Garad.setRange(77.7);
    }

    @Test
    public void testAllSetters() throws invalidHealth, invalidRange, invalidAttackPower, invalidDefense {
        Ork OrgrimDoomhammer = new Ork("Ork", Weapon.FIST,1,
                1,1,1,LocalDate.now() );

        System.out.println(OrgrimDoomhammer.toString());

        OrgrimDoomhammer.setName("Orgrim Doomhammer");
        OrgrimDoomhammer.setWeapon(Weapon.MACE);
        OrgrimDoomhammer.setHealth(50);
        OrgrimDoomhammer.setRange(90.5);
        OrgrimDoomhammer.setAttackPower(14);
        OrgrimDoomhammer.setDefense(20);
        OrgrimDoomhammer.setBornt(LocalDate.of(1998,5,20));

        System.out.println(OrgrimDoomhammer.toString());
    }



}