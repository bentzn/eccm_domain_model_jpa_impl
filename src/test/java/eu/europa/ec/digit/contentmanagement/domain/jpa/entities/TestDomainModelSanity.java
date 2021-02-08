package eu.europa.ec.digit.contentmanagement.domain.jpa.entities;

import static org.junit.Assert.*;

import javax.persistence.*;

import org.junit.*;

import eu.europa.ec.digit.contentmanagement.domain.api.entities.*;
import eu.europa.ec.digit.contentmanagement.domain.api.util.EccmUtils;
import eu.europa.ec.digit.contentmanagement.domain.jpa.entities.impl.*;

/**
 * 
 * @author bentsth
 */
public class TestDomainModelSanity {

    private static EntityManagerFactory factory;
    private static EntityManager em = null;
    private Repository_i[] repositories;


    @BeforeClass
    public static void openEntityManager() {
        EccmUtils.deleteAllFiles(".\\resources\\unittest");
        factory = Persistence.createEntityManagerFactory("unittest");
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }


    @AfterClass
    public static void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.getTransaction().commit();
            em.close();
            factory.close();
        }
    }


    @Test
    public void testRepository() {
        createRepositories();
    }


    public Repository_i[] createRepositories() {
        if (repositories == null) {
            synchronized (this) {
                if (repositories == null) {
                    repositories = new Repository_i[2];
                    repositories[0] = new RepositoryJpaImpl("name_0", "description_0", 0);
                    em.persist(repositories[0]);
                    assertTrue(repositories[0].getId() > 0);

                    repositories[1] = new RepositoryJpaImpl("name_1", "description_1", 1);
                    em.persist(repositories[1]);
                    assertTrue(repositories[1].getId() > 0);
                }
            }
        }

        return repositories;
    }


    @Test
    public void testTypeDefinitions() {
        createTypeDefinitions();
    }


    private TypeDefinition_i[] createTypeDefinitions() {
        TypeDefinitionJpaImpl[] typeDefs = new TypeDefinitionJpaImpl[5];
        Repository_i[] repos = createRepositories();
        for (int i = 0; i < 5; i++) {
            String name = BaseType.values()[i].name();
            typeDefs[i] = new TypeDefinitionJpaImpl("name_" + name, "displayName_" + name, "description_" + name);
            typeDefs[i].setBaseType(BaseType.values()[i]);
            typeDefs[i].setRepository(repos[i % 2]);
            if (i > 0)
                typeDefs[i].setParentTypeDefinition(typeDefs[i - 1]);
            em.persist(typeDefs[i]);

            assertTrue(typeDefs[i].getId() > 0);
            assertNotNull(typeDefs[i].getRepository());
            assertEquals(repos[i % 2].getId(), typeDefs[i].getRepository().getId());
            if (i > 0) {
                assertNotNull(typeDefs[i].getParentTypeDefinition());
                assertEquals(typeDefs[i - 1].getId(), typeDefs[i].getParentTypeDefinition().getId());
            }
        }

        return typeDefs;
    }


    @Test
    public void testEqualsAndHashcode() {
        Repository_i[] repos = createRepositories();
        System.out.println(repos[0]);
        System.out.println(repos[1]);
        assertNotEquals(repos[0], repos[1]);
        assertNotEquals(repos[0].hashCode(), repos[1].hashCode());
    }

    
    @Test
    public void testArtifacts() {
        Artifact_i repoArtRoot = new ArtifactJpaImpl();
        em.persist(repoArtRoot);
        
        Artifact_i repoArtSub = null;
        Artifact_i repoArtSubSub = null;
        Artifact_i repoArtDoc = null;
        for (int i = 0; i < 3; i++) {
            repoArtSub = new ArtifactJpaImpl();
            repoArtSub.setBaseType(BaseType.FOLDER);
            repoArtSub.addParent(repoArtRoot);
            em.persist(repoArtSub);
            
            for (int j = 0; j < 3; j++) {
                repoArtSubSub = new ArtifactJpaImpl();
                repoArtSubSub.setBaseType(BaseType.FOLDER);
                repoArtSubSub.addParent(repoArtSub);
                em.persist(repoArtSubSub);

                for (int m = 0; m < 3; m++) {
                    repoArtDoc = new ArtifactJpaImpl();
                    repoArtDoc.setBaseType(BaseType.DOCUMENT);
                    repoArtDoc.addParent(repoArtSubSub);
                    em.persist(repoArtDoc);
                }

                assertEquals(repoArtSubSub, repoArtDoc.getParents().get(0));
            }

            assertEquals(repoArtSub, repoArtSubSub.getParents().get(0));
        }

        assertEquals(repoArtRoot, repoArtSub.getParents().get(0));
        assertNull(repoArtRoot.getParents());

        Artifact_i repoArtDoc1 = em.find(ArtifactJpaImpl.class, repoArtDoc.getId());
        assertNotNull(repoArtDoc1);
        assertNotNull(repoArtDoc1.getParents()); // subsub
        assertEquals(repoArtSubSub, repoArtDoc1.getParents().get(0)); // subsub
        
        assertNotNull(repoArtDoc1.getParents().get(0).getParents().get(0)); // sub
        assertEquals(repoArtSub, repoArtDoc1.getParents().get(0).getParents().get(0)); // sub
        
        assertNotNull(repoArtDoc1.getParents().get(0).getParents().get(0).getParents().get(0)); // root
        assertEquals(repoArtRoot, repoArtDoc1.getParents().get(0).getParents().get(0).getParents().get(0)); // root
        
        assertNull(repoArtDoc1.getParents().get(0).getParents().get(0).getParents().get(0).getParents()); // none
        
        // Add new folder
        ArtifactJpaImpl repoArtSubSub1 = new ArtifactJpaImpl();
        repoArtSubSub1.addParent(repoArtSub);
        em.persist(repoArtSubSub1);
        
        repoArtDoc1.addParent(repoArtSubSub1);
        em.persist(repoArtDoc);
        
        Artifact_i repoArtDoc2 = em.find(ArtifactJpaImpl.class, repoArtDoc.getId());
        assertEquals(2, repoArtDoc2.getParents().size());
    }
}



