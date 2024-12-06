document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const tituloInput = document.getElementById("titulo");
    const contenidoInput = document.getElementById("contenido");
    const tipoSelect = document.getElementById("tipo");
    const limpiarBtn = document.querySelector("button[type='button']");
    const resultadosTabla = document.querySelector(".results tbody");

    form.addEventListener("submit", (event) => {
        event.preventDefault(); // Evita el envío del formulario por defecto

        // Obtén los valores del formulario
        const titulo = tituloInput.value.trim();
        const contenido = contenidoInput.value.trim();
        const tipo = tipoSelect.value;

        // Construir el cuerpo de la solicitud
        const datos = { titulo, contenido, tipo };

        // Realizar una solicitud POST al backend
        fetch("http://tu-servidor.com/api/busqueda", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(datos),
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Error en la solicitud: " + response.statusText);
                }
                return response.json(); // Procesa la respuesta como JSON
            })
            .then((resultados) => {
                // Limpia resultados anteriores
                resultadosTabla.innerHTML = "";

                // Muestra los resultados obtenidos del backend
                resultados.forEach((resultado) => {
                    const fila = document.createElement("tr");
                    fila.innerHTML = `
                        <td>${resultado.codigo}</td>
                        <td>${resultado.titulo}</td>
                        <td>${resultado.contenido}</td>
                        <td>${resultado.tipo}</td>
                        <td>${resultado.ubicacion}</td>
                        <td>${resultado.cantidad}</td>
                        <td>${resultado.disponibles}</td>
                        <td>${resultado.fecha}</td>
                    `;
                    resultadosTabla.appendChild(fila);
                });
            })
            .catch((error) => {
                console.error("Error:", error);
                alert("Hubo un problema al procesar la solicitud.");
            });
    });

    limpiarBtn.addEventListener("click", () => {
        tituloInput.value = "";
        contenidoInput.value = "";
        tipoSelect.value = "";
        resultadosTabla.innerHTML = ""; // Limpia los resultados mostrados
    });
});


// Datos de prueba:
// Código | Título | Contenido | Tipo | Ubicación | Cantidad | Disponibles | Fecha de Registro
// 05098  | Blade | CD        | Cd  | Estante 5 | 100      | 2           | 02-07-2005
