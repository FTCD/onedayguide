/**
 * @author xabier
 */
 $(document).ready(function(){

 	inicializarCiudad(("inputSearch"));
 	
 	$("#btnSearch").click(function(){
 		
 		window.location.href = "mapa.html?localidad=" + $("#inputSearch").val();
 		
 	});
	
});