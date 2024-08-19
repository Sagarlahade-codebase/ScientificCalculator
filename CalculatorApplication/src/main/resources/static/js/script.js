let display = document.getElementById('display');
let historyList = document.getElementById('history-list');

function appendToDisplay(value) {
    if (display.innerText === '0' && value !== '.') {
        display.innerText = value;
    } else {
        display.innerText += value;
    }
}

function clearDisplay() {
    display.innerText = '0';
}

function calculate() {
    try {
        let result = eval(display.innerText);
        if (result !== undefined) {
            addToHistory(display.innerText + ' = ' + result);
            display.innerText = result;
            saveHistory(display.innerText + ' = ' + result);
        }
    } catch (e) {
        display.innerText = 'Error';
    }
}

function addToHistory(entry) {
    let listItem = document.createElement('li');
    listItem.innerText = entry;
    historyList.insertBefore(listItem, historyList.firstChild);

    if (historyList.children.length > 50) {
        historyList.removeChild(historyList.lastChild);
    }
}

function saveHistory(entry) {
    fetch('/api/history', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ expression: entry.split(' = ')[0], result: entry.split(' = ')[1] })
    });
}

function loadHistory() {
    fetch('/api/history')
        .then(response => response.json())
        .then(data => {
            data.forEach(entry => {
                addToHistory(entry.expression + ' = ' + entry.result);
            });
        });
}

window.onload = function() {
    loadHistory();
};
