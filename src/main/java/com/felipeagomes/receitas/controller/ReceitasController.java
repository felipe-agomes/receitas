package com.felipeagomes.receitas.controller;

import java.util.List;
import java.util.Optional;

import com.felipeagomes.receitas.dtos.ReceitasDto;
import com.felipeagomes.receitas.entities.Categorias;
import com.felipeagomes.receitas.repositories.CategoriasRepository;
import com.felipeagomes.receitas.services.ReceitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipeagomes.receitas.entities.Receitas;
import com.felipeagomes.receitas.entities.Usuarios;
import com.felipeagomes.receitas.repositories.ReceitasRepository;
import com.felipeagomes.receitas.repositories.UsuariosRepository;

@RestController
@RequestMapping("/secure/receitas")
public class ReceitasController {
	private final ReceitasService receitasService;

	public ReceitasController(ReceitasService receitasService) {
        this.receitasService = receitasService;
    }

	@Autowired
	private ReceitasRepository receitasRepository;
	@Autowired
	private UsuariosRepository usuariosRepository;
	@Autowired
	private CategoriasRepository categoriasRepository;

	@GetMapping
	public List<ReceitasDto> findAllByUsuarioId(@RequestHeader long usuarioId) {
		return receitasService.findAllByUsuarioId(usuarioId);
	}

	@PostMapping
	public ReceitasDto saveReceita(@RequestBody ReceitasDto receita) {
		return receitasService.saveReceita(receita);
	}

	@PutMapping
	public Receitas updateReceita(@RequestBody Receitas receita, @RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			receita.setUsuario(usuario.get());
			receita.setId(id);
			return receitasRepository.save(receita);
		}

		return null;
	}

	@DeleteMapping
	public void deleteReceita(@RequestHeader long id, @RequestHeader long usuarioId) {
		Optional<Usuarios> usuario = usuariosRepository.findById(usuarioId);

		if (usuario.isPresent()) {
			receitasRepository.deleteById(id);
		}
	}

//	@GetMappingtegorias")
//	public List<Categorias> findAllCategorias(@RequestHeader long receitaId) {
//		Optional<Receitas> receita = receitasRepository.findById(receitaId);
//
//        return receita.map(Receitas::getCategorias).orElse(null);
//
//    }
//
//	@PostMappingtegorias")
//	public Categorias createReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId) {
//		Optional<Receitas> receita = receitasRepository.findById(receitaId);
//		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);
//
//		if (receita.isPresent() && categoria.isPresent()) {
//			receita.get().addCategoria(categoria.get());
//			receitasRepository.save(receita.get());
//
//			return categoria.get();
//		}
//
//		return null;
//	}
//
//	@PutMappingtegorias")
//	public void updateReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId, @RequestBody Categorias categorias) {
//		Optional<Receitas> receita = receitasRepository.findById(receitaId);
//		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);
//
//		if (receita.isPresent() && categoria.isPresent()) {
//			receita.get().deleteCategoria(categoria.get());
//
//			receita.get().addCategoria(categorias);
//			receitasRepository.save(receita.get());
//		}
//	}
//
//	@DeleteMappingtegorias")
//	public void deleteReceitaCategoria(@RequestHeader long receitaId, @RequestHeader long categoriaId) {
//		Optional<Receitas> receita = receitasRepository.findById(receitaId);
//		Optional<Categorias> categoria = categoriasRepository.findById(categoriaId);
//
//		if (receita.isPresent() && categoria.isPresent()) {
//			receita.get().deleteCategoria(categoria.get());
//			receitasRepository.save(receita.get());
//		}
//	}
}
