package beadando.dao;

import beadando.model.Ork;
import beadando.model.Weapon;
import beadando.dao.exceptions.OrkAlreadyAdded;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Collection;

public class OrkDAOTest {

    static String filePath = "Orktest.json";
    IOrkDAO dao;


    @BeforeClass
    public static void remake(){
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void  testAddOrk() throws OrkAlreadyAdded {
        Ork ork = new Ork("Ork1", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());

        dao.addOrk(ork);
        Collection<Ork> orks = dao.getAllOrk();

        System.out.println(orks.size());
    }

    @Test(expected = OrkAlreadyAdded.class)
    public void testOrkAlreadyAdded() throws OrkAlreadyAdded {
        Ork ork = new Ork("Ork1", Weapon.INSECTREAPER, 45,
                25,10, 10, LocalDate.now());
        dao.addOrk(ork);
        dao.addOrk(ork);

    }


}