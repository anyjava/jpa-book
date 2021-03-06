package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
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

        em.flush();
        em.clear();

        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m join fetch m.team where m.age > 10", Member.class);
        List<Member> resultList = query.getResultList();

        for (Member member1 : resultList) {
            System.out.println("member1 = " + member1);
            System.out.println("findTeam = " + member1.getTeam().getClass());

            String teamName = member1.getTeam().getName();
            System.out.println("teamName = " + teamName);
        }

    }
}

