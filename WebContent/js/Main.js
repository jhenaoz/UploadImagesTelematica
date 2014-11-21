function work(){
	var respuesta;
	$.ajax({
        url: "http://localhost:8081/Proyecto_Talos/ws/hello",
        type: "GET",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
       	 respuesta =data;
        }
	});
	respuesta = JSON.stringify(respuesta);
//	$("#rcity").text() = respuesta.city;
//	$("#rcountry").text() =respuesta.country;
//	
}