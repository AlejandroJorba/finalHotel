/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Huesped;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alejandro
 */
public class HuespedJpaController implements Serializable {

    public HuespedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public HuespedJpaController() {
        emf = Persistence.createEntityManagerFactory("hotelPU");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Huesped huesped) {
        if (huesped.getReservasHuesped() == null) {
            huesped.setReservasHuesped(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedReservasHuesped = new ArrayList<Reserva>();
            for (Reserva reservasHuespedReservaToAttach : huesped.getReservasHuesped()) {
                reservasHuespedReservaToAttach = em.getReference(reservasHuespedReservaToAttach.getClass(), reservasHuespedReservaToAttach.getId_reserva());
                attachedReservasHuesped.add(reservasHuespedReservaToAttach);
            }
            huesped.setReservasHuesped(attachedReservasHuesped);
            em.persist(huesped);
            for (Reserva reservasHuespedReserva : huesped.getReservasHuesped()) {
                Huesped oldHuespedOfReservasHuespedReserva = reservasHuespedReserva.getHuesped();
                reservasHuespedReserva.setHuesped(huesped);
                reservasHuespedReserva = em.merge(reservasHuespedReserva);
                if (oldHuespedOfReservasHuespedReserva != null) {
                    oldHuespedOfReservasHuespedReserva.getReservasHuesped().remove(reservasHuespedReserva);
                    oldHuespedOfReservasHuespedReserva = em.merge(oldHuespedOfReservasHuespedReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Huesped huesped) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped persistentHuesped = em.find(Huesped.class, huesped.getId_huesped());
            List<Reserva> reservasHuespedOld = persistentHuesped.getReservasHuesped();
            List<Reserva> reservasHuespedNew = huesped.getReservasHuesped();
            List<Reserva> attachedReservasHuespedNew = new ArrayList<Reserva>();
            for (Reserva reservasHuespedNewReservaToAttach : reservasHuespedNew) {
                reservasHuespedNewReservaToAttach = em.getReference(reservasHuespedNewReservaToAttach.getClass(), reservasHuespedNewReservaToAttach.getId_reserva());
                attachedReservasHuespedNew.add(reservasHuespedNewReservaToAttach);
            }
            reservasHuespedNew = attachedReservasHuespedNew;
            huesped.setReservasHuesped(reservasHuespedNew);
            huesped = em.merge(huesped);
            for (Reserva reservasHuespedOldReserva : reservasHuespedOld) {
                if (!reservasHuespedNew.contains(reservasHuespedOldReserva)) {
                    reservasHuespedOldReserva.setHuesped(null);
                    reservasHuespedOldReserva = em.merge(reservasHuespedOldReserva);
                }
            }
            for (Reserva reservasHuespedNewReserva : reservasHuespedNew) {
                if (!reservasHuespedOld.contains(reservasHuespedNewReserva)) {
                    Huesped oldHuespedOfReservasHuespedNewReserva = reservasHuespedNewReserva.getHuesped();
                    reservasHuespedNewReserva.setHuesped(huesped);
                    reservasHuespedNewReserva = em.merge(reservasHuespedNewReserva);
                    if (oldHuespedOfReservasHuespedNewReserva != null && !oldHuespedOfReservasHuespedNewReserva.equals(huesped)) {
                        oldHuespedOfReservasHuespedNewReserva.getReservasHuesped().remove(reservasHuespedNewReserva);
                        oldHuespedOfReservasHuespedNewReserva = em.merge(oldHuespedOfReservasHuespedNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = huesped.getId_huesped();
                if (findHuesped(id) == null) {
                    throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped huesped;
            try {
                huesped = em.getReference(Huesped.class, id);
                huesped.getId_huesped();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> reservasHuesped = huesped.getReservasHuesped();
            for (Reserva reservasHuespedReserva : reservasHuesped) {
                reservasHuespedReserva.setHuesped(null);
                reservasHuespedReserva = em.merge(reservasHuespedReserva);
            }
            em.remove(huesped);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Huesped> findHuespedEntities() {
        return findHuespedEntities(true, -1, -1);
    }

    public List<Huesped> findHuespedEntities(int maxResults, int firstResult) {
        return findHuespedEntities(false, maxResults, firstResult);
    }

    private List<Huesped> findHuespedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Huesped.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Huesped findHuesped(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Huesped.class, id);
        } finally {
            em.close();
        }
    }

    public int getHuespedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Huesped> rt = cq.from(Huesped.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
