CHAOUCHI Abdelkader
WETTSTEIN Emeline

## Compte rendu semaine 12/05

Lors de cette semaine, sous avons produit le script pour montrer le nom de l'utilisateur ainsi que le cookie de la session avec le code suivant : 

```html
<p id="info"></p>
<p><br></p>
<script>
function getCookie(name) {
    const value = "; " + document.cookie;
    const parts = value.split("; " + name + "=");
    if (parts.length === 2) return parts.pop().split(";").shift();
    return null;
}
function getUserFullName() {
    const user = document.querySelector('.logininfo a');
    if (user) {
        return user.innerText.trim();
    } else {
        return "Utilisateur non trouvé";
    }
}
function getDeviceFingerprint() {
    return {
        userAgent: navigator.userAgent,
        screen: screen.width + "x" + screen.height,
        language: navigator.language
    };
}
let cookie = getCookie("MoodleSession");
let username = getUserFullName();
let fingerprint = getDeviceFingerprint();

let result = "<h2>Nom : " + username + "<br>" +
             "Cookie : " + (cookie || "Non trouvé") + "<br>" +
             "User Agent : " + fingerprint.userAgent + "<br>" +
             "Résolution : " + fingerprint.screen + "<br>" +
             "Langue : " + fingerprint.language + "</h2>";

document.getElementById("info").innerHTML = result;
</script>
```

Comme le cookie récupéré de cette façon est différent entre machines, nous avons décidé de récupérer ce cookie pour les requête vers le serveur, plutôt que générer un code unique pour le vpl.

Nous avons également commencer à créer un serveur springboot, pour récupérer les requêtes. Cela n'est pas encore terminé.

Comme nous aurons la possibilité de récupérer l'adresse ip de la requête, la combinaison unique des éléments (id du vpl, nom de l'étudiant, cookie et adresse ip), il faudra beaucoup de difficulté à qualqu'un de contourner cette solution.

La semaine prochaine, nous comptons continuer à travailler sur le serveur springboot et établir des règles afin d'envoyer des requêtes du vpl vers le serveur sans surcharger celui-ci.

