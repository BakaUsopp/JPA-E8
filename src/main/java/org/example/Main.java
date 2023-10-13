package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Book;
import org.example.entities.ElectronicsDevice;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String puName = "pu-name";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create"); // create ,update, none

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Book book = new Book();
            book.setId(1L);
            book.setAuthor("Oda");
            book.setName("One Piece");


            ElectronicsDevice ed = new ElectronicsDevice();
            ed.setId(2L);
            ed.setVoltage(220);
            ed.setName("T.V.");

            em.persist(book);
            em.persist(ed);

            var sql ="SELECT p FROM Product p";

            //            var sql ="SELECT p FROM Book p";



            em.createQuery(sql , Product.class).getResultList().forEach(System.out::println);




            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}