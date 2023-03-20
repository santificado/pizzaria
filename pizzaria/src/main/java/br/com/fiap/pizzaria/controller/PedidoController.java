package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.model.Pedido;
import br.com.fiap.pizzaria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class PedidoController {
}
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private PedidoRepository clientRepository;

    @GetMapping
    public List<Pedido> getClients() {
        return PedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getClientById(@PathVariable Long id) {
        return PedidoRepository.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
                });
    }

    @PostMapping
    public Pedido createClient(@RequestBody Pedido pedido) {
        return PedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public Pedido updateClient(@PathVariable Long id, @RequestBody Pedido PedidoAtualizado) {
        Pedido pedido = PedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        pedido.setNome(PedidoAtualizado.getNome());
        pedido.setEntrega(PedidoAtualizado.getEntrega());

        return PedidoAtualizado.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        PedidoRepository.deleteById(id);
    }
}

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getOrders() {
        return PedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getOrderById(@PathVariable Long id) {
        return PedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @PostMapping
    public Pedido CriarPedido(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public Pedido AtualizarPedido(@PathVariable Long id, @RequestBody Pedido AtualizarPedido) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        pedido.setNome(AtualizarPedido.getNome());
        pedido.setEntrega(AtualizarPedido.getEntrega());

        return pedidoRepository.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
    }
}
