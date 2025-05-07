const express = require('express');
const fs = require('fs');
const cors = require('cors');
const http = require('http');
const { Server } = require('socket.io');

const app = express();
const server = http.createServer(app);
const io = new Server(server, {
  cors: { origin: "*" }
});

const PORT = 3000;
const fichier = './connexions.json';

app.use(cors());
app.use(express.json());

app.get('/', (req, res) => {
  res.send('✅ Serveur WebSocket actif');
});

app.get('/connexions', (req, res) => {
  let data = [];
  try {
    const contenu = fs.readFileSync(fichier, 'utf8');
    if (contenu.trim()) data = JSON.parse(contenu);
  } catch (err) {
    console.error("Erreur lecture JSON :", err.message);
  }
  res.json(data);
});

app.post('/log', (req, res) => {
  const { userId, browserId, fingerprint, timestamp } = req.body;

  let data = [];
  try {
    const contenu = fs.readFileSync(fichier, 'utf8');
    if (contenu.trim()) data = JSON.parse(contenu);
  } catch (err) {
    console.error("Erreur lecture JSON :", err.message);
  }

  const now = Date.now();
  data = data.filter(e => now - e.timestamp < 30 * 60 * 1000);
  data.push({ userId, browserId, fingerprint, timestamp });

  try {
    fs.writeFileSync(fichier, JSON.stringify(data, null, 2));
    res.sendStatus(200);
    // 🔴 Notifie tous les clients connectés qu'une mise à jour a eu lieu
    io.emit('maj_connexions');
  } catch (err) {
    console.error("Erreur écriture JSON :", err.message);
    res.status(500).send("Erreur serveur");
  }
});

// Lancement WebSocket
io.on('connection', (socket) => {
  console.log('🟢 Client connecté via WebSocket');
});

server.listen(PORT, () => {
  console.log(`✅ Serveur WebSocket actif sur http://localhost:${PORT}`);
});
