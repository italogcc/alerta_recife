package ifpe.recife.tads.alerta_recife;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_TELEFONE")
@Access(AccessType.FIELD)
public class Telefone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToMany(mappedBy = "telefones")
    private List<Usuario> usuarios;

    @NotNull
    @Column(name = "NUMERO")
    private String numero;

    @NotNull
    @Column(name = "DDD")
    private String ddd;

    public Telefone() {

    }

    public Telefone(Long id, List<Usuario> usuarios, String numero, String ddd) {
        this.id = id;
        this.usuarios = usuarios;
        this.numero = numero;
        this.ddd = ddd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

}
