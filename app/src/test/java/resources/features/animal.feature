# language: pt

Funcionalidade: Gerenciamento de um animal da Loja

  Cenario: Lista somente animais disponíveis para a venda
    Dado que eu possuo animais available
    Quando eu pesquiso por todos os animais available
    Então eu recebo a lista de animais available
    # Passo desnecessário, somente para exemplo
    E eu recebo uma outra lista de animais available