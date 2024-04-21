# Lab FrameWeb

Neste roteiro, exercitaremos o básico da modelagem FrameWeb com base no exemplo do [JavaHostel](https://github.com/dwws-ufes/javahostel/tree/main/jakartaee9) utilizando o software Visual Paradigm (VP) e o plug-in do FrameWeb.


## Preparação do Visual Paradigm:

1. Abra o VP, na aba _Help_, clique em _Update_ para abrir uma outra janela chamada _VP Update_;

2. Feche o VP, verifique se há atualizações e tente instalá-las (talvez seja necessário ter privilégios de administrador na máquina local para isso);

3. Caso tenha conseguido instalar, repita a operação, porém agora use a opção _Update to latest patch_ do _VP Update_, verifique se há algum _patch_ e tente instalá-lo;


## Instalação do plug-in FrameWeb:

Visite o repositório [nemo-ufes/frameweb-vp-plugin no GitHub](https://github.com/nemo-ufes/frameweb-vp-plugin) e tente fazer a instalação automática. Caso não seja possível, realize a instalação manual:

4. Clone o repositório `frameweb-vp-plugin` em sua máquina local com Git e abra a pasta do repositório no Visual Studio Code;

5. Com ajuda do professor (há também algumas dicas no README do repositório), descubra onde o VP foi instalado em sua máquina, este é o `<!-- APP_PATH -->`;

6. Descubra também onde é a pasta onde o VP instala os plug-ins, este é o `<!-- PLUGIN_PATH -->`;

7. Edite o arquivo `pom.xml`, procure pelos dois termos acima e substitua-os pelo caminho completo para as respectivas pastas;

8. Com o VP fechado, execute `mvn clean install` no diretório do repositório `frameweb-vp-plugin`;

9. Abra o VP e verifique se as funções do plug-in FrameWeb (ex.: _Generate Code for FrameWeb Model_) aparecem na aba _Plugin_;


## Modelo de Entidades:

Caso tenha havido algum problema com a instalação do plug-in FrameWeb, prossiga com a construção dos modelos FrameWeb, porém sem o auxílio do plug-in. Se nem o VP estiver funcionando a contento, use outro editor UML.

Para o Modelo de Entidades, utilizaremos como base o código do pacote `domain` do JavaHostel [disponível no repositório](https://github.com/dwws-ufes/javahostel/tree/main/jakartaee9/javahostel/src/main/java/br/ufes/inf/labes/javahostel/domain).

10. Crie um novo projeto (_Project_ > _New_) para a modelagem do JavaHostel e salve-o (_Project_ > _Save_) em alguma pasta. Recomenda-se salvar a cada passo concluído;

11. Na seção _Model Explorer_ na barra lateral esquerda (caso não apareça, na aba _View_ clique em _Panes_ > _Model Explorer_, minimize-o para que fique na barra), clique com o botão direito (usarei "clique-direito" daqui pra frente) no item _JavaHostel_ e escolha _Model_ > _Design Model_;

12. Clique-direito então em _Design Model_ e escolha _Sub Diagrams_ > _New Diagram_. Procure por _Class Diagram_ e crie um diagrama de classes em branco chamado **Modelo de Entidades**;

13. Adicione um pacote (clique em _Package_ na paleta e depois em qualquer lugar em branco na tela à direita) e dê a ele o mesmo nome do pacote em que foram colocadas as classes de domínio do JavaHostel;

14. Aplique ao pacote o estereótipo `<<FrameWeb Entity Package>>` (clique-direito no pacote > _Set FrameWeb Stereotype_ > _Entity Package_);

15. Modele as classes de domínio do FrameWeb dentro deste pacote. Elas já serão por padrão persistentes. Algumas dicas de modelagem no VP:

    * Para adicionar uma classe a um pacote, selecione _Class_ na paleta e clique em qualquer lugar do pacote;
  
    * Para adicionar um atributo, clique-direito na classe > _Add_ > _Attribute_. Digite o atributo no formato `nome : tipo` (ex.: `id : Long`). Se tiver outro atributo para adicionar, basta pressionar Enter e incluir o próximo. Quando concluído (após pressionar Enter para o último atributo), pressione Esc;

    * Para editar algum nome de classe ou algum atributo, faça duplo-clique nele;

    * Para indicar que um atributo é identificador (`@Id` no código Java), clique-direito nele > _Set FrameWeb Stereotype_ > _id_ para adicionar o estereótipo `<<id>>`;

    * Para indicar que a geração de valores para um atributo identificador é automática (`@GeneratedValue(strategy = GenerationType.AUTO)` no Java), clique-direito nele > _Add FrameWeb Constraint_ > _generation=auto_ para adicionar a restrição `{generation=auto}`;

    * Para indicar que um atributo do tipo `Date` tem precisão apenas de data (`@Temporal(TemporalType.DATE)` no Java), clique-direito nele > _Add FrameWeb Constraint_ > _precision=date_ para adicionar a restrição `{precision=date}`;

    * Para modelar associações entre classes (ex.: `Booking` possui um atributo do tipo `Guest`), selecione _Association_ na paleta, clique e segure numa das classes associadas (ex.: `Booking`), arraste e solte em cima da outra classe (ex: `Guest`). Se quiser pode dar um nome à associação, porém na fase de projeto isso não é comum (mais comum, quando necessário, dar nome às extremidades, correspondente aos nomes dos atributos no código-fonte). Clicando nas setas que aparecem também permite configurar cardinalidade e navegabilidade das extremidades (vide também as dicas seguintes);

    * Já feita a associação, para especificar a cardinalidade de uma extremidade (ex.: um `Booking` se refere a um e somente um -- i.e., mínimo 1 e máximo 1 -- `Guest`), clique-direito na linha da associação porém bem próximo da classe que se deseja estabelecer a cardinalidade (ex.: `Guest`) abra o menu _Multiplicity_ e escolha o valor adequado (no exemplo, _1_). A sintaxe da UML prevê a indicação de mínimo e máximo `x..y`, porém note que `1` é equivalente a `1..1` e `*` é equivalente a `0..*` (mínimo 0, máximo não especificado, quantos quiser);

    * Já feita a associação, para especificar a navegabilidade de uma extremidade (ex.: a classe `Booking` tem atributo `Guest`, porém o contrário não ocorre, portanto a navegabilidade é `Booking -> Guest`), clique direito no lado que **não** é navegável (ex.: o lado de `Booking`) > _Navigable_ > _Unspecified_. Se a navegabilidade for nas duas direções, basta não fazer nada (a linha sem seta de ambos os lados representa isso);

    * Selecionar uma classe e pressionar Enter (ou clique-direto > _Open Specification_) abre a especificação completa da classe. Nas várias abas, é possível alterar a classe de diversas formas: seus atributos, métodos (_operations_), associações, estereótipos, etc. Algumas abas estão escondidas, talvez seja necessário clicar na pequena seta no topo à direita e mais ainda em _More..._;


## Modelo de Navegação:

Para o Modelo de Navegação, utilizaremos como base o código do [pacote `control`](https://github.com/dwws-ufes/javahostel/tree/main/jakartaee9/javahostel/src/main/java/br/ufes/inf/labes/javahostel/control) bem como as páginas Web da [pasta `registration`](https://github.com/dwws-ufes/javahostel/tree/main/jakartaee9/javahostel/src/main/webapp/registration) do JavaHostel.

16. Em _Model Explorer_ > _JavaHostel_ > _Design Model_, crie um diagrama de classes chamado **Modelo de Navegação: Cadastre-se**;

17. Adicione dois pacotes: um com o mesmo nome do pacote em que foi desenvolvido o controlador `RegistrationController` e outro com nome `/registration`, representando o caminho a partir da raiz da aplicação Web até o local das páginas a serem modeladas;

18. Aplique ao primeiro pacote o estereótipo `<<FrameWeb Controller Package>>` (clique-direito no pacote > _Set FrameWeb Stereotype_ > _Controller Package_) e ao segundo o estereótipo `<<FrameWeb View Package>>` (clique-direito > _Set FrameWeb Stereotype_ > _View Package_);

19. No pacote visão, crie classes para representar: a página `index.xhtml`, o formulário `regForm` desta página, a página `success.xhtml` e a página `underage.xhtml`. Nas páginas, aplique o estereótipo `<<page>>` e no formulário o estereótipo `<<form>>`;

      > No momento da escrita deste roteiro, o plug-in do FrameWeb ainda não implementa os atalhos para o Modelo de Navegação. Assim, teremos que adicionar um estereótipo manualmente usando o VP. Abra a especificação da classe e siga para a aba _Stereotypes_. Caso o estereótipo não exista na coluna da esquerda, clique em _Edit Stereotypes..._, _Add..._ e adicione-o (basta o nome). Caso já esteja, selecione-o e clique na seta para direita (_>_) e em _OK_.
      >
      > Após aplicar um determinado estereótipo a uma classe, um atalho aparece para repetirmos o mesmo em outra classe: clique-direito > _Stereotypes_. Neste menu há também uma opção _Edit Stereotypes..._ que serve de atalho para a aba _Stereotypes_ mencionada acima.

20. Adicione a relação de composição entre `index.xhtml` e `regForm`. Há duas formas de fazer: na paleta, clique e segure no item _Association_ e substitua-o por _Composition_; ou faça uma associação normal, em seguida clique-direito na extremidade da associação próximo de `index.xhtml` > _Aggregation Kind_ > _Composited_;

21. Adicione à `regForm` os campos de formulário como atributos. O nome de cada atributo deve ser o atributo referido no controlador (ex.: em `<h:inputText id="name" value="#{registrationController.guest.name}" />` o atributo no controlador é `guest.name`) enquanto o tipo de cada atributo deve ser o nome da tag utilizada (no exemplo: `inputText`);

      > Neste caso, se você digitar, por exemplo `guest.name : inputText` para adicionar um atributo da mesma forma que fizemos no Modelo de Entidades, o VP irá dizer que a sintaxe está incorreta. O problema é o "." que ele não aceita como parte do nome do atributo. Para contornar, é preciso abrir a especificação da classe, ir até a aba _Attributes_, dar duplo-clique no nome do atributo a modificar e inserir o ponto, pois ali o VP aceita.
      >
      > Dica: insira os atributos sem o prefixo `guest.`, depois abra a especificação e copie e cole `guest.` antes de todos os nomes de atributo.

22. No pacote controle, crie a classe para representar `RegistrationController` e seus atributos (exceto `registrationService` que será representado no Modelo de Aplicação) e método `register()`. Por padrão ela já é uma classe controladora (não há necessidade de estereótipos);

      > Para adicionar um método, de forma similar aos atributos, clique-direito > _Add_ > _Operation_, em seguida pode escrever direto na classe com a sintaxe `nomeDoMetodo(param1 : tipo1, paramN : tipoN) : tipoDeRetorno` ou abrir a especificação da classe para construi-lo.
      >
      > Você pode também abrir a especificação de um atributo ou de um método, selecionando especificamente um deles e pressionando Enter (ou clique-direito > _Open Specification..._).

23. Represente a ação do `commandButton` de `regForm`, ou seja, a chamada do método `register()` em `RegistrationController` por meio de uma relação de dependência (use _Dependency_ na paleta) do formulário para a classe controladora. Insira `{method=register}` como nome da dependência;

      > Em teoria, `method=register` deveria ser adicionado como uma restrição UML e o VP exibi-la como `{method=register}`. No entanto, ao abrir a especificação da dependência, ir à aba _Constraints_, adicionar uma nova restrição `method=register` e confirmar, ela não aparece no diagrama. Talvez isso possa ser resolvido, mas não tendo uma solução no momento adotamos a prática de usar o nome da dependência para inserir as restrições manualmente.

24. Represente os dois tipos de retorno do método `register()` para as páginas `underage.xhtml` e `success.xhtml` também como relações de dependência, da classe controladora para as páginas;

25. Por fim, represente as _tags_ `outputText` presentes nestas duas páginas como atributos das classes que as representam no diagrama, definindo nome e tipo do atributo da mesma forma que foi feito para `regForm` no passo 21;


## Modelo de Aplicação:

Para o Modelo de Aplicação, utilizaremos como base o código do [pacote `application`](https://github.com/dwws-ufes/javahostel/tree/main/jakartaee9/javahostel/src/main/java/br/ufes/inf/labes/javahostel/application) do JavaHostel.

26. De forma similar ao feito anteriormente, crie um diagrama de classes chamado **Modelo de Aplicação**;

27. Adicione um pacote e dê a ele o mesmo nome do pacote em que se encontra a classe `RegistrationService` do JavaHostel;

28. Aplique ao pacote o estereótipo `<<FrameWeb Application Package>>` (clique-direito no pacote > _Set FrameWeb Stereotype_ > _Application Package_);

29. Suponha que já tenha sido solicitado um _refactoring_ do seu projeto para que as classes de serviço fossem divididas em interface e implementação. Adicione uma classe `RegistrationService` ao pacote de aplicação e indique que ela é uma interface, adicionando o estereótipo `<<service-interface>>` (clique-direito > _Set FrameWeb Stereotype_ > _Service Interface_);

      > Se estiver usando o plug-in FrameWeb, ele vai automaticamente substituir a visualização clássica da classe (retângulo dividido em 3 partes) por uma representação de interface (um círculo). Para fazer essa mudança manualmente, é preciso primeiro adicionar o estereótipo `<<Interface>>` à classe e, em seguida, clicar-direito > _Presentation Options_ > _Interface Ball_.

30. Nesse _refactoring_, a classe que antes se chamava `RegistrationService` (que agora é o nome da interface) deverá ser renomeada para `RegistrationServiceBean`. Adicione-a ao pacote (o estereótipo `<<service-class>>` é opcional, por padrão classes no pacote de aplicação já são classes de serviço);

31. Adicione uma relação de especialização (_Generalization_ na paleta) entre `RegistrationService` e `RegistrationServiceBean`, para indicar que a segunda implementa a primeira;

32. Represente o método `register()` de `RegistrationServiceBean` no diagrama. Se quiser, represente também o método `calculateAge()` (use `-` pra indicar que é privativo, abra sua especificação para indicar que seu _Scope_ é do tipo _classifier_, i.e., método _static_);

33. Abra o _Model Explorer_ e arraste o pacote de controle para o Modelo de Aplicação;

34. Em seguida, arraste a classe `RegistrationController` para dentro do pacote de controle;

35. Como a classe `RegistrationController` já foi especificada no Modelo de Navegação, não é necessário exibir seus detalhes neste modelo. Clique-direito > _Presentation Options_ > _Class Members_ > _Hide All Class Members_;

36. Represente o fato de `RegistrationController` ter um atributo do tipo `RegistrationService` com uma associação entre elas no modelo, com a navegabilidade na direção da interface de serviço;

      > Por algum motivo o VP não indica a navegabilidade da relação com a interface. No entanto, isso não é um problema, visto que o padrão é que a navegabilidade é sempre na direção do controlador para a classe de serviço.


## Modelo de Persistência:

O JavaHostel não usa o padrão DAO ou similar, de modo que a princípio não teria, assim, um Modelo de Persistência. Para exercitar, no entanto, vamos supor que já estejam planejadas duas novas funcionalidades no próximo ciclo de desenvolvimento do JavaHostel:

* Uma funcionalidade de login, para a qual será necessário recuperar um `Guest` a partir do valor do atributo `email`;

* Uma funcionalidade de busca de camas disponíveis numa determinada data, para a qual será necessário recuperar uma lista de objetos `Bed` a partir de um período (datas inicial e final) fornecidas pelo hóspede;

Dadas essas novas especificações:

37. Crie o **Modelo de Persistência**, com pacote de persistência;

38. Represente as interfaces e implementações dos DAOs de todas as classes, usando como padrão de nomes: `ClasseDAO` (interface) e `ClasseJPADAO` (implementação), ex.: `GuestDAO` e `GuestJPADAO`;

39. Represente nos DAOs apropriados os métodos que serão necessários para as novas funcionalidades listadas acima;

40. Adicione uma interface e uma classe de serviço para estas funcionalidades no Modelo de Aplicação, em seguida inclua também lá o pacote de persistência, as interfaces DAO e as dependências das classes de serviço com as respectivas interfaces DAO.