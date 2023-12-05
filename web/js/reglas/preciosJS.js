
        let botonMostrarClick = false;

        //Funcion que muestra la pestaña seleccionada
        function mostrarPestaña() {

            let seleccionMostrar = document.getElementById('selectMostrar');
            let valorseleccionado = seleccionMostrar.value;

            if (valorseleccionado === 'mostrar')
            {
                for (let i = 1; i <= 3; i++)
                {

                    document.getElementById('pestaña' + i).style.display = 'inline';
                }
            } else {
                for (let i = 1; i <= 3; i++)
                {
                    if (valorseleccionado !== ('mostrar' + i)) {

                        document.getElementById('pestaña' + i).style.display = 'none';
                    } else if (valorseleccionado === ('mostrar' + i))
                    {
                        document.getElementById('pestaña' + i).style.display = 'inline';
                    }
                }
            }
        }
        // Función para guardar la selección en sessionStorage
        function guardarSeleccion() {
            let seleccionMostrar = document.getElementById('selectMostrar');
            let valorseleccionado = seleccionMostrar.value;
            sessionStorage.setItem('SelectGuardadoMostrar', valorseleccionado);

            botonMostrarClick = true;
            mostrarPestaña();
        }

        // Al cargar la página, restaura el valor seleccionado si está almacenado
        window.onload = function () {

            let selectMostrar = document.getElementById('selectMostrar');
            valorGuardadoMostrar = sessionStorage.getItem('SelectGuardadoMostrar');

            if (valorGuardadoMostrar) {
                selectMostrar.value = valorGuardadoMostrar;
            } else {
                selectMostrar.value = 'mostrar';
            }
        };

        // Limpiar la selección guardada al abandonar la página si no se hizo clic en el botón
        window.addEventListener('beforeunload', function (event) {
            if (!botonMostrarClick) {
                sessionStorage.removeItem('SelectGuardadoMostrar');
            }
        }
        );

        mostrarPestaña();