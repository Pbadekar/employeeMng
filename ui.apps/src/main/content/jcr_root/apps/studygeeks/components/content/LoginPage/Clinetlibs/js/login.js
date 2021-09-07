console.log("I am here");


function Logindeatils(flag) {

var JSONObj = {
    fname : $('#name').val(),
    lname : $('#lname').val(),
    add : $('#add').val(),
    id : $('#name').val()+'.'+$('#lname').val(),
	pwd : $('#pwd').val(),
    flg : flag

};


var data = JSON.stringify(JSONObj);



var a = data.toString();

  console.log("flag" + flag +" "+a );
	$.ajax({
		url : '/prakash/addNodes',
		async : true,
       data: {
			data: data
		},
		type : "POST",

		success : function(response) {
			console.log('Success ' + response);

			alert(""+ response);

		},

		error : function(jqXHR, textStatus, errorThrown) {
			console.log('Error Occurred' + errorThrown);
			return res = errorThrown;
			// alert("Something went wrong....Try again.");
		}

	});

}