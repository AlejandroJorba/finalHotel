/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Reserva;
import Logica.Usuario;
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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("hotelPU");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getReservasUsuario() == null) {
            usuario.setReservasUsuario(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedReservasUsuario = new ArrayList<Reserva>();
            for (Reserva reservasUsuarioReservaToAttach : usuario.getReservasUsuario()) {
                reservasUsuarioReservaToAttach = em.getReference(reservasUsuarioReservaToAttach.getClass(), reservasUsuarioReservaToAttach.getId_reserva());
                attachedReservasUsuario.add(reservasUsuarioReservaToAttach);
            }
            usuario.setReservasUsuario(attachedReservasUsuario);
            em.persist(usuario);
            for (Reserva reservasUsuarioReserva : usuario.getReservasUsuario()) {
                Usuario oldUsuarioOfReservasUsuarioReserva = reservasUsuarioReserva.getUsuario();
                reservasUsuarioReserva.setUsuario(usuario);
                reservasUsuarioReserva = em.merge(reservasUsuarioReserva);
                if (oldUsuarioOfReservasUsuarioReserva != null) {
                    oldUsuarioOfReservasUsuarioReserva.getReservasUsuario().remove(reservasUsuarioReserva);
                    oldUsuarioOfReservasUsuarioReserva = em.merge(oldUsuarioOfReservasUsuarioReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId_usuario());
            List<Reserva> reservasUsuarioOld = persistentUsuario.getReservasUsuario();
            List<Reserva> reservasUsuarioNew = usuario.getReservasUsuario();
            List<Reserva> attachedReservasUsuarioNew = new ArrayList<Reserva>();
            for (Reserva reservasUsuarioNewReservaToAttach : reservasUsuarioNew) {
                reservasUsuarioNewReservaToAttach = em.getReference(reservasUsuarioNewReservaToAttach.getClass(), reservasUsuarioNewReservaToAttach.getId_reserva());
                attachedReservasUsuarioNew.add(reservasUsuarioNewReservaToAttach);
            }
            reservasUsuarioNew = attachedReservasUsuarioNew;
            usuario.setReservasUsuario(reservasUsuarioNew);
            usuario = em.merge(usuario);
            for (Reserva reservasUsuarioOldReserva : reservasUsuarioOld) {
                if (!reservasUsuarioNew.contains(reservasUsuarioOldReserva)) {
                    reservasUsuarioOldReserva.setUsuario(null);
                    reservasUsuarioOldReserva = em.merge(reservasUsuarioOldReserva);
                }
            }
            for (Reserva reservasUsuarioNewReserva : reservasUsuarioNew) {
                if (!reservasUsuarioOld.contains(reservasUsuarioNewReserva)) {
                    Usuario oldUsuarioOfReservasUsuarioNewReserva = reservasUsuarioNewReserva.getUsuario();
                    reservasUsuarioNewReserva.setUsuario(usuario);
                    reservasUsuarioNewReserva = em.merge(reservasUsuarioNewReserva);
                    if (oldUsuarioOfReservasUsuarioNewReserva != null && !oldUsuarioOfReservasUsuarioNewReserva.equals(usuario)) {
                        oldUsuarioOfReservasUsuarioNewReserva.getReservasUsuario().remove(reservasUsuarioNewReserva);
                        oldUsuarioOfReservasUsuarioNewReserva = em.merge(oldUsuarioOfReservasUsuarioNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = usuario.getId_usuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId_usuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> reservasUsuario = usuario.getReservasUsuario();
            for (Reserva reservasUsuarioReserva : reservasUsuario) {
                reservasUsuarioReserva.setUsuario(null);
                reservasUsuarioReserva = em.merge(reservasUsuarioReserva);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
