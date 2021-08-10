package Logica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    long id_usuario;
    
    @Basic
    String usuario;
    String password;
    
    @OneToOne
    Empleado usuarioEmpleado;
    @OneToMany
    List<Reserva> reservasUsuario = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(long id_usuario, String usuario, String password, Empleado usuarioEmpleado) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.password = password;
        this.usuarioEmpleado = usuarioEmpleado;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Empleado getUsuarioEmpleado() {
        return usuarioEmpleado;
    }

    public void setUsuarioEmpleado(Empleado usuarioEmpleado) {
        this.usuarioEmpleado = usuarioEmpleado;
    }

    public List<Reserva> getReservasUsuario() {
        return reservasUsuario;
    }

    public void setReservasUsuario(List<Reserva> reservasUsuario) {
        this.reservasUsuario = reservasUsuario;
    }

    
}
