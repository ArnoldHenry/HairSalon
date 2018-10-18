import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HairSalonTest {
@Before
    public void setUp(){
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test","arnold","/arnold/");
}
//testing fetch
@Test
    public void equals_returntrueifusernameandpassword_true(){
    DataProperties oldname = new DataProperties("arnold1234","Luke","Lane","Me",754896532,"Male","arnold@gmail.com");
    oldname.save();
    DataProperties newname = new DataProperties("arnold1234","Luke","Lane","Me",754896532,"Male","arnold@gmail.com");
    newname.save();
    assertEquals(DataProperties.all().get(0),DataProperties.all().get(1));
}
//testing save
@Test
    public void save_returnifdatasame_true(){
    DataProperties dataProperties = new DataProperties("arnold1234","Arnold","Luke","Me",754896532,"Male","arnold@gmail.com");
    dataProperties.save();
    assertTrue(DataProperties.all().get(0).equals(dataProperties));
}
//testing update
@Test
public void save_returnifupdatedata_true(){
    DataProperties dataProperties = new DataProperties("Arnold","Lane","Me","Male",754896532,"arnold@gmail.com","arnold1234");
    dataProperties.save();
    DataProperties dp = new DataProperties("Arnold","Lane","Me","Male",754896532,"arnold@gmail.com","arnold1234");
    dataProperties.save();
    assertFalse(DataProperties.all().get(0).equals(dataProperties));
}
//testing delete

    @After
    public void tearDown(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "DELETE FROM stylist *";
            connection.createQuery(sql).executeUpdate();
        }
    }

}
