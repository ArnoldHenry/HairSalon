import org.sql2o.*;
import java.time.LocalDateTime;
import java.util.List;

public class DataProperties {

    public static List<DataProperties> all(){
        String sql = "SELECT * FROM employee";
        try (Connection connection = DB.sql2o.open()){
            return connection.createQuery(sql).executeAndFetch(DataProperties.class);
        }
    }
    private String uname;
    private String password;
    private int id;
    private LocalDateTime createdAt;

    public DataProperties(String uname,String password){
        this.uname = uname;
        this.password = password;
        createdAt = LocalDateTime.now();
    }

    public String getUname() {
        return uname;
    }

    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static DataProperties find(int id){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM employee WHERE id=:id";
            DataProperties dp = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(DataProperties.class);
            return dp;

        }
    }

    @Override
    public boolean equals(Object otheruname){
        if (!(otheruname instanceof DataProperties)){
            return false;
        }else{
           DataProperties dp = (DataProperties) otheruname;
           return this.getUname().equals(dp.getUname());
        }
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String newdata = "INSERT INTO employee(uname,password)VALUES(:uname,:password)";
            connection.createQuery(newdata)
                    .addParameter("uname",this.uname)
                    .addParameter("password",this.password)
                    .executeUpdate();

        }
    }

}
