## Compte-rendu semaine 5

### Lundi :

- débuggage pour recevoir les requête dans springboot depuis vpl, la requête était "reçue" et montrée dans la console mais n'était pas ajouté à la liste d'étudiants.
    - en utilisant les fonctions de synchronosation pour la liste on peut faire en sorte que les vpl et les requêtes interne utilisent la même liste de session.
### Mardi :

- fonction filtre
- fonctions d'exportation (avec eet sans filtre)
- vpl : continuation de la recherche pour régler un problème de numéros de version(au ca où deux requêtes sont faites en même temps et donc avec le même numéro)