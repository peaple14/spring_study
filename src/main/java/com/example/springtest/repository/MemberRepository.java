package com.example.springtest.repository;


import com.example.springtest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.id = :loginId AND m.password = :password")
    Member findByLoginId(@Param("loginId") String loginId, @Param("password") String password);

    @Query("SELECT m.id FROM Member m WHERE m.email = :email")
    String findIdByEmail(@Param("email") String email);

    @Query("SELECT m.password FROM Member m WHERE m.id = :id AND m.email = :email")
    String findPasswordByIdAndEmail(@Param("id") String id, @Param("email") String email);

}

