# language: pt

Funcionalidade: Gerenciamento de um usuario na Petstore

  Algum contexto de negocio
  Historia do jira
  Qualquer coisa que faça sentido pro negócio

#  Cenario: Cria um usuario na loja
#    Quando eu faço um POST para /v3/user com os seguintes valores:
#      | id         | 10              |
#      | username   | rafael          |
#      | firstName  | Rafael          |
#      | lastName   | Lima            |
#      | email      | rafael@email.com |
#      | password   | 12345           |
#      | phone      | 12345           |
#      | userStatus | 1               |
#    Então quando faço um GET para /v3/user/rafael, o usuário criado é retornado
#
#  @quarentine # ignora o cenario
#  Cenario: Cria um usuário na loja usando docString
#    Quando eu faço um POST para /v3/user com a seguinte docString:
#          """json
#        {
#          "id": 10,
#          "username": "theUser",
#          "firstName": "John",
#          "lastName": "James",
#          "email": "john@email.com",
#          "password": "12345",
#          "phone": "12345",
#          "userStatus": 1
#        }
#      """
#    Então quando faço um GET para /v3/user/theUser, o usuário criado é retornado


#  Cenario: Cria um usuario na loja usando docstring
#    Quando faço um POST para /v3/user com os seguintes docstring:
#      """json
#        {
#          "id": 10,
#          "username": "theUser",
#          "firstName": "John",
#          "lastName": "James",
#          "email": "john@email.com",
#          "password": "12345",
#          "phone": "12345",
#          "userStatus": 1
#        }
#      """
#    Entao quando faço um GET para /v3/user/rafael, o usuario criado é retornado


  Cenario: Cria usuário na loja refletindo o negócio
    Quando crio um usuario
#    Entao recebo o status code 200
#      E o usuário é salvo no sistema
    Entao o usuário é salvo no sistema

