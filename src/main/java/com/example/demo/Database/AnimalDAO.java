package com.example.demo.Database;

import java.util.List;
import java.util.Properties;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class AnimalDAO {
    
    @SuppressWarnings("unchecked")
    public List<Animal> getAllAnimals(Session session) {
        // Write your code here

        if(session != null){
            
            Query query = session.createQuery("SELECT a FROM Animal a");
            return query.list();
        }
        
        return null;
    }
        
    @Entity(name = "Animal")
    public static class Animal {
        @Id
        public Integer id;
        @Column
        public String name;
    }
    
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.url", "jdbc:h2:mem:db1");
        prop.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        prop.setProperty("hibernate.hbm2ddl.auto", "create");

        SessionFactory sessionFactory = new Configuration().addProperties(prop)
            .addAnnotatedClass(Animal.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Animal animal = new Animal();
        animal.id = 0;
        animal.name ="Mr. Rabbit";
        
        session.save(animal);
        session.flush();
        
        AnimalDAO animalDAO = new AnimalDAO();
        List<Animal> animals = animalDAO.getAllAnimals(session);
        for(Animal an : animals) {
            System.out.println(an.id + " - " + an.name);
        }
    }

}
