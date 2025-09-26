fetch('./data.json')
  .then(r => r.json())
  .then(({ message, version }) => {
    document.getElementById('message').textContent = message;
    document.getElementById('version').textContent = version;
  })
  .catch(() => {
    document.getElementById('message').textContent = 'Failed to load data.json';
  });
