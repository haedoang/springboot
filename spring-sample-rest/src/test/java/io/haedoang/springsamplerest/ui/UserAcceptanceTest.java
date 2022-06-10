package io.haedoang.springsamplerest.ui;

import io.haedoang.springsamplerest.domain.User;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.util.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.config;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.MultiPartConfig.multiPartConfig;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * fileName : UserRestControllerTest
 * author : haedoang
 * date : 2022-06-10
 * description :
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserAcceptanceTest {
    public static final String BASE_URI = "api/v1/users";
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("리스트 조회")
    public void getRequest() {
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(BASE_URI)
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("사용자 등록 by json")
    public void postJson() {
        // given
        User request = User.valueOf(null, "새로운사용자", 55);

        // when
        ExtractableResponse<Response> actual = RestAssured.given().log().all()
                .urlEncodingEnabled(true)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(request)
                .post(BASE_URI + "/json")
                .then().log().all()
                .extract();

        // then
        assertThat(actual.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("사용자 등록 by formdata")
    public void postFormData() {
        // given
        User request = User.valueOf(null, "새로운사용자", 55);

        // when
        ExtractableResponse<Response> actual = RestAssured.given().log().all()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .when()
                .formParam("name", request.getName())
                .formParam("age", request.getAge())
                .post(BASE_URI + "/form")
                .then().log().all()
                .extract();

        // then
        assertThat(actual.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actual.jsonPath().getObject("", User.class).getName()).isEqualTo("새로운사용자");
    }

    @Test
    @DisplayName("multiple file upload by multipart")
    public void postMultipart() {
        // when
        ExtractableResponse<Response> actual = RestAssured.given().log().all()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart("files", Files.newTemporaryFile())
                .multiPart("files", Files.newTemporaryFile())
                .multiPart("files", Files.newTemporaryFile())
                .when()
                .post(BASE_URI + "/multipart")
                .then().log().all()
                .extract();

        // then
        assertThat(actual.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(actual.body().jsonPath().getList("", String.class)).hasSize(3);
    }

    @Test
    @DisplayName("파일 업로드와 오브젝트 파라미터 by multipart")
    public void postMultipartWithObj() {
        // given
        User request = User.valueOf(null, "새로운사용자", 34);

        // when
        ExtractableResponse<Response> actual = RestAssured.given().log().all()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart("files", Files.newTemporaryFile())
                .multiPart("files", Files.newTemporaryFile())
                .multiPart("files", Files.newTemporaryFile())
                .multiPart(new MultiPartSpecBuilder(request.getName()).controlName("name").charset("UTF-8").build())
                .multiPart("age", request.getAge())
                .when()
                .post(BASE_URI + "/multipart2")
                .then().log().all()
                .extract();

        // then
        assertThat(actual.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
