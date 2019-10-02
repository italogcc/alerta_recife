package ifpe.recife.tads.test;

import ifpe.recife.tads.alerta_recife.Administrador;
import ifpe.recife.tads.alerta_recife.Cargo;
import ifpe.recife.tads.alerta_recife.Contato;
import ifpe.recife.tads.alerta_recife.Usuario;
import ifpe.recife.tads.test.DataSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

public class ContatoCRUDTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public ContatoCRUDTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("alerta_recife");
        DataSet.inserirDados();

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
    public void t01_criaContato() {

//        logger.info("Executando: criaContato");
//        Contato contato = new Contato();
//        contato.setNumero("8133554320");
//        contato.setDescricao("Defesa Civil Regional Norte");
//        em.persist(contato);
//        em.flush();
//        assertNotNull(contato.getId());

    }

    // Ainda como teste t04 porque os demais testes de CRUD serão inseridos
    @Test
    public void t04_atualizaDado() {
//        logger.info("Executando: atualizaDado");
//        Contato cont;
//        TypedQuery<Contato> query = em.createNamedQuery("Contato.RecuperarPorDescricao", Contato.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter("descricao", "Defesa Civil Regional Oeste");
//        cont = (Contato) query.getSingleResult();
//        cont.setNumero("8133556977");
//        em.flush();
//        cont = (Contato) query.getSingleResult();
//        assertEquals("8133556977", cont.getNumero());

    }
    
    // <TB_CONTATO numero = "8133556856" descricao = "Defesa Civil Regional Nordeste"/>
    @Test
    public void t02_atualizacao() {
//        logger.info("Executando: Atualização de dados");
//        Contato contato;
//        String consulta = "SELECT c FROM Contato c WHERE c.descricao LIKE :descricao";
//        Query query = em.createQuery(consulta);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter(1, "iancurtis@yahoo.com.uk");
//        contato = (Contato) query.getSingleResult();
//        contato.setEmail("icurtis@yahoo.com.uk");
//        em.flush();
//        query.setParameter(1, "icurtis@yahoo.com.uk");
//        contato = (Contato) query.getSingleResult();
//        assertEquals("icurtis@yahoo.com.uk", contato.getEmail());

    }

    //<TB_CONTATO numero = "8133772100" descricao = "Defesa Civil Regional 1"/>
    @Test
    public void teste03_atualizacaoMerge() {
//        logger.info("Executando: Atualização de dados com merge");
//        Contato contato;
//        String consulta = "SELECT f FROM Funcionario f WHERE f.login=?1";
//        Query query = em.createQuery(consulta);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter(1, "iancurtis");
//        contato = (Contato) query.getSingleResult();
//        assertNotNull(contato);
//        em.clear();
//        contato.setLogin("iancurtisjoydiv");
//        em.merge(contato);
//        em.flush();
//        query.setParameter(1, "iancurtisjoydiv");
//        contato = (Contato) query.getSingleResult();
//        assertEquals("iancurtisjoydiv", contato.getLogin());
    }
    
    // <TB_CONTATO numero = "8133772121" descricao = "Defesa Civil Regional 2"/>
    @Test
    public void teste04_apaga() {
//        logger.info("Executando: Remove dados");
//        Contato contato;
//        String consulta = "SELECT c FROM Contato c WHERE c.numero = :numero";
//        Query query = em.createQuery(consulta);
//        query.setParameter("numero", "8133772121");
//        contato = (Contato) query.getSingleResult();
//        em.remove(contato);
//        em.flush();
//        Map map = new HashMap();
//        map.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        Contato deletado = em.find(Contato.class, map);
//        assertNull(deletado);
    }
    
    
}
