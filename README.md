# Tux-Letter-Game

Le principe du jeu sera de créer un jeu utilisant un environnement 3D, permettant l'apprentissage ludique de l'orthographe en cherchant les lettres d'un mot avec un personnage, tout cela dans un temps imparti. Le jeu pourra être configuré grâce à une page xhtml et toutes ces données seront stockées via des fichiers xml bien formés et valides ! A terme, le jeu se présentera sous la forme de 2 applications : une application serveur fournissant les mots d'un dictionnaire, et une application cliente, le jeu lui-même (voir un exemple de visuel ci-dessous).

Le jeu propose un premier menu permettant
De créer un nouveau joueur.
De charger le profil d'un joueur d'existant.
D'afficher la page des meilleurs scores.
De quitter le jeu.
Une fois le joueur actif, le jeu propose un deuxième menu permettant
D'éditer son profil.
De lancer une nouvelle partie.
De charger une partie existante.
De quitter le jeu.
Si une nouvelle partie est demandée, le jeu permet de sélectionner un niveau de difficulté. Ce niveau de difficulté sera laissé à votre appréciation, mais il concernera principalement la difficulté du mot à trouver. Jouer sur le temps imparti (facile = temps infini, moyen = 2 minutes, difficile = min ...). Plusieurs règles de jeu pourront être proposées comme "Trouver les lettres dans l'ordre", "Trouver les lettres dans le désordre"... augmentant ou diminuant ainsi la difficulté. Une autre possiblité est de proposer des conditions différentes de jeu selon l'age du joueur. A vous de voir.
Un mot est alors chargé depuis le dictionnaire (il est demandé par le jeu au serveur de dictionnaire).
Les instructions ("trouver les lettres ...") et le mot demandé sont montrés pendant quelques secondes ou tant que le joueur n'a pas appuyé sur une touche, puis la partie est effectivement lancée
Un personnage et des lettres apparaissent. Les lettres sont posées au hasard dans l'environnement (sans recouvrement). Le joueur peut déplacer son personnage au moins avec les flèches de direction (la combinaison souris+touches peut être envisagée si vous êtes à l'aise).
Le joueur est sensé chercher les lettres composant le mot, normalement dans l'ordre. Là vous pourrez décider de règles de jeu comme le fait qu'il est impossible de prendre la mauvaise lettre, ou qu'une pénalité est donnée pour chaque lettre mal choisie, ... Vous pouvez tout aussi bien ajouter des lettres excédentaires (qui ne sont pas sensées être choisies). Vous pouvez mettre une pénalité sur le dépassement de temps ...
7.Lorsque la partie est finie (soit parce que le temps imparti est fini, soit parce que les lettres ont toutes été choisies, soit ...) le jeu enregistre la partie et revient au 2eme menu.
