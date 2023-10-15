package core.config;

import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("feature.*")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setResourceRef(true);
        bean.setJndiName("jdbc/polybrain_mem");
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }
    @Bean
    public SessionFactory sessionFactory() throws IllegalArgumentException, NamingException {
        return new LocalSessionFactoryBuilder(dataSource()).scanPackages("feature.*.vo")
                .addProperties(getHibernateProperties()).buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", MySQLDialect.class.getName());
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.current_session_context_class", SpringSessionContext.class.getName());
        return properties;
    }
    @Bean
    public TransactionManager transactionManager() throws IllegalArgumentException,
            NamingException {
        return new HibernateTransactionManager(sessionFactory());
    }
}
