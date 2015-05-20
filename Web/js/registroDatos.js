/**
 * @author Iban
 */
var idioma = getUrlVars()["idioma"];
var geocoder = new google.maps.Geocoder();

//Variables para las listas cargadas de BD
var aActividades = new Array();
var aDiasActividades = new Array();
var aHorariosActividades = new Array();
var aIdiomas = new Array();
var aNivelIdiomas = new Array();

$(document).ready(function(){

	$(document).on('click','#btnAceptar', function(e) {

		//Prevent the default action, which in this case is the form submission.
 		e.preventDefault();
 		//Serialize the form data and store to a variable.
	  	var formData = $("#formRegistroDatos").serialize(); 
	    /* I put the above code for check data before send to ajax*/
	    $.ajax({
	    	type: "GET",
	        url: dir + "/OneDayGuide/rest/usuario/registrarUsuario/"+JSON.stringify({formData}),
	        //data: JSON.stringify({ formVars: formData }),
	        //data: "{datos:" + JSON.stringify(formData) + "}",
 			dataType: "jsonp",
 			contentType: "application/jsonp",
	        success: function (data) {
	        	alert("SUCCESS");
	            if (data.success) {

	            } else {

	            }
	        },
	        failure: function(xhr, ajaxOptions, thrownError){
              alert(thrownError);
                        
          	},
          	cache: false,
          	async: false
    
	   	});

		return false;

	})
	
	$("form#formRegistroDatos #btnLimpiar").click(function() {
		
		//Limpiar el formulario
		$("#formRegistroDatos")[0].reset();

		//Se recarga la página para que la deje como al principio
		location.reload();

		//Al recargar la página no deshabilita estos combos
		$("#nivelIdioma1").prop('disabled',true);
		$("#disponibilidadDia1").prop('disabled',true);
		$("#disponibilidadHora1").prop('disabled',true);

	});
	
	$("#colaborador").change(function() {
		
        if($("#colaborador").is(':checked')) {	
        	$("#divDatosColaborador").show("slow");
        	//Se carga el combo de actividades.
        	fCargarCombo(aActividades, "actividad1", "registro.seleccionActividad");
        	fCargarCombo(aDiasActividades, "disponibilidadDia1", "registro.seleccionDisponibilidad");
        	fCargarCombo(aHorariosActividades, "disponibilidadHora1", "registro.seleccionHorario");
        	fCargarCombo(aIdiomas, "idioma1", "registro.seleccionIdioma");
        	fCargarCombo(aNivelIdiomas, "nivelIdioma1", "registro.seleccionNivel");
        }else{
        	$("#divDatosColaborador").hide("slow");
        }
        
    });
    
    //Cuando se añada dirección, se obtiene la latitud y longitud de la misma
    $("#direccion").on('change', function(){
    	
	    var direccion = $("#direccion").val();
		
		if(direccion.length>0){
			
			geocoder.geocode( { 'address': direccion}, function(results, status) {

			    if (status == google.maps.GeocoderStatus.OK) {

			    	$("#latitud").val(results[0].geometry.location.lat());
			    	$("#longitud").val(results[0].geometry.location.lng());
			    	$("#imgDireccionValida").show();
			    	$("#imgDireccionNoValida").hide();

			    } else {

			    	$("#imgDireccionValida").hide();
			    	$("#imgDireccionNoValida").show();
			    	if(status != google.maps.GeocoderStatus.ZERO_RESULTS){
			    		alert('Geocode was not successful for the following reason: ' + status);
			    	}/*else{
			    		alert("La dirección introducida no existe");
			    	}*/
			    }

		  	});
		  	
		}else{
			$("#imgDireccionValida").hide();
			$("#imgDireccionNoValida").hide();
		}
		 
	});
    
	$("#formRegistroDatos").validate({
	    
	    /*errorClass:'labelError',*/

	    errorPlacement: function(error, element) {

            /*element.before(error);
	        offset = element.offset();
	        error.css('left', offset.left);
	        error.css('top', offset.top - element.outerHeight());*/

	        if (element.attr("name") == "direccion"){
				if($("#imgDireccionValida").is(':visible')){
					error.insertAfter("#imgDireccionValida");
				} else if($("#imgDireccionNoValida").is(':visible')){
					error.insertAfter("#imgDireccionNoValida");
				} else {
			      	error.insertAfter(element);
			    }
	        } else if (element.attr("name") == "idioma1") {
		    	error.insertAfter("#anadirIdioma1");
		    } else if (element.attr("name") == "idioma2") {
		      	error.insertAfter("#eleminarIdioma2");
		    } else if (element.attr("name") == "actividad1" || element.attr("name") == "disponibilidadDia1") {
		      	error.insertAfter("#anadirActividad1");
		    } else if (element.attr("name") == "actividad2" || element.attr("name") == "disponibilidadDia2") {
		      	error.insertAfter("#eliminarActividad2");
		    } else if (element.attr("name") == "actividad3" || element.attr("name") == "disponibilidadDia3") {
		      	error.insertAfter("#eliminarActividad3");
		    } else if (element.attr("name") == "actividad4" || element.attr("name") == "disponibilidadDia4") {
		      	error.insertAfter("#eliminarActividad4");
		    } else if (element.attr("name") == "actividad5" || element.attr("name") == "disponibilidadDia5") {
		      	error.insertAfter("#eliminarActividad5");
		    } else if (element.attr("name") == "actividad6" || element.attr("name") == "disponibilidadDia6") {
		      	error.insertAfter("#eliminarActividad6");
		    } else {
		      	error.insertAfter(element);
		    }
        },

	    rules: {
	        nickname: { required: true, minlength: 6 },
	        email: { required: true, email: true },
	        password: { required: true, minlength: 6 },
	        passwordConfirm: { required: true, minlength: 6 },
	        ciudad: { 
	        	required: { 
	        		depends: function(element) {
            			return $("#colaborador").is(":checked");
        			}
        		}
        	},
        	direccion: { 
        		required: { 
	        		depends: function(element) {
            			return $("#colaborador").is(":checked");
        			}
        		}
        	},
        	idioma1: { 
        		required: { 
	        		depends: function(element) {
            			return $("#colaborador").is(":checked");
        			}
        		}
        	},
        	nivelIdioma1: { 
        		required: { 
	        		depends: function(element) {
	        			return $("#idioma1 option:selected");
        			}
        		}
        	},
        	nivelIdioma2: { 
        		required: { 
	        		depends: function(element) {
	        			return $("#idioma2 option:selected");
        			}
        		}
        	},
        	actividad1: { 
        		required: { 
	        		depends: function(element) {
            			return $("#colaborador").is(":checked");
        			}
        		}
        	},
        	disponibilidadDia1: { 
        		required: { 
	        		depends: function(element) {
            			return $("#actividad1 option:selected");
        			}
        		}
        	},
        	disponibilidadHora1: { 
        		required: { 
	        		depends: function(element) {
            			return $("#disponibilidadDia1 option:selected");
        			}
        		}
        	},
        	disponibilidadDia2: { 
        		required: { 
	        		depends: function(element) {
            			return $("#actividad2 option:selected");
        			}
        		}
        	},
        	disponibilidadHora2: { 
        		required: { 
	        		depends: function(element) {
            			return $("#disponibilidadDia2 option:selected");
        			}
        		}
        	},
        	disponibilidadDia3: { 
        		required: { 
	        		depends: function(element) {
            			return $("#actividad3 option:selected");
        			}
        		}
        	},
        	disponibilidadHora3: { 
        		required: { 
	        		depends: function(element) {
            			return $("#disponibilidadDia3 option:selected");
        			}
        		}
        	},
        	disponibilidadDia4: { 
        		required: { 
	        		depends: function(element) {
            			return $("#actividad4 option:selected");
        			}
        		}
        	},
        	disponibilidadHora4: { 
        		required: { 
	        		depends: function(element) {
            			return $("#disponibilidadDia4 option:selected");
        			}
        		}
        	},
        	disponibilidadDia5: { 
        		required: { 
	        		depends: function(element) {
            			return $("#actividad5 option:selected");
        			}
        		}
        	},
        	disponibilidadHora5: { 
        		required: { 
	        		depends: function(element) {
            			return $("#disponibilidadDia5 option:selected");
        			}
        		}
        	},
        	disponibilidadDia6: { 
        		required: { 
	        		depends: function(element) {
            			return $("#actividad6 option:selected");
        			}
        		}
        	},
        	disponibilidadHora6: { 
        		required: { 
	        		depends: function(element) {
            			return $("#disponibilidadDia6 option:selected");
        			}
        		}
        	}
        	
	    },
	    messages: {
	        nickname: { 
	        	required: "Debe introducir su nombre de usuario.", 
	        	minlength: "El nombre de usuario debe tener 6 caracteres como mínimo."
	        },
	        email : { 
	        	required: "Debe introducir su email.", 
	        	email:"Debe introducir un email válido." 
	        },
	        password : "Debe introducir una contraseña.",
	        passwordConfirm : "Debe introducir una contraseña.",
	        ciudad: {
	        	required: "Debe introducir una ciudad."
	        },
	        direccion: {
	        	required: "Debe introducir una dirección donde poder quedar."
	        },
	        idioma1: {
	        	required: "Debe introducir un idioma."
	        },
	        nivelIdioma1: {
	        	required: "Debe introducir el nivel del idioma seleccionado."
	        },
	        nivelIdioma2: {
	        	required: "Debe introducir el nivel del idioma seleccionado."
	        },
	        actividad1: {
	        	required: "Debe introducir una actividad."
	        },
	        disponibilidadDia1: {
	        	required: "Debe introducir la disponibilidad para la actividad seleccionada."
	        },
	        disponibilidadHora1: {
	        	required: "Debe introducir la disponibilidad horaria para la actividad seleccionada."
	        },
			disponibilidadDia2: {
	        	required: "Debe introducir la disponibilidad para la actividad seleccionada."
	        },
	        disponibilidadHora2: {
	        	required: "Debe introducir la disponibilidad horaria para la actividad seleccionada."
	        },
	        disponibilidadDia3: {
	        	required: "Debe introducir la disponibilidad para la actividad seleccionada."
	        },
	        disponibilidadHora3: {
	        	required: "Debe introducir la disponibilidad horaria para la actividad seleccionada."
	        },
	        disponibilidadDia4: {
	        	required: "Debe introducir la disponibilidad para la actividad seleccionada."
	        },
	        disponibilidadHora4: {
	        	required: "Debe introducir la disponibilidad horaria para la actividad seleccionada."
	        },
	        disponibilidadDia5: {
	        	required: "Debe introducir la disponibilidad para la actividad seleccionada."
	        },
	        disponibilidadHora5: {
	        	required: "Debe introducir la disponibilidad horaria para la actividad seleccionada."
	        },
	        disponibilidadDia6: {
	        	required: "Debe introducir la disponibilidad para la actividad seleccionada."
	        },
	        disponibilidadHora6: {
	        	required: "Debe introducir la disponibilidad horaria para la actividad seleccionada."
	        }
	    }

	});

	chgLang(idioma);

	//Añade un listener al campo direccion para el autocompletado de google
	inicializarDireccion("direccion");

	//Cargar la lista de actividades
    fObtenerLista("actividades", aActividades);

    //Cargar la lista de días y horarios de las actividades
	fObtenerLista("diasActividades", aDiasActividades);
	fObtenerLista("horariosActividades", aHorariosActividades);
	
	//Cargar la lista de idiomas
	fObtenerLista("idiomas", aIdiomas);
	
	//Cargar la lista de niveles de los idiomas
	fObtenerLista("nivelesIdiomas", aNivelIdiomas);
	
});

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

/* Función que obtiene la lista pasada por parámetro de de BD. Para
ello realiza una llamada ajax al servicio web correspondiente. */
function fObtenerLista(coleccion, array){

	try {
			
	    $.ajax({
	         type: "GET",
	         url: dir + "/OneDayGuide/rest/lista/getColeccion/"+coleccion,
	         dataType: "json",
	         contentType: "application/json; charset=utf-8",
	         timeout: 60000,
	         async: false,
	         success: function(data){ 
	             fCargarArray(data, array);
	         },
	         failure: function(xhr, ajaxOptions, thrownError){
	             alert(thrownError);
	         },
	         cache: false
	     });
     
	 }catch (e) {
	     alert('failed to call web service. Error: ' + e);
	 }

}

function fCargarArray(data, array){
	
	for (var i = 0; i < data.length; i++) {
		
		var id = data[i]._id;
		var desc;
		if(idioma=="en-Idioma"){
			desc = data[i].descEn;
		}else if(idioma=="eu-Idioma"){
			desc = data[i].descEu;
		}else{
			desc = data[i].descCa;
		}
		//alert(desc);
		array[i] = [id, desc];
		
    }

}

function fCargarCombo(array, nomCombo, keyOpcionVacia){

	//var selectBox = document.getElementById("actividad1");
	var options = "";
	options += "<option value='' selected='' class='i18n' id='"+keyOpcionVacia+"'></option>"
	for(var i = 0; i < array.length; i++){
	  	//options += "<option value='"+array[i][0]+"' class='i18n' id='"+array[i][1]+"'></option>";
	  	options += "<option value='"+array[i][0]+"'>"+array[i][1]+"</option>";
	}

	$("#"+nomCombo).html(options);

	//Llamada al cambio de idioma para que cargue los datos obtenidos de BD
	chgLang(idioma);

}

/*Función que añade el código html necesario para añadir un nuevo idioma.*/
function anadirIdioma(numElemento){

	var idIdiomaNuevo = "idioma"+numElemento;
	var idNivelIdiomaNuevo = "nivelIdioma"+numElemento;
	var idAnadirIdiomaNuevo = "anadirIdioma"+numElemento;
	var idEliminarIdiomaNuevo = "eliminarIdioma"+numElemento;
	var comboIdiomaAnt = "#idioma"+(numElemento-1);
	var comboNivelIdiomaAnt = "#nivelIdioma"+(numElemento-1);
	var linkAnadirIdioma = "#anadirIdioma"+(numElemento-1);
	var linkEliminarIdioma = "#eliminarIdioma"+(numElemento-1);
	var parrafoIdioma = "#pIdioma"+(numElemento-1);

	var onchangeIdioma = "activarDesactivarCombo('idioma"+numElemento+"','nivelIdioma"+numElemento+"');";
	var onchangeNivelIdioma = "activarDesactivarCombo('nivelIdioma"+numElemento+"','idioma"+(numElemento+1)+"');";

	var codHtmlIdioma = "<p id=pIdioma"+numElemento+">"+
		"<label for='"+idIdiomaNuevo+"' class='i18n' id='registro.idioma'></label><br>"+
		"<select name='"+idIdiomaNuevo+"' id='"+idIdiomaNuevo+"' onChange="+onchangeIdioma+">"+
		"</select>&nbsp;"+
		"<select name='"+idNivelIdiomaNuevo+"' id='"+idNivelIdiomaNuevo+"' disabled='disabled' onChange="+onchangeNivelIdioma+">"+
		"</select>&nbsp;"+
		"<a title='Añadir' id='"+idAnadirIdiomaNuevo+"' name='"+idAnadirIdiomaNuevo+"' onClick='anadirIdioma("+(numElemento+1)+");'>"+
		"<img src='img/add.png'/></a>&nbsp;"+
		"<a title='Eliminar' id='"+idEliminarIdiomaNuevo+"' name='"+idEliminarIdiomaNuevo+"' onClick='eliminarIdioma("+numElemento+");'>"+
		"<img src='img/delete.png'/></a></p>";

	$(codHtmlIdioma).insertAfter($(parrafoIdioma));
	$(linkAnadirIdioma).hide();
	$(linkEliminarIdioma).hide();

	if(numElemento==3){
		$("#"+idAnadirIdiomaNuevo).remove();
	}

	if($(comboIdiomaAnt).val()=="" || $(comboNivelIdiomaAnt).val()==""){
		$("#"+idIdiomaNuevo).attr("disabled", true);
		$("#"+idNivelIdiomaNuevo).attr("disabled", true);
	}

	chgLang(idioma);

	fCargarCombo(aIdiomas, "idioma"+numElemento, "registro.seleccionIdioma");
	fCargarCombo(aNivelIdiomas, "nivelIdioma"+numElemento, "registro.seleccionNivel");
	
}

/*Función que añade el código html necesario para eliminar un idioma.*/
function eliminarIdioma(numElemento){

	var parrafoIdioma = "#pIdioma"+numElemento;

	$(parrafoIdioma).remove();

	var imgAnadirIdioma = "#anadirIdioma"+(numElemento-1);
	var imgEliminarIdioma = "#eliminarIdioma"+(numElemento-1);

	$(imgAnadirIdioma).show();
	if((numElemento-1)!=1){
		$(imgEliminarIdioma).show();
	}

}	

/*Función que añade el código html necesario para añadir una nueva actividad.*/
function anadirActividad(numElemento){
			
	var idActividadNueva = "actividad"+numElemento;
	var idDisponibilidadDiaNueva = "disponibilidadDia"+numElemento;
	var idDisponibilidadHoraNueva = "disponibilidadHora"+numElemento;

	var idAnadirActividadNueva = "anadirActividad"+numElemento;
	var idEliminarActividadNueva = "eliminarActividad"+numElemento;
	var comboActividadAnt = "#actividad"+(numElemento-1);
	var comboDisponibilidadHoraAnt = "#disponibilidadHora"+(numElemento-1);
	var comboDisponibilidadDiaAnt = "#disponibilidadDia"+(numElemento-1);
	var linkAnadirActividad = "#anadirActividad"+(numElemento-1);
	var linkEliminarActividad = "#eliminarActividad"+(numElemento-1);

	var parrafoActividad = "#pActividad"+(numElemento-1);

	var onchangeActividad = "activarDesactivarCombo('actividad"+numElemento+"','disponibilidadDia"+numElemento+"');";
	var onchangeDisponibilidadDia = "activarDesactivarCombo('disponibilidadDia"+numElemento+"','disponibilidadHora"+numElemento+"');";
	var onchangeDisponibilidadHora = "activarDesactivarCombo('disponibilidadHora"+numElemento+"','actividad"+(numElemento+1)+"');";

	var codHtmlIdioma = "<p id='pActividad"+numElemento+"'>"+
		"<label for='"+idActividadNueva+"' class='i18n' id='registro.actividad'></label><br>"+
		"<select name='"+idActividadNueva+"' id='"+idActividadNueva+"' onChange="+onchangeActividad+">"+
		"</select>&nbsp;"+
		"<select name='"+idDisponibilidadDiaNueva+"' id='"+idDisponibilidadDiaNueva+"' disabled='disabled' onChange="+onchangeDisponibilidadDia+">"+
		"</select>&nbsp;"+
		"<select name='"+idDisponibilidadHoraNueva+"' id='"+idDisponibilidadHoraNueva+"' disabled='disabled' onChange="+onchangeDisponibilidadHora+">"+
		"</select>&nbsp;"+
		"<a title='Añadir' id='"+idAnadirActividadNueva+"' name='"+idAnadirActividadNueva+"' onClick='anadirActividad("+(numElemento+1)+");'>"+
		"<img src='img/add.png'/></a>&nbsp;"+
		"<a title='Eliminar' id='"+idEliminarActividadNueva+"' name='"+idEliminarActividadNueva+"' onClick='eliminarActividad("+numElemento+");'>"+ 
		"<img src='img/delete.png'/>"+
		"</a></p>";

	$(codHtmlIdioma).insertAfter($(parrafoActividad));
	$(linkAnadirActividad).hide();
	$(linkEliminarActividad).hide();

	if(numElemento==6){
		$("#"+idAnadirActividadNueva).remove();
	}

	if($(comboActividadAnt).val()=="" || $(comboDisponibilidadDiaAnt).val()=="" || $(comboDisponibilidadHoraAnt).val()==""){
		$("#"+idActividadNueva).attr("disabled", true);
		$("#"+idDisponibilidadDiaNueva).attr("disabled", true);
		$("#"+idDisponibilidadHoraNueva).attr("disabled", true);
	}

	chgLang(idioma);

	fCargarCombo(aActividades, "actividad"+numElemento, "registro.seleccionActividad");
	fCargarCombo(aDiasActividades, "disponibilidadDia"+numElemento, "registro.seleccionDisponibilidad");
	fCargarCombo(aHorariosActividades, "disponibilidadHora"+numElemento, "registro.seleccionHorario");
	
}

/*Función que añade el código html necesario para eliminar una actividad.*/
function eliminarActividad(numElemento){

	var parrafoActividad = "#pActividad"+numElemento;

	$(parrafoActividad).remove();

	var linkAnadirActividad = "#anadirActividad"+(numElemento-1);
	var linkEliminarActividad = "#eliminarActividad"+(numElemento-1);

	$(linkAnadirActividad).show();
	if((numElemento-1)!=1){
		$(linkEliminarActividad).show();
	}

}

function activarDesactivarCombo(combo, comboADesact, array, keyOpcionVacia){

	if($("#"+combo).val()==""){
			$("#"+comboADesact).val("");
			$("#"+comboADesact).prop('disabled', 'disabled');
		}else{
			$("#"+comboADesact).removeAttr("disabled");
		}

}