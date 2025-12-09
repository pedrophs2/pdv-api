package br.com.leandrosnazareth.pdvapi.domain.repository;

import br.com.leandrosnazareth.pdvapi.domain.dto.user.EditUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.leandrosnazareth.pdvapi.domain.entity.Usuario;

@Repository
public interface UserRepository  extends JpaRepository<Usuario, Long> {	

	@Query("select u from Usuario u where u.username = ?1")
	Usuario findUserByUsername(String username);

	Usuario findByUsername(String username);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update usuario set token = ?1 where username = ?2")
	void atualizaTokenUser(String token, String username);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE usuario set username = :username, name = :name WHERE id = :id")
	void editUser(
			@Param("username") String username,
			@Param("name") String name,
			@Param("id") Long id);
}