const typewriter = document.getElementById('typewriter');
const text = document.getElementById("content").innerHTML;

let index = 0;

function type() {
    if (index < text.length) {
        typewriter.innerHTML = text.slice(0, index) + '<span class="blinking-cursor">|</span>';
        index++;
        setTimeout(type, Math.random() * 2 + 50);
    } else {
        typewriter.innerHTML = text.slice(0, index) + '<span class="blinking-cursor">|</span>';
    }
}

type();
