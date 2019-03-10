package dao;

import dominio.Voluntario;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class VoluntarioDAO {
    
    private static VoluntarioDAO instance;
    protected EntityManager em;

    public static VoluntarioDAO getInstance(){
        if(instance == null){
            instance = new VoluntarioDAO();
        }
        return instance;
    }
    
    public VoluntarioDAO(){
        em = getEntityManager();
    }
    
    private EntityManager getEntityManager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("minhaPU");
        if (em == null) {
            em = factory.createEntityManager();
        }
        return em;
    }
    
    public Voluntario getById(final int id) {
        return em.find(Voluntario.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<Voluntario> findAll() {
        return (ArrayList<Voluntario>) em.createQuery("SELECT FROM " + Voluntario.class.getName()).getResultList();
    }
    
    public void salvar(Voluntario voluntario){
        try{
            em.getTransaction().begin();
            em.persist(voluntario);
            em.getTransaction().commit();
            em.close();
        }catch(Exception e){
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
    }
    
    public void editar(Voluntario voluntario){
        try{
            em.getTransaction().begin();
            em.merge(voluntario);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
    }
    
    public void remove(Voluntario voluntario) {
        try {
            em.getTransaction().begin();
            voluntario = em.find(Voluntario.class, voluntario.getId());
            em.remove(voluntario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    
    public void removeById(final int id){
        try {
            Voluntario voluntario = getById(id);
            remove(voluntario);
        } catch (Exception ex) {
        }
    }
    
}
