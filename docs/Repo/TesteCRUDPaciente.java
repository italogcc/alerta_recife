package teste;

import Usuario.Paciente;
import Usuario.TipoPlanoSaude;
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
public class TesteCRUDPaciente {

    private static EntityManagerFactory emf;
    private static Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;

    public TesteCRUDPaciente() {
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

        Paciente paciente = new Paciente();

        paciente.setNome("Katy Perry");
        paciente.setLogin("katyperry");
        paciente.setEmail("katyperry@aol.com");
        paciente.setSenha("katyperry123");
        paciente.setSexo("F");
        paciente.setPlano(TipoPlanoSaude.HAPVIDA.toString());

        em.persist(paciente);
        em.flush();
        assertNotNull(paciente.getId());
        logger.log(Level.INFO, "Cadastro realizado com sucesso.", paciente);
    }

    @Test
    public void teste02_atualizacao() {
        logger.info("Teste 02: Atualização de dados");
        Paciente paciente;
        String consulta = "SELECT p FROM Paciente p WHERE p.senha=?1 ";
        Query query = em.createQuery(consulta);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "gwenstef");
        paciente = (Paciente) query.getSingleResult();
        paciente.setSenha("gwenstef12345");
        em.flush();
        query.setParameter(1, "gwenstef12345");
        paciente = (Paciente) query.getSingleResult();
        
        assertEquals("gwenstef12345", paciente.getSenha());

    }

    @Test
    public void teste03_atualizacaoMerge() {
        logger.info("Teste 03: Atualização de dados com merge");
        Paciente paciente;
        String consulta = "SELECT p FROM Paciente p WHERE p.login=?1";
        Query query = em.createQuery(consulta);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "mjackson");
        paciente = (Paciente) query.getSingleResult();
        assertNotNull(paciente);
        em.clear();
        paciente.setLogin("michaeljackson");
        em.merge(paciente);
        em.flush();
        query.setParameter(1, "michaeljackson");
        paciente = (Paciente) query.getSingleResult();
        assertEquals("michaeljackson", paciente.getLogin());
    }

    @Test
    public void teste04_apaga() {
        logger.info("Teste 04: Apaga dado");
        Paciente paciente;
        String consulta = "SELECT p FROM Paciente p WHERE p.id=?1";
        Query query = em.createQuery(consulta);
        long id = 1;
        query.setParameter(1, id);
        paciente = (Paciente) query.getSingleResult();
        em.remove(paciente);
        em.flush();
        Map map = new HashMap();
        map.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Paciente deletado = em.find(Paciente.class, id, map);
        assertNull(deletado);

    }

}
