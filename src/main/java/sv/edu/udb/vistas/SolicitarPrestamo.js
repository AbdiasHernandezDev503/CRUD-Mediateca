document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");

    form.addEventListener("submit", function(event) {
        event.preventDefault(); 
        
        const usuario = document.getElementById("usuario").value;
        const material = document.getElementById("material").value;
        const fecha = document.getElementById("fecha").value;
        const estado = document.getElementById("estado").value;

        if (!usuario || !material || !fecha || !estado) {
            alert("Todos los campos son obligatorios.");
            return;
        }

        // Datos a enviar a la API simulada
        const solicitud = {
            usuario: usuario,
            material: material,
            fecha: fecha,
            estado: estado
        };

        // Hacer la solicitud POST a una API simulada
        fetch('https://jsonplaceholder.typicode.com/posts', { // URL de la API simulada
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(solicitud) // Enviar los datos del formulario como JSON
        })
        .then(response => response.json()) // Procesar la respuesta en formato JSON
        .then(data => {
            console.log("Respuesta de la API:", data); // Aquí puedes ver la respuesta que devuelve la API
            alert("¡Solicitud de préstamo enviada con éxito!");
        })
        .catch(error => {
            console.error('Error al enviar la solicitud:', error);
            alert("Hubo un error al enviar la solicitud. Inténtalo nuevamente.");
        });

        // Limpiar 
        form.reset();
    });
});
