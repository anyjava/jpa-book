package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
//            logic(em);
            logic2(em);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic2(EntityManager em) {

        Board board = new Board();
        board.setId("id1");
        board.setTitle("테스트");
        board.setContent("dsafasdfsad");
        board.setViewCount(10);

        em.persist(board);

        board.setContent("modified content");
        board.setTitle("modified Title");


        Board findBoard = em.find(Board.class, board.getId());
        System.out.println("findBoard = " + findBoard);


        String findStr = "ed";
        List<Board> resultList
                = em.createQuery(
                        "select b " +
                        "from Board b " +
                        "where b.title like :search", Board.class)
                .setParameter("search", "%" + findStr + "%")
                .getResultList();

        System.out.println("resultList = " + resultList);

        for (Board b : resultList) {
            System.out.println("board = " + b);
        }

        em.remove(board);

        Collection c;
    }

    private static void logic(EntityManager em) {

        Member member = new Member();
        member.setId("id3");
        member.setUsername("SON");
        member.setAge(17);

        em.persist(member);


        // 수정
        member.setAge(10);
        member.setUsername("hihi");

        // 단건조회
        Member findMember = em.find(Member.class, member.getId());
        System.out.println("findMember = " + findMember);

        // 목록조회
        List<Member> resultList
                = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        System.out.println("resultList = " + resultList);

        em.remove(member);

    }
}

