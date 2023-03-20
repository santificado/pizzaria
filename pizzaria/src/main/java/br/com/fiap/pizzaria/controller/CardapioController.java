package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.repository.CardapioRepository;
import br.com.fiap.pizzaria.model.Cardapio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

public class CardapioController {
}
@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private CardapioRepository CardapioRepository;

    @PostMapping
    public Cardapio criarPizza(@RequestBody Cardapio cardapio) {
        return CardapioRepository.save(cardapio);
    }

    @GetMapping
    public Iterable<Cardapio> listarPizzas() {
        return CardapioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cardapio buscarPizza(@PathVariable Long id) {
        return CardapioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Cardapio atualizarPizza(@PathVariable Long id, @RequestBody Cardapio CardapioAtualizado) {
        Cardapio cardapio = CardapioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        cardapio.setNome(CardapioAtualizado.getNome());
        cardapio.setID(CardapioAtualizado.getID());
        cardapio.setPreco(CardapioAtualizado.getPreco());
        cardapio.setTamanho(CardapioAtualizado.getTamanho());
        return CardapioRepository.save(cardapio);
    }

    @DeleteMapping("/{id}")
    public void deletarPizza(@PathVariable Long id) {
        CardapioRepository.deleteById(id);
    }
}
