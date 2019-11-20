package beadando.dao;

import beadando.dao.exceptions.OrkNotFound;
import beadando.model.Ork;
import beadando.dao.exceptions.OrkAlreadyAdded;
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

    }

    @Override
    public Collection<Ork> getAllOrk() {
        Collection<Ork> temp = new ArrayList<>();

        for (Ork o: orks) {
           temp.add(o);
        }

        return temp;
    }

    @Override
    public Ork getOrkByName(String name) throws OrkNotFound {

        for (Ork ork: orks) {
            if (ork.getName() == name){
                return ork;
            }
        }

        throw new OrkNotFound(name);
    }

    @Override
    public void addOrk(Ork newOrk) throws OrkAlreadyAdded, IOException {
        for (Ork ork : orks) {
            if (ork.getName() == newOrk.getName()){
                throw new OrkAlreadyAdded();
            }
        }

        orks.add(newOrk);
        mapper.writeValue(file, orks);
    }

    @Override
    public boolean killOrk(Ork killedOrk) {
        for (Ork ork : orks) {
            if (ork.equals(killedOrk)){
               orks.remove(ork);
               return true;
            }
        }
        return false;
    }
}
