package br.com.leandrosnazareth.pdvapi.domain.dto;

import br.com.leandrosnazareth.pdvapi.domain.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String name;
//    private List<Role> roles;
}