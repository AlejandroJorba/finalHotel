package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Reserva implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    long id_reserva;
    
    
    @Basic
    int cant_personas;
    double cant_noches;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date check_in;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date check_out;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date diaCarga;
    double precio_final;
    @OneToOne
    private Huesped huesped;
    @OneToOne
    private Habitacion habitacion;
    @OneToOne
    private Usuario usuario;

    public Reserva() {
    }

    public Reserva(long id_reserva, int cant_personas, double cant_noches, Date check_in, Date check_out, Date diaCarga, double precio_final, Huesped huesped, Habitacion habitacion, Usuario usuario) {
        this.id_reserva = id_reserva;
        this.cant_personas = cant_personas;
        this.cant_noches = cant_noches;
        this.check_in = check_in;
        this.check_out = check_out;
        this.diaCarga = diaCarga;
        this.precio_final = precio_final;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.usuario = usuario;
    }

    public Date getDiaCarga() {
        return diaCarga;
    }

    public void setDiaCarga(Date diaCarga) {
        this.diaCarga = diaCarga;
    }

    

    public long getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(int cant_personas) {
        this.cant_personas = cant_personas;
    }

    public double getCant_noches() {
        return cant_noches;
    }

    public void setCant_noches(double cant_noches) {
        this.cant_noches = cant_noches;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    public double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(double precio_final) {
        this.precio_final = precio_final;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
