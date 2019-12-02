package beadando.dao;

import beadando.dao.exceptions.OrkNotFound;
import beadando.model.Ork;
import beadando.model.Weapon;
import beadando.dao.exceptions.OrkAlreadyAdded;
import beadando.model.exceptions.InvalidAttackPower;
import beadando.model.exceptions.InvalidDefense;
import beadando.model.exceptions.InvalidHealth;
import beadando.model.exceptions.InvalidRange;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

public class OrkDAOTest {

    static String filePath = "Orktest.json";
    IOrkDAO dao;


    @BeforeClass
    public static void remakeJSON(){
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }


    @Before
    public void testOrkDAO() throws IOException, OrkAlreadyAdded, InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        dao = new OrkDAO(filePath);
        Ork ork = new Ork("micsu", Weapon.MACE, 40, 20,
                10, 5, LocalDate.of(1997,12,31));

        dao.addOrk(ork);
    }

    /*
    @After
    public void eraseJSON(){
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
    */


    @Test
    public void  testAddOrk() throws OrkAlreadyAdded, IOException, InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork ork = new Ork("Thrall", Weapon.MACE, 45,
                25,10, 10, LocalDate.of(1994,11,12));

        dao.addOrk(ork);
        Collection<Ork> orks = dao.getAllOrk();

        System.out.println(orks.size());
    }

    @Test(expected = OrkAlreadyAdded.class)
    public void testOrkAlreadyAdded() throws OrkAlreadyAdded, IOException, InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork Durotan = new Ork("Durotan", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());
        dao.addOrk(Durotan);

        Ork Nerzhul = new Ork("Ner'zhul", Weapon.FIST, 2,
                15,12, 10, LocalDate.now());

        dao.addOrk(Nerzhul);
        dao.addOrk(Durotan);

    }

    @Test
    public void testGetOrkByName() throws OrkNotFound, OrkAlreadyAdded, IOException, InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork Orgrim = new Ork("Orgrim", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());
        dao.addOrk(Orgrim);

        Orgrim = dao.getOrkByName("Orgrim");

        System.out.println(Orgrim.toString());
    }

    @Test(expected = OrkNotFound.class)
    public void testOrkNotFound() throws OrkNotFound {
        Ork Guldan = dao.getOrkByName("Gul'dan");

        System.out.println(Guldan);
    }

    @Test
    public void testKillOrkTrue() throws OrkAlreadyAdded, IOException, InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {
        Ork Durotan = new Ork("Durotan", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());
        dao.addOrk(Durotan);

        Ork Nerzhul = new Ork("Ner'zhul", Weapon.FIST, 2,
                15,9, 10, LocalDate.now());

        dao.addOrk(Nerzhul);

        Collection<Ork> orks = dao.getAllOrk();

        for (Ork ork:orks) {
            System.out.println(ork);
        }

        boolean orkKilled = dao.killOrk("Ner'zhul");
        Assert.assertTrue(orkKilled);

        System.out.println("====================");

        orks = dao.getAllOrk();

        for (Ork ork:orks) {
            System.out.println(ork);
        }
    }

    @Test
    public void testKillOrkFalse() throws OrkAlreadyAdded, IOException, InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower {

        Ork Durotan = new Ork("Durotan", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());
        dao.addOrk(Durotan);

        Ork Nerzhul = new Ork("Ner'zhul", Weapon.FIST, 2,
                15,9, 10, LocalDate.now());

        dao.addOrk(Nerzhul);

        Collection<Ork> orks = dao.getAllOrk();

        for (Ork ork:orks) {
            System.out.println(ork);
        }

        boolean orkKilled = dao.killOrk("Malkorok");

        Assert.assertFalse(orkKilled);

        System.out.println("====================");

        orks = dao.getAllOrk();

        for (Ork ork:orks) {
            System.out.println(ork);
        }
    }


    @Test
    public void testOrkListSizeAfterKill() throws IOException {
        Collection<Ork> orks = dao.getAllOrk();

        int orksSize = orks.size();

        for (Ork ork:orks) {
            System.out.println(ork.getName());
        }

        System.out.println(dao.killOrk("micsu"));

        orks = dao.getAllOrk();

        Assert.assertEquals(orks.size(), orksSize - 1);
    }
}

