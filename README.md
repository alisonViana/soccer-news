# SOCCER NEWS <img src="https://github.com/alisonViana/soccer-news/blob/refactor-kotlin/images/icon.png" alt="ícone" width="48" height="48" align="left"/>

Olá, este é o repositório da versão em Kotlin do meu aplicativo Soccer News, projeto desenvolvido durante o Santander Bootcamp Mobile Developer 
oferecido pela [DIO](https://web.dio.me/) em parceria com o Santander!

A proposta do aplicativo é se tornar uma central de notícias esportivas com foco no universo do futebol feminino, permitindo favoritar as 
notícias que mais gostou e acessá-las rapidamente ou compartilhá-las onde quiser!

 <p align="center"> 
     <img src="https://github.com/alisonViana/soccer-news/blob/refactor-kotlin/images/fragment_news.png" alt="Tela inicial" width="240" height="520" />
     <img src="https://github.com/alisonViana/soccer-news/blob/refactor-kotlin/images/fragment_favorites.png" alt="Tela favoritos" width="240" height="520" />
     <img src="https://github.com/alisonViana/soccer-news/blob/refactor-kotlin/images/share.png" alt="Tela compartilhamento" width="240" height="520" /> 
</p>

<p align="center">
     [ Imagens: tela inicial, tela de favoritos e tela de compartilhamento]
</p>


As notícias são provenientes de um repositório do GitHub criado especialmente para este projeto, que utilizando o GitHub Pages, simula uma API Rest, 
da qual o app consome os dados.

O app foi desenvolvido inicialmente em Java como forma de desafio e após ter uma versão totalmente funcional, optei por refazê-lo utilizando Kotlin, 
que é a linguagem foco dos meus estudos. Desde a primeira versão em Java, ele faz uso da biblioteca Retrofit para tratar as requisições HTTP e da 
biblioteca Room para a persistência local das notícias favoritas, na versão em Kotlin, implementei a biblioteca Koin para 
injeção de dependência e adotei a arquitetura MVVM para criar um código mais limpo, robusto e de fácil manutenção e atualização.
