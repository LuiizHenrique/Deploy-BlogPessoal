package org.Generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.Generation.blogPessoal.model.UserLogin;
import org.Generation.blogPessoal.model.Usuario;
import org.Generation.blogPessoal.repositary.UsuarioRepository;
import org.Generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;
	@Operation(summary = "Faz login do Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Email ou senha inválidos"),
            @ApiResponse(responseCode = "422", description = "Usuario já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
		return usuarioService.logarUsuario(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

    @Operation(summary = "Faz cadastro do Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "422", description = "Usuario já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		return usuarioService.cadastrarUsuario(usuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}
	   @Operation(summary = "Busca lista de usuarios no sistema")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Retorna todos Usuarios"),
	            @ApiResponse(responseCode = "400", description = "Retorno sem Usuarios"),
	            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	    })
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	   @Operation(summary = "Atualiza Usuario existente")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "201", description = "Retorna Usuario Atualizado"),
	            @ApiResponse(responseCode = "400", description = "Erro na requisição"),
	            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	    })
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioRepository.save(usuario));
	}

}