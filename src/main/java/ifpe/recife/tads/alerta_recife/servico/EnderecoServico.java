package ifpe.recife.tads.alerta_recife.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import org.hibernate.validator.constraints.NotBlank;
import ifpe.recife.tads.alerta_recife.*;

@Stateless(name = "ejb/EnderecoServico") // O professor comentou que é padrão
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class EnderecoServico extends Servico<Endereco> {

    @PostConstruct
    public void init() {
        super.setClasse(Endereco.class);
    }
    
    public Endereco criar() {
        return new Endereco();
    }
    
    public void salvar(Endereco endereco){
        entityManager.persist(endereco);
    }
    
    public void atualizar(Endereco endereco){
        entityManager.merge(endereco);
        entityManager.flush();
    }
        
    public boolean existeRua(Endereco entidade) {
        TypedQuery<Endereco> query
                = entityManager.createNamedQuery("Endereco.RecuperarPorRua", classe);
        query.setParameter(1, entidade.getRua());
        return !query.getResultList().isEmpty();
    }

    public boolean existeBairro(Endereco entidade) {
        TypedQuery<Endereco> query
                = entityManager.createNamedQuery("Endereco.RecuperarPorBairro", classe);
        query.setParameter(1, entidade.getBairro());
        return !query.getResultList().isEmpty();
    }
    
    public List<Endereco> recuperarEnderecos() {
        TypedQuery<Endereco> query
                = entityManager.createNamedQuery("Endereco.RecuperarEnderecos", classe);
        return query.getResultList();
    }    
    
    @TransactionAttribute(SUPPORTS) 
    public List<Endereco> consultarPorRua(@NotNull String rua) {
        return super.consultarEntidades(new Object[] {rua}, "Endereco.RecuperarPorRua");         
    }
    
    @TransactionAttribute(SUPPORTS) 
    public List<Endereco> consultarPorBairro(@NotNull String bairro) {
        return super.consultarEntidades(new Object[] {bairro}, "Endereco.RecuperarPorBairro");
    }
}
// Nem precisa da maioria das notações. O professor colocou para ilustrar.

