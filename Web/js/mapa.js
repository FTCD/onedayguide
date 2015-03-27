/**
 * @author Iban
 */
var idiomaSel = "es";//Idioma seleccionado en la página.

var map = null;
var buscarUsuarios = true;

//Variables para capturar la localidad introducida en la página de inicio.

var localidad = unescape(getUrlVars()["localidad"]);//Obtenemos la localidad introducida en la página de inicio.
var latitudLocalidad;//Aqui dejaremos la latitud de la localidad introducida por el usuario.
var longitudLocalidad;//Aqui dejaremos la longitud de la localidad introducida por el usuario.

//Para localizar la localidad en el mapa.
var geocoder = new google.maps.Geocoder();

//1- Se carga el mapa.
google.maps.event.addDomListener(window,'load',initMap);

//2- Después de ejecutar el código jquery inicializa el mapa.

/* Función que inicializa el mapa de google con las opciones de configuración
   que le indiquemos. */
function initMap(){
	
	//Asignamos la localidad introducida por el usuario.
	document.getElementById("inputSearch").value = localidad;

	//Opciones de configuración del mapa
	var mapOptions={
		center:new google.maps.LatLng(0,0),
		zoom:1,
		mapTypeId:google.maps.MapTypeId.ROADMAP};

	//Constructor del mapa	
	map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

  //Listener que se ejecuta cuando finaliza un movimiento en el map, tanto
  //zoom como cambio de posición
  /*google.maps.event.addListener(map, 'idle', function() {

    //Coordenadas de dos de los bordes del mapa
    var bounds =  map.getBounds();
    //Coordenada noroeste
    var ne = bounds.getNorthEast();
    //Coordenada suroeste
    var sw = bounds.getSouthWest();

    alert("CAMBIO O MOVIMIENTO EN EL MAPA");

  });*/
		
}

//3- Ejecuta el showlocation que es quien realiza la llamada a callback.

/* Función que carga el mapa de google y realiza la llamada callback. */
function showlocation(){

	geocoder.geocode( { 'address': localidad}, function(results, status) {
	    if (status == google.maps.GeocoderStatus.OK) {
	    	latitudLocalidad = results[0].geometry.location.lat();
	    	longitudLocalidad = results[0].geometry.location.lng();
	    } else {
	    	if(status == google.maps.GeocoderStatus.ZERO_RESULTS){
	    		alert("La dirección introducida no existe");
	    	}else{
	    		alert('Geocode was not successful for the following reason: ' + status);
	    	}
	    }
  	});

	navigator.geolocation.getCurrentPosition(callback);
	
}

//4- Se ejecuta el callback.

/* Función que localiza la localidad introducida en la pantalla de inicio. Además de esto
   llama a la función que obtiene la lista de usuarios para marcar en el mapa. */
function callback(){

	var homeLatLong=new google.maps.LatLng(latitudLocalidad,longitudLocalidad);

  	/*var marker = new MarkerWithLabel({
       	position: homeLatLong,
       	map: map,
       	draggable: true,
      	raiseOnDrag: true,
       	labelContent: "",
       	labelAnchor: new google.maps.Point(60, 0),
       	labelClass: "labelMarker", // the CSS class for the label
       	labelInBackground: false
    });*/
	
	//Solo realizamos la búsqueda de usuarios una vez ya que de momento se
	//buscan todos. Si se cambia esto y se realizan búsquedas mas concretas
	//hay que quitar esto.
	if(buscarUsuarios){

		//Obtenemos los datos de usuarios mediante el servicio web y
		//se añaden las posiciones de los distintos usuarios de la zona
		fObtenerListaUsuarios(map);

		anadirListenerInputBuscar();

	}
	
	map.setZoom(9);
	map.setCenter(homeLatLong);

  /*var bounds = map.getBounds();
  var ne = bounds.getNorthEast();
  var sw = bounds.getSouthWest();

  alert(ne);
  alert(sw);

  var latLong = new google.maps.LatLng(ne.lat(), ne.lng());
  bounds.extend(latLong);

  var markers = [];
  //Crear el marcador
  var marker = new google.maps.Marker({
      position: latLong,
      map: map,
      //icon: image,
      //shape: shape,
      title: "PRUEBA"
  });

  //Se añade al array de markers para agrupar
  markers.push(marker);

  latLong = new google.maps.LatLng(sw.lat(), sw.lng());
  bounds.extend(latLong);

  //Crear el marcador
  marker = new google.maps.Marker({
      position: latLong,
      map: map,
      //icon: image,
      //shape: shape,
      title: "PRUEBA 2"
  });
  
  //Se añade al array de markers para agrupar
  markers.push(marker);

  //Se agrupan los markers
  var markerCluster = new MarkerClusterer(map, markers);*/
	
}

/* Función que añade un listener al inputSearch de cabecera para que ejecute
   la búsqueda cuando se pulse sobre el intro. */
function anadirListenerInputBuscar(){

	document.getElementById('inputSearch').addEventListener('keypress', function(event) {
		/* Se captura la tecla intro */
        if (event.keyCode == 13) {
        	fLocalizarLocalidad();
        }
    });

}

/* Función que llama al método showlocation para volver a realizar la búsqueda de
   localidades de google. */
function fLocalizarLocalidad(){

	if(document.getElementById('inputSearch').value!=""){

		localidad = document.getElementById('inputSearch').value;
		//buscarUsuarios = false;
		showlocation();

	}

}

/* Función que obtiene la lista de usuarios de la base de datos. Para
   ello realiza una llamada ajax al servicio web correspondiente. */
function fObtenerListaUsuarios(map){
	
	var users = new Array();
	try {
    
      $.ajax({
        type: "GET",
        url: dir + "/OneDayGuide/rest/usuario/getUsers",
        dataType: "jsonp",
        contentType: "application/json; charset=utf-8",
        timeout: 60000,
        success: function(data){
            for (var i = 0; i < data.length; i++) {		                	
    					var userName = data[i].userName;
    					var latitud = data[i].latitud;
    					var longitud = data[i].longitud;
    					var email = data[i].email;
              var aIdiomasNivel = data[i].listaIdiomas;
    					var aActividadesDisponibilidad = data[i].listaActividades;
    					users[i] = [userName, latitud, longitud, email, aIdiomasNivel, aActividadesDisponibilidad, i];
            }
            fAnadirMarkers(map, users);
            cargarInformacionUsuarios(users);
        },
        failure: function(xhr, ajaxOptions, thrownError){
          alert(thrownError);           
        },
        cache: false,
        async: false
      });
        
    }catch (e) {
        alert('failed to call web service. Error: ' + e);
    }

}

/* Función que llama realiza la llamada a la función que pone los marcadores en el mapa. */
function fAnadirMarkers(map, users){
	
	//Se añaden las posiciones de los distintos usuarios de la zona.
	setMarkers(map, users);

}		

/* Función que pone los marcadores de los usuarios en el mapa. */ 
function setMarkers(map, users) {
	
	var markers = [];	
  	// Add markers to the map

	// Marker sizes are expressed as a Size of X,Y
	// where the origin of the image (0,0) is located
	// in the top left of the image.

	// Origins, anchor positions and coordinates of the marker
	// increase in the X direction to the right and in
	// the Y direction down.
	/*var image = {
  	url: 'img/olimpiadas.png',
  	// This marker is 20 pixels wide by 32 pixels tall.
  	size: new google.maps.Size(40, 80),
  	// The origin for this image is 0,0.
  	origin: new google.maps.Point(0,0),
  	// The anchor for this image is the base of the flagpole at 0,32.
  	anchor: new google.maps.Point(0, 32)
	};*/
	
	// Shapes define the clickable region of the icon.
	// The type defines an HTML &lt;area&gt; element 'poly' which
	// traces out a polygon as a series of X,Y points. The final
	// coordinate closes the poly by connecting to the first
	// coordinate.
	var shape = {
  	coords: [1, 1, 1, 40, 80, 20, 18 , 1],
    	type: 'poly'
	};
	
	var bounds = new google.maps.LatLngBounds();
	var infowindow = new google.maps.InfoWindow();
	var marker = null;
	for (var i = 0; i < users.length; i++) {
		
  	var user = users[i];
  	var latLong = new google.maps.LatLng(user[1], user[2]);
  	bounds.extend(latLong);
  	
    var idiomas = "";
    for(var j=0; j<user[4].length; j++){
      idiomas = idiomas + "<p><b>Idioma "+(j+1)+": </b> "+user[4][j]+"</p> ";
    }

  	var actividades = "";
  	for(var j=0; j<user[5].length; j++){
      actividades = actividades + "<p><b>Actividad "+(j+1)+": </b> "+user[5][j]+"</p> ";
  	}

  	//Crear el marcador
  	marker = new google.maps.Marker({
      	position: latLong,
      	map: map,
      	//icon: image,
      	shape: shape,
      	title: user[0],
      	zIndex: user[6],
      	contenido:
	        "<div id=\"content\">"+
	            "<h4>"+user[0]+"</h4>"+
	                "<div id=\"bodyContent\">" +
	                    "<p><b>Email: </b>"+user[3]+"</p>" +
	                    idiomas + actividades +
	                "</div>"+
	        "</div>"
  	});
    
    //Añadir listener a cada marker para que abra su información
  	google.maps.event.addListener(marker, "click", function () { 
  		infowindow.setContent(this.contenido);
		infowindow.open(map, this);
  	});
  	
  	//Se añade al array de markers para agrupar
  	markers.push(marker);
  	
	}
	
	//map.fitBounds(bounds);
	
	//Se agrupan los markers
	var markerCluster = new MarkerClusterer(map, markers);
  	
}

/* Función que recoge los parámetros pasados por la url. */
function getUrlVars() {

	var vars = [], hash; 
	var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');     
	for(var i = 0; i < hashes.length; i++) {         
		hash = hashes[i].split('=');         
		vars.push(hash[0]);          
		vars[hash[0]] = hash[1];      
	}     
	return vars; 

}

/* Función que inserta el código html de la información de los usuarios.
   Esta información se mostrará a la derecha del mapa. */
function cargarInformacionUsuarios(users){

	var codigoHtml = "";

	for (var i = 0; i < users.length; i++) {
  		
    	var user = users[i];
    	
      var idiomas = "";
      for(var j=0; j<user[4].length; j++){
        idiomas = idiomas + "<p><b>Idioma "+(j+1)+": </b> "+user[4][j]+"</p> ";
      }

    	var actividades = "";
    	for(var j=0; j < user[5].length; j++){
    		actividades = actividades + "<p><b>Actividad "+(j+1)+": </b> "+user[5][j]+"</p> ";
    	}

        codigoHtml = codigoHtml + 
        	"<div id='divUsuario'"+i+" onmousemove=buscarUsuarioEnMapa("+i+");>"+
            	"<h4>"+user[0]+"</h4>"+
                "<div id='divActividadesUsuario'>" +
                    "<p><b>Email: </b>"+user[3]+"</p>" +
                    idiomas + actividades +
                "</div>"+
        	"</div>";
        
  	}
	
	$('#map-information').html(codigoHtml); 

}

function buscarUsuarioEnMapa(num){
	
	alert("Usuario :" + num);
	
}

$(document).ready(function(){

	$("#home").click(function(){
		
		//Recargamos la página de inicio
		//location.reload();

		document.location.href = "inicio.html";
		
    });
	
	$("#login").click(function(){
		
		//Cargamos el login en la página central de la pantalla de inicio
		$("#map-canvas").load('login.html');
		
    });

    $("#registro").click(function(){
	    
    	document.location.href = "registroDatos.html?idioma=" + idiomaSel;

	});

	$("#idiomaES").click(function(){

		idiomaSel = "es";
    	chgLang("es");

	});

	$("#idiomaEU").click(function(){

		idiomaSel = "eu";
    	chgLang("eu");

	});

	$("#idiomaEN").click(function(){

		idiomaSel = "en";
    	chgLang("en");

	});

	//Cuando se cambie la ciudad se realiza la nueva búsqueda
    $("#inputSearch").on('change', function(){
    	
	    fLocalizarLocalidad();
		 
	});

	//Añade un listener al campo search para el autocompletado de google
	inicializarCiudad("inputSearch");
	
});