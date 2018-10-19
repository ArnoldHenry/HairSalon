import org.sql2o.Connection;

import java.time.LocalDateTime;
import java.util.List;

public class DataProperties {
    private String fname,sname,lname,gender,email,mobile;
    private int  id ;

    public DataProperties( String fname, String sname, String lname,String mobile, String gender, String email) {
        this.fname = fname;
        this.sname = sname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.mobile= mobile;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getSname() {
        return sname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }


    public static List<DataProperties> all(){
        try (Connection connection = DB.sql2o.open()){
            String sql = "SELECT fname,sname,lname,mobile,gender,email FROM stylist";
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
            String newdata = "INSERT INTO stylist(fname,sname,lname,mobile,gender,email)" +
                    "VALUES(:fname,:sname,:lname,:mobile,:gender,:email)";
            connection.createQuery(newdata)
                    .addParameter("fname",this.fname)
                    .addParameter("sname",this.sname)
                    .addParameter("lname",this.lname)
                    .addParameter("mobile",this.mobile)
                    .addParameter("gender",this.gender)
                    .addParameter("email",this.email)
                    .executeUpdate();

        }
    }
    public void update(String fname,String sname,String lname,String mobile,String gender,String email){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE stylist SET( fname,sname,lname,mobile,gender,email ) = (:fname,:sname,:lname,:mobile,:gender,:email) WHERE id=:id;";
            connection.createQuery(sql)
                    .addParameter("fname",fname)
                    .addParameter("sname",sname)
                    .addParameter("lname",lname)
                    .addParameter("mobile",mobile)
                    .addParameter("gender",gender)
                    .addParameter("email",email)
                    .addParameter("id",id)
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
        String sql = "SELECT fname, sname, lname, mobile, gender, email FROM stylist";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(DataProperties.class);
        }
    }

    public static List<DataProperties> allcustomers(){
        String sql = "SELECT  fname, sname, lname, mobile, gender, email FROM customer";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(DataProperties.class);
        }
    }


    @Override
    public boolean equals(Object otheruname){
        if (!(otheruname instanceof DataProperties)){
            return false;
        }else{
            DataProperties dp = (DataProperties) otheruname;
            return this.getFname().equals(dp.getFname())&&
                    this.getSname().equals(dp.getSname())&&
                    this.getLname().equals(dp.getLname())&&
                    this.getMobile().equals(dp.getMobile())&&
                    this.getGender().equals(dp.getGender())&&
                    this.getEmail().equals(dp.getEmail());
        }
    }
}