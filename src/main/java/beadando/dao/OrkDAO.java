package beadando.dao;

import beadando.model.Ork;
import beadando.dao.exceptions.OrkAlreadyAdded;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class OrkDAO implements IOrkDAO {

    private Collection<Ork> orks = new ArrayList<>();

    private File file;
    private Logger logger = Logger.getLogger(OrkDAO.class);

    public OrkDAO(String filePath) throws IOException {
        file = new File(filePath);

        if (!file.exists()){
            file.createNewFile();
            FileWriter writer = new FileWriter(filePath);
            writer.write("[]");
            writer.close();
        }
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
    public Ork getOrkByName(String name) {
        return null;
    }

    @Override
    public void addOrk(Ork newOrk) throws OrkAlreadyAdded {
        for (Ork ork : orks) {
            if (ork.getName() == newOrk.getName()){
                throw new OrkAlreadyAdded();
            }
        }

        orks.add(newOrk);
    }
}
