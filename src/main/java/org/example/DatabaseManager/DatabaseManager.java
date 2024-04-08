package org.example.DatabaseManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Classes.Room;

import java.util.List;

public class DatabaseManager {
    private static DatabaseManager instance;
    public static DatabaseManager getInstance(){
        if(instance == null){
            instance=new DatabaseManager();
        }
        return instance;
    }
    public DatabaseManager(){
    }
    public List<Room> read() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("room");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> root = criteriaQuery.from(Room.class);
        criteriaQuery.select(root);
        TypedQuery<Room> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Room> list = typedQuery.getResultList();
        return list;
    }

    public void write(Room room) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("room");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(room);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }
}
