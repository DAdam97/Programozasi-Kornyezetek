package beadando.service;

import beadando.dao.IOrkDAO;
import beadando.dao.exceptions.OrkAlreadyAdded;
import beadando.dao.exceptions.OrkNotFound;
import beadando.model.Ork;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collection;

public class OrkService {

    IOrkDAO dao;
    Logger logger = Logger.getLogger(OrkService.class);

    public OrkService(IOrkDAO dao) {
        logger.debug("Ork Service Initialized");
        this.dao = dao;
    }

    Collection<Ork> getAllOrk() throws IOException {
        logger.debug("get All Ork called from Ork service");
        return  dao.getAllOrk();
    }

    Ork getOrkByName(String name) throws OrkNotFound {
        logger.debug("get Ork By Name called from Ork service");
        return dao.getOrkByName(name);
    }

    void addOrk(Ork newOrk) throws OrkAlreadyAdded, IOException {
        logger.debug("add Ork called from Ork service");
        dao.addOrk(newOrk);
    }

    boolean killOrk(String killedOrk) throws IOException {
        logger.debug("kill Ork called from Ork service");
        return  dao.killOrk(killedOrk);
    }
}
