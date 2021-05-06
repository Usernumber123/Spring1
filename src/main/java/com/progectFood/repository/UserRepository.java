package com.progectFood.repository;

import com.progectFood.domian.entity.Role;
import com.progectFood.domian.entity.Status;
import com.progectFood.domian.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("from User where role=:role and status=:status order by id")
    List<User> getCourierByStatus(@Param("role") Role role, @Param("status") Status status);

    @Query("from User where role=:role and (status=:status1 or status=:status2) order by id")
    List<User> getActiveUserById(@Param("role") Role role, @Param("status1") Status status1, @Param("status2") Status status2);

    @Query("from User where lastName=:lastname and role=:role and status<>:status")
    List<User> findByLastName(@Param("lastname") String lastname, @Param("role") Role role, @Param("status") Status status);

    @Modifying
    @Query("update User set status=:status where id=:id")
    void changeStatus(@Param("status") Status status, @Param("id") Integer id);


    Optional<User> findOneByLogin(String login);

    @Query(value = "SELECT date_part('hour',current_time) from users", nativeQuery = true)
    List<Integer> currentHour();

    @Modifying
    @Query("update User set phone=:phone where id=:id")
    void changePhone(@Param("phone") String phone, @Param("id") Integer id);


    @Modifying
    @Query("update User set firstName= :firstName, lastName= :lastName, phone= :phone, password= :password," +
            " login=:login where id=:id")
    void updateUser(@Param("firstName") String firstName, @Param("lastName") String lastName,
                    @Param("phone") String phone, @Param("id") Integer id, @Param("login") String login, @Param("password") String password);

    @Modifying
    @Query("update User set password=:password where id=:id")
    void changePassword(@Param("password") String password, @Param("id") Integer id);


}
