/*
  /lobbies GET (lista aktywnych lobby)
  /lobbies POST (dodanie nowego lobby)
  /lobbies/{LobbyID}/login GET (zalogowanie do lobby)
  /lobbies/{LobbyID}/logout GET (wylogowanie z lobby)
  /lobbies/{LobbyID}/mystatus POST (status="STATUS", zmiana statusu
    jendoczesnie uruchamia sprawdzanie statusu dla calego lobby
    dopiero gdy conajmniej 3 graczy jest READY, to mozna zaczynac)
  /lobbies/{LobbyID}/status GET (sprawdzenie statusu lobby)
*/

require("../app.js");
require("../database/lobbies_operations.js");
