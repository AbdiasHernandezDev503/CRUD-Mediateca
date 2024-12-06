document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");

    form.addEventListener("submit", function(event) {
        event.preventDefault(); // Previene el envío del formulario de manera predeterminada
        
        // Obtener los valores de los campos
        const usuario = document.getElementById("usuario").value;
        const material = document.getElementById("material").value;
        const fecha = document.getElementById("fecha").value;
        const estado = document.getElementById("estado").value;

        // Verificar que los campos no estén vacíos
        if (!usuario || !material || !fecha || !estado) {
            alert("Todos los campos son obligatorios.");
            return;
        }

        // Aquí podrías enviar los datos a un servidor o realizar algún procesamiento
        console.log("Solicitud de préstamo:");
        console.log(`Usuario: ${usuario}`);
        console.log(`Material: ${material}`);
        console.log(`Fecha de Préstamo: ${fecha}`);
        console.log(`Estado: ${estado}`);
        
        // Limpiar el formulario después de procesarlo
        form.reset();
        alert("¡Solicitud de préstamo enviada con éxito!");
    });
});
