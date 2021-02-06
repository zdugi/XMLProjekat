const LoginPage = Vue.component("login-page", {
    template: `
    <div style="max-width: 500px; margin:auto; ">
    <h1 style="max-width: 500px; margin:auto; margin-top:50px; margin-bottom:100px;"><b>Login page</b></h1>
    		<form id = "log_elem" >
    			<label for="email">Email </label>
    	        <input v-model="email" type="text" id="email"/>
    	        <br><br>
    	        <label for="password">Lozinka </label>
    	        <input v-model="password" type="password" id="password"/>
    	        <br><br>
    	        <input type="button" value="Log in" v-on:click="login" style = "background-color: #D8D8F6"/>
    	        <br><br>
    	        <p>Nemate nalog? <router-link to="/register">Registrujte se</router-link></p>
    		</form>
    		<p class="text-danger"><i style="display:none" id="ikonica" class="fas fa-exclamation-circle"></i><span id="greskaKI1"></span></p>
    	</div>
    `,
    methods: {
            login(){
            		this.sakrij_greske_log();
            		if(this.proveraUnosaLog()){
            		var xmlBody = "<korisnik_login email=" + "\"" + this.email + "\"" + " password=" + "\"" + this.password + "\"" +  ">" +
                                  "</korisnik_login>";

            			axios({
                          	method : 'post',
                            url : '/api/auth/login',
                            auth:{username:'my-trusted-client',password:'secret'},
                            headers: {"Content-type": "application/xml; charset=utf-8"},
                            data:xmlBody
                        }).then(function(response){
                          	console.log(response.data);
                          	xmlDoc = $.parseXML(response.data);
                            accessToken = $(xmlDoc).find('accessToken').text();
                            let jwtData = accessToken.split('.')[1]
                            let decodedJwtJsonData = window.atob(jwtData)
                            let decodedJwtData = JSON.parse(decodedJwtJsonData)

                          	localStorage.setItem('currentUser', JSON.stringify({
                                        username: this.email.value,
                                        roles: decodedJwtData['User-role'],
                                        token: accessToken
                                      }));
                           	//document.cookie = "user=" + response.data.access_token;
                           	if(decodedJwtData['User-role'] === "ROLE_POVERENIK"){
                           	    router.push("/poverenik")
                           	    //location.reload();
                           	    //router.push("/")
                           	}
                           	else{
                           	    //location.reload();
                           	    router.push("/gradjanin")
                           	    //router.push("/")
                           	}
                        }).catch((error)=>{
                          	document.getElementById("greskaKI1").innerText = "Pogresan imejl/lozinka!";
                       		document.getElementById("ikonica").style.display = "inline";
                        });
            			}
            			console.log("NESTO");
            			},
            		sakrij_greske_log(){
            			document.getElementById("greskaKI1").innerText = "";
            			document.getElementById("ikonica").style.display = "none";

            		},
            		proveraUnosaLog(){
                       	if(this.email == ''){
                       		document.getElementById("greskaKI1").innerText = "Unošenje email-a je obavezno!";
                       		document.getElementById("ikonica").style.display = "inline";
                       		return false;
                       	}
                       	if(this.password == ''){
                       		document.getElementById("greskaKI1").innerText = "Unošenje lozinke je obavezno!";
                       		document.getElementById("ikonica").style.display = "inline";
                       		return false;
                       	}

                       	return true;
            			}
        },
    data () {
            return {
    			email:'',
    			password:''
    		}
    	}
})