package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class ex24_11_2 {
    public static void main(String[] args) throws JsonProcessingException {
        RestAssured.baseURI = "https://ghibliapi.vercel.app";

        Response response = given()
                .pathParams("FILM_ID", "2baf70d1-42bb-4437-b551-e5fed5a87abe")
                .when()
                .get("/films/{FILM_ID}");

        String jsonResponse1 = response.getBody().asString();
        ObjectMapper objectMapper1 = new ObjectMapper();
        Film film1 = objectMapper1.readValue(jsonResponse1, Film.class);
        System.out.println(film1);

        Response response2 = given()
                .pathParams("FILM_ID", "ebbb6b7c-945c-41ee-a792-de0e43191bd8")
                .when()
                .get("/films/{FILM_ID}");

        String jsonResponse2 = response2.getBody().asString();
        ObjectMapper objectMapper2 = new ObjectMapper();
        Film film2 = objectMapper2.readValue(jsonResponse2, Film.class);
        System.out.println(film2);

        Response response3 = given()
                .pathParams("FILM_ID", "ff24da26-a969-4f0e-ba1e-a122ead6c6e3")
                .when()
                .get("/films/{FILM_ID}");

        String jsonResponse3 = response3.getBody().asString();
        ObjectMapper objectMapper3 = new ObjectMapper();
        Film film3 = objectMapper3.readValue(jsonResponse3, Film.class);
        System.out.println(film3);

        Response response4 = given()
                .pathParams("FILM_ID", "90b72513-afd4-4570-84de-a56c312fdf81")
                .when()
                .get("/films/{FILM_ID}");

        String jsonResponse4 = response4.getBody().asString();
        ObjectMapper objectMapper4 = new ObjectMapper();
        Film film4 = objectMapper4.readValue(jsonResponse4, Film.class);
        System.out.println(film4);

    }
}

class Film {
    private String id;
    private String title;
    private String original_title;
    private String original_title_romanised;
    private String image;
    private String movie_banner;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String running_time;
    private String rt_score;
    private String[] people;
    private String[] species;
    private String[] locations;
    private String[] vehicles;
    private String url;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOriginal_title_romanised(String original_title_romanised) {
        this.original_title_romanised = original_title_romanised;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMovie_banner(String movie_banner) {
        this.movie_banner = movie_banner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }

    public void setRt_score(String rt_score) {
        this.rt_score = rt_score;
    }

    public void setPeople(String[] people) {
        this.people = people;
    }

    public void setSpecies(String[] species) {
        this.species = species;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public void setVehicles(String[] vehicles) {
        this.vehicles = vehicles;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", original_title_romanised='" + original_title_romanised + '\'' +
                ", image='" + image + '\'' +
                ", movie_banner='" + movie_banner + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", release_date='" + release_date + '\'' +
                ", running_time='" + running_time + '\'' +
                ", rt_score='" + rt_score + '\'' +
                ", people=" + Arrays.toString(people) +
                ", species=" + Arrays.toString(species) +
                ", locations=" + Arrays.toString(locations) +
                ", vehicles=" + Arrays.toString(vehicles) +
                ", url='" + url + '\'' +
                '}';
    }
}
