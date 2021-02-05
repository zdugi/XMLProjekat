const LoginPage = Vue.component('login-page', {
    data() {
        return {
            email: '',
            sifra: ''
        }
    },
    template: `
    <div class="registration-box">
        <h2>Prijava</h2>
        <form method="post" @submit="checkForm"><input v-model="email" minlength="3" type="email" name="email" placeholder="Email" required/>
            <input v-model="sifra" minlength="3" type="password" name="password" placeholder="Sifra" required/>
            <router-link to="/register">Registracija</router-link>
            <button style="margin-top: 20px; width: 100%;">Prijavi se</button>
        </form>
    </div>
    `,
    methods: {
        checkForm(e) {
        var xmlBody = '<korisnik_login ' +
                                    'email="' + this.email + '" ' +
                                    'password="' + this.sifra + '" ' +
                                  '/>';

                    axios.post('/api/auth/login', xmlBody, { headers: {'Content-Type': 'application/xml'} }).then(
                    response => {
                        // this.$router.push({ name: '/login' });
                        var xmlDoc = $.parseXML(response.data);
                        var accessToken = $(xmlDoc).find('accessToken').text();
                        var jwtData = accessToken.split('.')[1]
                        var decodedJwtJsonData = window.atob(jwtData)
                        var decodedJwtData = JSON.parse(decodedJwtJsonData)

                        localStorage.setItem('currentUser', JSON.stringify({
                            username: this.email,
                            roles: decodedJwtData['User-role'],
                            token: accessToken
                        }));

                        axios.defaults.headers.common['Authorization'] = 'Bearer ' + accessToken;

                        if (decodedJwtData['User-role'] == 'ROLE_CITIZEN')
                            this.$router.push({ path: '/citizen' });
                        else
                            this.$router.push({ path: '/official' });

                        console.log(decodedJwtData['User-role']);
                    });

            e.preventDefault();
        }
    }
})