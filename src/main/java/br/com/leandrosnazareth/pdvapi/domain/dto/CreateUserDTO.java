package br.com.leandrosnazareth.pdvapi.domain.dto;

import lombok.Data;

@Data
public class CreateUserDTO {

    private int id;
    private String username;
    private String password;
    private String name;

}
