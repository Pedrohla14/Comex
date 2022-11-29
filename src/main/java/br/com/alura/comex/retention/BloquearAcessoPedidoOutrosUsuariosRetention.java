package br.com.alura.comex.retention;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.PedidoService;
import br.com.alura.comex.service.UsuarioService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Aspect
@Component
public class BloquearAcessoPedidoOutrosUsuariosRetention {

    private PedidoService pedidoService;

    public BloquearAcessoPedidoOutrosUsuariosRetention(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Around("@annotation(BloquearAcessoPedidoDeOutrosUsuarios)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Pedido pedido = pedidoService.findById((Long) joinPoint.getArgs()[0]);
        if (!UsuarioService.getIdUsuarioJWT().equals(pedido.getCliente().getUsuario().getId())) {
            throw new NoResultException("Usuário não autorizado");
        }

        return joinPoint.proceed();
    }
}
