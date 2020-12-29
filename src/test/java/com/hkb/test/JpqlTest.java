package com.hkb.test;

import com.hkb.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * Jpql查询实体类
 */
public class JpqlTest {

    @Test
    public void testFindAll() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*******************************************/

        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        List list = query.getResultList();
        list.forEach(System.out::println);


        /********************************************/
        tx.commit();
        em.close();
    }

    @Test
    public void testFindAllDesc() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*******************************************/

        String jpql = "from Customer order by custId desc";
        Query query = em.createQuery(jpql);
        List list = query.getResultList();
        list.forEach(System.out::println);


        /********************************************/
        tx.commit();
        em.close();
    }

    @Test
    public void testFindCount() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*******************************************/

        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);

        /********************************************/
        tx.commit();
        em.close();
    }

    @Test
    public void testPage() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*******************************************/

        String jpql = "from Customer order by custId";
        Query query = em.createQuery(jpql);
        query.setFirstResult(3);
        query.setMaxResults(3);

        List resultList = query.getResultList();
        resultList.forEach(System.out::println);

        /********************************************/
        tx.commit();
        em.close();
    }

    @Test
    public void testCondition() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*******************************************/

        String jpql = "from Customer where custName like ?";
        Query query = em.createQuery(jpql);
        query.setParameter(1,"huangkb01%");

        List resultList = query.getResultList();
        resultList.forEach(System.out::println);

        /********************************************/
        tx.commit();
        em.close();
    }
}
