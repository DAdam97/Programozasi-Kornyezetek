package beadando.dao;

import beadando.dao.exceptions.OrkNotFound;
import beadando.model.Ork;
import beadando.dao.exceptions.OrkAlreadyAdded;

import java.io.IOException;
import java.util.Collection;

public interface IOrkDAO {

    Collection<Ork> getAllOrk() throws IOException;
    Ork getOrkByName(String name) throws OrkNotFound;
    void addOrk(Ork ork) throws OrkAlreadyAdded, IOException;
    boolean killOrk(String killedOrk) throws IOException;
}
