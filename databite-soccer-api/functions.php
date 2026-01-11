<?php
function makeApiRequest($endpoint) {
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, API_BASE . $endpoint);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, [
        'X-Auth-Token: ' . API_KEY
    ]);
    $response = curl_exec($ch);
    $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    curl_close($ch);

    if ($httpCode != 200) return null;
    return json_decode($response, true);
}

function getCompetitions() {
    return [
        'BSA' => ['id' => 2013, 'name' => 'Brasileirão Série A'],
        'PL' => ['id' => 2021, 'name' => 'Premier League'],
        'LL' => ['id' => 2014, 'name' => 'La Liga']
    ];
}

function getNextMatches($competitionId) {
    $data = makeApiRequest("competitions/{$competitionId}/matches?status=SCHEDULED");
    if (!$data || !isset($data['matches'])) return [];
    usort($data['matches'], fn($a, $b) => strtotime($a['utcDate']) - strtotime($b['utcDate']));
    return array_slice($data['matches'], 0, 10);
}

function getLatestResults($competitionId) {
    $data = makeApiRequest("competitions/{$competitionId}/matches?status=FINISHED");
    if (!$data || !isset($data['matches'])) return [];
    usort($data['matches'], fn($a, $b) => strtotime($b['utcDate']) - strtotime($a['utcDate']));
    return array_slice($data['matches'], 0, 10);
}

function searchTeamMatches($teamName) {
    $teamData = makeApiRequest("teams?name=" . urlencode($teamName));
    if (!$teamData || empty($teamData['teams'])) return null;
    
    $team = $teamData['teams'][0];
    $upcoming = makeApiRequest("teams/{$team['id']}/matches?status=SCHEDULED");
    $past = makeApiRequest("teams/{$team['id']}/matches?status=FINISHED&limit=5");
    
    return [
        'team' => $team,
        'upcoming' => $upcoming['matches'] ?? [],
        'past' => $past['matches'] ?? []
    ];
}
?>
