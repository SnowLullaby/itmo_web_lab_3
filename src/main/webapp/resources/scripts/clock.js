/*
    a
  f   b
    g
  e   c
    d
*/

const patterns = [
    ['a','b','c','d','e','f'],       // 0
    ['b','c'],                       // 1
    ['a','b','d','e','g'],           // 2
    ['a','b','c','d','g'],           // 3
    ['b','c','f','g'],               // 4
    ['a','c','d','f','g'],           // 5
    ['a','c','d','e','f','g'],       // 6
    ['a','b','c'],                   // 7
    ['a','b','c','d','e','f','g'],   // 8
    ['a','b','c','d','f','g']        // 9
];

function setDigit(digitId, number) {
    const digit = document.getElementById(digitId);
    if (!digit) return;

    digit.querySelectorAll('.segment').forEach(seg => seg.classList.remove('on'));

    if (number >= 0 && number <= 9) {
        patterns[number].forEach(segment => {
            digit.querySelector(`.segment.${segment}`).classList.add('on');
        });
    }
}

function updateClock() {
    const now = new Date();

    const h = now.getHours().toString().padStart(2, '0');
    const m = now.getMinutes().toString().padStart(2, '0');
    const s = now.getSeconds().toString().padStart(2, '0');

    setDigit('digit-h1', +h[0]);
    setDigit('digit-h2', +h[1]);
    setDigit('digit-m1', +m[0]);
    setDigit('digit-m2', +m[1]);
    setDigit('digit-s1', +s[0]);
    setDigit('digit-s2', +s[1]);

    const options = { day: 'numeric', month: 'long', year: 'numeric' };
    document.getElementById('date-display').textContent = now.toLocaleDateString('ru-RU', options);
}

setInterval(updateClock, 8000);
updateClock();