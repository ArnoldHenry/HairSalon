import org.sql2o.Connection;

import java.util.List;

public class HairSalonDB {
    HairSalonDP hdp = new HairSalonDP();

    public static List<HairSalonDP> all(){
        String sql = "SELECT stylistid, fname, sname, lname, mobile, gender, email FROM stylist";
        try(Connection con = DB.sql2oHair.open()) {
            return con.createQuery(sql).executeAndFetch(HairSalonDP.class);
        }
    }
    public static List<HairSalonDP> allstylist(){
        String sql = "SELECT stylistid, fname, sname FROM stylist";
        try(Connection con = DB.sql2oHair.open()) {
            return con.createQuery(sql).executeAndFetch(HairSalonDP.class);
        }
    }

    public static List<HairSalonDP> allcustomers(){
        String sql = "SELECT  fname, sname, lname, mobile, gender, email,customerid,stylistid FROM customer";
        try(Connection con = DB.sql2oHair.open()) {
            return con.createQuery(sql).executeAndFetch(HairSalonDP.class);
        }
    }

//validate admin login
    public static String adminval(HairSalonDP admincheck) {
        try(Connection con = DB.sql2oHair.open()) {
            String sql = "SELECT username,password FROM admin WHERE username=:uname AND password=:passw";
            String loginval = con.createQuery(sql)
                    .addParameter("uname",admincheck.getUname())
                    .addParameter("passw",admincheck.getPassword())
                    .executeScalar(String.class);
            return loginval;
        }
    }


    //save admin credentials
    public void save(HairSalonDP admin) {
        try (Connection connection = DB.sql2oHair.open()) {
            String newdata = "INSERT INTO admin(username,password)VALUES(:uname,:passw)";
            connection.createQuery(newdata)
                    .addParameter("uname", admin.getUname())
                    .addParameter("passw", admin.getPassword())
                    .executeUpdate();

        }
    }
//save stylist credentials
    public void savestylist(HairSalonDP stylist) {
        try (Connection connection = DB.sql2oHair.open()) {
            String newdata = "INSERT INTO stylist(stylistid,fname,sname,lname,mobile,gender,email)" +
                    "VALUES(:stylistid,:fname,:sname,:lname,:mobile,:gender,:email)";
            connection.createQuery(newdata)
                    .addParameter("stylistid", stylist.getStylistid())
                    .addParameter("fname", stylist.getFname())
                    .addParameter("sname", stylist.getSname())
                    .addParameter("lname", stylist.getLname())
                    .addParameter("mobile", stylist.getMobile())
                    .addParameter("gender", stylist.getGender())
                    .addParameter("email", stylist.getEmail())
                    .executeUpdate();
        }
    }
    //save customer credentials
    public void savecustomer(HairSalonDP customer) {
        try (Connection connection = DB.sql2oHair.open()) {
            String newdata = "INSERT INTO customer(fname,sname,lname,mobile,gender,email,customerid,stylistid)" +
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

    public static HairSalonDB find(String username){
        try(Connection connection = DB.sql2oHair.open()){
            String sql = "SELECT username FROM admin WHERE username=:username";
            HairSalonDB db = connection.createQuery(sql)
                    .addParameter("username",username)
                    .executeAndFetchFirst(HairSalonDB.class);
            return db;
        }
    }
    public void update(String update){
        try(Connection connection = DB.sql2oHair.open()){
            String sql = "UPDATE fname,sname,lname,mobile,gender,email FROM stylist WHERE fname=:fname";
            connection.createQuery(sql)
                    .addParameter("fname",hdp.getFname())
                    .addParameter("sname",hdp.getSname())
                    .addParameter("lname",hdp.getLname())
                    .addParameter("mobile",hdp.getMobile())
                    .addParameter("gender",hdp.getGender())
                    .addParameter("email",hdp.getEmail())
                    .executeUpdate();
        }
    }


}