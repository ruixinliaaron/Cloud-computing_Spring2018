package com.csye6225.spring2018.Repository;


import com.csye6225.spring2018.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Userrepository extends CrudRepository<Account,Integer> {
//    @Query("select t from user  t where t.emailAddr = :emailAddr")
//    Account findByUseremailAddr(@Param("emailAddr") String emailAddr);
        @Query(value="delete from user where id=2",nativeQuery=true)
        @Modifying
        @Transactional
        void deleteAllBySql();

        @Query(value="select * from user where id=?1",nativeQuery=true)
        @Transactional
        Account selectAllById(int id);

        @Query(value="select * from user where email_addr=?1",nativeQuery=true)
        @Transactional
        Account selectAllByemailAddr(String emailAddr);

        @Query(value="select * from user where password=?1",nativeQuery=true)
        @Transactional
        Account selectAllBypassword(String password);

        @Query(value="update user set password=?2 where email_addr=?1",nativeQuery = true)
        @Modifying
        @Transactional
        void updatePasswordByemailAddr(String emailAddr,String newpassword);

}

