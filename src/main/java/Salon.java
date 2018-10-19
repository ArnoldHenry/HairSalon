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

        get("/backhome",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template","/templates/stylistregform.vtl");
            model.put("stylists",HairSalonDB.allstylist());
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("template","/templates/login.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        post("/login",((request, response) ->{
            Map<String,Object> model = new HashMap<String,Object>();

            String adminuname = request.queryParams("userid");
            hairdp.setUname(adminuname);

            String stylistid = request.queryParams("userid");
            hairdp.setStylistid(stylistid);

            String password = request.queryParams("passw");
            byte[] pass = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hairdp.setPassword(Arrays.toString(pass));
//            hb.save(hairdp);

            String confirmadmin = HairSalonDB.adminval(hairdp);

            String confirmstylist = HairSalonDB.stylistval(hairdp);
            if (confirmadmin != null){
                model.put("template","/templates/stylistregform.vtl");
            }else if(confirmstylist != null){
                model.put("stylstcust",HairSalonDB.stylistcustomers(hairdp));
                model.put("template","/templates/stylistpage.vtl");
            }else{
                model.put("template","/templates/caution.vtl");
            }
            model.put("stylists",HairSalonDB.allstylist());
            return new ModelAndView(model,layout);
        }),new VelocityTemplateEngine());


        post("/stylistform",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();

            String stylistid = request.queryParams("stylistid");
            hairdp.setStylistid(stylistid);
            String fname = request.queryParams("fname");
            hairdp.setFname(fname);
            String sname = request.queryParams("sname");
            hairdp.setSname(sname);
            String lname = request.queryParams("lname");
            hairdp.setLname(lname);
            Integer mobile = Integer.parseInt (request.queryParams("mobile"));
            hairdp.setMobile(mobile);
            String gender = request.queryParams("gender");
            hairdp.setGender(gender);
            String email = request.queryParams("email");
            hairdp.setEmail(email);
            String password = request.queryParams("password");
            byte[] pass = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hairdp.setPassword(Arrays.toString(pass));

            hb.savestylist(hairdp);
            response.redirect("/backhome");
            return new  ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        post("/customer",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();

            String fname = request.queryParams("fname");
            hairdp.setFname(fname);
            String sname = request.queryParams("sname");
            hairdp.setSname(sname);
            String lname = request.queryParams("lname");
            hairdp.setLname(lname);
            Integer mobile = Integer.parseInt (request.queryParams("mobile"));
            hairdp.setMobile(mobile);
            String gender = request.queryParams("gender");
            hairdp.setGender(gender);
            String email = request.queryParams("email");
            hairdp.setEmail(email);
            String customerid = request.queryParams("clientid");
            hairdp.setCustomerid(customerid);
            String stylistid = request.queryParams("stylistid");
            hairdp.setStylistid(stylistid);

            hb.savecustomer(hairdp);
            response.redirect("/backhome");
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

//view stylist customers

        get("/stylistcustomers",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();

            try{
//                model.put("customers",HairSalonDB.stylistcustomers());
                model.put("template","/templates/stylistpage.vtl");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());


        post("/delcustomer",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();
            try{
                String customerid = request.queryParams("delcustomer");
                hairdp.setCustomerid(customerid);
                String df = HairSalonDB.selectcustomer(hairdp);
                if ( df != null) {
                    hb.delcustomer(hairdp);
                    response.redirect("/backhome");
                } else{
                    model.put("template","/templates/caution.vtl");
                    System.out.println(HairSalonDB.select(hairdp));
                }

            }catch(Exception e){
                System.out.println(e.getMessage());
            }


            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());



        post("/delstylist",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();
            try{
                String stylistid = request.queryParams("delstylist");
                hairdp.setStylistid(stylistid);
                String df = HairSalonDB.select(hairdp);
                if ( df != null) {
                 hb.delstylist(hairdp);
                    response.redirect("/backhome");
                } else{
                    model.put("template","/templates/caution.vtl");
                    System.out.println(HairSalonDB.select(hairdp));
                }

            }catch(Exception e){
                System.out.println(e.getMessage());
            }


            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());
    }
    }

