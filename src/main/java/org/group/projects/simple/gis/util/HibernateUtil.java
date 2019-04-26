package org.group.projects.simple.gis.util;

import org.group.projects.simple.gis.model.entity.fias.FiasAddress;
import org.group.projects.simple.gis.model.entity.fias.FiasHouse;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = configureSessionFactory();

    /**
     * This method returns a new Properties Object
     * @return {@link Properties}
     * @throws IOException
     */
    private static Properties getSessionFactoryConfigurationPropertis(String propertiesFileName) throws IOException {

        Properties mProperties = new Properties();
        mProperties.load(HibernateUtil.class.getClassLoader()
                .getResourceAsStream(String.format("%s.%s", propertiesFileName, "properties")));

        return mProperties;
    }


    /**
     * This method returns a new Session Factory Object
     * @return {@link SessionFactory}
     * @throws HibernateException
     */
    private static SessionFactory configureSessionFactory() throws HibernateException {

        Configuration mConfiguration = null;

        try {
            mConfiguration = new Configuration()
                    .addProperties(getSessionFactoryConfigurationPropertis("fias-mysql"))
                    .addPackage( "simple.testing.system")
                    .addAnnotatedClass(FiasAddress.class)
                    .addAnnotatedClass(FiasHouse.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if(mConfiguration == null) {
            return null;
        }

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(
                mConfiguration.getProperties());

        return mConfiguration.buildSessionFactory(builder.build());
    }

    /**
     * This method allows to get a Session Factory Object
     * @return {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
