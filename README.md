# Juego36

<h2>Descripcion</h2>
Este juego tiene consiste en un tablero 6x6 que contiene los numeros del 1 al 36. Cuando tu eliges un número, el oponente debe elegir un múltiplo o un divisor de este.

Así pues si eliges el 10, el jugador oponente puede seguir con : {1, 2, 5, 20, 30}.

Las reglas son :
  - La restricción de tirar con un múltiplo o divisor del oponente.
  - La primera tirada tienes que elegir un número par.
  - No se puede volver a elegir un numero que se ha elegido antes.
  - Gana el jugador que consigue que el siguiente jugador no pueda tirar. 
  (Ejemplo : Si tirase un 1, el oponente podría tirar el 23, que al ser primo me deja a mi sin poder tirar, y por tanto gana el oponente).

El proyecto aún no está terminado.

<h2>Algoritmo utilizado como consola</h2>
Se ha utilizado como oponente un algoritmo que se basa en el análisis por casos, realizando siempre la mejor jugada ante un tablero x. Esto implica que en la primera tirada el algoritmo se demore un poco menos de un minuto, pero el resto es bastante rápido.

<h2>Imagenes</h2>

![image](https://user-images.githubusercontent.com/78687102/167120830-8a88a8cd-ad4d-4feb-a962-f6a405c9d44c.png)

![image](https://user-images.githubusercontent.com/78687102/167121087-80bf8b7a-b89a-4c6f-a59c-1ee1905f50d7.png)
