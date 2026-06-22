const API_URL = 'http://localhost:8080/api/vehiculos';


async function listarVehiculos() {
    const res = await fetch(API_URL);
    const vehiculos = await res.json();
    const tabla = document.getElementById('tabla-vehiculos');
    tabla.innerHTML = '';

    vehiculos.forEach(v => {
        tabla.innerHTML += `<tr>
            <td>${v.id}</td>
            <td>${v.modelo}</td>
            <td>${v.categoria}</td>
            <td>${v.precioPorDia}</td>
            <td>${v.unidadesDisponibles}</td>
            <td><button onclick="eliminarVehiculo(${v.id})">Eliminar</button></td>
        </tr>`;
    });
}

async function guardarVehiculo() {
    const nuevoVehiculo = {
        modelo: document.getElementById('modelo').value,
        categoria: document.getElementById('categoria').value,
        precioPorDia: parseFloat(document.getElementById('precio').value),
        unidadesDisponibles: parseInt(document.getElementById('unidades').value)
    };

    await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(nuevoVehiculo)
    });

    listarVehiculos();
}


async function eliminarVehiculo(id) {
    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    listarVehiculos();
}


listarVehiculos();