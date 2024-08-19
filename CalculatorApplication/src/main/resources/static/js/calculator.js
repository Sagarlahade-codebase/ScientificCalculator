// calculator.js

function appendToDisplay(value) {
    document.getElementById('display').value += value;
}

function clearDisplay() {
    document.getElementById('display').value = '';
}

function backspace() {
    let display = document.getElementById('display');
    display.value = display.value.slice(0, -1);
}

function calculateResult() {
    var display = document.getElementById('display');
    var expression = display.value;
    var angleUnit = document.getElementById('angle-unit').value;

    // Replace square root and power with corresponding functions
    expression = expression.replace(/sqrt\(/g, 'Math.sqrt(');
    expression = expression.replace(/pow\(/g, 'Math.pow(');
    expression = expression.replace(/log\(/g, 'Math.log10(');
    expression = expression.replace(/ln\(/g, 'Math.log(');

    // Convert trigonometric functions based on angle unit
    if (angleUnit === 'deg') {
        expression = expression.replace(/sin\(/g, 'Math.sin(degToRad(');
        expression = expression.replace(/cos\(/g, 'Math.cos(degToRad(');
        expression = expression.replace(/tan\(/g, 'Math.tan(degToRad(');
    } else {
        expression = expression.replace(/sin\(/g, 'Math.sin(');
        expression = expression.replace(/cos\(/g, 'Math.cos(');
        expression = expression.replace(/tan\(/g, 'Math.tan(');
    }

    // Add closing parentheses for radToDeg conversions
    expression = expression.replace(/degToRad\(/g, 'degToRad(');
    expression = expression.replace(/radToDeg\(/g, 'radToDeg(');

    // Evaluate expression
    try {
        var result = eval(expression);
        display.value = result;
    } catch (e) {
        display.value = 'Error';
    }
}

function degToRad(degrees) {
    return degrees * (Math.PI / 180);
}

function radToDeg(radians) {
    return radians * (180 / Math.PI);
}
