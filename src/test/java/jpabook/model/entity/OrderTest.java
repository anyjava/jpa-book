package jpabook.model.entity;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by anyjava on 2016. 3. 23..
 */
public class OrderTest {

    private EntityManager em;
    private EntityTransaction tx;

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");

    @Before
    public void setUp() {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    @Test
    public void testOrder() {

        try {
            this.tx.begin();

            Order order = new Order();

            em.persist(order);

            this.tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.tx.rollback();
        }
    }

}