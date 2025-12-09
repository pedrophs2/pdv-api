package br.com.leandrosnazareth.pdvapi.domain.dto.user;

import lombok.Data;

@Data
public class EditUserDTO {
    private Long id;
    private String username;
    private String name;
}
