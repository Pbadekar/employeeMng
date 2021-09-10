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

            if (response=="Validated"){

                console.log("I am here");
			alert(""+ response);
			window.location.href ='http://localhost:4502/content/studygeeks/us/en/LoginPage/welcomePage.html'
            }



		},

		error : function(jqXHR, textStatus, errorThrown) {
			console.log('Error Occurred' + errorThrown);
			return res = errorThrown;
			// alert("Something went wrong....Try again.");
		}

	});

}