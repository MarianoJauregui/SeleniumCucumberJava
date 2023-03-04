package pages;

import static io.restassured.RestAssured.given;

import java.util.Base64;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ejemplosAPI {

    public void GETRequest(){
        given()
        .baseUri("https://api.github.com/")
        .when()
        .get("/users/TheFreeRangeTester/repos")
        .getBody()
        .toString();
    }

    public void POSTRequest(){
        given()
        .baseUri("baseUri")
        .when()
        .post("", "");
    }

    public void PUTRequest(){
        given()
        .baseUri("null")
        .put();
    }

    public void DELETERequest(){
        given()
        .baseUri("")
        .when()
        .delete();
    }

    public void basicAuth(String username, String password){
        given()
        .auth().basic(username, password)
        .when()
        .get("AUTH_ENDPOINT")
        .then()
        .assertThat()
        .statusCode(200);
    }

    public void formAuth(String username, String password){
        given()
        .auth().form(username, password)
        .when()
        .get("SERVICE")
        .then()
        .assertThat()
        .statusCode(200);
    }

    /*
     * Obtener el código del servicio original para obtener el token.
     * Obtener el token intercambiando el código que obtuvimos.
     * Acceder al recurso protegido, con nuestro token.
     */

     public static String clientID = "";
     public static String redirectURI = "";
     public static String scope = "";
     public static String username = "";
     public static String password = "";
     public static String grantType = "";
     public static String accesToken = "";

     public static String encode(String str1, String str2){
        return new String(Base64.getEncoder().encode((str1+ ":" + str2).getBytes()));
     }

     public static Response getCode(){
        String authorization = encode(username, password);

        return 
                given().header("authorization", "Basic" + authorization).contentType(ContentType.URLENC)
                .formParam("response_type", "code").queryParam("client_id", clientID)
                .queryParam("redirect_uri", redirectURI).queryParam("scope", scope).post("/oauth/authorize")
                .then()
                .statusCode(200).extract().response();
     }

     public static String parseForOAuthCode(Response response) {
        return response.jsonPath().getString("code");
     }

     public static Response getToken(String authCode){
        String authorization = encode(username, password);

        return 
                given().header("authorization", "Basic" + authorization).contentType(ContentType.URLENC)
                .formParam("response_type", authCode).queryParam("redirect_uri", redirectURI)
                .queryParam("grant_type", grantType).post("/oauth/token")
                .then()
                .statusCode(200).extract().response();
     }

     public static String parseForToken(Response loginResponse){
        return loginResponse.jsonPath().getString("acces_token");
     }

     public static void getFinalService(){
        given().auth()
        .oauth2(accesToken)
        .when()
        .get("/service")
        .then()
        .statusCode(200);
     }
    
}
