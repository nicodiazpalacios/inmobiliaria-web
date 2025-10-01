/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function actualizarContador(textarea) {
    var contador = document.getElementById('contador');
    if (contador && textarea) {
        contador.textContent = textarea.value.length + " / 255";
    }
}

window.addEventListener('load', function () {
    var textarea = document.getElementById('formAgregar:contenido');
    if (!textarea) {
        textarea = document.querySelector('textarea[id$="contenido"]');
    }
    if (textarea) {
        actualizarContador(textarea);
        textarea.addEventListener('input', function () {
            actualizarContador(textarea);
        });
    }
});


