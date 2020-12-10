package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Temporal;
import javax.transaction.UserTransaction;

import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Inject
    EntityManager em;

    @Inject
    UserTransaction userTransaction;

    @Test
    public void testHibernateBug() throws Exception {
        Team team = (Team) em.createQuery("select t from Team t").getSingleResult();
        userTransaction.begin();
        em.merge(team);
        userTransaction.commit();
    }

}