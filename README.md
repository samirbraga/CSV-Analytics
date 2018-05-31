# Trabalho Final - Técnicas de Programação

Este repositório contém o trabalho final da disciplina Técnicas de Programação, cursada pelo bacharelado em Ciência da Computação da Universidade Federal do Ceará no semestre 2018.1. A disciplina aborda as técnicas de programação orientada a objetos.

## Equipe
 - Breno Araújo de Lima
 - Francisco San Diego de Sousa Castilho
 - Lucas Tavares de Oliveira [@ltdeoliveira](https://github.com/ltdeoliveira)
 - Matheus Sousa Correia [@Lukiam23](https://github.com/Lukiam23)
 - Samir Braga Chaves [@samirbraga](https://github.com/samirbraga)

## Objetivo
O objetivo deste trabalho é criar um analisador descritivo de dados. A aplicação deve permitir que o usuário carregue um arquivo de extensão *csv* e calcule métricas e gráficos da estatística descritiva.

## Padrão de Arquitetura

Nestre projeto utilizamos o padrão de arquitetura **MVC (Model-View-Controller)**, separando a aplicação em três componentes interconectadas. O padrão foi escolhido pois, a partir de interfaces bem definidas entre as componentes, possibilita o desenvolvimento paralelo de maneira eficiente e modular, evitando que mudanças em uma componente afetem outras.

 - **Model**
 
 Nosso modelo *CSVModel* armazena os dados de cada sessão em um HashMap, onde as chaves são os códigos das sessões (geradas no upload de cada arquivo) e os valores são os dados de cada arquivo csv. A sessão *Session* também armazena as métricas já calculadas, evitando repetições desnecessárias de cálculos.
 
- **View**

Nossa visão *WSView* é a interface entre o *front-end* e o controller. Ela recebe as requisições do primeiro e repassa as instruções para o último.

- **Controller**

 Nosso controlador *CSVController* recebe  e calcula sob demanda as métricas e gráficos.
 
## Padrões de Projeto

- **Observer**

Utilizamos o padrão Observer para representar a interface entre *WSView* e *CSVController*. Sempre que um arquivo é carregado, o observado *WSView* alerta o observador *CSVController* que recebe os dados e cria uma nova sessão. Este padrão foi escolhido pois permite um baixo acoplamento entre visão e modelo.

- **Strategy**

O padrão Strategy foi utilizado para representar as diferentes estratégias de manipulação dos dados. Cada tipo de operação (*ArithmeticOperation*, *GrapichOperation* ou *Mode*) implementa o padrão em suas subclasses. Este padrão foi escolhido pois permite que os algoritmos variem de forma independente, e que o cliente *CSVController* escolha em tempo de execução qual deles deseja utilizar.
