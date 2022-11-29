package br.com.alura.comex.service;

import br.com.alura.comex.controller.model.ClienteDTO;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class ClientesService {

    private final ClienteRepository clienteRepository;

    public ClientesService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> {
            throw new NoResultException("Ops! Ocorreu um problema, o Cliente não foi encontrado. :(");
        });
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<ClienteDTO> findClientes(Pageable pageable) {
        return clienteRepository.findClientes(pageable);
    }
}
