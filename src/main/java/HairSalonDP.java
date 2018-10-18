import java.time.LocalDateTime;

public class HairSalonDP {
    private String uname,fname,sname,lname,gender,email,stylistid,customerid;
    private String password;
    private int id,mobile;
    private LocalDateTime createdAt;

    public String getUname() {
        return uname;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getStylistid() {
        return stylistid;
    }

    public void setStylistid(String stylistid) {
        this.stylistid = stylistid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getGender() {
        return gender;
    }

    public int getMobile() {
        return mobile;
    }

    public String getLname() {
        return lname;
    }

    public String getSname() {
        return sname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
