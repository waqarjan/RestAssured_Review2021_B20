package day03;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class GithubRestAPITest {

    /*
    {
    "login": "CybertekSchool",
    "id": 33201481,
    "node_id": "MDQ6VXNlcjMzMjAxNDgx",
    "avatar_url": "https://avatars.githubusercontent.com/u/33201481?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/CybertekSchool",
    "html_url": "https://github.com/CybertekSchool",
    "followers_url": "https://api.github.com/users/CybertekSchool/followers",
    "following_url": "https://api.github.com/users/CybertekSchool/following{/other_user}",
    "gists_url": "https://api.github.com/users/CybertekSchool/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/CybertekSchool/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/CybertekSchool/subscriptions",
    "organizations_url": "https://api.github.com/users/CybertekSchool/orgs",
    "repos_url": "https://api.github.com/users/CybertekSchool/repos",
    "events_url": "https://api.github.com/users/CybertekSchool/events{/privacy}",
    "received_events_url": "https://api.github.com/users/CybertekSchool/received_events",
    "type": "User",
    "site_admin": false,
    "name": "Cybertek School",
    "company": "Cybertek",
    "blog": "www.cybertekschool.com",
    "location": null,
    "email": null,
    "hireable": null,
    "bio": "If you are a student and need access to any of the repositories, email support@cybertekschool.com to request access.",
    "twitter_username": null,
    "public_repos": 3,
    "public_gists": 0,
    "followers": 1432,
    "following": 0,
    "created_at": "2017-10-29T15:57:33Z",
    "updated_at": "2021-06-22T23:40:40Z"
}
     */

    //create a test for testing github rest api users/user endpoint

    @DisplayName("Test GitHub GET /users/{username}")
    @Test
    public void testGitHub() {
        given()
                .pathParam("username", "CybertekSchool").
                when()
                .get("https://api.github.com/users/{username}").
                then()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .header("Server", "GitHub.com")
                .body("login", is("CybertekSchool"))
                .body("id", is(33201481))
        ;
    }
}
