package br.com.discipular.context;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableJpaRepositories("br.com.discipular.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "br.com.discipular.service",
        "br.com.discipular.repository",
        "br.com.discipular.validator"
})
@PropertySource({"classpath:application.properties", "classpath:i18n/messages_pt_BR.properties"})
public class ApplicationContext {

	@Resource
	private Environment env;
	
    private static final String PROPRIEDADE_DATABASE_DRIVER = "db.driver";
    private static final String PROPRIEDADE_DATABASE_PASSWORD = "db.password";
    private static final String PROPRIEDADE_DATABASE_URL = "db.url";
    private static final String PROPRIEDADE_DATABASE_USERNAME = "db.username";

    private static final String PROPRIEDADE_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPRIEDADE_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPRIEDADE_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPRIEDADE_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPRIEDADE_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPRIEDADE_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	
    /**
	 * Configuração do Driver de conexão
	 */
	@Bean
	public DataSource dataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();
		
		ds.setDriverClass(env.getRequiredProperty(PROPRIEDADE_DATABASE_DRIVER));
        ds.setJdbcUrl(env.getRequiredProperty(PROPRIEDADE_DATABASE_URL));
        ds.setUsername(env.getRequiredProperty(PROPRIEDADE_DATABASE_USERNAME));
        ds.setPassword(env.getRequiredProperty(PROPRIEDADE_DATABASE_PASSWORD));
		
		return ds;
	}
	
	/**
	 * Configuração do EntityManager Factory da Aplicação
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(this.dataSource());
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setPackagesToScan(env.getRequiredProperty(PROPRIEDADE_ENTITYMANAGER_PACKAGES_TO_SCAN));
		
		Properties p = new Properties();
		p.put(PROPRIEDADE_HIBERNATE_DIALECT, env.getRequiredProperty(PROPRIEDADE_HIBERNATE_DIALECT));
        p.put(PROPRIEDADE_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPRIEDADE_HIBERNATE_FORMAT_SQL));
        p.put(PROPRIEDADE_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROPRIEDADE_HIBERNATE_HBM2DDL_AUTO));
        p.put(PROPRIEDADE_HIBERNATE_NAMING_STRATEGY, env.getRequiredProperty(PROPRIEDADE_HIBERNATE_NAMING_STRATEGY));
        p.put(PROPRIEDADE_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPRIEDADE_HIBERNATE_SHOW_SQL));

		em.setJpaProperties(p);
		
		return em;
	}
	
	/**
	 * Configurando bean para o gerenciamento de transações
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager jtm = new JpaTransactionManager();
		jtm.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return jtm;
	}
	
}
