package com.hkb.test;

import com.hkb.entity.Customer;
import com.hkb.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     * Jpa的操作步骤：
     * 1.加载配置文件创建工厂（实体管理类工厂）对象
     * 2.通过实体管理类工厂获取实体管理器
     * 3.获取事务对象，开启事务
     * 4.完成增删改查
     * 5.提交事务，回滚事务
     * 6.释放资源
     */

//    @Test
    public void testSave(int i) {
        //1.加载配置文件创建工厂（实体管理类工厂）对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理类工厂获取实体管理器
        EntityManager em = factory.createEntityManager();

        //3.获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //4.完成增删改查
        Customer customer = new Customer();
        customer.setCustName("huangkb0" + i);
        customer.setCustPhone("15527619163");

        //保存
        em.persist(customer);

        //5.提交事务
        tx.commit();

        //6.释放资源
        em.close();
        factory.close();
    }

    @Test
    public void createData() {
        int i = 0;
        while (i < 10) {
            i++;
            testSave(i);
        }
    }


    @Test
    public void testFind() {
        //2.通过实体管理类工厂获取实体管理器
        EntityManager em = JpaUtils.getEntityManager();

        //3.获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer Customer = em.getReference(Customer.class, 1L);
        Customer Customer2 = em.find(Customer.class, 1L);

        System.out.println(Customer);
        System.out.println(Customer2);
        //5.提交事务
        tx.commit();

        //6.释放资源
        em.close();
    }

    @Test
    public void testRemove() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*******************************************/

        Customer customer = em.find(Customer.class, 1L);
        em.remove(customer);

        /********************************************/
        tx.commit();
        em.close();
    }

    @Test
    public void testUpdate() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*******************************************/

        Customer customer = em.find(Customer.class, 2L);
        customer.setCustPhone("110");

        em.merge(customer);

        /********************************************/
        tx.commit();
        em.close();
    }


}
