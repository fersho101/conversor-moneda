document.addEventListener('DOMContentLoaded', async () => {
    try {
        const response = await fetch('http://localhost:8080/api/conversor/monedas-soportadas');
        if (!response.ok) throw new Error("Error al cargar monedas");

        const monedas = await response.json();
        const selectOrigen = document.getElementById('monedaOrigen');
        const selectDestino = document.getElementById('monedaDestino');

        monedas.forEach(moneda => {
            selectOrigen.add(new Option(moneda, moneda));
            selectDestino.add(new Option(moneda, moneda));
        });
    } catch (error) {
        console.error("Error:", error);
        document.getElementById('error').textContent = "No se pudieron cargar las monedas. Recarga la página.";
        document.getElementById('error').classList.remove('hidden');
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