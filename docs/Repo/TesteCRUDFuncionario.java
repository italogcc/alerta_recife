package teste;

import Usuario.Funcionario;
import Usuario.TipoFuncionario;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("JPQLValidation")
public class TesteCRUDFuncionario {

    private static EntityManagerFactory emf;
    private static Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;

    public TesteCRUDFuncionario() {
    }

    @BeforeClass
    public static void setUpClass() {
        //logger.setLevel(Level.INFO);
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("consultorio");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        beginTransaction();
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            et.rollback();
            fail(ex.getMessage());
        }
    }

    @Test
    public void teste01_persistencia() {
        logger.info("Teste 01: Persistência de dados");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Freddy Mercury");
        funcionario.setLogin("freddym");
        funcionario.setSenha("freddym123");
        funcionario.setEmail("freddym@yahoo.com");
        funcionario.setSexo("M");
        funcionario.setTipofunc(TipoFuncionario.MEDICO.toString());
        
        em.persist(funcionario);
        em.flush();
        assertNotNull(funcionario.getId());
        logger.log(Level.INFO, "Cadastro realizado com sucesso.", funcionario);
    }

    @Test
    public void teste02_atualizacao() {
        logger.info("Teste 02: Atualização de dados");
        Funcionario funcionario;
        String consulta = "SELECT f FROM Funcionario f WHERE f.email=?1";
        Query query = em.createQuery(consulta);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "iancurtis@yahoo.com.uk");
        funcionario = (Funcionario) query.getSingleResult();
        funcionario.setEmail("icurtis@yahoo.com.uk");
        em.flush();
        query.setParameter(1, "icurtis@yahoo.com.uk");
        funcionario = (Funcionario) query.getSingleResult();
        assertEquals("icurtis@yahoo.com.uk", funcionario.getEmail());

    }

    @Test
    public void teste03_atualizacaoMerge() {
        logger.info("Teste 03: Atualização de dados com merge");
        Funcionario funcionario;
        String consulta = "SELECT f FROM Funcionario f WHERE f.login=?1";
        Query query = em.createQuery(consulta);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "iancurtis");
        funcionario = (Funcionario) query.getSingleResult();
        assertNotNull(funcionario);
        em.clear();
        funcionario.setLogin("iancurtisjoydiv");
        em.merge(funcionario);
        em.flush();
        query.setParameter(1, "iancurtisjoydiv");
        funcionario = (Funcionario) query.getSingleResult();
        assertEquals("iancurtisjoydiv", funcionario.getLogin());
    }
    
    @Test
    public void teste04_apaga() {
        logger.info("Teste 04: Apaga dado");
        Funcionario funcionario;
        String consulta = "SELECT f FROM Funcionario f WHERE f.id=?7";
        Query query = em.createQuery(consulta);
        long id = 7;
        query.setParameter(7, id);
        funcionario = (Funcionario) query.getSingleResult();
        em.remove(funcionario);
        em.flush();
        Map map = new HashMap();
        map.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Funcionario deletado = em.find(Funcionario.class, id, map);
        assertNull(deletado);

    }

}
