import org.sql2o.Connection;

import java.util.List;

public class HairSalonDB {
    HairSalonDP hdp = new HairSalonDP();
    private String fname,sname,lname,gender,email,mobile;
    private int  id ;
//
//    public HairSalonDB( String fname, String sname, String lname,String mobile, String gender, String email) {
//        this.fname = fname;
//        this.sname = sname;
//        this.lname = lname;
//        this.gender = gender;
//        this.email = email;
//        this.mobile= mobile;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getFname() {
//        return fname;
//    }
//
//    public String getLname() {
//        return lname;
//    }
//
//    public String getSname() {
//        return sname;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//


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
    //confirm stylist id
    public static String select(HairSalonDP idcheck) {
        try(Connection con = DB.sql2oHair.open()) {
            String sql = "SELECT stylistid FROM stylist WHERE stylistid=:stylistid";
            String idstylist = con.createQuery(sql)
                    .addParameter("stylistid",idcheck.getStylistid())
                    .executeScalar(String.class);
            return idstylist;
        }
    }
    //confirm stylist id
    public static String selectcustomer(HairSalonDP idcheck) {
        try(Connection con = DB.sql2oHair.open()) {
            String sql = "SELECT customer FROM customer WHERE customerid=:customerid";
            String idcustomer = con.createQuery(sql)
                    .addParameter("customerid",idcheck.getCustomerid())
                    .executeScalar(String.class);
            return idcustomer;
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

    public void delstylist(HairSalonDP stylistid){
        try(Connection connection = DB.sql2oHair.open()){
            String sql = "DELETE FROM stylist WHERE stylistid=:stylistid";
            connection.createQuery(sql)
                    .addParameter("stylistid",stylistid.getStylistid())
                    .executeUpdate();

        }
    }

    public void delcustomer(HairSalonDP customerid){
        try(Connection connection = DB.sql2oHair.open()){
            String sql = "DELETE FROM customer WHERE customerid=:customerid";
            connection.createQuery(sql)
                    .addParameter("customerid",customerid.getCustomerid())
                    .executeUpdate();
        }
    }

    public void updatestylist(String fname,String sname,String lname,String mobile,String gender,String email){
        try(Connection connection = DB.sql2oHair.open()){
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
    public void updateclient(String fname,String sname,String lname,String mobile,String gender,String email){
        try(Connection connection = DB.sql2oHair.open()){
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
    @Override
    public boolean equals(Object otheruname){
        if (!(otheruname instanceof HairSalonDP)){
            return false;
        }else{
            HairSalonDP dp = (HairSalonDP) otheruname;
            return dp.getFname().equals(otheruname)&&
                    dp.getSname().equals(otheruname)&&
                    dp.getLname().equals(otheruname)&&
                    dp.getMobile().equals(otheruname)&&
                    dp.getGender().equals(otheruname)&&
                    dp.getEmail().equals(otheruname);
        }
    }

}