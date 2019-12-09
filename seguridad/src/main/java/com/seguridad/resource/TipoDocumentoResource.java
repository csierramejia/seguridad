package com.seguridad.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.dto.TipoDocumentoDTO;
import com.seguridad.entity.TipoDocumento;
import com.seguridad.service.TipoDocumentoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tipo-documento")
public class TipoDocumentoResource {

//	 private final Logger log = LoggerFactory.getLogger(TipoDocumentoResource.class);
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	@GetMapping("/tipo-documentos")	
	@ApiOperation(value = "Buscar tipos de documento", notes = "Operacion para consultar tipos de documento")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Sorteos encontrados"),
			@ApiResponse(code = 404, message = "Usuarios no encontrados") })	
	public ResponseEntity<List<TipoDocumento>> findAll() {
		return ResponseEntity.ok(tipoDocumentoService.findAll());
	}

	@GetMapping("/tipo-documentos/{id}")
	@ApiOperation(value = "Buscar tipo de documento", notes = "Operacion para consultar tipo documento por Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Tipo documento encontrado"),
			@ApiResponse(code = 404, message = "Tipo documento no encontrado") })	
	public ResponseEntity<Optional<TipoDocumento>> findById(@PathVariable String id) {
		return ResponseEntity.ok(tipoDocumentoService.findById(id));
	} 

    @PostMapping("/tipo-documentos")
	@ApiOperation(value="Crear tipo documento", notes="Operacion para crear un tipo de documento")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Tipo de documento creado correctamente"),
			@ApiResponse(code=400, message="Solicitud invalida")})    
    public ResponseEntity<TipoDocumento> crear(@RequestBody TipoDocumentoDTO tipoDocumentoDTO) throws Exception {
    	TipoDocumento tipoDocumento = new TipoDocumento();
    	BeanUtils.copyProperties(tipoDocumentoDTO, tipoDocumento);

    	return new ResponseEntity<>(this.tipoDocumentoService.save(tipoDocumento), HttpStatus.CREATED);
    }
    
}
