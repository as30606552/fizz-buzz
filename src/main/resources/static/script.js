const serverUrl = 'http://localhost:8080'
const simpleGameUrl = serverUrl + '/simple'
const restartUrl = simpleGameUrl + '/restart'
const turnUrl = simpleGameUrl + '/turn'

const acceptButton = document.getElementById('accept-button')
const restartButton = document.getElementById('restart-button')
const turnInput = document.getElementById('turn-input')
const logContainer = document.getElementById('log-container')

restartButton.onclick = restartGame
acceptButton.onclick = performTurn
turnInput.addEventListener('keypress', async e => {
    if (e.key === 'Enter' && isAcceptButtonEnabled()) {
        await performTurn()
    }
})

async function restartGame() {
    await fetch(restartUrl, {method: 'POST'});
    enableAcceptButton()
    clearLog()
}

async function performTurn() {
    const turn = getTurn()
    if (turn === '') {
        return
    }

    clearTurn()
    logSuccess('Turn: ' + turn)

    const response = await fetch(turnUrl + '/' + turn)
    const code = response.status
    const isTurnValid = await response.json()
    if (code !== 200 || !isTurnValid) {
        logError('Game over!')
        disableAcceptButton()
    }
}

function getTurn() {
    return turnInput.value
}

function clearTurn() {
    turnInput.value = '';
}

function logSuccess(text) {
    logMessage(text, 'success-label')
}

function logError(text) {
    logMessage(text, 'error-label');
}

function logMessage(text, style) {
    const turnLabel = document.createElement('label')

    turnLabel.innerText = text
    turnLabel.classList.add(style, 'log-label')

    logContainer.appendChild(turnLabel)
}

function clearLog() {
    while (logContainer.firstChild) {
        logContainer.removeChild(logContainer.firstChild)
    }
}

function disableAcceptButton() {
    acceptButton.setAttribute('disabled', 'true')
}

function enableAcceptButton() {
    acceptButton.removeAttribute('disabled')
}

function isAcceptButtonEnabled() {
    return acceptButton.getAttribute('disabled') === null
}
