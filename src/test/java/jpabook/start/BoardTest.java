package jpabook.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class BoardTest {

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
    public void testFirstLevelCache() {

        try {
            this.tx.begin();

            // Given
            Board board = new Board();
            board.setId("id1");
            board.setTitle("notice");
            board.setContent("notice contents!!!");
            board.setViewCount(1);
            em.persist(board);

            // When
            Board selectedBoard = em.find(Board.class, board.getId());

            // Then
            assertEquals(board, selectedBoard);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.tx.rollback();
        }
    }

    @Test
    public void testSameObjectCreateWithJPA() {

        try {
            this.tx.begin();

            // Given
            Board board = new Board();
            board.setId("id2");
            board.setTitle("notice");
            board.setContent("notice contents!!!");
            board.setViewCount(1);
            em.persist(board);

            tx.commit();

            // When
            Board selectedBoard = em.find(Board.class, board.getId());

            // Then
            assertTrue(selectedBoard == board);

        } catch (Exception e) {
            e.printStackTrace();
            this.tx.rollback();
        }

    }

    @Test
    public void testLazyWrite() {
        try {
            this.tx.begin();

            // Given
            Board board = new Board();
            board.setId("id2");
            board.setTitle("notice");
            board.setContent("notice contents!!!");
            board.setViewCount(1);
            em.persist(board);

            System.out.println("BEFORE Commit!!");

            tx.commit();

            System.out.println("AFTER Commit!!");


        } catch (Exception e) {
            e.printStackTrace();
            this.tx.rollback();
        }

    }


    @After
    public void tearDown() {
        em.close();
    }
}