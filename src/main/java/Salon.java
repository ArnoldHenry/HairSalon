import spark.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;

public class Salon {
    public static void main(String[] args)throws NoSuchAlgorithmException {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);

        String layout = "templates/layout.vtl";
        staticFileLocation ("/public");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        HairSalonDP hairdp = new HairSalonDP();
        HairSalonDB hb = new HairSalonDB();

        get("/back",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template","/templates/stylistregform.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template","/templates/login.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        post("/login",((request, response) ->{
            Map<String,Object> model = new HashMap<String,Object>();

            String username = request.queryParams("uname");
            hairdp.setUname(username);

            String password = request.queryParams("passw");
            byte[] pass = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hairdp.setPassword(Arrays.toString(pass));
//            hb.save(hairdp);
            String confirm = hb.adminval(hairdp);
            if (confirm != null){
                model.put("template","/templates/stylistregform.vtl");
            }else{
                model.put("template","/templates/caution.vtl");
            }
            model.put("stylists",HairSalonDB.allstylist());
            return new ModelAndView(model,layout);
        }),new VelocityTemplateEngine());


        post("/stylistform",(request,respond)->{
            Map<String,Object> model = new HashMap<String,Object>();

            String stylistid = request.queryParams("stylistid");
            hairdp.setStylistid(stylistid);
            String fname = request.queryParams("fname");
            hairdp.setFname(fname);
            String sname = request.queryParams("sname");
            hairdp.setSname(sname);
            String lname = request.queryParams("lname");
            hairdp.setLname(lname);
            int mobile = Integer.parseInt(request.queryParams("mobile"));
            hairdp.setMobile(mobile);
            String gender = request.queryParams("gender");
            hairdp.setGender(gender);
            String email = request.queryParams("email");
            hairdp.setEmail(email);

            hb.savestylist(hairdp);
            model.put("template","/templates/stylistregform.vtl");
            return new  ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        post("/customer",(request,respond)->{
            Map<String,Object> model = new HashMap<String,Object>();

            String fname = request.queryParams("fname");
            hairdp.setFname(fname);
            String sname = request.queryParams("sname");
            hairdp.setSname(sname);
            String lname = request.queryParams("lname");
            hairdp.setLname(lname);
            int mobile = Integer.parseInt(request.queryParams("mobile"));
            hairdp.setMobile(mobile);
            String gender = request.queryParams("gender");
            hairdp.setGender(gender);
            String email = request.queryParams("email");
            hairdp.setEmail(email);
            String customerid = request.queryParams("customerid");
            hairdp.setCustomerid(customerid);
            String stylistid = request.queryParams("stylistid");
            hairdp.setStylistid(stylistid);

            hb.savecustomer(hairdp);
            model.put("template","/templates/stylistregform.vtl");
            return new  ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/viewstylist",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();
            try{
                model.put("stylists",HairSalonDB.all());
                model.put("template","/templates/records.vtl");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/viewcustomers",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();
            try{
                model.put("customers",HairSalonDB.allcustomers());
                model.put("template","/templates/customers.vtl");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());


    }
    }

