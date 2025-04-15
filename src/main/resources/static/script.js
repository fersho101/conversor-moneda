document.addEventListener('DOMContentLoaded', () => {
    const toggleFiltro = document.getElementById('toggleFiltro');
    const selectOrigen = document.getElementById('monedaOrigen');
    const selectDestino = document.getElementById('monedaDestino');

    // Carga inicial de monedas (todas)
    cargarMonedas(false);

    // Evento para el toggle
    toggleFiltro.addEventListener('change', (e) => {
        cargarMonedas(e.target.checked);
    });

    async function cargarMonedas(soloComunes) {
        try {
            // Mostrar estado de carga
            selectOrigen.innerHTML = '<option value="" disabled selected>Cargando...</option>';
            selectDestino.innerHTML = '<option value="" disabled selected>Cargando...</option>';

            // Fetch a la API
            const response = await fetch(`http://localhost:8080/api/conversor/monedas-soportadas?soloComunes=${soloComunes}`);
            if (!response.ok) throw new Error("Error al cargar monedas");

            const monedas = await response.json();

            // Llenar selects
            llenarSelect(selectOrigen, monedas);
            llenarSelect(selectDestino, monedas);

        } catch (error) {
            console.error("Error:", error);
            selectOrigen.innerHTML = '<option value="" disabled selected>Error al cargar</option>';
            document.getElementById('error').textContent = "Error al cargar monedas. Recarga la página.";
            document.getElementById('error').classList.remove('hidden');
        }
    }

    function llenarSelect(selectElement, monedas) {
        selectElement.innerHTML = '';

        // Opción por defecto
        const defaultOption = document.createElement('option');
        defaultOption.value = "";
        defaultOption.disabled = true;
        defaultOption.selected = true;
        defaultOption.textContent = "Selecciona moneda";
        selectElement.appendChild(defaultOption);

        // Opciones de monedas
        monedas.forEach(moneda => {
            const option = document.createElement('option');
            option.value = moneda;
            option.textContent = moneda;
            selectElement.appendChild(option);
        });
    }
});

document.getElementById('conversorForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const monedaOrigen = document.getElementById('monedaOrigen').value;
    const monedaDestino = document.getElementById('monedaDestino').value;
    const cantidad = document.getElementById('cantidad').value;



    try {
        const response = await fetch('http://localhost:8080/api/conversor/convertir', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                monedaOrigen,
                monedaDestino,
                cantidad: parseFloat(cantidad)
            })
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Error en la conversión");
        }

        const data = await response.json();

        document.getElementById('cantidadConvertida').textContent =
            `${cantidad} ${monedaOrigen} = ${data.cantidadConvertida} ${monedaDestino}`;
        document.getElementById('tasaCambio').textContent = data.tasaCambio;

        document.getElementById('resultado').classList.remove('hidden');
        document.getElementById('error').classList.add('hidden');
    } catch (error) {
        document.getElementById('error').textContent = `❌ Error: ${error.message}`;
        document.getElementById('error').classList.remove('hidden');
        document.getElementById('resultado').classList.add('hidden');
    }
});

