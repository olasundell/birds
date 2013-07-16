package se.atrosys.birds;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbcp.DataSourceConnectionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
import se.atrosys.birds.factory.ScrapedTableBuilder;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.service.BirdService;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = { "classpath:/spring/context.xml", "classpath:/spring/services.xml" } )
public class BaseTest extends AbstractTestNGSpringContextTests {
	protected Element table;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired(required = true) BirdModelListFactory birdModelListFactory;
	@Autowired(required = true) BirdService birdService;
//    @Autowired LocalContainerEntityManagerFactoryBean fb;
    @Autowired AnnotationSessionFactoryBean sessionFactoryBean;
	@Autowired HibernateTransactionManager transactionManager;
	@Autowired BasicDataSource basicDataSource;

    private static List<BirdModel> birdModels;

/*	@Override
	@BeforeSuite
	protected void springTestContextPrepareTestInstance() throws Exception {
		super.springTestContextPrepareTestInstance();
	}*/

	// this test is a prerequisite for all other tests requiring a database.
	@BeforeMethod(groups = "system")
	public void initDb() throws Exception, IOException, NoFamilyException, NoSuchLanguageException, CouldNotFindDetailsException, JAXBException, SQLException {
        if (birdModels == null || birdModels.isEmpty()) {
            birdModels = birdModelListFactory.scrapeFromAviBase("avibase-short.html");
        }

        if (!birdService.findAll().isEmpty()) {
//			resetDB();
			birdService.clearAll();
		}
		
        assertTrue(birdService.findAll().isEmpty(), "Could not clear birds.");

//        birdService.saveAll(birdModels);
		birdService.saveAll(birdModelListFactory.scrapeFromAviBase("avibase-short.html"));
        List<BirdModel> list = birdService.findAll();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertNotNull(list.get(0).getFamily());
        assertNotNull(list.get(0).getNames(), "Names list is null");
        assertFalse(list.get(0).getNames().isEmpty(), "Names list is empty");
	}

	private void resetDB() {
		//            new SchemaExport(new Configuration());
//            birdService.clearAll();
/*            Ejb3Configuration cfg = new Ejb3Configuration();
            Ejb3Configuration configured = cfg.configure(sessionFactoryBean.getConfiguration());*/
//            SchemaExport schemaExport = new SchemaExport(configured.getHibernateConfiguration());
//			Configuration configuration = sessionFactoryBean.getConfiguration();
//			SchemaExport schemaExport = new SchemaExport(configuration);
//			schemaExport.drop(true, false);
//            schemaExport.create(true, false);
//			LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) applicationContext.getBean("&mySessionFactory");
//			Session currentSession = sessionFactoryBean.getObject().getCurrentSession();
		sessionFactoryBean.dropDatabaseSchema();
		sessionFactoryBean.createDatabaseSchema();
		transactionManager.getSessionFactory().getCache().evictCollectionRegions();
		transactionManager.getSessionFactory().getCache().evictDefaultQueryRegion();
//			transactionManager.getSessionFactory().getCache().evictQueryRegions();
		transactionManager.getSessionFactory().getCache().evictEntityRegions();
/*			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setUrl(basicDataSource.getUrl());

			BasicDataSourceFactory dataSourceFactory = new BasicDataSourceFactory();
			dataSource = (BasicDataSource) dataSourceFactory.getObjectInstance(basicDataSource, null, null, null);

			sessionFactoryBean.setDataSource(dataSource);
			transactionManager.setDataSource(dataSource);
			transactionManager.setSessionFactory(sessionFactoryBean.getObject());
			Map<String, Object> foo = applicationContext.getBeansWithAnnotation(Repository.class);*/

/*			SessionFactory sessionFactory = new SessionFactoryImpl(
					configuration,
					configuration.buildMapping(),
					configuration.buildSettings(),
					configuration.getEventListeners(),
					configuration.getSessionFactoryObserver()
			);*/

/*			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		 sessionFactory.setHibernateProperties(sessionFactoryBean.getHibernateProperties());
		 sessionFactory.setDataSource(dataSource);*/

/*//			SessionFactory object = sessionFactory.
			HibernateTemplate hibernateTemplate = new HibernateTemplate(object);

			for (Object bar : foo.values()) {
				((HibernateDaoSupport)bar).setHibernateTemplate(hibernateTemplate);
//				((HibernateDaoSupport)bar).setSessionFactory();
			}*/
/*			LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
			localSessionFactoryBean.setHibernateProperties(sessionFactoryBean.getHibernateProperties());
			localSessionFactoryBean.setDataSource(sessionFactoryBean.getDataSource());*/

//			transactionManager.setSessionFactory(localSessionFactoryBean.getObject());
	}

	public BaseTest() {
		ScrapedTableBuilder tableBuilder = new ScrapedTableBuilder();
		try {
			table = tableBuilder.getTable(Jsoup.parse(new File("avibase.html"), "UTF-8"));
		} catch (IOException e) {
			logger.error("Could not read file", e);
		}
	}
	
/*	@BeforeSuite(alwaysRun = true, enabled = true)
	public void beforeSuite() throws IOException {
		logger.info("beforeSuite");
		ScrapedTableBuilder tableBuilder = new ScrapedTableBuilder();
		table = tableBuilder.getTable(Jsoup.parse(new File("avibase.html"), "UTF-8"));
	}*/
}
