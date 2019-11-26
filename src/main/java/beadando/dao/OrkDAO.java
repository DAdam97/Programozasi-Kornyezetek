package beadando.dao;

import beadando.dao.exceptions.OrkNotFound;
import beadando.model.Ork;
import beadando.dao.exceptions.OrkAlreadyAdded;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class OrkDAO implements IOrkDAO {

    private Collection<Ork> orks = new ArrayList<>();

    private File file;
    private Logger logger = Logger.getLogger(OrkDAO.class);
    private ObjectMapper mapper;

    public OrkDAO(String filePath) throws IOException {
        file = new File(filePath);
        file.delete();

        if (!file.exists()){
            file.createNewFile();
            FileWriter writer = new FileWriter(filePath);
            writer.write("[]");
            writer.close();
        }

        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        logger.debug("DAO init");
    }

    @Override
    public Collection<Ork> getAllOrk() throws IOException {
        Collection<Ork> temp = mapper.readValue(file,
                new TypeReference<ArrayList<Ork>>() {});

        logger.debug("json fileban levo orkok: " + temp.size());

        return temp;
    }

    @Override
    public Ork getOrkByName(String name) throws OrkNotFound {

        for (Ork ork: orks) {
            if (ork.getName() == name){
                logger.info("ork megtalalva: " + ork.toString());
                return ork;
            }
        }
        logger.error(name + " nevu ork nincs meg :(");
        throw new OrkNotFound(name);
    }

    @Override
    public void addOrk(Ork newOrk) throws OrkAlreadyAdded, IOException {
        for (Ork ork : orks) {
            if (ork.getName() == newOrk.getName()){
                logger.error("ork hozzaadasa sikertelen: " + ork.toString());
                throw new OrkAlreadyAdded();
            }
        }

        orks.add(newOrk);
        logger.info("ork hozzaadva: " + newOrk.toString());
        mapper.writeValue(file, orks);
    }

    @Override
    public boolean killOrk(String killedOrk) throws IOException {
        for (Ork ork : orks) {
            if (ork.getName() == killedOrk){
               orks.remove(ork);
               mapper.writeValue(file, orks);
               logger.info("ork sikeresen megölve");
               return true;
            }
        }
        logger.info("az ork tulelte");
        return false;
    }
}
