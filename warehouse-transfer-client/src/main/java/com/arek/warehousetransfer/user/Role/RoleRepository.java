//package com.arek.warehousetransfer.user.Role;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Set;
//
//public interface RoleRepository extends JpaRepository<Role,Integer> {
//
//	@Query("SELECT r FROM Role r")
//	Set<Role> findAllRoles();
//
//	Role findFirstByName(String name);
//}
