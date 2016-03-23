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


        Query query = em.createQuery("SELECT m.username, m.age FROM Member m where m.age > 10");
        List resultList = query.getResultList();

        for (Object o : resultList) {
            Object[] result = (Object[]) o;
            System.out.println("result.username = " + result[0]);
            System.out.println("result.age = " + result[1]);
        }

    }
}

