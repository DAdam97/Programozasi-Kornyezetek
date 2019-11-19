package beadando.dao;

import beadando.model.Ork;
import beadando.dao.exceptions.OrkAlreadyAdded;

import java.util.Collection;

public interface IOrkDAO {

    Collection<Ork> getAllOrk();
    Ork getOrkByName(String name);
    void addOrk(Ork ork) throws OrkAlreadyAdded;

}
