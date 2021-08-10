/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Habitacion;
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
public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public HabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("hotelPU");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) {
        if (habitacion.getReservasHabitacion() == null) {
            habitacion.setReservasHabitacion(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedReservasHabitacion = new ArrayList<Reserva>();
            for (Reserva reservasHabitacionReservaToAttach : habitacion.getReservasHabitacion()) {
                reservasHabitacionReservaToAttach = em.getReference(reservasHabitacionReservaToAttach.getClass(), reservasHabitacionReservaToAttach.getId_reserva());
                attachedReservasHabitacion.add(reservasHabitacionReservaToAttach);
            }
            habitacion.setReservasHabitacion(attachedReservasHabitacion);
            em.persist(habitacion);
            for (Reserva reservasHabitacionReserva : habitacion.getReservasHabitacion()) {
                Habitacion oldHabitacionOfReservasHabitacionReserva = reservasHabitacionReserva.getHabitacion();
                reservasHabitacionReserva.setHabitacion(habitacion);
                reservasHabitacionReserva = em.merge(reservasHabitacionReserva);
                if (oldHabitacionOfReservasHabitacionReserva != null) {
                    oldHabitacionOfReservasHabitacionReserva.getReservasHabitacion().remove(reservasHabitacionReserva);
                    oldHabitacionOfReservasHabitacionReserva = em.merge(oldHabitacionOfReservasHabitacionReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getId_habitacion());
            List<Reserva> reservasHabitacionOld = persistentHabitacion.getReservasHabitacion();
            List<Reserva> reservasHabitacionNew = habitacion.getReservasHabitacion();
            List<Reserva> attachedReservasHabitacionNew = new ArrayList<Reserva>();
            for (Reserva reservasHabitacionNewReservaToAttach : reservasHabitacionNew) {
                reservasHabitacionNewReservaToAttach = em.getReference(reservasHabitacionNewReservaToAttach.getClass(), reservasHabitacionNewReservaToAttach.getId_reserva());
                attachedReservasHabitacionNew.add(reservasHabitacionNewReservaToAttach);
            }
            reservasHabitacionNew = attachedReservasHabitacionNew;
            habitacion.setReservasHabitacion(reservasHabitacionNew);
            habitacion = em.merge(habitacion);
            for (Reserva reservasHabitacionOldReserva : reservasHabitacionOld) {
                if (!reservasHabitacionNew.contains(reservasHabitacionOldReserva)) {
                    reservasHabitacionOldReserva.setHabitacion(null);
                    reservasHabitacionOldReserva = em.merge(reservasHabitacionOldReserva);
                }
            }
            for (Reserva reservasHabitacionNewReserva : reservasHabitacionNew) {
                if (!reservasHabitacionOld.contains(reservasHabitacionNewReserva)) {
                    Habitacion oldHabitacionOfReservasHabitacionNewReserva = reservasHabitacionNewReserva.getHabitacion();
                    reservasHabitacionNewReserva.setHabitacion(habitacion);
                    reservasHabitacionNewReserva = em.merge(reservasHabitacionNewReserva);
                    if (oldHabitacionOfReservasHabitacionNewReserva != null && !oldHabitacionOfReservasHabitacionNewReserva.equals(habitacion)) {
                        oldHabitacionOfReservasHabitacionNewReserva.getReservasHabitacion().remove(reservasHabitacionNewReserva);
                        oldHabitacionOfReservasHabitacionNewReserva = em.merge(oldHabitacionOfReservasHabitacionNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = habitacion.getId_habitacion();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
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
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getId_habitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> reservasHabitacion = habitacion.getReservasHabitacion();
            for (Reserva reservasHabitacionReserva : reservasHabitacion) {
                reservasHabitacionReserva.setHabitacion(null);
                reservasHabitacionReserva = em.merge(reservasHabitacionReserva);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
