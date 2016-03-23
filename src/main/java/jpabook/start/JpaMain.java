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
            logic(em);
//            logic2(em);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
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

        Team team = new Team();
        team.setName("teamA");

        Member member = new Member();
        member.setUsername("SON");
        member.setAge(17);
        member.setTeam(team);

        em.persist(member);
        em.persist(team);





        Team teamB = new Team();
        teamB.setName("TeamB");

        em.persist(teamB);

        // 단건조회
        Member findMember = em.find(Member.class, member.getId());
        System.out.println("findMember = " + findMember);


    }
}

