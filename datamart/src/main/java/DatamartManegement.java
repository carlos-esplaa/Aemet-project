public class DatamartManegement {

    public void datamartManegement(){
        DatamartDegreesCreator datamartDegreesCreator = new DatamartDegreesCreator();
        datamartDegreesCreator.createNewDatabase("Datamart.db");
        String dbPath = "C:\\Users\\fenix\\IdeaProjects\\Aemet-project\\Datamart.db";
        datamartDegreesCreator.connect(dbPath);
        datamartDegreesCreator.createNewTable();
    }
}
