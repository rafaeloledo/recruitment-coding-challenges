<?php
require_once 'config.php';
require_once 'functions.php';

$competitions = getCompetitions();
$selectedComp = null;
$teamData = null;
$error = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['competition'])) {
        $selectedComp = $competitions[$_POST['competition']] ?? null;
        if (!$selectedComp) $error = 'Competição inválida';
    } elseif (isset($_POST['team_search'])) {
        $teamName = htmlspecialchars(trim($_POST['team_name']));
        if (!empty($teamName)) {
            $teamData = searchTeamMatches($teamName);
            if (!$teamData) $error = 'Time não encontrado';
        } else {
            $error = 'Digite um nome de time';
        }
    }
}
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Informações de Futebol</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-5">
  <h1 class="mb-4">Sistema de Jogos de Futebol ⚽</h1>

  <div class="row">
    <div class="col-md-6">
      <form method="post">
        <div class="mb-3">
          <select name="competition" class="form-select">
            <?php foreach ($competitions as $key => $value): ?>
              <option value="<?= $key ?>">
                <?= $value['name'] ?>
              </option>
            <?php endforeach; ?>
          </select>
        </div>
        <button type="submit" class="btn btn-primary">Carregar Jogos</button>
      </form>
    </div>
    
    <div class="col-md-6">
      <form method="post">
        <div class="input-group">
          <input type="text" name="team_name" class="form-control" placeholder="Buscar time...">
          <button type="submit" name="team_search" class="btn btn-success">Buscar</button>
        </div>
      </form>
    </div>
  </div>

  <?php if ($error): ?>
    <div class="alert alert-danger mt-4"><?= $error ?></div>
  <?php endif; ?>

  <?php if ($selectedComp): ?>
    <h2 class="mt-5"><?= $selectedComp['name'] ?></h2>
    
    <div class="row mt-4">
      <div class="col-md-6">
        <h4>Próximos Jogos</h4>
        <?php $matches = getNextMatches($selectedComp['id']); ?>
        <?php if ($matches): ?>
          <?php foreach ($matches as $match): ?>
            <div class="card mb-3">
              <div class="card-body">
                <h5 class="card-title">
                  <?= $match['homeTeam']['name'] ?> vs <?= $match['awayTeam']['name'] ?>
                </h5>
                <p class="card-text">
                  <?= (new DateTime($match['utcDate']))->format('d/m/Y H:i') ?>
                  <?php if ($match['venue']): ?>
                    <br><small><?= $match['venue'] ?></small>
                  <?php endif; ?>
                </p>
              </div>
            </div>
          <?php endforeach; ?>
        <?php else: ?>
          <div class="alert alert-info">Nenhum jogo agendado</div>
        <?php endif; ?>
      </div>

      <div class="col-md-6">
        <h4>Últimos Resultados</h4>
        <?php $results = getLatestResults($selectedComp['id']); ?>
        <?php if ($results): ?>
          <?php foreach ($results as $match): ?>
            <div class="card mb-3">
              <div class="card-body">
                <h5 class="card-title">
                  <?= $match['homeTeam']['name'] ?> 
                  <span class="badge bg-primary">
                    <?= $match['score']['fullTime']['home'] ?? '-' ?>x<?= $match['score']['fullTime']['away'] ?? '-' ?>
                  </span>
                  <?= $match['awayTeam']['name'] ?>
                </h5>
                <p class="card-text">
                  <?= (new DateTime($match['utcDate']))->format('d/m/Y') ?>
                </p>
              </div>
            </div>
          <?php endforeach; ?>
        <?php else: ?>
            <div class="alert alert-info">Nenhum resultado recente</div>
        <?php endif; ?>
      </div>
    </div>
  <?php endif; ?>

  <?php if ($teamData): ?>
    <h2 class="mt-5"><?= $teamData['team']['name'] ?></h2>
    
    <div class="row mt-4">
      <div class="col-md-6">
        <h4>Próximos Jogos</h4>
        <?php if ($teamData['upcoming']): ?>
          <?php foreach ($teamData['upcoming'] as $match): ?>
            <div class="card mb-3">
              <div class="card-body">
                <h5 class="card-title">
                  <?= $match['homeTeam']['name'] ?> vs <?= $match['awayTeam']['name'] ?>
                </h5>
                <p class="card-text">
                  <?= (new DateTime($match['utcDate']))->format('d/m/Y H:i') ?>
                </p>
              </div>
            </div>
          <?php endforeach; ?>
        <?php else: ?>
          <div class="alert alert-info">Nenhum jogo agendado</div>
        <?php endif; ?>
      </div>

      <div class="col-md-6">
        <h4>Últimos Jogos</h4>
        <?php if ($teamData['past']): ?>
          <?php foreach ($teamData['past'] as $match): ?>
            <div class="card mb-3">
              <div class="card-body">
                <h5 class="card-title">
                  <?= $match['homeTeam']['name'] ?> 
                  <span class="badge bg-primary">
                      <?= $match['score']['fullTime']['home'] ?? '-' ?>x<?= $match['score']['fullTime']['away'] ?? '-' ?>
                  </span>
                  <?= $match['awayTeam']['name'] ?>
                </h5>
                <p class="card-text">
                    <?= (new DateTime($match['utcDate']))->format('d/m/Y') ?>
                </p>
              </div>
            </div>
          <?php endforeach; ?>
        <?php else: ?>
            <div class="alert alert-info">Nenhum jogo recente</div>
        <?php endif; ?>
      </div>
    </div>
  <?php endif; ?>
</body>

</html>
