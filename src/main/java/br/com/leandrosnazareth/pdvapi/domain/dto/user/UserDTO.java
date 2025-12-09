package br.com.leandrosnazareth.pdvapi.domain.dto.user;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String name;
//    private List<Role> roles;
}