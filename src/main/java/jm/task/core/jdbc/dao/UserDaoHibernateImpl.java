package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    // Реализовать работу с БД
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createSQLQuery("create table user\n" +
                    "(\n" +
                    "    id       bigint auto_increment,\n" +
                    "    name     varchar(50) not null,\n" +
                    "    lastName varchar(50) not null,\n" +
                    "    age      tinyint     not null,\n" +
                    "    constraint user_pk\n" +
                    "        primary key (id)\n" +
                    ");").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createSQLQuery("DROP TABLE user").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            userList = session.createQuery("from User").getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("delete User").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
