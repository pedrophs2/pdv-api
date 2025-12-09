package br.com.leandrosnazareth.pdvapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.leandrosnazareth.pdvapi.domain.dto.user.CreateUserDTO;
import br.com.leandrosnazareth.pdvapi.domain.dto.user.EditUserDTO;
import br.com.leandrosnazareth.pdvapi.domain.dto.user.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leandrosnazareth.pdvapi.domain.dto.LoginDTO;
import br.com.leandrosnazareth.pdvapi.domain.entity.Usuario;
import br.com.leandrosnazareth.pdvapi.domain.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository usuarioRepository;

    public Optional<LoginDTO> findByIdDTO(long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> modelMapper.map(usuario, LoginDTO.class));
    }

    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario findByName(String name) {
        return usuarioRepository.findByUsername(name);
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public List<UserDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UserDTO.class))
                .collect(Collectors.toList());
    }

    public LoginDTO save(CreateUserDTO user) {
        Usuario usuario = modelMapper.map(user, Usuario.class);
        return modelMapper.map(usuarioRepository.save(usuario), LoginDTO.class);
    }

    public UserDTO edit(EditUserDTO user) {
        UserDTO usuario = modelMapper.map(user, UserDTO.class);

        usuarioRepository.editUser(
                user.getUsername(),
                user.getName(),
                user.getId()
        );

        return usuario;
    }
}