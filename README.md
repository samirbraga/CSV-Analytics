# Trabalho Final - Técnicas de Programação

Este repositório contém o trabalho final da disciplina Técnicas de Programação, cursada pelo bacharelado em Ciência da Computação da Universidade Federal do Ceará no semestre 2018.1. A disciplina aborda as técnicas de programação orientada a objetos.

## Equipe
 - Breno Araújo de Lima [@brenoaraujolima](https://github.com/brenoaraujolima)
 - Francisco San Diego de Sousa Castilho [@SanDiegoCastilho](https://github.com/SanDiegoCastilho)
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

 Nosso controlador *CSVController* recebe e calcula sob demanda as métricas e gráficos.
 
## Padrões de Projeto

- **Observer**

Utilizamos o padrão Observer para representar a interface entre *WSView* e *CSVController*. Sempre que um arquivo é carregado, o observado *WSView* alerta o observador *CSVController* que recebe os dados e cria uma nova sessão. Este padrão foi escolhido pois permite um baixo acoplamento entre visão e modelo.

No front-end, o Observer também é utilizado por meio do padrão Redux de persitência de estruturas de dados, populadas por emissão e captura de eventos por toda a aplicação.

- **Strategy**

O padrão Strategy foi utilizado para representar as diferentes estratégias de manipulação dos dados. Cada tipo de operação (*ArithmeticOperation*, *GrapichOperation* ou *Mode*) implementa o padrão em suas subclasses. Este padrão foi escolhido pois permite que os algoritmos variem de forma independente, e que o cliente *CSVController* escolha em tempo de execução qual deles deseja utilizar.

- **Factory**

O ReactJs trabalha com o conceito de Web Components e os trata como classes ou como *Factory Functions*, cada componente ao ser adicionada ao Virtual DOM (árvore de elementos criada dinâmicamente pelo javascript) é instanciado pelo padrão Factory, o qual, em determinado contexto instancia o componente que convém. [Mais sobre](http://brianyang.com/szabototo89-create-it/).

## Características

Optamos por criar um Web Sevice em Java utilizando o Framework Spring Boot. Nele recebemos e tratamos os arquivos CSVs via requisição HTTP e calculamos todas as métricas sob demanda.

- **Técnologias**	
Spring Boot Documentação: https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/

- **Interface Gráfica**
A interface gráfica do projeto baseia-se em uma Single Page Application, desenvolvida em [ReactJs](https://reactjs.org) com o gerador de projetos "Create React App". Por meio do padrão webpack, todo projeto é transformado em arquivos estáticos, os quais estão hospedados no Github Pages, podendo ser acesso por meio do link http://samirbraga.github.io/CSV-Analytics. 
Como o CORS WebService foi configurado de modo a ser aberto, as requisições são feitas via AJAX e o fluxo da página é guiado pelas respostas do servidor. Contudo, a API pode ser usado por quem quer que seja.

## Métricas E Gráficos

# Métricas

- **Média**

Métrica que demonstra a tendência de concentração dos dados.É representada pelo quociente entre a soma de todos os valores de um   determinado conjunto e a quantidade de valores. 
- **Moda**

É o valor que ocorre com maior frequência em um determinado conjunto de dados.Um mesmo conjunto pode possuir mais de uma moda. Nesse caso,dizemos que ele é polimodal.
- **Mediana**

É a medida que representa o valor central em uma distribuição de dados,quando organizados em ordem crescente ou decrescente.No caso em que a quantidade de elementos é par,a mediana é igual a média aritmética entre os dois termos centrais.
- **Variância**

Indica o grau de dispersão dos dados em relação à media.É calculada através do quociente entre a soma dos quadrados das diferenças entre cada valor do conjunto e a média, e a quantidade de elementos do conjunto decrementada em uma unidade.
- **Desvio Padrão**

Também é utilizado para representar a uniformidade de um conjunto de dados.Em comparação com a variância, possui a vantagem de ser expresso na mesma unidade dos dados de origem. É numericamente igual a raiz quadrada positiva da variância.
- **Mínimo**

Menor valor numeŕico dentro do conjunto de dados.
- **Máximo**

Maior valor numérico dentro do conjunto de dados.
- **Obliquidade (skewness)**

Medida que quantifica a assimetria entre os lados de uma distribuição de dados em um gráfico.Caso a obliquidade seja positiva, então o conjunto possui mais elementos abaixo da média.Se a obliquidade for negativa,então o conjunto possui mais elementos acima da média.
- **Curtóse (kurtosis)**

Representa o achatamento da curva em uma função de distribuição de dados.
- **Covariância**

Mede o grau de dependência ou independência entre duas variáveis aleatórias.
- **Coeficiente de Correlação de Pearson**

Exprime o grau de relação entre duas variáveis através de valores situados entre -1 e 1.
# Gráficos

- **Gráfico de Barras**

Conjunto de barras justapostas caracterizando os elementos da amostra,onde a altura de cada barra é proporcional ao valor numérico que ela representa.
- **Tabela de Contingência**

Maneira gráfica de visualizar o comportamento de mútiplas variáveis de maneira simultânea.
- **Tabela de Frequências**

Tabela para visualização do número de ocorrências de cada valor quantitativamente e percentualmente.
- **Histograma**

Conjunto de retângulos assentados sobre um eixo,onde suas alturas correspondem às frequências relativas dos valores da amostra.
- **Boxplot**

Consiste em uma reresentação através de um diagrama de caixas, facilitando a visualização das principais medidas de centro,assimetria e dispersão de uma distribuição de valores.
- **Scatterplot**

Representação de dados por meio de pontos em um eixo vertical e horizontal onde pode-se verificar a relação entre duas ou mais variáveis.
