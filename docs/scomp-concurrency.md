# SCOMP - Java Concurrency

## Introduction

This document describes the SCOMP part of the project related to Java concurrency.

## Threads

- Foram criadas threads para dar handle a cada cliente que se liga ao servidor da shared board; implementamos a classe Runnable à ClientHandler. A classe TcpServer, ao aceitar uma ligação de um cliente, cria uma instancia desta classe, cria uma instancia da classe Thread que recebe no seu construtor a instancia aanteriormente criada e chama o metodo start() da thread; este metodo retorna imediatamente (a thread continua a correr) e o servidor consegue assim esperar por mais ligações de clientes

## Local Objects

- Para guardar a sessão de utilizador de cada cliente cada thread cria uma instancia da classe ClientState usando a classe ThreadLocal que cria um objeto do tipo ClientState. as threads são multiplos "contextos" a correr no mesmo espaço de memória que têm uma stack separada das outras, no entanto a heap continua a ser partilha. Como todos os objetos são guardados na heap que é partilhada entre todas as threads, foi necessário usar a classe ThreadLocal, fazendo com que cada thread tenha a sua instancia da classe ClientState

## Race Conditions

- Race Conditions: foi implementado um contador de clientes que estão online. de modo a prevenir race conditions (duas threads diferentes tentam incrementar/decrementar o mesmo contador ao mesmo tempo), foi feita sincronização entre threads ao mudar o valor do contador. A classe SafeOnlineCounter implementa os métodos increment(), decrement() e getCount(). todas estas 3 classes usam a keyword synchronized, que faz com que quando o método é executado, o java use um lock interno específico a este objeto. Cada thread, ao chamar o método, tenta obter o lock. Caso outra thread já tenha obtido o lock, a thread espera com que este lock seja libertado. Este lock só pode ser obtido por uma thread de cada vez. O lock é obtido antes do método correr e libertado após a execução do mesmo.

## Thread Signaling

De 3 em 3 clientes print, ....

## References

[TP 11 - Java Concurrency](https://moodle.isep.ipp.pt/pluginfile.php/280091/mod_resource/content/3/Java%20Concurrency.pdf)
