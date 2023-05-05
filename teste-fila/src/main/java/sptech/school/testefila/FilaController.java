package sptech.school.testefila;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("/fila")
public class FilaController {

    Integer contReq = 0;

    @PostMapping
    public String executarRequisicao(@RequestParam Integer num1, @RequestParam Integer num2) {
        Semaphore semaforo = new Semaphore(1);
        try {
            semaforo.acquire();
            String conta = String.format("a soma dos numeros %d e %d é %d e seu numero de requisição é %d", num1, num2, num1 + num2, contReq++);
            semaforo.release(); // libera o semáforo
            return conta;
            // tenta adquirir o semáforo

            // se chegou aqui, significa que o semáforo foi adquirido com sucesso
            // execute a requisição aqui
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Erro ao executar a requisição";
        }
    }

}
