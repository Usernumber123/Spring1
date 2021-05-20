package intagration.progectFood.repository;

import com.progectFood.Application;
import com.progectFood.domian.entity.Dish;
import com.progectFood.domian.entity.Restaurant;
import com.progectFood.repository.DishRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes=Application.class)
public class DishRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DishRepository dishRepository;

    @Test
    public void findDishesByRestTest() {
        Restaurant restaurant = new Restaurant();
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setTitle("123");
        Dish dish=new Dish();
        Dish dish1=new Dish();
        dish.setRestaurant(restaurant);
        dish1.setRestaurant(restaurant);
        entityManager.persist(restaurant);
        entityManager.persist(dish1);
        entityManager.persist(dish);
        entityManager.flush();


        List<Dish> found = dishRepository.findDishesByRest(restaurant);


        assertThat(found.get(0).getRestaurant())
                .isEqualTo(restaurant);
        assertThat(found.get(1).getRestaurant())
                .isNotEqualTo(restaurant1);
        assertThat(found.size())
                .isEqualTo(2);

    }
    @Test
    public void findDishesByRestTest1() {
        Restaurant restaurant = new Restaurant();
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setTitle("123");
        Dish dish=new Dish();
        Dish dish1=new Dish();
        dish.setRestaurant(restaurant);
        dish1.setRestaurant(restaurant);
        entityManager.persist(restaurant);
        entityManager.persist(dish1);
        entityManager.persist(dish);
        entityManager.flush();


        List<Dish> found = dishRepository.findDishesByRest(restaurant);


        assertThat(found.get(0).getRestaurant())
                .isEqualTo(restaurant);
        assertThat(found.get(1).getRestaurant())
                .isNotEqualTo(restaurant1);
        assertThat(found.size())
                .isEqualTo(2);

    }
}
