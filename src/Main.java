import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Создаем фабрику сессий
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

        // Создаем сессию
        Session session = factory.getCurrentSession();

        try {
            // Создаем объекты Person
            Person person1 = new Person("Alice", 30);
            Person person2 = new Person("Bob", 25);
            Person person3 = new Person("Charlie", 35);

            // Открываем транзакцию
            session.beginTransaction();

            // Сохраняем объекты в базу данных
            session.save(person1);
            session.save(person2);
            session.save(person3);

            // Завершаем транзакцию
            session.getTransaction().commit();

            System.out.println("Объекты успешно сохранены в базу данных!");

        } finally {
            factory.close();
        }
    }
}
