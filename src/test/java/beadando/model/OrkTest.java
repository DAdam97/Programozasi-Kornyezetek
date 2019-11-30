package beadando.model;

import beadando.model.exceptions.InvalidAttackPower;
import beadando.model.exceptions.InvalidDefense;
import beadando.model.exceptions.InvalidHealth;
import beadando.model.exceptions.InvalidRange;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class OrkTest {


    @Test
    public void testConstructor() throws InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork ork = new Ork("DummyOrk", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());

        System.out.println(ork);
    }

    @Test (expected = InvalidHealth.class)
    public void testSetHealth() throws InvalidHealth, InvalidDefense, InvalidRange, InvalidAttackPower {
        Ork Drakthul = new Ork("Drak'thul", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());

        Drakthul.setHealth(54);
        System.out.println(Drakthul);
    }

    @Test
    public  void testSetRange() throws InvalidRange, InvalidAttackPower, InvalidDefense, InvalidHealth {
        Ork Garad = new Ork("Garad",Weapon.SWORD, 20,7.7,
                5,19,LocalDate.of(2005,9,20));

        Garad.setRange(77.7);
    }

    @Test
    public void testAllSetters() throws InvalidHealth, InvalidRange, InvalidAttackPower, InvalidDefense {
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

    @Test (expected = InvalidAttackPower.class)
    public void testInvalidAttackPower() throws InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork Blackhand = new Ork("Blackhand", Weapon.MALLET,20, 5.2,
                9000, 10, LocalDate.now());
    }

    @Test (expected = InvalidDefense.class)
    public void testInvalidDefense() throws InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork Nazgrim = new Ork("Nazgrim", Weapon.HATCHET,20, 5.2,
                10, 1000, LocalDate.now());
    }

    @Test (expected = InvalidRange.class)
    public void testInvalidRange() throws InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork DranoshSaurfang = new Ork("Dranosh Saurfang", Weapon.FIST,20, -5.2,
                12, 10, LocalDate.now());
    }

    @Test
    public void testOrkGetters() throws InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork Kilrogg = new Ork("Kilrogg", Weapon.INSECTREAPER, 25, 30.5, 15,
                20, LocalDate.of(2019,11,30));

        String KilroggJson = "main.model.Ork{name='"+ Kilrogg.getName() +"'"+
                "weapon='"+Kilrogg.getWeapon()+"'"+
                "health='"+Kilrogg.getHealth()+"'"+
                "range='"+Kilrogg.getRange()+"'"+
                "attackPower='"+Kilrogg.getAttackPower()+"'"+
                "defense='" + Kilrogg.getDefense() + "'"+
                "bornt='" + Kilrogg.getBornt() + "'}";

        Assert.assertEquals(KilroggJson, Kilrogg.toString());
    }
}
