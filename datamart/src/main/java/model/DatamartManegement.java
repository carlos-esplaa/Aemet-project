package model;
import view.*;

import java.io.File;

public class DatamartManegement {

    public void datamartManegement(){
        DatamartDegreesCreator datamartDegreesCreator = new DatamartDegreesCreator();
        File archivo = new File("datamartDir/datamart.db");
        if (!archivo.exists()) {
            datamartDegreesCreator.createNewDatabase("datamart.db");
            String dbPath = "Datamartdir/datamart.db";
            datamartDegreesCreator.connect(dbPath);
            datamartDegreesCreator.createNewTable();
        }

    }
}
