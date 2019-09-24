package ifpe.recife.tads.alerta_recife;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_SOLICITACAO")
@Access(AccessType.FIELD)
public class Solicitacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "DESCRICAO", length = 220)
    private String descricao;

    @NotNull
    @Column(name = "TIPO_SOLICITACAO")
    private TipoDeSolicitacao tipoDeSolicitacao;

    @NotNull
    @OneToOne(mappedBy = "endereco")
    private PontoDeRisco pontoDeRisco;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDeSolicitacao getTipoDeSolicitacao() {
        return tipoDeSolicitacao;
    }

    public void setTipoDeSolicitacao(TipoDeSolicitacao tipoDeSolicitacao) {
        this.tipoDeSolicitacao = tipoDeSolicitacao;
    }

    public PontoDeRisco getPontoDeRisco() {
        return pontoDeRisco;
    }

    public void setPontoDeRisco(PontoDeRisco pontoDeRisco) {
        this.pontoDeRisco = pontoDeRisco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
