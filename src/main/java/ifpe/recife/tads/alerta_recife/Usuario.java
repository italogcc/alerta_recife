package ifpe.recife.tads.alerta_recife;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_USUARIO")
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @NotNull
    @Column(name="EMAIL", unique = true, length = 100)
    private String email;

    @NotNull
    @Column(name="SENHA",length = 100)
    private String senha;

    @NotNull
    @Column(name="PRIMEIRO_NOME",length = 30)
    private String primeiroNome;
    
    @NotNull
    @Column(name="ULTIMO_NOME",length = 30)
    private String ultimoNome;

    @NotNull
    @Column(name="HABILITADO")
    private boolean habilitado = false;

    public Usuario() {
    
    }
    
    public Usuario(Long id, String email, String senha, String primeiroNome, String ultimoNome, boolean habilitado) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.habilitado = habilitado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
}
