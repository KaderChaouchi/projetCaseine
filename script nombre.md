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

```
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
