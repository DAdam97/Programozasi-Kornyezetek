package beadando.service;

import beadando.dao.IOrkDAO;
import beadando.dao.exceptions.OrkAlreadyAdded;
import beadando.dao.exceptions.OrkNotFound;
import beadando.model.Ork;
import beadando.model.Weapon;
import beadando.model.exceptions.InvalidAttackPower;
import beadando.model.exceptions.InvalidDefense;
import beadando.model.exceptions.InvalidHealth;
import beadando.model.exceptions.InvalidRange;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class OrkServiceTest {

    OrkService service;

    @Before
    public void setUp() throws InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower, OrkNotFound, IOException, OrkAlreadyAdded {
        IOrkDAO mock = Mockito.mock(IOrkDAO.class);
        service = new OrkService(mock);

        Ork ork1 = new Ork("Dummy1", Weapon.MACE, 10, 50.6, 4,20, LocalDate.now());
        Ork ork2 = new Ork("Dummy2", Weapon.INSECTREAPER, 17, 48.6,
                15,15, LocalDate.of(2019,10,10));
        Ork ork3 = new Ork("Dummy3", Weapon.INSECTREAPER, 2, 15.7,
                15,2, LocalDate.of(2001,11,11));

        Collection<Ork> Orks = new ArrayList<>();
        Orks.add(ork1);
        Orks.add(ork2);
        Orks.add(ork3);

        Mockito.doThrow(OrkAlreadyAdded.class).when(mock).addOrk(ork3);

        Mockito.when(mock.getAllOrk()).thenReturn(Orks);

        Mockito.doReturn(false).when(mock).killOrk(Mockito.anyString());
        Mockito.doReturn(true).when(mock).killOrk("Dummy2");

        Mockito.doThrow(OrkNotFound.class).when(mock).getOrkByName(Mockito.anyString());
        Mockito.doReturn(ork1).when(mock).getOrkByName("Dummy1");
        Mockito.doReturn(ork2).when(mock).getOrkByName("Dummy2");


    }


    @Test(expected = OrkNotFound.class)
    public void testOrkNotFound() throws OrkNotFound {
        Ork ork = service.getOrkByName("Durothan");
    }

    @Test
    public void testOrkFound() throws OrkNotFound {
        Ork ork = service.getOrkByName("Dummy1");

        assertEquals(ork.getAttackPower(), 4);
    }

    @Test
    public void testGetAllOrk() throws OrkNotFound, IOException {
        Collection<Ork> Orks = service.getAllOrk();

        Assert.assertEquals(Orks.size(), 3);
    }

    @Test
    public void killedOrkFalse() throws IOException {
        Assert.assertEquals(service.killOrk("Garrosh"), false);
    }

    @Test
    public void killedOrkTrue() throws IOException {
        Assert.assertEquals(service.killOrk("Dummy2"), true);
    }


    @Test(expected = OrkAlreadyAdded.class)
    public void testOrkAlreadyAdded() throws InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower, OrkAlreadyAdded, IOException {
        Ork ork3 = new Ork("Dummy3", Weapon.INSECTREAPER, 2, 15.7,
                15,2, LocalDate.of(2001,11,11));

        service.addOrk(ork3);
    }

    @Test
    public void testAddOrk() throws InvalidRange, InvalidDefense, InvalidHealth, InvalidAttackPower, OrkAlreadyAdded, IOException {
        Ork  Nazgrel = new  Ork("Nazgrel", Weapon.MALLET, 30, 35.2,
                14, 20, LocalDate.of(2004,04,04));

        service.addOrk(Nazgrel);

        Collection<Ork> Orks = service.getAllOrk();
        System.out.println(Orks.size());
    }
}