# language: pt

Funcionalidade: Gerenciamento de um animal da Loja

  Cenario: Lista somente animais disponíveis para a venda
    Dado que eu possuo animais disponíveis
    Quando eu pesquiso por todos os animais disponíveis
    Então eu recebo a lista de animais disponíveis