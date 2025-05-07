# Script pour renvoyer un code aléatoire

## Méthode 1 :

`
<p id="nombre"></p>
<script>
  let $i = Math.floor(Math.random() * 101);
  document.getElementById("nombre").innerHTML = "<h2>" + $i + "</h2>";
</script>
`
