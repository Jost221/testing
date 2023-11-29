package apiTest;

import apiTest.Create.RequestCreate;
import apiTest.Create.RootResponseCreate;
import apiTest.DelayedResponse.RootResponse;
import apiTest.ListUsers.RootList;
import apiTest.LoginSuccessful.*;
import apiTest.LoginUnsuccessful.RootError;
import apiTest.MainData.ResourceData;
import apiTest.MainData.Support;
import apiTest.MainData.UserData;
import apiTest.NotFound.RootEpty;
import apiTest.RegisterSuccesfull.Register;
import apiTest.RegisterSuccesfull.RootRegister;
import apiTest.RegisterUnsuccessful.Error;
import apiTest.RegisterUnsuccessful.RootRequest;
import apiTest.ResourceList.RootResource;
import apiTest.SingleResource.RootSingleResource;
import apiTest.SingleUser.RootSingle;
import apiTest.Update.RootUpdatePut;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
public class ReqresTest {
    public final static  String URL = "https://reqres.in/";

    //LIST USER
    @Test
    public void ListUser(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

         RootList data = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().as(RootList.class);
        Assert.assertEquals(2, data.page);
        Assert.assertEquals(6, data.per_page);
        Assert.assertEquals(12, data.total);
        Assert.assertEquals(2, data.total_pages);

        List<UserData> users = data.data;

        users.forEach(user->{
            Assert.assertTrue("Error check User Data", checkData(user));
        });

        Assert.assertTrue(checkSupport(data.support));
    }

    @Test
    public void singleUser(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

        RootSingle data = given()
                .when()
                .get("/api/users/2")
                .then().log().all()
                .extract().as(RootSingle.class);
        Assert.assertTrue("Error check User Data", checkData(data.data));
        Assert.assertTrue(checkSupport(data.support));
    }

    @Test
    public void single_user_not_found() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec404());

        RootEpty data = given()
                .when()
                .get("api/users/23")
                .then().log().all()
                .extract().as(RootEpty.class);
    }

    @Test
    public void resourceList(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

        RootResource data = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().as(RootResource.class);

        Assert.assertEquals(1, data.page);
        Assert.assertEquals(6, data.per_page);
        Assert.assertEquals(12, data.total);
        Assert.assertEquals(2, data.total_pages);

        for(int i = 0; i < data.data.size(); i++){
            checkResource(data.data.get(i));
            if (i > 0){
                Assert.assertTrue(data.data.get(i - 1).year < data.data.get(i).year);
                Assert.assertTrue(data.data.get(i - 1).id < data.data.get(i).id);
            }
        }

        Assert.assertTrue(checkSupport(data.support));
    }

    @Test
    public void singleResource(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

        RootSingleResource data = given()
                .when()
                .get("api/unknown/2")
                .then().log().all()
                .extract().as(RootSingleResource.class);

        Assert.assertTrue("Error check User Data", checkResource(data.data));
        Assert.assertTrue(checkSupport(data.support));
    }

    @Test
    public void singleResourceNotFound(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec404());

        RootSingleResource data = given()
                .when()
                .get("api/unknown/23")
                .then().log().all()
                .extract().as(RootSingleResource.class);
    }

    @Test
    public void create(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec201());

        RequestCreate requestCreate = new RequestCreate("morpheus", "leader");

        RootResponseCreate data = given()
                .header("Content-type", "application/json")
                .when()
                .body(requestCreate)
                .post("api/users")
                .then().log().all()
                .extract().as(RootResponseCreate.class);
        Assert.assertEquals("morpheus", data.name);
        Assert.assertEquals("leader", data.job);
        Assert.assertNotNull(data.id);
        Assert.assertTrue(new Date().getTime() > data.createdAt.getTime());
    }

    @Test
    public void putUpdate(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

        RequestCreate requestCreate = new RequestCreate("morpheus", "zion resident");

        RootUpdatePut data = given()
                .header("Content-type", "application/json")
                .when()
                .body(requestCreate)
                .put("api/users/2")
                .then().log().all()
                .extract().as(RootUpdatePut.class);

        Assert.assertEquals("morpheus", data.name);
        Assert.assertEquals("zion resident", data.job);
        Assert.assertTrue(new Date().getTime() > data.updatedAt.getTime());
    }

    @Test
    public void pathUpdate(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

        RequestCreate requestCreate = new RequestCreate("morpheus", "zion resident");

        RootUpdatePut data = given()
                .header("Content-type", "application/json")
                .when()
                .body(requestCreate)
                .patch("api/users/2")
                .then().log().all()
                .extract().as(RootUpdatePut.class);

        Assert.assertEquals("morpheus", data.name);
        Assert.assertEquals("zion resident", data.job);
        Assert.assertTrue(new Date().getTime() > data.updatedAt.getTime());
    }

    @Test
    public void delete() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec204());

        Response data = given()
                .header("Content-type", "application/json")
                .when()
                .delete("api/users/2")
                .then()
                .extract().response();
    }

    @Test
    public void registerSuccessfull(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

        RootRegister rootRegister = new RootRegister("eve.holt@reqres.in", "pistol");

        Register data = given()
                .header("Content-type", "application/json")
                .when()
                .body(rootRegister)
                .post("api/register")
                .then().log().all()
                .extract().as(Register.class);

        Assert.assertEquals(4, data.id);
        Assert.assertEquals("QpwL5tke4Pnpja7X4", data.token);
    }

    @Test
    public void registerUnsuccessful(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec400());

        RootRequest rootRequest = new RootRequest("eve.holt@reqres.in");

        Error data = given()
                .header("Content-type", "application/json")
                .when()
                .body(rootRequest)
                .post("api/register")
                .then().log().all()
                .extract().as(Error.class);

        Assert.assertEquals("Missing password", data.error);
    }

    @Test
    public void LoginSuccessful(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

        RootLogin rootLogin = new RootLogin("eve.holt@reqres.in", "cityslicka");

        Login data = given()
                .header("Content-type", "application/json")
                .when()
                .body(rootLogin)
                .post("api/login")
                .then().log().all()
                .extract().as(Login.class);

        Assert.assertEquals("QpwL5tke4Pnpja7X4", data.token);
    }
    @Test
    public void LoginUnsuccessful(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec400());

        RootRequest rootRequest = new RootRequest("eve.holt@reqres.in");

        RootError data = given()
                .header("Content-type", "application/json")
                .when()
                .body(rootRequest)
                .post("api/login")
                .then().log().all()
                .extract().as(RootError.class);

        Assert.assertEquals("Missing password", data.error);
    }

    @Test
    public void delayedResponse(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());

        RootResponse data = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().as(RootResponse.class);

        Assert.assertEquals(2, data.page);
        Assert.assertEquals(6, data.per_page);
        Assert.assertEquals(2, data.total_pages);

        for(int i = 0; i < data.data.size(); i++){
            Assert.assertTrue(checkData(data.data.get(i)));
            if (i > 0)
                Assert.assertTrue(data.data.get(i).id > data.data.get(i-1).id);
        }
    }

    public Boolean checkData(UserData user){
        Assert.assertNotNull(user.id);
        Assert.assertNotNull(user.email);
        Assert.assertNotNull(user.first_name);
        Assert.assertNotNull(user.last_name);
        Assert.assertNotNull(user.avatar);
        Assert.assertTrue(user.email.endsWith("@reqres.in"));
        Assert.assertTrue(user.avatar.contains("https://reqres.in/img/faces/"+user.id.toString()+"-image.jpg"));
        return true;
    }

    public Boolean checkSupport(Support support){
        Assert.assertNotNull(support.url);
        Assert.assertNotNull(support.text);
        Assert.assertTrue(support.url.contains("https://reqres.in/#support-heading"));
        return true;
    }

    public Boolean checkResource(ResourceData resource){
        Assert.assertNotNull(resource.id);
        Assert.assertNotNull(resource.name);
        Assert.assertNotNull(resource.year);
        Assert.assertNotNull(resource.color);
        Assert.assertNotNull(resource.pantone_value);
        return true;
    }

//    @Test
//    public void successRegTest(){
//        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec200());
//        Integer id = 4;
//        String token = "QpwL5tke4Pnpja7X4";
//        Register user = new Register("eve.holt@reqres.inoqiwe", "pistol");
//        SuccessReg successReg = given()
//                .body(user)
//                .when()
//                .post("apiTest/register")
//                .then().log().all()
//                .extract().as(SuccessReg.class);
//        Assert.assertNotNull(successReg.getId());
//        Assert.assertNotNull(successReg.getToken());
//
//        Assert.assertEquals(id, successReg.getId());
//        Assert.assertEquals(token, successReg.getToken());
//    }

//        users.forEach(x-> Assert.assertTrue(x.avatar.contains(x.id.toString())));
//
//        Assert.assertTrue(users.stream().allMatch(x-> x.getEmail().endsWith("@reqres.in")));
//
//        List<String> avatars = users.stream().map(UserData::Avatar).toList();
//        List<String> id = users.stream().map(x->x.getId().toString()).toList();
//
//        for (int i = 0; i<avatars.size(); i++){
//            Assert.assertTrue(avatars.get(i).contains(id.get(i)));
//        }
}


