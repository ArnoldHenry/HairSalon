import org.sql2o.Connection;

import java.time.LocalDateTime;
import java.util.List;

public class DataProperties {
    private String fname,sname,lname,gender,email,stylistid;
    private int mobile;

    public DataProperties(String stylistid, String fname, String sname, String lname,int mobile, String gender, String email) {
        this.stylistid = stylistid;
        this.fname = fname;
        this.sname = sname;
        this.lname = lname;
        this.mobile = mobile;
        this.gender = gender;
        this.email = email;
        this.mobile= mobile;
    }
    public static List<DataProperties> all(){
        String sql = "SELECT stylistid,fname,sname,lname,mobile,gender,email FROM stylist";
        try (Connection connection = DB.sql2o.open()){
            return connection.createQuery(sql).executeAndFetch(DataProperties.class);
        }
    }
    public static DataProperties find(int id){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM stylist WHERE id=:id";
            DataProperties dp = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(DataProperties.class);
            return dp;

        }
    }


    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String newdata = "INSERT INTO stylist(stylistid,fname,sname,lname,mobile,gender,email)" +
                    "VALUES(:stylistid,:fname,:sname,:lname,:mobile,:gender,:email)";
            connection.createQuery(newdata)
                    .addParameter("stylistid",this.stylistid)
                    .addParameter("fname",this.fname)
                    .addParameter("sname",this.sname)
                    .addParameter("lname",this.lname)
                    .addParameter("mobile",this.mobile)
                    .addParameter("gender",this.gender)
                    .addParameter("email",this.email)
                    .executeUpdate();

        }
    }
    public void update(String update){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE fname,sname,lname,mobile,gender,email FROM stylist WHERE fname=:fname";
            connection.createQuery(sql)
                    .addParameter("fname",this.fname)
                    .addParameter("sname",this.sname)
                    .addParameter("lname",this.lname)
                    .addParameter("mobile",this.mobile)
                    .addParameter("gender",this.gender)
                    .addParameter("email",this.email)
                    .executeUpdate();
        }
    }

    public static HairSalonDB find(String username){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT username FROM stylist WHERE username=:username";
            HairSalonDB db = connection.createQuery(sql)
                    .addParameter("username",username)
                    .executeAndFetchFirst(HairSalonDB.class);
            return db;
        }
    }
    public void savecustomer(HairSalonDP customer) {
        try (Connection connection = DB.sql2o.open()) {
            String newdata = "INSERT INTO stylist(fname,sname,lname,mobile,gender,email,customerid,stylistid)" +
                    "VALUES(:fname,:sname,:lname,:mobile,:gender,:email,:customerid,:stylistid)";
            connection.createQuery(newdata)
                    .addParameter("fname", customer.getFname())
                    .addParameter("sname", customer.getSname())
                    .addParameter("lname", customer.getLname())
                    .addParameter("mobile", customer.getMobile())
                    .addParameter("gender", customer.getGender())
                    .addParameter("email", customer.getEmail())
                    .addParameter("customerid", customer.getCustomerid())
                    .addParameter("stylistid", customer.getStylistid())
                    .executeUpdate();
        }
    }
    public static List<DataProperties> allstylist(){
        String sql = "SELECT stylistid, fname, sname FROM stylist";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(DataProperties.class);
        }
    }

    public static List<DataProperties> allcustomers(){
        String sql = "SELECT  fname, sname, lname, mobile, gender, email,customerid,stylistid FROM customer";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(DataProperties.class);
        }
    }
}
