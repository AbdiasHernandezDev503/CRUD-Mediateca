// Espera a que la página se cargue completamente
document.addEventListener("DOMContentLoaded", () => {
    // Selecciona elementos del formulario
    const form = document.querySelector("form");
    const tituloInput = document.getElementById("titulo");
    const contenidoInput = document.getElementById("contenido");
    const tipoSelect = document.getElementById("tipo");
    const limpiarBtn = document.querySelector("button[type='button']");
    const resultadosTabla = document.querySelector(".results tbody");

    // Evento para manejar el envío del formulario
    form.addEventListener("submit", (event) => {
        event.preventDefault(); // Previene el comportamiento por defecto del formulario

        // Obtén los valores del formulario
        const titulo = tituloInput.value.trim();
        const contenido = contenidoInput.value.trim();
        const tipo = tipoSelect.value;

        // Simula una búsqueda (aquí puedes integrar con un backend o API)
        const resultados = buscarMateriales(titulo, contenido, tipo);

        // Limpia resultados anteriores
        resultadosTabla.innerHTML = "";

        // Muestra los resultados en la tabla
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
    });

    // Evento para manejar el botón "Limpiar filtros"
    limpiarBtn.addEventListener("click", () => {
        tituloInput.value = "";
        contenidoInput.value = "";
        tipoSelect.value = "";
        resultadosTabla.innerHTML = ""; // Limpia los resultados mostrados
    });
});

// Función que simula una búsqueda de materiales (puedes sustituirla con datos reales)
function buscarMateriales(titulo, contenido, tipo) {
    // Datos de ejemplo
    const materiales = [
        {
            codigo: "00098",
            titulo: "Blair Witch",
            contenido: "100pg",
            tipo: "Libro",
            ubicacion: "Estante 3",
            cantidad: 50,
            disponibles: 20,
            fecha: "08-01-2002",
        },
        {
            codigo: "05098",
            titulo: "Blade",
            contenido: "CD",
            tipo: "Cd",
            ubicacion: "Estante 5",
            cantidad: 100,
            disponibles: 2,
            fecha: "02-07-2005",
        },
    ];

    // Filtrar materiales según los valores ingresados
    return materiales.filter((material) => {
        return (
            (!titulo || material.titulo.toLowerCase().includes(titulo.toLowerCase())) &&
            (!contenido || material.contenido.toLowerCase().includes(contenido.toLowerCase())) &&
            (!tipo || material.tipo.toLowerCase() === tipo.toLowerCase())
        );
    });
}
