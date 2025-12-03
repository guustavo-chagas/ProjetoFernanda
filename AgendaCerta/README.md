# Agenda Local - Projeto (simples, nível estudante)

Estrutura padrão do Spring Boot (Maven). O projeto implementa de forma simples os requisitos:
- Cadastro de clientes, prestadores, serviços
- Agendamento com bloqueio de horários duplicados por prestador
- Geração automática de Ordem de Serviço ao confirmar agendamento
- Registro de pagamento e finalização da ordem somente após pagamento
- Cancelamentos com justificativa
- Registros criados/atualizados com timestamps
- Simulação completa no método `CommandLineRunner` (Application.java)

Como rodar:
- Tenha Java 17+ e Maven instalados.
- No diretório do projeto, rode: `mvn spring-boot:run`
- A simulação será executada e mostrada no console.

Observações:
- Implementação em memória (listas/hashes) para facilitar execução em qualquer computador sem configurar banco de dados.
- Código simples, comentado e pensado para ser compreendido por um estudante.

Responsável: Gustavo Chagas
