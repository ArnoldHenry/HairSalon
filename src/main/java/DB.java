import org.sql2o.*;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test","arnold","/arnold/");
    public static Sql2o sql2oHair = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon","arnold","/arnold/");
}