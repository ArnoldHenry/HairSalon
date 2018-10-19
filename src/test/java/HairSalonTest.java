import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class HairSalonTest {
//testing fetch
@Test
    public void equals_returntrueifusernameandpassword_true(){
    DataProperties oldname = new DataProperties("Luke","Lane","lname","744568921","male","arnold@gmail.com");
    oldname.save();
    DataProperties newname = new DataProperties("Luke","Lane","lname","744568921","male","arnold@gmail.com");
    newname.save();
    assertEquals(DataProperties.all().get(0),DataProperties.all().get(1));
}
//testing save
@Test
    public void save_returnifdatasame_true(){
    DataProperties dataProperties = new DataProperties("Luke","Lane","lname","744568921","male","arnold@gmail.com");
    dataProperties.save();
    assertTrue(DataProperties.all().get(0).equals(dataProperties));
}
//testing update
@Test
public void save_returnifupdatedata_true(){
    DataProperties dataProperties = new DataProperties("Luke","Lane","lname","744568921","male","arnold@gmail.com");
    dataProperties.save();
    dataProperties.update("LA","LO","df","456","female","arnod@gmail.com");
    assertEquals(DataProperties.all().get(0), dataProperties);
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
