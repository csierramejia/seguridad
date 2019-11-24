package com.seguridad.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.dto.UsuarioDTO;
import com.seguridad.entity.Usuario;
import com.seguridad.service.UsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

//	 private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);
	private final UsuarioService usuarioService;
	
	public UsuarioResource(UsuarioService sorteoService) {
		this.usuarioService = sorteoService;
	}

	@GetMapping("/usuarios")	
	@ApiOperation(value = "Buscar Usuarios", notes = "Operacion para consultar usuarios")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Sorteos encontrados"),
			@ApiResponse(code = 404, message = "Usuarios no encontrados") })	
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@GetMapping("/usuarios/{id}")
	@ApiOperation(value = "Buscar Usuario", notes = "Operacion para consultar usuario por Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario encontrado"),
			@ApiResponse(code = 404, message = "Usuario no encontrado") })	
	public ResponseEntity<Optional<Usuario>> findById(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.findById(id));
	} 

    @PostMapping("/usuarios")
	@ApiOperation(value="Crear usuario", notes="Operacion para crear un nuevo usuario")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Usuario creado correctamente"),
			@ApiResponse(code=400, message="Solicitud invalida")})    
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
    	Usuario usuario = new Usuario();
    	BeanUtils.copyProperties(usuarioDTO, usuario);
    	//if (usuario.getId() != null) {
        //    throw new Exception("Un nuevo usuario no puede tener Id");
        //}
        return new ResponseEntity<>(this.usuarioService.save(usuario), HttpStatus.CREATED);
    }
    
}
