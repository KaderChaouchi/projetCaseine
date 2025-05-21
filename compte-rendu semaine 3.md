## Compte-rendu semaine 3

### Lundi :

- Création de fonction equals dans classe Etudiant pour qua la fonction contains de ArrayList fonctionne correctement.
- Problème récurrent : lorsque on fait une requete, les valeur de id_vpl et cookie sont null --> changement de Post vers get pour la méthode recharge.
- test d'envoi de requête vers le serveur springboot depuis vpl
- en copiant directement l'url ci-dessous :
http://localhost:8080/recharge?nom_etud=ABC3&id_vpl=voici2&cookie=azerty123
 cela fonctionne, mais en lançant depuis une autre source comme le vpl, cela ne fonctionne pas.

### Mardi

- relevé des adresses ip des salles E37 et E38 :
    - E37 : 10.8.11.1
    - E38 : 10.8.12.1
- toujours pas d'avancée sur le coté serveur, car les requêtes venant de l'exterieur ne sont pas prises en compte.
- avancée, peut recevoir les requêtes depuis une autre machine de la même salle, avec nom, id vpl et cookie.

### Mercredi

- changement de @Controller vers @RestController
- affichage en local depuis le vpl OK
- affichage apr_s reception depuis un autre pc 
- création d'une url tunnel qui rend possible l'envoi depuis d'autre pc hors fac avec localtunnel OK

### Jeudi

- test avec ngrok

### semaine prochaine

- faire en sorte que le serveur soit en version portable et soit facilement lancable avec un fichier executable
- créer un algorithme qui peut analyser les données et montrer qui est en train de tricher en rouge, avec un historique et des explications pour les éventuels changements (changement de navigateur ou de machines en différent cas).



